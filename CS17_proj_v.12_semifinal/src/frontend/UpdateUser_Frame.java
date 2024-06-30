package frontend;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.Cursor;

import backend.User;
import backend.User.Manager;
import backend.User.UserType;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;

public class UpdateUser_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfUserid, tfFname, tfLname, tfUsername; private JPasswordField tfPassword;
	private JLabel lblFname; private JLabel lblLname;private JLabel lblUsername; private JLabel lblPassword; private JLabel lblUsertype, lblUserid;    

	/**
	 * Create the frame.
	 */
	public UpdateUser_Frame (Manager manager, User user, ManageUser_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(460, 610);
	
		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));
		
		setContentPane(pnlContent);
		pnlContent.setLayout(null);
				
		JPanel containerRegister = new JPanel();
		containerRegister.setBackground(new Color(244, 244, 255));
		containerRegister.setBounds(5, 5, 450, 600);
		pnlContent.add(containerRegister);
		containerRegister.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update User");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel.setBounds(98, 20, 342, 50);
		containerRegister.add(lblNewLabel);
		
		RoundedSearchPnl pnlUserid= new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlUserid.setBorder(null);
		pnlUserid.setBounds(98, 101, 250, 40);
		containerRegister.add(pnlUserid);
		pnlUserid.setLayout(null);
		
		tfUserid = new JTextField();
		tfUserid.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfUserid.setText(String.valueOf(user.getId()));
		tfUserid.setBorder(null);
		tfUserid.setEditable(false);
		tfUserid.setBackground(Color.WHITE);
		tfUserid.setBounds(10, 5, 230, 30);
		pnlUserid.add(tfUserid);
		tfUserid.setColumns(10);
		
		lblUserid = new JLabel();
		lblUserid.setText("User ID");
		lblUserid.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUserid.setVisible(true);
		lblUserid.setBounds(98, 80, 83, 20);
		containerRegister.add(lblUserid);
		
		RoundedSearchPnl pnlFname = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlFname.setBorder(null);
		pnlFname.setBounds(98, 166, 250, 40);
		containerRegister.add(pnlFname);
		pnlFname.setLayout(null);
		
		tfFname = new JTextField();
		tfFname.setText(user.getFname());
		tfFname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfFname.getText().equals(user.getFname()))
				{
					tfFname.setText(user.getFname());
					tfFname.selectAll();
				}				
			}
			
			public void focusLost(FocusEvent e) {
				if(tfFname.getText().equals(""))
				{
					tfFname.setText(user.getFname());				
				}
			}
			
		});
		tfFname.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfFname.setBorder(null);
		tfFname.setBackground(Color.WHITE);
		tfFname.setBounds(10, 5, 230, 30);
		pnlFname.add(tfFname);
		tfFname.setColumns(10);
		
		lblFname = new JLabel();
		lblFname.setText("First Name");
		lblFname.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblFname.setBounds(98, 146, 83, 20);
		containerRegister.add(lblFname);
		
		RoundedSearchPnl pnlLname = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlLname.setLayout(null);
		pnlLname.setBounds(98, 231, 250, 40);
		containerRegister.add(pnlLname);
		
		tfLname = new JTextField();
		tfLname.setText(user.getLname());
		tfLname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfLname.getText().equals(user.getLname()))
				{
					tfLname.setText(user.getLname());
					tfLname.selectAll();
				}
			
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfLname.getText().equals(""))
				{
					tfLname.setText(user.getLname());
				}
			}
		});
		tfLname.setBorder(null);
		tfLname.setBackground(Color.WHITE);
		tfLname.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfLname.setColumns(10);
		tfLname.setBounds(10, 5, 230, 30);
		pnlLname.add(tfLname);
		
		lblLname = new JLabel();
		lblLname.setText("Last Name");
		lblLname.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblLname.setBounds(98, 211, 73, 20);
		containerRegister.add(lblLname);
		
		lblUsername = new JLabel();
		lblUsername.setText("Username");
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUsername.setBounds(98, 276, 83, 20);
		containerRegister.add(lblUsername);
		
		RoundedSearchPnl pnlUsername = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlUsername.setLayout(null);
		pnlUsername.setBounds(98, 296, 250, 40);
		containerRegister.add(pnlUsername);
		
		tfUsername = new JTextField();
		tfUsername.setText(user.getUsername());
		tfUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfUsername.getText().equals(user.getUsername()))
				{
					tfUsername.setText(user.getUsername());
					tfUsername.selectAll();
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfUsername.getText().equals(""))
				{
					tfUsername.setText(user.getUsername());
				}
			}
		});
		tfUsername.setBorder(null);
		tfUsername.setBackground(Color.WHITE);
		tfUsername.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfUsername.setColumns(10);
		tfUsername.setBounds(10, 5, 230, 30);
		pnlUsername.add(tfUsername);
		
		lblPassword = new JLabel();
		lblPassword.setText("Password");
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblPassword.setBounds(98, 341, 73, 20);
		containerRegister.add(lblPassword);
		
		RoundedSearchPnl pnlPassword = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlPassword.setBackground(Color.WHITE);
		pnlPassword.setLayout(null);
		pnlPassword.setBounds(98, 361, 250, 40);
		containerRegister.add(pnlPassword);
		
		tfPassword = new JPasswordField();
		tfPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(tfPassword.getPassword()).equals(user.getPassword()))
				{
					//tfPassword.setText(user.getPassword());
					tfPassword.selectAll();
				}

			}
			@Override
			public void focusLost(FocusEvent e) {
				if(String.valueOf(tfPassword.getPassword()).equals(""))
				{
					tfPassword.setText(user.getPassword());
				}
			}
		});
		tfPassword.setBorder(null);
		tfPassword.setText(user.getPassword());
		tfPassword.setEchoChar((char)0);  
		tfPassword.setBackground(Color.WHITE);
		tfPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfPassword.setColumns(10);
		tfPassword.setBounds(10, 5, 230, 30);
		pnlPassword.add(tfPassword);
		
		RoundedSearchPnl pnlUsertype = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlUsertype.setLayout(null);
		pnlUsertype.setBounds(98, 426, 250, 40);
		containerRegister.add(pnlUsertype);
		
		UserType[] userTypes = manager.chooseUserType();
		JComboBox<String> cbUsertype = new JComboBox<String>();
		for (UserType userType : userTypes) {
			cbUsertype.addItem(userType.getDesc());
		}
		//cbUsertype.insertItemAt("---Select---", 0);
		cbUsertype.setSelectedItem(user.getType());
		cbUsertype.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbUsertype.setBorder(null);
		cbUsertype.setFont(new Font("SansSerif", Font.PLAIN, 14));
		cbUsertype.setBackground(Color.WHITE);
		cbUsertype.setBounds(2, 2, 246, 36);
		pnlUsertype.add(cbUsertype);		
		
		lblUsertype = new JLabel();
		lblUsertype.setText("User Type");
		lblUsertype.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUsertype.setVisible(true);
		lblUsertype.setBounds(98, 406, 73, 20);
		containerRegister.add(lblUsertype);
		
		Design_Back lbl_Back = new Design_Back ("Back" , 50, this, new Color(244, 244, 255));
		lbl_Back.setText("");
		lbl_Back.setBounds(5, 5, 40, 40);
		containerRegister.add(lbl_Back);
		

		RoundedSubmit btnSubmit = new RoundedSubmit(50, 0, new Color(18, 72, 107), new Color(18, 72, 127));
		btnSubmit.setLayout(null);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int id = Integer.parseInt(tfUserid.getText()); //no textfield for id
				String fname = tfFname.getText();
				String lname = tfLname.getText();
				String username = tfUsername.getText();
				String pass = String.valueOf(((JPasswordField) tfPassword).getPassword());
				UserType type =  userTypes[cbUsertype.getSelectedIndex()];
				
				if(fname.equals("First name") || fname.equals("") || lname.equals("Last name") || lname.equals("") || username.equals("Username") || username.equals("") || pass.equals("Password") || pass.equals("")) {
					JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "Register User", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else {
					try {					
						manager.updateUser(new User(id, fname, lname, username, pass, type));
						frame.table.setModel(manager.viewAllUsers());
						UpdateUser_Frame.this.dispose();
					} catch (IndexOutOfBoundsException e1) {
						JOptionPane.showMessageDialog(null, "Select User Type", "Register User", 2);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Something went wrong.\n"+e1.getMessage(), "Register User", 1);
					
					}
				}
			}
			});
		btnSubmit.setBounds(120, 515, 210, 40);
		containerRegister.add(btnSubmit);
		btnSubmit.setLayout(null);

		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setBounds(10, 0, 190, 40);
		btnSubmit.add(lblSubmit);
				
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
