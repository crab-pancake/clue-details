/*
 * Copyright (c) 2024, Zoinkwiz <https://github.com/Zoinkwiz>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.cluedetails;

import com.cluedetails.filters.ClueTier;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.Getter;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ItemDespawned;
import net.runelite.api.events.ItemSpawned;

import java.util.*;
import net.runelite.api.gameval.ItemID;

@Singleton
public class ClueGroundManager
{
	private final Client client;

	private final ClueDetailsPlugin clueDetailsPlugin;
	@Getter
	private final ClueGroundSaveDataManager clueGroundSaveDataManager;
	private final Set<Tile> itemHasSpawnedOnTileThisTick = new HashSet<>();
	@Getter
	private final List<ClueInstance> despawnedClueQueueForInventoryCheck = new ArrayList<>();
	private final int MAX_DESPAWN_TIMER = 6100;
	private Zone lastZone;
	private Zone currentZone;
	private final WorldPointToClueInstances trackedClues;

	private final Set<Tile> resetEasyToEliteThisTick = new HashSet<>();

	@Inject
	public ClueGroundManager(Client client, ClueGroundSaveDataManager clueGroundSaveDataManager, ClueDetailsPlugin clueDetailsPlugin)
	{
		this.client = client;
		this.clueDetailsPlugin = clueDetailsPlugin;
		this.clueGroundSaveDataManager = clueGroundSaveDataManager;

		trackedClues = new WorldPointToClueInstances(client, clueDetailsPlugin);
	}

	public void startUp()
	{
		loadStateFromConfig();
	}

	public void shutDown()
	{
		// Need to clear incase the player toggles the plugin on/off on the same tick
		resetEasyToEliteThisTick.clear();
	}

	public SortedSet<ClueInstance> getAllGroundCluesOnWp(WorldPoint worldPoint)
	{
		return trackedClues.getAllCluesAtWorldPoint(worldPoint);
	}

	public void onItemSpawned(ItemSpawned event)
	{
		TileItem item = event.getItem();
		Tile tile = event.getTile();

		if (!Clues.isClue(item.getId(), clueDetailsPlugin.isDeveloperMode())) return;

		// If easy-elite task, we just override
		if (!Clues.isBeginnerOrMasterClue(item.getId(), clueDetailsPlugin.isDeveloperMode()))
		{
			resetEasyToEliteThisTick.add(tile);
			return;
		}

		ClueInstance inventoryClue = clueDetailsPlugin.getClueInventoryManager().getClueByClueItemId(item.getId());
		// If clue in inventory AND new clue appeared with fresh despawn timer, it must be the inventory item being dropped
		if (isNewGroundClue(item.getId(), item.getDespawnTime()) && inventoryClue != null)
		{
			ClueInstance newGroundClue = new ClueInstance(inventoryClue.getClueIds(), inventoryClue.getItemId(), tile.getWorldLocation(), item, client.getTickCount());
			trackedClues.addClue(newGroundClue);
			return;
		}

		// Handle items spawned on tile without aligned times and not dropped
		itemHasSpawnedOnTileThisTick.add(tile);
	}

	private boolean isNewGroundClue(int itemID, int despawnTick)
	{
		int ticksToDespawn = despawnTick - client.getTickCount();

		if (ticksToDespawn == MAX_DESPAWN_TIMER) return true;

		return clueDetailsPlugin.isDeveloperMode() &&
			Clues.DEV_MODE_IDS.contains(itemID) &&
			despawnTick >= 300;
	}

	public void onItemDespawned(ItemDespawned event)
	{
		TileItem item = event.getItem();
		if (!Clues.isClue(item.getId(), clueDetailsPlugin.isDeveloperMode())) return;
		WorldPoint location = event.getTile().getWorldLocation();

		// Only process events where the actual item has just despawned
		// This helps to retain identified clues
		if (event.getItem().getId() == ItemID.TRAIL_CLUE_BEGINNER
			|| event.getItem().getId() == ItemID.TRAIL_CLUE_MASTER)
		{
			if (ClueDetailsPlugin.getCurrentPlane() != location.getPlane()) return;
		}

		if (!Clues.isBeginnerOrMasterClue(item.getId(), clueDetailsPlugin.isDeveloperMode()))
		{
			ClueInstance clueInstance = new ClueInstance(List.of(), item.getId(), location, item, client.getTickCount());
			trackedClues.removeClue(clueInstance);
			return;
		}

		List<ClueInstance> cluesAtLocation = trackedClues.getBeginnerAndMasterCluesAtWorldPoint(location);

		// Catch despawn in vicinity
		if (cluesAtLocation == null) return;

		// If no logging out/reloading and such happens, despawnTime remains off by 1, so need to account for it
		if (item.getDespawnTime() - client.getTickCount() <= 1)
		{
			Optional<ClueInstance> optionalClue = cluesAtLocation.stream()
				.filter((clue) -> clue.getTileItem() == item)
				.findFirst();
			optionalClue.ifPresent((trackedClues::removeClue));
			return;
		}

		if (trackedClues.getTileAtWorldPoint(location) == null)
		{
			return;
		}

		// If despawned on a tile still in the scene, AND hasn't timed out, we might have:
		// 1. Picked up the clue
		// 2. Done nothing, clue is still there just with a new ID
		// We know it's 2 if we've gone from 5 zones distance to 4 zones distance
		Zone clueZone = new Zone(location);
		Zone currentZone = new Zone(client.getLocalPlayer().getWorldLocation());
		if (lastZone != null)
		{
			int distFromLastZone = clueZone.maxDistanceTo(lastZone);
			int distFromCurrentZone = clueZone.maxDistanceTo(currentZone);
			if (distFromLastZone == 4 && distFromCurrentZone == 3)
			{
				return;
			}
		}

		// Not gone over a zone to load, probably picked up
		Optional<ClueInstance> optionalClue = cluesAtLocation.stream()
			.filter((clue) -> clue.getTileItem() == item)
			.findFirst();
		optionalClue.ifPresent(despawnedClueQueueForInventoryCheck::add);
		optionalClue.ifPresent((trackedClues::removeClue));
	}

	public Set<WorldPoint> getTrackedWorldPoints()
	{
		return trackedClues.getAllTrackedWorldPoints();
	}

	public void onGameTick()
	{
		currentZone = new Zone(client.getLocalPlayer().getWorldLocation());
		trackedClues.clearEmptyTiles(currentZone);

		for (Tile tile : itemHasSpawnedOnTileThisTick)
		{
			checkClueThroughRelativeDespawnTimers(tile);
		}
		itemHasSpawnedOnTileThisTick.clear();
		trackedClues.removeDespawnedClues();
		resetEasyToEliteThisTick.forEach(this::createEasyToEliteForTile);
		resetEasyToEliteThisTick.clear();

		lastZone = currentZone;
	}

	private void createEasyToEliteForTile(Tile tile)
	{

		List<TileItem> items = getClueItemsAtTile(tile);
		if (items == null) return;

		clearEasyToEliteCluesAtWorldPoint(tile.getWorldLocation());

		for (TileItem item : items)
		{
			if (Clues.isClue(item.getId(), clueDetailsPlugin.isDeveloperMode()) && !Clues.isBeginnerOrMasterClue(item.getId(), clueDetailsPlugin.isDeveloperMode()))
			{
				ClueInstance clueInstance = new ClueInstance(List.of(), item.getId(), tile.getWorldLocation(), item, client.getTickCount());
				trackedClues.addClue(clueInstance);
			}
		}

		SortedSet<ClueInstance> cluesAtWp = trackedClues.getAllCluesAtWorldPoint(tile.getWorldLocation());
		for (int i = 0; i < items.size(); i++)
		{
			TileItem tileItem = items.get(i);
			int finalI = i + 1;
			cluesAtWp.stream()
				.filter((clueInstance -> clueInstance.getTileItem() == tileItem))
				.findFirst()
				.ifPresent((clueInstance -> clueInstance.setSequenceNumber(finalI)));
		}
		List<ClueInstance> clues = new ArrayList<>(cluesAtWp);
		for (ClueInstance clueInstance : clues)
		{
			// Re-sorts the order of clues
			trackedClues.removeClue(clueInstance);
			trackedClues.addClue(clueInstance);
		}
	}

	private void checkClueThroughRelativeDespawnTimers(Tile tile)
	{
		WorldPoint tileWp = tile.getWorldLocation();

		List<TileItem> itemsOnTile = getTrackedItemsAtTile(tile);
		if (itemsOnTile.isEmpty())
		{
			return;
		}

		List<ClueInstance> storedClues = trackedClues.getBeginnerAndMasterCluesAtWorldPoint(tileWp);

		List<ClueInstance> updatedStoredClues = generateNewCluesOnTile(tileWp, storedClues, itemsOnTile);

		if (updatedStoredClues.isEmpty())
		{
			trackedClues.clearBeginnerAndMasterCluesAtWorldPoint(tileWp);
		}
		else
		{
			// If we didn't find an item for it on the tile, remove it
			updatedStoredClues.removeIf((clue) -> clue.getTileItem() == null);

			trackedClues.clearBeginnerAndMasterCluesAtWorldPoint(tileWp);

			// Update the stored clues
			for (ClueInstance updatedStoredClue : updatedStoredClues)
			{
				trackedClues.addClue(updatedStoredClue);
			}
		}
	}

	private List<ClueInstance> generateNewCluesOnTile(WorldPoint tileWp, List<ClueInstance> storedClues, List<TileItem> cluesOnTile)
	{
		int currentTick = client.getTickCount();

		if (storedClues.size() == 1 && cluesOnTile.size() == 1)
		{
			// We assume it is the same clue. It is possible for it to be swapped with another clue though in
			// another client/mobile, and this will be wrong
			if (storedClues.get(0).getDespawnTick() >= cluesOnTile.get(0).getDespawnTime())
			{
				storedClues.get(0).setTileItem(cluesOnTile.get(0));
				return storedClues;
			}
		}

		List<ClueInstance> sortedStoredClues = new ArrayList<>(storedClues);
		sortedStoredClues.sort(Comparator.comparingInt((clue) -> clue.getTicksToDespawnConsideringTileItem(currentTick)));

		// If only 1 of either but not both, less certainty as can't use diffs.
		// Could assume things like last clue expired, probs let's just assume nothing
		if (storedClues.size() <= 1 || cluesOnTile.size() == 1)
		{
			List<ClueInstance> actualCluesOnTile = new ArrayList<>();
			// Set tile's clues to just be unknown for all clues on tile
			for (TileItem groundClue : cluesOnTile)
			{
				ClueInstance clueInstance = new ClueInstance(List.of(),
					groundClue.getId(),
					tileWp,
					groundClue,
					client.getTickCount()
				);
				clueInstance.setTileItem(groundClue);
				actualCluesOnTile.add(clueInstance);
			}
			return actualCluesOnTile;
		}

		// Sort ground clues by despawn time ascending
		List<TileItem> sortedGroundClues = new ArrayList<>(cluesOnTile);
		sortedGroundClues.removeIf((tileItem -> tileItem.getDespawnTime() > sortedStoredClues.get(sortedStoredClues.size() - 1).getDespawnTick()));
		sortedGroundClues.sort(Comparator.comparingInt(TileItem::getDespawnTime));

		Map<Integer, List<TileItem>> groundItemsByItemID = sortedGroundClues.stream()
			.collect(Collectors.groupingBy(TileItem::getId, LinkedHashMap::new, Collectors.toList()));
		Map<Integer, List<ClueInstance>> sortedItemsByItemID = sortedStoredClues.stream()
			.collect(Collectors.groupingBy(ClueInstance::getItemId, LinkedHashMap::new, Collectors.toList()));

		for (Integer itemID : groundItemsByItemID.keySet())
		{
			if (sortedItemsByItemID.get(itemID) == null) continue;
			findMatchingClues(sortedItemsByItemID.get(itemID), groundItemsByItemID.get(itemID));
		}
		
		List<ClueInstance> foundClues = new ArrayList<>();

		cluesOnTile.stream()
			.map(tileItem -> sortedStoredClues.stream()
				.filter(clue -> clue.getTileItem() == tileItem)
				.findFirst()
				.orElseGet(() -> new ClueInstance(List.of(), tileItem.getId(), tileWp, tileItem, client.getTickCount())))
			.forEach(foundClues::add);

		return foundClues;
	}

	private void findMatchingClues(List<ClueInstance> sortedStoredClues, List<TileItem> sortedGroundClues)
	{
		// Need to loop diffs, and see matches in each.
		// For items with the same ID, no matter what item you click in a stack, you will always pick up the first item dropped in the stack
		// This means we don't need to worry about considering gaps where a clue has been taken from the middle of a stack.
		int minGroundItemFound = 0;

		if (sortedStoredClues.size() == 1 && sortedGroundClues.size() == 1)
		{
			// We assume it is the same clue. It is possible for it to be swapped with another clue though in
			// another client/mobile, and this will be wrong
			if (sortedStoredClues.get(0).getDespawnTick() >= sortedGroundClues.get(0).getDespawnTime())
			{
				sortedStoredClues.get(0).setTileItem(sortedGroundClues.get(0));
				return;
			}
		}

		for (int i = 0; i < sortedStoredClues.size() - 1; i++)
		{
			ClueInstance clueInstance1 = sortedStoredClues.get(i);
			ClueInstance clueInstance2 = sortedStoredClues.get(i + 1);

			TileItem groundClue1 = sortedGroundClues.get(minGroundItemFound);
			TileItem groundClue2 = sortedGroundClues.get(minGroundItemFound + 1);

			int currentStoredClueDiff = clueInstance2.getTimeToDespawnFromDataInTicks() - clueInstance1.getTimeToDespawnFromDataInTicks();
			int currentGroundClueDiff = groundClue2.getDespawnTime() - groundClue1.getDespawnTime();

			// Same diff, probs same thing
			if (currentGroundClueDiff != currentStoredClueDiff) continue;
			// If item will despawn later than the stored clue, it can't be it.
			if (groundClue1.getDespawnTime() > clueInstance1.getDespawnTick()) continue;
			if (groundClue2.getDespawnTime() > clueInstance2.getDespawnTick()) continue;
			clueInstance1.setTileItem(groundClue1);
			clueInstance2.setTileItem(groundClue2);
			minGroundItemFound++;
		}
	}

	private List<TileItem> getClueItemsAtTile(Tile tile)
	{
		List<TileItem> items = tile.getGroundItems();
		if (items == null)
		{
			return Collections.emptyList();
		}
		return items.stream()
			.filter(item -> Clues.isClue(item.getId(), clueDetailsPlugin.isDeveloperMode()))
			.collect(Collectors.toList());
	}

	private List<TileItem> getTrackedItemsAtTile(Tile tile)
	{
		List<TileItem> items = tile.getGroundItems();
		if (items == null)
		{
			return Collections.emptyList();
		}
		return items.stream()
			.filter(item -> Clues.isBeginnerOrMasterClue(item.getId(), clueDetailsPlugin.isDeveloperMode()))
			.collect(Collectors.toList());
	}

	class ClueInstanceComparator implements Comparator<ClueInstance>
	{
		@Override
		public int compare(ClueInstance o1, ClueInstance o2)
		{
			return Comparator
				.comparingLong(ClueInstance::getSequenceNumber)
				.thenComparingInt(ClueInstance::getDespawnTick)
				.compare(o1, o2);
		}
	}

	public TreeMap<ClueInstance, Integer> getClueInstancesWithQuantityAtWp(ClueDetailsConfig config, WorldPoint wp)
	{
		if (!trackedClues.getAllTrackedWorldPoints().contains(wp)) return null;

		SortedSet<ClueInstance> groundItemList = trackedClues.getAllCluesAtWorldPoint(wp);
		Map<ClueInstance, Integer> groundItemMap = new HashMap<>();

		if (config.collapseGroundCluesByTier())
		{
			groundItemMap = keepOldestTierClues(groundItemList);
		}
		else if (config.collapseGroundClues())
		{
			groundItemMap = keepOldestUniqueClues(groundItemList);
		}
		else
		{
			for (ClueInstance item : groundItemList)
			{
				groundItemMap.put(item, 1);
			}
		}

		// Sort ClueInstances by despawn time
		ClueInstanceComparator clueInstanceComparator = new ClueInstanceComparator();
		TreeMap<ClueInstance, Integer> clueInstancesWithQuantityAtWp = new TreeMap<>(clueInstanceComparator);
		clueInstancesWithQuantityAtWp.putAll(groundItemMap);
		return clueInstancesWithQuantityAtWp;
	}

	// Remove duplicate step clues, maintaining a count of the original amount of each
	public static Map<ClueInstance, Integer> keepOldestUniqueClues(SortedSet<ClueInstance> items)
	{
		Map<List<Integer>, ClueInstance> lowestValueItems = new HashMap<>();
		Map<List<Integer>, Integer> uniqueCount = new HashMap<>();

		for (ClueInstance item : items)
		{
			List<Integer> clueIds = item.getUniqueIds();

			if (!lowestValueItems.containsKey(clueIds)
				|| item.getDespawnTick() < lowestValueItems.get(clueIds).getDespawnTick())
			{
				lowestValueItems.put(clueIds, item);
				uniqueCount.put(clueIds, 1);
			}
			else
			{
				uniqueCount.put(clueIds, uniqueCount.get(clueIds) + 1);
			}
		}

		return lowestValueItems.values().stream()
			.collect(Collectors.toMap(item -> item, item -> uniqueCount.get(item.getUniqueIds())));
	}

	// Remove duplicate tier clues, maintaining a count of the original amount of each
	public static Map<ClueInstance, Integer> keepOldestTierClues(SortedSet<ClueInstance> items)
	{
		Map<ClueTier, ClueInstance> lowestValueItems = new HashMap<>();
		Map<ClueTier, Integer> uniqueCount = new HashMap<>();

		for (ClueInstance item : items)
		{
			ClueTier tier = item.getTier();

			if (!lowestValueItems.containsKey(tier)
				|| item.getDespawnTick() < lowestValueItems.get(tier).getDespawnTick())
			{
				lowestValueItems.put(tier, item);
				uniqueCount.put(tier, 1);
			}
			else
			{
				uniqueCount.put(tier, uniqueCount.get(tier) + 1);
			}
		}

		return lowestValueItems.values().stream()
			.collect(Collectors.toMap(item -> item, item ->	uniqueCount.get(item.getTier())));
	}

	public void clearEasyToEliteCluesAtWorldPoint(WorldPoint wp)
	{
		trackedClues.clearEasyToEliteCluesAtWorldPoint(wp);
	}

	public void saveStateToConfig()
	{
		clueGroundSaveDataManager.saveStateToConfig(trackedClues.getAllClues());
	}

	public void loadStateFromConfig()
	{
		trackedClues.clearAllClues();
		overwriteGroundClues(clueGroundSaveDataManager.loadStateFromConfig());
	}

	private void overwriteGroundClues(Map<WorldPoint, List<ClueInstance>> newGroundClues)
	{
		// Iterate over each list of ClueInstances in the map.
		for (List<ClueInstance> clueInstances : newGroundClues.values())
		{
			for (ClueInstance clueInstance : clueInstances)
			{
					trackedClues.addClue(clueInstance);
			}
		}
	}
}
