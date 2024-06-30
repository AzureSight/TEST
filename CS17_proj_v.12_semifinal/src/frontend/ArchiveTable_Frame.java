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

import backend.User;
import backend.User.Manager;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedBorderPanel;
import frontend.UIcomponents.TableFormats;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArchiveTable_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private Image img_Restore = new ImageIcon(Login.class.getResource("resources/Restore.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	protected CustomTable table;
	private JScrollPane scrollpane;

	/**
	 * Create the frame.
	 */
	public ArchiveTable_Frame(Manager manager, ManageUser_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 575);
	
		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);
				
		JPanel container = new JPanel();
		container.setBackground(new Color(244, 244, 255));
		container.setBounds(5, 5, 890, 565);
		pnlContent.add(container);
		container.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Archive Log");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setBorder(null);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setBounds(15, 45, 865, 50);
		container.add(lblNewLabel);
		
		Design_Back lbl_Back = new Design_Back ("Back" , 50, this, new Color(244, 244, 255));
		lbl_Back.setText("");
		lbl_Back.setBounds(5, 5, 40, 40);
		container.add(lbl_Back);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 244, 255));
		panel.setBounds(10, 90, 430, 50);
		container.add(panel);
		panel.setLayout(null);
		
		RoundedBorderPanel btnUnarchive = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnUnarchive.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUnarchive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(u == null)
					JOptionPane.showMessageDialog(null, "No row selected", "Unarchive User", 2);
				else {
					manager.unarchiveUser(u);
					frame.table.setModel(manager.viewAllUsers());
					ArchiveTable_Frame.this.dispose();
				}
			}
		});
		btnUnarchive.setBounds(5, 5, 163, 40);		
		btnUnarchive.setLayout(null);
		btnUnarchive.setBackground(Color.WHITE);
		panel.add(btnUnarchive);
		
		JLabel lblUnarchive = new JLabel("Unarchive User");
		lblUnarchive.setForeground(new Color(255, 255, 255));
		lblUnarchive.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUnarchive.setBounds(47, 5, 109, 30);
		btnUnarchive.add(lblUnarchive);
		
		JLabel iconUnarchive = new JLabel("");
		iconUnarchive.setHorizontalAlignment(SwingConstants.CENTER);
		iconUnarchive.setBounds(7, 5, 40, 30);
		iconUnarchive.setIcon(new ImageIcon(img_Restore));
		btnUnarchive.add(iconUnarchive);

		table = new CustomTable(5, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if(dataModel.getColumnCount() > 0)
					TableFormats.toArchiveUserTable(this);
			}
		};
		
		table.setModel(manager.viewArchivedUsers());
		scrollpane = new JScrollPane(table, 20, 31);	
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel table_model = table.getModel();
				int archiveID = Integer.parseInt(table_model.getValueAt(i, 0).toString());
				String username = table_model.getValueAt(i, 3).toString();

				u = new User(archiveID, username);
			}
		});		
		scrollpane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		scrollpane.setBounds(10, 154, 870, 400);
		container.add(scrollpane);
		
		setUndecorated(true);
		setLocationRelativeTo(null);

	}
	private User u;
}
