package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import backend.User.Employee;
import frontend.UIcomponents.Design_PanelBorder;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;

public class PopFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;

	/**
	 * Create the frame.
	 */
	public PopFrame(Employee emp, double change, Checkout_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 285);
	
		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);
				
		Design_PanelBorder container = new Design_PanelBorder();
		container.setBackground(new Color(238, 237, 237));
		container.setBounds(5, 5, 340, 275);
		pnlContent.add(container);
		container.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Change");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblNewLabel.setBounds(10, 20, 320, 50);
		container.add(lblNewLabel);
		
		RoundedSearchPnl pnlEventName = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107), new Color(18, 72, 107));
		pnlEventName.setBounds(35, 100, 270, 40);
		container.add(pnlEventName);
		pnlEventName.setLayout(null);
		
		JLabel lblEventname = new JLabel(String.valueOf(change));
		lblEventname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEventname.setForeground(Color.DARK_GRAY);
		lblEventname.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblEventname.setBounds(10, 5 ,250, 30);
		pnlEventName.add(lblEventname);
		
		RoundedSubmit pnlSubmit = new RoundedSubmit(50, 0, new Color(18, 72, 107), new Color(18, 72, 127));
		pnlSubmit.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				PopFrame.this.dispose();
				frame.dispose();
			}
		});
		pnlSubmit.setBounds(65, 180, 210, 40);
		container.add(pnlSubmit);
		pnlSubmit.setLayout(null);
		
		JLabel lblSubmit = new JLabel("Close");
		lblSubmit.setBounds(10, 5, 190, 30);
		pnlSubmit.add(lblSubmit);
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

}
