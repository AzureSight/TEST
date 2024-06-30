package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import backend.Item.StockOut;
import backend.Item.Cause;
import backend.User.Manager;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedBorderPanel;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;

public class ConfirmCause_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmCause_Frame frame = new ConfirmCause_Frame(null, null, null);
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
	public ConfirmCause_Frame(Manager manager, StockOut[] stockout_items, ManageStock_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480, 320);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 2));
		pnlContent.setBackground(new Color(224, 224, 224));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		
		JPanel containerCause = new RoundedClearpnl(15, 3, new Color(240, 240, 240), new Color(240, 240, 240));
		containerCause.setBounds(10, 10, 460, 300);
		pnlContent.add(containerCause);
		containerCause.setLayout(null);
		
		RoundedClearpnl pnlCauseContainer = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlCauseContainer.setBounds(5, 5, 450, 290);
		containerCause.add(pnlCauseContainer);
		pnlCauseContainer.setLayout(null);
		

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlHeader.setBackground(new Color(255, 255, 255));
		pnlHeader.setBounds(0, 5, 450, 50);
		pnlCauseContainer.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		Design_Back lbl_Back = new Design_Back("Back", 50, this, new Color(255, 255, 255));
		lbl_Back.setBackgroundColor(new Color(255, 255, 255));
		lbl_Back.setText("");
		lbl_Back.setBounds(5, 0, 40, 40);
		pnlHeader.add(lbl_Back);
		
		JLabel lblHeader = new JLabel("Stock Out Cause");
		lblHeader.setForeground(new Color(102, 102, 102));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblHeader.setBounds(0, 0, 450, 40);
		pnlHeader.add(lblHeader);
		
		JPanel pnlSupplierforItem = new JPanel();
		pnlSupplierforItem.setLayout(null);
		pnlSupplierforItem.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(192, 192, 192)));
		pnlSupplierforItem.setBackground(Color.WHITE);
		pnlSupplierforItem.setBounds(0, 65, 450, 143);
		pnlCauseContainer.add(pnlSupplierforItem);
		
		RoundedSearchPnl pnlCause = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206), new Color(71, 180, 206));
		pnlCause.setLayout(null);
		pnlCause.setBounds(20, 20, 220, 40);
		pnlSupplierforItem.add(pnlCause);
		
		causes = manager.chooseCauses();
		cbCause = new JComboBox<String>();
		for (Cause cause : causes) {
			cbCause.addItem(cause.getCausedesc());
		}
		cbCause.insertItemAt("---Select---", 0);
		cbCause.setSelectedIndex(0);

		cbCause.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbCause.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbCause.setBackground(Color.WHITE);
		cbCause.setBounds(2, 2, 216, 36);
		pnlCause.add(cbCause);
		
		JLabel lblCause = new JLabel("Cause for Stocking out");
		lblCause.setHorizontalAlignment(SwingConstants.LEFT);
		lblCause.setForeground(new Color(102, 102, 102));
		lblCause.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblCause.setBackground(Color.WHITE);
		lblCause.setBounds(20, 0, 220, 20);
		pnlSupplierforItem.add(lblCause);
		
		RoundedBorderPanel btnCreateCause = new RoundedBorderPanel(50, 0, new Color(71, 180, 206));
		btnCreateCause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateCause CC = new CreateCause(manager, ConfirmCause_Frame.this);
				CC.setVisible(true);
			}
		});
		btnCreateCause.setLayout(null);
		btnCreateCause.setBackground(Color.WHITE);
		btnCreateCause.setBounds(260, 14, 150, 50);
		pnlSupplierforItem.add(btnCreateCause);
		
		JLabel lblCreateCause = new JLabel("Create");
		lblCreateCause.setVerticalAlignment(SwingConstants.TOP);
		lblCreateCause.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateCause.setForeground(Color.WHITE);
		lblCreateCause.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblCreateCause.setBounds(10, 5, 130, 20);
		btnCreateCause.add(lblCreateCause);
		
		JLabel lblNewCause = new JLabel("New Cause");
		lblNewCause.setVerticalAlignment(SwingConstants.TOP);
		lblNewCause.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewCause.setForeground(Color.WHITE);
		lblNewCause.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewCause.setBounds(10, 23, 130, 20);
		btnCreateCause.add(lblNewCause);
		
		RoundedClearpnl btnEditCause = new RoundedClearpnl(50, 3, new Color(35, 204, 113), new Color(123, 224, 169));
		btnEditCause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EditCause EC = new EditCause(manager, ConfirmCause_Frame.this);
				EC.setVisible(true);
			}
		});
		btnEditCause.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditCause.setBounds(260, 80, 150, 50);
		pnlSupplierforItem.add(btnEditCause);
		btnEditCause.setLayout(null);
		btnEditCause.setBackground(Color.WHITE);
		
		JLabel lblEditCause = new JLabel("Edit");
		lblEditCause.setVerticalAlignment(SwingConstants.TOP);
		lblEditCause.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditCause.setForeground(Color.WHITE);
		lblEditCause.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblEditCause.setBounds(10, 5, 130, 20);
		btnEditCause.add(lblEditCause);
		
		JLabel lblCauseDetails = new JLabel("Cause Details");
		lblCauseDetails.setVerticalAlignment(SwingConstants.TOP);
		lblCauseDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblCauseDetails.setForeground(Color.WHITE);
		lblCauseDetails.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblCauseDetails.setBounds(10, 23, 130, 20);
		btnEditCause.add(lblCauseDetails);
			
		JPanel pnlFooter = new JPanel();
		pnlFooter.setLayout(null);
		pnlFooter.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(192, 192, 192)));
		pnlFooter.setBackground(Color.WHITE);
		pnlFooter.setBounds(0, 219, 450, 60);
		pnlCauseContainer.add(pnlFooter);
		
		RoundedBorderPanel btnSubmit = new RoundedBorderPanel(50, 0, new Color(71, 180, 206));
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if(cbCause.getSelectedIndex() <= 0)
						JOptionPane.showMessageDialog(null, "Pleasee select option");
					else {
						Cause cause = causes[cbCause.getSelectedIndex() - 1];
						Date stockOutDate = new Date();

						manager.removeStocks(stockOutDate, stockout_items, cause);
						frame.stockout_model.setRowCount(0);
						frame.tableItems_SO.setModel(manager.viewStockInventory());
						frame.itemList_SO.clear();
						frame.item_SO = null;
						frame.tfQuantity_SO.setText("");
						frame.tfItemname_SO.setText("");
						ConfirmCause_Frame.this.dispose();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnSubmit.setLayout(null);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setBounds(162, 20, 140, 40);
		pnlFooter.add(btnSubmit);
		
		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSubmit.setBounds(10, 0, 120, 40);
		btnSubmit.add(lblSubmit);
		setUndecorated(true);
		setLocationRelativeTo(null);
		
	}

	protected void createCauseDropdown(Manager manager) {
		causes = manager.chooseCauses();
		cbCause.removeAllItems();
		for (Cause cause : causes) {
			cbCause.addItem(cause.getCausedesc());
		}
		cbCause.insertItemAt("---Select---", 0);
		cbCause.setSelectedIndex(0);
	}

	private Cause[] causes = null;
	private JComboBox<String> cbCause = null;
}
