package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;
import frontend.UIcomponents.CalendarComponents.Cell;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import backend.Customer;
import backend.Order;
import backend.Event.EventPackage;
import backend.Event.EventSchedule;
import backend.Event.EventSchedule.AdditionalItem;
import backend.Item.Cause;
import backend.Order.Payment;
import backend.User;

public class Payment_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfPayment;
	private JTextField tfChange;

	/**
	 * Create the frame.
	 * 
	 * @param total
	 * @param totalAddItem
	 * @param isNewCustomer
	 * @param chosen_package
	 * @param eventDate
	 * @param customer
	 * @param addSchedule
	 * @param user
	 * @param additionalList
	 * @param selectedDate 
	 * @param contractPayment_Frame
	 */
	public Payment_Frame(User user, AddSchedule addSchedule, Customer customer, Date eventDate,
			EventPackage chosen_package, boolean isNewCustomer, double totalAddItem, double total,
			ArrayList<AdditionalItem> additionalList, ContractPayment_Frame frame, Cell selectedDate) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(320, 310);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 2));
		pnlContent.setBackground(new Color(224, 224, 224));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		JPanel containerPayment = new JPanel();
		containerPayment.setBackground(new Color(240, 240, 240));
		containerPayment.setBounds(10, 10, 300, 290);
		pnlContent.add(containerPayment);
		containerPayment.setLayout(null);

		RoundedClearpnl pnlForm = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlForm.setBounds(10, 10, 280, 270);
		containerPayment.add(pnlForm);
		pnlForm.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlHeader.setBackground(Color.WHITE);
		pnlHeader.setBounds(0, 5, 280, 50);
		pnlForm.add(pnlHeader);
		pnlHeader.setLayout(null);

		Design_Back lbl_Back = new Design_Back("Back", 50, this, new Color(255, 255, 255));
		lbl_Back.setBounds(5, 0, 40, 40);
		pnlHeader.add(lbl_Back);
		lbl_Back.setText("");

		JLabel lblItemHeader = new JLabel("Payment");
		lblItemHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemHeader.setForeground(new Color(102, 102, 102));
		lblItemHeader.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblItemHeader.setBounds(0, 0, 280, 40);
		pnlHeader.add(lblItemHeader);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(new Color(255, 255, 255));
		pnlBody.setBounds(0, 60, 280, 150);
		pnlForm.add(pnlBody);
		pnlBody.setLayout(null);

		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setForeground(new Color(102, 102, 102));
		lblPayment.setFont(new Font("SansSerif", Font.BOLD, 17));
		lblPayment.setBounds(50, 5, 180, 20);
		pnlBody.add(lblPayment);

		RoundedSearchPnl pnlPayment = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlPayment.setLayout(null);
		pnlPayment.setBounds(50, 27, 180, 40);
		pnlBody.add(pnlPayment);

		tfPayment = new JTextField();
		tfPayment.setForeground(Color.DARK_GRAY);
		tfPayment.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfPayment.setColumns(10);
		tfPayment.setBorder(null);
		tfPayment.setBounds(10, 5, 160, 30);
		pnlPayment.add(tfPayment);

		JLabel lblChange = new JLabel("Change");
		lblChange.setForeground(new Color(102, 102, 102));
		lblChange.setFont(new Font("SansSerif", Font.BOLD, 17));
		lblChange.setBounds(50, 85, 180, 20);
		pnlBody.add(lblChange);

		RoundedSearchPnl pnlChange = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlChange.setLayout(null);
		pnlChange.setBounds(50, 107, 180, 40);
		pnlBody.add(pnlChange);

		tfChange = new JTextField();
		tfChange.setEditable(false);
		tfChange.setBackground(Color.WHITE);
		tfChange.setForeground(Color.DARK_GRAY);
		tfChange.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfChange.setColumns(10);
		tfChange.setBorder(null);
		tfChange.setBounds(10, 5, 160, 30);
		pnlChange.add(tfChange);

		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(Color.WHITE);
		pnlFooter.setBounds(0, 215, 280, 50);
		pnlForm.add(pnlFooter);
		pnlFooter.setLayout(null);

		RoundedSubmit btnSubmit = new RoundedSubmit(40, 0, new Color(71, 180, 206), new Color(107, 195, 215));
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					int orderID = -1;

					double payment = Double.parseDouble(tfPayment.getText());

					if (tfPayment.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields");
					else if (payment < total)
						JOptionPane.showMessageDialog(null, "Insufficient payment");
					else {
						EventSchedule sched = null;
						if (isNewCustomer) {
							sched = new EventSchedule(eventDate, chosen_package.getEvent(), customer, chosen_package,
									new Order(eventDate, total, customer));
							orderID = user.newCustomerEvent(customer, sched);
						} else {
							sched = new EventSchedule(eventDate, chosen_package.getEvent(), customer, chosen_package,
									new Order(eventDate, total, customer));
							orderID = user.regCustomerEvent(customer, sched);
						}
						if (orderID != -1) {
							sched.setOrder(new Order(eventDate, total, customer));
							for (int i = 0; i < additionalList.size(); i++) {
								additionalList.get(i).setEventdate(sched);
								user.takeAdditionalItems(additionalList.get(i));
							}
							Order order = new Order(orderID, eventDate, total, customer);
							Payment payment_detail = new Payment(eventDate, payment, order);
							Cause cause = user.searchCause("Sold");

							//included items
							user.insertPayment(payment_detail, cause.getId(), chosen_package.getId(), eventDate);

							//additional items
							if(additionalList.size() != 0)
								user.insertPayment(payment_detail, cause.getId(), eventDate);

							selectedDate.asUnavailable(sched);
							selectedDate.repaint();
						}
						double change = payment - total;
						tfChange.setText(df.format(change));
						btnSubmit.setVisible(false);

						frame.dispose();
						addSchedule.dispose();
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Kindly enter the field correctly", "Invalid Input", 0);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e2.getMessage());
					e2.printStackTrace();
				}
			}
		});
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setBounds(70, 8, 140, 40);
		pnlFooter.add(btnSubmit);
		btnSubmit.setLayout(null);

		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setBounds(10, 0, 120, 40);
		btnSubmit.add(lblSubmit);

		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	private DecimalFormat df = new DecimalFormat("0.00");
}
