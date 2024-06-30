package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import backend.Customer;
import backend.Event;
import backend.User;
import backend.Event.EventSchedule;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;
import frontend.UIcomponents.CalendarComponents.PanelDate;

import com.toedter.calendar.JDateChooser;
import java.awt.Cursor;

public class UpdateSchedule extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfFname;
	private JTextField tfLname;
	private JTextField tfEmail;
	private JTextField tfPhone;

	/**
	 * Create the frame.
	 */
	public UpdateSchedule(User user, EventSchedule bookedSchedule, PanelDate datepanel, Scheduling_Frame sched_frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(720, 620);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		JPanel container = new JPanel();
		container.setBackground(new Color(238, 237, 237));
		container.setBounds(5, 5, 710, 610);
		pnlContent.add(container);
		container.setLayout(null);

		Design_Back lbl_Back = new Design_Back("Back", 50, this, new Color(238, 237, 237));
		lbl_Back.setBackgroundColor(new Color(238, 237, 237));
		lbl_Back.setText("");
		lbl_Back.setBounds(10, 10, 50, 50);
		container.add(lbl_Back);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(null);
		pnlHeader.setBackground(new Color(238, 237, 237));
		pnlHeader.setBounds(0, 0, 710, 64);
		container.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblNewLabel = new JLabel("Update Schedule");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblNewLabel.setBounds(10, 11, 690, 50);
		pnlHeader.add(lblNewLabel);

		RoundedSearchPnl pnlFname = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlFname.setBounds(58, 200, 270, 40);
		container.add(pnlFname);
		pnlFname.setLayout(null);

		tfFname = new JTextField(bookedSchedule.getCustomer().getFname());
		tfFname.setBorder(null);
		tfFname.setBackground(Color.WHITE);
		tfFname.setForeground(Color.BLACK);
		tfFname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfFname.setBounds(10, 5, 250, 30);
		pnlFname.add(tfFname);
		tfFname.setColumns(10);

		JLabel lblFname = new JLabel("First Name");
		lblFname.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblFname.setBounds(58, 175, 125, 25);
		container.add(lblFname);

		RoundedSearchPnl pnlLname = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlLname.setLayout(null);
		pnlLname.setBounds(370, 200, 270, 40);
		container.add(pnlLname);

		tfLname = new JTextField(bookedSchedule.getCustomer().getLname());
		tfFname.setBackground(Color.WHITE);
		tfLname.setForeground(Color.BLACK);
		tfLname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfLname.setColumns(10);
		tfLname.setBorder(null);
		tfLname.setBounds(10, 5, 250, 30);
		pnlLname.add(tfLname);

		RoundedSearchPnl pnlEmail = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlEmail.setLayout(null);
		pnlEmail.setBounds(58, 124, 270, 40);
		container.add(pnlEmail);

		tfEmail = new JTextField(bookedSchedule.getCustomer().getEmail());
		tfFname.setBackground(Color.WHITE);
		tfEmail.setForeground(Color.BLACK);
		tfEmail.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfEmail.setColumns(10);
		tfEmail.setBorder(null);
		tfEmail.setBounds(10, 5, 250, 30);
		pnlEmail.add(tfEmail);

		RoundedSearchPnl pnlPhone = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlPhone.setLayout(null);
		pnlPhone.setBounds(370, 124, 270, 40);
		container.add(pnlPhone);

		tfPhone = new JTextField(bookedSchedule.getCustomer().getContactnum());
		tfFname.setBackground(Color.WHITE);
		tfPhone.setForeground(Color.BLACK);
		tfPhone.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfPhone.setColumns(10);
		tfPhone.setBorder(null);
		tfPhone.setBounds(10, 5, 250, 30);
		pnlPhone.add(tfPhone);

		JLabel lblLname = new JLabel("Last Name");
		lblLname.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblLname.setBounds(370, 175, 125, 25);
		container.add(lblLname);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEmail.setBounds(58, 100, 147, 25);
		container.add(lblEmail);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPhoneNumber.setBounds(370, 99, 125, 25);
		container.add(lblPhoneNumber);

		JLabel lblCustomerDetails = new JLabel("  Customer Details:");
		lblCustomerDetails.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(18, 72, 107)));
		lblCustomerDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomerDetails.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCustomerDetails.setBounds(0, 70, 710, 25);
		container.add(lblCustomerDetails);

		JLabel lblEventDetails = new JLabel("  Event Details:");
		lblEventDetails.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(18, 72, 107)));
		lblEventDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblEventDetails.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEventDetails.setBounds(0, 280, 710, 25);
		container.add(lblEventDetails);

		RoundedSearchPnl pnlSelectEvent = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlSelectEvent.setBounds(58, 343, 270, 40);
		container.add(pnlSelectEvent);
		pnlSelectEvent.setLayout(null);

		JTextField cbEventName = new JTextField(bookedSchedule.getEvent().getDesc());
		cbEventName.setEditable(false);
		cbEventName.setBackground(Color.WHITE);
		cbEventName.setForeground(Color.BLACK);
		cbEventName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbEventName.setBounds(2, 2, 266, 36);
		pnlSelectEvent.add(cbEventName);

		RoundedSearchPnl pnlSelectEventPackage = new RoundedSearchPnl(25, 2, new Color(255, 255, 255),
				new Color(18, 72, 107), new Color(18, 72, 107));
		pnlSelectEventPackage.setBounds(370, 343, 270, 40);
		container.add(pnlSelectEventPackage);
		pnlSelectEventPackage.setLayout(null);

		JTextField cbEventPackages = new JTextField(bookedSchedule.getEventpackage().getDesc());
		cbEventPackages.setEditable(false);
		cbEventPackages.setBackground(Color.WHITE);
		cbEventPackages.setForeground(Color.BLACK);
		cbEventPackages.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbEventPackages.setBounds(2, 2, 266, 36);
		pnlSelectEventPackage.add(cbEventPackages);

		JLabel lblEventName = new JLabel("Event Name");
		lblEventName.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEventName.setBounds(58, 316, 125, 25);
		container.add(lblEventName);

		RoundedSearchPnl pnlDate = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlDate.setLayout(null);
		pnlDate.setBounds(58, 417, 270, 40);
		container.add(pnlDate);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDate(bookedSchedule.getDate());
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setFont(new Font("SansSerif", Font.PLAIN, 15));
		dateChooser.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dateChooser.getCalendarButton().setBorder(null);
		dateChooser.setBackground(Color.WHITE);
		dateChooser.getCalendarButton().setHorizontalAlignment(SwingConstants.LEFT);
		dateChooser.getCalendarButton().setFont(new Font("SansSerif", Font.PLAIN, 15));
		dateChooser.setBorder(null);
		dateChooser.setBounds(2, 2, 266, 36);
		pnlDate.add(dateChooser);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDate.setBounds(58, 394, 125, 25);
		container.add(lblDate);

		JLabel lblEventsPackages = new JLabel("Event Packages");
		lblEventsPackages.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEventsPackages.setBounds(370, 319, 147, 25);
		container.add(lblEventsPackages);

		RoundedSubmit pnlSubmit = new RoundedSubmit(50, 0, new Color(18, 72, 107), new Color(18, 72, 127));
		pnlSubmit.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					if (tfFname.getText().equals("") || tfLname.getText().equals("") || tfEmail.getText().equals("")
							|| tfPhone.getText().equals(""))
						JOptionPane.showMessageDialog(null, "kindly fill the empty fields");
					else {
						String fname = tfFname.getText();
						String lname = tfLname.getText();
						String email = tfEmail.getText();
						String contactnum = tfPhone.getText();

						Customer new_cust = new Customer(bookedSchedule.getCustomer().getId(), fname, lname, email,
								contactnum, user);

						System.out.println(bookedSchedule.getCustomer().getId());
						user.updateEventSchedule(new EventSchedule(new_cust));
						sched_frame.dispose();
						new Scheduling_Frame(user).setVisible(true);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(pnlSubmit, "Something went wrong!\n" + e1.getMessage(),
							"Update Event Schedule", 0);
				}
			}
		});
		pnlSubmit.setBounds(530, 530, 170, 40);
		container.add(pnlSubmit);
		pnlSubmit.setLayout(null);

		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setBounds(10, 0, 150, 40);
		pnlSubmit.add(lblSubmit);

		RoundedClearpnl pblRebook = new RoundedClearpnl(35, 0, new Color(225, 127, 107), new Color(220, 144, 127));
		pblRebook.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Calendar calendar = Calendar.getInstance();
				Date current_date = calendar.getTime();
				try {
					if (dateChooser.getDate() == null)
						JOptionPane.showMessageDialog(null, "Please pick a date", "Reschedule Event", 2);
					else {
						reschedule_date = new Date(dateChooser.getDate().getTime());
						if (reschedule_date.before(current_date))
							JOptionPane.showMessageDialog(null,
									"The date you've chosen is already passed. Please pick a date that is ahead of the current date.");
						else {
							Calendar extract_reschedDate = Calendar.getInstance(),
									extract_bookedDate = Calendar.getInstance();
							extract_reschedDate.setTime(reschedule_date);
							extract_bookedDate.setTime(bookedSchedule.getDate());
							int r_year = extract_reschedDate.get(Calendar.YEAR),
									b_year = extract_bookedDate.get(Calendar.YEAR);
							int r_month = extract_reschedDate.get(Calendar.MONTH) + 1,
									b_month = extract_bookedDate.get(Calendar.MONTH) + 1;
							int r_day = extract_reschedDate.get(Calendar.DAY_OF_MONTH),
									b_day = extract_bookedDate.get(Calendar.DAY_OF_MONTH);
							EventSchedule check_bookedschedule = user.viewIfScheduleBooked(r_month, r_year, r_day);

							if (check_bookedschedule != null)
								JOptionPane.showMessageDialog(null,
										"This date is already taken. Please pick a date that is available");
							else {
								Event current_event = bookedSchedule.getEvent();
								Customer current_customer = bookedSchedule.getCustomer();
								EventSchedule new_schedule = new EventSchedule(reschedule_date,
										current_event, current_customer, bookedSchedule.getEventpackage());
								user.rescheduleEvent(new_schedule, bookedSchedule.getDate());

								for (int i = 0; i < datepanel.getDaynums().length; i++) {

									if (datepanel.getDaynums()[i].isDate(b_day, b_month, b_year)) {
										datepanel.getDaynums()[i].asAvailable();
										datepanel.getDaynums()[i].repaint();
									}
									if (datepanel.getYear() != r_year)
										continue;
									if (datepanel.getMonth() != r_month)
										continue;
									if (datepanel.getDaynums()[i].isDate(r_day, r_month, r_year)) {
										datepanel.getDaynums()[i].asUnavailable(new_schedule);
										datepanel.getDaynums()[i].repaint();
									}
								}
								UpdateSchedule.this.dispose();
							}
						}
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage(), "Reschedule Event",
							0);
				}
			}
		});
		pblRebook.setLayout(null);
		pblRebook.setBounds(338, 417, 130, 40);
		container.add(pblRebook);

		JLabel lblRebook = new JLabel("Rebook");
		lblRebook.setHorizontalAlignment(SwingConstants.CENTER);
		lblRebook.setForeground(Color.WHITE);
		lblRebook.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblRebook.setBounds(10, 0, 110, 40);
		pblRebook.add(lblRebook);

		setUndecorated(true);
		setLocationRelativeTo(null);
	}

	private Date reschedule_date;
}
