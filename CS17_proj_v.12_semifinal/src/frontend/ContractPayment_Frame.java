package frontend;

import java.awt.Color;
import java.awt.Cursor;

import backend.Customer;
import backend.Event.EventPackage;
import backend.Event.EventSchedule.AdditionalItem;
import backend.User;

import java.awt.Font;
import java.awt.Image;

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

import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.Design_SearchText;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;
import frontend.UIcomponents.CalendarComponents.Cell;

import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class ContractPayment_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfSearch;
	protected CustomTable tableItems, viewItems;
	private JScrollPane spItems, spViewItems;
	
	private Image img_Search = new ImageIcon(Login.class.getResource("resources/SearchUser.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private JTextField tfItem;
	private JTextField tfQuantity;
	private JTextField tfTotalAddItem;
	private JTextField tfTotalPackage;
	private JTextField tfSubtotal;
	/**
	 * Create the frame.
	 * @param isNewCustomer 
	 * @param chosen_packageID 
	 * @param chosen_eventID 
	 * @param customer 
	 * @param addSchedule 
	 * @param user 
	 * @param selectedDate 
	 */
	public ContractPayment_Frame(User user, AddSchedule addSchedule, Customer customer, Date eventDate, EventPackage chosen_package, boolean isNewCustomer, Cell selectedDate) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 560);
	
		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 2));
		pnlContent.setBackground(new Color(224, 224, 224));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		
		JPanel containerAddItems = new JPanel();
		containerAddItems.setBackground(new Color(240, 240, 240));
		containerAddItems.setBounds(10, 10, 930, 540);
		pnlContent.add(containerAddItems);
		containerAddItems.setLayout(null);		

		RoundedClearpnl pnlItemContainer = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlItemContainer.setBounds(5, 5, 450, 530);
		containerAddItems.add(pnlItemContainer);
		pnlItemContainer.setLayout(null);
		
		JPanel pnlItemContainer_Header = new JPanel();
		pnlItemContainer_Header.setBounds(0, 10, 450, 75);
		pnlItemContainer_Header.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlItemContainer_Header.setBackground(new Color(255, 255, 255));
		pnlItemContainer.add(pnlItemContainer_Header);
		pnlItemContainer_Header.setLayout(null);
		
		RoundedSearchPnl  pnlSearch = new RoundedSearchPnl (25, 2, Color.white, new Color(71, 180, 206), new Color(71, 180, 206));
		pnlSearch.setBackground(Color.WHITE);
		pnlSearch.setBounds(190, 28, 250, 40);
		pnlSearch.setLayout(null);
		pnlItemContainer_Header.add(pnlSearch);
		
		tfSearch = new Design_SearchText();
		tfSearch.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(tfSearch.getText().equals(""))
					tableItems.setModel(user.viewHalfInventory());
				else {
					tableItems.setModel(user.searchHalfItem(tfSearch.getText()));
				}
			}
		});
		tfSearch.setForeground(new Color (106, 106, 106));
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
		
		JLabel lblItemHeader = new JLabel("  Add Item");
		lblItemHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemHeader.setForeground(new Color(102, 102, 102));
		lblItemHeader.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblItemHeader.setBounds(0, 28, 188, 40);
		pnlItemContainer_Header.add(lblItemHeader);
		
		Design_Back lbl_Back = new Design_Back ("Back" , 50, this, new Color(255, 255, 255));
		lbl_Back.setBounds(0, 0, 40, 40);
		pnlItemContainer_Header.add(lbl_Back);
		lbl_Back.setText("");
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlTable.setBackground(new Color(255, 255, 255));
		pnlTable.setBounds(0, 84, 450, 310);
		pnlItemContainer.add(pnlTable);
		pnlTable.setLayout(null);
		
		tableItems = new CustomTable(4, 14);
		tableItems.setModel(user.viewHalfInventory());
		spItems = new JScrollPane(tableItems, 20 ,31);	
		tableItems.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				int i = tableItems.getSelectedRow();
				TableModel table_model = tableItems.getModel();
				String itemdesc = table_model.getValueAt(i, 0).toString();
				int stock = Integer.parseInt(table_model.getValueAt(i, 1).toString());
				double r_price = Double.parseDouble(table_model.getValueAt(i, 2).toString());
				
				TableModel item_model = user.searchItem(itemdesc);
				int itemID = Integer.parseInt(item_model.getValueAt(0,0).toString());
				additionalItem = new AdditionalItem(itemID, itemdesc, stock, r_price);
				tfItem.setText(itemdesc);
			}
		});
		spItems.setBounds(5, 10, 435, 289);
		pnlTable.add(spItems);
		
		RoundedSubmit btnAddItem = new RoundedSubmit(40, 0, new Color(71, 180, 206), new Color(107, 195, 215));
		btnAddItem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (tfItem.getText().equals("") || tfQuantity.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields");
					else {
						if (additionalList.contains(additionalItem))
							JOptionPane.showMessageDialog(null, "Already Added");
						else {
							int stock = additionalItem.getStock();
							int quantity = Integer.parseInt(tfQuantity.getText());
							if (quantity > stock)
								JOptionPane.showMessageDialog(null,
										"Your entered quantity exceeds from the current stock. Please lower the amount");
							else {
								if (quantity != 0) {
									String itemdesc = additionalItem.getDesc();
									double additonal_price = additionalItem.getAdditional_price();
									double subtotal_price = quantity * additonal_price;
									additionalTable_model.addRow(new Object[] {
											itemdesc,
											quantity,
											additonal_price,
											subtotal_price
									});
									additionalItem.setQuantity(quantity);
									additionalList.add(additionalItem);
									double totalAddItem = calculateTotalAmount();
									double totalPackage = chosen_package.getPrice();
									double total = totalAddItem + totalPackage;
									tfTotalAddItem.setText(df.format(totalAddItem));
									tfTotalPackage.setText(df.format(totalPackage));
									tfSubtotal.setText(df.format(total));
								}
							}
						}
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Kindly enter the field correctly", "Invalid Input", 0);
				}
			}
		});
		btnAddItem.setBounds(320, 468, 120, 40);
		pnlItemContainer.add(btnAddItem);
		btnAddItem.setLayout(null);
		btnAddItem.setBorder(null);
		
		JLabel lblAddItem = new JLabel("Add Item");
		lblAddItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddItem.setForeground(Color.WHITE);
		lblAddItem.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblAddItem.setBounds(10, 0, 100, 40);
		btnAddItem.add(lblAddItem);
		
		RoundedSearchPnl pnlItem = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206), new Color(71, 180, 206));
		pnlItem.setBackgroundColor(Color.WHITE);
		pnlItem.setBackground(new Color(192, 192, 192));
		pnlItem.setLayout(null);
		pnlItem.setBounds(91, 413, 210, 40);
		pnlItemContainer.add(pnlItem);
		
		tfItem = new JTextField();
		tfItem.setEditable(false);
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
		lblItem.setBounds(10, 413, 93, 40);
		pnlItemContainer.add(lblItem);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(new Color(102, 102, 102));
		lblQuantity.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblQuantity.setBounds(10, 468, 93, 40);
		pnlItemContainer.add(lblQuantity);
		
		RoundedSearchPnl pnlQuantity = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206), new Color(71, 180, 206));
		pnlQuantity.setLayout(null);
		pnlQuantity.setBounds(91, 468, 170, 40);
		pnlItemContainer.add(pnlQuantity);
		
		tfQuantity = new JTextField();
		tfQuantity.setForeground(Color.DARK_GRAY);
		tfQuantity.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfQuantity.setColumns(10);
		tfQuantity.setBorder(null);
		tfQuantity.setBounds(10, 5, 150, 30);
		pnlQuantity.add(tfQuantity);
//		
		RoundedClearpnl pnlIncludedItems = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlIncludedItems.setBounds(465, 5, 455, 530);
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
		additionalTable_model = new DefaultTableModel();
		additionalTable_model.setColumnIdentifiers(new Object[] {
			"Description",
			"Quantity",
			"Additional Price"
		});
		viewItems.setModel(additionalTable_model);
		spViewItems = new JScrollPane(viewItems, 20 ,31);	
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
						additionalTable_model.removeRow(i);
						additionalList.remove(i);

						double totalAddItem = calculateTotalAmount();
						double totalPackage = chosen_package.getPrice();
						double total = totalAddItem + totalPackage;
						tfTotalAddItem.setText(df.format(totalAddItem));
						tfTotalPackage.setText(df.format(totalPackage));
						tfSubtotal.setText(df.format(total));
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnRemove.setLayout(null);
		btnRemove.setBounds(25, 470, 160, 45);
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
			@Override
			public void mouseClicked(MouseEvent e) {
				
				double totalAddItem = calculateTotalAmount();
				double totalPackage = chosen_package.getPrice();
				double total = totalAddItem + totalPackage;

				tfTotalAddItem.setText(df.format(totalAddItem));
				tfTotalPackage.setText(df.format(totalPackage));
				tfSubtotal.setText(df.format(total));
				
				Payment_Frame pay = new Payment_Frame(user, addSchedule, customer, eventDate, chosen_package, isNewCustomer, 
						totalAddItem, total, additionalList, ContractPayment_Frame.this, selectedDate);
				pay.setVisible(true);					
			}
		});
		btnConfirm.setLayout(null);
		btnConfirm.setBounds(270, 470, 160, 45);
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
		pnlTotalPrice.setBounds(0, 285, 455, 170);
		pnlIncludedItems.add(pnlTotalPrice);
		pnlTotalPrice.setLayout(null);
		
		JLabel lblTotalAddItem = new JLabel("Additional Items Amount");
		lblTotalAddItem.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalAddItem.setForeground(new Color(102, 102, 102));
		lblTotalAddItem.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTotalAddItem.setBounds(40, 15, 170, 40);
		pnlTotalPrice.add(lblTotalAddItem);
		
		RoundedSearchPnl pnlTotalAddItem = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206), new Color(71, 180, 206));
		pnlTotalAddItem.setLayout(null);
		pnlTotalAddItem.setBounds(214, 15, 170, 40);
		pnlTotalPrice.add(pnlTotalAddItem);
		
		tfTotalAddItem = new JTextField();
		tfTotalAddItem.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotalAddItem.setForeground(Color.DARK_GRAY);
		tfTotalAddItem.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfTotalAddItem.setColumns(10);
		tfTotalAddItem.setBorder(null);
		tfTotalAddItem.setBounds(10, 5, 150, 30);
		pnlTotalAddItem.add(tfTotalAddItem);
		
		JLabel lblTotalPackage = new JLabel("Package Amount");
		lblTotalPackage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalPackage.setForeground(new Color(102, 102, 102));
		lblTotalPackage.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTotalPackage.setBounds(40, 65, 170, 40);
		pnlTotalPrice.add(lblTotalPackage);
		
		RoundedSearchPnl pnlTotalPackage = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206), new Color(71, 180, 206));
		pnlTotalPackage.setLayout(null);
		pnlTotalPackage.setBounds(214, 65, 170, 40);
		pnlTotalPrice.add(pnlTotalPackage);
		
		tfTotalPackage = new JTextField();
		tfTotalPackage.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotalPackage.setForeground(Color.DARK_GRAY);
		tfTotalPackage.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfTotalPackage.setColumns(10);
		tfTotalPackage.setBorder(null);
		tfTotalPackage.setBounds(10, 5, 150, 30);
		pnlTotalPackage.add(tfTotalPackage);
		
		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setForeground(new Color(102, 102, 102));
		lblSubtotal.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSubtotal.setBounds(40, 115, 170, 40);
		pnlTotalPrice.add(lblSubtotal);
		
		RoundedSearchPnl pnlSubtotal = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206), new Color(71, 180, 206));
		pnlSubtotal.setLayout(null);
		pnlSubtotal.setBounds(214, 115, 170, 40);
		pnlTotalPrice.add(pnlSubtotal);
		
		tfSubtotal = new JTextField();
		tfSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfSubtotal.setForeground(Color.DARK_GRAY);
		tfSubtotal.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfSubtotal.setColumns(10);
		tfSubtotal.setBorder(null);
		tfSubtotal.setBounds(10, 5, 150, 30);
		pnlSubtotal.add(tfSubtotal);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
	
	private double calculateTotalAmount() {
		double totalAmount = 0;
		for (int j = 0; j < additionalTable_model.getRowCount(); j++) {
			int quantity = Integer.parseInt(additionalTable_model.getValueAt(j, 1).toString());
			double price = Double.parseDouble(additionalTable_model.getValueAt(j, 2).toString());
			totalAmount += quantity * price;
		}
		return totalAmount;
	}

	private AdditionalItem additionalItem = null;
	protected ArrayList<AdditionalItem> additionalList = new ArrayList<AdditionalItem>();
	protected DefaultTableModel additionalTable_model = null;
	protected IncludedItems[] includedItems = null;

	private DecimalFormat df = new DecimalFormat("0.00");
}
