package frontend;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import backend.Item;
import backend.User.Employee;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Close;
import frontend.UIcomponents.Minimize;
import frontend.UIcomponents.RoundedBorderPanel;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;
import frontend.UIcomponents.Sidebar;
import frontend.UIcomponents.TableFormats;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

public class Transaction_Frame extends JFrame {

	private JPanel Content, pnlSidebar;
	private JTable itemtable;
	private JTable ordertable;
	private JTextField tfQuantity;
	private JTextField tfPname;
	private JTextField tfAmount;
	private JTextField tfPayment;
	private RoundedClearpnl btnCheckout;
	private JComboBox<String> cbPricing;

	private DecimalFormat df = new DecimalFormat("0.00");
	private DefaultTableModel order_model = new DefaultTableModel();
	private DefaultTableModel item_model = new DefaultTableModel();
	private JTextField tfPrice;

	public Transaction_Frame(Employee emp) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 700);

		Content = new JPanel();
		Content.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Content);
		Content.setLayout(null);

		pnlSidebar = new Sidebar(emp, this);
		Content.add(pnlSidebar);

		JPanel pnlContent = new JPanel();
		pnlContent.setBackground(new Color(224, 224, 224));
		pnlContent.setBounds(300, 0, 980, 700);
		Content.add(pnlContent);
		pnlContent.setLayout(null);

		JPanel pnlItemTable = new JPanel();
		pnlItemTable.setBounds(10, 15, 605, 345);
		pnlContent.add(pnlItemTable);
		pnlItemTable.setLayout(null);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

		itemtable = new CustomTable(4, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if (dataModel.getColumnCount() > 0)
					TableFormats.toItemTable(this);
			}
		};
		itemtable.setModel(emp.viewFullInventory());
		JScrollPane scrollitem = new JScrollPane(itemtable, 20, 31);
		scrollitem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		itemtable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				try {
					int i = itemtable.getSelectedRow();
					TableModel takeitem_model = itemtable.getModel();
					String prodname = takeitem_model.getValueAt(i, 1).toString();
					double r_price = Double.parseDouble(takeitem_model.getValueAt(i, 4).toString());
					double w_price = Double.parseDouble(takeitem_model.getValueAt(i, 3).toString());
					int item_id = Integer.parseInt(takeitem_model.getValueAt(i, 0).toString());
					int stock = Integer.parseInt(takeitem_model.getValueAt(i, 2).toString());

					tfPname.setText(prodname);
					int qty = 1;
					if (tfQuantity.getText().equals("")) {
						tfQuantity.setText(String.valueOf(qty));
					} else {
						qty = Integer.parseInt(tfQuantity.getText());
					}

					item = new Item(item_id, prodname, stock, w_price, r_price);

					if (cbPricing.getSelectedIndex() == 0)
						tfPrice.setText(df.format(item.getRetail_price() * qty));
					else
						tfPrice.setText(df.format(item.getWholesale_price() * qty));

					itemtable.clearSelection();
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
		scrollitem.setBounds(0, 0, 605, 345);
		pnlItemTable.add(scrollitem);
		pnlContent.setLayout(null);

		RoundedClearpnl pnlDetail = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlDetail.setBackground(Color.WHITE);
		pnlDetail.setBounds(10, 365, 605, 320);
		pnlContent.add(pnlDetail);
		pnlDetail.setLayout(null);

		JLabel lblTotalamount = new JLabel(" Product Detail");
		lblTotalamount.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(18, 72, 107)));
		lblTotalamount.setVerticalAlignment(SwingConstants.TOP);
		lblTotalamount.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalamount.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblTotalamount.setForeground(new Color(106, 106, 106));
		lblTotalamount.setBackground(new Color(194, 194, 194));
		lblTotalamount.setBounds(0, 10, 605, 50);
		pnlDetail.add(lblTotalamount);

		JLabel lblProductname = new JLabel("Product name");
		lblProductname.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblProductname.setBounds(10, 72, 129, 40);
		pnlDetail.add(lblProductname);

		RoundedSearchPnl pnlPname = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlPname.setBounds(125, 71, 280, 40);
		pnlDetail.add(pnlPname);
		pnlPname.setLayout(null);

		tfPname = new JTextField();
		tfPname.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				item = null;
				tfPrice.setText("");
				tfQuantity.setText("");
				if (tfPname.getText().equals(""))
					item_model.setRowCount(0);
				else {
					try {
						String search = tfPname.getText();
						item_model = emp.searchProductName(search);
						itemtable.setModel(item_model);

						// checks prod name equal to the desc of the 1st row
						if (item_model.getRowCount() != 0 && tfPname.getText().equals(item_model.getValueAt(0, 1))) {
							// takes data of the 1st row
							double r_price = Double.parseDouble(item_model.getValueAt(0, 4).toString());
							double w_price = Double.parseDouble(item_model.getValueAt(0, 3).toString());
							int item_id = Integer.parseInt(item_model.getValueAt(0, 0).toString());
							int stock = Integer.parseInt(item_model.getValueAt(0, 2).toString());
							int qty = 1;
							if (tfQuantity.getText().equals("")) {
								tfQuantity.setText(String.valueOf(qty));
							} else {
								qty = Integer.parseInt(tfQuantity.getText());
							}

							item = new Item(item_id, tfPname.getText(), stock, w_price, r_price);

							if (cbPricing.getSelectedIndex() == 0)
								tfPrice.setText(df.format(item.getRetail_price() * qty));
							else
								tfPrice.setText(df.format(item.getWholesale_price() * qty));
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "A search problem has occured!\n" + e1.getMessage(),
								"Search Product", 0);
					}
				}
			}
		});
		tfPname.setForeground(Color.DARK_GRAY);
		tfPname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfPname.setBackground(Color.WHITE);
		tfPname.setColumns(10);
		tfPname.setBorder(null);
		tfPname.setBounds(10, 5, 260, 30);
		pnlPname.add(tfPname);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantity.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblQuantity.setBounds(415, 71, 83, 40);
		pnlDetail.add(lblQuantity);

		JLabel lblPricing = new JLabel("Pricing");
		lblPricing.setHorizontalAlignment(SwingConstants.LEFT);
		lblPricing.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblPricing.setBounds(10, 135, 129, 40);
		pnlDetail.add(lblPricing);

		RoundedBorderPanel btnAdd = new RoundedBorderPanel(50, 0, new Color(71, 180, 206));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String product = tfPname.getText();
					if (product.equals("") || tfPrice.getText().equals("") || tfQuantity.getText().equals("")
							|| item.equals(null))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "", 2);
					else {
						Double price = Double.parseDouble(tfPrice.getText());
						int qty = Integer.parseInt(tfQuantity.getText());

						if (item.getStock() == 0) {
							JOptionPane.showMessageDialog(null, "Item is out of stock.");
						} else if (qty > item.getStock()) {
							JOptionPane.showMessageDialog(null,
									"Your entered quantity exceeds from the current stock. Please lower the amount");
						} else {
							if (orderList.contains(item)) {
								System.out.println(orderList.contains(item));
								int row = orderList.indexOf(item);
								if (qty != 0) {
									order_model.setValueAt(price, row, 1);
									order_model.setValueAt(qty, row, 2);
								} else {
									order_model.removeRow(row);
									orderList.remove(row);
								}
							} else {
								if (qty != 0) {
									order_model.addRow(new Object[] {
											product,
											price,
											qty
									});
									orderList.add(item);
								}
							}

							double amount = 0;
							for (int i = 0; i < orderList.size(); i++) {
								amount += Double.parseDouble(order_model.getValueAt(i, 1).toString());
							}
							tfAmount.setText(df.format(amount));
						}
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Kindly enter fields correctly!", "Invalid Input", 2);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setBounds(445, 259, 150, 50);
		pnlDetail.add(btnAdd);
		btnAdd.setLayout(null);

		JLabel lblAdd = new JLabel("Add Item");
		lblAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setForeground(Color.WHITE);
		lblAdd.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblAdd.setBounds(24, 10, 100, 30);
		btnAdd.add(lblAdd);

		RoundedSearchPnl pnlQuantity = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlQuantity.setLayout(null);
		pnlQuantity.setBounds(492, 71, 83, 40);
		pnlDetail.add(pnlQuantity);

		tfQuantity = new JTextField();
		tfQuantity.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {

				try {
					if (item != null) {
						if (!tfQuantity.getText().equals("")) {

							int qty = Integer.parseInt(tfQuantity.getText());
							if (cbPricing.getSelectedIndex() == 0)
								tfPrice.setText(df.format(item.getRetail_price() * qty));
							else
								tfPrice.setText(df.format(item.getWholesale_price() * qty));

						} else {
							tfPrice.setText("");
						}
					}
				} catch (InputMismatchException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Input");
				}

			}
		});
		tfQuantity.setForeground(Color.DARK_GRAY);
		tfQuantity.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfQuantity.setBorder(null);
		tfQuantity.setColumns(10);
		tfQuantity.setBounds(10, 5, 63, 30);
		tfQuantity.setHorizontalAlignment(JTextField.RIGHT);
		pnlQuantity.add(tfQuantity);

		RoundedSearchPnl pnlPricing = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlPricing.setLayout(null);
		pnlPricing.setBounds(125, 135, 280, 40);
		pnlDetail.add(pnlPricing);

		cbPricing = new JComboBox<String>(new String[] { "Retail Price", "Wholesale Price" });
		cbPricing.setSelectedIndex(0);
		cbPricing.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (item != null)
						if (tfQuantity.getText().equals("") || tfQuantity.getText().equals("0"))
							tfQuantity.setText("1");
						else {
							int qty = Integer.parseInt(tfQuantity.getText());
							if (cbPricing.getSelectedIndex() == 0)
								tfPrice.setText(String.valueOf(item.getRetail_price() * qty));
							else
								tfPrice.setText(String.valueOf(item.getWholesale_price() * qty));
						}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
				}
			}
		});
		cbPricing.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbPricing.setBackground(new Color(255, 255, 255));
		cbPricing.setBorder(new LineBorder(Color.WHITE, 2));
		cbPricing.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbPricing.setBounds(2, 2, 276, 36);
		pnlPricing.add(cbPricing);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblPrice.setBounds(10, 195, 129, 40);
		pnlDetail.add(lblPrice);

		RoundedSearchPnl pnlPrice_1 = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlPrice_1.setLayout(null);
		pnlPrice_1.setBounds(125, 195, 280, 40);
		pnlDetail.add(pnlPrice_1);

		tfPrice = new JTextField();
		tfPrice.setForeground(Color.DARK_GRAY);
		tfPrice.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfPrice.setEditable(false);
		tfPrice.setColumns(10);
		tfPrice.setBorder(null);
		tfPrice.setBackground(Color.WHITE);
		tfPrice.setBounds(10, 5, 260, 30);
		tfPrice.setHorizontalAlignment(JTextField.RIGHT);
		pnlPrice_1.add(tfPrice);

		RoundedClearpnl pnlOrder = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlOrder.setBounds(625, 15, 350, 670);
		pnlContent.add(pnlOrder);
		pnlOrder.setLayout(null);

		JLabel lblOrder = new JLabel("Order");
		lblOrder.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrder.setBounds(15, 0, 350, 50);
		pnlOrder.add(lblOrder);
		lblOrder.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrder.setForeground(new Color(106, 106, 106));
		lblOrder.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblOrder.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(18, 72, 107)));
		lblOrder.setBackground(new Color(194, 194, 194));
		ordertable = new JTable() {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if (dataModel.getColumnCount() > 0)
					TableFormats.toOrderTable(this);
			}
		};
		order_model.setColumnIdentifiers(new Object[] {
				"Item",
				"Subtotal",
				"Qty"
		});
		ordertable.setModel(order_model);
		JScrollPane scrollorder = new JScrollPane(ordertable, 20, 31);
		scrollorder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ordertable.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {

				int o = ordertable.getSelectedRow();
				TableModel takeorder_model = ordertable.getModel();
				String prodname = takeorder_model.getValueAt(o, 0).toString();
				double price = Double.parseDouble(takeorder_model.getValueAt(o, 1).toString());
				int qty = Integer.parseInt(takeorder_model.getValueAt(o, 2).toString());

				tfPname.setText(prodname);
				item = orderList.get(0);

				if (price == item.getRetail_price()) {
					cbPricing.setSelectedIndex(0);
					tfPrice.setText(df.format(item.getRetail_price()));
				} else {
					cbPricing.setSelectedIndex(1);
					tfPrice.setText(df.format(item.getWholesale_price()));
				}

				tfQuantity.setText(String.valueOf(qty));
				ordertable.clearSelection();
			}
		});
		scrollorder.setBounds(10, 55, 330, 360);
		pnlOrder.add(scrollorder);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(18, 72, 107)));
		panel.setBounds(0, 420, 350, 185);
		pnlOrder.add(panel);
		panel.setLayout(null);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblAmount.setBounds(30, 15, 100, 40);
		panel.add(lblAmount);

		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblPayment.setBounds(30, 75, 100, 40);
		panel.add(lblPayment);

		RoundedSearchPnl pnlAmount = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlAmount.setLayout(null);
		pnlAmount.setBounds(130, 15, 150, 40);
		panel.add(pnlAmount);

		tfAmount = new JTextField();
		tfAmount.setEditable(false);
		tfAmount.setBounds(10, 5, 130, 30);
		pnlAmount.add(tfAmount);
		tfAmount.setForeground(Color.DARK_GRAY);
		tfAmount.setFont(new Font("SansSerif", Font.BOLD, 17));
		tfAmount.setColumns(10);
		tfAmount.setBorder(null);
		tfAmount.setBackground(Color.WHITE);
		tfAmount.setHorizontalAlignment(JTextField.RIGHT);

		RoundedSearchPnl pnlPayment = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlPayment.setLayout(null);
		pnlPayment.setBounds(130, 75, 150, 40);
		panel.add(pnlPayment);

		tfPayment = new JTextField();
		tfPayment.setForeground(Color.DARK_GRAY);
		tfPayment.setFont(new Font("SansSerif", Font.BOLD, 17));
		tfPayment.setColumns(10);
		tfPayment.setBorder(null);
		tfPayment.setBackground(Color.WHITE);
		tfPayment.setBounds(10, 5, 130, 30);
		tfPayment.setHorizontalAlignment(JTextField.RIGHT);
		pnlPayment.add(tfPayment);

		btnCheckout = new RoundedClearpnl(50, 0, new Color(71, 180, 206), new Color(107, 195, 215));
		btnCheckout.setBorder(null);
		btnCheckout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (tfPayment.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "", 2);
					} else {
						amount = Double.parseDouble(tfAmount.getText());
						payment = Double.parseDouble(tfPayment.getText());
						if (amount != 0 && payment != 0) {
							if (payment >= amount) {
								Checkout_Frame frame = new Checkout_Frame(emp, amount, payment, order_model, orderList);
								frame.setVisible(true);
							} else
								JOptionPane.showMessageDialog(null, "Insufficient Payment");
						}
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Kindly enter fields correctly", "", 2);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
				}

			}
		});
		btnCheckout.setLayout(null);
		btnCheckout.setBounds(200, 612, 140, 50);
		pnlOrder.add(btnCheckout);

		JLabel lblPay = new JLabel("Checkout");
		lblPay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblPay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPay.setForeground(Color.WHITE);
		lblPay.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPay.setBounds(10, 10, 120, 30);
		btnCheckout.add(lblPay);

		RoundedSubmit btnClear = new RoundedSubmit(50, 0, new Color(225, 127, 107), new Color(220, 144, 127));

		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					tfPayment.setText("");
					cbPricing.setSelectedIndex(0);
					tfPrice.setText("");
					tfQuantity.setText("");
					tfAmount.setText("");
					order_model.setRowCount(0);
					tfPname.setText("");
					orderList.clear();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
				}
			}
		});
		btnClear.setLayout(null);
		btnClear.setBounds(10, 612, 100, 50);
		pnlOrder.add(btnClear);

		JLabel lblClear = new JLabel("Clear");
		lblClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClear.setHorizontalAlignment(SwingConstants.CENTER);
		lblClear.setForeground(Color.WHITE);
		lblClear.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblClear.setBounds(10, 10, 80, 30);
		btnClear.add(lblClear);

		JLabel lblOrderID = new JLabel("");
		lblOrderID.setBounds(294, 25, 50, 14);
		pnlOrder.add(lblOrderID);

		Design_Close lbl_Close = new Design_Close("Close", 0, this, new Color(255, 255, 255));
		lbl_Close.setBounds(320, 0, 30, 30);
		pnlOrder.add(lbl_Close);
		lbl_Close.setText("");

		Minimize lbl_Minimize = new Minimize("Minimize", 0, this, new Color(255, 255, 255), new Color(227, 244, 244),
				Color.white);
		lbl_Minimize.setText("");
		lbl_Minimize.setBounds(290, 0, 30, 30);
		pnlContent.setLayout(null);
		pnlOrder.add(lbl_Minimize);

		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	ArrayList<Item> orderList = new ArrayList<Item>();
	private Item item = null;
	private double amount = 0, payment = 0;
}
