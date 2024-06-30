package frontend;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import backend.Item;
import backend.User.Manager;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Close;
import frontend.UIcomponents.Design_PanelBorder;
import frontend.UIcomponents.Design_SearchText;
import frontend.UIcomponents.Design_btnSidebar;
import frontend.UIcomponents.Minimize;
import frontend.UIcomponents.RoundedBorderPanel;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.Sidebar;
import frontend.UIcomponents.TableFormats;

import javax.swing.border.MatteBorder;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class Inventory_Frame extends JFrame {

	private Image img_Search = new ImageIcon(Login.class.getResource("resources/SearchUser.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_Add = new ImageIcon(Login.class.getResource("resources/Add.png")).getImage().getScaledInstance(25,
			25,
			Image.SCALE_SMOOTH);
	private Image img_Delete = new ImageIcon(Login.class.getResource("resources/Delete.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_Update = new ImageIcon(Login.class.getResource("resources/Update.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

	private JPanel Content, pnlSidebar;
	private JLabel lblAdd, lblUpdate, lblDelete;
	protected RoundedClearpnl btnUndo;
	Design_btnSidebar btnHome;
	Design_btnSidebar btnSchedule;
	Design_btnSidebar btnManageUser;
	Design_btnSidebar btnInventory;
	Design_btnSidebar btnLogout;
	protected CustomTable tableItems;
	private Design_SearchText tfSearch;
	private JScrollPane spItems;

	public Inventory_Frame(Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 700);

		Content = new JPanel();
		Content.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Content);
		Content.setLayout(null);

		pnlSidebar = new Sidebar(manager, this);
		pnlSidebar.setBounds(0, 0, 300, 700);
		pnlSidebar.setBackground(new Color(71, 180, 206));
		Content.add(pnlSidebar);

		JPanel pnlContent = new JPanel();
		pnlContent.setBackground(new Color(224, 224, 224));
		pnlContent.setBounds(300, 0, 980, 700);
		Content.add(pnlContent);

		Design_Close lbl_Close = new Design_Close("Close", 0, this, new Color(224, 224, 224));
		lbl_Close.setText("");
		lbl_Close.setBounds(950, 0, 30, 30);
		pnlContent.setLayout(null);
		pnlContent.add(lbl_Close);

		Minimize lbl_Minimize = new Minimize("Minimize", 0, this, new Color(224, 224, 224), new Color(227, 244, 244),
				Color.white);
		lbl_Minimize.setText("");
		lbl_Minimize.setBounds(920, 0, 30, 30);
		pnlContent.setLayout(null);
		pnlContent.add(lbl_Minimize);

		Design_PanelBorder containerInventory = new Design_PanelBorder();
		containerInventory.setBackground(Color.WHITE);
		containerInventory.setBounds(30, 30, 920, 640);
		pnlContent.add(containerInventory);

		tableItems = new CustomTable(4, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if (dataModel.getColumnCount() > 0)
					TableFormats.toInventoryTable(this);
			}
		};

		tableItems.setModel(manager.viewFullInventory());
		spItems = new JScrollPane(tableItems, 20, 31);
		tableItems.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int j = tableItems.getSelectedRow();
				TableModel table_model = tableItems.getModel();
				int id = Integer.parseInt(table_model.getValueAt(j, 0).toString());
				String desc = table_model.getValueAt(j, 1).toString();
				int qty = Integer.parseInt(table_model.getValueAt(j, 2).toString());
				double wprice = Double.parseDouble(table_model.getValueAt(j, 3).toString());
				double rprice = Double.parseDouble(table_model.getValueAt(j, 4).toString());

				item = new Item(id, desc, qty, wprice, rprice);
			}
		});
		spItems.setBounds(20, 135, 880, 485);
		containerInventory.add(spItems);

		JPanel pnlOperations = new JPanel();
		pnlOperations.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		pnlOperations.setBackground(Color.WHITE);
		pnlOperations.setBounds(0, 15, 920, 100);
		containerInventory.add(pnlOperations);
		pnlOperations.setLayout(null);

		RoundedBorderPanel btnAdd = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddItem_Frame add = new AddItem_Frame(manager, Inventory_Frame.this);
				add.setVisible(true);
			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(10, 49, 120, 40);
		pnlOperations.add(btnAdd);
		btnAdd.setLayout(null);

		lblAdd = new JLabel("Add Item");
		lblAdd.setForeground(Color.WHITE);
		lblAdd.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblAdd.setBounds(47, 5, 70, 30);
		btnAdd.add(lblAdd);

		JLabel iconAdd = new JLabel("");
		iconAdd.setHorizontalAlignment(SwingConstants.CENTER);
		iconAdd.setBounds(13, 5, 40, 30);
		iconAdd.setIcon(new ImageIcon(img_Add));
		btnAdd.add(iconAdd);

		RoundedSearchPnl pnlSearch = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlSearch.setBackground(Color.WHITE);
		pnlSearch.setBounds(660, 49, 250, 40);
		pnlOperations.add(pnlSearch);
		pnlSearch.setLayout(null);

		tfSearch = new Design_SearchText();
		tfSearch.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String search = tfSearch.getText();
				if (search.equals(""))
					tableItems.setModel(manager.viewFullInventory());
				else {
					try {
						tableItems.setModel(manager.searchItem(search));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "A search problem has occured!\n" + e1.getMessage(),
								"Search Item", 0);
					}
				}
			}
		});

		tfSearch.setForeground(new Color(106, 106, 106));
		tfSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfSearch.setBorder(null);
		tfSearch.setBounds(10, 5, 200, 30);
		pnlSearch.add(tfSearch);
		tfSearch.setColumns(10);

		JLabel iconSearch = new JLabel("");
		iconSearch.setHorizontalAlignment(SwingConstants.CENTER);
		iconSearch.setBounds(210, 5, 40, 30);
		iconSearch.setIcon(new ImageIcon(img_Search));
		pnlSearch.add(iconSearch);

		RoundedBorderPanel btnUpdate = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (item == null)
					JOptionPane.showMessageDialog(null, "No row selected", "Update Item", 2);
				else {
					UpdateItem_Frame add = new UpdateItem_Frame(manager, Inventory_Frame.this, item);
					add.setVisible(true);
					item = null;
				}
			}
		});
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setLayout(null);
		btnUpdate.setBounds(140, 49, 120, 40);
		pnlOperations.add(btnUpdate);

		lblUpdate = new JLabel("Update");
		lblUpdate.setForeground(Color.WHITE);
		lblUpdate.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUpdate.setBounds(50, 5, 70, 30);
		btnUpdate.add(lblUpdate);

		JLabel iconUpdate = new JLabel("");
		iconUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		iconUpdate.setBounds(15, 5, 40, 30);
		iconUpdate.setIcon(new ImageIcon(img_Update));
		btnUpdate.add(iconUpdate);

		RoundedClearpnl btnDelete = new RoundedClearpnl(40, 0, new Color(225, 127, 107), new Color(220, 144, 127));
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setLayout(null);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(270, 49, 120, 40);
		btnDelete.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (item == null)
					JOptionPane.showMessageDialog(null, "No row selected", "Remove Item", 2);
				else {
					int confirm = JOptionPane.showConfirmDialog(null,
							"Deleting this item will remove it permanently. Do you want to continue?\nWarning: All related data will be deleted as well");
					if (confirm == 0) {
						manager.deleteItem(item);
						tableItems.setModel(manager.viewFullInventory());
						item = null;
					}
				}
			}
		});
		pnlOperations.add(btnDelete);

		lblDelete = new JLabel("Delete");
		lblDelete.setForeground(Color.WHITE);
		lblDelete.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblDelete.setBounds(50, 5, 70, 30);
		btnDelete.add(lblDelete);

		JLabel iconDelete = new JLabel("");
		iconDelete.setHorizontalAlignment(SwingConstants.CENTER);
		iconDelete.setBounds(15, 5, 40, 30);
		iconDelete.setIcon(new ImageIcon(img_Delete));
		btnDelete.add(iconDelete);
		//
		// JPanel pnlFooter = new JPanel();
		// pnlFooter.setBackground(new Color(255, 255, 255));
		// pnlFooter.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(192, 192,
		// 192)));
		// pnlFooter.setBounds(0, 580, 920, 50);
		// pnlTable.add(pnlFooter);
		// pnlFooter.setLayout(null);
		//
		// RoundedBorderPanel btnAddStock = new RoundedBorderPanel(40, 0, new Color(71,
		// 180, 206));
		// btnAddStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// btnAddStock.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// if(item == null)
		// JOptionPane.showMessageDialog(null, "No row selected", "Update Item", 2);
		// else {
		// ManageStock add = new ManageStock (manager, Inventory_Frame.this, item);
		// add.setVisible(true);
		//
		// //reset undo button and disable
		// btnUndo.setDate(null); btnUndo.setItem(null);
		// btnUndo.setEnabledpanelbutton(false);
		// item = null;
		// }
		// }
		// });
		// btnAddStock.setBounds(10, 9, 138, 40);
		// pnlFooter.add(btnAddStock);
		// btnAddStock.setLayout(null);
		// btnAddStock.setBackground(Color.WHITE);
		//
		// lblAddStock = new JLabel("Manage Stock");
		// lblAddStock.setHorizontalAlignment(SwingConstants.CENTER);
		// lblAddStock.setForeground(Color.WHITE);
		// lblAddStock.setFont(new Font("SansSerif", Font.BOLD, 16));
		// lblAddStock.setBounds(10, 5, 121, 30);
		// btnAddStock.add(lblAddStock);
		//
		// RoundedBorderPanel btnStockintable = new RoundedBorderPanel(40, 0, new
		// Color(71, 180, 206));
		// btnStockintable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// btnStockintable.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// new Stockintable(manager).setVisible(true);
		// }
		// });
		// btnStockintable.setBounds(158, 9, 138, 40);
		// pnlFooter.add(btnStockintable);
		// btnStockintable.setLayout(null);
		// btnStockintable.setBackground(Color.WHITE);
		//
		// lblStockintable = new JLabel("Stock in Table");
		// lblStockintable.setHorizontalAlignment(SwingConstants.CENTER);
		// lblStockintable.setForeground(Color.WHITE);
		// lblStockintable.setFont(new Font("SansSerif", Font.BOLD, 16));
		// lblStockintable.setBounds(10, 5, 118, 30);
		// btnStockintable.add(lblStockintable);
		//
		// RoundedBorderPanel btnStockouttable = new RoundedBorderPanel(40, 0, new
		// Color(71, 180, 206));
		// btnStockouttable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// btnStockouttable.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// new Stockouttable(manager).setVisible(true);
		// }
		// });
		// btnStockouttable.setLayout(null);
		// btnStockouttable.setBackground(Color.WHITE);
		// btnStockouttable.setBounds(306, 9, 145, 40);
		// pnlFooter.add(btnStockouttable);
		//
		// lblStockouttable = new JLabel("Stock out Table");
		// lblStockouttable.setHorizontalAlignment(SwingConstants.CENTER);
		// lblStockouttable.setForeground(Color.WHITE);
		// lblStockouttable.setFont(new Font("SansSerif", Font.BOLD, 16));
		// lblStockouttable.setBounds(10, 5, 125, 30);
		// btnStockouttable.add(lblStockouttable);
		//
		// btnUndo = new RoundedClearpnl(40, 0, new Color(225,127,107), new Color(220,
		// 144, 127));
		// btnUndo.addMouseListener(new MouseAdapter() {
		//
		// public void mouseClicked(MouseEvent e) {
		//
		// try {
		// if (btnUndo.isEnabledpanelbutton()) {
		// //get data from add stock frame
		// Date undo_date = btnUndo.getDate();
		// Item undo_item = btnUndo.getItem();
		//
		// manager.undoAddStock(undo_date, undo_item);
		//
		// //disable undo button
		// btnUndo.setEnabledpanelbutton(false);
		//
		// table.setModel(manager.viewInventory());
		// } else
		// JOptionPane.showMessageDialog(null, "No recent changes");
		//
		// } catch (Exception e1) {
		// JOptionPane.showMessageDialog(null, "something went
		// wrong!\n"+e1.getMessage());
		// }
		// }
		// });
		// btnUndo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// btnUndo.setLayout(null);
		// btnUndo.setBackground(Color.WHITE);
		// btnUndo.setBounds(772, 9, 138, 40);
		// pnlFooter.add(btnUndo);
		//
		// JLabel lblUndo = new JLabel("Undo");
		// lblUndo.setHorizontalAlignment(SwingConstants.CENTER);
		// lblUndo.setForeground(Color.WHITE);
		// lblUndo.setFont(new Font("SansSerif", Font.BOLD, 16));
		// lblUndo.setBounds(10, 5, 121, 30);
		// btnUndo.add(lblUndo);

		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	private Item item;
}
