package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import backend.Item;
import backend.Item.StockIn;
import backend.Item.StockOut;
import backend.User.Manager;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Close;
import frontend.UIcomponents.Minimize;
import frontend.UIcomponents.RoundedBorderPanel;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;
import frontend.UIcomponents.Sidebar;

public class ManageStock_Frame extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageStock_Frame frame = new ManageStock_Frame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static final long serialVersionUID = 1L;
	private JPanel Content, pnlSidebar;
	RoundedClearpnl pnlClicked, pnlClicked1;
	RoundedClearpnl pnltableItems_SI, pnlDetail_SI, pnlOrder_SI, pnlOperations_SI;
	RoundedClearpnl pnltableItems_SO, pnlDetail_SO, pnlOrder_SO, pnlOperations_SO;
	private JLabel lblStockintable, lblStockouttable;;
	protected RoundedClearpnl btnUndo;
	protected CustomTable tableItems_SI, tableStockIn, tableItems_SO, tableStockOut;

	private Image img_AddStock = new ImageIcon(Login.class.getResource("resources/AddStock.png")).getImage()
			.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_RemoveStock = new ImageIcon(Login.class.getResource("resources/RemoveStock.png")).getImage()
			.getScaledInstance(35, 35, Image.SCALE_SMOOTH);

	protected JTextField tfQuantity_SI, tfQuantity_SO;
	protected JTextField tfItemname_SI, tfItemname_SO;
	private RoundedClearpnl btnConfirm_SI, btnConfirm_SO;

	protected DefaultTableModel stockin_model = new DefaultTableModel();
	protected DefaultTableModel stockout_model = new DefaultTableModel();

	/**
	 * Create the frame.
	 */
	public ManageStock_Frame(Manager manager) {
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
		pnlContent.setLayout(null);

		RoundedClearpnl pnlHeader = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlHeader.setBackground(new Color(255, 255, 255));
		pnlHeader.setBounds(10, 5, 965, 75);
		pnlContent.add(pnlHeader);
		pnlHeader.setLayout(null);

		Design_Close lbl_Close = new Design_Close("Close", 0, this, new Color(255, 255, 255));
		lbl_Close.setBounds(930, 0, 30, 30);
		pnlHeader.add(lbl_Close);
		lbl_Close.setText("");

		Minimize lbl_Minimize = new Minimize("Minimize", 0, this, new Color(255, 255, 255), new Color(227, 244, 244),
				Color.white);
		lbl_Minimize.setBounds(900, 0, 30, 30);
		pnlHeader.add(lbl_Minimize);
		lbl_Minimize.setText("");

		RoundedClearpnl btnStockIn = new RoundedClearpnl(50, 3, new Color(116, 140, 241), new Color(106, 120, 221));
		btnStockIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStockIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pnltableItems_SI.setVisible(true);
				pnltableItems_SI.setEnabled(true);
				pnlDetail_SI.setVisible(true);
				pnlDetail_SI.setEnabled(true);
				pnlOrder_SI.setVisible(true);
				pnlOrder_SI.setEnabled(true);
				pnlOperations_SI.setVisible(true);
				pnlOperations_SI.setEnabled(true);
				pnlClicked.setVisible(true);
				pnltableItems_SO.setVisible(false);
				pnltableItems_SO.setEnabled(false);
				pnlDetail_SO.setVisible(false);
				pnlDetail_SO.setEnabled(false);
				pnlOrder_SO.setVisible(false);
				pnlOrder_SO.setEnabled(false);
				pnlOperations_SO.setVisible(false);
				pnlOperations_SO.setEnabled(false);
				pnlClicked1.setVisible(false);
			}
		});
		btnStockIn.setLayout(null);
		btnStockIn.setBackground(new Color(116, 140, 241));
		btnStockIn.setBounds(140, 5, 200, 56);
		pnlHeader.add(btnStockIn);

		JLabel lblStockIn = new JLabel("Stock In");
		lblStockIn.setIcon(new ImageIcon(img_AddStock));
		lblStockIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockIn.setForeground(Color.WHITE);
		lblStockIn.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblStockIn.setBounds(15, 0, 165, 55);
		btnStockIn.add(lblStockIn);

		pnlClicked = new RoundedClearpnl(1, 3, new Color(230, 242, 247), new Color(230, 242, 247));
		pnlClicked.setVisible(true);
		pnlClicked.setBounds(150, 60, 180, 10);
		pnlHeader.add(pnlClicked);

		RoundedClearpnl btnStockOut = new RoundedClearpnl(50, 3, new Color(236, 37, 90), new Color(216, 7, 80));
		btnStockOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStockOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pnltableItems_SO.setVisible(true);
				pnltableItems_SO.setEnabled(true);
				pnlDetail_SO.setVisible(true);
				pnlDetail_SO.setEnabled(true);
				pnlOrder_SO.setVisible(true);
				pnlOrder_SO.setEnabled(true);
				pnlOperations_SO.setVisible(true);
				pnlOperations_SO.setEnabled(true);
				pnlClicked1.setVisible(true);
				pnltableItems_SI.setVisible(false);
				pnltableItems_SI.setEnabled(false);
				pnlDetail_SI.setVisible(false);
				pnlDetail_SI.setEnabled(false);
				pnlOrder_SI.setVisible(false);
				pnlOrder_SI.setEnabled(false);
				pnlOperations_SI.setVisible(false);
				pnlOperations_SI.setEnabled(false);
				pnlClicked.setVisible(false);
			}
		});
		btnStockOut.setLayout(null);
		btnStockOut.setBackground(new Color(236, 37, 90));
		btnStockOut.setBounds(620, 5, 200, 56);
		pnlHeader.add(btnStockOut);

		JLabel lblStockOut = new JLabel("Stock Out");
		lblStockOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockOut.setIcon(new ImageIcon(img_RemoveStock));
		lblStockOut.setForeground(Color.WHITE);
		lblStockOut.setFont(new Font("SansSerif", Font.BOLD, 23));
		lblStockOut.setBounds(15, 0, 175, 55);
		btnStockOut.add(lblStockOut);

		pnlClicked1 = new RoundedClearpnl(1, 3, new Color(248, 228, 233), new Color(248, 228, 233));
		pnlClicked1.setVisible(false);
		pnlClicked1.setBounds(630, 60, 180, 10);
		pnlHeader.add(pnlClicked1);

		// --------------------------------------------------------------------------------------------------------------------START
		// OF STOCK IN
		// CONTENT--------------------------------------------------------------------------------------------------------------------\\
		//
		pnltableItems_SI = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnltableItems_SI.setVisible(true);
		pnltableItems_SI.setEnabled(true);
		pnltableItems_SI.setBackground(new Color(255, 255, 255));
		pnltableItems_SI.setBounds(10, 90, 610, 480);
		pnlContent.add(pnltableItems_SI);
		pnltableItems_SI.setLayout(null);

		tableItems_SI = new CustomTable(4, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				// if(dataModel.getColumnCount() > 0)
				// TableFormats.
			}
		};
		tableItems_SI.setModel(manager.viewStockInventory());
		tableItems_SI.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					int i = tableItems_SI.getSelectedRow();
					TableModel item_model = tableItems_SI.getModel();
					int itemID = Integer.parseInt(item_model.getValueAt(i, 0).toString());
					String itemdesc = item_model.getValueAt(i, 1).toString();
					int currStock = Integer.parseInt(item_model.getValueAt(i, 2).toString());

					item_SI = new Item(itemID, itemdesc, currStock);
					tfItemname_SI.setText(itemdesc);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		JScrollPane spItem_SI = new JScrollPane(tableItems_SI, 20, 31);
		spItem_SI.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spItem_SI.setBounds(5, 65, 600, 404);
		pnltableItems_SI.add(spItem_SI);

		JPanel pnlItemTableHeader = new JPanel();
		pnlItemTableHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlItemTableHeader.setBackground(new Color(255, 255, 255));
		pnlItemTableHeader.setBounds(0, 5, 610, 50);
		pnltableItems_SI.add(pnlItemTableHeader);
		pnlItemTableHeader.setLayout(null);

		RoundedBorderPanel btnStockinTable = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnStockinTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStockinTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Stockintable(manager).setVisible(true);
			}
		});
		btnStockinTable.setBounds(430, 3, 170, 40);
		pnlItemTableHeader.add(btnStockinTable);
		btnStockinTable.setLayout(null);
		btnStockinTable.setBackground(Color.WHITE);

		lblStockintable = new JLabel("View Stock In Table");
		lblStockintable.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockintable.setForeground(Color.WHITE);
		lblStockintable.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblStockintable.setBounds(10, 0, 150, 40);
		btnStockinTable.add(lblStockintable);

		JLabel lblItems = new JLabel("  Items");
		lblItems.setBounds(0, 0, 605, 45);
		pnlItemTableHeader.add(lblItems);
		lblItems.setHorizontalTextPosition(SwingConstants.CENTER);
		lblItems.setHorizontalAlignment(SwingConstants.LEFT);
		lblItems.setForeground(new Color(106, 106, 106));
		lblItems.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblItems.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(18, 72, 107)));
		lblItems.setBackground(new Color(194, 194, 194));
		pnlContent.setLayout(null);

		pnlDetail_SI = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlDetail_SI.setVisible(true);
		pnlDetail_SI.setEnabled(true);
		pnlDetail_SI.setBackground(Color.WHITE);
		pnlDetail_SI.setBounds(10, 575, 610, 115);
		pnlContent.add(pnlDetail_SI);
		pnlDetail_SI.setLayout(null);

		JLabel lblItemname = new JLabel("Selected Item");
		lblItemname.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblItemname.setBounds(15, 20, 280, 25);
		pnlDetail_SI.add(lblItemname);

		RoundedSearchPnl pnlItemname = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlItemname.setBounds(15, 45, 280, 40);
		pnlDetail_SI.add(pnlItemname);
		pnlItemname.setLayout(null);

		tfItemname_SI = new JTextField();
		tfItemname_SI.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				try {
					if (!tfItemname_SI.getText().equals(""))
						tableItems_SI.setModel(manager.searchStockItem(tfItemname_SI.getText()));
					else
						tableItems_SI.setModel(manager.viewStockInventory());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tfItemname_SI.setForeground(Color.DARK_GRAY);
		tfItemname_SI.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfItemname_SI.setBackground(Color.WHITE);
		tfItemname_SI.setColumns(10);
		tfItemname_SI.setBorder(null);
		tfItemname_SI.setBounds(10, 5, 260, 30);
		pnlItemname.add(tfItemname_SI);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantity.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblQuantity.setBounds(320, 20, 83, 25);
		pnlDetail_SI.add(lblQuantity);

		RoundedClearpnl btnAddStock = new RoundedClearpnl(50, 3, new Color(116, 140, 241), new Color(106, 120, 221));
		btnAddStock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (tfItemname_SI.getText().equals("") || tfQuantity_SI.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields");
					else {
						if (item_SI == null)
							JOptionPane.showMessageDialog(null, "Item undefined");
						else {
							if (itemList_SI.contains(item_SI))
								JOptionPane.showMessageDialog(null, "Already Added");
							else {
								int stockToAdd = Integer.parseInt(tfQuantity_SI.getText());
								String itemdesc = tfItemname_SI.getText();

								itemList_SI.add(item_SI);
								stockin_model.addRow(new Object[] {
										itemdesc,
										stockToAdd
								});
								item_SI = null;
							}
						}
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Kindly enter fields correctly!", "Invalid Input", 2);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e2.getMessage());
					e2.printStackTrace();
				}
			}
		});
		btnAddStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddStock.setBounds(445, 40, 150, 50);
		pnlDetail_SI.add(btnAddStock);
		btnAddStock.setLayout(null);

		JLabel lblAddStock = new JLabel("Add Stock");
		lblAddStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStock.setForeground(Color.WHITE);
		lblAddStock.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblAddStock.setBounds(24, 10, 100, 30);
		btnAddStock.add(lblAddStock);

		RoundedSearchPnl pnlQuantity = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlQuantity.setLayout(null);
		pnlQuantity.setBounds(320, 45, 83, 40);
		pnlDetail_SI.add(pnlQuantity);

		tfQuantity_SI = new JTextField();
		tfQuantity_SI.setForeground(Color.DARK_GRAY);
		tfQuantity_SI.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfQuantity_SI.setBorder(null);
		tfQuantity_SI.setColumns(10);
		tfQuantity_SI.setBounds(10, 5, 63, 30);
		tfQuantity_SI.setHorizontalAlignment(JTextField.RIGHT);
		pnlQuantity.add(tfQuantity_SI);

		pnlOrder_SI = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlOrder_SI.setVisible(true);
		pnlOrder_SI.setEnabled(true);
		pnlOrder_SI.setBounds(625, 90, 350, 480);
		pnlContent.add(pnlOrder_SI);
		pnlOrder_SI.setLayout(null);

		JLabel lblOrder = new JLabel("  Stocks Added");
		lblOrder.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrder.setBounds(0, 5, 350, 50);
		pnlOrder_SI.add(lblOrder);
		lblOrder.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrder.setForeground(new Color(106, 106, 106));
		lblOrder.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblOrder.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		lblOrder.setBackground(new Color(194, 194, 194));
		tableStockIn = new CustomTable(4, 14) {
			// @Override
			// public void setModel(TableModel dataModel) {
			// super.setModel(dataModel);
			// if (dataModel.getColumnCount() > 0)
			// TableFormats.totableOrderedStock(this);
			// }
		};
		stockin_model.setColumnIdentifiers(new Object[] {
				"Item",
				"Stock"
		});
		tableStockIn.setModel(stockin_model);
		JScrollPane spStockin = new JScrollPane(tableStockIn, 20, 31);
		spStockin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spStockin.setBounds(5, 65, 340, 409);
		pnlOrder_SI.add(spStockin);

		JLabel lblOrderID = new JLabel("");
		lblOrderID.setBounds(294, 25, 50, 14);
		pnlOrder_SI.add(lblOrderID);
		pnlContent.setLayout(null);

		pnlOperations_SI = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlOperations_SI.setVisible(true);
		pnlOperations_SI.setEnabled(true);
		pnlOperations_SI.setBounds(625, 575, 350, 115);
		pnlContent.add(pnlOperations_SI);
		pnlOperations_SI.setBackground(Color.WHITE);
		pnlOperations_SI.setLayout(null);

		RoundedSubmit btnRemove_SI;
		btnRemove_SI = new RoundedSubmit(50, 0, new Color(225, 127, 107), new Color(220, 144, 127));
		btnRemove_SI.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (tableStockIn.getSelectedRow() == -1)
						JOptionPane.showMessageDialog(null, "No row item has been selected");
					else {
						if (tableStockIn.getRowCount() != 0) {
							int i = tableStockIn.getSelectedRow();
							stockin_model.removeRow(i);
							itemList_SI.remove(i);
						}
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnRemove_SI.setBounds(10, 40, 140, 50);
		pnlOperations_SI.add(btnRemove_SI);

		btnRemove_SI.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove_SI.setLayout(null);

		lblRemove = new JLabel("Remove");
		lblRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemove.setForeground(Color.WHITE);
		lblRemove.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblRemove.setBounds(10, 0, 120, 50);
		btnRemove_SI.add(lblRemove);

		btnConfirm_SI = new RoundedClearpnl(50, 0, new Color(71, 180, 206), new Color(107, 195, 215));
		btnConfirm_SI.setBounds(200, 40, 140, 50);
		pnlOperations_SI.add(btnConfirm_SI);
		btnConfirm_SI.setBorder(null);
		btnConfirm_SI.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirm_SI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (itemList_SI.size() != 0) {
						tfQuantity_SI.setText("");
						tfItemname_SI.setText("");
						StockIn[] stockin_items = new StockIn[itemList_SI.size()];
						for (int i = 0; i < stockin_items.length; i++) {
							TableModel stockin = tableStockIn.getModel();
							int quantity = Integer.parseInt(stockin.getValueAt(i, 1).toString());
							stockin_items[i] = new Item(-1).new StockIn(quantity, itemList_SI.get(i));
						}
						ConfirmSupplier_Frame CS = new ConfirmSupplier_Frame(manager, stockin_items,
								ManageStock_Frame.this);
						CS.setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "Stockin table empty");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnConfirm_SI.setLayout(null);

		JLabel lblConfirm = new JLabel("Confirm");
		lblConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setForeground(Color.WHITE);
		lblConfirm.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblConfirm.setBounds(10, 0, 120, 50);
		btnConfirm_SI.add(lblConfirm);

		// --------------------------------------------------------------------------------------------------------------------END
		// OF STOCK IN
		// BUTTON--------------------------------------------------------------------------------------------------------------------\\

		pnltableItems_SO = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnltableItems_SO.setVisible(false);
		pnltableItems_SO.setEnabled(false);
		pnltableItems_SO.setBackground(new Color(255, 255, 255));
		pnltableItems_SO.setBounds(10, 90, 610, 480);
		pnlContent.add(pnltableItems_SO);
		pnltableItems_SO.setLayout(null);

		DefaultTableCellRenderer rightRenderer_SO = new DefaultTableCellRenderer();
		rightRenderer_SO.setHorizontalAlignment(JLabel.RIGHT);

		tableItems_SO = new CustomTable(4, 14) {
			// @Override
			// public void setModel(TableModel dataModel) {
			// super.setModel(dataModel);
			// if(dataModel.getColumnCount() > 0)
			// TableFormats.totableItems(this);
			// }
		};
		tableItems_SO.setModel(manager.viewStockInventory());
		tableItems_SO.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					int i = tableItems_SO.getSelectedRow();
					TableModel item_model = tableItems_SO.getModel();
					int itemID = Integer.parseInt(item_model.getValueAt(i, 0).toString());
					String itemdesc = item_model.getValueAt(i, 1).toString();
					int currStock = Integer.parseInt(item_model.getValueAt(i, 2).toString());

					item_SO = new Item(itemID, itemdesc, currStock);
					tfItemname_SO.setText(itemdesc);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		JScrollPane spItem_SO = new JScrollPane(tableItems_SO, 20, 31);
		spItem_SO.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spItem_SO.setBounds(5, 65, 600, 404);
		pnltableItems_SO.add(spItem_SO);

		JPanel pnlItemTableHeader_SO = new JPanel();
		pnlItemTableHeader_SO.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlItemTableHeader_SO.setBackground(new Color(255, 255, 255));
		pnlItemTableHeader_SO.setBounds(0, 5, 610, 50);
		pnltableItems_SO.add(pnlItemTableHeader_SO);
		pnlItemTableHeader_SO.setLayout(null);

		RoundedBorderPanel btnStockoutTable = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnStockoutTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStockoutTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Stockouttable(manager).setVisible(true);
			}
		});
		btnStockoutTable.setBounds(408, 3, 192, 40);
		pnlItemTableHeader_SO.add(btnStockoutTable);
		btnStockoutTable.setLayout(null);
		btnStockoutTable.setBackground(Color.WHITE);

		lblStockouttable = new JLabel("View Stock Out Table");
		lblStockouttable.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockouttable.setForeground(Color.WHITE);
		lblStockouttable.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblStockouttable.setBounds(10, 0, 172, 40);
		btnStockoutTable.add(lblStockouttable);

		JLabel lblItems_SO = new JLabel("  Items");
		lblItems_SO.setBounds(0, 0, 605, 45);
		pnlItemTableHeader_SO.add(lblItems_SO);
		lblItems_SO.setHorizontalTextPosition(SwingConstants.CENTER);
		lblItems_SO.setHorizontalAlignment(SwingConstants.LEFT);
		lblItems_SO.setForeground(new Color(106, 106, 106));
		lblItems_SO.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblItems_SO.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(18, 72, 107)));
		lblItems_SO.setBackground(new Color(194, 194, 194));
		pnlContent.setLayout(null);

		pnlDetail_SO = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlDetail_SO.setVisible(false);
		pnlDetail_SO.setEnabled(false);
		pnlDetail_SO.setBackground(Color.WHITE);
		pnlDetail_SO.setBounds(10, 575, 610, 115);
		pnlContent.add(pnlDetail_SO);
		pnlDetail_SO.setLayout(null);

		JLabel lblItemname_SO = new JLabel("Selected Item");
		lblItemname_SO.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblItemname_SO.setBounds(15, 20, 280, 25);
		pnlDetail_SO.add(lblItemname_SO);

		RoundedSearchPnl pnlItemname_SO = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlItemname_SO.setBounds(15, 45, 280, 40);
		pnlDetail_SO.add(pnlItemname_SO);
		pnlItemname_SO.setLayout(null);

		tfItemname_SO = new JTextField();
		tfItemname_SO.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				try {
					if (!tfItemname_SO.getText().equals(""))
						tableItems_SO.setModel(manager.searchStockItem(tfItemname_SO.getText()));
					else
						tableItems_SO.setModel(manager.viewStockInventory());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tfItemname_SO.setForeground(Color.DARK_GRAY);
		tfItemname_SO.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfItemname_SO.setBackground(Color.WHITE);
		tfItemname_SO.setColumns(10);
		tfItemname_SO.setBorder(null);
		tfItemname_SO.setBounds(10, 5, 260, 30);
		pnlItemname_SO.add(tfItemname_SO);

		JLabel lblQuantity_SO = new JLabel("Quantity");
		lblQuantity_SO.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantity_SO.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblQuantity_SO.setBounds(320, 20, 83, 25);
		pnlDetail_SO.add(lblQuantity_SO);

		RoundedSearchPnl pnlQuantity_SO = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlQuantity_SO.setLayout(null);
		pnlQuantity_SO.setBounds(320, 45, 83, 40);
		pnlDetail_SO.add(pnlQuantity_SO);

		tfQuantity_SO = new JTextField();
		tfQuantity_SO.setForeground(Color.DARK_GRAY);
		tfQuantity_SO.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfQuantity_SO.setBorder(null);
		tfQuantity_SO.setColumns(10);
		tfQuantity_SO.setBounds(10, 5, 63, 30);
		tfQuantity_SO.setHorizontalAlignment(JTextField.RIGHT);
		pnlQuantity_SO.add(tfQuantity_SO);

		RoundedClearpnl btnRemoveStock = new RoundedClearpnl(50, 3, new Color(236, 37, 90), new Color(216, 7, 80));
		btnRemoveStock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (tfItemname_SO.getText().equals("") || tfQuantity_SO.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields");
					else {
						if (item_SO == null)
							JOptionPane.showMessageDialog(null, "Item undefined");
						else {
							if (itemList_SO.contains(item_SO))
								JOptionPane.showMessageDialog(null, "Already Added");
							else {
								String itemdesc = tfItemname_SO.getText();
								int stockToRemove = Integer.parseInt(tfQuantity_SO.getText());

								itemList_SO.add(item_SO);
								stockout_model.addRow(new Object[] {
										itemdesc,
										stockToRemove
								});
								item_SO = null;
							}
						}
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Kindly enter fields correctly");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e2.getMessage());
					e2.printStackTrace();
				}
			}
		});
		btnRemoveStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemoveStock.setBounds(445, 40, 150, 50);
		pnlDetail_SO.add(btnRemoveStock);
		btnRemoveStock.setLayout(null);

		JLabel lblRemoveStock = new JLabel("Remove Stock");
		lblRemoveStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemoveStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveStock.setForeground(Color.WHITE);
		lblRemoveStock.setFont(new Font("SansSerif", Font.BOLD, 17));
		lblRemoveStock.setBounds(19, 0, 115, 50);
		btnRemoveStock.add(lblRemoveStock);

		pnlOrder_SO = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlOrder_SO.setVisible(false);
		pnlOrder_SO.setEnabled(false);
		pnlOrder_SO.setBounds(625, 90, 350, 480);
		pnlContent.add(pnlOrder_SO);
		pnlOrder_SO.setLayout(null);

		JLabel lblOrder_SO = new JLabel("  Stocks Removed");
		lblOrder_SO.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrder_SO.setBounds(0, 5, 350, 50);
		pnlOrder_SO.add(lblOrder_SO);
		lblOrder_SO.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrder_SO.setForeground(new Color(106, 106, 106));
		lblOrder_SO.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblOrder_SO.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		lblOrder_SO.setBackground(new Color(194, 194, 194));
		tableStockOut = new CustomTable(4, 14) {
			// @Override
			// public void setModel(TableModel dataModel) {
			// super.setModel(dataModel);
			// if (dataModel.getColumnCount() > 0)
			// TableFormats.totableOrderedStock(this);
			// }
		};
		stockout_model.setColumnIdentifiers(new Object[] {
				"Item",
				"Stock"
		});
		tableStockOut.setModel(stockout_model);
		JScrollPane spStockout = new JScrollPane(tableStockOut, 20, 31);
		spStockout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spStockout.setBounds(5, 65, 340, 409);
		pnlOrder_SO.add(spStockout);

		JLabel lblOrderID1 = new JLabel("");
		lblOrderID1.setBounds(294, 25, 50, 14);
		pnlOrder_SO.add(lblOrderID1);
		pnlContent.setLayout(null);

		pnlOperations_SO = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlOperations_SO.setVisible(false);
		pnlOperations_SO.setEnabled(false);
		pnlOperations_SO.setBounds(625, 575, 350, 115);
		pnlContent.add(pnlOperations_SO);
		pnlOperations_SO.setBackground(Color.WHITE);
		pnlOperations_SO.setLayout(null);

		RoundedSubmit btnRemove_SO = new RoundedSubmit(50, 0, new Color(225, 127, 107), new Color(220, 144, 127));
		btnRemove_SO.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (tableStockOut.getSelectedRow() == -1)
						JOptionPane.showMessageDialog(null, "No row item has been selected");
					else {
						if (tableStockOut.getRowCount() != 0) {
							int i = tableStockOut.getSelectedRow();
							stockout_model.removeRow(i);
							itemList_SO.remove(i);
						}
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnRemove_SO.setBounds(10, 40, 140, 50);
		pnlOperations_SO.add(btnRemove_SO);

		btnRemove_SO.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove_SO.setLayout(null);

		lblRemove = new JLabel("Remove");
		lblRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemove.setForeground(Color.WHITE);
		lblRemove.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblRemove.setBounds(10, 0, 120, 50);
		btnRemove_SO.add(lblRemove);

		btnConfirm_SO = new RoundedClearpnl(50, 0, new Color(71, 180, 206), new Color(107, 195, 215));
		btnConfirm_SO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (itemList_SO.size() != 0 && tableStockOut.getRowCount() != 0) {
						tfQuantity_SO.setText("");
						tfItemname_SO.setText("");
						StockOut[] stockout_items = new StockOut[itemList_SO.size()];
						for (int i = 0; i < stockout_items.length; i++) {
							TableModel stockout = tableStockOut.getModel();
							int quantity = Integer.parseInt(stockout.getValueAt(i, 1).toString());
							stockout_items[i] = new Item(-1).new StockOut(itemList_SO.get(i), quantity);
						}
						new ConfirmCause_Frame(manager, stockout_items, ManageStock_Frame.this).setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "Stockout table empty");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnConfirm_SO.setBounds(200, 40, 140, 50);
		pnlOperations_SO.add(btnConfirm_SO);
		btnConfirm_SO.setBorder(null);
		btnConfirm_SO.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirm_SO.setLayout(null);

		JLabel lblConfirm_SO = new JLabel("Confirm");
		lblConfirm_SO.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfirm_SO.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm_SO.setForeground(Color.WHITE);
		lblConfirm_SO.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblConfirm_SO.setBounds(10, 0, 120, 50);
		btnConfirm_SO.add(lblConfirm_SO);

		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	protected ArrayList<Item> itemList_SI = new ArrayList<Item>();
	protected ArrayList<Item> itemList_SO = new ArrayList<Item>();
	protected Item item_SI = null, item_SO = null;
	private JLabel lblRemove;
}