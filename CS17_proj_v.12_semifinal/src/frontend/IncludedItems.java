package frontend;

import java.awt.Color;
import java.awt.Cursor;

import backend.Item;
import backend.Event.EventPackage;
import backend.Event.EventPackage.IncludedItem;

import java.awt.Font;
import java.awt.Image;

import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import backend.User.Manager;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.Design_SearchText;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;

import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

public class IncludedItems extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfSearch;
	protected CustomTable tableItems, viewItems;
	private JScrollPane spItems, spViewItems;

	private Image img_Search = new ImageIcon(Login.class.getResource("resources/SearchUser.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private JTextField tfItem;
	private JTextField tfQuantity;
	private JTextField tfTotal;;

	/**
	 * Create the frame.
	 */
	public IncludedItems(Manager manager, EventPackages_Frame frame, int eventPackageID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 460);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 2));
		pnlContent.setBackground(new Color(224, 224, 224));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		JPanel containerAddItems = new JPanel();
		containerAddItems.setBackground(new Color(240, 240, 240));
		containerAddItems.setBounds(10, 10, 930, 440);
		pnlContent.add(containerAddItems);
		containerAddItems.setLayout(null);

		RoundedClearpnl pnlItemContainer = new RoundedClearpnl(15, 3, new Color(255, 255, 255),
				new Color(255, 255, 255));
		pnlItemContainer.setBounds(5, 5, 450, 430);
		containerAddItems.add(pnlItemContainer);
		pnlItemContainer.setLayout(null);

		JPanel pnlItemContainer_Header = new JPanel();
		pnlItemContainer_Header.setBounds(0, 10, 450, 75);
		pnlItemContainer_Header.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlItemContainer_Header.setBackground(new Color(255, 255, 255));
		pnlItemContainer.add(pnlItemContainer_Header);
		pnlItemContainer_Header.setLayout(null);

		RoundedSearchPnl pnlSearch = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlSearch.setBackground(Color.WHITE);
		pnlSearch.setBounds(190, 28, 250, 40);
		pnlSearch.setLayout(null);
		pnlItemContainer_Header.add(pnlSearch);

		tfSearch = new Design_SearchText();
		tfSearch.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					if (tfSearch.getText().equals("")) {
						tableItems.setModel(manager.viewQuarterInventory());
					} else {
						tableItems.setModel(manager.searchQuarterItem(tfSearch.getText()));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		tfSearch.setForeground(new Color(106, 106, 106));
		tfSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfSearch.setBorder(null);
		tfSearch.setBounds(10, 10, 200, 20);
		pnlSearch.add(tfSearch);
		tfSearch.setColumns(10);

		JLabel iconSearch = new JLabel("");
		iconSearch.setHorizontalAlignment(SwingConstants.CENTER);
		iconSearch.setBounds(210, 5, 40, 30);
		iconSearch.setIcon(new ImageIcon(img_Search));
		pnlSearch.add(iconSearch);

		JLabel lblItemHeader = new JLabel("  Add Item");
		lblItemHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemHeader.setForeground(new Color(102, 102, 102));
		lblItemHeader.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblItemHeader.setBounds(0, 28, 188, 40);
		pnlItemContainer_Header.add(lblItemHeader);

		Design_Back lbl_Back = new Design_Back("Back", 50, this, new Color(255, 255, 255));
		lbl_Back.setBounds(0, 0, 40, 40);
		pnlItemContainer_Header.add(lbl_Back);
		lbl_Back.setText("");

		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlTable.setBackground(new Color(255, 255, 255));
		pnlTable.setBounds(0, 84, 450, 202);
		pnlItemContainer.add(pnlTable);
		pnlTable.setLayout(null);

		tableItems = new CustomTable(4, 14);
		spItems = new JScrollPane(tableItems, 20, 31);
		tableItems.setModel(manager.viewQuarterInventory());
		tableItems.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				int i = tableItems.getSelectedRow();
				TableModel table_model = tableItems.getModel();
				String itemdesc = table_model.getValueAt(i, 0).toString();
				double price = Double.parseDouble(table_model.getValueAt(i, 1).toString());

				TableModel item_model = manager.searchItem(itemdesc);
				int item_id = Integer.parseInt(item_model.getValueAt(0, 0).toString());
				selectedItem = new Item(item_id, itemdesc, price);
				tfItem.setText(itemdesc);
			}
		});
		spItems.setBounds(5, 10, 435, 182);
		pnlTable.add(spItems);

		RoundedSubmit btnAddItem = new RoundedSubmit(40, 0, new Color(71, 180, 206), new Color(107, 195, 215));
		btnAddItem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (tfItem.getText().equals("") || tfQuantity.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "", 2);
					else {
						String itemdesc = selectedItem.getDesc();
						int quantity = Integer.parseInt(tfQuantity.getText());
						double price = selectedItem.getRetail_price();

						includedItem = new IncludedItem(new EventPackage(eventPackageID),
								selectedItem, quantity);

						if (manager.searchIncludedItem(includedItem.getId(), eventPackageID).getRowCount() != 0
								|| includedItemList.contains(includedItem)) {
							JOptionPane.showMessageDialog(null, "Already Added");
						} else {
							if (quantity != 0) {
								includeTable_model.addRow(new Object[] {
										itemdesc,
										quantity,
										price
								});
								includedItemList.add(includedItem);
							}
							tfTotal.setText(df.format(calculateTotalAmount()));
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnAddItem.setBounds(320, 365, 120, 40);
		pnlItemContainer.add(btnAddItem);
		btnAddItem.setLayout(null);
		btnAddItem.setBorder(null);
		// new AdditionalItem(0, null, 0, 0, 0);
		JLabel lblAddItem = new JLabel("Add Item");
		lblAddItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddItem.setForeground(Color.WHITE);
		lblAddItem.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblAddItem.setBounds(10, 0, 100, 40);
		btnAddItem.add(lblAddItem);

		RoundedSearchPnl pnlItem = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlItem.setBackgroundColor(Color.WHITE);
		pnlItem.setBackground(new Color(192, 192, 192));
		pnlItem.setLayout(null);
		pnlItem.setBounds(91, 310, 210, 40);
		pnlItemContainer.add(pnlItem);

		tfItem = new JTextField();
		tfItem.setEditable(false);
		tfItem.setOpaque(true);
		tfItem.setBackground(Color.WHITE);
		tfItem.setForeground(Color.DARK_GRAY);
		tfItem.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfItem.setColumns(10);
		tfItem.setBorder(null);
		tfItem.setBounds(10, 5, 190, 30);
		pnlItem.add(tfItem);

		JLabel lblItem = new JLabel("Item Name");
		lblItem.setForeground(new Color(102, 102, 102));
		lblItem.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblItem.setBounds(10, 310, 93, 40);
		pnlItemContainer.add(lblItem);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(new Color(102, 102, 102));
		lblQuantity.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblQuantity.setBounds(10, 365, 93, 40);
		pnlItemContainer.add(lblQuantity);

		RoundedSearchPnl pnlQuantity = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlQuantity.setLayout(null);
		pnlQuantity.setBounds(91, 365, 170, 40);
		pnlItemContainer.add(pnlQuantity);

		tfQuantity = new JTextField();
		tfQuantity.setForeground(Color.DARK_GRAY);
		tfQuantity.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfQuantity.setColumns(10);
		tfQuantity.setBorder(null);
		tfQuantity.setBounds(10, 5, 150, 30);
		pnlQuantity.add(tfQuantity);
		//
		RoundedClearpnl pnlIncludedItems = new RoundedClearpnl(15, 3, new Color(255, 255, 255),
				new Color(255, 255, 255));
		pnlIncludedItems.setBounds(465, 5, 455, 430);
		containerAddItems.add(pnlIncludedItems);
		pnlIncludedItems.setLayout(null);

		JPanel pnlIncItem_Header = new JPanel();
		pnlIncItem_Header.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlIncItem_Header.setBackground(new Color(255, 255, 255));
		pnlIncItem_Header.setBounds(0, 0, 455, 85);
		pnlIncludedItems.add(pnlIncItem_Header);
		pnlIncItem_Header.setLayout(null);

		JLabel lblIncludedItem = new JLabel("Additional Items");
		lblIncludedItem.setBounds(0, 0, 455, 85);
		pnlIncItem_Header.add(lblIncludedItem);
		lblIncludedItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncludedItem.setForeground(new Color(102, 102, 102));
		lblIncludedItem.setFont(new Font("SansSerif", Font.BOLD, 24));

		JPanel pnlViewItems = new JPanel();
		pnlViewItems.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlViewItems.setBackground(new Color(255, 255, 255));
		pnlViewItems.setBounds(0, 85, 455, 202);
		pnlIncludedItems.add(pnlViewItems);
		pnlViewItems.setLayout(null);

		viewItems = new CustomTable(4, 14);
		includeTable_model = manager.viewIncludedItems(eventPackageID);
		viewItems.setModel(includeTable_model);
		spViewItems = new JScrollPane(viewItems, 20, 31);
		// tableItems.addMouseListener(new MouseAdapter() {
		//
		// public void mousePressed(MouseEvent e) {
		// int i = tableEventPackage.getSelectedRow();
		// TableModel table_model = tableEventPackage.getModel();
		// int eventID = Integer.parseInt(table_model.getValueAt(i,0).toString());
		// String eventname = table_model.getValueAt(i, 1).toString();
		// int eventPackageID = Integer.parseInt(table_model.getValueAt(i,
		// 2).toString());
		// String desc = table_model.getValueAt(i, 3).toString();
		// double price = Double.parseDouble(table_model.getValueAt(i, 4).toString());
		//
		// ep = new EventPackage(eventPackageID, desc, price, new Event(eventID,
		// eventname));
		// }
		// });
		spViewItems.setBounds(5, 10, 445, 182);
		pnlViewItems.add(spViewItems);

		RoundedSubmit btnRemove = new RoundedSubmit(50, 0, new Color(225, 127, 107), new Color(220, 144, 127));
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (viewItems.getSelectedRow() == -1)
						JOptionPane.showMessageDialog(null, "No included item has been selected");
					else {
						int i = viewItems.getSelectedRow();
						String iitemdesc = includeTable_model.getValueAt(i, 0).toString();
						int iiquantity = Integer.parseInt(includeTable_model.getValueAt(i, 1).toString());
						TableModel searchItem = manager.searchItem(iitemdesc);
						int itemID = Integer.parseInt(searchItem.getValueAt(0, 0).toString());
						includedItem = new IncludedItem(new EventPackage(eventPackageID),
								new Item(itemID), iiquantity);
						if (!includedItemList.contains(includedItem)) {
							manager.deleteIncludedItem(includedItem);
							frame.includeTable_model = manager.viewIncludedItems(eventPackageID);
							frame.tableItems.setModel(frame.includeTable_model);
							frame.lblPriceHere.setText(df.format(calculateTotalAmount()));
						} else
							includedItemList.remove(includedItem);
						includeTable_model.removeRow(i);
						tfTotal.setText(df.format(calculateTotalAmount()));
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnRemove.setLayout(null);
		btnRemove.setBounds(25, 360, 160, 45);
		pnlIncludedItems.add(btnRemove);

		JLabel lblRemove = new JLabel("Remove Item");
		lblRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemove.setForeground(Color.WHITE);
		lblRemove.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblRemove.setBounds(10, 0, 140, 45);
		btnRemove.add(lblRemove);

		RoundedSubmit btnConfirm = new RoundedSubmit(50, 0, new Color(71, 180, 206), new Color(107, 195, 215));
		btnConfirm.setBorder(null);
		btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (includedItemList.size() != 0) {
						IncludedItem[] itemsInclude = new IncludedItem[includedItemList.size()];
						for (int i = 0; i < includedItemList.size(); i++) {
							itemsInclude[i] = includedItemList.get(i);
						}
						manager.createIncludedItems(itemsInclude);
						frame.includeTable_model = manager.viewIncludedItems(eventPackageID);
						frame.tableItems.setModel(frame.includeTable_model);
						frame.lblPriceHere.setText(df.format(calculateTotalAmount()));
						includedItemList.clear();
						includedItem = null;
						selectedItem = null;
					}
					IncludedItems.this.dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
			// @Override
			// public void mouseClicked(MouseEvent e) {
			// try {
			// if (tfConfirmment.getText().equals("")) {
			// JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "", 2);
			// } else {
			// amount = Double.parseDouble(tfTotal.getText());
			// Confirmment = Double.parseDouble(tfConfirmment.getText());
			// if (amount != 0 && Confirmment != 0) {
			// if (Confirmment >= amount) {
			// Confirm_Frame frame = new Confirm_Frame(emp, amount, Confirmment,
			// includeTable_model, orderList);
			// frame.setVisible(true);
			// } else
			// JOptionPane.showMessageDialog(null, "Insufficient Confirmment");
			// }
			// }
			// } catch (NumberFormatException e1) {
			// JOptionPane.showMessageDialog(null, "Kindly enter fields correctly", "", 2);
			// } catch (Exception e1) {
			// JOptionPane.showMessageDialog(null, "Something went wrong!\n" +
			// e1.getMessage());
			// }

			// }
		});
		btnConfirm.setLayout(null);
		btnConfirm.setBounds(270, 360, 160, 45);
		pnlIncludedItems.add(btnConfirm);

		JLabel lblConfirm = new JLabel("Confirm");
		lblConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setForeground(Color.WHITE);
		lblConfirm.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblConfirm.setBounds(10, 0, 140, 45);
		btnConfirm.add(lblConfirm);

		JPanel pnlTotalPrice = new JPanel();
		pnlTotalPrice.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlTotalPrice.setBackground(new Color(255, 255, 255));
		pnlTotalPrice.setBounds(0, 285, 455, 51);
		pnlIncludedItems.add(pnlTotalPrice);
		pnlTotalPrice.setLayout(null);

		JLabel lblTotal = new JLabel("Total Amount");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(new Color(102, 102, 102));
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTotal.setBounds(69, 5, 147, 40);
		pnlTotalPrice.add(lblTotal);

		RoundedSearchPnl pnlTotal = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlTotal.setLayout(null);
		pnlTotal.setBounds(214, 5, 170, 40);
		pnlTotalPrice.add(pnlTotal);

		tfTotal = new JTextField();
		tfTotal.setText(df.format(calculateTotalAmount()));
		tfTotal.setEditable(false);
		tfTotal.setBackground(Color.WHITE);
		tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotal.setForeground(Color.DARK_GRAY);
		tfTotal.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfTotal.setColumns(10);
		tfTotal.setBorder(null);
		tfTotal.setBounds(10, 5, 150, 30);
		pnlTotal.add(tfTotal);

		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	private double calculateTotalAmount() {
		double totalAmount = 0;
		for (int j = 0; j < includeTable_model.getRowCount(); j++) {
			int quantity = Integer.parseInt(includeTable_model.getValueAt(j, 1).toString());
			double price = Double.parseDouble(includeTable_model.getValueAt(j, 2).toString());
			totalAmount += quantity * price;
		}
		return totalAmount;
	}

	private Item selectedItem = null;
	private IncludedItem includedItem = null;
	private ArrayList<IncludedItem> includedItemList = new ArrayList<IncludedItem>();
	private DefaultTableModel includeTable_model;
	private DecimalFormat df = new DecimalFormat("PHP 0.00");
}
