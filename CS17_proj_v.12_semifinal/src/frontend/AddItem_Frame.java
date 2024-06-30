package frontend;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Cursor;

import backend.Item;
import backend.User.Manager;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;

public class AddItem_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfPname;
	private JTextField tfWholesale;
	private JTextField tfRetail;

	private JLabel lblPname;
	private JLabel lblWholesale;
	private JLabel lblRetail;
	public JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public AddItem_Frame(Manager manager, Inventory_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(410, 475);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		JPanel containerRegister = new JPanel();
		containerRegister.setBackground(new Color(244, 244, 255));
		containerRegister.setBounds(5, 5, 400, 465);
		pnlContent.add(containerRegister);
		containerRegister.setLayout(null);

		lblNewLabel = new JLabel("Add Item");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel.setBounds(75, 43, 250, 50);
		containerRegister.add(lblNewLabel);

		RoundedSearchPnl pnlPname = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlPname.setBorder(null);
		pnlPname.setBounds(75, 123, 250, 40);
		containerRegister.add(pnlPname);
		pnlPname.setLayout(null);

		tfPname = new JTextField();
		tfPname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfPname.selectAll();

			}
		});
		tfPname.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfPname.setBorder(null);
		tfPname.setBackground(Color.WHITE);
		tfPname.setBounds(10, 5, 230, 30);
		pnlPname.add(tfPname);
		tfPname.setColumns(10);

		lblPname = new JLabel();
		lblPname.setText("Product Name");
		lblPname.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblPname.setBounds(75, 103, 113, 20);
		containerRegister.add(lblPname);

		RoundedSearchPnl pnlWholesale = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlWholesale.setLayout(null);
		pnlWholesale.setBounds(75, 194, 250, 40);
		containerRegister.add(pnlWholesale);

		tfWholesale = new JTextField();
		tfWholesale.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfWholesale.selectAll();

			}
		});
		tfWholesale.setBorder(null);
		tfWholesale.setBackground(Color.WHITE);
		tfWholesale.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfWholesale.setColumns(10);
		tfWholesale.setBounds(10, 5, 230, 30);
		tfWholesale.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlWholesale.add(tfWholesale);

		lblWholesale = new JLabel();
		lblWholesale.setText("Wholesale Price");
		lblWholesale.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblWholesale.setBounds(75, 174, 113, 20);
		containerRegister.add(lblWholesale);

		lblRetail = new JLabel();
		lblRetail.setText("Retail Price");
		lblRetail.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblRetail.setBounds(75, 245, 113, 20);
		containerRegister.add(lblRetail);

		RoundedSearchPnl pnlRetail = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlRetail.setLayout(null);
		pnlRetail.setBounds(75, 265, 250, 40);
		containerRegister.add(pnlRetail);

		tfRetail = new JTextField();
		tfRetail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfRetail.selectAll();

			}
		});
		tfRetail.setBorder(null);
		tfRetail.setBackground(Color.WHITE);
		tfRetail.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfRetail.setColumns(10);
		tfRetail.setBounds(10, 5, 230, 30);
		tfRetail.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlRetail.add(tfRetail);

		Design_Back lbl_Back = new Design_Back("Back", 50, this, new Color(244, 244, 255));
		lbl_Back.setText("");
		lbl_Back.setBounds(5, 5, 40, 40);
		containerRegister.add(lbl_Back);

		RoundedSubmit btnSubmit = new RoundedSubmit(50, 0, new Color(18, 72, 107), new Color(18, 72, 127));
		btnSubmit.setLayout(null);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					String desc = tfPname.getText();

					if (desc.equals("Product name") || desc.equals("") || tfWholesale.getText().equals("")
							|| tfWholesale.getText().equals("Wholesale price") || tfRetail.getText().equals("")
							|| tfRetail.getText().equals("Retail price")) {
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "Add Item",
								JOptionPane.INFORMATION_MESSAGE);
					}

					else {
						double w_price = Double.parseDouble(tfWholesale.getText());
						double r_price = Double.parseDouble(tfRetail.getText());
						manager.createItem(new Item(desc, w_price, r_price));
						frame.tableItems.setModel(manager.viewFullInventory());
						AddItem_Frame.this.dispose();
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Kindly enter fields correctly");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage(), "Add Item", 0);
				}
			}
		});
		btnSubmit.setBounds(95, 350, 210, 40);
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
