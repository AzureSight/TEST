package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableModel;

import backend.Customer;
import backend.Event;
import backend.User;
import backend.Event.EventPackage;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.Design_SearchText;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;
import frontend.UIcomponents.TableFormats;
import frontend.UIcomponents.CalendarComponents.Cell;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddSchedule extends JFrame {

	private JPanel pnlContent;
	private JTextField tfFname;
	private JTextField tfLname;
	private JTextField tfEmail;
	private JTextField tfPhone, tfSearch;
	private JTextField tfDate;

	private Event chosen_event;
	protected CustomTable tableCustomers, tableItems;
	private JScrollPane spCustomer, spItems;
	private Image img_Search = new ImageIcon(Login.class.getResource("resources/SearchUser.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Create the frame.
	 */
	public AddSchedule(User user, Date eventDate, Cell selectedDate) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(720, 620);

		pnlContent = new JPanel();
		// pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 2));
		pnlContent.setBackground(new Color(224, 224, 224));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		JPanel containerAddSchedule = new JPanel();
		containerAddSchedule.setBackground(new Color(240, 240, 240));
		containerAddSchedule.setBounds(10, 10, 700, 600);
		pnlContent.add(containerAddSchedule);
		containerAddSchedule.setLayout(null);

		RoundedClearpnl pnlContractContainer = new RoundedClearpnl(15, 3, new Color(255, 255, 255),
				new Color(255, 255, 255));
		pnlContractContainer.setBounds(5, 5, 690, 590);
		containerAddSchedule.add(pnlContractContainer);
		pnlContractContainer.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlHeader.setBackground(new Color(255, 255, 255));
		pnlHeader.setBounds(0, 5, 690, 50);
		pnlContractContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		Design_Back lbl_Back = new Design_Back("Back", 50, this, new Color(238, 237, 237));
		lbl_Back.setBackgroundColor(new Color(255, 255, 255));
		lbl_Back.setText("");
		lbl_Back.setBounds(5, 5, 40, 40);
		pnlHeader.add(lbl_Back);

		JLabel lblHeader = new JLabel("Create Contract");
		lblHeader.setForeground(new Color(102, 102, 102));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblHeader.setBounds(0, 5, 680, 40);
		pnlHeader.add(lblHeader);

		JPanel pnlCustomerTable = new JPanel();
		pnlCustomerTable.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlCustomerTable.setBackground(new Color(255, 255, 255));
		pnlCustomerTable.setBounds(0, 55, 690, 260);
		pnlContractContainer.add(pnlCustomerTable);
		pnlCustomerTable.setLayout(null);

		RoundedSearchPnl pnlSearch = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlSearch.setBounds(430, 5, 250, 40);
		pnlCustomerTable.add(pnlSearch);
		pnlSearch.setBackground(Color.WHITE);
		pnlSearch.setLayout(null);
		//
		// tableCustomer= new CustomTable(3, 14) {
		// @Override
		// public void setModel(TableModel dataModel) {
		// super.setModel(dataModel);
		// if(dataModel.getColumnCount() > 0)
		// TableFormats.toCustomerTable(this);
		// }
		// };
		//
		tfSearch = new Design_SearchText();
		tfSearch.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				try {
					if (tfSearch.getText().equals("")) {
						tfFname.setEditable(true);
						tfLname.setEditable(true);
						tfEmail.setEditable(true);
						tfPhone.setEditable(true);
						tfFname.setText("");
						tfLname.setText("");
						tfEmail.setText("");
						tfPhone.setText("");
						tableCustomers.setModel(user.viewAllCustomer());
						isNewCustomer = true;
					} else {
						tableCustomers.setModel(user.searchCustomer(tfSearch.getText()));
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tfSearch.setForeground(new Color(106, 106, 106));
		tfSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfSearch.setBorder(null);
		tfSearch.setBounds(18, 10, 180, 20);
		pnlSearch.add(tfSearch);
		tfSearch.setColumns(10);

		JLabel iconSearch = new JLabel("");
		iconSearch.setHorizontalAlignment(SwingConstants.CENTER);
		iconSearch.setBounds(210, 5, 40, 30);
		iconSearch.setIcon(new ImageIcon(img_Search));
		pnlSearch.add(iconSearch);

		JPanel pnlCT_Header = new JPanel();
		pnlCT_Header.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlCT_Header.setBackground(new Color(255, 255, 255));
		pnlCT_Header.setBounds(0, 0, 690, 50);
		pnlCustomerTable.add(pnlCT_Header);
		pnlCT_Header.setLayout(null);

		JLabel lblCustomerDetails = new JLabel("Customer Details:");
		lblCustomerDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomerDetails.setForeground(new Color(102, 102, 102));
		lblCustomerDetails.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblCustomerDetails.setBounds(10, 5, 420, 40);
		pnlCT_Header.add(lblCustomerDetails);

		JPanel pnlViewCustomerTable = new JPanel();
		pnlViewCustomerTable.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlViewCustomerTable.setBackground(new Color(255, 255, 255));
		pnlViewCustomerTable.setBounds(0, 45, 690, 110);
		pnlCustomerTable.add(pnlViewCustomerTable);
		pnlViewCustomerTable.setLayout(null);

		tableCustomers = new CustomTable(4, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if(dataModel.getColumnCount() > 0)
					TableFormats.toCustomerTable(this);
			}
		};
		tableCustomers.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int c = tableCustomers.getSelectedRow();
				TableModel cust_model = tableCustomers.getModel();
				int id = Integer.parseInt(cust_model.getValueAt(c, 0).toString());
				String fname = cust_model.getValueAt(c, 1).toString();
				String lname = cust_model.getValueAt(c, 2).toString();
				String email = cust_model.getValueAt(c, 3).toString();

				customer = new Customer(id, fname, lname, email, email, user);
				tfFname.setText(customer.getFname());
				tfLname.setText(customer.getLname());
				tfEmail.setText(customer.getEmail());
				tfPhone.setText(customer.getContactnum());

				tfFname.setEditable(false);
				tfLname.setEditable(false);
				tfEmail.setEditable(false);
				tfPhone.setEditable(false);

				isNewCustomer = false;
				tableCustomers.clearSelection();
			}
		});
		tableCustomers.setModel(user.viewAllCustomer());
		spCustomer = new JScrollPane(tableCustomers, 20, 31);
		spCustomer.setBounds(10, 11, 670, 90);
		pnlViewCustomerTable.add(spCustomer);

		JPanel pnlCustomerDetails = new JPanel();
		pnlCustomerDetails.setBackground(new Color(255, 255, 255));
		pnlCustomerDetails.setBounds(0, 152, 690, 105);
		pnlCustomerTable.add(pnlCustomerDetails);
		pnlCustomerDetails.setLayout(null);

		RoundedSearchPnl pnlFname = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlFname.setBounds(110, 10, 210, 40);
		pnlCustomerDetails.add(pnlFname);
		pnlFname.setLayout(null);

		tfFname = new JTextField();
		tfFname.setBorder(null);
		tfFname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				{
					tfFname.selectAll();
				}
			}

		});
		tfFname.setBackground(Color.WHITE);
		tfFname.setForeground(Color.BLACK);
		tfFname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfFname.setBounds(10, 5, 190, 30);
		pnlFname.add(tfFname);
		tfFname.setColumns(10);

		JLabel lblFname = new JLabel("First Name");
		lblFname.setBounds(0, 10, 103, 40);
		pnlCustomerDetails.add(lblFname);
		lblFname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFname.setFont(new Font("SansSerif", Font.BOLD, 15));

		RoundedSearchPnl pnlLname = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(18, 72, 107));
		pnlLname.setLayout(null);
		pnlLname.setBounds(450, 10, 210, 40);
		pnlCustomerDetails.add(pnlLname);

		tfLname = new JTextField();
		tfLname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				{
					tfLname.selectAll();
				}
			}

		});
		tfLname.setBackground(Color.WHITE);
		tfLname.setForeground(Color.BLACK);
		tfLname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfLname.setColumns(10);
		tfLname.setBorder(null);
		tfLname.setBounds(10, 5, 180, 30);
		pnlLname.add(tfLname);

		JLabel lblLname = new JLabel("Last Name");
		lblLname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLname.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblLname.setBounds(325, 10, 120, 40);
		pnlCustomerDetails.add(lblLname);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEmail.setBounds(0, 58, 103, 40);
		pnlCustomerDetails.add(lblEmail);

		RoundedSearchPnl pnlEmail = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlEmail.setLayout(null);
		pnlEmail.setBounds(110, 58, 210, 40);
		pnlCustomerDetails.add(pnlEmail);

		tfEmail = new JTextField();
		tfEmail.setBounds(10, 5, 190, 30);
		tfEmail.addFocusListener(new FocusAdapter() {
			{
				tfEmail.selectAll();
			}

		});
		tfEmail.setBackground(Color.WHITE);
		tfEmail.setForeground(Color.BLACK);
		tfEmail.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfEmail.setColumns(10);
		tfEmail.setBorder(null);
		pnlEmail.add(tfEmail);

		JLabel lblPhone = new JLabel("Phone Number");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPhone.setBounds(325, 58, 120, 40);
		pnlCustomerDetails.add(lblPhone);

		RoundedSearchPnl pnlPhone = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(18, 72, 107));
		pnlPhone.setLayout(null);
		pnlPhone.setBounds(450, 58, 210, 40);
		pnlCustomerDetails.add(pnlPhone);

		tfPhone = new JTextField();
		tfPhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfPhone.selectAll();
			}

		});
		tfPhone.setBackground(Color.WHITE);
		tfPhone.setForeground(Color.BLACK);
		tfPhone.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfPhone.setColumns(10);
		tfPhone.setBorder(null);
		tfPhone.setBounds(10, 5, 180, 30);
		pnlPhone.add(tfPhone);

		JPanel pnlEventDetailsContainer = new JPanel();
		pnlEventDetailsContainer.setBackground(new Color(255, 255, 255));
		pnlEventDetailsContainer.setBounds(0, 314, 690, 271);
		pnlContractContainer.add(pnlEventDetailsContainer);
		pnlEventDetailsContainer.setLayout(null);

		JPanel pnlCT_Header_1 = new JPanel();
		pnlCT_Header_1.setLayout(null);
		pnlCT_Header_1.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(192, 192, 192)));
		pnlCT_Header_1.setBackground(Color.WHITE);
		pnlCT_Header_1.setBounds(0, 0, 345, 40);
		pnlEventDetailsContainer.add(pnlCT_Header_1);

		JLabel lblEventDetails = new JLabel("Event Details:");
		lblEventDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblEventDetails.setForeground(new Color(102, 102, 102));
		lblEventDetails.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblEventDetails.setBounds(10, 5, 325, 30);
		pnlCT_Header_1.add(lblEventDetails);

		JPanel pnlEventDetails = new JPanel();
		pnlEventDetails.setBackground(new Color(255, 255, 255));
		pnlEventDetails.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(192, 192, 192)));
		pnlEventDetails.setBounds(0, 40, 345, 235);
		pnlEventDetailsContainer.add(pnlEventDetails);
		pnlEventDetails.setLayout(null);

		RoundedSearchPnl pnlSelectEvent = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlSelectEvent.setBounds(48, 30, 250, 40);
		pnlEventDetails.add(pnlSelectEvent);
		pnlSelectEvent.setLayout(null);

		Event[] cevents = user.chooseEvent();
		JComboBox<String> cbEventName = new JComboBox<String>();
		for (Event event : cevents) {
			cbEventName.addItem(event.getDesc());
		}
		cbEventName.insertItemAt("---Select---", 0);
		cbEventName.setSelectedIndex(0);
		cbEventName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbEventName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbEventName.setBackground(Color.WHITE);
		cbEventName.setBounds(2, 2, 246, 36);
		pnlSelectEvent.add(cbEventName);

		JLabel lblEventName = new JLabel("Event Name");
		lblEventName.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEventName.setBounds(48, 6, 250, 25);
		pnlEventDetails.add(lblEventName);

		RoundedSearchPnl pnlSelectEventPackage = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlSelectEventPackage.setBounds(48, 105, 250, 40);
		pnlEventDetails.add(pnlSelectEventPackage);
		pnlSelectEventPackage.setLayout(null);

		JComboBox<String> cbEventPackages = new JComboBox<String>();

		cbEventName.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (cbEventPackages.getItemCount() > 1) {
					System.out.println("here2");
					cbEventPackages.removeAllItems();
					cbEventPackages.insertItemAt("---Select---", 0);
					cbEventPackages.setSelectedIndex(0);
				}

				if (cbEventName.getSelectedIndex() != 0) {

					// get eventID
					chosen_eventID = cevents[cbEventName.getSelectedIndex() - 1].getId();
					// get packages using the eventID
					chosen_event = cevents[cbEventName.getSelectedIndex() - 1];
					user.chooseEventPackages(chosen_event);
					for (EventPackage cpackages : chosen_event.getPackages()) {
						cbEventPackages.addItem(cpackages.getDesc());
					}
				}
			}
		});
		cbEventPackages.insertItemAt("---Select---", 0);
		cbEventPackages.setSelectedIndex(0);
		cbEventPackages.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbEventPackages.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbEventPackages.setBackground(Color.WHITE);
		cbEventPackages.setBounds(2, 2, 246, 36);
		pnlSelectEventPackage.add(cbEventPackages);

		cbEventPackages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (chosen_event.getPackages() != null) {
						
						// get eventpackageID
						chosen_package = chosen_event.getPackage(cbEventPackages.getSelectedIndex() - 1);
						// // get included items
						tableItems.setModel(user.viewIncludedItems(chosen_package.getId()));
					}
				} catch (Exception e1) {}
			}
		});
		JLabel lblEventPackage = new JLabel("Event Package");
		lblEventPackage.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEventPackage.setBounds(48, 80, 250, 25);
		pnlEventDetails.add(lblEventPackage);

		RoundedSearchPnl pnlDate = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlDate.setLayout(null);
		pnlDate.setBounds(48, 180, 250, 40);
		pnlEventDetails.add(pnlDate);

		tfDate = new JTextField();
		tfDate.setEditable(false);
		tfDate.setText(sdf.format(eventDate));
		tfDate.setForeground(Color.BLACK);
		tfDate.setBackground(Color.white);
		tfDate.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfDate.setColumns(10);
		tfDate.setBorder(null);
		tfDate.setBounds(10, 5, 230, 30);
		pnlDate.add(tfDate);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDate.setBounds(48, 155, 250, 25);
		pnlEventDetails.add(lblDate);

		JPanel pnlItemContainer = new JPanel();
		pnlItemContainer.setBackground(new Color(255, 255, 255));
		pnlItemContainer.setBounds(344, 0, 346, 275);
		pnlEventDetailsContainer.add(pnlItemContainer);
		pnlItemContainer.setLayout(null);

		tableItems = new CustomTable(4, 14);
		spItems = new JScrollPane(tableItems, 20, 31);
		spItems.setBounds(5, 40, 335, 180);
		pnlItemContainer.add(spItems);

		JPanel pnlFooter = new JPanel();
		pnlFooter.setLayout(null);
		pnlFooter.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(192, 192, 192)));
		pnlFooter.setBackground(Color.WHITE);
		pnlFooter.setBounds(0, 225, 345, 50);
		pnlItemContainer.add(pnlFooter);

		RoundedSubmit btnSubmit = new RoundedSubmit(40, 0, new Color(71, 180, 206), new Color(107, 195, 215));
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (isNewCustomer) {
						if (tfFname.getText().equals("") || tfLname.getText().equals("") || tfEmail.getText().equals("")
								|| tfPhone.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "Add Schedule", 1);
						else {
							String fname = tfFname.getText();
							String lname = tfLname.getText();
							String email = tfEmail.getText();
							String phone = tfPhone.getText();
							customer = new Customer(fname, lname, email, phone, user);
						}
					}

					if (chosen_eventID == -1)
						JOptionPane.showMessageDialog(null, "Select Event", "Add Event Schedule", 2);
					else if (chosen_package.getId() == -1)
						JOptionPane.showMessageDialog(null, "Select Event Package", "Add Event Schedule", 2);
					else {
						new ContractPayment_Frame(user, AddSchedule.this, customer, eventDate, chosen_package,
								isNewCustomer, selectedDate).setVisible(true);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setBounds(190, 4, 150, 40);
		pnlFooter.add(btnSubmit);
		btnSubmit.setLayout(null);

		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setBounds(10, 0, 130, 40);
		btnSubmit.add(lblSubmit);

		JLabel lblItemsIncluded = new JLabel("Items Included in the Package");
		lblItemsIncluded.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsIncluded.setForeground(new Color(102, 102, 102));
		lblItemsIncluded.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblItemsIncluded.setBounds(5, 5, 335, 30);
		pnlItemContainer.add(lblItemsIncluded);
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	protected boolean isNewCustomer = true;
	protected Customer customer = null;
	protected int chosen_eventID = -1;
	protected EventPackage chosen_package = null;
}
