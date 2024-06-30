package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import backend.Item.StockIn;
import backend.Item.Supplier;
import backend.User.Manager;

import java.util.Date;

import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;

import frontend.UIcomponents.RoundedBorderPanel;

public class ConfirmSupplier_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmSupplier_Frame frame = new ConfirmSupplier_Frame(null, null, null);
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
	public ConfirmSupplier_Frame(Manager manager, StockIn[] stockin_items, ManageStock_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480, 320);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 2));
		pnlContent.setBackground(new Color(224, 224, 224));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		JPanel containerConfirmSupplier = new RoundedClearpnl(15, 3, new Color(240, 240, 240),
				new Color(240, 240, 240));
		containerConfirmSupplier.setBounds(10, 10, 460, 300);
		pnlContent.add(containerConfirmSupplier);
		containerConfirmSupplier.setLayout(null);

		RoundedClearpnl pnlConfirmSupplierContainer = new RoundedClearpnl(15, 3, new Color(255, 255, 255),
				new Color(255, 255, 255));
		pnlConfirmSupplierContainer.setBounds(5, 5, 450, 290);
		containerConfirmSupplier.add(pnlConfirmSupplierContainer);
		pnlConfirmSupplierContainer.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlHeader.setBackground(new Color(255, 255, 255));
		pnlHeader.setBounds(0, 5, 450, 50);
		pnlConfirmSupplierContainer.add(pnlHeader);
		pnlHeader.setLayout(null);

		Design_Back lbl_Back = new Design_Back("Back", 50, this, new Color(255, 255, 255));
		lbl_Back.setBackgroundColor(new Color(255, 255, 255));
		lbl_Back.setText("");
		lbl_Back.setBounds(5, 0, 40, 40);
		pnlHeader.add(lbl_Back);

		JLabel lblHeader = new JLabel("Stock In Supplier");
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
		pnlConfirmSupplierContainer.add(pnlSupplierforItem);

		RoundedSearchPnl pnlSupplier = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlSupplier.setLayout(null);
		pnlSupplier.setBounds(20, 20, 220, 40);
		pnlSupplierforItem.add(pnlSupplier);

		suppliers = manager.chooseSuppliers();
		cbSupplier = new JComboBox<String>();
		for (Supplier supplier : suppliers) {
			cbSupplier.addItem(supplier.getName());
		}
		cbSupplier.insertItemAt("---Select---", 0);
		cbSupplier.setSelectedIndex(0);

		cbSupplier.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbSupplier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbSupplier.setBackground(Color.WHITE);
		cbSupplier.setBounds(2, 2, 216, 36);
		pnlSupplier.add(cbSupplier);

		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setHorizontalAlignment(SwingConstants.LEFT);
		lblSupplier.setForeground(new Color(102, 102, 102));
		lblSupplier.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSupplier.setBackground(Color.WHITE);
		lblSupplier.setBounds(20, 0, 200, 20);
		pnlSupplierforItem.add(lblSupplier);

		RoundedBorderPanel btnCreateSupplier = new RoundedBorderPanel(50, 0, new Color(71, 180, 206));
		btnCreateSupplier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateSupplier CS = new CreateSupplier(manager, ConfirmSupplier_Frame.this);
				CS.setVisible(true);
			}
		});
		btnCreateSupplier.setLayout(null);
		btnCreateSupplier.setBackground(Color.WHITE);
		btnCreateSupplier.setBounds(260, 14, 150, 50);
		pnlSupplierforItem.add(btnCreateSupplier);

		JLabel lblCreateNewSupplier = new JLabel("Create");
		lblCreateNewSupplier.setVerticalAlignment(SwingConstants.TOP);
		lblCreateNewSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateNewSupplier.setForeground(Color.WHITE);
		lblCreateNewSupplier.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblCreateNewSupplier.setBounds(10, 5, 130, 20);
		btnCreateSupplier.add(lblCreateNewSupplier);

		JLabel lblNewSupplier = new JLabel("New Supplier");
		lblNewSupplier.setVerticalAlignment(SwingConstants.TOP);
		lblNewSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewSupplier.setForeground(Color.WHITE);
		lblNewSupplier.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewSupplier.setBounds(10, 23, 130, 20);
		btnCreateSupplier.add(lblNewSupplier);

		RoundedClearpnl btnEditSupplier = new RoundedClearpnl(50, 3, new Color(35, 204, 113), new Color(123, 224, 169));
		btnEditSupplier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EditSupplier ES = new EditSupplier(manager, ConfirmSupplier_Frame.this);
				ES.setVisible(true);
			}
		});
		btnEditSupplier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditSupplier.setBounds(260, 80, 150, 50);
		pnlSupplierforItem.add(btnEditSupplier);
		btnEditSupplier.setLayout(null);
		btnEditSupplier.setBackground(Color.WHITE);

		JLabel lblEditSupplier = new JLabel("Edit");
		lblEditSupplier.setVerticalAlignment(SwingConstants.TOP);
		lblEditSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditSupplier.setForeground(Color.WHITE);
		lblEditSupplier.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblEditSupplier.setBounds(10, 5, 130, 20);
		btnEditSupplier.add(lblEditSupplier);

		JLabel lblSupplierDetails = new JLabel("Supplier Details");
		lblSupplierDetails.setVerticalAlignment(SwingConstants.TOP);
		lblSupplierDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupplierDetails.setForeground(Color.WHITE);
		lblSupplierDetails.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblSupplierDetails.setBounds(10, 23, 130, 20);
		btnEditSupplier.add(lblSupplierDetails);

		JPanel pnlFooter = new JPanel();
		pnlFooter.setLayout(null);
		pnlFooter.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(192, 192, 192)));
		pnlFooter.setBackground(Color.WHITE);
		pnlFooter.setBounds(0, 219, 450, 60);
		pnlConfirmSupplierContainer.add(pnlFooter);

		RoundedBorderPanel btnSubmit = new RoundedBorderPanel(50, 0, new Color(71, 180, 206));
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if(cbSupplier.getSelectedIndex() <= 0)
						JOptionPane.showMessageDialog(null, "Please select option");
					else {
						Supplier supplier = suppliers[cbSupplier.getSelectedIndex() - 1];
						Date stockInDate = new Date();

						manager.addStocks(stockInDate, stockin_items, supplier);
						frame.stockin_model.setRowCount(0);
						frame.tableItems_SI.setModel(manager.viewStockInventory());
						frame.itemList_SI.clear();
						frame.item_SI = null;
						frame.tfQuantity_SI.setText("");
						frame.tfItemname_SI.setText("");
						ConfirmSupplier_Frame.this.dispose();
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

	protected void createSupplierDropdown(Manager manager) {
		suppliers = manager.chooseSuppliers();
		cbSupplier.removeAllItems();
		for (Supplier supplier : suppliers) {
			cbSupplier.addItem(supplier.getName());
		}
		cbSupplier.insertItemAt("---Select---", 0);
		cbSupplier.setSelectedIndex(0);
	}

	protected Supplier[] suppliers = null;
	protected JComboBox<String> cbSupplier = null;
}
