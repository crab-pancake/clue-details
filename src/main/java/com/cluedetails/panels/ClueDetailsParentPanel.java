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
package com.cluedetails.panels;

import com.cluedetails.*;
import com.cluedetails.ClueDetailsConfig.*;

import static com.cluedetails.ClueDetailsConfig.GROUP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lombok.Getter;
import net.runelite.api.ChatMessageType;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.chatbox.ChatboxItemSearch;
import net.runelite.client.game.chatbox.ChatboxPanelManager;
import net.runelite.client.plugins.grounditems.GroundItemsConfig;
import net.runelite.client.plugins.inventorytags.InventoryTagsConfig;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.IconTextField;
import net.runelite.client.ui.components.colorpicker.RuneliteColorPicker;
import net.runelite.client.util.ImageUtil;
import org.apache.commons.text.WordUtils;

public class ClueDetailsParentPanel extends PluginPanel
{
	JPanel searchCluesPanel = new JPanel();
	private JComboBox<Enum> tierFilterDropdown, regionFilterDropdown, orderDropdown;

	public static final int DROPDOWN_HEIGHT = 26;

	private ClueTableModel clueTableModel;
	private JTable clueTable;
	@Getter
	private int hoveredRow = -1;
	private int rightClickedRow = -1;
	private final IconTextField searchBar = new IconTextField();
	private List<ListItem> allClues = new ArrayList<>();

	private ConfigManager configManager;

	private ChatboxPanelManager chatboxPanelManager;

	private CluePreferenceManager cluePreferenceManager;
	private ClueDetailsSharingManager clueDetailsSharingManager;
	private final ClueDetailsPlugin plugin;
	private final ClueDetailsConfig config;

	private final JScrollPane scrollableContainer;

	private final JPanel allDropdownSections = new JPanel();

	private static final ImageIcon RESET_ICON;
	private static final ImageIcon RESET_HOVER_ICON;
	private static final ImageIcon COPY_ICON;
	private static final ImageIcon COPY_HOVER_ICON;
	private static final ImageIcon PASTE_ICON;
	private static final ImageIcon PASTE_HOVER_ICON;

	private final JLabel resetMarkers = new JLabel(RESET_ICON);
	private final JLabel copyMarkers = new JLabel(COPY_ICON);
	private final JLabel pasteMarkers = new JLabel(PASTE_ICON);

	static
	{
		final BufferedImage resetIcon = ImageUtil.loadImageResource(ClueDetailsPlugin.class, "/reset_icon.png");
		RESET_ICON = new ImageIcon(resetIcon);
		RESET_HOVER_ICON = new ImageIcon(ImageUtil.alphaOffset(resetIcon, 0.53f));

		final BufferedImage copyIcon = ImageUtil.loadImageResource(ClueDetailsPlugin.class, "/copy_icon.png");
		COPY_ICON = new ImageIcon(copyIcon);
		COPY_HOVER_ICON = new ImageIcon(ImageUtil.alphaOffset(copyIcon, 0.53f));

		final BufferedImage pasteIcon = ImageUtil.loadImageResource(ClueDetailsPlugin.class, "/paste_icon.png");
		PASTE_ICON = new ImageIcon(pasteIcon);
		PASTE_HOVER_ICON = new ImageIcon(ImageUtil.alphaOffset(pasteIcon, 0.53f));
	}

	public ClueDetailsParentPanel(ConfigManager configManager, CluePreferenceManager cluePreferenceManager, ClueDetailsConfig config,
									ChatboxPanelManager chatboxPanelManager, ClueDetailsSharingManager clueDetailsSharingManager, ClueDetailsPlugin plugin)
	{
		super(false);

		this.configManager = configManager;
		this.cluePreferenceManager = cluePreferenceManager;
		this.config = config;
		this.chatboxPanelManager = chatboxPanelManager;
		this.clueDetailsSharingManager = clueDetailsSharingManager;
		this.plugin = plugin;

		setBackground(ColorScheme.DARK_GRAY_COLOR);
		setLayout(new BorderLayout());

		setupTable();

		/* Setup overview panel */
		JPanel titlePanel = setupTitlePanel();

		titlePanel.add(setupImportExportButtons(), BorderLayout.EAST);

		setupSearchBar();

		showMatchingClues("");

		// Filters
		setupFilters();

		scrollableContainer = new JScrollPane(clueTable);
		scrollableContainer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel introDetailsPanel = new JPanel();
		introDetailsPanel.setLayout(new BorderLayout());
		introDetailsPanel.add(titlePanel, BorderLayout.NORTH);
		introDetailsPanel.add(searchCluesPanel, BorderLayout.CENTER);

		add(introDetailsPanel, BorderLayout.NORTH);
		add(scrollableContainer, BorderLayout.CENTER);

		refresh();
	}

	private void setupTable()
	{
		clueTableModel = new ClueTableModel();
		clueTable = new ClueJTable(clueTableModel);

		clueTable.setDefaultRenderer(Object.class, new ClueTableCellRenderer(this, cluePreferenceManager, configManager));
		clueTable.setDefaultEditor(Object.class, new ClueTableCellEditor(configManager, clueTable));

		JPopupMenu clueTablePopupMenu = getClueTablePopupMenu();
		clueTable.setComponentPopupMenu(clueTablePopupMenu);
		clueTable.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				int row = clueTable.rowAtPoint(e.getPoint());
				int column = clueTable.columnAtPoint(e.getPoint());

				if (row < 0 || column < 0) return;

				ListItem item = (ListItem) clueTableModel.getValueAt(row, column);
				if (item.isHeader()) return;

				Clues clue = item.getClue();
				if (!clickedOnTextBox(e, row)) return;

				if (SwingUtilities.isLeftMouseButton(e))
				{
					boolean currentState = cluePreferenceManager.getHighlightPreference(clue.getClueID());
					boolean newState = !currentState;
					cluePreferenceManager.saveHighlightPreference(clue.getClueID(), newState);
					clueTable.repaint();
				}
				else if (SwingUtilities.isRightMouseButton(e))
				{
					rightClickedRow = row;
				}
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				if (hoveredRow != -1)
				{
					int previousHoveredRow = hoveredRow;
					hoveredRow = -1;
					clueTable.repaint(clueTable.getCellRect(previousHoveredRow, 0, true));
				}
			}
		});
		clueTable.addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e)
			{
				int row = clueTable.rowAtPoint(e.getPoint());

				if (row != hoveredRow)
				{
					int previousHoveredRow = hoveredRow;
					hoveredRow = row;

					if (previousHoveredRow != -1)
					{
						clueTable.repaint(clueTable.getCellRect(previousHoveredRow, 0, true));
					}
					if (hoveredRow != -1)
					{
						clueTable.repaint(clueTable.getCellRect(hoveredRow, 0, true));
					}
				}
			}
		});
	}

	private boolean clickedOnTextBox(MouseEvent e, int row)
	{
		Rectangle cellRect = clueTable.getCellRect(row, 0, false);

		int cellX = e.getX() - cellRect.x;
		int cellY = e.getY() - cellRect.y;

		Insets cellInsets = new Insets(5, 5, 0, 5);

		int textAreaX = cellInsets.left;
		int textAreaY = cellInsets.top;
		int textAreaWidth = cellRect.width - cellInsets.left - cellInsets.right;
		int textAreaHeight = cellRect.height - cellInsets.top - cellInsets.bottom;

		return cellX >= textAreaX && cellX <= textAreaX + textAreaWidth &&
			cellY >= textAreaY && cellY <= textAreaY + textAreaHeight;
	}

	private JPopupMenu getClueTablePopupMenu()
	{
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem inputTextItem = new JMenuItem("Edit text for clue");
		inputTextItem.addActionListener(event ->
		{
			clueTableModel.setEditableRow(rightClickedRow);
			clueTable.editCellAt(rightClickedRow, 0);
			Component editorComponent = clueTable.getEditorComponent();
			if (editorComponent != null)
			{
				editorComponent.requestFocusInWindow();
			}
		});
		popupMenu.add(inputTextItem);

		JMenuItem inputColorItem = new JMenuItem("Edit color for clue");
		inputColorItem.addActionListener(event ->
		{
			ListItem item = (ListItem) clueTableModel.getValueAt(rightClickedRow, 0);
			Clues clue = item.getClue();
			int clueItemId = clue.getItemID();
			int clueClueID = clue.getClueID();

			RuneliteColorPicker colorPicker = getColorPicker(clue.getDetailColor(configManager));
			colorPicker.setOnColorChange(c ->
			{
				// Default color is white, so white is used to unset configurations
				if (ClueIdToDetails.equalRGB(c, Color.WHITE))
				{
					configManager.unsetConfiguration("clue-details-color", String.valueOf(clueClueID));

					// Reset Ground Items and Inventory Tags
					// Beginner & master clues are not supported by these plugins
					if (clueClueID >= 2677)
					{
						if (config.colorGroundItems())
						{
							configManager.unsetConfiguration(GroundItemsConfig.GROUP, "highlight_" + clueClueID);
						}
						if (config.colorInventoryTags())
						{
							configManager.unsetConfiguration(InventoryTagsConfig.GROUP, "tag_" + clueClueID);
						}
					}
				}
				else
				{
					configManager.setConfiguration("clue-details-color", String.valueOf(clueClueID), c);

					// Apply color to Ground Items and Inventory Tags
					// Beginner & master clues are not supported by these plugins
					if (clueClueID >= 2677)
					{
						if (config.colorGroundItems())
						{
							configManager.setConfiguration(GroundItemsConfig.GROUP, "highlight_" + clueItemId, c);
						}
						if (config.colorInventoryTags())
						{
							configManager.setConfiguration(InventoryTagsConfig.GROUP, "tag_" + clueItemId,
								plugin.getGson().toJson(Map.of("color", c)));
						}
					}
				}
			});
			colorPicker.setVisible(true);
		});
		popupMenu.add(inputColorItem);

		String inputItemsTooltip = "Add/Remove item";
		JMenuItem inputItems = new JMenuItem(inputItemsTooltip);
		inputItems.addActionListener(event ->
		{
			ListItem item = (ListItem) clueTableModel.getValueAt(rightClickedRow, 0);
			Clues clue = item.getClue();

			ChatboxItemSearch itemSearch = getItemSearch(inputItemsTooltip);
			itemSearch.onItemSelected((itemId) ->
			{
				// Get existing Clue itemIds
				int clueId = clue.getClueID();
				List<Integer> clueItemIds = cluePreferenceManager.getItemsPreference(clueId);

				if (clueItemIds == null)
				{
					clueItemIds = new ArrayList<>();
				}

				// Remove if already present
				if (clueItemIds.contains(itemId))
				{
					clueItemIds.remove(itemId);
					String chatMessage = "Removed item from " + WordUtils.capitalize(clue.getClueTier().name().toLowerCase()) + " clue";
					sendChatMessage(chatMessage);
				}
				// Add if not present
				else
				{
					clueItemIds.add(itemId);
					String chatMessage = "Added item to " + WordUtils.capitalize(clue.getClueTier().name().toLowerCase()) + " clue";
					sendChatMessage(chatMessage);
				}

				// Save Clue itemIds
				cluePreferenceManager.saveItemsPreference(clueId, clueItemIds);
			}).build();
		});
		popupMenu.add(inputItems);

		return popupMenu;
	}

	private void openResetPopup(boolean resetText, boolean resetColors, boolean resetItems, boolean resetWidgets)
	{
		int confirm = JOptionPane.showConfirmDialog(ClueDetailsParentPanel.this,
			"Are you sure you want to reset your customised details?",
			"Warning", JOptionPane.OK_CANCEL_OPTION);

		if (confirm == 0)
		{
			clueDetailsSharingManager.resetClueDetails(resetText, resetColors, resetItems, resetWidgets);
		}
	}

	private JPanel setupTitlePanel()
	{
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		titlePanel.setLayout(new BorderLayout());

		JTextArea title = JGenerator.makeJTextArea("Clue Details");
		title.setForeground(Color.WHITE);
		titlePanel.add(title, BorderLayout.WEST);

		return titlePanel;
	}
	private void setupSearchBar()
	{
		/* Search bar */
		searchBar.setIcon(IconTextField.Icon.SEARCH);
		searchBar.setPreferredSize(new Dimension(PluginPanel.PANEL_WIDTH - 20, 30));
		searchBar.setBackground(ColorScheme.DARKER_GRAY_COLOR);
		searchBar.setHoverBackgroundColor(ColorScheme.DARK_GRAY_HOVER_COLOR);
		searchBar.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e)
			{
				onSearchBarChanged();
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				onSearchBarChanged();
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				onSearchBarChanged();
			}
		});

		searchCluesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		searchCluesPanel.setLayout(new BorderLayout(0, BORDER_OFFSET));
		searchCluesPanel.add(searchBar, BorderLayout.CENTER);
	}

	private JPanel setupImportExportButtons()
	{
		// Import/Export Options
		JPanel markerButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 7, 3));

		resetMarkers.setToolTipText("Reset currently filtered customised details");
		JPopupMenu resetPopupMenu = getResetPopupMenu();
		resetMarkers.setComponentPopupMenu(resetPopupMenu);
		resetMarkers.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				if (SwingUtilities.isLeftMouseButton(e))
				{
					openResetPopup(true, true, true, true);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				resetMarkers.setIcon(RESET_HOVER_ICON);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				resetMarkers.setIcon(RESET_ICON);
			}
		});

		copyMarkers.setToolTipText("Export currently filtered details to your clipboard");
		JPopupMenu copyPopupMenu = getCopyPopupMenu();
		copyMarkers.setComponentPopupMenu(copyPopupMenu);
		copyMarkers.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				if (SwingUtilities.isLeftMouseButton(e))
				{
					clueDetailsSharingManager.exportClueDetails(true, true, true, true);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				copyMarkers.setIcon(COPY_HOVER_ICON);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				copyMarkers.setIcon(COPY_ICON);
			}
		});

		pasteMarkers.setToolTipText("Import details from your clipboard");
		pasteMarkers.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				clueDetailsSharingManager.promptForImport();
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				pasteMarkers.setIcon(PASTE_HOVER_ICON);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				pasteMarkers.setIcon(PASTE_ICON);
			}
		});

		markerButtons.add(resetMarkers);
		markerButtons.add(pasteMarkers);
		markerButtons.add(copyMarkers);

		return markerButtons;
	}

	private void setupFilters()
	{
		tierFilterDropdown = makeNewDropdown(ClueDetailsConfig.ClueTierFilter.displayFilters(), "filterListByTier");
		JPanel filtersTierPanel = makeDropdownPanel(tierFilterDropdown, "Tier");
		filtersTierPanel.setPreferredSize(new Dimension(PANEL_WIDTH, DROPDOWN_HEIGHT));

		regionFilterDropdown = makeNewDropdown(ClueDetailsConfig.ClueRegionFilter.displayFilters(), "filterListByRegion");
		JPanel filtersRegionPanel = makeDropdownPanel(regionFilterDropdown, "Region");
		filtersRegionPanel.setPreferredSize(new Dimension(PANEL_WIDTH, DROPDOWN_HEIGHT));

		orderDropdown = makeNewDropdown(ClueDetailsConfig.ClueOrdering.values(), "orderListBy");
		JPanel orderPanel = makeDropdownPanel(orderDropdown, "Ordering");
		orderPanel.setPreferredSize(new Dimension(PANEL_WIDTH, DROPDOWN_HEIGHT));

		allDropdownSections.setLayout(new BoxLayout(allDropdownSections, BoxLayout.Y_AXIS));
		allDropdownSections.setBorder(new EmptyBorder(0, 0, 10, 0));
		allDropdownSections.add(filtersTierPanel);
		allDropdownSections.add(filtersRegionPanel);
		allDropdownSections.add(orderPanel);

		searchCluesPanel.add(allDropdownSections, BorderLayout.NORTH);
	}

	private JPopupMenu getResetPopupMenu()
	{
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem inputItemResetText = new JMenuItem("Reset clue text");
		inputItemResetText.addActionListener(event
			-> openResetPopup(true, false, false, false)
		);
		popupMenu.add(inputItemResetText);

		JMenuItem inputItemResetColors = new JMenuItem("Reset clue colors");
		inputItemResetColors.addActionListener(event
			-> openResetPopup(false, true, false, false)
		);
		popupMenu.add(inputItemResetColors);

		JMenuItem inputItemResetItems = new JMenuItem("Reset clue items");
		inputItemResetItems.addActionListener(event
			-> openResetPopup(false, false, true, false)
		);
		popupMenu.add(inputItemResetItems);

		JMenuItem inputItemResetWidgets = new JMenuItem("Reset clue widgets");
		inputItemResetWidgets.addActionListener(event
			-> openResetPopup(false, false, false, true)
		);
		popupMenu.add(inputItemResetWidgets);

		return popupMenu;
	}

	private JPopupMenu getCopyPopupMenu()
	{
		JPopupMenu popupMenu = new JPopupMenu();

		JMenuItem inputItemExportText = new JMenuItem("Export clue text");
		inputItemExportText.addActionListener(event
			-> clueDetailsSharingManager.exportClueDetails(true, false, false, false)
		);
		popupMenu.add(inputItemExportText);

		JMenuItem inputItemExportColors = new JMenuItem("Export clue colors");
		inputItemExportColors.addActionListener(event
			-> clueDetailsSharingManager.exportClueDetails(false, true, false, false)
		);
		popupMenu.add(inputItemExportColors);

		JMenuItem inputItemExportItems = new JMenuItem("Export clue items");
		inputItemExportItems.addActionListener(event
			-> clueDetailsSharingManager.exportClueDetails(false, false, true, false)
		);
		popupMenu.add(inputItemExportItems);

		JMenuItem inputItemExportWidgets = new JMenuItem("Export clue widgets");
		inputItemExportWidgets.addActionListener(event
			-> clueDetailsSharingManager.exportClueDetails(false, false, false, true)
		);
		popupMenu.add(inputItemExportWidgets);

		return popupMenu;
	}

	private JComboBox<Enum> makeNewDropdown(Enum[] values, String key)
	{
		JComboBox<Enum> dropdown = new JComboBox<>(values);
		dropdown.setFocusable(false);
		dropdown.setForeground(Color.WHITE);
		dropdown.setRenderer(new DropdownRenderer());
		dropdown.addItemListener(e ->
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				Enum source = (Enum) e.getItem();
				configManager.setConfiguration(ClueDetailsConfig.class.getAnnotation(ConfigGroup.class).value(), key,
					source);
			}
		});

		return dropdown;
	}

	private JPanel makeDropdownPanel(JComboBox dropdown, String name)
	{
		// Filters
		JTextArea filterName = JGenerator.makeJTextArea(name);
		filterName.setForeground(Color.WHITE);

		JPanel filtersPanel = new JPanel();
		filtersPanel.setLayout(new BorderLayout());
		filtersPanel.setBorder(new EmptyBorder(0, 0, BORDER_OFFSET, 0));
		filtersPanel.setMinimumSize(new Dimension(PANEL_WIDTH, BORDER_OFFSET));
		filtersPanel.add(filterName, BorderLayout.CENTER);
		filtersPanel.add(dropdown, BorderLayout.EAST);

		return filtersPanel;
	}

	private void showMatchingClues(String searchText)
	{
		final String[] searchTerms = searchText.toLowerCase().split("\\s+");

		List<ListItem> filteredItems = new ArrayList<>();
		ListItem header = null;
		for (ListItem item : allClues)
		{
			if (item.isHeader())
			{
				header = item;
			}
			else
			{
				Clues clue = item.getClue();
				List<String> keywords = new ArrayList<>();
				keywords.add(clue.getDetail(configManager).toLowerCase());
				keywords.add(Integer.toString(clue.getClueID()));

				boolean matches = Arrays.stream(searchTerms)
					.allMatch(term -> keywords.stream().anyMatch(keyword -> keyword.contains(term)));

				if (matches)
				{
					// Only add header if search result contains items for that header
					if (header != null)
					{
						filteredItems.add(header);
						header = null;
					}
					filteredItems.add(item);
				}
			}
		}

		updateClueList(filteredItems);
	}

	private void onSearchBarChanged()
	{
		final String text = searchBar.getText();
		showMatchingClues(text);
	}

	public void refresh()
	{
		SwingWorker<List<ListItem>, Void> worker = new SwingWorker<>()
		{
			@Override
			protected List<ListItem> doInBackground()
			{
				// Update dropdowns to match current config
				tierFilterDropdown.setSelectedItem(
					ClueTierFilter.valueOf(configManager.getConfiguration(GROUP, "filterListByTier")));
				regionFilterDropdown.setSelectedItem(
					ClueRegionFilter.valueOf(configManager.getConfiguration(GROUP, "filterListByRegion")));
				orderDropdown.setSelectedItem(
					ClueOrdering.valueOf(configManager.getConfiguration(GROUP, "orderListBy")));

				List<Clues> filteredClues = Clues.CLUES.stream()
					.filter(config.filterListByTier())
					.filter(config.filterListByRegion())
					.filter(ClueDetailsParentPanel.this::filterUnmarkedClues)
					.sorted(config.orderListBy())
					.collect(Collectors.toList());

				ClueFilter[] sections = config.orderListBy().getSections();

				List<ListItem> items = new ArrayList<>();

				for (ClueFilter section : sections)
				{
					List<Clues> filterList = filteredClues.stream()
						.filter(section)
						.collect(Collectors.toList());

					if (!filterList.isEmpty())
					{
						items.add(new ListItem(section.getDisplayName()));
						for (Clues clue : filterList)
						{
							items.add(new ListItem(clue));
						}
					}
				}
				return items;
			}

			@Override
			protected void done()
			{
				try
				{
					List<ListItem> items = get();
					allClues = items;
					String searchText = searchBar.getText() != null ? searchBar.getText() : "";
					showMatchingClues(searchText);
				}
				catch (InterruptedException | ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		};
		worker.execute();
	}

	private void updateClueList(List<ListItem> items)
	{
		SwingUtilities.invokeLater(() ->
		{
			clueTableModel.setItems(items);
		});
	}

	public boolean filterUnmarkedClues(Clues clue)
	{
		if (!config.onlyShowMarkedClues()) return true;
		return cluePreferenceManager.getHighlightPreference(clue.getClueID());
	}

	private RuneliteColorPicker getColorPicker(Color color)
	{
		RuneliteColorPicker colorPicker = plugin.getColorPickerManager().create(
			SwingUtilities.windowForComponent(this),
			color,
			"Edit Clue Detail Color",
			true);
		colorPicker.setLocationRelativeTo(this);
		return colorPicker;
	}

	public ChatboxItemSearch getItemSearch(String tooltip)
	{
		return plugin.getItemSearch()
			.tooltipText(tooltip);
	}

	private void sendChatMessage(final String message)
	{
		plugin.getChatMessageManager().queue(QueuedMessage.builder()
			.type(ChatMessageType.CONSOLE)
			.runeLiteFormattedMessage(message)
			.build());
	}
}
