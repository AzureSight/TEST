package frontend;

import java.awt.Color;
import java.awt.Cursor;

import backend.Event;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import backend.User.Manager;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.Design_PanelBorder;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;

import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddEvents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfEventname;

	/**
	 * Create the frame.
	 */
	public AddEvents(Manager manager, EventPackages_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(390, 350);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		Design_PanelBorder container = new Design_PanelBorder();
		container.setBackground(new Color(238, 237, 237));
		container.setBounds(5, 5, 380, 340);
		pnlContent.add(container);
		container.setLayout(null);

		Design_Back lbl_Back = new Design_Back("Back", 0, this, new Color(238, 237, 237));
		lbl_Back.setBorderRadius(50);
		lbl_Back.setBackgroundColor(new Color(238, 237, 237));
		lbl_Back.setText("");
		lbl_Back.setBounds(5, 5, 40, 40);
		container.add(lbl_Back);

		RoundedSearchPnl pnlEventName = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlEventName.setBounds(55, 144, 270, 40);
		container.add(pnlEventName);
		pnlEventName.setLayout(null);

		JLabel lblEventname = new JLabel("Event Name");
		lblEventname.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEventname.setBounds(55, 120, 147, 25);
		container.add(lblEventname);

		tfEventname = new JTextField();
		tfEventname.setBorder(null);
		tfEventname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfEventname.selectAll();
			}

		});
		tfEventname.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfEventname.setBounds(10, 5, 250, 30);
		pnlEventName.add(tfEventname);
		tfEventname.setColumns(10);

		RoundedSubmit pnlSubmit = new RoundedSubmit(50, 0, new Color(18, 72, 107), new Color(18, 72, 127));
		pnlSubmit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try {
					if (tfEventname.getText().equals("") || tfEventname.getText().equals("Event name here..."))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty field", "Add Event", 1);
					else {
						String desc = tfEventname.getText();
						manager.createEvent(new Event(desc));
						frame.tableEvent.setModel(manager.viewAllEvents());

						frame.cbSelectEvent.removeAllItems();
						frame.cevents = manager.chooseEvent();
						for (Event event : frame.cevents) {
							frame.cbSelectEvent.addItem(event.getDesc());
						}
						frame.cbSelectEvent.setSelectedIndex(0);
						AddEvents.this.dispose();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage(), "Add Event", 0);
				}
			}
		});
		pnlSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlSubmit.setBounds(85, 235, 210, 40);
		container.add(pnlSubmit);
		pnlSubmit.setLayout(null);

		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setBounds(10, 0, 190, 40);
		pnlSubmit.add(lblSubmit);

		JLabel lblNewLabel = new JLabel("Add Event Name");
		lblNewLabel.setBounds(55, 60, 280, 50);
		container.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
