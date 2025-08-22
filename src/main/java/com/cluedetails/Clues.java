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

import static com.cluedetails.ClueDetailsConfig.CLUE_ITEMS_CONFIG;

import com.cluedetails.filters.ClueTier;
import com.cluedetails.filters.OrRequirement;
import com.google.common.collect.ImmutableList;
import com.google.gson.reflect.TypeToken;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.ItemID;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.cluescrolls.clues.MapClue;
import net.runelite.client.plugins.cluescrolls.clues.hotcold.HotColdLocation;
import net.runelite.client.util.Text;

@Getter
public class Clues
{
	public static final List<Clues> CLUES = ImmutableList.of(
		new Clues(0, "Ranael: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_ANAGRAM_0, List.of(new WorldPoint(3316, 3163, 0))),
		new Clues(1, "Archmage Sedridor: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_ANAGRAM_1, List.of(new WorldPoint(3109, 3160, 0))),
		new Clues(2, "Apothecary: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_ANAGRAM_2, List.of(new WorldPoint(3195, 3404, 0))),
		new Clues(3, "Doric: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_ANAGRAM_3, List.of(new WorldPoint(2952, 3452, 0))),
		new Clues(4, "Brian: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_ANAGRAM_4, List.of(new WorldPoint(3027, 3249, 0))),
		new Clues(5, "Veronica: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_ANAGRAM_5, List.of(new WorldPoint(3110, 3330, 0))),
		new Clues(6, "Gertrude: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_ANAGRAM_6, List.of(new WorldPoint(3150, 3409, 0))),
		new Clues(7, "Hairdresser: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_ANAGRAM_7, List.of(new WorldPoint(2945, 3380, 0))),
		new Clues(8, "Fortunato: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_ANAGRAM_8, List.of(new WorldPoint(3085, 3252, 0))),
		new Clues(9, "Hans: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CRYPTIC_0, List.of(new WorldPoint(3212, 3219, 0))),
		new Clues(10, "Reldo: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CRYPTIC_1, List.of(new WorldPoint(3210, 3495, 0))),
		new Clues(11, "the Cook in Lumbridge: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CRYPTIC_2, List.of(new WorldPoint(3209, 3214, 0))),
		new Clues(12, "Hunding: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CRYPTIC_3, List.of(new WorldPoint(3097, 3429, 2))),
		new Clues(13, "Charlie the Tramp: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CRYPTIC_4, List.of(new WorldPoint(3206, 3390, 0))),
		new Clues(14, "Shantay: Talk", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CRYPTIC_5, List.of(new WorldPoint(3304, 3124, 0))),
		new Clues(15, "Aris: Emote", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_EMOTE_0, List.of(new WorldPoint(3206, 3422, 0))),
		new Clues(16, "Brugsen Bursen: Emote", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_EMOTE_1, List.of(new WorldPoint(3165, 3478, 0))),
		new Clues(17, "Iffie Nitter: Emote", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_EMOTE_2, List.of(new WorldPoint(3209, 3416, 0))),
		new Clues(18, "Bob's Axes: Emote", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_EMOTE_3, List.of(new WorldPoint(3233, 3200, 0))),
		new Clues(19, "Al Kharid mine: Emote", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_EMOTE_4, List.of(new WorldPoint(3298, 3293, 0))),
		new Clues(20, "Flynn's Mace Shop: Emote", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_EMOTE_5, List.of(new WorldPoint(2948, 3389, 0))),
		new Clues(21, "Champions' Guild: Dig", InterfaceID.CLUE_BEGINNER_MAP_CHAMPIONS_GUILD, ClueTier.BEGINNER, MapClue.CHAMPIONS_GUILD, List.of(new WorldPoint(3166, 3361, 0))),
		new Clues(22, "Varrock East Mine: Dig", InterfaceID.CLUE_BEGINNER_MAP_VARROCK_EAST_MINE, ClueTier.BEGINNER, MapClue.VARROCK_EAST_MINE, List.of(new WorldPoint(3290, 3374, 0))),
		new Clues(23, "Draynor Village Bank: Dig", InterfaceID.CLUE_BEGINNER_MAP_DYANOR, ClueTier.BEGINNER, MapClue.SOUTH_OF_DRAYNOR_BANK, List.of(new WorldPoint(3093, 3226, 0))),
		new Clues(24, "Falador stones: Dig", InterfaceID.CLUE_BEGINNER_MAP_NORTH_OF_FALADOR, ClueTier.BEGINNER, MapClue.STANDING_STONES, List.of(new WorldPoint(3043, 3398, 0))),
		new Clues(25, "DIS: Dig", InterfaceID.CLUE_BEGINNER_MAP_WIZARDS_TOWER, ClueTier.BEGINNER, MapClue.WIZARDS_TOWER_DIS, List.of(new WorldPoint(3110, 3152, 0))),
		new Clues(26, "Charlie the Tramp: trout", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CHARLIE_0, List.of(new WorldPoint(3206, 3390, 0))),
		new Clues(27, "Charlie the Tramp: pike", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CHARLIE_1, List.of(new WorldPoint(3206, 3390, 0))),
		new Clues(28, "Charlie the Tramp: raw herring", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CHARLIE_2, List.of(new WorldPoint(3206, 3390, 0))),
		new Clues(29, "Charlie the Tramp: raw trout", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CHARLIE_3, List.of(new WorldPoint(3206, 3390, 0))),
		new Clues(30, "Charlie the Tramp: iron ore", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CHARLIE_4, List.of(new WorldPoint(3206, 3390, 0))),
		new Clues(31, "Charlie the Tramp: iron dagger", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CHARLIE_5, List.of(new WorldPoint(3206, 3390, 0))),
		new Clues(32, "Charlie the Tramp: leather body", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CHARLIE_6, List.of(new WorldPoint(3206, 3390, 0))),
		new Clues(33, "Charlie the Tramp: leather chaps", ItemID.CLUE_SCROLL_BEGINNER, ClueTier.BEGINNER, BeginnerMasterClueText.BEGINNER_CHARLIE_7, List.of(new WorldPoint(3206, 3390, 0))),
		new Clues(34, "Draynor Manor: Dig", HotColdLocation.DRAYNOR_MANOR_MUSHROOMS.ordinal(), ClueTier.BEGINNER, HotColdLocation.DRAYNOR_MANOR_MUSHROOMS.getArea(), List.of(HotColdLocation.DRAYNOR_MANOR_MUSHROOMS.getWorldPoint())),
		new Clues(35, "Draynor Village wheat: Dig", HotColdLocation.DRAYNOR_WHEAT_FIELD.ordinal(), ClueTier.BEGINNER, HotColdLocation.DRAYNOR_WHEAT_FIELD.getArea(), List.of(HotColdLocation.DRAYNOR_WHEAT_FIELD.getWorldPoint())),
		new Clues(36, "Ice Mountain: Dig", HotColdLocation.ICE_MOUNTAIN.ordinal(), ClueTier.BEGINNER, HotColdLocation.ICE_MOUNTAIN.getArea(), List.of(HotColdLocation.ICE_MOUNTAIN.getWorldPoint())),
		new Clues(37, "Lumbridge cows: Dig", HotColdLocation.LUMBRIDGE_COW_FIELD.ordinal(), ClueTier.BEGINNER, HotColdLocation.LUMBRIDGE_COW_FIELD.getArea(), List.of(HotColdLocation.LUMBRIDGE_COW_FIELD.getWorldPoint())),
		new Clues(38, "Al Kharid mine north-east: Dig", HotColdLocation.NORTHEAST_OF_AL_KHARID_MINE.ordinal(), ClueTier.BEGINNER, HotColdLocation.NORTHEAST_OF_AL_KHARID_MINE.getArea(), List.of(HotColdLocation.NORTHEAST_OF_AL_KHARID_MINE.getWorldPoint())),
		new Clues("Duke's room: Search", ItemID.CLUE_SCROLL_EASY, ClueTier.EASY, null, List.of(new WorldPoint(3209, 3218, 0))),
		new Clues("Ardougne church: Search", ItemID.CLUE_SCROLL_EASY_2711, ClueTier.EASY, null, List.of(new WorldPoint(2612, 3304, 0))),
		new Clues("Al Kharid mine: Dig", ItemID.CLUE_SCROLL_EASY_12179, ClueTier.EASY, null, List.of(new WorldPoint(3300, 3291, 0))),
		new Clues("Falador stones: Dig", ItemID.CLUE_SCROLL_EASY_2719, ClueTier.EASY, null, List.of(new WorldPoint(3043, 3398, 0))),
		new Clues("Belladonna Leprechaun: Talk", ItemID.CLUE_SCROLL_EASY_19823, ClueTier.EASY, null, List.of(new WorldPoint(3088, 3357, 0))),
		new Clues("Ardougne Zoo: Emote", ItemID.CLUE_SCROLL_EASY_10216, ClueTier.EASY, null, List.of(new WorldPoint(2607, 3282, 0))),
		new Clues("Keep Le Faye: Emote", ItemID.CLUE_SCROLL_EASY_10222, ClueTier.EASY, null, List.of(new WorldPoint(2757, 3401, 0))),
		new Clues("Emir's Arena: Emote", ItemID.CLUE_SCROLL_EASY_10232, ClueTier.EASY, null, List.of(new WorldPoint(3314, 3241, 0))),
		new Clues("Legends' Guild: Emote", ItemID.CLUE_SCROLL_EASY_10188, ClueTier.EASY, null, List.of(new WorldPoint(2729, 3349, 0))),
		new Clues("Galahad's house: Dig", ItemID.CLUE_SCROLL_EASY_3516, ClueTier.EASY, null, List.of(new WorldPoint(2612, 3482, 0))),
		new Clues("Druids' Circle: Emote", ItemID.CLUE_SCROLL_EASY_10210, ClueTier.EASY, null, List.of(new WorldPoint(2924, 3478, 0))),
		new Clues("games room: Emote", ItemID.CLUE_SCROLL_EASY_10212, ClueTier.EASY, null, List.of(new WorldPoint(2207, 4952, 0))),
		new Clues("Sarim monks: Emote", ItemID.CLUE_SCROLL_EASY_10192, ClueTier.EASY, null, List.of(new WorldPoint(3047, 3237, 0))),
		new Clues("Exam Centre: Emote", ItemID.CLUE_SCROLL_EASY_10228, ClueTier.EASY, null, List.of(new WorldPoint(3361, 3339, 0))),
		new Clues("Wizards' Tower: Emote", ItemID.CLUE_SCROLL_EASY_10182, ClueTier.EASY, null, List.of(new WorldPoint(3113, 3196, 0))),
		new Clues("Ardougne mill: Emote", ItemID.CLUE_SCROLL_EASY_10206, ClueTier.EASY, null, List.of(new WorldPoint(2635, 3385, 0))),
		new Clues("Fishing Guild: Emote", ItemID.CLUE_SCROLL_EASY_10220, ClueTier.EASY, null, List.of(new WorldPoint(2610, 3391, 0))),
		new Clues("Draynor crossroads: Emote", ItemID.CLUE_SCROLL_EASY_10200, ClueTier.EASY, null, List.of(new WorldPoint(3109, 3294, 0))),
		new Clues("Grand Exchange: Emote", ItemID.CLUE_SCROLL_EASY_19831, ClueTier.EASY, null, List.of(new WorldPoint(3165, 3467, 0))),
		new Clues("Party Room: Emote", ItemID.CLUE_SCROLL_EASY_10208, ClueTier.EASY, null, List.of(new WorldPoint(3045, 3376, 0))),
		new Clues("Lumbridge Swamp: Emote", ItemID.CLUE_SCROLL_EASY_10180, ClueTier.EASY, null, List.of(new WorldPoint(3203, 3169, 0))),
		new Clues("Falador stones: Dig", ItemID.CLUE_SCROLL_EASY_12170, ClueTier.EASY, null, List.of(new WorldPoint(3040, 3399, 0))),
		new Clues("Giant's Den: Dig", ItemID.CLUE_SCROLL_EASY_25788, ClueTier.EASY, null, List.of(new WorldPoint(1418, 3591, 0))),
		new Clues("Kourend centre: Dig", ItemID.CLUE_SCROLL_EASY_19826, ClueTier.EASY, null, List.of(new WorldPoint(1639, 3673, 0))),
		new Clues("Grand Tree: Dig", ItemID.CLUE_SCROLL_EASY_3510, ClueTier.EASY, null, List.of(new WorldPoint(2458, 3504, 0))),
		new Clues("Ithoi's cabin: Dig", ItemID.CLUE_SCROLL_EASY_22001, ClueTier.EASY, null, List.of(new WorldPoint(2529, 2838, 0))),
		new Clues("Lumbridge Castle: Dig", ItemID.CLUE_SCROLL_EASY_19814, ClueTier.EASY, null, List.of(new WorldPoint(3221, 3219, 0))),
		new Clues("Wizards' Tower: Dig", ItemID.CLUE_SCROLL_EASY_3518, ClueTier.EASY, null, List.of(new WorldPoint(3109, 3153, 0))),
		new Clues("Varrock's rune store: Emote", ItemID.CLUE_SCROLL_EASY_19833, ClueTier.EASY, null, List.of(new WorldPoint(3253, 3401, 0))),
		new Clues("Al Kharid mine: Emote", ItemID.CLUE_SCROLL_EASY_10194, ClueTier.EASY, null, List.of(new WorldPoint(3299, 3289, 0))),
		new Clues("Vannaka: Talk", ItemID.CLUE_SCROLL_EASY_19816, ClueTier.EASY, null, List.of(new WorldPoint(3146, 9913, 0))),
		new Clues("Chemist: Talk", ItemID.CLUE_SCROLL_EASY_19822, ClueTier.EASY, null, List.of(new WorldPoint(2932, 3212, 0))),
		new Clues("Father Jean: Talk", ItemID.CLUE_SCROLL_EASY_19820, ClueTier.EASY, null, List.of(new WorldPoint(1734, 3576, 0))),
		new Clues("beehives: Emote", ItemID.CLUE_SCROLL_EASY_10214, ClueTier.EASY, null, List.of(new WorldPoint(2759, 3445, 0))),
		new Clues("Sinclair Mansion: Emote", ItemID.CLUE_SCROLL_EASY_10226, ClueTier.EASY, null, List.of(new WorldPoint(2741, 3536, 0))),
		new Clues("Falador east crate: Search", ItemID.CLUE_SCROLL_EASY_2712, ClueTier.EASY, null, List.of(new WorldPoint(3029, 3355, 0))),
		new Clues("monk's camp: Search", ItemID.CLUE_SCROLL_EASY_19818, ClueTier.EASY, null, List.of(new WorldPoint(1746, 3490, 0))),
		new Clues("North of Falador: Search", ItemID.CLUE_SCROLL_EASY_7236, ClueTier.EASY, null, List.of(new WorldPoint(2970, 3415, 0))),
		new Clues("Captain Tobias: Talk", ItemID.CLUE_SCROLL_EASY_3496, ClueTier.EASY, null, List.of(new WorldPoint(3026, 3216, 0))),
		new Clues("Limestone Mine: Emote", ItemID.CLUE_SCROLL_EASY_10186, ClueTier.EASY, null, List.of(new WorldPoint(3372, 3498, 0))),
		new Clues("Fishing trawler: Emote", ItemID.CLUE_SCROLL_EASY_10224, ClueTier.EASY, null, List.of(new WorldPoint(2676, 3169, 0))),
		new Clues("Konoo: Talk", ItemID.CLUE_SCROLL_EASY_19825, ClueTier.EASY, null, List.of(new WorldPoint(1703, 3524, 0))),
		new Clues("Urhney's books: Search", ItemID.CLUE_SCROLL_EASY_12166, ClueTier.EASY, null, List.of(new WorldPoint(3146, 3177, 0))),
		new Clues("Wizards tower: Search", ItemID.CLUE_SCROLL_EASY_12167, ClueTier.EASY, null, List.of(new WorldPoint(3113, 3159, 0))),
		new Clues("Aggie's wardrobe: Search", ItemID.CLUE_SCROLL_EASY_12168, ClueTier.EASY, null, List.of(new WorldPoint(3087, 3261, 0))),
		new Clues("Wydin's Food: Search", ItemID.CLUE_SCROLL_EASY_3495, ClueTier.EASY, null, List.of(new WorldPoint(3016, 3205, 0))),
		new Clues("Varrock Castle: Search", ItemID.CLUE_SCROLL_EASY_3515, ClueTier.EASY, null, List.of(new WorldPoint(3224, 3492, 0))),
		new Clues("Hemenster: Search", ItemID.CLUE_SCROLL_EASY_3506, ClueTier.EASY, null, List.of(new WorldPoint(2636, 3453, 0))),
		new Clues("Farsight's house: Search", ItemID.CLUE_SCROLL_EASY_3509, ClueTier.EASY, null, List.of(new WorldPoint(2699, 3470, 0))),
		new Clues("Edgeville coffin: Search", ItemID.CLUE_SCROLL_EASY_12174, ClueTier.EASY, null, List.of(new WorldPoint(3091, 3477, 0))),
		new Clues("Edgeville Monastery: Search", ItemID.CLUE_SCROLL_EASY_12177, ClueTier.EASY, null, List.of(new WorldPoint(3054, 3484, 0))),
		new Clues("Gaius' Shop: Search", ItemID.CLUE_SCROLL_EASY_3499, ClueTier.EASY, null, List.of(new WorldPoint(2886, 3449, 0))),
		new Clues("Al Kharid silk: Search", ItemID.CLUE_SCROLL_EASY_3501, ClueTier.EASY, null, List.of(new WorldPoint(3308, 3206, 0))),
		new Clues("Goblin house: Search", ItemID.CLUE_SCROLL_EASY_2679, ClueTier.EASY, null, List.of(new WorldPoint(3245, 3245, 0))),
		new Clues("Varrock south: Search", ItemID.CLUE_SCROLL_EASY_2685, ClueTier.EASY, null, List.of(new WorldPoint(3203, 3384, 0))),
		new Clues("outside Zenesha's: Search", ItemID.CLUE_SCROLL_EASY_2705, ClueTier.EASY, null, List.of(new WorldPoint(2654, 3299, 0))),
		new Clues("Crystal Chest: Search", ItemID.CLUE_SCROLL_EASY_23149, ClueTier.EASY, null, List.of(new WorldPoint(2915, 3452, 0))),
		new Clues("Falador General Store: Search", ItemID.CLUE_SCROLL_EASY_2692, ClueTier.EASY, null, List.of(new WorldPoint(2955, 3390, 0))),
		new Clues("Sarim jail: Search", ItemID.CLUE_SCROLL_EASY_12192, ClueTier.EASY, null, List.of(new WorldPoint(3013, 3179, 0))),
		new Clues("Digsite bush: Search", ItemID.CLUE_SCROLL_EASY_12175, ClueTier.EASY, null, List.of(new WorldPoint(3345, 3378, 0))),
		new Clues("Barbarian Village wheel: Search", ItemID.CLUE_SCROLL_EASY_12172, ClueTier.EASY, null, List.of(new WorldPoint(3085, 3429, 0))),
		new Clues("Fred the Farmer: Search", ItemID.CLUE_SCROLL_EASY_23151, ClueTier.EASY, null, List.of(new WorldPoint(3185, 3274, 0))),
		new Clues("Camelot Castle: Search", ItemID.CLUE_SCROLL_EASY_2703, ClueTier.EASY, null, List.of(new WorldPoint(2748, 3495, 0))),
		new Clues("Hura's Shop: Search", ItemID.CLUE_SCROLL_EASY_3493, ClueTier.EASY, null, List.of(new WorldPoint(3000, 9798, 0))),
		new Clues("Al Kharid Palace: Search", ItemID.CLUE_SCROLL_EASY_2680, ClueTier.EASY, null, List.of(new WorldPoint(3301, 3169, 0))),
		new Clues("Rommik's shop: Search", ItemID.CLUE_SCROLL_EASY_23153, ClueTier.EASY, null, List.of(new WorldPoint(2946, 3207, 0))),
		new Clues("Burthorpe pub: Search", ItemID.CLUE_SCROLL_EASY_12185, ClueTier.EASY, null, List.of(new WorldPoint(2913, 3536, 0))),
		new Clues("Lumbridge Castle tower: Search", ItemID.CLUE_SCROLL_EASY_2678, ClueTier.EASY, null, List.of(new WorldPoint(3228, 3212, 0))),
		new Clues("Port Khazard: Search", ItemID.CLUE_SCROLL_EASY_3504, ClueTier.EASY, null, List.of(new WorldPoint(2660, 3149, 0))),
		new Clues("Canifis crate: Search", ItemID.CLUE_SCROLL_EASY_7238, ClueTier.EASY, null, List.of(new WorldPoint(3509, 3497, 0))),
		new Clues("Draynor Manor crate: Search", ItemID.CLUE_SCROLL_EASY_3502, ClueTier.EASY, null, List.of(new WorldPoint(3106, 3369, 0))),
		new Clues("Ardougne General store: Search", ItemID.CLUE_SCROLL_EASY_2706, ClueTier.EASY, null, List.of(new WorldPoint(2615, 3291, 0))),
		new Clues("Falador General store: Search", ItemID.CLUE_SCROLL_EASY_23154, ClueTier.EASY, null, List.of(new WorldPoint(2955, 3390, 0))),
		new Clues("Horvik's crates: Search", ItemID.CLUE_SCROLL_EASY_2688, ClueTier.EASY, null, List.of(new WorldPoint(3228, 3433, 0))),
		new Clues("West Varrock bank: Search", ItemID.CLUE_SCROLL_EASY_12176, ClueTier.EASY, null, List.of(new WorldPoint(3187, 9825, 0))),
		new Clues("Yanille piano: Search", ItemID.CLUE_SCROLL_EASY_3491, ClueTier.EASY, null, List.of(new WorldPoint(2598, 3105, 0))),
		new Clues("Barbarian Village helmet: Search", ItemID.CLUE_SCROLL_EASY_2690, ClueTier.EASY, null, List.of(new WorldPoint(3073, 3430, 0))),
		new Clues("Drogo's shop: Search", ItemID.CLUE_SCROLL_EASY_12178, ClueTier.EASY, null, List.of(new WorldPoint(3035, 9849, 0))),
		new Clues("Gerrant's shop: Search", ItemID.CLUE_SCROLL_EASY_2695, ClueTier.EASY, null, List.of(new WorldPoint(3012, 3222, 0))),
		new Clues("Hosidius fruit store: Search", ItemID.CLUE_SCROLL_EASY_25789, ClueTier.EASY, null, List.of(new WorldPoint(1799, 3613, 0))),
		new Clues("Ardougne guard house: Search", ItemID.CLUE_SCROLL_EASY_2704, ClueTier.EASY, null, List.of(new WorldPoint(2645, 3338, 0))),
		new Clues("Al Kharid north-west : Search", ItemID.CLUE_SCROLL_EASY_2682, ClueTier.EASY, null, List.of(new WorldPoint(3289, 3202, 0))),
		new Clues("Taverley stile: Search", ItemID.CLUE_SCROLL_EASY_12191, ClueTier.EASY, null, List.of(new WorldPoint(2914, 3433, 0))),
		new Clues("Ardougne shed: Search", ItemID.CLUE_SCROLL_EASY_2707, ClueTier.EASY, null, List.of(new WorldPoint(2617, 3347, 0))),
		new Clues("Varrock cart: Search", ItemID.CLUE_SCROLL_EASY_2709, ClueTier.EASY, null, List.of(new WorldPoint(3226, 3452, 0))),
		new Clues("Thessalia's upstairs: Search", ItemID.CLUE_SCROLL_EASY_2708, ClueTier.EASY, null, List.of(new WorldPoint(3206, 3419, 0))),
		new Clues("Falador east drawers: Search", ItemID.CLUE_SCROLL_EASY_3498, ClueTier.EASY, null, List.of(new WorldPoint(3039, 3342, 0))),
		new Clues("Ardougne pub: Search", ItemID.CLUE_SCROLL_EASY_2710, ClueTier.EASY, null, List.of(new WorldPoint(2574, 3326, 0))),
		new Clues("Wayne's Chains: Search", ItemID.CLUE_SCROLL_EASY_2694, ClueTier.EASY, null, List.of(new WorldPoint(2969, 3311, 0))),
		new Clues("Draynor drawers: Search", ItemID.CLUE_SCROLL_EASY_3512, ClueTier.EASY, null, List.of(new WorldPoint(3097, 3277, 0))),
		new Clues("Gertrude's house: Search", ItemID.CLUE_SCROLL_EASY_2689, ClueTier.EASY, null, List.of(new WorldPoint(3156, 3406, 0))),
		new Clues("Yanille hunter shop: Search", ItemID.CLUE_SCROLL_EASY_3492, ClueTier.EASY, null, List.of(new WorldPoint(2570, 3085, 0))),
		new Clues("Sarim water house: Search", ItemID.CLUE_SCROLL_EASY_12188, ClueTier.EASY, null, List.of(new WorldPoint(3024, 3259, 0))),
		new Clues("Perdu's house: Search", ItemID.CLUE_SCROLL_EASY_3507, ClueTier.EASY, null, List.of(new WorldPoint(2809, 3451, 0))),
		new Clues("Hild's house: Search", ItemID.CLUE_SCROLL_EASY_3490, ClueTier.EASY, null, List.of(new WorldPoint(2929, 3570, 0))),
		new Clues("Noella's house: Search", ItemID.CLUE_SCROLL_EASY_3505, ClueTier.EASY, null, List.of(new WorldPoint(2653, 3320, 0))),
		new Clues("Cassie's Shop: Search", ItemID.CLUE_SCROLL_EASY_2691, ClueTier.EASY, null, List.of(new WorldPoint(2971, 3386, 0))),
		new Clues("East Varrock bank: Search", ItemID.CLUE_SCROLL_EASY_2687, ClueTier.EASY, null, List.of(new WorldPoint(3250, 3420, 0))),
		new Clues("Urhney's books east: Search", ItemID.CLUE_SCROLL_EASY_23150, ClueTier.EASY, null, List.of(new WorldPoint(3149, 3177, 0))),
		new Clues("Hunter Guild: Search", ItemID.CLUE_SCROLL_EASY_28913, ClueTier.EASY, null, List.of(new WorldPoint(1560, 3048, 0))),
		new Clues("Hosidius kitchens: Search", ItemID.CLUE_SCROLL_EASY_19829, ClueTier.EASY, null, List.of(new WorldPoint(1683, 3616, 0))),
		new Clues("Horvik's crate: Search", ItemID.CLUE_SCROLL_EASY_23152, ClueTier.EASY, null, List.of(new WorldPoint(3228, 3433, 0))),
		new Clues("Burthorpe tents: Search", ItemID.CLUE_SCROLL_EASY_3503, ClueTier.EASY, null, List.of(new WorldPoint(2885, 3540, 0))),
		new Clues("Rimmington mine: Search", ItemID.CLUE_SCROLL_EASY_12189, ClueTier.EASY, null, List.of(new WorldPoint(2978, 3239, 0))),
		new Clues("Falador east upstairs: Search", ItemID.CLUE_SCROLL_EASY_3497, ClueTier.EASY, null, List.of(new WorldPoint(3041, 3364, 0))),
		new Clues("Taverley south: Search", ItemID.CLUE_SCROLL_EASY_3500, ClueTier.EASY, null, List.of(new WorldPoint(2894, 3418, 0))),
		new Clues("Rimmington upstairs: Search", ItemID.CLUE_SCROLL_EASY_3494, ClueTier.EASY, null, List.of(new WorldPoint(2970, 3214, 0))),
		new Clues("Seers' Village upstairs: Search", ItemID.CLUE_SCROLL_EASY_3508, ClueTier.EASY, null, List.of(new WorldPoint(2716, 3471, 0))),
		new Clues("Rimmington mine: Emote", ItemID.CLUE_SCROLL_EASY_10202, ClueTier.EASY, null, List.of(new WorldPoint(2976, 3238, 0))),
		new Clues("Jeed: Talk", ItemID.CLUE_SCROLL_EASY_3514, ClueTier.EASY, null, List.of(new WorldPoint(3366, 3222, 0))),
		new Clues("South-east Varrock mine: Dig", ItemID.CLUE_SCROLL_EASY_2716, ClueTier.EASY, null, List.of(new WorldPoint(3290, 3373, 0))),
		new Clues("Catherby Archery shop: Search", ItemID.CLUE_SCROLL_EASY_2700, ClueTier.EASY, null, List.of(new WorldPoint(2825, 3442, 0))),
		new Clues("Arhein: Talk", ItemID.CLUE_SCROLL_EASY_2701, ClueTier.EASY, null, List.of(new WorldPoint(2803, 3430, 0))),
		new Clues("Doric: Talk", ItemID.CLUE_SCROLL_EASY_2698, ClueTier.EASY, null, List.of(new WorldPoint(2951, 3450, 0))),
		new Clues("Ellis: Talk", ItemID.CLUE_SCROLL_EASY_2684, ClueTier.EASY, null, List.of(new WorldPoint(3276, 3191, 0))),
		new Clues("Gaius: Talk", ItemID.CLUE_SCROLL_EASY_2699, ClueTier.EASY, null, List.of(new WorldPoint(2884, 3450, 0))),
		new Clues("Hans: Talk", ItemID.CLUE_SCROLL_EASY_2681, ClueTier.EASY, null, List.of(new WorldPoint(3221, 3218, 0))),
		new Clues("Jatix: Talk", ItemID.CLUE_SCROLL_EASY_12184, ClueTier.EASY, null, List.of(new WorldPoint(2898, 3428, 0))),
		new Clues("Ned: Talk", ItemID.CLUE_SCROLL_EASY_2697, ClueTier.EASY, null, List.of(new WorldPoint(3098, 3258, 0))),
		new Clues("Rusty: Talk", ItemID.CLUE_SCROLL_EASY_12187, ClueTier.EASY, null, List.of(new WorldPoint(2979, 3435, 0))),
		new Clues("Sarah: Talk", ItemID.CLUE_SCROLL_EASY_12186, ClueTier.EASY, null, List.of(new WorldPoint(3038, 3292, 0))),
		new Clues("Sir Kay: Talk", ItemID.CLUE_SCROLL_EASY_2702, ClueTier.EASY, null, List.of(new WorldPoint(2760, 3496, 0))),
		new Clues("Lady of the Lake: Talk", ItemID.CLUE_SCROLL_EASY_12190, ClueTier.EASY, null, List.of(new WorldPoint(2924, 3405, 0))),
		new Clues("Blue Moon Inn Bartender: Talk", ItemID.CLUE_SCROLL_EASY_2686, ClueTier.EASY, null, List.of(new WorldPoint(3226, 3399, 0))),
		new Clues("Louisa Sinclair: Talk", ItemID.CLUE_SCROLL_EASY_3513, ClueTier.EASY, null, List.of(new WorldPoint(2736, 3578, 0))),
		new Clues("Rimmington crossroads: Emote", ItemID.CLUE_SCROLL_EASY_10218, ClueTier.EASY, null, List.of(new WorldPoint(2981, 3276, 0))),
		new Clues("Draynor Manor fountain: Emote", ItemID.CLUE_SCROLL_EASY_10196, ClueTier.EASY, null, List.of(new WorldPoint(3088, 3336, 0))),
		new Clues("Varrock Castle: Emote", ItemID.CLUE_SCROLL_EASY_12162, ClueTier.EASY, null, List.of(new WorldPoint(3213, 3463, 0))),
		new Clues("Apothecary: Talk", ItemID.CLUE_SCROLL_EASY_19817, ClueTier.EASY, null, List.of(new WorldPoint(3195, 3404, 0))),
		new Clues("Herquin: Talk", ItemID.CLUE_SCROLL_EASY_19819, ClueTier.EASY, null, List.of(new WorldPoint(2945, 3335, 0))),
		new Clues("Ali the Leaflet Dropper: Talk", ItemID.CLUE_SCROLL_EASY_23164, ClueTier.EASY, null, List.of(new WorldPoint(3283, 3329, 0))),
		new Clues("Ambassador Spanfipple: Talk", ItemID.CLUE_SCROLL_EASY_12182, ClueTier.EASY, null, List.of(new WorldPoint(2979, 3340, 0))),
		new Clues("Cassie: Talk", ItemID.CLUE_SCROLL_EASY_12181, ClueTier.EASY, null, List.of(new WorldPoint(2975, 3383, 0))),
		new Clues("Charles in Piscarilius: Talk", ItemID.CLUE_SCROLL_EASY_23161, ClueTier.EASY, null, List.of(new WorldPoint(1821, 3690, 0))),
		new Clues("Ermin: Talk", ItemID.CLUE_SCROLL_EASY_19828, ClueTier.EASY, null, List.of(new WorldPoint(2488, 3409, 0))),
		new Clues("Morgan: Talk", ItemID.CLUE_SCROLL_EASY_23162, ClueTier.EASY, null, List.of(new WorldPoint(3098, 3268, 0))),
		new Clues("Turael/Aya: Talk", ItemID.CLUE_SCROLL_EASY_23165, ClueTier.EASY, null, List.of(new WorldPoint(2930, 3536, 0))),
		new Clues("Wayne: Talk", ItemID.CLUE_SCROLL_EASY_23163, ClueTier.EASY, null, List.of(new WorldPoint(2972, 3312, 0))),
		new Clues("Zeke: Talk", ItemID.CLUE_SCROLL_EASY_2683, ClueTier.EASY, null, List.of(new WorldPoint(3287, 3190, 0))),
		new Clues("Lucy: Talk", ItemID.CLUE_SCROLL_EASY_12169, ClueTier.EASY, null, List.of(new WorldPoint(3046, 3382, 0))),
		new Clues("Doomsayer: Talk", ItemID.CLUE_SCROLL_EASY_19830, ClueTier.EASY, null, List.of(new WorldPoint(3230, 3230, 0))),
		new Clues("Squire: Talk", ItemID.CLUE_SCROLL_EASY_2693, ClueTier.EASY, null, List.of(new WorldPoint(2977, 3343, 0))),
		new Clues("Hairdresser: Talk", ItemID.CLUE_SCROLL_EASY_12183, ClueTier.EASY, null, List.of(new WorldPoint(2944, 3381, 0))),
		new Clues("Rusty Anchor Bartender: Talk", ItemID.CLUE_SCROLL_EASY_2696, ClueTier.EASY, null, List.of(new WorldPoint(3045, 3256, 0))),
		new Clues("Blue Moon Inn Cook: Talk", ItemID.CLUE_SCROLL_EASY_23166, ClueTier.EASY, null, List.of(new WorldPoint(3230, 3401, 0))),
		new Clues("Doris: Talk", ItemID.CLUE_SCROLL_EASY_12173, ClueTier.EASY, null, List.of(new WorldPoint(3079, 3493, 0))),
		new Clues("Tynan: Talk", ItemID.CLUE_SCROLL_EASY_19821, ClueTier.EASY, null, List.of(new WorldPoint(1836, 3786, 0))),
		new Clues("The Face: Talk", ItemID.CLUE_SCROLL_EASY_19824, ClueTier.EASY, null, List.of(new WorldPoint(3019, 3232, 0))),
		new Clues("Lumbridge mill: Emote", ItemID.CLUE_SCROLL_EASY_10198, ClueTier.EASY, null, List.of(new WorldPoint(3159, 3298, 0))),
		new Clues("Lumber Yard: Emote", ItemID.CLUE_SCROLL_EASY_10230, ClueTier.EASY, null, List.of(new WorldPoint(3307, 3491, 0))),
		new Clues("Herquin's shop: Emote", ItemID.CLUE_SCROLL_EASY_12164, ClueTier.EASY, null, List.of(new WorldPoint(2945, 3335, 0))),
		new Clues("Mudskipper Point: Emote", ItemID.CLUE_SCROLL_EASY_10190, ClueTier.EASY, null, List.of(new WorldPoint(2989, 3110, 0))),
		new Clues("Champions' Guild: Dig", ItemID.CLUE_SCROLL_EASY_2713, ClueTier.EASY, null, List.of(new WorldPoint(3166, 3361, 0))),
		new Clues("Draynor Marketplace: Emote", ItemID.CLUE_SCROLL_EASY_10184, ClueTier.EASY, null, List.of(new WorldPoint(3083, 3253, 0))),
		new Clues("Fortis Grand Museum: Emote", ItemID.CLUE_SCROLL_EASY_28914, ClueTier.EASY, null, List.of(new WorldPoint(1712, 3163, 0))),
		new Clues("Varrock library: Emote", ItemID.CLUE_SCROLL_EASY_10204, ClueTier.EASY, null, List.of(new WorldPoint(3209, 3492, 0))),
		new Clues("Song: Vision", ItemID.CLUE_SCROLL_EASY_23155, ClueTier.EASY, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: The Forlorn Homestead", ItemID.CLUE_SCROLL_EASY_23156, ClueTier.EASY, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: Tiptoe", ItemID.CLUE_SCROLL_EASY_23157, ClueTier.EASY, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: Rugged Terrain", ItemID.CLUE_SCROLL_EASY_23158, ClueTier.EASY, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: On the Shore", ItemID.CLUE_SCROLL_EASY_23159, ClueTier.EASY, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: Alone", ItemID.CLUE_SCROLL_EASY_23160, ClueTier.EASY, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Aldarin bank: Dig", ItemID.CLUE_SCROLL_EASY_29853, ClueTier.EASY, null, List.of(new WorldPoint(1390, 2926, 0))),
		new Clues("Quetzacalli Gorge: Dig", ItemID.CLUE_SCROLL_EASY_29854, ClueTier.EASY, null, List.of(new WorldPoint(1490, 3257, 0))),
		new Clues("Ranulph: Talk", ItemID.CLUE_SCROLL_EASY_30928, ClueTier.EASY, null, List.of(new WorldPoint(1366, 3367, 0))),
		new Clues("H.A.M. Hideout: Dig", ItemID.CLUE_SCROLL_MEDIUM, ClueTier.MEDIUM, null, List.of(new WorldPoint(3160, 3251, 0))),
		new Clues("Tree Gnome Village: Dig", ItemID.CLUE_SCROLL_MEDIUM_2809, ClueTier.MEDIUM, null, List.of(new WorldPoint(2479, 3158, 0))),
		new Clues("Karamja banana: Dig", ItemID.CLUE_SCROLL_MEDIUM_3588, ClueTier.MEDIUM, null, List.of(new WorldPoint(2887, 3154, 0))),
		new Clues("Brimhaven Dungeon: Dig", ItemID.CLUE_SCROLL_MEDIUM_3590, ClueTier.MEDIUM, null, List.of(new WorldPoint(2743, 3151, 0))),
		new Clues("Lumbridge Swamp south: Dig", ItemID.CLUE_SCROLL_MEDIUM_7313, ClueTier.MEDIUM, null, List.of(new WorldPoint(3184, 3150, 0))),
		new Clues("Lumbridge Swamp east: Dig", ItemID.CLUE_SCROLL_MEDIUM_2823, ClueTier.MEDIUM, null, List.of(new WorldPoint(3217, 3177, 0))),
		new Clues("Asgarnian Ice Dungeon: Dig", ItemID.CLUE_SCROLL_MEDIUM_2819, ClueTier.MEDIUM, null, List.of(new WorldPoint(3007, 3144, 0))),
		new Clues("DKP: Dig", ItemID.CLUE_SCROLL_MEDIUM_7309, ClueTier.MEDIUM, null, List.of(new WorldPoint(2896, 3119, 0))),
		new Clues("Moss Giant Island: Dig", ItemID.CLUE_SCROLL_MEDIUM_2805, ClueTier.MEDIUM, null, List.of(new WorldPoint(2697, 3207, 0))),
		new Clues("Hazelmere's north: Dig", ItemID.CLUE_SCROLL_MEDIUM_2803, ClueTier.MEDIUM, null, List.of(new WorldPoint(2679, 3110, 0))),
		new Clues("Crandor south: Dig", ItemID.CLUE_SCROLL_MEDIUM_23136, ClueTier.MEDIUM, null, List.of(new WorldPoint(2828, 3234, 0))),
		new Clues("Uzer east: Dig", ItemID.CLUE_SCROLL_MEDIUM_12035, ClueTier.MEDIUM, null, List.of(new WorldPoint(3510, 3074, 0))),
		new Clues("DJP: Dig", ItemID.CLUE_SCROLL_MEDIUM_2813, ClueTier.MEDIUM, null, List.of(new WorldPoint(2643, 3252, 0))),
		new Clues("BKP: Dig", ItemID.CLUE_SCROLL_MEDIUM_12041, ClueTier.MEDIUM, null, List.of(new WorldPoint(2322, 3061, 0))),
		new Clues("Nature Altar north: Dig", ItemID.CLUE_SCROLL_MEDIUM_7317, ClueTier.MEDIUM, null, List.of(new WorldPoint(2875, 3046, 0))),
		new Clues("Nature Altar west: Dig", ItemID.CLUE_SCROLL_MEDIUM_2817, ClueTier.MEDIUM, null, List.of(new WorldPoint(2849, 3033, 0))),
		new Clues("Crandor north: Dig", ItemID.CLUE_SCROLL_MEDIUM_2815, ClueTier.MEDIUM, null, List.of(new WorldPoint(2848, 3296, 0))),
		new Clues("AKS north: Dig", ItemID.CLUE_SCROLL_MEDIUM_7307, ClueTier.MEDIUM, null, List.of(new WorldPoint(2583, 2990, 0))),
		new Clues("Lumbridge mill cows: Dig", ItemID.CLUE_SCROLL_MEDIUM_2825, ClueTier.MEDIUM, null, List.of(new WorldPoint(3179, 3344, 0))),
		new Clues("Outpost west: Dig", ItemID.CLUE_SCROLL_MEDIUM_2807, ClueTier.MEDIUM, null, List.of(new WorldPoint(2383, 3370, 0))),
		new Clues("Exam Centre hill: Dig", ItemID.CLUE_SCROLL_MEDIUM_12051, ClueTier.MEDIUM, null, List.of(new WorldPoint(3312, 3375, 0))),
		new Clues("Draynor Manor: Dig", ItemID.CLUE_SCROLL_MEDIUM_12043, ClueTier.MEDIUM, null, List.of(new WorldPoint(3121, 3384, 0))),
		new Clues("BKR: Dig", ItemID.CLUE_SCROLL_MEDIUM_3584, ClueTier.MEDIUM, null, List.of(new WorldPoint(3430, 3388, 0))),
		new Clues("Lady of the Lake: Dig", ItemID.CLUE_SCROLL_MEDIUM_2821, ClueTier.MEDIUM, null, List.of(new WorldPoint(2920, 3403, 0))),
		new Clues("AKS south: Dig", ItemID.CLUE_SCROLL_MEDIUM_12033, ClueTier.MEDIUM, null, List.of(new WorldPoint(2594, 2899, 0))),
		new Clues("terrorbirds: Dig", ItemID.CLUE_SCROLL_MEDIUM_3592, ClueTier.MEDIUM, null, List.of(new WorldPoint(2387, 3435, 0))),
		new Clues("Baxtorian Falls: Dig", ItemID.CLUE_SCROLL_MEDIUM_2811, ClueTier.MEDIUM, null, List.of(new WorldPoint(2512, 3467, 0))),
		new Clues("terrorbirds north: Dig", ItemID.CLUE_SCROLL_MEDIUM_12045, ClueTier.MEDIUM, null, List.of(new WorldPoint(2381, 3468, 0))),
		new Clues("Ice Mountain,: Dig", ItemID.CLUE_SCROLL_MEDIUM_7311, ClueTier.MEDIUM, null, List.of(new WorldPoint(3005, 3475, 0))),
		new Clues("Bazaar: Dig", ItemID.CLUE_SCROLL_MEDIUM_28909, ClueTier.MEDIUM, null, List.of(new WorldPoint(1659, 3111, 0))),
		new Clues("Coal Trucks north: Dig", ItemID.CLUE_SCROLL_MEDIUM_12049, ClueTier.MEDIUM, null, List.of(new WorldPoint(2585, 3505, 0))),
		new Clues("Slayer Tower south: Dig", ItemID.CLUE_SCROLL_MEDIUM_3582, ClueTier.MEDIUM, null, List.of(new WorldPoint(3443, 3515, 0))),
		new Clues("Grand Tree swamp: Dig", ItemID.CLUE_SCROLL_MEDIUM_3594, ClueTier.MEDIUM, null, List.of(new WorldPoint(2416, 3516, 0))),
		new Clues("Slayer Tower south-east: Dig", ItemID.CLUE_SCROLL_MEDIUM_12053, ClueTier.MEDIUM, null, List.of(new WorldPoint(3429, 3523, 0))),
		new Clues("Eagles' Peak: Dig", ItemID.CLUE_SCROLL_MEDIUM_12047, ClueTier.MEDIUM, null, List.of(new WorldPoint(2363, 3531, 0))),
		new Clues("Burthorpe pub: Dig", ItemID.CLUE_SCROLL_MEDIUM_3586, ClueTier.MEDIUM, null, List.of(new WorldPoint(2919, 3535, 0))),
		new Clues("Fenkenstrain's Castle: Dig", ItemID.CLUE_SCROLL_MEDIUM_12037, ClueTier.MEDIUM, null, List.of(new WorldPoint(3548, 3560, 0))),
		new Clues("Graveyard of Heroes: Dig", ItemID.CLUE_SCROLL_MEDIUM_19774, ClueTier.MEDIUM, null, List.of(new WorldPoint(1476, 3566, 0))),
		new Clues("AJR: Dig", ItemID.CLUE_SCROLL_MEDIUM_7315, ClueTier.MEDIUM, null, List.of(new WorldPoint(2735, 3638, 0))),
		new Clues("Rellekka garden: Dig", ItemID.CLUE_SCROLL_MEDIUM_12039, ClueTier.MEDIUM, null, List.of(new WorldPoint(2681, 3653, 0))),
		new Clues("Farming Guild: Dig", ItemID.CLUE_SCROLL_MEDIUM_23135, ClueTier.MEDIUM, null, List.of(new WorldPoint(1247, 3726, 0))),
		new Clues("CIP east: Dig", ItemID.CLUE_SCROLL_MEDIUM_7305, ClueTier.MEDIUM, null, List.of(new WorldPoint(2537, 3881, 0))),
		new Clues("Fossil Island island: Dig", ItemID.CLUE_SCROLL_MEDIUM_23137, ClueTier.MEDIUM, null, List.of(new WorldPoint(3770, 3898, 0))),
		new Clues("Baraek: Talk", ItemID.CLUE_SCROLL_MEDIUM_12057, ClueTier.MEDIUM, null, List.of(new WorldPoint(3217, 3434, 0))),
		new Clues("Saba: Talk", ItemID.CLUE_SCROLL_MEDIUM_3613, ClueTier.MEDIUM, null, List.of(new WorldPoint(2858, 3577, 0))),
		new Clues("Captain Tobias: Talk", ItemID.CLUE_SCROLL_MEDIUM_12061, ClueTier.MEDIUM, null, List.of(new WorldPoint(3026, 3216, 0))),
		new Clues("Aretha: Talk", ItemID.CLUE_SCROLL_MEDIUM_19758, ClueTier.MEDIUM, null, List.of(new WorldPoint(1814, 3851, 0))),
		new Clues("hill giant: Kill", ItemID.CLUE_SCROLL_MEDIUM_19760, ClueTier.MEDIUM, null, List.of(new WorldPoint(1444, 3613, 0))),
		new Clues("Canifis clothes: Search", ItemID.CLUE_SCROLL_MEDIUM_3609, ClueTier.MEDIUM, null, List.of(new WorldPoint(3498, 3507, 0))),
		new Clues("Jaraah: Talk", ItemID.CLUE_SCROLL_MEDIUM_3616, ClueTier.MEDIUM, null, List.of(new WorldPoint(3359, 3276, 0))),
		new Clues("Caroline: Talk", ItemID.CLUE_SCROLL_MEDIUM_7280, ClueTier.MEDIUM, null, List.of(new WorldPoint(2715, 3302, 0))),
		new Clues("Oracle: Talk", ItemID.CLUE_SCROLL_MEDIUM_2851, ClueTier.MEDIUM, null, List.of(new WorldPoint(3013, 3501, 0))),
		new Clues("Father Aereck: Talk", ItemID.CLUE_SCROLL_MEDIUM_19746, ClueTier.MEDIUM, null, List.of(new WorldPoint(3242, 3207, 0))),
		new Clues("Charlie the Tramp: Talk", ItemID.CLUE_SCROLL_MEDIUM_19750, ClueTier.MEDIUM, null, List.of(new WorldPoint(3209, 3392, 0))),
		new Clues("Gloria: Talk", ItemID.CLUE_SCROLL_MEDIUM_28908, ClueTier.MEDIUM, null, List.of(new WorldPoint(1802, 9504, 0))),
		new Clues("Ourania Cave: Dig", ItemID.CLUE_SCROLL_MEDIUM_7290, ClueTier.MEDIUM, null, List.of(new WorldPoint(2454, 3230, 0))),
		new Clues("Brimstail: Talk", ItemID.CLUE_SCROLL_MEDIUM_3612, ClueTier.MEDIUM, null, List.of(new WorldPoint(2407, 9816, 0))),
		new Clues("Ali the Kebab seller: Talk", ItemID.CLUE_SCROLL_MEDIUM_19768, ClueTier.MEDIUM, null, List.of(new WorldPoint(3354, 2974, 0))),
		new Clues("Tai Bwo Wannai: Emote", ItemID.CLUE_SCROLL_MEDIUM_10260, ClueTier.MEDIUM, null, List.of(new WorldPoint(2803, 3073, 0))),
		new Clues("Digsite winch: Emote", ItemID.CLUE_SCROLL_MEDIUM_10274, ClueTier.MEDIUM, null, List.of(new WorldPoint(3370, 3425, 0))),
		new Clues("Shayzien Combat Ring: Emote", ItemID.CLUE_SCROLL_MEDIUM_19776, ClueTier.MEDIUM, null, List.of(new WorldPoint(1543, 3623, 0))),
		new Clues("Chemist's house: Dig", ItemID.CLUE_SCROLL_MEDIUM_3602, ClueTier.MEDIUM, null, List.of(new WorldPoint(2924, 3209, 0))),
		new Clues("CJR south-west: Dig", ItemID.CLUE_SCROLL_MEDIUM_7294, ClueTier.MEDIUM, null, List.of(new WorldPoint(2667, 3562, 0))),
		new Clues("Madame Caldarium: Talk", ItemID.CLUE_SCROLL_MEDIUM_23133, ClueTier.MEDIUM, null, List.of(new WorldPoint(2553, 2868, 0))),
		new Clues("Nicholas: Talk", ItemID.CLUE_SCROLL_MEDIUM_23131, ClueTier.MEDIUM, null, List.of(new WorldPoint(1841, 3803, 0))),
		new Clues("Barbarian Agility: Emote", ItemID.CLUE_SCROLL_MEDIUM_10264, ClueTier.MEDIUM, null, List.of(new WorldPoint(2552, 3556, 0))),
		new Clues("Edgeville General store: Emote", ItemID.CLUE_SCROLL_MEDIUM_12031, ClueTier.MEDIUM, null, List.of(new WorldPoint(3080, 3509, 0))),
		new Clues("Ogre Pen: Emote", ItemID.CLUE_SCROLL_MEDIUM_10272, ClueTier.MEDIUM, null, List.of(new WorldPoint(2527, 3375, 0))),
		new Clues("Seers court house: Emote", ItemID.CLUE_SCROLL_MEDIUM_12025, ClueTier.MEDIUM, null, List.of(new WorldPoint(2735, 3469, 0))),
		new Clues("Mount Karuulm north: Emote", ItemID.CLUE_SCROLL_MEDIUM_23046, ClueTier.MEDIUM, null, List.of(new WorldPoint(1306, 3839, 0))),
		new Clues("Catherby Archery shop: Emote", ItemID.CLUE_SCROLL_MEDIUM_10276, ClueTier.MEDIUM, null, List.of(new WorldPoint(2823, 3443, 0))),
		new Clues("Draynor Village jail: Emote", ItemID.CLUE_SCROLL_MEDIUM_19780, ClueTier.MEDIUM, null, List.of(new WorldPoint(3128, 3245, 0))),
		new Clues("Catherby beach: Emote", ItemID.CLUE_SCROLL_MEDIUM_12027, ClueTier.MEDIUM, null, List.of(new WorldPoint(2853, 3424, 0))),
		new Clues("Gnome Agility: Emote", ItemID.CLUE_SCROLL_MEDIUM_10266, ClueTier.MEDIUM, null, List.of(new WorldPoint(2473, 3420, 0))),
		new Clues("Brundt: Talk", ItemID.CLUE_SCROLL_MEDIUM_7274, ClueTier.MEDIUM, null, List.of(new WorldPoint(2658, 3670, 0))),
		new Clues("Shantay Pass: Emote", ItemID.CLUE_SCROLL_MEDIUM_10278, ClueTier.MEDIUM, null, List.of(new WorldPoint(3304, 3124, 0))),
		new Clues("Canifis centre: Emote", ItemID.CLUE_SCROLL_MEDIUM_10254, ClueTier.MEDIUM, null, List.of(new WorldPoint(3492, 3488, 0))),
		new Clues("Lumbridge Swamp Caves: Emote", ItemID.CLUE_SCROLL_MEDIUM_12021, ClueTier.MEDIUM, null, List.of(new WorldPoint(3168, 9571, 0))),
		new Clues("Captain Khaled: Talk", ItemID.CLUE_SCROLL_MEDIUM_19766, ClueTier.MEDIUM, null, List.of(new WorldPoint(1845, 3754, 0))),
		new Clues("Zoo keeper: Talk", ItemID.CLUE_SCROLL_MEDIUM_2845, ClueTier.MEDIUM, null, List.of(new WorldPoint(2613, 3269, 0))),
		new Clues("Lowe: Talk", ItemID.CLUE_SCROLL_MEDIUM_2847, ClueTier.MEDIUM, null, List.of(new WorldPoint(3233, 3423, 0))),
		new Clues("Ardougne Monastery: Search", ItemID.CLUE_SCROLL_MEDIUM_3610, ClueTier.MEDIUM, null, List.of(new WorldPoint(2614, 3204, 0))),
		new Clues("Otto: Talk", ItemID.CLUE_SCROLL_MEDIUM_12055, ClueTier.MEDIUM, null, List.of(new WorldPoint(2501, 3487, 0))),
		new Clues("King Bolren: Talk", ItemID.CLUE_SCROLL_MEDIUM_2857, ClueTier.MEDIUM, null, List.of(new WorldPoint(2541, 3170, 0))),
		new Clues("Gabooty: Talk", ItemID.CLUE_SCROLL_MEDIUM_7276, ClueTier.MEDIUM, null, List.of(new WorldPoint(2790, 3066, 0))),
		new Clues("Drezel: Talk", ItemID.CLUE_SCROLL_MEDIUM_19772, ClueTier.MEDIUM, null, List.of(new WorldPoint(3440, 9895, 0))),
		new Clues("Penda: Kill", ItemID.CLUE_SCROLL_MEDIUM_3607, ClueTier.MEDIUM, null, List.of(new WorldPoint(2909, 3539, 0))),
		new Clues("Rellekka guard: Kill", ItemID.CLUE_SCROLL_MEDIUM_7298, ClueTier.MEDIUM, null, List.of(new WorldPoint(2643, 3677, 0))),
		new Clues("Luthas: Talk", ItemID.CLUE_SCROLL_MEDIUM_2858, ClueTier.MEDIUM, null, List.of(new WorldPoint(2938, 3152, 0))),
		new Clues("Eohric: Talk", ItemID.CLUE_SCROLL_MEDIUM_12071, ClueTier.MEDIUM, null, List.of(new WorldPoint(2900, 3565, 0))),
		new Clues("Jethick: Talk", ItemID.CLUE_SCROLL_MEDIUM_7278, ClueTier.MEDIUM, null, List.of(new WorldPoint(2541, 3305, 0))),
		new Clues("Horphis: Talk", ItemID.CLUE_SCROLL_MEDIUM_19742, ClueTier.MEDIUM, null, List.of(new WorldPoint(1639, 3812, 0))),
		new Clues("Ironman tutor: Talk", ItemID.CLUE_SCROLL_MEDIUM_19770, ClueTier.MEDIUM, null, List.of(new WorldPoint(3227, 3227, 0))),
		new Clues("Marisi: Talk", ItemID.CLUE_SCROLL_MEDIUM_19756, ClueTier.MEDIUM, null, List.of(new WorldPoint(1737, 3557, 0))),
		new Clues("Dominic Onion: Talk", ItemID.CLUE_SCROLL_MEDIUM_19736, ClueTier.MEDIUM, null, List.of(new WorldPoint(2609, 3116, 0))),
		new Clues("Nieve/Steve: Talk", ItemID.CLUE_SCROLL_MEDIUM_12059, ClueTier.MEDIUM, null, List.of(new WorldPoint(2432, 3423, 0))),
		new Clues("Fycie: Talk", ItemID.CLUE_SCROLL_MEDIUM_3618, ClueTier.MEDIUM, null, List.of(new WorldPoint(2630, 2997, 0))),
		new Clues("chicken: Kill", ItemID.CLUE_SCROLL_MEDIUM_2837, ClueTier.MEDIUM, null, List.of(new WorldPoint(2709, 3478, 0))),
		new Clues("guard dog: Kill", ItemID.CLUE_SCROLL_MEDIUM_2833, ClueTier.MEDIUM, null, List.of(new WorldPoint(2635, 3313, 0))),
		new Clues("Ardougne guard: Kill", ItemID.CLUE_SCROLL_MEDIUM_2835, ClueTier.MEDIUM, null, List.of(new WorldPoint(2661, 3306, 0))),
		new Clues("Yanille man: Kill", ItemID.CLUE_SCROLL_MEDIUM_2839, ClueTier.MEDIUM, null, List.of(new WorldPoint(2594, 3105, 0))),
		new Clues("Tai Bwo Wannai: Search", ItemID.CLUE_SCROLL_MEDIUM_3604, ClueTier.MEDIUM, null, List.of(new WorldPoint(2800, 3074, 0))),
		new Clues("Yanille bank: Emote", ItemID.CLUE_SCROLL_MEDIUM_10268, ClueTier.MEDIUM, null, List.of(new WorldPoint(2610, 3092, 0))),
		new Clues("TzHaar Sword shop: Emote", ItemID.CLUE_SCROLL_MEDIUM_12029, ClueTier.MEDIUM, null, List.of(new WorldPoint(2477, 5146, 0))),
		new Clues("Sir Kay: Talk", ItemID.CLUE_SCROLL_MEDIUM_12069, ClueTier.MEDIUM, null, List.of(new WorldPoint(2760, 3496, 0))),
		new Clues("Metla: Talk", ItemID.CLUE_SCROLL_MEDIUM_28907, ClueTier.MEDIUM, null, List.of(new WorldPoint(1742, 2977, 0))),
		new Clues("King Roald: Talk", ItemID.CLUE_SCROLL_MEDIUM_7284, ClueTier.MEDIUM, null, List.of(new WorldPoint(3220, 3476, 0))),
		new Clues("Kaylee: Talk", ItemID.CLUE_SCROLL_MEDIUM_12065, ClueTier.MEDIUM, null, List.of(new WorldPoint(2957, 3370, 0))),
		new Clues("Gallow: Talk", ItemID.CLUE_SCROLL_MEDIUM_19738, ClueTier.MEDIUM, null, List.of(new WorldPoint(1805, 3566, 0))),
		new Clues("Ardougne Monastery: Kill", ItemID.CLUE_SCROLL_MEDIUM_2831, ClueTier.MEDIUM, null, List.of(new WorldPoint(2615, 3209, 0))),
		new Clues("Femi: Talk", ItemID.CLUE_SCROLL_MEDIUM_3611, ClueTier.MEDIUM, null, List.of(new WorldPoint(2461, 3382, 0))),
		new Clues("CIP south-east: Dig", ItemID.CLUE_SCROLL_MEDIUM_7286, ClueTier.MEDIUM, null, List.of(new WorldPoint(2536, 3865, 0))),
		new Clues("BIP south: Dig", ItemID.CLUE_SCROLL_MEDIUM_7288, ClueTier.MEDIUM, null, List.of(new WorldPoint(3434, 3266, 0))),
		new Clues("Edmond: Talk", ItemID.CLUE_SCROLL_MEDIUM_7282, ClueTier.MEDIUM, null, List.of(new WorldPoint(2566, 3332, 0))),
		new Clues("DJP west: Dig", ItemID.CLUE_SCROLL_MEDIUM_3599, ClueTier.MEDIUM, null, List.of(new WorldPoint(2650, 3231, 0))),
		new Clues("Cook: Talk", ItemID.CLUE_SCROLL_MEDIUM_2843, ClueTier.MEDIUM, null, List.of(new WorldPoint(3208, 3213, 0))),
		new Clues("Captain Ginea: Talk", ItemID.CLUE_SCROLL_MEDIUM_19734, ClueTier.MEDIUM, null, List.of(new WorldPoint(1504, 3632, 0))),
		new Clues("Flax keeper: Talk", ItemID.CLUE_SCROLL_MEDIUM_19752, ClueTier.MEDIUM, null, List.of(new WorldPoint(2744, 3444, 0))),
		new Clues("Party Pete: Talk", ItemID.CLUE_SCROLL_MEDIUM_2856, ClueTier.MEDIUM, null, List.of(new WorldPoint(3047, 3376, 0))),
		new Clues("Mausoleum: Emote", ItemID.CLUE_SCROLL_MEDIUM_10256, ClueTier.MEDIUM, null, List.of(new WorldPoint(3504, 3576, 0))),
		new Clues("Wizards' Tower: Kill", ItemID.CLUE_SCROLL_MEDIUM_7301, ClueTier.MEDIUM, null, List.of(new WorldPoint(3109, 3164, 0))),
		new Clues("Professor Gracklebone: Talk", ItemID.CLUE_SCROLL_MEDIUM_19762, ClueTier.MEDIUM, null, List.of(new WorldPoint(1625, 3802, 0))),
		new Clues("Squire: Talk", ItemID.CLUE_SCROLL_MEDIUM_19754, ClueTier.MEDIUM, null, List.of(new WorldPoint(2977, 3343, 0))),
		new Clues("Karim: Talk", ItemID.CLUE_SCROLL_MEDIUM_2849, ClueTier.MEDIUM, null, List.of(new WorldPoint(3273, 3181, 0))),
		new Clues("Clerris: Talk", ItemID.CLUE_SCROLL_MEDIUM_19740, ClueTier.MEDIUM, null, List.of(new WorldPoint(1761, 3850, 0))),
		new Clues("Taria: Talk", ItemID.CLUE_SCROLL_MEDIUM_12063, ClueTier.MEDIUM, null, List.of(new WorldPoint(2940, 3223, 0))),
		new Clues("Dunstan: Talk", ItemID.CLUE_SCROLL_MEDIUM_19748, ClueTier.MEDIUM, null, List.of(new WorldPoint(2919, 3574, 0))),
		new Clues("Hosidius apples: Search", ItemID.CLUE_SCROLL_MEDIUM_25783, ClueTier.MEDIUM, null, List.of(new WorldPoint(1718, 3626, 0))),
		new Clues("Brimhaven pirate: Kill", ItemID.CLUE_SCROLL_MEDIUM_3605, ClueTier.MEDIUM, null, List.of(new WorldPoint(2794, 3185, 0))),
		new Clues("Catherby bank: Emote", ItemID.CLUE_SCROLL_MEDIUM_12023, ClueTier.MEDIUM, null, List.of(new WorldPoint(2808, 3440, 0))),
		new Clues("Draynor bank south: Dig", ItemID.CLUE_SCROLL_MEDIUM_2827, ClueTier.MEDIUM, null, List.of(new WorldPoint(3092, 3226, 0))),
		new Clues("ALP south-east: Dig", ItemID.CLUE_SCROLL_MEDIUM_7292, ClueTier.MEDIUM, null, List.of(new WorldPoint(2578, 3597, 0))),
		new Clues("Donovan: Talk", ItemID.CLUE_SCROLL_MEDIUM_2855, ClueTier.MEDIUM, null, List.of(new WorldPoint(2743, 3578, 0))),
		new Clues("Hajedy: Talk", ItemID.CLUE_SCROLL_MEDIUM_2848, ClueTier.MEDIUM, null, List.of(new WorldPoint(2779, 3211, 0))),
		new Clues("Kangai Mau: Talk", ItemID.CLUE_SCROLL_MEDIUM_3617, ClueTier.MEDIUM, null, List.of(new WorldPoint(2791, 3183, 0))),
		new Clues("Roavar: Talk", ItemID.CLUE_SCROLL_MEDIUM_3615, ClueTier.MEDIUM, null, List.of(new WorldPoint(3494, 3474, 0))),
		new Clues("Uglug Nar: Talk", ItemID.CLUE_SCROLL_MEDIUM_2841, ClueTier.MEDIUM, null, List.of(new WorldPoint(2444, 3049, 0))),
		new Clues("Ulizius: Talk", ItemID.CLUE_SCROLL_MEDIUM_3614, ClueTier.MEDIUM, null, List.of(new WorldPoint(3444, 3461, 0))),
		new Clues("Gnome ball ref: Talk", ItemID.CLUE_SCROLL_MEDIUM_2853, ClueTier.MEDIUM, null, List.of(new WorldPoint(2386, 3487, 0))),
		new Clues("Barbarian Village: Emote", ItemID.CLUE_SCROLL_MEDIUM_10258, ClueTier.MEDIUM, null, List.of(new WorldPoint(3105, 3420, 0))),
		new Clues("Dockmaster: Talk", ItemID.CLUE_SCROLL_MEDIUM_19744, ClueTier.MEDIUM, null, List.of(new WorldPoint(1822, 3739, 0))),
		new Clues("Hickton: Talk", ItemID.CLUE_SCROLL_MEDIUM_12067, ClueTier.MEDIUM, null, List.of(new WorldPoint(2822, 3442, 0))),
		new Clues("ALS crate: Search", ItemID.CLUE_SCROLL_MEDIUM_3598, ClueTier.MEDIUM, null, List.of(new WorldPoint(2658, 3488, 0))),
		new Clues("Clock Tower: Search", ItemID.CLUE_SCROLL_MEDIUM_3601, ClueTier.MEDIUM, null, List.of(new WorldPoint(2565, 3248, 0))),
		new Clues("Barbarian: Kill", ItemID.CLUE_SCROLL_MEDIUM_7296, ClueTier.MEDIUM, null, List.of(new WorldPoint(3082, 3419, 0))),
		new Clues("Observatory: Emote", ItemID.CLUE_SCROLL_MEDIUM_10270, ClueTier.MEDIUM, null, List.of(new WorldPoint(2439, 3161, 0))),
		new Clues("Ranging Guild: Search", ItemID.CLUE_SCROLL_MEDIUM_7304, ClueTier.MEDIUM, null, List.of(new WorldPoint(2671, 3437, 0))),
		new Clues("Desert Mining Camp: Search", ItemID.CLUE_SCROLL_MEDIUM_7303, ClueTier.MEDIUM, null, List.of(new WorldPoint(3289, 3022, 0))),
		new Clues("Unobtainable", ItemID.CLUE_SCROLL_MEDIUM_2829, ClueTier.MEDIUM, null, List.of(new WorldPoint(2702, 3429, 0))),
		new Clues("Fishing Platform: Serach", ItemID.CLUE_SCROLL_MEDIUM_7300, ClueTier.MEDIUM, null, List.of(new WorldPoint(2764, 3273, 0))),
		new Clues("Drunken soldier: Talk", ItemID.CLUE_SCROLL_MEDIUM_25784, ClueTier.MEDIUM, null, List.of(new WorldPoint(1551, 3565, 0))),
		new Clues("Crafting Guild west: Dig", ItemID.CLUE_SCROLL_MEDIUM_3596, ClueTier.MEDIUM, null, List.of(new WorldPoint(2907, 3295, 0))),
		new Clues("Wizard Traiborn: Talk", ItemID.CLUE_SCROLL_MEDIUM_19764, ClueTier.MEDIUM, null, List.of(new WorldPoint(3112, 3162, 0))),
		new Clues("Castle Wars: Emote", ItemID.CLUE_SCROLL_MEDIUM_10262, ClueTier.MEDIUM, null, List.of(new WorldPoint(2440, 3092, 0))),
		new Clues("Arceuus Library: Emote", ItemID.CLUE_SCROLL_MEDIUM_19778, ClueTier.MEDIUM, null, List.of(new WorldPoint(1632, 3807, 0))),
		new Clues("Song: Karamja Jam", ItemID.CLUE_SCROLL_MEDIUM_23138, ClueTier.MEDIUM, null, List.of(new WorldPoint(2990, 3384, 0))),
		new Clues("Song: Faerie", ItemID.CLUE_SCROLL_MEDIUM_23139, ClueTier.MEDIUM, null, List.of(new WorldPoint(2990, 3384, 0))),
		new Clues("Song: Forgotten", ItemID.CLUE_SCROLL_MEDIUM_23140, ClueTier.MEDIUM, null, List.of(new WorldPoint(2990, 3384, 0))),
		new Clues("Song: Catch Me If You Can", ItemID.CLUE_SCROLL_MEDIUM_23141, ClueTier.MEDIUM, null, List.of(new WorldPoint(2990, 3384, 0))),
		new Clues("Song: Cave of Beasts", ItemID.CLUE_SCROLL_MEDIUM_23142, ClueTier.MEDIUM, null, List.of(new WorldPoint(2990, 3384, 0))),
		new Clues("Song: Devils May Care", ItemID.CLUE_SCROLL_MEDIUM_23143, ClueTier.MEDIUM, null, List.of(new WorldPoint(2990, 3384, 0))),
		new Clues("Twilight Temple: Emote", ItemID.CLUE_SCROLL_MEDIUM_29857, ClueTier.MEDIUM, null, List.of(new WorldPoint(1672, 3284, 0))),
		new Clues("Proudspire river: Emote", ItemID.CLUE_SCROLL_MEDIUM_29858, ClueTier.MEDIUM, null, List.of(new WorldPoint(1626, 3241, 0))),
		new Clues("Teicuh: Talk", ItemID.CLUE_SCROLL_MEDIUM_30933, ClueTier.MEDIUM, null, List.of(new WorldPoint(1212, 3119, 0))),
		new Clues("Gloomthorn crate: Search", ItemID.CLUE_SCROLL_MEDIUM_30935, ClueTier.MEDIUM, null, List.of(new WorldPoint(1388, 3216, 0))),
		new Clues("6859", ItemID.CHALLENGE_SCROLL_MEDIUM, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint( 2444, 3049, 0))),
		new Clues("9", ItemID.CHALLENGE_SCROLL_MEDIUM_2844, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3208, 3213, 0))),
		new Clues("51", ItemID.CHALLENGE_SCROLL_MEDIUM_2846, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2613, 3269, 0))),
		new Clues("5", ItemID.CHALLENGE_SCROLL_MEDIUM_2850, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3273, 3181, 0))),
		new Clues("48", ItemID.CHALLENGE_SCROLL_MEDIUM_2852, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3013, 3501, 0))),
		new Clues("5096", ItemID.CHALLENGE_SCROLL_MEDIUM_2854, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2386, 3487, 0))),
		new Clues("5", ItemID.CHALLENGE_SCROLL_MEDIUM_7275, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2658, 3670, 0))),
		new Clues("11", ItemID.CHALLENGE_SCROLL_MEDIUM_7277, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2790, 3066, 0))),
		new Clues("38", ItemID.CHALLENGE_SCROLL_MEDIUM_7279, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2541, 3305, 0))),
		new Clues("11", ItemID.CHALLENGE_SCROLL_MEDIUM_7281, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2715, 3302, 0))),
		new Clues("3", ItemID.CHALLENGE_SCROLL_MEDIUM_7283, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2566, 3332, 0))),
		new Clues("24", ItemID.CHALLENGE_SCROLL_MEDIUM_7285, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3220, 3476, 0))),
		new Clues("2", ItemID.CHALLENGE_SCROLL_MEDIUM_12056, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2501, 3487, 0))),
		new Clues("5", ItemID.CHALLENGE_SCROLL_MEDIUM_12058, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3217, 3434, 0))),
		new Clues("2", ItemID.CHALLENGE_SCROLL_MEDIUM_12060, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2432, 3423, 0))),
		new Clues("6", ItemID.CHALLENGE_SCROLL_MEDIUM_12062, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3026, 3216, 0))),
		new Clues("7", ItemID.CHALLENGE_SCROLL_MEDIUM_12064, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2940, 3223, 0))),
		new Clues("18", ItemID.CHALLENGE_SCROLL_MEDIUM_12066, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2957, 3370, 0))),
		new Clues("2", ItemID.CHALLENGE_SCROLL_MEDIUM_12068, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2822, 3442, 0))),
		new Clues("6", ItemID.CHALLENGE_SCROLL_MEDIUM_12070, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2760, 3496, 0))),
		new Clues("36", ItemID.CHALLENGE_SCROLL_MEDIUM_12072, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2900, 3565, 0))),
		new Clues("113", ItemID.CHALLENGE_SCROLL_MEDIUM_19735, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1504, 3632, 0))),
		new Clues("9500", ItemID.CHALLENGE_SCROLL_MEDIUM_19737, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2609, 3116, 0))),
		new Clues("12", ItemID.CHALLENGE_SCROLL_MEDIUM_19739, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1805, 3566, 0))),
		new Clues("738", ItemID.CHALLENGE_SCROLL_MEDIUM_19741, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1761, 3850, 0))),
		new Clues("1", ItemID.CHALLENGE_SCROLL_MEDIUM_19743, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1639, 3812, 0))),
		new Clues("5", ItemID.CHALLENGE_SCROLL_MEDIUM_19745, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1822, 3739, 0))),
		new Clues("19 or 20", ItemID.CHALLENGE_SCROLL_MEDIUM_19747, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3242, 3207, 0))),
		new Clues("8", ItemID.CHALLENGE_SCROLL_MEDIUM_19749, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2919, 3574, 0))),
		new Clues("0", ItemID.CHALLENGE_SCROLL_MEDIUM_19751, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3209, 3392, 0))),
		new Clues("676", ItemID.CHALLENGE_SCROLL_MEDIUM_19753, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2744, 3444, 0))),
		new Clues("654", ItemID.CHALLENGE_SCROLL_MEDIUM_19755, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2977, 3343, 0))),
		new Clues("5", ItemID.CHALLENGE_SCROLL_MEDIUM_19757, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1737, 3557, 0))),
		new Clues("2", ItemID.CHALLENGE_SCROLL_MEDIUM_19759, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1814, 3851, 0))),
		new Clues("9", ItemID.CHALLENGE_SCROLL_MEDIUM_19763, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1625, 3802, 0))),
		new Clues("3150", ItemID.CHALLENGE_SCROLL_MEDIUM_19765, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3112, 3162, 0))),
		new Clues("5", ItemID.CHALLENGE_SCROLL_MEDIUM_19767, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1845, 3754, 0))),
		new Clues("399", ItemID.CHALLENGE_SCROLL_MEDIUM_19769, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3354, 2974, 0))),
		new Clues("666", ItemID.CHALLENGE_SCROLL_MEDIUM_19771, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3227, 3227, 0))),
		new Clues("7", ItemID.CHALLENGE_SCROLL_MEDIUM_19773, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(3440, 9895, 0))),
		new Clues("4", ItemID.CHALLENGE_SCROLL_MEDIUM_23132, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1841, 3803, 0))),
		new Clues("6", ItemID.CHALLENGE_SCROLL_MEDIUM_23134, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(2553, 2868, 0))),
		new Clues("598", ItemID.CHALLENGE_SCROLL_MEDIUM_25785, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1551, 3565, 0))),
		new Clues("4097", ItemID.CHALLENGE_SCROLL_MEDIUM_30934, ClueTier.MEDIUM_CHALLENGE, null, List.of(new WorldPoint(1212, 3119, 0))),
		new Clues("Varrock church: Search", ItemID.KEY_MEDIUM, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(3256, 3487, 0))),
		new Clues("Ardougne pub: Search", ItemID.KEY_MEDIUM_2834, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(2574, 3326, 1))),
		new Clues("Jerico's house: Search", ItemID.KEY_MEDIUM_2836, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(2611, 3324, 1))),
		new Clues("Seers' Village house: Sarch", ItemID.KEY_MEDIUM_2838, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(2709, 3478, 0))),
		new Clues("Yanille house: Search", ItemID.KEY_MEDIUM_2840, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(2593, 3108, 1))),
		new Clues("Brimhaven south-east: Search", ItemID.KEY_MEDIUM_3606, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(2809, 3165, 1))),
		new Clues("Dunstan's house: Search", ItemID.KEY_MEDIUM_3608, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(2921, 3577, 0))),
		new Clues("Exam Centre: Search", ItemID.KEY_MEDIUM_7297, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(3353, 3332, 0))),
		new Clues("Lighthouse: Search", ItemID.KEY_MEDIUM_7299, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(2512, 3641, 1))),
		new Clues("Wizards' Tower: Search", ItemID.KEY_MEDIUM_7302, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(3116, 9562, 0))),
		new Clues("Osten's clothing: Search", ItemID.KEY_MEDIUM_19761, ClueTier.MEDIUM_KEY, null, List.of(new WorldPoint(1506, 3590, 2))),
		new Clues("Tyras Camp: Dig", ItemID.CLUE_SCROLL_HARD_3560, ClueTier.HARD, null, List.of(new WorldPoint(2209, 3161, 0))),
		new Clues("Iorwerth Camp south: Dig", ItemID.CLUE_SCROLL_HARD_3562, ClueTier.HARD, null, List.of(new WorldPoint(2181, 3206, 0))),
		new Clues("CLP: Dig", ItemID.CLUE_SCROLL_HARD_12554, ClueTier.HARD, null, List.of(new WorldPoint(3081, 3209, 0))),
		new Clues("Behind Emir's Arena: Dig", ItemID.CLUE_SCROLL_HARD_3554, ClueTier.HARD, null, List.of(new WorldPoint(3399, 3246, 0))),
		new Clues("AIR: Dig", ItemID.CLUE_SCROLL_HARD_12558, ClueTier.HARD, null, List.of(new WorldPoint(2699, 3251, 0))),
		new Clues("Burgh de Rott north-east 1: Dig", ItemID.CLUE_SCROLL_HARD_19844, ClueTier.HARD, null, List.of(new WorldPoint(3546, 3251, 0))),
		new Clues("Burgh de Rott north-east 2: Dig", ItemID.CLUE_SCROLL_HARD_12562, ClueTier.HARD, null, List.of(new WorldPoint(3544, 3256, 0))),
		new Clues("Crandor: Dig", ItemID.CLUE_SCROLL_HARD_12560, ClueTier.HARD, null, List.of(new WorldPoint(2841, 3267, 0))),
		new Clues("Bedabin Camp: Dig", ItemID.CLUE_SCROLL_HARD_3552, ClueTier.HARD, null, List.of(new WorldPoint(3168, 3041, 0))),
		new Clues("Gu'Tanoth: Dig", ItemID.CLUE_SCROLL_HARD_3546, ClueTier.HARD, null, List.of(new WorldPoint(2542, 3031, 0))),
		new Clues("Gu'Tanoth island: Dig", ItemID.CLUE_SCROLL_HARD_3548, ClueTier.HARD, null, List.of(new WorldPoint(2581, 3030, 0))),
		new Clues("DKP Ship yard: Dig", ItemID.CLUE_SCROLL_HARD_3538, ClueTier.HARD, null, List.of(new WorldPoint(2961, 3024, 0))),
		new Clues("Arandar pass: Dig", ItemID.CLUE_SCROLL_HARD_7256, ClueTier.HARD, null, List.of(new WorldPoint(2339, 3311, 0))),
		new Clues("Unobtainable: Dig", ItemID.CLUE_SCROLL_HARD_3550, ClueTier.HARD, null, List.of(new WorldPoint(3288, 2982, 0))),
		new Clues("Nature Spirit's grotto: Dig", ItemID.CLUE_SCROLL_HARD_3542, ClueTier.HARD, null, List.of(new WorldPoint(3440, 3341, 0))),
		new Clues("Cairn Isle: Dig", ItemID.CLUE_SCROLL_HARD_3530, ClueTier.HARD, null, List.of(new WorldPoint(2763, 2974, 0))),
		new Clues("Desert Bandit Camp: Dig", ItemID.CLUE_SCROLL_HARD_7258, ClueTier.HARD, null, List.of(new WorldPoint(3138, 2969, 0))),
		new Clues("Karamja south-east: Dig", ItemID.CLUE_SCROLL_HARD_3540, ClueTier.HARD, null, List.of(new WorldPoint(2924, 2963, 0))),
		new Clues("Kharazi Jungle pool: Dig", ItemID.CLUE_SCROLL_HARD_3534, ClueTier.HARD, null, List.of(new WorldPoint(2838, 2914, 0))),
		new Clues("BKR: Dig", ItemID.CLUE_SCROLL_HARD_3544, ClueTier.HARD, null, List.of(new WorldPoint(3441, 3419, 0))),
		new Clues("Kharazi Jungle south-east: Dig", ItemID.CLUE_SCROLL_HARD_3536, ClueTier.HARD, null, List.of(new WorldPoint(2950, 2902, 0))),
		new Clues("Kharazi Jungle south-west: Dig", ItemID.CLUE_SCROLL_HARD_3532, ClueTier.HARD, null, List.of(new WorldPoint(2775, 2891, 0))),
		new Clues("Shayziens' Wall: Dig", ItemID.CLUE_SCROLL_HARD_25790, ClueTier.HARD, null, List.of(new WorldPoint(1409, 3483, 0))),
		new Clues("Ferox Enclave: Dig", ItemID.CLUE_SCROLL_HARD_7262, ClueTier.HARD, null, List.of(new WorldPoint(3113, 3602, 0))),
		new Clues("Trollheim: Dig", ItemID.CLUE_SCROLL_HARD_3526, ClueTier.HARD, null, List.of(new WorldPoint(2892, 3675, 0))),
		new Clues("Graveyard of Shadows: Dig", ItemID.CLUE_SCROLL_HARD_2743, ClueTier.HARD, null, List.of(new WorldPoint(3168, 3677, 0))),
		new Clues("Troll Stronghold: Dig", ItemID.CLUE_SCROLL_HARD_3528, ClueTier.HARD, null, List.of(new WorldPoint(2853, 3690, 0))),
		new Clues("Wilderness salamanders 1: Dig", ItemID.CLUE_SCROLL_HARD_7264, ClueTier.HARD, null, List.of(new WorldPoint(3305, 3692, 0))),
		new Clues("Bandit Camp: Dig", ItemID.CLUE_SCROLL_HARD_2745, ClueTier.HARD, null, List.of(new WorldPoint(3055, 3696, 0))),
		new Clues("Wilderness salamanders 2: Dig", ItemID.CLUE_SCROLL_HARD_12564, ClueTier.HARD, null, List.of(new WorldPoint(3302, 3696, 0))),
		new Clues("Piscatoris Fishing Colony: Dig", ItemID.CLUE_SCROLL_HARD_23167, ClueTier.HARD, null, List.of(new WorldPoint(2341, 3697, 0))),
		new Clues("Lizardman Canyon: Dig", ItemID.CLUE_SCROLL_HARD_19840, ClueTier.HARD, null, List.of(new WorldPoint(1479, 3699, 0))),
		new Clues("DKS west: Dig", ItemID.CLUE_SCROLL_HARD_7266, ClueTier.HARD, null, List.of(new WorldPoint(2712, 3732, 0))),
		new Clues("Forgotten Cemetery: Dig", ItemID.CLUE_SCROLL_HARD_7260, ClueTier.HARD, null, List.of(new WorldPoint(2970, 3749, 0))),
		new Clues("Bandit mining site: Dig", ItemID.CLUE_SCROLL_HARD_3556, ClueTier.HARD, null, List.of(new WorldPoint(3094, 3764, 0))),
		new Clues("Silk Chasm south: Dig", ItemID.CLUE_SCROLL_HARD_2747, ClueTier.HARD, null, List.of(new WorldPoint(3311, 3769, 0))),
		new Clues("Black Chinchompa: Dig", ItemID.CLUE_SCROLL_HARD_23168, ClueTier.HARD, null, List.of(new WorldPoint(3143, 3774, 0))),
		new Clues("Burning Man: Dig", ItemID.CLUE_SCROLL_HARD_19842, ClueTier.HARD, null, List.of(new WorldPoint(1460, 3782, 0))),
		new Clues("Lava Dragon Isle: Dig", ItemID.CLUE_SCROLL_HARD_2741, ClueTier.HARD, null, List.of(new WorldPoint(3244, 3792, 0))),
		new Clues("Black Chinchompa north: Dig", ItemID.CLUE_SCROLL_HARD_2733, ClueTier.HARD, null, List.of(new WorldPoint(3140, 3804, 0))),
		new Clues("Chaos Temple 38: Dig", ItemID.CLUE_SCROLL_HARD_2735, ClueTier.HARD, null, List.of(new WorldPoint(2946, 3819, 0))),
		new Clues("Museum Camp east: Dig", ItemID.CLUE_SCROLL_HARD_21526, ClueTier.HARD, null, List.of(new WorldPoint(3771, 3825, 0))),
		new Clues("KBD entrance: Dig", ItemID.CLUE_SCROLL_HARD_2737, ClueTier.HARD, null, List.of(new WorldPoint(3013, 3846, 0))),
		new Clues("Wilderness runite: Dig", ItemID.CLUE_SCROLL_HARD_2723, ClueTier.HARD, null, List.of(new WorldPoint(3058, 3884, 0))),
		new Clues("Demonic Ruins: Dig", ItemID.CLUE_SCROLL_HARD_2731, ClueTier.HARD, null, List.of(new WorldPoint(3290, 3889, 0))),
		new Clues("Fossil Island island: Dig", ItemID.CLUE_SCROLL_HARD_21527, ClueTier.HARD, null, List.of(new WorldPoint(3770, 3897, 0))),
		new Clues("AJS: Dig", ItemID.CLUE_SCROLL_HARD_12556, ClueTier.HARD, null, List.of(new WorldPoint(2505, 3899, 0))),
		new Clues("Frozen Waste Plateau: Dig", ItemID.CLUE_SCROLL_HARD_23169, ClueTier.HARD, null, List.of(new WorldPoint(2970, 3913, 0))),
		new Clues("Rogues' Castle: Dig", ItemID.CLUE_SCROLL_HARD_3558, ClueTier.HARD, null, List.of(new WorldPoint(3285, 3942, 0))),
		new Clues("Wilderness lever north: Dig", ItemID.CLUE_SCROLL_HARD_2727, ClueTier.HARD, null, List.of(new WorldPoint(3159, 3959, 0))),
		new Clues("Pirates' Hideout: Dig", ItemID.CLUE_SCROLL_HARD_2739, ClueTier.HARD, null, List.of(new WorldPoint(3039, 3960, 0))),
		new Clues("Wilderness Agility west: Dig", ItemID.CLUE_SCROLL_HARD_2725, ClueTier.HARD, null, List.of(new WorldPoint(2987, 3963, 0))),
		new Clues("Magic axe hut: Dig", ItemID.CLUE_SCROLL_HARD_2729, ClueTier.HARD, null, List.of(new WorldPoint(3189, 3963, 0))),
		new Clues("Wilderness spider hill: Dig", ItemID.CLUE_SCROLL_HARD_2788, ClueTier.HARD, null, List.of(new WorldPoint(3170, 3885, 0))),
		new Clues("AJP: Dig", ItemID.CLUE_SCROLL_HARD_28915, ClueTier.HARD, null, List.of(new WorldPoint(1646, 3012, 0))),
		new Clues("Abbot Langley: Talk", ItemID.CLUE_SCROLL_HARD_2793, ClueTier.HARD, null, List.of(new WorldPoint(3052, 3490, 0))),
		new Clues("General Hining: Talk", ItemID.CLUE_SCROLL_HARD_3564, ClueTier.HARD, null, List.of(new WorldPoint(2186, 3148, 0))),
		new Clues("Almera's house: Search", ItemID.CLUE_SCROLL_HARD_3573, ClueTier.HARD, null, List.of(new WorldPoint(2523, 3493, 0))),
		new Clues("AIQ: Dig", ItemID.CLUE_SCROLL_HARD_19862, ClueTier.HARD, null, List.of(new WorldPoint(3000, 3110, 0))),
		new Clues("AIR: Dig", ItemID.CLUE_SCROLL_HARD_19864, ClueTier.HARD, null, List.of(new WorldPoint(2702, 3246, 0))),
		new Clues("ALP: Dig", ItemID.CLUE_SCROLL_HARD_19866, ClueTier.HARD, null, List.of(new WorldPoint(2504, 3633, 0))),
		new Clues("Aggie's house: Dig", ItemID.CLUE_SCROLL_HARD_2780, ClueTier.HARD, null, List.of(new WorldPoint(3085, 3255, 0))),
		new Clues("Etceteria castle: Dig", ItemID.CLUE_SCROLL_HARD_7243, ClueTier.HARD, null, List.of(new WorldPoint(2591, 3879, 0))),
		new Clues("Brambickle: Talk", ItemID.CLUE_SCROLL_HARD_19894, ClueTier.HARD, null, List.of(new WorldPoint(2783, 3861, 0))),
		new Clues("BIP: Dig", ItemID.CLUE_SCROLL_HARD_19868, ClueTier.HARD, null, List.of(new WorldPoint(3407, 3330, 0))),
		new Clues("BJR: Dig", ItemID.CLUE_SCROLL_HARD_19870, ClueTier.HARD, null, List.of(new WorldPoint(2648, 4729, 0))),
		new Clues("BLP: Dig", ItemID.CLUE_SCROLL_HARD_19872, ClueTier.HARD, null, List.of(new WorldPoint(2439, 5132, 0))),
		new Clues("Lumbridge Guide: Talk", ItemID.CLUE_SCROLL_HARD_19886, ClueTier.HARD, null, List.of(new WorldPoint(3238, 3220, 0))),
		new Clues("Arnold Lydspor: Talk", ItemID.CLUE_SCROLL_HARD_23170, ClueTier.HARD, null, List.of(new WorldPoint(2329, 3689, 0))),
		new Clues("Soar Leader Pitri: Talk", ItemID.CLUE_SCROLL_HARD_28916, ClueTier.HARD, null, List.of(new WorldPoint(1558, 3046, 0))),
		new Clues("Bolkoy: Talk", ItemID.CLUE_SCROLL_HARD_7270, ClueTier.HARD, null, List.of(new WorldPoint(2529, 3162, 0))),
		new Clues("Kharazi Jungle east: Emote", ItemID.CLUE_SCROLL_HARD_12544, ClueTier.HARD, null, List.of(new WorldPoint(2954, 2933, 0))),
		new Clues("Fishing Guild bank: Emote", ItemID.CLUE_SCROLL_HARD_10236, ClueTier.HARD, null, List.of(new WorldPoint(2588, 3419, 0))),
		new Clues("Shilo Village bank: Emote", ItemID.CLUE_SCROLL_HARD_10252, ClueTier.HARD, null, List.of(new WorldPoint(2852, 2952, 0))),
		new Clues("Lighthouse top: Emote", ItemID.CLUE_SCROLL_HARD_10238, ClueTier.HARD, null, List.of(new WorldPoint(2511, 3641, 0))),
		new Clues("Gnome Coach: Talk", ItemID.CLUE_SCROLL_HARD_7268, ClueTier.HARD, null, List.of(new WorldPoint(2395, 3486, 0))),
		new Clues("CIS: Dig", ItemID.CLUE_SCROLL_HARD_19880, ClueTier.HARD, null, List.of(new WorldPoint(1630, 3868, 0))),
		new Clues("CKP: Dig", ItemID.CLUE_SCROLL_HARD_19874, ClueTier.HARD, null, List.of(new WorldPoint(2073, 4846, 0))),
		new Clues("Prospector Percy: Talk", ItemID.CLUE_SCROLL_HARD_12566, ClueTier.HARD, null, List.of(new WorldPoint(3061, 3377, 0))),
		new Clues("Agility pyramid: Emote", ItemID.CLUE_SCROLL_HARD_12550, ClueTier.HARD, null, List.of(new WorldPoint(3043, 4697, 0))),
		new Clues("Heckel Funch: Talk", ItemID.CLUE_SCROLL_HARD_3575, ClueTier.HARD, null, List.of(new WorldPoint(2490, 3488, 0))),
		new Clues("Gnome trainer: Talk", ItemID.CLUE_SCROLL_HARD_3577, ClueTier.HARD, null, List.of(new WorldPoint(2469, 3435, 0))),
		new Clues("Miner Magnus: Talk", ItemID.CLUE_SCROLL_HARD_19846, ClueTier.HARD, null, List.of(new WorldPoint(2527, 3891, 0))),
		new Clues("Edgeville Yew: Dig", ItemID.CLUE_SCROLL_HARD_2774, ClueTier.HARD, null, List.of(new WorldPoint(3089, 3468, 0))),
		new Clues("Mort'ton centre: Dig", ItemID.CLUE_SCROLL_HARD_7245, ClueTier.HARD, null, List.of(new WorldPoint(3488, 3289, 0))),
		new Clues("Daer Krand: Talk", ItemID.CLUE_SCROLL_HARD_24493, ClueTier.HARD, null, List.of(new WorldPoint(3728, 3302, 0))),
		new Clues("Dark Mage: Talk", ItemID.CLUE_SCROLL_HARD_19888, ClueTier.HARD, null, List.of(new WorldPoint(3039, 4835, 0))),
		new Clues("DIP: Dig", ItemID.CLUE_SCROLL_HARD_19876, ClueTier.HARD, null, List.of(new WorldPoint(3041, 4770, 0))),
		new Clues("DKS: Dig", ItemID.CLUE_SCROLL_HARD_19878, ClueTier.HARD, null, List.of(new WorldPoint(2747, 3720, 0))),
		new Clues("Doomsayer: Talk", ItemID.CLUE_SCROLL_HARD_12576, ClueTier.HARD, null, List.of(new WorldPoint(3230, 3230, 0))),
		new Clues("Drunken Dwarf: Talk", ItemID.CLUE_SCROLL_HARD_19890, ClueTier.HARD, null, List.of(new WorldPoint(2913, 10221, 0))),
		new Clues("strange old man: Talk", ItemID.CLUE_SCROLL_HARD_12568, ClueTier.HARD, null, List.of(new WorldPoint(3564, 3288, 0))),
		new Clues("Great pyramid: Emote", ItemID.CLUE_SCROLL_HARD_10242, ClueTier.HARD, null, List.of(new WorldPoint(3294, 2781, 0))),
		new Clues("Forthos Dungeon: Dig", ItemID.CLUE_SCROLL_HARD_25791, ClueTier.HARD, null, List.of(new WorldPoint(1820, 9935, 0))),
		new Clues("Champions' Guild: Dig", ItemID.CLUE_SCROLL_HARD_19848, ClueTier.HARD, null, List.of(new WorldPoint(3195, 3357, 0))),
		new Clues("Lumbridge mill: Search", ItemID.CLUE_SCROLL_HARD_2785, ClueTier.HARD, null, List.of(new WorldPoint(3166, 3309, 0))),
		new Clues("Fairy Queen: Talk", ItemID.CLUE_SCROLL_HARD_19910, ClueTier.HARD, null, List.of(new WorldPoint(2347, 4435, 0))),
		new Clues("General Bentnoze: Talk", ItemID.CLUE_SCROLL_HARD_2799, ClueTier.HARD, null, List.of(new WorldPoint(2957, 3511, 0))),
		new Clues("Saniboch: Talk", ItemID.CLUE_SCROLL_HARD_12578, ClueTier.HARD, null, List.of(new WorldPoint(2745, 3151, 0))),
		new Clues("Fairy Godfather: Talk", ItemID.CLUE_SCROLL_HARD_19908, ClueTier.HARD, null, List.of(new WorldPoint(2446, 4428, 0))),
		new Clues("Brother Kojo: Talk", ItemID.CLUE_SCROLL_HARD_19854, ClueTier.HARD, null, List.of(new WorldPoint(2570, 3250, 0))),
		new Clues("Exam Centre: Emote", ItemID.CLUE_SCROLL_HARD_12542, ClueTier.HARD, null, List.of(new WorldPoint(3362, 3340, 0))),
		new Clues("Uzer crate: Search", ItemID.CLUE_SCROLL_HARD_7253, ClueTier.HARD, null, List.of(new WorldPoint(3478, 3091, 0))),
		new Clues("West Varrock bank: Dig", ItemID.CLUE_SCROLL_HARD_2776, ClueTier.HARD, null, List.of(new WorldPoint(3191, 9825, 0))),
		new Clues("Spirit tree: Talk", ItemID.CLUE_SCROLL_HARD_19858, ClueTier.HARD, null, List.of(new WorldPoint(2544, 3170, 0))),
		new Clues("Graveyard of Shadows: Dig", ItemID.CLUE_SCROLL_HARD_2786, ClueTier.HARD, null, List.of(new WorldPoint(3174, 3663, 0))),
		new Clues("Ellena: Talk", ItemID.CLUE_SCROLL_HARD_12581, ClueTier.HARD, null, List.of(new WorldPoint(2860, 3431, 0))),
		new Clues("Gunnjorn: Talk", ItemID.CLUE_SCROLL_HARD_23172, ClueTier.HARD, null, List.of(new WorldPoint(2541, 3548, 0))),
		new Clues("Hamid: Talk", ItemID.CLUE_SCROLL_HARD_3568, ClueTier.HARD, null, List.of(new WorldPoint(3376, 3284, 0))),
		new Clues("Gerrant: Talk", ItemID.CLUE_SCROLL_HARD_2778, ClueTier.HARD, null, List.of(new WorldPoint(3014, 3222, 0))),
		new Clues("Ranging Guild hay: Search", ItemID.CLUE_SCROLL_HARD_7254, ClueTier.HARD, null, List.of(new WorldPoint(2672, 3416, 0))),
		new Clues("Dark Warriors' Fortress: Search", ItemID.CLUE_SCROLL_HARD_3525, ClueTier.HARD, null, List.of(new WorldPoint(3026, 3628, 0))),
		new Clues("Observatory Dungeon: Search", ItemID.CLUE_SCROLL_HARD_3524, ClueTier.HARD, null, List.of(new WorldPoint(2457, 3182, 0))),
		new Clues("Head chef: Talk", ItemID.CLUE_SCROLL_HARD_19853, ClueTier.HARD, null, List.of(new WorldPoint(3143, 3445, 0))),
		new Clues("West Ardougne: Dig", ItemID.CLUE_SCROLL_HARD_3522, ClueTier.HARD, null, List.of(new WorldPoint(2488, 3308, 0))),
		new Clues("Dwarven Mine: Search", ItemID.CLUE_SCROLL_HARD_7251, ClueTier.HARD, null, List.of(new WorldPoint(3041, 9820, 0))),
		new Clues("Jiggig: Emote", ItemID.CLUE_SCROLL_HARD_12548, ClueTier.HARD, null, List.of(new WorldPoint(2477, 3047, 0))),
		new Clues("Mountain Camp: Emote", ItemID.CLUE_SCROLL_HARD_10248, ClueTier.HARD, null, List.of(new WorldPoint(2812, 3681, 0))),
		new Clues("Mawnis Burowgar: Talk", ItemID.CLUE_SCROLL_HARD_19856, ClueTier.HARD, null, List.of(new WorldPoint(2336, 3799, 0))),
		new Clues("Brother Omad: Talk", ItemID.CLUE_SCROLL_HARD_12574, ClueTier.HARD, null, List.of(new WorldPoint(2606, 3211, 0))),
		new Clues("Lammy Langle: Talk", ItemID.CLUE_SCROLL_HARD_19896, ClueTier.HARD, null, List.of(new WorldPoint(1688, 3540, 0))),
		new Clues("Karamja Volcano: Dig", ItemID.CLUE_SCROLL_HARD_3580, ClueTier.HARD, null, List.of(new WorldPoint(2832, 9586, 0))),
		new Clues("Dwarf Cannon: Search", ItemID.CLUE_SCROLL_HARD_3574, ClueTier.HARD, null, List.of(new WorldPoint(2576, 3464, 0))),
		new Clues("moss giants: Dig", ItemID.CLUE_SCROLL_HARD_2790, ClueTier.HARD, null, List.of(new WorldPoint(3161, 9904, 0))),
		new Clues("Lumbridge Castle: Search", ItemID.CLUE_SCROLL_HARD_2782, ClueTier.HARD, null, List.of(new WorldPoint(3213, 3216, 0))),
		new Clues("Wilough: Talk", ItemID.CLUE_SCROLL_HARD_2797, ClueTier.HARD, null, List.of(new WorldPoint(3221, 3435, 0))),
		new Clues("Cap'n Izzy: Talk", ItemID.CLUE_SCROLL_HARD_7272, ClueTier.HARD, null, List.of(new WorldPoint(2807, 3191, 0))),
		new Clues("Eluned: Talk", ItemID.CLUE_SCROLL_HARD_19898, ClueTier.HARD, null, List.of(new WorldPoint(2289, 3144, 0))),
		new Clues("Examiner: Talk", ItemID.CLUE_SCROLL_HARD_3566, ClueTier.HARD, null, List.of(new WorldPoint(3362, 3341, 0))),
		new Clues("White Wolf Mountain: Emote", ItemID.CLUE_SCROLL_HARD_10250, ClueTier.HARD, null, List.of(new WorldPoint(2847, 3499, 0))),
		new Clues("Haunted Woods: Emote", ItemID.CLUE_SCROLL_HARD_10240, ClueTier.HARD, null, List.of(new WorldPoint(3611, 3492, 0))),
		new Clues("Wilderness volcano: Emote", ItemID.CLUE_SCROLL_HARD_12546, ClueTier.HARD, null, List.of(new WorldPoint(3368, 3935, 0))),
		new Clues("Wizards' Tower: Search", ItemID.CLUE_SCROLL_HARD_7249, ClueTier.HARD, null, List.of(new WorldPoint(3096, 9572, 0))),
		new Clues("Brother Tranquility: Talk", ItemID.CLUE_SCROLL_HARD_19892, ClueTier.HARD, null, List.of(new WorldPoint(3681, 2963, 0))),
		new Clues("Martin Thwait: Talk", ItemID.CLUE_SCROLL_HARD_12570, ClueTier.HARD, null, List.of(new WorldPoint(3044, 4969, 0))),
		new Clues("Sorcerer's Tower: Search", ItemID.CLUE_SCROLL_HARD_3572, ClueTier.HARD, null, List.of(new WorldPoint(2702, 3409, 0))),
		new Clues("Burgh de Rott fish: Dig", ItemID.CLUE_SCROLL_HARD_19850, ClueTier.HARD, null, List.of(new WorldPoint(3547, 3183, 0))),
		new Clues("Wise Old Man: Talk", ItemID.CLUE_SCROLL_HARD_12572, ClueTier.HARD, null, List.of(new WorldPoint(3088, 3253, 0))),
		new Clues("Karamja banana: Emote", ItemID.CLUE_SCROLL_HARD_10246, ClueTier.HARD, null, List.of(new WorldPoint(2914, 3168, 0))),
		new Clues("Mess Hall: Emote", ItemID.CLUE_SCROLL_HARD_12552, ClueTier.HARD, null, List.of(new WorldPoint(1646, 3631, 0))),
		new Clues("Obelisk 19 east: Emote", ItemID.CLUE_SCROLL_HARD_10234, ClueTier.HARD, null, List.of(new WorldPoint(3241, 3672, 0))),
		new Clues("Hans: Talk", ItemID.CLUE_SCROLL_HARD_2792, ClueTier.HARD, null, List.of(new WorldPoint(3221, 3218, 0))),
		new Clues("BLR south-west: Dig", ItemID.CLUE_SCROLL_HARD_7241, ClueTier.HARD, null, List.of(new WorldPoint(2722, 3338, 0))),
		new Clues("Wilderness Agility south: Dig", ItemID.CLUE_SCROLL_HARD_7239, ClueTier.HARD, null, List.of(new WorldPoint(3021, 3912, 0))),
		new Clues("Sir Prysin: Talk", ItemID.CLUE_SCROLL_HARD_2796, ClueTier.HARD, null, List.of(new WorldPoint(3205, 3474, 0))),
		new Clues("Sir Vyvin: Talk", ItemID.CLUE_SCROLL_HARD_12584, ClueTier.HARD, null, List.of(new WorldPoint(2983, 3338, 0))),
		new Clues("Barbarian Village: Dig", ItemID.CLUE_SCROLL_HARD_12590, ClueTier.HARD, null, List.of(new WorldPoint(3081, 3421, 0))),
		new Clues("Yu'biusk: Dig", ItemID.CLUE_SCROLL_HARD_26566, ClueTier.HARD, null, List.of(new WorldPoint(3572, 4372, 0))),
		new Clues("Guardian mummy: Talk", ItemID.CLUE_SCROLL_HARD_12587, ClueTier.HARD, null, List.of(new WorldPoint(1934, 4427, 0))),
		new Clues("Song: The Moons of Ruin", ItemID.CLUE_SCROLL_HARD_28918, ClueTier.HARD, null, List.of(new WorldPoint(2990, 3384, 0))),
		new Clues("Ardougne Zoo: Dig", ItemID.CLUE_SCROLL_HARD_2783, ClueTier.HARD, null, List.of(new WorldPoint(2598, 3267, 0))),
		new Clues("Desert Bandit Camp: Search", ItemID.CLUE_SCROLL_HARD_7248, ClueTier.HARD, null, List.of(new WorldPoint(3178, 2987, 0))),
		new Clues("Ket'sal K'uk: Dig", ItemID.CLUE_SCROLL_HARD_23045, ClueTier.HARD, null, List.of(new WorldPoint(1179, 3626, 0))),
		new Clues("Oziach: Talk", ItemID.CLUE_SCROLL_HARD_2794, ClueTier.HARD, null, List.of(new WorldPoint(3068, 3516, 0))),
		new Clues("Wizard Mizgog: Talk", ItemID.CLUE_SCROLL_HARD_19857, ClueTier.HARD, null, List.of(new WorldPoint(3103, 3163, 0))),
		new Clues("Zul-Cheray: Talk", ItemID.CLUE_SCROLL_HARD_19852, ClueTier.HARD, null, List.of(new WorldPoint(2204, 3050, 0))),
		new Clues("Captain Bleemadge: Talk", ItemID.CLUE_SCROLL_HARD_3570, ClueTier.HARD, null, List.of(new WorldPoint(2847, 3499, 0))),
		new Clues("Shilo Village mine: Search", ItemID.CLUE_SCROLL_HARD_7247, ClueTier.HARD, null, List.of(new WorldPoint(2833, 2992, 0))),
		new Clues("Jimmy Dazzler's: Search", ItemID.CLUE_SCROLL_HARD_7255, ClueTier.HARD, null, List.of(new WorldPoint(2561, 3323, 0))),
		new Clues("Otto Godblessed: Talk", ItemID.CLUE_SCROLL_HARD_19900, ClueTier.HARD, null, List.of(new WorldPoint(2501, 3487, 0))),
		new Clues("Evil Dave: Talk", ItemID.CLUE_SCROLL_HARD_19882, ClueTier.HARD, null, List.of(new WorldPoint(3079, 9892, 0))),
		new Clues("King Percival: Talk", ItemID.CLUE_SCROLL_HARD_19906, ClueTier.HARD, null, List.of(new WorldPoint(2634, 4682, 0))),
		new Clues("Awowogei: Talk", ItemID.CLUE_SCROLL_HARD_19884, ClueTier.HARD, null, List.of(new WorldPoint(2802, 2765, 0))),
		new Clues("Entrana: Search", ItemID.CLUE_SCROLL_HARD_3579, ClueTier.HARD, null, List.of(new WorldPoint(2818, 3351, 0))),
		new Clues("Yanille Agility: Search", ItemID.CLUE_SCROLL_HARD_7252, ClueTier.HARD, null, List.of(new WorldPoint(2576, 9583, 0))),
		new Clues("Citharede Abbey: Dig", ItemID.CLUE_SCROLL_HARD_19860, ClueTier.HARD, null, List.of(new WorldPoint(3388, 3152, 0))),
		new Clues("Yanille anvils: Dig", ItemID.CLUE_SCROLL_HARD_3520, ClueTier.HARD, null, List.of(new WorldPoint(2616, 3077, 0))),
		new Clues("Noterazzo's shop: Emote", ItemID.CLUE_SCROLL_HARD_10244, ClueTier.HARD, null, List.of(new WorldPoint(3026, 3701, 0))),
		new Clues("Elemental Workshop: Search", ItemID.CLUE_SCROLL_HARD_7250, ClueTier.HARD, null, List.of(new WorldPoint(2723, 9891, 0))),
		new Clues("Lumbridge Castle basement: Search", ItemID.CLUE_SCROLL_HARD_2773, ClueTier.HARD, null, List.of(new WorldPoint(3219, 9617, 0))),
		new Clues("Weird Old Man: Talk", ItemID.CLUE_SCROLL_HARD_19904, ClueTier.HARD, null, List.of(new WorldPoint(3224, 3112, 0))),
		new Clues("Pirate Pete: Talk", ItemID.CLUE_SCROLL_HARD_19902, ClueTier.HARD, null, List.of(new WorldPoint(3680, 3537, 0))),
		new Clues("Song: Scorpia Dances", ItemID.CLUE_SCROLL_HARD_23174, ClueTier.HARD, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: Complication", ItemID.CLUE_SCROLL_HARD_23175, ClueTier.HARD, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Lumber Yard: Search", ItemID.CLUE_SCROLL_HARD, ClueTier.HARD, null, List.of(new WorldPoint(3309, 3503, 0))),
		new Clues("Song: Subterranea", ItemID.CLUE_SCROLL_HARD_23176, ClueTier.HARD, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: Little Cave of Horrors", ItemID.CLUE_SCROLL_HARD_23177, ClueTier.HARD, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: Roc and Roll", ItemID.CLUE_SCROLL_HARD_23178, ClueTier.HARD, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: La Mort", ItemID.CLUE_SCROLL_HARD_23179, ClueTier.HARD, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: Fossilised", ItemID.CLUE_SCROLL_HARD_23180, ClueTier.HARD, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: Hells Bells", ItemID.CLUE_SCROLL_HARD_23181, ClueTier.HARD, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: Regal Pomp", ItemID.CLUE_SCROLL_HARD_25792, ClueTier.HARD, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Twlight Temple: Emote", ItemID.CLUE_SCROLL_HARD_29859, ClueTier.HARD, null, List.of(new WorldPoint(1694, 3247, 0))),
		new Clues("Sulisal: Talk", ItemID.CLUE_SCROLL_HARD_30929, ClueTier.HARD, null, List.of(new WorldPoint(1376, 3037, 0))),
		new Clues("Custodia Pass south: Dig", ItemID.CLUE_SCROLL_HARD_30931, ClueTier.HARD, null, List.of(new WorldPoint(1321, 3323, 0))),
		new Clues("6", ItemID.CHALLENGE_SCROLL_HARD, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2395, 3486, 0))),
		new Clues("13", ItemID.CHALLENGE_SCROLL_HARD_7271, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2529, 3162, 0))),
		new Clues("33", ItemID.CHALLENGE_SCROLL_HARD_7273, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2807, 3191, 0))),
		new Clues("12", ItemID.CHALLENGE_SCROLL_HARD_12567, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(3061, 3377, 0))),
		new Clues("40", ItemID.CHALLENGE_SCROLL_HARD_12569, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(3564, 3288, 0))),
		new Clues("2", ItemID.CHALLENGE_SCROLL_HARD_12571, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(3044, 4969, 0))),
		new Clues("28", ItemID.CHALLENGE_SCROLL_HARD_12573, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(3088, 3253, 0))),
		new Clues("129", ItemID.CHALLENGE_SCROLL_HARD_12575, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2606, 3211, 0))),
		new Clues("95", ItemID.CHALLENGE_SCROLL_HARD_12577, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(3230, 3230, 0))),
		new Clues("8", ItemID.CHALLENGE_SCROLL_HARD_19847, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2527, 3891, 0))),
		new Clues("22", ItemID.CHALLENGE_SCROLL_HARD_19855, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2570, 3250, 0))),
		new Clues("13112221", ItemID.CHALLENGE_SCROLL_HARD_19859, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2544, 3170, 0))),
		new Clues("666", ItemID.CHALLENGE_SCROLL_HARD_19883, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(3079, 9892, 0))),
		new Clues("24", ItemID.CHALLENGE_SCROLL_HARD_19885, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2802, 2765, 0))),
		new Clues("13", ItemID.CHALLENGE_SCROLL_HARD_19889, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(3039, 4835, 0))),
		new Clues("7", ItemID.CHALLENGE_SCROLL_HARD_19893, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(3681, 2963, 0))),
		new Clues("53000", ItemID.CHALLENGE_SCROLL_HARD_19899, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2289, 3144, 0))),
		new Clues("3", ItemID.CHALLENGE_SCROLL_HARD_19901, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(3680, 3537, 0))),
		new Clues("150", ItemID.CHALLENGE_SCROLL_HARD_19905, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(3224, 3112, 0))),
		new Clues("5", ItemID.CHALLENGE_SCROLL_HARD_19907, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2634, 4682, 1))),
		new Clues("64", ItemID.CHALLENGE_SCROLL_HARD_19909, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(2446, 4428, 0))),
		new Clues("4", ItemID.CHALLENGE_SCROLL_HARD_30930, ClueTier.HARD_CHALLENGE, null, List.of(new WorldPoint(1376, 3037, 0))),
		new Clues("West Ardougne Church: Emote", ItemID.CLUE_SCROLL_ELITE, ClueTier.ELITE, null, List.of(new WorldPoint(2528, 3294, 0))),
		new Clues("Dominic Onion: Talk", ItemID.CLUE_SCROLL_ELITE_12157, ClueTier.ELITE, null, List.of(new WorldPoint(2609, 3116, 0))),
		new Clues("Ortus Farm north: Dig", ItemID.CLUE_SCROLL_ELITE_28912, ClueTier.ELITE, null, List.of(new WorldPoint(1557, 3183, 0))),
		new Clues("Lletya south-east: Dig", ItemID.CLUE_SCROLL_ELITE_12089, ClueTier.ELITE, null, List.of(new WorldPoint(2357, 3151, 0))),
		new Clues("Meiyerditch Walls: Dig", ItemID.CLUE_SCROLL_ELITE_12091, ClueTier.ELITE, null, List.of(new WorldPoint(3587, 3180, 0))),
		new Clues("Hardwood Grove: Dig", ItemID.CLUE_SCROLL_ELITE_12110, ClueTier.ELITE, null, List.of(new WorldPoint(2820, 3078, 0))),
		new Clues("Mos Le'Harmless island: Dig", ItemID.CLUE_SCROLL_ELITE_12086, ClueTier.ELITE, null, List.of(new WorldPoint(3811, 3060, 0))),
		new Clues("Iorwerth Camp north: Dig", ItemID.CLUE_SCROLL_ELITE_12111, ClueTier.ELITE, null, List.of(new WorldPoint(2180, 3282, 0))),
		new Clues("Shilo Village: Dig", ItemID.CLUE_SCROLL_ELITE_12107, ClueTier.ELITE, null, List.of(new WorldPoint(2870, 2997, 0))),
		new Clues("Prifddinas: Dig", ItemID.CLUE_SCROLL_ELITE_23770, ClueTier.ELITE, null, List.of(new WorldPoint(3246, 6083, 0))),
		new Clues("Pollnivneach cliff: Dig", ItemID.CLUE_SCROLL_ELITE_12100, ClueTier.ELITE, null, List.of(new WorldPoint(3302, 2988, 0))),
		new Clues("Gu'Tanoth south: Dig", ItemID.CLUE_SCROLL_ELITE_12098, ClueTier.ELITE, null, List.of(new WorldPoint(2511, 2980, 0))),
		new Clues("Isle of Souls north-east: Dig", ItemID.CLUE_SCROLL_ELITE_25499, ClueTier.ELITE, null, List.of(new WorldPoint(2318, 2954, 0))),
		new Clues("Legends' Guild: Dig", ItemID.CLUE_SCROLL_ELITE_12102, ClueTier.ELITE, null, List.of(new WorldPoint(2732, 3372, 0))),
		new Clues("Dessous's tomb: Dig", ItemID.CLUE_SCROLL_ELITE_12103, ClueTier.ELITE, null, List.of(new WorldPoint(3573, 3425, 0))),
		new Clues("Isle of Souls west: Dig", ItemID.CLUE_SCROLL_ELITE_25498, ClueTier.ELITE, null, List.of(new WorldPoint(2094, 2889, 0))),
		new Clues("Harmony Island: Dig", ItemID.CLUE_SCROLL_ELITE_12088, ClueTier.ELITE, null, List.of(new WorldPoint(3828, 2848, 0))),
		new Clues("Ancient Pyramid: Dig", ItemID.CLUE_SCROLL_ELITE_12099, ClueTier.ELITE, null, List.of(new WorldPoint(3225, 2838, 0))),
		new Clues("Ruins of Morra: Dig", ItemID.CLUE_SCROLL_ELITE_25787, ClueTier.ELITE, null, List.of(new WorldPoint(1451, 3509, 0))),
		new Clues("Hosidius ruins: Dig", ItemID.CLUE_SCROLL_ELITE_19813, ClueTier.ELITE, null, List.of(new WorldPoint(1773, 3510, 0))),
		new Clues("Dragontooth Island: Dig", ItemID.CLUE_SCROLL_ELITE_12085, ClueTier.ELITE, null, List.of(new WorldPoint(3822, 3562, 0))),
		new Clues("ALQ north: Dig", ItemID.CLUE_SCROLL_ELITE_12108, ClueTier.ELITE, null, List.of(new WorldPoint(3603, 3564, 0))),
		new Clues("Crash Island: Dig", ItemID.CLUE_SCROLL_ELITE_12106, ClueTier.ELITE, null, List.of(new WorldPoint(2936, 2721, 0))),
		new Clues("Jaltevas: Dig", ItemID.CLUE_SCROLL_ELITE_26944, ClueTier.ELITE, null, List.of(new WorldPoint(3318, 2706, 0))),
		new Clues("Ape Atoll: Dig", ItemID.CLUE_SCROLL_ELITE_12096, ClueTier.ELITE, null, List.of(new WorldPoint(2697, 2705, 0))),
		new Clues("Mountain Camp: Dig", ItemID.CLUE_SCROLL_ELITE_12104, ClueTier.ELITE, null, List.of(new WorldPoint(2778, 3678, 0))),
		new Clues("Obelisk 28 east: Dig", ItemID.CLUE_SCROLL_ELITE_23146, ClueTier.ELITE, null, List.of(new WorldPoint(3051, 3736, 0))),
		new Clues("Ice Path: Dig", ItemID.CLUE_SCROLL_ELITE_12090, ClueTier.ELITE, null, List.of(new WorldPoint(2827, 3740, 0))),
		new Clues("Neitiznot: Dig", ItemID.CLUE_SCROLL_ELITE_12094, ClueTier.ELITE, null, List.of(new WorldPoint(2359, 3799, 0))),
		new Clues("Pirates' Cove: Dig", ItemID.CLUE_SCROLL_ELITE_12105, ClueTier.ELITE, null, List.of(new WorldPoint(2194, 3807, 0))),
		new Clues("DKS north-west: Dig", ItemID.CLUE_SCROLL_ELITE_12087, ClueTier.ELITE, null, List.of(new WorldPoint(2700, 3808, 0))),
		new Clues("Neitiznot west: Dig", ItemID.CLUE_SCROLL_ELITE_23148, ClueTier.ELITE, null, List.of(new WorldPoint(2316, 3814, 0))),
		new Clues("Lava Dragon Isle: Dig", ItemID.CLUE_SCROLL_ELITE_12109, ClueTier.ELITE, null, List.of(new WorldPoint(3215, 3835, 0))),
		new Clues("Fountain of Rune: Dig", ItemID.CLUE_SCROLL_ELITE_12101, ClueTier.ELITE, null, List.of(new WorldPoint(3369, 3894, 0))),
		new Clues("Lunar Isle west: Dig", ItemID.CLUE_SCROLL_ELITE_12092, ClueTier.ELITE, null, List.of(new WorldPoint(2065, 3923, 0))),
		new Clues("Resource Area: Dig", ItemID.CLUE_SCROLL_ELITE_12095, ClueTier.ELITE, null, List.of(new WorldPoint(3188, 3933, 0))),
		new Clues("Weiss: Dig", ItemID.CLUE_SCROLL_ELITE_23147, ClueTier.ELITE, null, List.of(new WorldPoint(2872, 3937, 0))),
		new Clues("Pirates' Hideout: Dig", ItemID.CLUE_SCROLL_ELITE_12097, ClueTier.ELITE, null, List.of(new WorldPoint(3043, 3940, 0))),
		new Clues("Wilderness Volcano: Dig", ItemID.CLUE_SCROLL_ELITE_12093, ClueTier.ELITE, null, List.of(new WorldPoint(3380, 3963, 0))),
		new Clues("Lithkren: Dig", ItemID.CLUE_SCROLL_ELITE_22000, ClueTier.ELITE, null, List.of(new WorldPoint(3560, 3987, 0))),
		new Clues("Island of Stone: Dig", ItemID.CLUE_SCROLL_ELITE_24253, ClueTier.ELITE, null, List.of(new WorldPoint(2484, 4016, 0))),
		new Clues("Taverley stones: Search", ItemID.CLUE_SCROLL_ELITE_12156, ClueTier.ELITE, null, List.of(new WorldPoint(2922, 3484, 0))),
		new Clues("Hellhound: Kill", ItemID.CLUE_SCROLL_ELITE_19797, ClueTier.ELITE, null, List.of(
				new WorldPoint(3172, 3952, 0), // Wilderness
				new WorldPoint(2739, 9688, 0), // Witchaven dungeon
				new WorldPoint(2892, 5304, 3), // God Wars Dungeon
				new WorldPoint(2409, 9786, 0), // Stronghold
				new WorldPoint(2851, 9849, 0), // Taverley dungeon
				new WorldPoint(3439, 10082, 0), // Wildy slayer dungeon
				new WorldPoint(1639, 10060, 0), // Kourend Catacombs
				new WorldPoint(1200, 10263, 1) // Karuulm Slayer Dungeon
			)
		),
		new Clues("Ankou: Kill", ItemID.CLUE_SCROLL_ELITE_19805, ClueTier.ELITE, null, List.of(
				new WorldPoint(2960, 3744, 0),
				new WorldPoint(1963, 4951, 0),
				new WorldPoint(2003, 4953, 0),
				new WorldPoint(2468, 9802, 0),
				new WorldPoint(3348, 10075, 0),
				new WorldPoint(1637, 9991, 0)
			)
		),
		new Clues("Crocodile: Kill", ItemID.CLUE_SCROLL_ELITE_19804, ClueTier.ELITE, null, List.of(
				new WorldPoint(3337, 2922, 0),
				new WorldPoint(3296, 2912, 0),
				new WorldPoint(3190, 2825, 0),
				new WorldPoint(3412, 2774, 0)
			)
		),
		new Clues("Waterfiend: Kill", ItemID.CLUE_SCROLL_ELITE_19798, ClueTier.ELITE, null, List.of(
				new WorldPoint(1738, 5343, 0),
				new WorldPoint(1610, 5343, 0),
				new WorldPoint(2248, 9994, 0),
				new WorldPoint(3161, 12457, 0)
			)),
		new Clues("Green dragon: Kill", ItemID.CLUE_SCROLL_ELITE_19799, ClueTier.ELITE, null, List.of(
				new WorldPoint(3331, 3672, 0),
				new WorldPoint(2973, 3620, 0),
				new WorldPoint(3137, 3707, 0),
				new WorldPoint(3078, 3810, 0),
				new WorldPoint(3410, 10065, 0),
				new WorldPoint(1939, 8991, 0),
				// Brutal ones
				new WorldPoint(1628, 5334, 0),
				new WorldPoint(1756, 5334, 0)
			)
		),
		new Clues("Basilisk: Kill", ItemID.CLUE_SCROLL_ELITE_19800, ClueTier.ELITE, null, List.of(
				new WorldPoint(2738, 10008, 0),
				new WorldPoint(2459, 10398, 0)
			)),
		new Clues("Lost barbarian: Kill", ItemID.CLUE_SCROLL_ELITE_19806, ClueTier.ELITE, null, List.of(
			new WorldPoint(2506, 3518, 0)
		)),
		new Clues("Rock Crab: Kill", ItemID.CLUE_SCROLL_ELITE_19796, ClueTier.ELITE, null, List.of(
			new WorldPoint(2694, 3724, 0),
			new WorldPoint(2529, 3740, 0),
			new WorldPoint(1197, 3587, 0),
			new WorldPoint(2442, 10159, 0),
			new WorldPoint(2748, 10166, 0)
		)),
		new Clues("Aviansie: Kill", ItemID.CLUE_SCROLL_ELITE_19803, ClueTier.ELITE, null, List.of(
			new WorldPoint(2833, 5289, 0),
			new WorldPoint(2938, 10115, 0)
		)),
		new Clues("Bloodveld: Kill", ItemID.CLUE_SCROLL_ELITE_19801, ClueTier.ELITE, null, List.of(
			new WorldPoint(2880, 5321, 0),
			new WorldPoint(3564, 9741, 0),
			new WorldPoint(3409, 3571, 0),
			new WorldPoint(3403, 9934, 0),
			new WorldPoint(2434, 9817, 0),
			new WorldPoint(2950, 10085, 0)
		)),
		new Clues("Aberrant spectre: Kill", ItemID.CLUE_SCROLL_ELITE_19802, ClueTier.ELITE, null, List.of(
			new WorldPoint(3413, 3550, 0),
			new WorldPoint(2443, 9785, 0),
			new WorldPoint(1646, 9988, 0)
		)),
		new Clues("Father Aereck: Talk", ItemID.CLUE_SCROLL_ELITE_12151, ClueTier.ELITE, null, List.of(new WorldPoint(3242, 3207, 0))),
		new Clues("Dagannoth Kings: Dig", ItemID.CLUE_SCROLL_ELITE_19809, ClueTier.ELITE, null, List.of(new WorldPoint(1910, 4367, 0))),
		new Clues("Regath: Talk", ItemID.CLUE_SCROLL_ELITE_19793, ClueTier.ELITE, null, List.of(new WorldPoint(1719, 3723, 0))),
		new Clues("Soul Altar: Dig", ItemID.CLUE_SCROLL_ELITE_19784, ClueTier.ELITE, null, List.of(new WorldPoint(1815, 3852, 0))),
		new Clues("Lava Maze: Emote", ItemID.CLUE_SCROLL_ELITE_12075, ClueTier.ELITE, null, List.of(new WorldPoint(3069, 3861, 0))),
		new Clues("Legends' Guild: Emote", ItemID.CLUE_SCROLL_ELITE_19789, ClueTier.ELITE, null, List.of(new WorldPoint(2728, 3377, 0))),
		new Clues("Edgeville Monastery: Emote", ItemID.CLUE_SCROLL_ELITE_12078, ClueTier.ELITE, null, List.of(new WorldPoint(3056, 3484, 0))),
		new Clues("Fortis temple: Emote", ItemID.CLUE_SCROLL_ELITE_28910, ClueTier.ELITE, null, List.of(new WorldPoint(1699, 3087, 0))),
		new Clues("Oneiromancer: Talk", ItemID.CLUE_SCROLL_ELITE_12132, ClueTier.ELITE, null, List.of(new WorldPoint(2150, 3866, 0))),
		new Clues("Old crone: Talk", ItemID.CLUE_SCROLL_ELITE_12138, ClueTier.ELITE, null, List.of(new WorldPoint(3462, 3557, 0))),
		new Clues("Shadow dungeon: Emote", ItemID.CLUE_SCROLL_ELITE_12076, ClueTier.ELITE, null, List.of(new WorldPoint(2629, 5071, 0))),
		new Clues("Jardric: Talk", ItemID.CLUE_SCROLL_ELITE_21524, ClueTier.ELITE, null, List.of(new WorldPoint(3661, 3849, 0))),
		new Clues("Mandrith: Talk", ItemID.CLUE_SCROLL_ELITE_12134, ClueTier.ELITE, null, List.of(new WorldPoint(3182, 3946, 0))),
		new Clues("Fishing Platform: Emote", ItemID.CLUE_SCROLL_ELITE_12079, ClueTier.ELITE, null, List.of(new WorldPoint(2782, 3273, 0))),
		new Clues("Kalphite Cave: Dig", ItemID.CLUE_SCROLL_ELITE_12158, ClueTier.ELITE, null, List.of(new WorldPoint(3307, 9505, 0))),
		new Clues("Toad batta: Dig", ItemID.CLUE_SCROLL_ELITE_19810, ClueTier.ELITE, null, List.of(new WorldPoint(3139, 4554, 0))),
		new Clues("Law rift: Dig", ItemID.CLUE_SCROLL_ELITE_12150, ClueTier.ELITE, null, List.of(new WorldPoint(3049, 4839, 0))),
		new Clues("Horacio: Talk", ItemID.CLUE_SCROLL_ELITE_12154, ClueTier.ELITE, null, List.of(new WorldPoint(2635, 3310, 0))),
		new Clues("Volcanic Mine: Dig", ItemID.CLUE_SCROLL_ELITE_21525, ClueTier.ELITE, null, List.of(new WorldPoint(3816, 3810, 0))),
		new Clues("Burgh de Rott: Dig", ItemID.CLUE_SCROLL_ELITE_19785, ClueTier.ELITE, null, List.of(new WorldPoint(3538, 3208, 0))),
		new Clues("Seers Agility: Search", ItemID.CLUE_SCROLL_ELITE_12145, ClueTier.ELITE, null, List.of(new WorldPoint(2707, 3488, 0))),
		new Clues("Trollweiss: Dig", ItemID.CLUE_SCROLL_ELITE_12141, ClueTier.ELITE, null, List.of(new WorldPoint(2780, 3783, 0))),
		new Clues("Mawrth: Talk", ItemID.CLUE_SCROLL_ELITE_12140, ClueTier.ELITE, null, List.of(new WorldPoint(2333, 3165, 0))),
		new Clues("Slayer Tower: Emote", ItemID.CLUE_SCROLL_ELITE_12080, ClueTier.ELITE, null, List.of(new WorldPoint(3421, 3537, 0))),
		new Clues("Fight Arena pub: Emote", ItemID.CLUE_SCROLL_ELITE_19791, ClueTier.ELITE, null, List.of(new WorldPoint(2568, 3149, 0))),
		new Clues("Barker: Talk", ItemID.CLUE_SCROLL_ELITE_12155, ClueTier.ELITE, null, List.of(new WorldPoint(3499, 3503, 0))),
		new Clues("Kamfreena: Talk", ItemID.CLUE_SCROLL_ELITE_12144, ClueTier.ELITE, null, List.of(new WorldPoint(2845, 3539, 0))),
		new Clues("Candle maker: Talk", ItemID.CLUE_SCROLL_ELITE_12152, ClueTier.ELITE, null, List.of(new WorldPoint(2799, 3438, 0))),
		new Clues("Genie: Talk", ItemID.CLUE_SCROLL_ELITE_12153, ClueTier.ELITE, null, List.of(new WorldPoint(3371, 9320, 0))),
		new Clues("Mogre Camp: Dig", ItemID.CLUE_SCROLL_ELITE_19782, ClueTier.ELITE, null, List.of(new WorldPoint(2953, 9523, 0))),
		new Clues("Neitiznot runite: Emote", ItemID.CLUE_SCROLL_ELITE_12074, ClueTier.ELITE, null, List.of(new WorldPoint(2375, 3850, 0))),
		new Clues("Ancient Cavern: Emote", ItemID.CLUE_SCROLL_ELITE_12083, ClueTier.ELITE, null, List.of(new WorldPoint(1768, 5366, 0))),
		new Clues("Guard Vemmeldo: Talk", ItemID.CLUE_SCROLL_ELITE_19792, ClueTier.ELITE, null, List.of(new WorldPoint(2447, 3418, 0))),
		new Clues("Fountain of Heroes: Emote", ItemID.CLUE_SCROLL_ELITE_12082, ClueTier.ELITE, null, List.of(new WorldPoint(2920, 9893, 0))),
		new Clues("Ardougne gem stall: Emote", ItemID.CLUE_SCROLL_ELITE_19790, ClueTier.ELITE, null, List.of(new WorldPoint(2666, 3304, 0))),
		new Clues("Cam the Camel: Talk", ItemID.CLUE_SCROLL_ELITE_12136, ClueTier.ELITE, null, List.of(new WorldPoint(3300, 3231, 0))),
		new Clues("Ambassador Alvijar: Talk", ItemID.CLUE_SCROLL_ELITE_12133, ClueTier.ELITE, null, List.of(new WorldPoint(2736, 5351, 0))),
		new Clues("Resource Area gold: Dig", ItemID.CLUE_SCROLL_ELITE_23144, ClueTier.ELITE, null, List.of(new WorldPoint(3183, 3941, 0))),
		new Clues("Oronwen: Talk", ItemID.CLUE_SCROLL_ELITE_12135, ClueTier.ELITE, null, List.of(new WorldPoint(2326, 3177, 0))),
		new Clues("Nurse Wooned: Talk", ItemID.CLUE_SCROLL_ELITE_19794, ClueTier.ELITE, null, List.of(new WorldPoint(1511, 3619, 0))),
		new Clues("DLR: Dig", ItemID.CLUE_SCROLL_ELITE_23145, ClueTier.ELITE, null, List.of(new WorldPoint(2221, 3091, 0))),
		new Clues("Trollweiss Mountain: Emote", ItemID.CLUE_SCROLL_ELITE_19787, ClueTier.ELITE, null, List.of(new WorldPoint(2776, 3781, 0))),
		new Clues("BIP: Dig", ItemID.CLUE_SCROLL_ELITE_12146, ClueTier.ELITE, null, List.of(new WorldPoint(3410, 3324, 0))),
		new Clues("Lisse Isaakson: Talk", ItemID.CLUE_SCROLL_ELITE_19795, ClueTier.ELITE, null, List.of(new WorldPoint(2351, 3801, 0))),
		new Clues("Charcoal Burners: Emote", ItemID.CLUE_SCROLL_ELITE_25786, ClueTier.ELITE, null, List.of(new WorldPoint(1714, 3467, 0))),
		new Clues("Warriors' guild bank: Emote", ItemID.CLUE_SCROLL_ELITE_12077, ClueTier.ELITE, null, List.of(new WorldPoint(2844, 3542, 0))),
		new Clues("KBD: Kill", ItemID.CLUE_SCROLL_ELITE_19807, ClueTier.ELITE, null, List.of(new WorldPoint(2288, 4702, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12127, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3415, 0))),
		new Clues("Shayzien war tent: Emote", ItemID.CLUE_SCROLL_ELITE_19788, ClueTier.ELITE, null, List.of(new WorldPoint(1487, 3635, 0))),
		new Clues("Tree Gnome Village: Dig", ItemID.CLUE_SCROLL_ELITE_12130, ClueTier.ELITE, null, List.of(new WorldPoint(2449, 3130, 0))),
		new Clues("Wyson: Talk", ItemID.CLUE_SCROLL_ELITE_12159, ClueTier.ELITE, null, List.of(new WorldPoint(3026, 3378, 0))),
		new Clues("Piscatoris Fishing Colony: Dig", ItemID.CLUE_SCROLL_ELITE_12143, ClueTier.ELITE, null, List.of(new WorldPoint(2342, 3677, 0))),
		new Clues("Ape Atoll: Search", ItemID.CLUE_SCROLL_ELITE_19786, ClueTier.ELITE, null, List.of(new WorldPoint(2703, 2716, 0))),
		new Clues("Waterbirth Island Dungeon: Dig", ItemID.CLUE_SCROLL_ELITE_12142, ClueTier.ELITE, null, List.of(new WorldPoint(2523, 3739, 0))),
		new Clues("Sigli: Talk", ItemID.CLUE_SCROLL_ELITE_12137, ClueTier.ELITE, null, List.of(new WorldPoint(2660, 3654, 0))),
		new Clues("Aris: Talk", ItemID.CLUE_SCROLL_ELITE_12149, ClueTier.ELITE, null, List.of(new WorldPoint(3203, 3424, 0))),
		new Clues("Warriors' Guild: Dig", ItemID.CLUE_SCROLL_ELITE_12148, ClueTier.ELITE, null, List.of(new WorldPoint(2867, 3546, 0))),
		new Clues("Daga: Talk", ItemID.CLUE_SCROLL_ELITE_19808, ClueTier.ELITE, null, List.of(new WorldPoint(2759, 2775, 0))),
		new Clues("Funbo: Talk", ItemID.CLUE_SCROLL_ELITE_28911, ClueTier.ELITE, null, List.of(new WorldPoint(1432, 9584, 0))),
		new Clues("Trollheim: Emote", ItemID.CLUE_SCROLL_ELITE_12081, ClueTier.ELITE, null, List.of(new WorldPoint(2887, 3676, 0))),
		new Clues("Veteran Squire: Talk", ItemID.CLUE_SCROLL_ELITE_19811, ClueTier.ELITE, null, List.of(new WorldPoint(2638, 2656, 0))),
		new Clues("Vannaka: Talk", ItemID.CLUE_SCROLL_ELITE_12147, ClueTier.ELITE, null, List.of(new WorldPoint(3148, 9913, 0))),
		new Clues("Zul-Andra: Dig", ItemID.CLUE_SCROLL_ELITE_19783, ClueTier.ELITE, null, List.of(new WorldPoint(2202, 3062, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12113, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12114, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12115, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12116, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12117, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12118, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12119, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12120, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12121, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12122, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12123, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12124, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12125, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Sherlock: Talk", ItemID.CLUE_SCROLL_ELITE_12126, ClueTier.ELITE, null, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues("Song: Lament for the Hallowed", ItemID.CLUE_SCROLL_ELITE_24773, ClueTier.ELITE, null, List.of(new WorldPoint(2990, 3383, 0))),
		new Clues("Song: The Pharaoh", ItemID.CLUE_SCROLL_ELITE_26943, ClueTier.ELITE, null, List.of(new WorldPoint(2990, 3384, 0))),
		new Clues("Proudspire summit: Dig", ItemID.CLUE_SCROLL_ELITE_29855, ClueTier.ELITE, null, List.of(new WorldPoint(1571, 3245, 0))),
		new Clues("Frost Nagua: Kill", ItemID.CLUE_SCROLL_ELITE_29856, ClueTier.ELITE, null, List.of(new WorldPoint(1627, 9623, 0))),
		new Clues("Earthen Nagua: Kill", ItemID.CLUE_SCROLL_ELITE_30932, ClueTier.ELITE, null, List.of(new WorldPoint(1305, 3033, 0))),
		new Clues("Challenge: Sherlock", ItemID.CHALLENGE_SCROLL_ELITE, ClueTier.ELITE_CHALLENGE, null, List.of(new WorldPoint(2733, 3415, 0))),
		new Clues("Challenge", ItemID.CHALLENGE_SCROLL_ELITE_12139, ClueTier.ELITE_CHALLENGE, null, List.of(new WorldPoint(0, 0, 0))),
		new Clues(500, "Snowflake: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_0, List.of(new WorldPoint(2872, 3935, 0))),
		new Clues(501, "Captain Bruce: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_1, List.of(new WorldPoint(1530, 3567, 0))),
		new Clues(502, "Sacrifice: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_2, List.of(new WorldPoint(2210, 3056, 0))),
		new Clues(503, "Edward: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_3, List.of(new WorldPoint(3283, 3934, 0))),
		new Clues(504, "Mandrith: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_4, List.of(new WorldPoint(3184, 3945, 0))),
		new Clues(505, "Dugopul: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_5, List.of(new WorldPoint(2801, 2745, 0))),
		new Clues(506, "Runolf: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_6, List.of(new WorldPoint(2508, 10258, 0))),
		new Clues(507, "Immenizz: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_7, List.of(new WorldPoint(2592, 4319, 0))),
		new Clues(508, "Luminata: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_8, List.of(new WorldPoint(3505, 3236, 0))),
		new Clues(509, "Old Man Ral: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_9, List.of(new WorldPoint(3607, 3208, 0))),
		new Clues(510, "Radimus Erkle: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_10, List.of(new WorldPoint(2726, 3368, 0))),
		new Clues(511, "Primula: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_11, List.of(new WorldPoint(2454, 2853, 1))),
		new Clues(512, "Goreu: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_12, List.of(new WorldPoint(2336, 3161, 0))),
		new Clues(513, "Guildmaster Lars: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_13, List.of(new WorldPoint(1652, 3499, 0))),
		new Clues(514, "Wingstone: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_14, List.of(new WorldPoint(3381, 2891, 0))),
		new Clues(515, "New Recruit Tony: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_ANAGRAM_15, List.of(new WorldPoint(1502, 3554, 0))),
		new Clues(516, "Iorwerth Camp south: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_0, List.of(new WorldPoint(2178, 3209, 0))),
		new Clues(517, "Port Tyras south: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_1, List.of(new WorldPoint(2155, 3100, 0))),
		new Clues(518, "DLR: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_2, List.of(new WorldPoint(2217, 3092, 0))),
		new Clues(519, "Mos Le'Harmless island: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_3, List.of(new WorldPoint(3830, 3060, 0))),
		new Clues(520, "Crandor: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_4, List.of(new WorldPoint(2834, 3271, 0))),
		new Clues(521, "Witchaven: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_5, List.of(new WorldPoint(2732, 3284, 0))),
		new Clues(522, "Meiyerditch Mine: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_6, List.of(new WorldPoint(3622, 3320, 0))),
		new Clues(523, "Prifddinas east: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_7, List.of(new WorldPoint(2303, 3328, 0))),
		new Clues(524, "Morytania Graveyard: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_8, List.of(new WorldPoint(3570, 3405, 0))),
		new Clues(525, "Crabclaw Isle: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_9, List.of(new WorldPoint(1769, 3418, 0))),
		new Clues(526, "Water Obelisk: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_10, List.of(new WorldPoint(2840, 3423, 0))),
		new Clues(527, "ALQ north: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_11, List.of(new WorldPoint(3604, 3564, 0))),
		new Clues(528, "Air Obelisk: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_12, List.of(new WorldPoint(3085, 3569, 0))),
		new Clues(529, "Crash Island: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_13, List.of(new WorldPoint(2934, 2727, 0))),
		new Clues(530, "Lizardman Shaman: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_14, List.of(new WorldPoint(1451, 3695, 0))),
		new Clues(531, "Waterbirth Island: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_15, List.of(new WorldPoint(2538, 3739, 0))),
		new Clues(532, "Farming Guild: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_16, List.of(new WorldPoint(1248, 3751, 0))),
		new Clues(533, "Arceuus church crypt: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_17, List.of(new WorldPoint(1698, 3792, 0))),
		new Clues(534, "Chaos Temple 38: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_18, List.of(new WorldPoint(2951, 3820, 0))),
		new Clues(535, "Pirates' Cove: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_19, List.of(new WorldPoint(2202, 3825, 0))),
		new Clues(536, "Dense essence mine: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_20, List.of(new WorldPoint(1761, 3853, 0))),
		new Clues(537, "Astral Altar west: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_21, List.of(new WorldPoint(2090, 3863, 0))),
		new Clues(538, "Sulphur mine: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_22, List.of(new WorldPoint(1442, 3878, 0))),
		new Clues(539, "Wilderness Volcano: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_23, List.of(new WorldPoint(3380, 3929, 0))),
		new Clues(540, "Resource Area: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_24, List.of(new WorldPoint(3188, 3939, 0))),
		new Clues(541, "Rogues' Castle: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_25, List.of(new WorldPoint(3304, 3941, 0))),
		new Clues(542, "Wilderness Agility: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_COORDINATE_26, List.of(new WorldPoint(3028, 3928, 0))),
		new Clues(543, "Ping and Pong: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_0, List.of(new WorldPoint(2670, 10395, 0))),
		new Clues(544, "Wizard Cromperty: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_1, List.of(new WorldPoint(2684, 3325, 0))),
		new Clues(545, "Thorgel: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_2, List.of(new WorldPoint(1861, 4641, 0))),
		new Clues(546, "GWD entrance: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_3, List.of(new WorldPoint(2918, 3745, 0))),
		new Clues(547, "Abbot Langley: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_4, List.of(new WorldPoint(3052, 3490, 0))),
		new Clues(548, "Monk of Entrana: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_5, List.of(new WorldPoint(3052, 3237, 0))),
		new Clues(549, "Jorral: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_6, List.of(new WorldPoint(2436, 3346, 0))),
		new Clues(550, "Viggora: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_7, List.of(new WorldPoint(3119, 9996, 0), new WorldPoint(3294, 3934 , 0), new WorldPoint(3448, 3550, 0))),
		new Clues(551, "Biblia: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_8, List.of(new WorldPoint(1633, 3823, 0))),
		new Clues(552, "Kamil: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_9, List.of(new WorldPoint(2873, 3757, 0))),
		new Clues(553, "Deep Wilderness Dungeon: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_10, List.of(new WorldPoint(3045, 3925, 0))),
		new Clues(554, "Falo the Bard: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_11, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(555, "Prifddinas Onion: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_12, List.of(new WorldPoint(2299, 3328, 0))),
		new Clues(556, "Rogues' Den: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_13, List.of(new WorldPoint(2906, 3537, 0))),
		new Clues(557, "Ghommal: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_14, List.of(new WorldPoint(2879, 3547, 0))),
		new Clues(558, "Lava Maze Dungeon: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_15, List.of(new WorldPoint(3069, 3860, 0))),
		new Clues(559, "Guthix Lake: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_16, List.of(new WorldPoint(3069, 3932, 0))),
		new Clues(560, "Juna: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_17, List.of(new WorldPoint(3252, 9517, 0))),
		new Clues(561, "Sir Vyvin: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_18, List.of(new WorldPoint(2984, 3339, 2))),
		new Clues(562, "Viyeldi caves: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_19, List.of(new WorldPoint(2782, 2935, 0))),
		new Clues(563, "Saradomin's Encampment: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_20, List.of(new WorldPoint(2918, 3745, 0))),
		new Clues(564, "Key Master: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_21, List.of(new WorldPoint(1310, 1251, 0))),
		new Clues(565, "Piles: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_22, List.of(new WorldPoint(3185, 3934, 0))),
		new Clues(566, "Demonic Ruins: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_23, List.of(new WorldPoint(3294, 3889, 0))),
		new Clues(567, "Robin: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_24, List.of(new WorldPoint(3676, 3494, 0))),
		new Clues(568, "Lovada: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_25, List.of(new WorldPoint(1487, 3833, 0))),
		new Clues(569, "Logosia: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_26, List.of(new WorldPoint(1633, 3808, 0))),
		new Clues(570, "Sherlock: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_27, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(571, "Shilo furnace: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_28, List.of(new WorldPoint(2859, 2962, 0))),
		new Clues(572, "Shadow Dungeon: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_29, List.of(new WorldPoint(2547, 3421, 0))),
		new Clues(573, "Ewesey: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_30, List.of(new WorldPoint(1647, 3627, 0))),
		new Clues(574, "terrorbird display: Dig", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_31, List.of(new WorldPoint(3260, 3449, 0))),
		new Clues(575, "Mage of Zamorak: Talk", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_CRYPTIC_32, List.of(new WorldPoint(3259, 3386, 0))),
		new Clues(576, "Gwenith: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_0, List.of(new WorldPoint(2213, 3427, 0))),
		new Clues(577, "K'ril Tsutsaroth: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_1, List.of(new WorldPoint(2931, 5337, 0))),
		new Clues(578, "Warrior's guild bank: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_2, List.of(new WorldPoint(2843, 3540, 0))),
		new Clues(579, "Iorwerth Camp: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_3, List.of(new WorldPoint(2199, 3254, 0))),
		new Clues(580, "Entrana church: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_4, List.of(new WorldPoint(2851, 3354, 0))),
		new Clues(581, "Magic axe hut: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_5, List.of(new WorldPoint(3188, 3957, 0))),
		new Clues(582, "Tzhaar gem store: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_6, List.of(new WorldPoint(2466, 5150, 0))),
		new Clues(583, "Iban's temple: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_7, List.of(new WorldPoint(2006, 4709, 1))),
		new Clues(584, "KBD Lair: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_8, List.of(new WorldPoint(2286, 4680, 0))),
		new Clues(585, "Barrows chest: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_9, List.of(new WorldPoint(3548, 9691, 0))),
		new Clues(586, "Death Altar: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_10, List.of(new WorldPoint(2210, 4842, 0))),
		new Clues(587, "Cam Torum: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_11, List.of(new WorldPoint(1428, 3119, 0))),
		new Clues(588, "Goblin Village: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_12, List.of(new WorldPoint(2959, 3502, 0))),
		new Clues(589, "Zul-Andra: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_13, List.of(new WorldPoint(2204, 3059, 0))),
		new Clues(590, "Lava Dragon Isle: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_14, List.of(new WorldPoint(3229, 3832, 0))),
		new Clues(591, "Wise old man: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_15, List.of(new WorldPoint(3095, 3255, 0))),
		new Clues(592, "Ellamaria's garden: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_16, List.of(new WorldPoint(3232, 3493, 0))),
		new Clues(593, "Catacombs: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_17, List.of(new WorldPoint(1662, 10044, 0))),
		new Clues(594, "Soul Altar: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_18, List.of(new WorldPoint(1811, 3853, 0))),
		new Clues(595, "Enchanted valley: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_19, List.of(new WorldPoint(3022, 4517, 0))),
		new Clues(596, "Watchtower: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_20, List.of(new WorldPoint(2548, 3112, 0))),
		new Clues(597, "Drakan: Emote Castl", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_21, List.of(new WorldPoint(3563, 3379, 0))),
		new Clues(598, "Pyramid Plunder: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_22, List.of(new WorldPoint(1951, 4431, 0))),
		new Clues(599, "Salvager Overlook: Emote", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_EMOTE_23, List.of(new WorldPoint(1614, 3296, 0))),
		new Clues(600, "Dragon scimitar: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_0, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(601, "God book: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_1, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(602, "Crystal bow: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_2, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(603, "Infernal axe: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_3, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(604, "Mark of grace: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_4, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(605, "Lava dragon bones: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_5, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(606, "Armadyl helmet: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_6, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(607, "Dragon defender: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_7, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(608, "Warrior guild token: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_8, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(609, "Greenman's ale(m): Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_9, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(610, "Barrelchest anchor: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_10, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(611, "Basalt: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_11, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(612, "Tzhaar-ket-om: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_12, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(613, "Fighter torso: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_13, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(614, "Barrows gloves: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_14, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(615, "Cooking gauntlets: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_15, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(616, "Numulite: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_16, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(617, "Rune platebody: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_17, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(618, "Ivandis flail: Falo", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_FALO_18, List.of(new WorldPoint(2689, 3549, 0))),
		new Clues(619, "anglerfish: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_0, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(620, "blood rune: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_1, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(621, "burn magic: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_2, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(622, "burn redwood: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_3, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(623, "tecu: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_4, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(624, "chop redwood: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_5, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(625, "graceful: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_6, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(626, "light orb: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_7, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(627, "tablet: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_8, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(628, "amulet: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_9, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(629, "burn fiyr: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_10, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(630, "eel: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_11, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(631, "whip: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_12, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(632, "rune dart: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_13, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(633, "kill fiyr: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_14, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(634, "shaman: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_15, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(635, "reanimate: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_16, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(636, "mage: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_17, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(637, "runite ore: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_18, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(638, "ranging mix: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_19, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(639, "anti-venom: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_20, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(640, "elf: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_21, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(641, "nechryael: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_22, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(642, "rune med: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_23, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(643, "gem: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_24, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(644, "tree: Sherlock", ItemID.CLUE_SCROLL_MASTER, ClueTier.MASTER, BeginnerMasterClueText.MASTER_SHERLOCK_25, List.of(new WorldPoint(2733, 3413, 0))),
		new Clues(645, "Warriors' Guild: Dig", HotColdLocation.ASGARNIA_WARRIORS.ordinal(), ClueTier.MASTER, HotColdLocation.ASGARNIA_WARRIORS.getArea(), List.of(HotColdLocation.ASGARNIA_WARRIORS.getWorldPoint())),
		new Clues(646, "Jatix's Herblore: Dig", HotColdLocation.ASGARNIA_JATIX.ordinal(), ClueTier.MASTER, HotColdLocation.ASGARNIA_JATIX.getArea(), List.of(HotColdLocation.ASGARNIA_JATIX.getWorldPoint())),
		new Clues(647, "Barbarian Village: Dig", HotColdLocation.ASGARNIA_BARB.ordinal(), ClueTier.MASTER, HotColdLocation.ASGARNIA_BARB.getArea(), List.of(HotColdLocation.ASGARNIA_BARB.getWorldPoint())),
		new Clues(648, "Miazrqa's tower: Dig", HotColdLocation.ASGARNIA_MIAZRQA.ordinal(), ClueTier.MASTER, HotColdLocation.ASGARNIA_MIAZRQA.getArea(), List.of(HotColdLocation.ASGARNIA_MIAZRQA.getWorldPoint())),
		new Clues(649, "Sarah's farm: Dig", HotColdLocation.ASGARNIA_COW.ordinal(), ClueTier.MASTER, HotColdLocation.ASGARNIA_COW.getArea(), List.of(HotColdLocation.ASGARNIA_COW.getWorldPoint())),
		new Clues(650, "Party Room: Dig", HotColdLocation.ASGARNIA_PARTY_ROOM.ordinal(), ClueTier.MASTER, HotColdLocation.ASGARNIA_PARTY_ROOM.getArea(), List.of(HotColdLocation.ASGARNIA_PARTY_ROOM.getWorldPoint())),
		new Clues(651, "Crafting Guild: Dig", HotColdLocation.ASGARNIA_CRAFT_GUILD.ordinal(), ClueTier.MASTER, HotColdLocation.ASGARNIA_CRAFT_GUILD.getArea(), List.of(HotColdLocation.ASGARNIA_CRAFT_GUILD.getWorldPoint())),
		new Clues(652, "Rimmington mine: Dig", HotColdLocation.ASGARNIA_RIMMINGTON.ordinal(), ClueTier.MASTER, HotColdLocation.ASGARNIA_RIMMINGTON.getArea(), List.of(HotColdLocation.ASGARNIA_RIMMINGTON.getWorldPoint())),
		new Clues(653, "Mudskipper Point: Dig", HotColdLocation.ASGARNIA_MUDSKIPPER.ordinal(), ClueTier.MASTER, HotColdLocation.ASGARNIA_MUDSKIPPER.getArea(), List.of(HotColdLocation.ASGARNIA_MUDSKIPPER.getWorldPoint())),
		new Clues(654, "Troll arena: Dig", HotColdLocation.ASGARNIA_TROLL.ordinal(), ClueTier.MASTER, HotColdLocation.ASGARNIA_TROLL.getArea(), List.of(HotColdLocation.ASGARNIA_TROLL.getWorldPoint())),
		new Clues(655, "Nardah cave: Dig", HotColdLocation.DESERT_GENIE.ordinal(), ClueTier.MASTER, HotColdLocation.DESERT_GENIE.getArea(), List.of(HotColdLocation.DESERT_GENIE.getWorldPoint())),
		new Clues(656, "Al Kharid mine west: Dig", HotColdLocation.DESERT_ALKHARID_MINE.ordinal(), ClueTier.MASTER, HotColdLocation.DESERT_ALKHARID_MINE.getArea(), List.of(HotColdLocation.DESERT_ALKHARID_MINE.getWorldPoint())),
		new Clues(657, "Menaphos gate: Dig", HotColdLocation.DESERT_MENAPHOS_GATE.ordinal(), ClueTier.MASTER, HotColdLocation.DESERT_MENAPHOS_GATE.getArea(), List.of(HotColdLocation.DESERT_MENAPHOS_GATE.getWorldPoint())),
		new Clues(658, "Bedabin Camp: Dig", HotColdLocation.DESERT_BEDABIN_CAMP.ordinal(), ClueTier.MASTER, HotColdLocation.DESERT_BEDABIN_CAMP.getArea(), List.of(HotColdLocation.DESERT_BEDABIN_CAMP.getWorldPoint())),
		new Clues(659, "Uzer west: Dig", HotColdLocation.DESERT_UZER.ordinal(), ClueTier.MASTER, HotColdLocation.DESERT_UZER.getArea(), List.of(HotColdLocation.DESERT_UZER.getWorldPoint())),
		new Clues(660, "Pollnivneach west: Dig", HotColdLocation.DESERT_POLLNIVNEACH.ordinal(), ClueTier.MASTER, HotColdLocation.DESERT_POLLNIVNEACH.getArea(), List.of(HotColdLocation.DESERT_POLLNIVNEACH.getWorldPoint())),
		new Clues(661, "Mage Training Arena: Dig", HotColdLocation.DESERT_MTA.ordinal(), ClueTier.MASTER, HotColdLocation.DESERT_MTA.getArea(), List.of(HotColdLocation.DESERT_MTA.getWorldPoint())),
		new Clues(662, "Ruins of Ullek: Dig", HotColdLocation.DESERT_RUINS_OF_ULLEK.ordinal(), ClueTier.MASTER, HotColdLocation.DESERT_RUINS_OF_ULLEK.getArea(), List.of(HotColdLocation.DESERT_RUINS_OF_ULLEK.getWorldPoint())),
		new Clues(663, "Shantay Pass: Dig", HotColdLocation.DESERT_SHANTY.ordinal(), ClueTier.MASTER, HotColdLocation.DESERT_SHANTY.getArea(), List.of(HotColdLocation.DESERT_SHANTY.getWorldPoint())),
		new Clues(664, "Jiggig west: Dig", HotColdLocation.FELDIP_HILLS_JIGGIG.ordinal(), ClueTier.MASTER, HotColdLocation.FELDIP_HILLS_JIGGIG.getArea(), List.of(HotColdLocation.FELDIP_HILLS_JIGGIG.getWorldPoint())),
		new Clues(665, "Feldip Hills: Dig", HotColdLocation.FELDIP_HILLS_SW.ordinal(), ClueTier.MASTER, HotColdLocation.FELDIP_HILLS_SW.getArea(), List.of(HotColdLocation.FELDIP_HILLS_SW.getWorldPoint())),
		new Clues(666, "Lemantolly Undri: Dig", HotColdLocation.FELDIP_HILLS_GNOME_GLITER.ordinal(), ClueTier.MASTER, HotColdLocation.FELDIP_HILLS_GNOME_GLITER.getArea(), List.of(HotColdLocation.FELDIP_HILLS_GNOME_GLITER.getWorldPoint())),
		new Clues(667, "Rantz: Dig", HotColdLocation.FELDIP_HILLS_RANTZ.ordinal(), ClueTier.MASTER, HotColdLocation.FELDIP_HILLS_RANTZ.getArea(), List.of(HotColdLocation.FELDIP_HILLS_RANTZ.getWorldPoint())),
		new Clues(668, "Jiggig south: Dig", HotColdLocation.FELDIP_HILLS_SOUTH.ordinal(), ClueTier.MASTER, HotColdLocation.FELDIP_HILLS_SOUTH.getArea(), List.of(HotColdLocation.FELDIP_HILLS_SOUTH.getWorldPoint())),
		new Clues(669, "red chinchompas: Dig", HotColdLocation.FELDIP_HILLS_RED_CHIN.ordinal(), ClueTier.MASTER, HotColdLocation.FELDIP_HILLS_RED_CHIN.getArea(), List.of(HotColdLocation.FELDIP_HILLS_RED_CHIN.getWorldPoint())),
		new Clues(670, "∩-shaped lake: Dig", HotColdLocation.FELDIP_HILLS_SE.ordinal(), ClueTier.MASTER, HotColdLocation.FELDIP_HILLS_SE.getArea(), List.of(HotColdLocation.FELDIP_HILLS_SE.getWorldPoint())),
		new Clues(671, "Castle Wars balloon: Dig", HotColdLocation.FELDIP_HILLS_CW_BALLOON.ordinal(), ClueTier.MASTER, HotColdLocation.FELDIP_HILLS_CW_BALLOON.getArea(), List.of(HotColdLocation.FELDIP_HILLS_CW_BALLOON.getWorldPoint())),
		new Clues(672, "Mountain Camp: Dig", HotColdLocation.FREMENNIK_PROVINCE_MTN_CAMP.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_MTN_CAMP.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_MTN_CAMP.getWorldPoint())),
		new Clues(673, "Rellekka Hunter area: Dig", HotColdLocation.FREMENNIK_PROVINCE_RELLEKKA_HUNTER.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_RELLEKKA_HUNTER.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_RELLEKKA_HUNTER.getWorldPoint())),
		new Clues(674, "Keldagrim mine: Dig", HotColdLocation.FREMENNIK_PROVINCE_KELGADRIM_ENTRANCE.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_KELGADRIM_ENTRANCE.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_KELGADRIM_ENTRANCE.getWorldPoint())),
		new Clues(675, "Rellekka fence: Dig", HotColdLocation.FREMENNIK_PROVINCE_SW.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_SW.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_SW.getWorldPoint())),
		new Clues(676, "Lighthouse: Dig", HotColdLocation.FREMENNIK_PROVINCE_LIGHTHOUSE.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_LIGHTHOUSE.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_LIGHTHOUSE.getWorldPoint())),
		new Clues(677, "Etceteria's castle: Dig", HotColdLocation.FREMENNIK_PROVINCE_ETCETERIA_CASTLE.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_ETCETERIA_CASTLE.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_ETCETERIA_CASTLE.getWorldPoint())),
		new Clues(678, "Miscellania's courtyard: Dig", HotColdLocation.FREMENNIK_PROVINCE_MISC_COURTYARD.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_MISC_COURTYARD.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_MISC_COURTYARD.getWorldPoint())),
		new Clues(679, "Neitiznot rune: Dig", HotColdLocation.FREMENNIK_PROVINCE_FREMMY_ISLES_MINE.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_FREMMY_ISLES_MINE.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_FREMMY_ISLES_MINE.getWorldPoint())),
		new Clues(680, "Neitiznot mine: Dig", HotColdLocation.FREMENNIK_PROVINCE_WEST_ISLES_MINE.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_WEST_ISLES_MINE.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_WEST_ISLES_MINE.getWorldPoint())),
		new Clues(681, "Jatizso mine: Dig", HotColdLocation.FREMENNIK_PROVINCE_WEST_JATIZSO_ENTRANCE.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_WEST_JATIZSO_ENTRANCE.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_WEST_JATIZSO_ENTRANCE.getWorldPoint())),
		new Clues(682, "Pirates' Cove: Dig", HotColdLocation.FREMENNIK_PROVINCE_PIRATES_COVE.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_PIRATES_COVE.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_PIRATES_COVE.getWorldPoint())),
		new Clues(683, "Astral altar: Dig", HotColdLocation.FREMENNIK_PROVINCE_ASTRAL_ALTER.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_ASTRAL_ALTER.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_ASTRAL_ALTER.getWorldPoint())),
		new Clues(684, "Lunar Isle village: Dig", HotColdLocation.FREMENNIK_PROVINCE_LUNAR_VILLAGE.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_LUNAR_VILLAGE.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_LUNAR_VILLAGE.getWorldPoint())),
		new Clues(685, "Lunar Isle north: Dig", HotColdLocation.FREMENNIK_PROVINCE_LUNAR_NORTH.ordinal(), ClueTier.MASTER, HotColdLocation.FREMENNIK_PROVINCE_LUNAR_NORTH.getArea(), List.of(HotColdLocation.FREMENNIK_PROVINCE_LUNAR_NORTH.getWorldPoint())),
		new Clues(686, "Isle of Souls Mine: Dig", HotColdLocation.ISLE_OF_SOULS_MINE.ordinal(), ClueTier.MASTER, HotColdLocation.ISLE_OF_SOULS_MINE.getArea(), List.of(HotColdLocation.ISLE_OF_SOULS_MINE.getWorldPoint())),
		new Clues(687, "Sinclair Mansion: Dig", HotColdLocation.KANDARIN_SINCLAR_MANSION.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_SINCLAR_MANSION.getArea(), List.of(HotColdLocation.KANDARIN_SINCLAR_MANSION.getWorldPoint())),
		new Clues(688, "Catherby rocks: Dig", HotColdLocation.KANDARIN_CATHERBY.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_CATHERBY.getArea(), List.of(HotColdLocation.KANDARIN_CATHERBY.getWorldPoint())),
		new Clues(689, "Grand Tree: Dig", HotColdLocation.KANDARIN_GRAND_TREE.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_GRAND_TREE.getArea(), List.of(HotColdLocation.KANDARIN_GRAND_TREE.getWorldPoint())),
		new Clues(690, "Seers' Village: Dig", HotColdLocation.KANDARIN_SEERS.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_SEERS.getArea(), List.of(HotColdLocation.KANDARIN_SEERS.getWorldPoint())),
		new Clues(691, "McGrubor's Wood: Dig", HotColdLocation.KANDARIN_MCGRUBORS_WOOD.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_MCGRUBORS_WOOD.getArea(), List.of(HotColdLocation.KANDARIN_MCGRUBORS_WOOD.getWorldPoint())),
		new Clues(692, "Fishing Guild: Dig", HotColdLocation.KANDARIN_FISHING_BUILD.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_FISHING_BUILD.getArea(), List.of(HotColdLocation.KANDARIN_FISHING_BUILD.getWorldPoint())),
		new Clues(693, "Witchaven: Dig", HotColdLocation.KANDARIN_WITCHHAVEN.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_WITCHHAVEN.getArea(), List.of(HotColdLocation.KANDARIN_WITCHHAVEN.getWorldPoint())),
		new Clues(694, "Necromancer Tower: Dig", HotColdLocation.KANDARIN_NECRO_TOWER.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_NECRO_TOWER.getArea(), List.of(HotColdLocation.KANDARIN_NECRO_TOWER.getWorldPoint())),
		new Clues(695, "Fight Arena: Dig", HotColdLocation.KANDARIN_FIGHT_ARENA.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_FIGHT_ARENA.getArea(), List.of(HotColdLocation.KANDARIN_FIGHT_ARENA.getWorldPoint())),
		new Clues(696, "Tree Gnome Village: Dig", HotColdLocation.KANDARIN_TREE_GNOME_VILLAGE.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_TREE_GNOME_VILLAGE.getArea(), List.of(HotColdLocation.KANDARIN_TREE_GNOME_VILLAGE.getWorldPoint())),
		new Clues(697, "Grave of Scorpius: Dig", HotColdLocation.KANDARIN_GRAVE_OF_SCORPIUS.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_GRAVE_OF_SCORPIUS.getArea(), List.of(HotColdLocation.KANDARIN_GRAVE_OF_SCORPIUS.getWorldPoint())),
		new Clues(698, "Khazard Battlefield: Dig", HotColdLocation.KANDARIN_KHAZARD_BATTLEFIELD.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_KHAZARD_BATTLEFIELD.getArea(), List.of(HotColdLocation.KANDARIN_KHAZARD_BATTLEFIELD.getWorldPoint())),
		new Clues(699, "West Ardougne: Dig", HotColdLocation.KANDARIN_WEST_ARDY.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_WEST_ARDY.getArea(), List.of(HotColdLocation.KANDARIN_WEST_ARDY.getWorldPoint())),
		new Clues(700, "Tree Gnome Stronghold: Dig", HotColdLocation.KANDARIN_SW_TREE_GNOME_STRONGHOLD.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_SW_TREE_GNOME_STRONGHOLD.getArea(), List.of(HotColdLocation.KANDARIN_SW_TREE_GNOME_STRONGHOLD.getWorldPoint())),
		new Clues(701, "Outpost: Dig", HotColdLocation.KANDARIN_OUTPOST.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_OUTPOST.getArea(), List.of(HotColdLocation.KANDARIN_OUTPOST.getWorldPoint())),
		new Clues(702, "Baxtorian Falls: Dig", HotColdLocation.KANDARIN_BAXTORIAN_FALLS.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_BAXTORIAN_FALLS.getArea(), List.of(HotColdLocation.KANDARIN_BAXTORIAN_FALLS.getWorldPoint())),
		new Clues(703, "Barbarian Agility Course: Dig", HotColdLocation.KANDARIN_BA_AGILITY_COURSE.ordinal(), ClueTier.MASTER, HotColdLocation.KANDARIN_BA_AGILITY_COURSE.getArea(), List.of(HotColdLocation.KANDARIN_BA_AGILITY_COURSE.getWorldPoint())),
		new Clues(704, "Musa Point: Dig", HotColdLocation.KARAMJA_MUSA_POINT.ordinal(), ClueTier.MASTER, HotColdLocation.KARAMJA_MUSA_POINT.getArea(), List.of(HotColdLocation.KARAMJA_MUSA_POINT.getWorldPoint())),
		new Clues(705, "Brimhaven fruit tree: Dig", HotColdLocation.KARAMJA_BRIMHAVEN_FRUIT_TREE.ordinal(), ClueTier.MASTER, HotColdLocation.KARAMJA_BRIMHAVEN_FRUIT_TREE.getArea(), List.of(HotColdLocation.KARAMJA_BRIMHAVEN_FRUIT_TREE.getWorldPoint())),
		new Clues(706, "Brimhaven west: Dig", HotColdLocation.KARAMJA_WEST_BRIMHAVEN.ordinal(), ClueTier.MASTER, HotColdLocation.KARAMJA_WEST_BRIMHAVEN.getArea(), List.of(HotColdLocation.KARAMJA_WEST_BRIMHAVEN.getWorldPoint())),
		new Clues(707, "Brimhaven gnome glider: Dig", HotColdLocation.KARAMJA_GLIDER.ordinal(), ClueTier.MASTER, HotColdLocation.KARAMJA_GLIDER.getArea(), List.of(HotColdLocation.KARAMJA_GLIDER.getWorldPoint())),
		new Clues(708, "Kharazi Jungle north-east: Dig", HotColdLocation.KARAMJA_KHARAZI_NE.ordinal(), ClueTier.MASTER, HotColdLocation.KARAMJA_KHARAZI_NE.getArea(), List.of(HotColdLocation.KARAMJA_KHARAZI_NE.getWorldPoint())),
		new Clues(709, "Kharazi Jungle south-west: Dig", HotColdLocation.KARAMJA_KHARAZI_SW.ordinal(), ClueTier.MASTER, HotColdLocation.KARAMJA_KHARAZI_SW.getArea(), List.of(HotColdLocation.KARAMJA_KHARAZI_SW.getWorldPoint())),
		new Clues(710, "Crash Island: Dig", HotColdLocation.KARAMJA_CRASH_ISLAND.ordinal(), ClueTier.MASTER, HotColdLocation.KARAMJA_CRASH_ISLAND.getArea(), List.of(HotColdLocation.KARAMJA_CRASH_ISLAND.getWorldPoint())),
		new Clues(711, "Champion's Guild: Dig", HotColdLocation.MISTHALIN_VARROCK_STONE_CIRCLE.ordinal(), ClueTier.MASTER, HotColdLocation.MISTHALIN_VARROCK_STONE_CIRCLE.getArea(), List.of(HotColdLocation.MISTHALIN_VARROCK_STONE_CIRCLE.getWorldPoint())),
		new Clues(712, "Lumbridge Fishing tutor: Dig", HotColdLocation.MISTHALIN_LUMBRIDGE.ordinal(), ClueTier.MASTER, HotColdLocation.MISTHALIN_LUMBRIDGE.getArea(), List.of(HotColdLocation.MISTHALIN_LUMBRIDGE.getWorldPoint())),
		new Clues(713, "Lumbridge windmill pond: Dig", HotColdLocation.MISTHALIN_LUMBRIDGE_2.ordinal(), ClueTier.MASTER, HotColdLocation.MISTHALIN_LUMBRIDGE_2.getArea(), List.of(HotColdLocation.MISTHALIN_LUMBRIDGE_2.getWorldPoint())),
		new Clues(714, "Gertrude's house: Dig", HotColdLocation.MISTHALIN_GERTUDES.ordinal(), ClueTier.MASTER, HotColdLocation.MISTHALIN_GERTUDES.getArea(), List.of(HotColdLocation.MISTHALIN_GERTUDES.getWorldPoint())),
		new Clues(715, "Draynor Village bank: Dig", HotColdLocation.MISTHALIN_DRAYNOR_BANK.ordinal(), ClueTier.MASTER, HotColdLocation.MISTHALIN_DRAYNOR_BANK.getArea(), List.of(HotColdLocation.MISTHALIN_DRAYNOR_BANK.getWorldPoint())),
		new Clues(716, "Lumber Yard: Dig", HotColdLocation.MISTHALIN_LUMBER_YARD.ordinal(), ClueTier.MASTER, HotColdLocation.MISTHALIN_LUMBER_YARD.getArea(), List.of(HotColdLocation.MISTHALIN_LUMBER_YARD.getWorldPoint())),
		new Clues(717, "Burgh de Rott: Dig", HotColdLocation.MORYTANIA_BURGH_DE_ROTT.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_BURGH_DE_ROTT.getArea(), List.of(HotColdLocation.MORYTANIA_BURGH_DE_ROTT.getWorldPoint())),
		new Clues(718, "Darkmeyer: Dig", HotColdLocation.MORYTANIA_DARKMEYER.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_DARKMEYER.getArea(), List.of(HotColdLocation.MORYTANIA_DARKMEYER.getWorldPoint())),
		new Clues(719, "ALQ: Dig", HotColdLocation.MORYTANIA_PORT_PHASMATYS.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_PORT_PHASMATYS.getArea(), List.of(HotColdLocation.MORYTANIA_PORT_PHASMATYS.getWorldPoint())),
		new Clues(720, "The Hollows: Dig", HotColdLocation.MORYTANIA_HOLLOWS.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_HOLLOWS.getArea(), List.of(HotColdLocation.MORYTANIA_HOLLOWS.getWorldPoint())),
		new Clues(721, "Mort Myre Swamp: Dig", HotColdLocation.MORYTANIA_SWAMP.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_SWAMP.getArea(), List.of(HotColdLocation.MORYTANIA_SWAMP.getWorldPoint())),
		new Clues(722, "Haunted Mine: Dig", HotColdLocation.MORYTANIA_HAUNTED_MINE.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_HAUNTED_MINE.getArea(), List.of(HotColdLocation.MORYTANIA_HAUNTED_MINE.getWorldPoint())),
		new Clues(723, "Mausoleum south: Dig", HotColdLocation.MORYTANIA_MAUSOLEUM.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_MAUSOLEUM.getArea(), List.of(HotColdLocation.MORYTANIA_MAUSOLEUM.getWorldPoint())),
		new Clues(724, "Mos Le'Harmless lakes: Dig", HotColdLocation.MORYTANIA_MOS_LES_HARMLESS.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_MOS_LES_HARMLESS.getArea(), List.of(HotColdLocation.MORYTANIA_MOS_LES_HARMLESS.getWorldPoint())),
		new Clues(725, "Mos Le'Harmless bar: Dig", HotColdLocation.MORYTANIA_MOS_LES_HARMLESS_BAR.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_MOS_LES_HARMLESS_BAR.getArea(), List.of(HotColdLocation.MORYTANIA_MOS_LES_HARMLESS_BAR.getWorldPoint())),
		new Clues(726, "Dragontooth north: Dig", HotColdLocation.MORYTANIA_DRAGONTOOTH_NORTH.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_DRAGONTOOTH_NORTH.getArea(), List.of(HotColdLocation.MORYTANIA_DRAGONTOOTH_NORTH.getWorldPoint())),
		new Clues(727, "Dragontooth south: Dig", HotColdLocation.MORYTANIA_DRAGONTOOTH_SOUTH.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_DRAGONTOOTH_SOUTH.getArea(), List.of(HotColdLocation.MORYTANIA_DRAGONTOOTH_SOUTH.getWorldPoint())),
		new Clues(728, "Slepe tents: Dig", HotColdLocation.MORYTANIA_SLEPE_TENTS.ordinal(), ClueTier.MASTER, HotColdLocation.MORYTANIA_SLEPE_TENTS.getArea(), List.of(HotColdLocation.MORYTANIA_SLEPE_TENTS.getWorldPoint())),
		new Clues(729, "Bazaar: Dig", HotColdLocation.VARLAMORE_BAZAAR.ordinal(), ClueTier.MASTER, HotColdLocation.VARLAMORE_BAZAAR.getArea(), List.of(HotColdLocation.VARLAMORE_BAZAAR.getWorldPoint())),
		new Clues(730, "Locus Oasis: Dig", HotColdLocation.VARLAMORE_LOCUS_OASIS.ordinal(), ClueTier.MASTER, HotColdLocation.VARLAMORE_LOCUS_OASIS.getArea(), List.of(HotColdLocation.VARLAMORE_LOCUS_OASIS.getWorldPoint())),
		new Clues(731, "Tlati Rainforest: Dig", HotColdLocation.VARLAMORE_RAINFOREST_CENTRE.ordinal(), ClueTier.MASTER, HotColdLocation.VARLAMORE_RAINFOREST_CENTRE.getArea(), List.of(HotColdLocation.VARLAMORE_RAINFOREST_CENTRE.getWorldPoint())),
		new Clues(732, "Sunset Coast: Dig", HotColdLocation.VARLAMORE_SUNSET_COAST.ordinal(), ClueTier.MASTER, HotColdLocation.VARLAMORE_SUNSET_COAST.getArea(), List.of(HotColdLocation.VARLAMORE_SUNSET_COAST.getWorldPoint())),
		new Clues(733, "Villa Lucens: Dig", HotColdLocation.VARLAMORE_VILLA_LUCENS.ordinal(), ClueTier.MASTER, HotColdLocation.VARLAMORE_VILLA_LUCENS.getArea(), List.of(HotColdLocation.VARLAMORE_VILLA_LUCENS.getWorldPoint())),
		new Clues(734, "Eagles' Peak: Dig", HotColdLocation.WESTERN_PROVINCE_EAGLES_PEAK.ordinal(), ClueTier.MASTER, HotColdLocation.WESTERN_PROVINCE_EAGLES_PEAK.getArea(), List.of(HotColdLocation.WESTERN_PROVINCE_EAGLES_PEAK.getWorldPoint())),
		new Clues(735, "Piscatoris Fishing Colony: Dig", HotColdLocation.WESTERN_PROVINCE_PISCATORIS.ordinal(), ClueTier.MASTER, HotColdLocation.WESTERN_PROVINCE_PISCATORIS.getArea(), List.of(HotColdLocation.WESTERN_PROVINCE_PISCATORIS.getWorldPoint())),
		new Clues(736, "Piscatoris Hunter area: Dig", HotColdLocation.WESTERN_PROVINCE_PISCATORIS_HUNTER_AREA.ordinal(), ClueTier.MASTER, HotColdLocation.WESTERN_PROVINCE_PISCATORIS_HUNTER_AREA.getArea(), List.of(HotColdLocation.WESTERN_PROVINCE_PISCATORIS_HUNTER_AREA.getWorldPoint())),
		new Clues(737, "Arandar: Dig", HotColdLocation.WESTERN_PROVINCE_ARANDAR.ordinal(), ClueTier.MASTER, HotColdLocation.WESTERN_PROVINCE_ARANDAR.getArea(), List.of(HotColdLocation.WESTERN_PROVINCE_ARANDAR.getWorldPoint())),
		new Clues(738, "Iorwerth Camp east: Dig", HotColdLocation.WESTERN_PROVINCE_ELF_CAMP_EAST.ordinal(), ClueTier.MASTER, HotColdLocation.WESTERN_PROVINCE_ELF_CAMP_EAST.getArea(), List.of(HotColdLocation.WESTERN_PROVINCE_ELF_CAMP_EAST.getWorldPoint())),
		new Clues(739, "Iorwerth Camp north-west: Dig", HotColdLocation.WESTERN_PROVINCE_ELF_CAMP_NW.ordinal(), ClueTier.MASTER, HotColdLocation.WESTERN_PROVINCE_ELF_CAMP_NW.getArea(), List.of(HotColdLocation.WESTERN_PROVINCE_ELF_CAMP_NW.getWorldPoint())),
		new Clues(740, "Lletya: Dig", HotColdLocation.WESTERN_PROVINCE_LLETYA.ordinal(), ClueTier.MASTER, HotColdLocation.WESTERN_PROVINCE_LLETYA.getArea(), List.of(HotColdLocation.WESTERN_PROVINCE_LLETYA.getWorldPoint())),
		new Clues(741, "Tyras Camp: Dig", HotColdLocation.WESTERN_PROVINCE_TYRAS.ordinal(), ClueTier.MASTER, HotColdLocation.WESTERN_PROVINCE_TYRAS.getArea(), List.of(HotColdLocation.WESTERN_PROVINCE_TYRAS.getWorldPoint())),
		new Clues(742, "Zul-Andra: Dig", HotColdLocation.WESTERN_PROVINCE_ZULANDRA.ordinal(), ClueTier.MASTER, HotColdLocation.WESTERN_PROVINCE_ZULANDRA.getArea(), List.of(HotColdLocation.WESTERN_PROVINCE_ZULANDRA.getWorldPoint())),
		new Clues(743, "GE north: Dig", HotColdLocation.WILDERNESS_5.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_5.getArea(), List.of(HotColdLocation.WILDERNESS_5.getWorldPoint())),
		new Clues(744, "Dark Warriors' Fortress: Dig", HotColdLocation.WILDERNESS_12.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_12.getArea(), List.of(HotColdLocation.WILDERNESS_12.getWorldPoint())),
		new Clues(745, "Corporeal Beast's lair: Dig", HotColdLocation.WILDERNESS_20.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_20.getArea(), List.of(HotColdLocation.WILDERNESS_20.getWorldPoint())),
		new Clues(746, "Eastern Ruins: Dig", HotColdLocation.WILDERNESS_27.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_27.getArea(), List.of(HotColdLocation.WILDERNESS_27.getWorldPoint())),
		new Clues(747, "Wilderness crabs teleport: Dig", HotColdLocation.WILDERNESS_28.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_28.getArea(), List.of(HotColdLocation.WILDERNESS_28.getWorldPoint())),
		new Clues(748, "black knights: Dig", HotColdLocation.WILDERNESS_32.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_32.getArea(), List.of(HotColdLocation.WILDERNESS_32.getWorldPoint())),
		new Clues(749, "Wilderness canoe: Dig", HotColdLocation.WILDERNESS_35.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_35.getArea(), List.of(HotColdLocation.WILDERNESS_35.getWorldPoint())),
		new Clues(750, "Chaos Temple: Dig", HotColdLocation.WILDERNESS_37.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_37.getArea(), List.of(HotColdLocation.WILDERNESS_37.getWorldPoint())),
		new Clues(751, "Callisto's Den: Dig", HotColdLocation.WILDERNESS_38.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_38.getArea(), List.of(HotColdLocation.WILDERNESS_38.getWorldPoint())),
		new Clues(752, "Deserted Keep: Dig", HotColdLocation.WILDERNESS_49.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_49.getArea(), List.of(HotColdLocation.WILDERNESS_49.getWorldPoint())),
		new Clues(753, "Wilderness Agility Course: Dig", HotColdLocation.WILDERNESS_54.ordinal(), ClueTier.MASTER, HotColdLocation.WILDERNESS_54.getArea(), List.of(HotColdLocation.WILDERNESS_54.getWorldPoint())),
		new Clues(754, "Blast mine bank: Dig", HotColdLocation.ZEAH_BLASTMINE_BANK.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_BLASTMINE_BANK.getArea(), List.of(HotColdLocation.ZEAH_BLASTMINE_BANK.getWorldPoint())),
		new Clues(755, "Blast mine north: Dig", HotColdLocation.ZEAH_BLASTMINE_NORTH.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_BLASTMINE_NORTH.getArea(), List.of(HotColdLocation.ZEAH_BLASTMINE_NORTH.getWorldPoint())),
		new Clues(756, "lovakite furnace: Dig", HotColdLocation.ZEAH_LOVAKITE_FURNACE.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_LOVAKITE_FURNACE.getArea(), List.of(HotColdLocation.ZEAH_LOVAKITE_FURNACE.getWorldPoint())),
		new Clues(757, "Lovakengj mithril: Dig", HotColdLocation.ZEAH_LOVAKENGJ_MINE.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_LOVAKENGJ_MINE.getArea(), List.of(HotColdLocation.ZEAH_LOVAKENGJ_MINE.getWorldPoint())),
		new Clues(758, "Sulphur mine: Dig", HotColdLocation.ZEAH_SULPHR_MINE.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_SULPHR_MINE.getArea(), List.of(HotColdLocation.ZEAH_SULPHR_MINE.getWorldPoint())),
		new Clues(759, "War tent: Dig", HotColdLocation.ZEAH_SHAYZIEN_BANK.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_SHAYZIEN_BANK.getArea(), List.of(HotColdLocation.ZEAH_SHAYZIEN_BANK.getWorldPoint())),
		new Clues(760, "Lizardman overpass: Dig", HotColdLocation.ZEAH_OVERPASS.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_OVERPASS.getArea(), List.of(HotColdLocation.ZEAH_OVERPASS.getWorldPoint())),
		new Clues(761, "Lizardman Canyon: Dig", HotColdLocation.ZEAH_LIZARDMAN.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_LIZARDMAN.getArea(), List.of(HotColdLocation.ZEAH_LIZARDMAN.getWorldPoint())),
		new Clues(762, "Combat Ring: Dig", HotColdLocation.ZEAH_COMBAT_RING.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_COMBAT_RING.getArea(), List.of(HotColdLocation.ZEAH_COMBAT_RING.getWorldPoint())),
		new Clues(763, "Graveyard of Heroes: Dig", HotColdLocation.ZEAH_SHAYZIEN_BANK_2.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_SHAYZIEN_BANK_2.getArea(), List.of(HotColdLocation.ZEAH_SHAYZIEN_BANK_2.getWorldPoint())),
		new Clues(764, "Arceuus Library: Dig", HotColdLocation.ZEAH_LIBRARY.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_LIBRARY.getArea(), List.of(HotColdLocation.ZEAH_LIBRARY.getWorldPoint())),
		new Clues(765, "Arceuus church: Dig", HotColdLocation.ZEAH_HOUSECHURCH.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_HOUSECHURCH.getArea(), List.of(HotColdLocation.ZEAH_HOUSECHURCH.getWorldPoint())),
		new Clues(766, "Dark Altar: Dig", HotColdLocation.ZEAH_DARK_ALTAR.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_DARK_ALTAR.getArea(), List.of(HotColdLocation.ZEAH_DARK_ALTAR.getWorldPoint())),
		new Clues(767, "Arceuus entrance: Dig", HotColdLocation.ZEAH_ARCEUUS_HOUSE.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_ARCEUUS_HOUSE.getArea(), List.of(HotColdLocation.ZEAH_ARCEUUS_HOUSE.getWorldPoint())),
		new Clues(768, "Dense essence mine: Dig", HotColdLocation.ZEAH_ESSENCE_MINE.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_ESSENCE_MINE.getArea(), List.of(HotColdLocation.ZEAH_ESSENCE_MINE.getWorldPoint())),
		new Clues(769, "Dense essence mine north-east: Dig", HotColdLocation.ZEAH_ESSENCE_MINE_NE.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_ESSENCE_MINE_NE.getArea(), List.of(HotColdLocation.ZEAH_ESSENCE_MINE_NE.getWorldPoint())),
		new Clues(770, "Piscarilius mine: Dig", HotColdLocation.ZEAH_PISCARILUS_MINE.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_PISCARILUS_MINE.getArea(), List.of(HotColdLocation.ZEAH_PISCARILUS_MINE.getWorldPoint())),
		new Clues(771, "Kingstown gravestone: Dig", HotColdLocation.ZEAH_GOLDEN_FIELD_TAVERN.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_GOLDEN_FIELD_TAVERN.getArea(), List.of(HotColdLocation.ZEAH_GOLDEN_FIELD_TAVERN.getWorldPoint())),
		new Clues(772, "Mess hall: Dig", HotColdLocation.ZEAH_MESS_HALL.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_MESS_HALL.getArea(), List.of(HotColdLocation.ZEAH_MESS_HALL.getWorldPoint())),
		new Clues(773, "Watson's house: Dig", HotColdLocation.ZEAH_WATSONS_HOUSE.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_WATSONS_HOUSE.getArea(), List.of(HotColdLocation.ZEAH_WATSONS_HOUSE.getWorldPoint())),
		new Clues(774, "Tithe Farm: Dig", HotColdLocation.ZEAH_VANNAHS_FARM_STORE.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_VANNAHS_FARM_STORE.getArea(), List.of(HotColdLocation.ZEAH_VANNAHS_FARM_STORE.getWorldPoint())),
		new Clues(775, "Farming Guild: Dig", HotColdLocation.ZEAH_FARMING_GUILD_W.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_FARMING_GUILD_W.getArea(), List.of(HotColdLocation.ZEAH_FARMING_GUILD_W.getWorldPoint())),
		new Clues(776, "Battlefront: Dig", HotColdLocation.ZEAH_DAIRY_COW.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_DAIRY_COW.getArea(), List.of(HotColdLocation.ZEAH_DAIRY_COW.getWorldPoint())),
		new Clues(777, "Kebos Swamp: Dig", HotColdLocation.ZEAH_CRIMSON_SWIFTS.ordinal(), ClueTier.MASTER, HotColdLocation.ZEAH_CRIMSON_SWIFTS.getArea(), List.of(HotColdLocation.ZEAH_CRIMSON_SWIFTS.getWorldPoint()))
	);

	final String clueText;
	final String clueDetail;
	final Color clueDetailColor;
	final int itemID;
	final int clueID;
	final ClueTier clueTier;

	@Getter
	final OrRequirement regions;

	// To be initialized to avoid passing around
	@Setter
	@Inject
	public static ClueDetailsConfig config;

	Clues(String clueDetail, int itemID, ClueTier clueTier, String clueText, List<WorldPoint> wps)
	{
		this.clueID = -1;
		this.clueDetail = clueDetail;
		this.clueDetailColor = Color.WHITE;
		this.itemID = itemID;
		this.clueTier = clueTier;
		this.clueText = clueText;
		this.regions = new OrRequirement(wps);
	}

	Clues(int clueID, String clueDetail, int itemID, ClueTier clueTier, String clueText, List<WorldPoint> wps)
	{
		this.clueID = clueID;
		this.clueDetail = clueDetail;
		this.clueDetailColor = Color.WHITE;
		this.itemID = itemID;
		this.clueTier = clueTier;
		this.clueText = clueText;
		this.regions = new OrRequirement(wps);
	}

	Clues(String clueDetail, Color clueDetailColor, int itemID, ClueTier clueTier, String clueText, List<WorldPoint> wps)
	{
		this.clueID = -1;
		this.clueDetail = clueDetail;
		this.clueDetailColor = clueDetailColor;
		this.itemID = itemID;
		this.clueTier = clueTier;
		this.clueText = clueText;
		this.regions = new OrRequirement(wps);
	}

	private static final Collection<Integer> TRACKED_CLUE_IDS = List.of(
		ItemID.CLUE_SCROLL_MASTER,
		ItemID.CLUE_SCROLL_BEGINNER
	);

	private static final Collection<Integer> TRACKED_TORN_CLUE_IDS = List.of(
		ItemID.TORN_CLUE_SCROLL_PART_1,
		ItemID.TORN_CLUE_SCROLL_PART_2,
		ItemID.TORN_CLUE_SCROLL_PART_3
	);

	public static final Collection<Integer> DEV_MODE_IDS = List.of(
		ItemID.DAEYALT_ESSENCE
	);

	private static Map<Integer, List<Clues>> itemIdClueCache = new HashMap<>();
	private static Map<Integer, Clues> clueIdClueCache = new HashMap<>();
	private static Map<Integer, Clues> unfilteredClueCache = new HashMap<>();

	public static void rebuildFilteredCluesCache()
	{
		List<ClueTier> enabledClueTiers = getEnabledClueTiers();

		List<Clues> enabledClues = Clues.CLUES.stream()
				.filter(c -> enabledClueTiers.contains(c.getClueTier()))
				.collect(Collectors.toList());

		itemIdClueCache = enabledClues
				.stream()
				.filter(clue -> clue.getItemID() >= 2677) // Ignore InterfaceID and HotColdLocation
				.collect(Collectors.groupingBy(Clues::getItemID));

		clueIdClueCache = enabledClues
				.stream()
				.collect(Collectors.toMap(Clues::getClueID, clue -> clue));

		unfilteredClueCache = Clues.CLUES
				.stream()
				.collect(Collectors.toMap(Clues::getClueID, clue -> clue));
	}

	private static List<ClueTier> getEnabledClueTiers()
	{
		if (config == null)
		{
			return Arrays.asList(ClueTier.values());
		}

		List<ClueTier> enabledClues = new ArrayList<>();
		if (config.beginnerDetails()) enabledClues.add(ClueTier.BEGINNER);
		if (config.easyDetails()) enabledClues.add(ClueTier.EASY);
		if (config.mediumDetails())
		{
			enabledClues.add(ClueTier.MEDIUM);
			enabledClues.add(ClueTier.MEDIUM_CHALLENGE);
			enabledClues.add(ClueTier.MEDIUM_KEY);
		}
		if (config.hardDetails())
		{
			enabledClues.add(ClueTier.HARD);
			enabledClues.add(ClueTier.HARD_CHALLENGE);
		}
		if (config.eliteDetails())
		{
			enabledClues.add(ClueTier.ELITE);
			enabledClues.add(ClueTier.ELITE_CHALLENGE);
		}
		if (config.masterDetails()) enabledClues.add(ClueTier.MASTER);

		return enabledClues;
	}

	public static Clues forItemId(int itemId)
	{
		Clues clue = clueIdClueCache.get(itemId);
		if (clue != null && clue.clueID == -1)
		{
			return clue;
		}
		return null;
	}

	public static Clues forClueId(int clueId)
	{
		return unfilteredClueCache.get(clueId);
	}

	public static Clues forClueIdFiltered(int clueId)
	{
		return clueIdClueCache.get(clueId);
	}

	public Integer getClueID()
	{
		if (clueID != -1)
		{
			return clueID;
		}
		return getItemID();
	}

	public static Integer forTextGetId(String rawText)
	{
		final String text = Text.sanitizeMultilineText(rawText).toLowerCase();

		for (Clues clue : clueIdClueCache.values())
		{
			if (text.equalsIgnoreCase(clue.getClueText()))
			{
				return clue.getClueID();
			}
		}

		return null;
	}

	public static Integer forOtherIdGetId(int otherId)
	{
		List<Clues> clues = itemIdClueCache.get(otherId);
		if (!clues.isEmpty())
		{
			return clues.get(0).clueID;
		}
		return null;
	}

	public String getDetail(ConfigManager configManager)
	{
		String text = configManager.getConfiguration("clue-details-text", String.valueOf(getClueID()));
		if (text != null) return text;
		return getClueDetail();
	}

	public Color getDetailColor(ConfigManager configManager)
	{
		String colorCode = configManager.getConfiguration("clue-details-color", String.valueOf(getClueID()));
		if (colorCode != null) return Color.decode(colorCode);
		return getClueDetailColor();
	}

	public List<Integer> getItems(ClueDetailsPlugin plugin, ConfigManager configManager)
	{
		String items = configManager.getConfiguration(CLUE_ITEMS_CONFIG, String.valueOf(getClueID()));
		if (items != null)
		{
			return plugin.gson.fromJson(items, new TypeToken<List<Integer>>()
			{
			}.getType());
		}
		return null;
	}

	public static boolean isClue(int itemId, boolean isDeveloperMode)
	{
		return itemIdClueCache.containsKey(itemId) || (isDeveloperMode && DEV_MODE_IDS.contains(itemId));
	}

	public static boolean isBeginnerOrMasterClue(int itemId, boolean isDeveloperMode)
	{
		return TRACKED_CLUE_IDS.contains(itemId) || (isDeveloperMode && DEV_MODE_IDS.contains(itemId));
	}

	public static boolean isTrackedClueOrTornClue(int itemId, boolean isDeveloperMode)
	{
		return TRACKED_CLUE_IDS.contains(itemId) || TRACKED_TORN_CLUE_IDS.contains(itemId) || (isDeveloperMode && DEV_MODE_IDS.contains(itemId));
	}

	public static Collection<Integer> getTrackedClueAndTornClueIds(boolean isDevMode)
	{
		Collection<Integer> allIds = new ArrayList<>();
		allIds.addAll(TRACKED_CLUE_IDS);
		allIds.addAll(TRACKED_TORN_CLUE_IDS);
		if (isDevMode) allIds.addAll(DEV_MODE_IDS);
		return allIds;
	}

	public boolean isEnabled(ClueDetailsConfig config)
	{
		ClueTier tier = getClueTier();

		if (config == null) return true;

		if (tier == ClueTier.BEGINNER)
		{
			return config.beginnerDetails();
		}
		if (tier == ClueTier.EASY)
		{
			return config.easyDetails();
		}
		if (tier == ClueTier.MEDIUM || tier == ClueTier.MEDIUM_CHALLENGE || tier == ClueTier.MEDIUM_KEY)
		{
			return config.mediumDetails();
		}
		if (tier == ClueTier.HARD || tier == ClueTier.HARD_CHALLENGE)
		{
			return config.hardDetails();
		}
		if (tier == ClueTier.ELITE || tier == ClueTier.ELITE_CHALLENGE)
		{
			return config.eliteDetails();
		}
		if (tier == ClueTier.MASTER)
		{
			return config.masterDetails();
		}
		return true;
	}
}
