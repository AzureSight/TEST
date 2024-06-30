package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import backend.User.Manager;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;

public class CreateSupplier extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfSupplier;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateSupplier frame = new CreateSupplier(null, null);
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
	public CreateSupplier(Manager manager, ConfirmSupplier_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(330, 250);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 2));
		pnlContent.setBackground(new Color(224, 224, 224));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		JPanel containerCreateSupplier = new RoundedClearpnl(15, 3, new Color(240, 240, 240), new Color(240, 240, 240));
		containerCreateSupplier.setBackground(new Color(240, 240, 240));
		containerCreateSupplier.setBounds(10, 10, 310, 230);
		pnlContent.add(containerCreateSupplier);
		containerCreateSupplier.setLayout(null);

		RoundedClearpnl pnlForm = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlForm.setBounds(5, 5, 300, 220);
		containerCreateSupplier.add(pnlForm);
		pnlForm.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlHeader.setBackground(Color.WHITE);
		pnlHeader.setBounds(0, 5, 300, 50);
		pnlForm.add(pnlHeader);
		pnlHeader.setLayout(null);

		Design_Back lbl_Back = new Design_Back("Back", 50, this, new Color(255, 255, 255));
		lbl_Back.setBounds(5, 0, 40, 40);
		pnlHeader.add(lbl_Back);
		lbl_Back.setText("");

		JLabel lblItemHeader = new JLabel("Create Supplier");
		lblItemHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemHeader.setForeground(new Color(102, 102, 102));
		lblItemHeader.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblItemHeader.setBounds(0, 0, 300, 40);
		pnlHeader.add(lblItemHeader);

		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(new Color(255, 255, 255));
		pnlBody.setBounds(0, 54, 300, 105);
		pnlForm.add(pnlBody);
		pnlBody.setLayout(null);

		JLabel lblSupplier = new JLabel("Supplier Detail");
		lblSupplier.setForeground(new Color(102, 102, 102));
		lblSupplier.setFont(new Font("SansSerif", Font.BOLD, 17));
		lblSupplier.setBounds(45, 15, 180, 20);
		pnlBody.add(lblSupplier);

		RoundedSearchPnl pnlSupplier = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlSupplier.setLayout(null);
		pnlSupplier.setBounds(45, 37, 210, 40);
		pnlBody.add(pnlSupplier);

		tfSupplier = new JTextField();
		tfSupplier.setForeground(Color.DARK_GRAY);
		tfSupplier.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfSupplier.setColumns(10);
		tfSupplier.setBorder(null);
		tfSupplier.setBounds(10, 5, 190, 30);
		pnlSupplier.add(tfSupplier);

		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(Color.WHITE);
		pnlFooter.setBounds(0, 158, 300, 50);
		pnlForm.add(pnlFooter);
		pnlFooter.setLayout(null);

		RoundedSubmit btnSubmit = new RoundedSubmit(40, 0, new Color(71, 180, 206), new Color(107, 195, 215));
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (tfSupplier.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty field");
					else {
						manager.createSupplier(tfSupplier.getText());
						frame.createSupplierDropdown(manager);
						CreateSupplier.this.dispose();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setBounds(80, 8, 140, 40);
		pnlFooter.add(btnSubmit);
		btnSubmit.setLayout(null);

		JLabel lblCreate = new JLabel("Create");
		lblCreate.setForeground(Color.WHITE);
		lblCreate.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblCreate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreate.setBounds(10, 0, 120, 40);
		btnSubmit.add(lblCreate);

		setUndecorated(true);
		setLocationRelativeTo(null);
	}

}
