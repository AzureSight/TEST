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

import backend.Item;
import backend.User.Manager;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;

public class CreateCause extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfCause;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCause frame = new CreateCause(null, null);
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
	public CreateCause(Manager manager, ConfirmCause_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(330, 250);
	
		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 2));
		pnlContent.setBackground(new Color(224, 224, 224));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);
		
		JPanel containerPayment = new RoundedClearpnl(15, 3, new Color(240, 240, 240), new Color(240, 240, 240));
		containerPayment.setBackground(new Color(240, 240, 240));
		containerPayment.setBounds(10, 10, 310, 230);
		pnlContent.add(containerPayment);
		containerPayment.setLayout(null); 	
		
		RoundedClearpnl pnlForm =  new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlForm.setBounds(5, 5, 300, 220);
		containerPayment.add(pnlForm);
		pnlForm.setLayout(null);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlHeader.setBackground(Color.WHITE);
		pnlHeader.setBounds(0, 5, 300, 50);
		pnlForm.add(pnlHeader);
		pnlHeader.setLayout(null);
		
		Design_Back lbl_Back = new Design_Back ("Back" , 50, this, new Color(255, 255, 255));
		lbl_Back.setBounds(5, 0, 40, 40);
		pnlHeader.add(lbl_Back);
		lbl_Back.setText("");
		
		JLabel lblItemHeader = new JLabel("Create Cause of");
		lblItemHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemHeader.setForeground(new Color(102, 102, 102));
		lblItemHeader.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblItemHeader.setBounds(0, 0, 300, 23);
		pnlHeader.add(lblItemHeader);
		
		JLabel lblOfStockOut = new JLabel("Stock Out");
		lblOfStockOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblOfStockOut.setForeground(new Color(102, 102, 102));
		lblOfStockOut.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblOfStockOut.setBounds(0, 20, 300, 25);
		pnlHeader.add(lblOfStockOut);
		
		JPanel pnlBody = new JPanel();
		pnlBody.setBackground(new Color(255, 255, 255));
		pnlBody.setBounds(0, 54, 300, 105);
		pnlForm.add(pnlBody);
		pnlBody.setLayout(null);
		
		JLabel lblCause = new JLabel("Cause Detail");
		lblCause.setForeground(new Color(102, 102, 102));
		lblCause.setFont(new Font("SansSerif", Font.BOLD, 17));
		lblCause.setBounds(45, 15, 180, 20);
		pnlBody.add(lblCause);
		
		RoundedSearchPnl pnlCause = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206), new Color(71, 180, 206));
		pnlCause.setLayout(null);
		pnlCause.setBounds(45, 37, 210, 40);
		pnlBody.add(pnlCause);
		
		tfCause = new JTextField();
		tfCause.setForeground(Color.DARK_GRAY);
		tfCause.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfCause.setColumns(10);
		tfCause.setBorder(null);
		tfCause.setBounds(10, 5, 190, 30);
		pnlCause.add(tfCause);
		
		JPanel pnlFooter = new JPanel();
		pnlFooter.setBackground(Color.WHITE);
		pnlFooter.setBounds(0, 158, 300, 50);
		pnlForm.add(pnlFooter);
		pnlFooter.setLayout(null);
		
		RoundedSubmit btnSubmit = new RoundedSubmit(40, 0, new Color(71, 180, 206), new Color(107, 195, 215));
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (tfCause.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty field");
					else {
						manager.createCause(new Item(-1).new Cause(tfCause.getText()));
						frame.createCauseDropdown(manager);
						CreateCause.this.dispose();
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
