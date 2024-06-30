package frontend;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

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

public class UpdateItem_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfPname;
	private JTextField tfWholesale;
	private JTextField tfRetail, tfProductid;
	private JLabel lblProductid, lblPname;
	private JLabel lblWholesale;
	private JLabel lblRetail;
	public JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public UpdateItem_Frame(Manager manager, Inventory_Frame frame, Item item) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(410, 550);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		JPanel containerRegister = new JPanel();
		containerRegister.setBackground(new Color(244, 244, 255));
		containerRegister.setBounds(5, 5, 400, 540);
		pnlContent.add(containerRegister);
		containerRegister.setLayout(null);

		lblNewLabel = new JLabel("Update Item");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel.setBounds(75, 41, 250, 50);
		containerRegister.add(lblNewLabel);

		RoundedSearchPnl pnlProductid = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlProductid.setBorder(null);
		pnlProductid.setBounds(75, 120, 250, 40);
		containerRegister.add(pnlProductid);
		pnlProductid.setLayout(null);

		tfProductid = new JTextField();
		tfProductid.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfProductid.setText(String.valueOf(item.getId()));
		tfProductid.setBorder(null);
		tfProductid.setEditable(false);
		tfProductid.setBackground(Color.WHITE);
		tfProductid.setBounds(10, 5, 230, 30);
		pnlProductid.add(tfProductid);
		tfProductid.setColumns(10);

		lblProductid = new JLabel();
		lblProductid.setText("Product ID");
		lblProductid.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblProductid.setBounds(75, 101, 83, 20);
		containerRegister.add(lblProductid);

		RoundedSearchPnl pnlPname = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlPname.setBorder(null);
		pnlPname.setBounds(75, 191, 250, 40);
		containerRegister.add(pnlPname);
		pnlPname.setLayout(null);

		tfPname = new JTextField();
		tfPname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfPname.getText().equals(item.getDesc())) {
					tfPname.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfPname.getText().equals("")) {
					tfPname.setText(item.getDesc());
				}
			}
		});
		tfPname.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfPname.setBorder(null);
		tfPname.setText(item.getDesc());
		tfPname.setBackground(Color.WHITE);
		tfPname.setBounds(10, 5, 230, 30);
		pnlPname.add(tfPname);
		tfPname.setColumns(10);

		lblPname = new JLabel();
		lblPname.setText("Product Name");
		lblPname.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblPname.setBounds(75, 171, 113, 20);
		containerRegister.add(lblPname);

		RoundedSearchPnl pnlWholesale = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlWholesale.setLayout(null);
		pnlWholesale.setBounds(75, 262, 250, 40);
		containerRegister.add(pnlWholesale);

		tfWholesale = new JTextField();
		tfWholesale.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfWholesale.getText().equals(String.valueOf(item.getWholesale_price()))) {
					tfWholesale.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfWholesale.getText().equals("")) {
					tfWholesale.setText(String.valueOf(item.getWholesale_price()));
				}
			}
		});
		tfWholesale.setBorder(null);
		tfWholesale.setText(String.valueOf(item.getWholesale_price()));
		tfWholesale.setBackground(Color.WHITE);
		tfWholesale.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfWholesale.setColumns(10);
		tfWholesale.setBounds(10, 5, 230, 30);
		tfWholesale.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlWholesale.add(tfWholesale);

		lblWholesale = new JLabel();
		lblWholesale.setText("Wholesale Price");
		lblWholesale.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblWholesale.setBounds(75, 242, 113, 20);
		containerRegister.add(lblWholesale);

		lblRetail = new JLabel();
		lblRetail.setText("Retail Price");
		lblRetail.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblRetail.setBounds(75, 313, 113, 20);
		containerRegister.add(lblRetail);

		RoundedSearchPnl pnlRetail = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlRetail.setLayout(null);
		pnlRetail.setBounds(75, 333, 250, 40);
		containerRegister.add(pnlRetail);

		tfRetail = new JTextField();
		tfRetail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfRetail.getText().equals(String.valueOf(item.getRetail_price()))) {
					tfRetail.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfRetail.getText().equals("")) {

					tfRetail.setText(String.valueOf(item.getRetail_price()));
				}
			}
		});
		tfRetail.setBorder(null);
		tfRetail.setText(String.valueOf(item.getRetail_price()));
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

					if (desc.equals("") || tfWholesale.getText().equals("") || tfRetail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "Update Item",
								JOptionPane.INFORMATION_MESSAGE);
					}

					else {
						int item_id = Integer.parseInt(tfProductid.getText());
						double w_price = Double.parseDouble(
								new DecimalFormat("0.00").format(Double.parseDouble(tfWholesale.getText())));
						double r_price = Double
								.parseDouble(new DecimalFormat("0.00").format(Double.parseDouble(tfRetail.getText())));

						manager.updateItem(new Item(item_id, desc, w_price, r_price));
						frame.tableItems.setModel(manager.viewFullInventory());
						UpdateItem_Frame.this.dispose();
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Kindly enter fields correctly");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage(), "Update Item", 0);
				}

			}
		});

		btnSubmit.setBounds(95, 430, 210, 40);
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
