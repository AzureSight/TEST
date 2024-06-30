package frontend;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing. JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import backend.User;
import backend.Event.EventSchedule;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedSearchPnl;

import javax.swing.JTextField;

public class EventToday extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfFname;
	private JTextField tfLname;
	private JTextField tfEmail;
	private JTextField tfPhone;
	private JTextField tfDate;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Create the frame.
	 */
	public EventToday(User user, EventSchedule bookedSchedule) {
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
		
		Design_Back lbl_Back = new Design_Back ("Back" , 50, this, new Color(238, 237, 237));
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
		
		JLabel lblNewLabel = new JLabel("Today's Event");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblNewLabel.setBounds(10, 11, 690, 50);
		pnlHeader.add(lblNewLabel);
		
		RoundedSearchPnl pnlFname = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlFname.setBounds(58, 124, 270, 40);
		container.add(pnlFname);
		pnlFname.setLayout(null);
		
		tfFname = new JTextField(bookedSchedule.getCustomer().getFname());
		tfFname.setBackground(Color.white);
		tfFname.setEditable(false);		
		tfFname.setBorder(null);
		tfFname.setForeground(Color.BLACK);
		tfFname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfFname.setBounds(10, 5, 250, 30);
		pnlFname.add(tfFname);
		tfFname.setColumns(10);
			
		JLabel lblFname = new JLabel("First Name");
		lblFname.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblFname.setBounds(58, 100, 147, 25);
		lblFname.setVisible(true);
		container.add(lblFname);
		
		RoundedSearchPnl pnlLname = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlLname.setLayout(null);
		pnlLname.setBounds(370, 124, 270, 40);
		container.add(pnlLname);
		
		tfLname = new JTextField(bookedSchedule.getCustomer().getLname());
		tfLname.setEditable(false);
		tfLname.setForeground(Color.BLACK);
		tfLname.setBackground(Color.white);
		tfLname.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfLname.setColumns(10);
		tfLname.setBorder(null);
		tfLname.setBounds(10, 5, 250, 30);
		pnlLname.add(tfLname);
		
		RoundedSearchPnl pnlEmail = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlEmail.setLayout(null);
		pnlEmail.setBounds(58, 200, 270, 40);
		container.add(pnlEmail);
		
		tfEmail = new JTextField(bookedSchedule.getCustomer().getEmail());
		tfEmail.setEditable(false);
		tfEmail.setForeground(Color.BLACK);
		tfEmail.setBackground(Color.white);
		tfEmail.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfEmail.setColumns(10);
		tfEmail.setBorder(null);
		tfEmail.setBounds(10, 5, 250, 30);
		pnlEmail.add(tfEmail);
		
		RoundedSearchPnl pnlPhone = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlPhone.setLayout(null);
		pnlPhone.setBounds(370, 200, 270, 40);
		container.add(pnlPhone);
		
		tfPhone = new JTextField(bookedSchedule.getCustomer().getContactnum());
		tfPhone.setEditable(false);
		tfPhone.setBackground(Color.white);
		
		tfPhone.setForeground(Color.BLACK);
		tfPhone.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfPhone.setColumns(10);
		tfPhone.setBorder(null);
		tfPhone.setBounds(10, 5, 250, 30);
		pnlPhone.add(tfPhone);
		
		JLabel lblLname = new JLabel("Last Name");
		lblLname.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblLname.setBounds(370, 99, 125, 25);
		container.add(lblLname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEmail.setBounds(58, 175, 125, 25);
		container.add(lblEmail);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPhoneNumber.setBounds(370, 175, 125, 25);
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
		
		RoundedSearchPnl pnlSelectEvent = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlSelectEvent.setBounds(58, 343, 270, 40);
		container.add(pnlSelectEvent);
		pnlSelectEvent.setLayout(null);
		
		JTextField tfEventName = new JTextField(bookedSchedule.getEvent().getDesc());
		tfEventName.setEditable(false);
		tfEventName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfEventName.setBackground(Color.WHITE);
		tfEventName.setBounds(2, 2, 266, 36);
		pnlSelectEvent.add(tfEventName);
        
        RoundedSearchPnl pnlSelectEventPackage = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlSelectEventPackage.setBounds(370, 343, 270, 40);
		container.add(pnlSelectEventPackage);
		pnlSelectEventPackage.setLayout(null);
		
		JTextField tfEventPackage = new JTextField(bookedSchedule.getEventpackage().getDesc());
		tfEventPackage.setEditable(false);
		tfEventPackage.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfEventPackage.setBackground(Color.WHITE);
		tfEventPackage.setBounds(2, 2, 266, 36);
		pnlSelectEventPackage.add(tfEventPackage);
		
		JLabel lblEventName = new JLabel("Event Name");
		lblEventName.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEventName.setBounds(58, 316, 125, 25);
		container.add(lblEventName);
		
		RoundedSearchPnl pnlDate = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlDate.setLayout(null);
		pnlDate.setBounds(58, 417, 270, 40);
		container.add(pnlDate);
		
		tfDate = new JTextField(sdf.format(bookedSchedule.getDate()));
		tfDate.setEditable(false);
		tfDate.setForeground(Color.BLACK);
		tfDate.setBackground(Color.white);
		tfDate.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfDate.setColumns(10);
		tfDate.setBorder(null);
		tfDate.setBounds(10, 5, 250, 30);
		pnlDate.add(tfDate);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDate.setBounds(58, 394, 125, 25);
		container.add(lblDate);
		
		JLabel lblEventsPackages = new JLabel("Event Packages");
		lblEventsPackages.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEventsPackages.setBounds(370, 319, 147, 25);
		container.add(lblEventsPackages);
        
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
