package frontend;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.Cursor;

import backend.User;
import backend.User.Manager;
import backend.User.UserType;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Close;
import frontend.UIcomponents.Design_PanelBorder;
import frontend.UIcomponents.Design_SearchText;
import frontend.UIcomponents.Design_btnSidebar;
import frontend.UIcomponents.Minimize;
import frontend.UIcomponents.RoundedBorderPanel;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.Sidebar;
import frontend.UIcomponents.TableFormats;

public class ManageUser_Frame extends JFrame {
	
	private Image img_Search = new ImageIcon(Login.class.getResource("resources/SearchUser.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_Register = new ImageIcon(Login.class.getResource("resources/Add.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_Update = new ImageIcon(Login.class.getResource("resources/Update.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_Archive = new ImageIcon(Login.class.getResource("resources/Archive.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_Usertype = new ImageIcon(Login.class.getResource("resources/Tools.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_ArchiveTable = new ImageIcon(Login.class.getResource("resources/ViewAT.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

	private JPanel Content, pnlSidebar;
	Design_btnSidebar btnHome; Design_btnSidebar btnProfile; Design_btnSidebar btnSchedule; Design_btnSidebar btnManageUser; Design_btnSidebar btnInventory; Design_btnSidebar btnLogout;
	protected JTable table;
	private Design_SearchText tfSearch;
	private JScrollPane scrollpane;

	/**
	 * Create the frame.
	 */
	public ManageUser_Frame(Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 700);
		
		Content= new JPanel();
		Content.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Content);
		Content.setLayout(null);
		
		pnlSidebar = new Sidebar(manager, this);
		pnlSidebar.setBounds(0, 0, 300, 700);
		pnlSidebar.setBackground(new Color(71, 180, 206));
		Content.add(pnlSidebar);		
		
		JPanel pnlContent = new JPanel();
		pnlContent.setBackground(new Color(224, 224, 224));
		pnlContent.setBounds(300, 0, 980, 700);
		Content.add(pnlContent);	
		
		Design_Close lbl_Close = new Design_Close ("Close", 0, this, new Color(224, 224, 224)) ;
		lbl_Close.setText("");
		lbl_Close.setBounds(950, 0, 30, 30);
		pnlContent.setLayout(null);
		pnlContent.add(lbl_Close);
		
		Minimize lbl_Minimize = new Minimize ("Minimize", 0, this,  new Color(224, 224, 224), new Color(227, 244, 244), Color.white);
		lbl_Minimize.setText("");
		lbl_Minimize.setBounds(920, 0, 30, 30);
		pnlContent.setLayout(null);
		pnlContent.add(lbl_Minimize);
		
		Design_PanelBorder pnlTable = new Design_PanelBorder();
		pnlTable.setBackground(Color.WHITE);
		pnlTable.setBounds(30, 30, 920, 640);
		pnlContent.add(pnlTable);
		
		table = new CustomTable(6, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if (dataModel.getColumnCount() > 0)
					TableFormats.toManageUser(this);
			};
		};
		
		table.setModel(manager.viewAllUsers());
		scrollpane = new JScrollPane(table, 20, 31);		
		scrollpane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel table_model = table.getModel();
				int id = Integer.parseInt(table_model.getValueAt(i, 0).toString());
				String fname = table_model.getValueAt(i, 1).toString();
				String lname = table_model.getValueAt(i, 2).toString();
				String username = table_model.getValueAt(i, 3).toString();
				String password = table_model.getValueAt(i, 4).toString();
				String type = table_model.getValueAt(i, 5).toString();
				
				u = new User(id, fname, lname, username, password, new UserType(type));
			}
		});
		scrollpane.setBounds(20, 130, 890, 440);
		pnlTable.add(scrollpane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 15, 930, 100);
		pnlTable.add(panel);
		panel.setLayout(null);
		
		RoundedBorderPanel btnAdd = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register_Frame add = new Register_Frame (manager, ManageUser_Frame.this);
				add.setVisible(true);				
			}
		});
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(10, 49, 120, 40);
		panel.add(btnAdd);
		btnAdd.setLayout(null);
		
		JLabel lblAdd = new JLabel("Add User");
		lblAdd.setForeground(Color.WHITE);
		lblAdd.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblAdd.setBounds(42, 5, 70, 30);
		btnAdd.add(lblAdd);
		
		JLabel iconAdd = new JLabel("");
		iconAdd.setHorizontalAlignment(SwingConstants.CENTER);
		iconAdd.setBounds(7, 5, 40, 30);
		iconAdd.setIcon(new ImageIcon(img_Register));
		btnAdd.add(iconAdd);
		
		RoundedSearchPnl  pnlSearch = new RoundedSearchPnl (25, 2, Color.WHITE, new Color(71, 180, 206), new Color(71, 180, 206));
		pnlSearch.setBounds(660, 49, 250, 40);
		panel.add(pnlSearch);
		pnlSearch.setLayout(null);
		
		tfSearch = new Design_SearchText();
		tfSearch.addCaretListener(new CaretListener() {
			
			public void caretUpdate(CaretEvent e) {
				String search = tfSearch.getText();
				if (search.equals(""))
					table.setModel(manager.viewAllUsers());
				else {
					try {
						table.setModel(manager.searchUsers(search));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "A search problem has occured!\n"+e1.getMessage(), "Search User", 1);
					}
				}
			}
		});
		tfSearch.setForeground(new Color (106, 106, 106));
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
		
		RoundedBorderPanel btnUpdate = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(u == null)
					JOptionPane.showMessageDialog(null, "No row selected", "Update User", 2);
				else {
					UpdateUser_Frame add = new UpdateUser_Frame (manager, u, ManageUser_Frame.this);
					add.setVisible(true);	
					u = null;	
				}
			}
		});
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setLayout(null);
		btnUpdate.setBounds(140, 49, 120, 40);
		panel.add(btnUpdate);
		
		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setForeground(Color.WHITE);
		lblUpdate.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUpdate.setBounds(47, 5, 70, 30);
		btnUpdate.add(lblUpdate);
		
		JLabel iconUpdate = new JLabel("");
		iconUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		iconUpdate.setBounds(13, 5, 40, 30);
		iconUpdate.setIcon(new ImageIcon(img_Update));
		btnUpdate.add(iconUpdate);
		
		RoundedClearpnl btnArchive = new RoundedClearpnl(40, 0, new Color(225,127,107), new Color(220, 144, 127));
		btnArchive.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if(u == null)
						JOptionPane.showMessageDialog(null, "No row selected", "Archive User", 2);
					else if(u.getId() == manager.getId())
						JOptionPane.showMessageDialog(null, "Warrning: You're currently using this account!", "Archive User", 2);
					else {
						int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to archive this user?\nWarning: This user will be deactivated and cannot be logged in", "Archive User", 2);
						if (confirm == 0) {							
							manager.archiveUser(u);
							table.setModel(manager.viewAllUsers());
							u = null;
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n"+e1.getMessage(), "Archive User", 0);
				}
			}
		});
		btnArchive.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnArchive.setLayout(null);
		btnArchive.setBackground(Color.WHITE);
		btnArchive.setBounds(270, 49, 120, 40);
		panel.add(btnArchive);
		
		JLabel lblArchive = new JLabel("Archive");
		lblArchive.setForeground(Color.WHITE);
		lblArchive.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblArchive.setBounds(50, 5, 70, 30);
		btnArchive.add(lblArchive);
		
		JLabel iconArchive = new JLabel("");
		iconArchive.setHorizontalAlignment(SwingConstants.CENTER);
		iconArchive.setBounds(15, 5, 40, 30);
		iconArchive.setIcon(new ImageIcon(img_Archive));
		btnArchive.add(iconArchive);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel_1.setBounds(0, 580, 930, 50);
		pnlTable.add(panel_1);
		panel_1.setLayout(null);
		
		RoundedBorderPanel btnArchiveTable = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnArchiveTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnArchiveTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArchiveTable_Frame add = new ArchiveTable_Frame (manager, ManageUser_Frame.this);
				add.setVisible(true);	
			}
		});
		btnArchiveTable.setBounds(10, 9, 138, 40);
		panel_1.add(btnArchiveTable);
		btnArchiveTable.setLayout(null);
		btnArchiveTable.setBackground(Color.WHITE);
		
		JLabel lblArchiveTable = new JLabel("Archive Table");
		lblArchiveTable.setForeground(Color.WHITE);
		lblArchiveTable.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblArchiveTable.setBounds(50, 5, 84, 30);
		btnArchiveTable.add(lblArchiveTable);
		
		JLabel iconArchiveTable = new JLabel("");
		iconArchiveTable.setHorizontalAlignment(SwingConstants.CENTER);
		iconArchiveTable.setBounds(15, 5, 40, 30);
		iconArchiveTable.setIcon(new ImageIcon(img_ArchiveTable));
		btnArchiveTable.add(iconArchiveTable);
		
		RoundedBorderPanel btnUsertype = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnUsertype.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsertype.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EditUsertype_Frame add = new EditUsertype_Frame (manager);
				add.setVisible(true);		
			}
		});
		btnUsertype.setBounds(158, 9, 151, 40);
		panel_1.add(btnUsertype);
		btnUsertype.setLayout(null);
		btnUsertype.setBackground(Color.WHITE);
		
		JLabel lblUsertype = new JLabel("Edit User Type");
		lblUsertype.setForeground(Color.WHITE);
		lblUsertype.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblUsertype.setBounds(55, 5, 86, 30);
		btnUsertype.add(lblUsertype);
		
		JLabel iconUsertype = new JLabel("");
		iconUsertype.setHorizontalAlignment(SwingConstants.CENTER);
		iconUsertype.setBounds(15, 5, 40, 30);
		iconUsertype.setIcon(new ImageIcon(img_Usertype));
		btnUsertype.add(iconUsertype);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
	private User u;
}
