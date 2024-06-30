package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableModel;

import backend.Item;
import backend.Item.Cause;
import backend.User.Manager;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.Design_SearchText;
import frontend.UIcomponents.RoundedBorderPanel;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;

public class EditCause extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private Image img_Update = new ImageIcon(Login.class.getResource("resources/Update.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_Delete = new ImageIcon(Login.class.getResource("resources/Delete.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_Search = new ImageIcon(Login.class.getResource("resources/SearchUser.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	protected CustomTable tableCause;
	private JScrollPane spCause;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCause frame = new EditCause(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditCause(Manager manager, ConfirmCause_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480, 320);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 2));
		pnlContent.setBackground(new Color(224, 224, 224));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		
		RoundedClearpnl containerEditCause = new RoundedClearpnl(15, 3, new Color(240, 240, 240), new Color(240, 240, 240));
		containerEditCause.setBackground(new Color(240, 240, 240));
		containerEditCause.setBounds(10, 10, 460, 300);
		pnlContent.add(containerEditCause);
		containerEditCause.setLayout(null);
		
		RoundedClearpnl pnlEditCauseContainer = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlEditCauseContainer.setBounds(5, 5, 450, 290);
		containerEditCause.add(pnlEditCauseContainer);
		pnlEditCauseContainer.setLayout(null);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(new Color(255, 255, 255));
		pnlHeader.setBounds(0, 5, 450, 50);
		pnlEditCauseContainer.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		Design_Back lbl_Back = new Design_Back("Back", 50, this, new Color(255, 255, 255));
		lbl_Back.setBackgroundColor(new Color(255, 255, 255));
		lbl_Back.setText("");
		lbl_Back.setBounds(5, 0, 40, 40);
		pnlHeader.add(lbl_Back);
		
		JLabel lblHeader = new JLabel("Edit Cause Details");
		lblHeader.setForeground(new Color(102, 102, 102));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblHeader.setBounds(0, 0, 450, 40);
		pnlHeader.add(lblHeader);
		
		JPanel pnlOpertaions = new JPanel();
		pnlOpertaions.setLayout(null);
		pnlOpertaions.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlOpertaions.setBackground(Color.WHITE);
		pnlOpertaions.setBounds(0, 54, 450, 50);
		pnlEditCauseContainer.add(pnlOpertaions);
		
		RoundedBorderPanel btnUpdate = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (cause == null)
					JOptionPane.showMessageDialog(null, "No row selected");
				else {					
					UpdateCause UC = new UpdateCause(manager, EditCause.this, cause, frame);
					UC.setVisible(true);
					cause = null;
				}
			}
		});
		btnUpdate.setLayout(null);
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(5, 4, 120, 40);
		pnlOpertaions.add(btnUpdate);
		
		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setIcon(new ImageIcon(img_Update));
		lblUpdate.setForeground(Color.WHITE);
		lblUpdate.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblUpdate.setBounds(10, 0, 100, 40);
		btnUpdate.add(lblUpdate);
		
		JLabel iconUpdate = new JLabel("");
		iconUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		iconUpdate.setBounds(10, 0, 40, 40);
		btnUpdate.add(iconUpdate);
		
		RoundedClearpnl btnRemove = new RoundedClearpnl(40, 0, new Color(225, 127, 107), new Color(220, 144, 127));
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove.setLayout(null);
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setBounds(130, 4, 120, 40);
		btnRemove.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (cause == null)
					JOptionPane.showMessageDialog(null, "No row selected", "Remove Item", 2);
				else {
					int confirm = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete this?");
					if (confirm == 0) {
						manager.deleteCause(cause);
						tableCause.setModel(manager.viewAllCauses());
						frame.createCauseDropdown(manager);
						cause = null;
					}
				}
			}
		});
		pnlOpertaions.add(btnRemove);
		
		JLabel lblRemove = new JLabel("Remove");
		lblRemove.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemove.setIcon(new ImageIcon(img_Delete));
		lblRemove.setForeground(Color.WHITE);
		lblRemove.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblRemove.setBounds(10, 0, 100, 40);
		btnRemove.add(lblRemove);
		
		RoundedSearchPnl pnlSearch = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206), new Color(71, 180, 206));
		pnlSearch.setLayout(null);
		pnlSearch.setBackground(Color.WHITE);
		pnlSearch.setBounds(265, 4, 180, 40);
		pnlOpertaions.add(pnlSearch);
		
		Design_SearchText tfSearch = new Design_SearchText();
		tfSearch.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String search = tfSearch.getText();
				if(search.equals(""))
					tableCause.setModel(manager.viewAllCauses());
				else {
					try {
						tableCause.setModel(manager.searchCauses(search));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage(), "Search Cause", 0);
						e1.printStackTrace();
					}
				}
			}
		});
		tfSearch.setForeground(new Color(106, 106, 106));
		tfSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfSearch.setColumns(10);
		tfSearch.setBorder(null);
		tfSearch.setBounds(10, 5, 130, 30);
		pnlSearch.add(tfSearch);
		
		JLabel iconSearch = new JLabel("");
		iconSearch.setIcon(new ImageIcon(img_Search));
		iconSearch.setHorizontalAlignment(SwingConstants.CENTER);
		iconSearch.setBounds(140, 0, 40, 40);
		pnlSearch.add(iconSearch);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setBackground(new Color(255, 255, 255));
		pnlTable.setBounds(0, 102, 450, 184);
		pnlEditCauseContainer.add(pnlTable);
		pnlTable.setLayout(null);
		
		tableCause = new CustomTable(1, 1);
		tableCause.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int j = tableCause.getSelectedRow();
				TableModel table_model = tableCause.getModel();
				int id = Integer.parseInt(table_model.getValueAt(j, 0).toString());
				String desc = table_model.getValueAt(j, 1).toString();

				cause = new Item(-1).new Cause(id, desc);
			}
		});
		tableCause.setModel(manager.viewAllCauses());
		spCause = new JScrollPane(tableCause, 20, 31);
		spCause.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		spCause.setBounds(5, 5, 440, 179);
		pnlTable.add(spCause);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
	private Cause cause = null;
}
