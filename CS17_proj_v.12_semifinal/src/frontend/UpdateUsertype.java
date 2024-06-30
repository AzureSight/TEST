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

public class UpdateUsertype extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JLabel lblJobtype, lblUsertypeID;
	private JTextField tfJobtype, tfUsertypeID;

	/**
	 * Create the frame.
	 */
	public UpdateUsertype(Manager manager, EditUsertype_Frame frame, UserType usertype) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(360, 410);
	
		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));
		
		setContentPane(pnlContent);
		pnlContent.setLayout(null);
				
		JPanel container = new JPanel();
		container.setBackground(new Color(244, 244, 255));
		container.setBounds(5, 5, 350, 400);
		pnlContent.add(container);
		container.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update User Type");
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
		
		RoundedSearchPnl pnlUsertypeID = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlUsertypeID.setBorder(null);
		pnlUsertypeID.setBounds(50, 135, 250, 40);
		container.add(pnlUsertypeID);
		pnlUsertypeID.setLayout(null);
		
		tfUsertypeID = new JTextField();
		tfUsertypeID.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfUsertypeID.setBorder(null);
		tfUsertypeID.setText(String.valueOf(usertype.getId()));
		tfUsertypeID.setEditable(false);
		tfUsertypeID.setBackground(new Color(255, 255, 255));
		tfUsertypeID.setBounds(10, 5, 230, 30);
		pnlUsertypeID.add(tfUsertypeID);
		tfUsertypeID.setColumns(10);
		
		lblUsertypeID = new JLabel();
		lblUsertypeID.setText("User type ID");
		lblUsertypeID.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUsertypeID.setBounds(50, 115, 98, 20);
		container.add(lblUsertypeID);
		
		RoundedSearchPnl pnlFname = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlFname.setBorder(null);
		pnlFname.setBounds(50, 206, 250, 40);
		container.add(pnlFname);
		pnlFname.setLayout(null);
		
		tfJobtype = new JTextField();
		tfJobtype.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfJobtype.getText().equals(usertype.getDesc()))
				{
					tfJobtype.setText(usertype.getDesc());
					tfJobtype.selectAll();
				}				
			}
			
			public void focusLost(FocusEvent e) {
				if(tfJobtype.getText().equals(""))
				{
					tfJobtype.setText(usertype.getDesc());				
				}
			}
		});
		tfJobtype.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfJobtype.setBorder(null);
		tfJobtype.setBackground(new Color(255, 255, 255));
		tfJobtype.setBounds(10, 5, 230, 30);
		pnlFname.add(tfJobtype);
		tfJobtype.setColumns(10);
		
		lblJobtype = new JLabel();
		lblJobtype.setText("Type Description");
		lblJobtype.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblJobtype.setVisible(true);
		lblJobtype.setBounds(50, 186, 133, 20);
		container.add(lblJobtype);
		
		RoundedSubmit btnSubmit = new RoundedSubmit(50, 0, new Color(18, 72, 107), new Color(18, 72, 127));
		btnSubmit.setLayout(null);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					int id = Integer.parseInt(tfUsertypeID.getText());
					String desc = tfJobtype.getText();
					
					if(desc.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "Register User", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						manager.updateUserType(new UserType(desc, id));
						frame.table.setModel(manager.viewUserTypes());
						UpdateUsertype.this.dispose();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n"+e1.getMessage(), "Update User Type", 0);
				}
			}
			
		});
		//btnSubmit.setBackground((new Color(71, 180, 206)));
		btnSubmit.setBounds(70, 285, 210, 40);
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