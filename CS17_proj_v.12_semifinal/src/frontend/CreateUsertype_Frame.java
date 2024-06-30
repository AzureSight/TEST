package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import backend.User.Manager;
import backend.User.UserType;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateUsertype_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JLabel lblJobtype;
	private JTextField tfJobtype;

	/**
	 * Create the frame.
	 */
	public CreateUsertype_Frame(Manager manager, EditUsertype_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(360, 345);
	
		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));
		
		setContentPane(pnlContent);
		pnlContent.setLayout(null);
				
		JPanel container =  new JPanel();
		container.setBackground(new Color(244, 244, 255));
		container.setBounds(5, 5, 350, 335);
		pnlContent.add(container);
		container.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create User Type");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setBorder(null);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel.setBounds(50, 55, 300, 50);
		container.add(lblNewLabel);
		
		Design_Back lbl_Back = new Design_Back ("Back" , 50, this, new Color(244, 244, 255));
		lbl_Back.setText("");
		lbl_Back.setBounds(5, 5, 40, 40);
		container.add(lbl_Back);
		
		RoundedSearchPnl pnlUsertype = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlUsertype.setBorder(null);
		pnlUsertype.setBounds(50, 135, 250, 40);
		container.add(pnlUsertype);
		pnlUsertype.setLayout(null);
		
		tfJobtype = new JTextField();
		tfJobtype.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
					tfJobtype.selectAll();
				
			}

		});
		tfJobtype.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfJobtype.setBorder(null);
		tfJobtype.setBackground(Color.WHITE);
		tfJobtype.setBounds(10, 5, 230, 30);
		pnlUsertype.add(tfJobtype);
		tfJobtype.setColumns(10);
		
		lblJobtype = new JLabel();
		lblJobtype.setForeground(Color.BLACK);
		lblJobtype.setText("User type");
		lblJobtype.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblJobtype.setBounds(50, 115, 83, 20);
		container.add(lblJobtype);
		
		RoundedSubmit btnSubmit = new RoundedSubmit(50, 0, new Color(18, 72, 107), new Color(18, 72, 127));
		btnSubmit.setLayout(null);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
					String desc = tfJobtype.getText();
				if(desc.equals("")) {
					JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "Register User", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else {
				try {
				
					manager.createUserType(new UserType(desc));
					frame.table.setModel(manager.viewUserTypes());
					CreateUsertype_Frame.this.dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n"+e1.getMessage(), "Create User Type", 0);
				}
			}
			}
		});
		btnSubmit.setBounds(70, 225, 210, 40);
		container.add(btnSubmit);
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