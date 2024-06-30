package frontend;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.table.TableModel;

import backend.User.Manager;
import backend.User.UserType;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedBorderPanel;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.TableFormats;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditUsertype_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private Image img_Create = new ImageIcon(Login.class.getResource("resources/Add.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_Update = new ImageIcon(Login.class.getResource("resources/Update.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_Delete = new ImageIcon(Login.class.getResource("resources/Delete.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	protected CustomTable table;
	private JScrollPane scrollpane;

	/**
	 * Create the frame.
	 */
	public EditUsertype_Frame(Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(460, 610);
	
		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));
		
		setContentPane(pnlContent);
		pnlContent.setLayout(null);
				
		JPanel container = new JPanel();
		container.setBackground(new Color(244, 244, 255));
		container.setBounds(5, 5, 450, 600);
		pnlContent.add(container);
		container.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit User Type");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setBorder(null);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 40, 450, 75);
		container.add(lblNewLabel);
		
		Design_Back lbl_Back = new Design_Back ("Back" , 50, this, new Color(244, 244, 255));
		lbl_Back.setText("");
		lbl_Back.setBounds(10, 10, 50, 50);
		container.add(lbl_Back);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 244, 255));
		panel.setBounds(10, 126, 430, 50);
		container.add(panel);
		panel.setLayout(null);

		table = new CustomTable(1, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if(dataModel.getColumnCount() > 0)
					TableFormats.toUserTypeTable(this);
			}
		};
		
		RoundedBorderPanel btnCreate = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateUsertype_Frame cut = new CreateUsertype_Frame(manager, EditUsertype_Frame.this);
				cut.setVisible(true);
			}
		});
		btnCreate.setBounds(0, 5, 120, 40);		
		btnCreate.setLayout(null);
		btnCreate.setBackground(Color.WHITE);
		panel.add(btnCreate);
		
		JLabel lblCreate = new JLabel("Create");
		lblCreate.setForeground(Color.WHITE);
		lblCreate.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblCreate.setBounds(55, 5, 55, 30);
		btnCreate.add(lblCreate);
		
		JLabel iconCreate = new JLabel("");
		iconCreate.setHorizontalAlignment(SwingConstants.CENTER);
		iconCreate.setBounds(15, 5, 40, 30);
		iconCreate.setIcon(new ImageIcon(img_Create));
		btnCreate.add(iconCreate);
		
		RoundedBorderPanel btnUpdate = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(ut == null)
					JOptionPane.showMessageDialog(null, "No row selected", "Update User", 2);
				else {					
					UpdateUsertype ust = new UpdateUsertype(manager, EditUsertype_Frame. this, ut);
					ust.setVisible(true);		
					ut = null;
				}
				
			}
		});
		btnUpdate.setLayout(null);
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(130, 5, 120, 40);
		panel.add(btnUpdate);
		
		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setForeground(Color.WHITE);
		lblUpdate.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUpdate.setBounds(55, 5, 55, 30);
		btnUpdate.add(lblUpdate);
		
		JLabel iconUpdate = new JLabel("");
		iconUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		iconUpdate.setBounds(15, 5, 40, 30);
		iconUpdate.setIcon(new ImageIcon(img_Update));
		btnUpdate.add(iconUpdate);
		
		RoundedClearpnl btnDelete = new RoundedClearpnl(40, 0, new Color(225,127,107), new Color(220, 144, 127));
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(ut == null) {
						JOptionPane.showMessageDialog(null, "No row selected", "Delete User Type", 2);
					} else {
						manager.deleteUserType(ut);
						table.setModel(manager.viewUserTypes());
						ut = null;
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n"+e1.getMessage(), "Remove User Type", 0);
				}
			}
		});
		btnDelete.setLayout(null);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(260, 5, 120, 40);
		panel.add(btnDelete);
		
		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setForeground(Color.WHITE);
		lblDelete.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblDelete.setBounds(55, 5, 55, 30);
		btnDelete.add(lblDelete);
		
		JLabel iconDelete = new JLabel("");
		iconDelete.setHorizontalAlignment(SwingConstants.CENTER);
		iconDelete.setBounds(15, 5, 40, 30);
		iconDelete.setIcon(new ImageIcon(img_Delete));
		btnDelete.add(iconDelete);
		
		
		table.setModel(manager.viewUserTypes());
		scrollpane = new JScrollPane(table, 20, 31);		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel table_model = table.getModel();
				int id = Integer.parseInt(table_model.getValueAt(i, 0).toString());
				String desc = table_model.getValueAt(i, 1).toString();

				ut = new UserType(desc, id);
			}
		});
			
		
		scrollpane.setBounds(10, 190, 430, 400);
		container.add(scrollpane);
		
		setUndecorated(true);
		setLocationRelativeTo(null);

	}
	private UserType ut;
}
