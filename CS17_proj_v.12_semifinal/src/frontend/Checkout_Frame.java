package frontend;

import java.awt.Color;
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

import backend.Customer;
import backend.Item;
import backend.Item.Cause;
import backend.Order;
import backend.Order.OrderedItem;
import backend.Order.Payment;
import backend.User.Employee;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.Design_SearchText;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.TableFormats;

import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

public class Checkout_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfLname;
	private JTextField tfEmail;
	private JTextField tfPhone;
	private JTextField tfAmount;
	private JTextField tfPaymentAmount;
	private JTextField tfSearch;
	private RoundedClearpnl pnlSubmit;
	private CustomTable custtable;
	private JScrollPane sp;
	private Image img_Search = new ImageIcon(Login.class.getResource("resources/SearchUser.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private JTextField tfFname;

	/**
	 * Create the frame.
	 */
	public Checkout_Frame(Employee emp, double amount, double payment, TableModel order_model, ArrayList<Item> itemlist) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(790, 810);
	
		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);
				
		JPanel container = new JPanel();
		container.setBackground(new Color(238, 237, 237));
		container.setBounds(5, 5, 780, 800);
		pnlContent.add(container);
		container.setLayout(null);
		
		Design_Back lbl_Back = new Design_Back ("Back" , 50, this, new Color(238, 237, 237));
		lbl_Back.setBackgroundColor(new Color(238, 237, 237));
		lbl_Back.setText("");
		lbl_Back.setBounds(10, 5, 40, 40);
		container.add(lbl_Back);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(null);
		pnlHeader.setBackground(new Color(238, 237, 237));
		pnlHeader.setBounds(0, 0, 780, 50);
		container.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Customer");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblNewLabel.setBounds(0, 0, 780, 53);
		pnlHeader.add(lblNewLabel);
		
		tfFname = new JTextField();
		tfFname.setBorder(null);
		tfFname.setForeground(Color.BLACK);
		tfFname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfFname.setBounds(10, 5, 250, 30);
		tfFname.setColumns(10);
		
		tfLname = new JTextField();
		tfLname.setForeground(Color.BLACK);
		tfLname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfLname.setColumns(10);
		tfLname.setBorder(null);
		tfLname.setBounds(10, 5, 250, 30);
		
		tfEmail = new JTextField();
		tfEmail.setForeground(Color.BLACK);
		tfEmail.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfEmail.setColumns(10);
		tfEmail.setBorder(null);
		tfEmail.setBounds(10, 5, 250, 30);
		
		RoundedSearchPnl pnlAmount = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlAmount.setLayout(null);
		pnlAmount.setBounds(82, 662, 270, 40);
		container.add(pnlAmount);
		
		tfAmount = new JTextField(String.valueOf(amount));
		tfAmount.setBackground(Color.white);
		tfAmount.setEditable(false);
		tfAmount.setForeground(Color.BLACK);
		tfAmount.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfAmount.setColumns(10);
		tfAmount.setBorder(null);
		tfAmount.setBounds(10, 5, 250, 30);
		tfAmount.setHorizontalAlignment(JTextField.RIGHT);
		pnlAmount.add(tfAmount);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblAmount.setBounds(82, 637, 125, 25);
		container.add(lblAmount);
		
		JLabel lblEventDetails = new JLabel("CHECKOUT");
		lblEventDetails.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(18, 72, 107)));
		lblEventDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventDetails.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEventDetails.setBounds(0, 395, 780, 25);
		container.add(lblEventDetails);
		
		pnlSubmit = new RoundedClearpnl(50, 0, new Color(18, 72, 107), new Color(18, 72, 127));
		pnlSubmit.setBorder(null);
		pnlSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int orderID = -1;
					Date orderdate = new Date();
					if (isNewCustomer) {
						if(tfFname.getText().equals("") || tfLname.getText().equals("") || tfEmail.getText().equals("") || tfPhone.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "Add Schedule", 1);
						else {							
							String fname = tfFname.getText();
							String lname = tfLname.getText();
							String email = tfEmail.getText();
							String phone = tfPhone.getText();
							customer = new Customer(fname, lname, email, phone, emp);
							orderID = emp.newCustomerOrder(customer, new Order(orderdate, amount, customer));
						}
					}
					else {
						System.out.println(amount);
						orderID = emp.regCustomerOrder(customer, new Order(orderdate, amount, customer));
					}

					if(orderID != -1) {
						Order order = new Order(orderID, amount);
						for (int i = 0; i < order_model.getRowCount(); i++) {
							Item item = itemlist.get(i);
							int qty = Integer.parseInt(order_model.getValueAt(i, 2).toString());
							double orderprice = Double.parseDouble(order_model.getValueAt(i, 1).toString())
									/ Integer.parseInt(order_model.getValueAt(i, 2).toString());
							OrderedItem order_item = new OrderedItem(item, order, qty, orderprice);
							emp.takeOrderedItems(order_item);
						}
						Payment payment_detail = new Payment(orderdate, payment, order);
						Cause cause = emp.searchCause("Sold");
						emp.insertPayment(payment_detail, cause.getId());
						change = payment - amount;

						PopFrame frame = new PopFrame(emp, change, Checkout_Frame.this);
						frame.setVisible(true);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n"+e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		pnlSubmit.setBounds(600, 741, 170, 40);
		container.add(pnlSubmit);
		pnlSubmit.setLayout(null);
		
		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setBounds(10, 0, 150, 40);
		pnlSubmit.add(lblSubmit);
		
		JLabel lblCustomerDetails_1 = new JLabel("  Customer Details:");
		lblCustomerDetails_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomerDetails_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCustomerDetails_1.setBorder(null);
		lblCustomerDetails_1.setBounds(0, 420, 780, 25);
		container.add(lblCustomerDetails_1);
		
		JLabel lblFname = new JLabel("First Name");
		lblFname.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblFname.setBounds(82, 456, 125, 25);
		container.add(lblFname);
		
		RoundedSearchPnl pnlFname = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlFname.setLayout(null);
		pnlFname.setBounds(82, 481, 270, 40);
		container.add(pnlFname);
		
		tfFname = new JTextField();
		tfFname.setColumns(10);
		tfFname.setBorder(null);
		tfFname.setBounds(10, 5, 250, 30);
		pnlFname.add(tfFname);
		
		JLabel lblPhoneNumber_1_1 = new JLabel("Last Name");
		lblPhoneNumber_1_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPhoneNumber_1_1.setBounds(372, 456, 125, 25);
		container.add(lblPhoneNumber_1_1);
		
		RoundedSearchPnl pnlLname = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlLname.setLayout(null);
		pnlLname.setBounds(372, 481, 270, 40);
		container.add(pnlLname);
		
		tfLname = new JTextField();
		tfLname.setBorder(null);
		tfLname.setColumns(10);
		tfLname.setBounds(10, 5, 250, 30);
		pnlLname.add(tfLname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEmail.setBounds(82, 529, 125, 25);
		container.add(lblEmail);
		
		RoundedSearchPnl pnlEmail = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlEmail.setLayout(null);
		pnlEmail.setBounds(82, 554, 270, 40);
		container.add(pnlEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBorder(null);
		tfEmail.setColumns(10);
		tfEmail.setBounds(10, 5, 250, 30);
		pnlEmail.add(tfEmail);
		
		JLabel lblPhone = new JLabel("Phone Number");
		lblPhone.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPhone.setBounds(372, 529, 125, 25);
		container.add(lblPhone);
		
		RoundedSearchPnl pnlPhone = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlPhone.setLayout(null);
		pnlPhone.setBounds(372, 554, 270, 40);
		container.add(pnlPhone);
		
		tfPhone = new JTextField();
		tfPhone.setBorder(null);
		tfPhone.setColumns(10);
		tfPhone.setBounds(10, 5, 250, 30);
		pnlPhone.add(tfPhone);
		
		JLabel lblPurchase = new JLabel("  Purchase Details:");
		lblPurchase.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchase.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPurchase.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(18, 72, 107)));
		lblPurchase.setBounds(0, 601, 780, 25);
		container.add(lblPurchase);
		
		JLabel lblPaymentAmount = new JLabel("Payment Amount");
		lblPaymentAmount.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPaymentAmount.setBounds(372, 637, 125, 25);
		container.add(lblPaymentAmount);
		
		RoundedSearchPnl pnlPaymentAmount = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlPaymentAmount.setLayout(null);
		pnlPaymentAmount.setBounds(372, 662, 270, 40);
		container.add(pnlPaymentAmount);
		
		tfPaymentAmount = new JTextField(String.valueOf(payment));
		tfPaymentAmount.setEditable(false);
		tfPaymentAmount.setBackground(Color.white);
		tfPaymentAmount.setForeground(Color.BLACK);
		tfPaymentAmount.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfPaymentAmount.setColumns(10);
		tfPaymentAmount.setBorder(null);
		tfPaymentAmount.setBounds(10, 5, 250, 30);
		tfPaymentAmount.setHorizontalAlignment(JTextField.RIGHT);
		pnlPaymentAmount.add(tfPaymentAmount);
		
		RoundedSearchPnl pnlSearch = new RoundedSearchPnl(25, 2, Color.white, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlSearch.setBounds(520, 55, 250, 40);
		container.add(pnlSearch);
		pnlSearch.setBackground(Color.WHITE);
		pnlSearch.setLayout(null);

		custtable= new CustomTable(3, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if(dataModel.getColumnCount() > 0)
					TableFormats.toCustomerTable(this);
			}
		};
		
		tfSearch = new Design_SearchText();
		tfSearch.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				String search = tfSearch.getText();
				if (search.equals("")) {
					custtable.setModel(emp.viewAllCustomer());
					tfFname.setEditable(true);
					tfLname.setEditable(true);
					tfEmail.setEditable(true);
					tfPhone.setEditable(true);
					tfFname.setText("");
					tfLname.setText("");
					tfEmail.setText("");
					tfPhone.setText("");
					isNewCustomer = true;
				}
				else {
					try {
						custtable.setModel(emp.searchCustomer(search));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "A search problem has occured!\n"+e1.getMessage(), "Search Customer", 0);
					}
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
		
		custtable.setModel(emp.viewAllCustomer());
		sp = new JScrollPane(custtable, 20, 31);
		custtable.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				int c = custtable.getSelectedRow();
				TableModel cust_model = custtable.getModel();
				int id = Integer.parseInt(cust_model.getValueAt(c, 0).toString());
				String fname = cust_model.getValueAt(c, 1).toString();
				String lname = cust_model.getValueAt(c, 2).toString();
				String email = cust_model.getValueAt(c, 3).toString();

				customer = new Customer(id, fname, lname, email, email, emp);
				tfFname.setText(customer.getFname());
				tfLname.setText(customer.getLname());
				tfEmail.setText(customer.getEmail());
				tfPhone.setText(customer.getContactnum());

				tfFname.setEditable(false);
				tfLname.setEditable(false);
				tfEmail.setEditable(false);
				tfPhone.setEditable(false);
				
				isNewCustomer = false;
				custtable.clearSelection();
				
			}
		});
		sp.setBounds(10, 105, 760, 280);
		container.add(sp);
        
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
	private Customer customer;
	private boolean isNewCustomer = true;
	private double change = 0;
}
