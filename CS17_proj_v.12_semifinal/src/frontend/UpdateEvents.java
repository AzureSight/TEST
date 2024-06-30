package frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import backend.Event;
import backend.User.Manager;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.RoundedSubmit;

import javax.swing.JTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class UpdateEvents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private JTextField tfPD;
	private JTextField tfEventID;

	/**
	 * Create the frame.
	 */
	public UpdateEvents(Manager manager, Event event, EventPackages_Frame frame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(390, 400);

		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 10));
		pnlContent.setBackground(new Color(255, 255, 255));

		setContentPane(pnlContent);
		pnlContent.setLayout(null);

		JPanel container = new JPanel();
		container.setBackground(new Color(238, 237, 237));
		container.setBounds(5, 5, 380, 390);
		pnlContent.add(container);
		container.setLayout(null);

		Design_Back lbl_Back = new Design_Back("Back", 50, this, new Color(238, 237, 237));
		lbl_Back.setBackgroundColor(new Color(238, 237, 237));
		lbl_Back.setText("");
		lbl_Back.setBounds(5, 5, 40, 40);
		container.add(lbl_Back);

		RoundedSearchPnl pnlEventID = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlEventID.setBounds(55, 125, 270, 40);
		container.add(pnlEventID);
		pnlEventID.setLayout(null);

		tfEventID = new JTextField(String.valueOf(event.getId()));
		tfEventID.setEditable(false);
		tfEventID.setBackground(Color.white);
		tfEventID.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfEventID.setColumns(10);
		tfEventID.setBorder(null);
		tfEventID.setBounds(10, 5, 250, 30);
		pnlEventID.add(tfEventID);

		JLabel lblSelectEvent = new JLabel("Event ID");
		lblSelectEvent.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSelectEvent.setBounds(55, 100, 147, 25);
		lblSelectEvent.setVisible(true);
		container.add(lblSelectEvent);

		JLabel lblEventName = new JLabel("Event Name");
		lblEventName.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEventName.setBounds(55, 175, 147, 25);
		container.add(lblEventName);

		RoundedSearchPnl pnlPD = new RoundedSearchPnl(25, 2, new Color(255, 255, 255), new Color(18, 72, 107),
				new Color(18, 72, 107));
		pnlPD.setBounds(55, 200, 270, 40);
		container.add(pnlPD);
		pnlPD.setLayout(null);

		tfPD = new JTextField(event.getDesc());
		tfPD.setBorder(null);
		tfPD.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				tfPD.selectAll();
			}

		});
		tfPD.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfPD.setBounds(10, 5, 250, 30);
		pnlPD.add(tfPD);
		tfPD.setColumns(10);

		RoundedSubmit pnlSubmit = new RoundedSubmit(50, 0, new Color(18, 72, 107), new Color(18, 72, 127));
		pnlSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int id = Integer.parseInt(tfEventID.getText());
				String desc = tfPD.getText();

				try {
					if (desc.equals(""))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty field", "", 1);
					else {
						manager.updateEvent(new Event(id, desc));
						frame.tableEvent.setModel(manager.viewAllEvents());

						frame.cbSelectEvent.removeAllItems();
						frame.cevents = manager.chooseEvent();
						for (Event event : frame.cevents) {
							frame.cbSelectEvent.addItem(event.getDesc());
						}
						frame.cbSelectEvent.setSelectedIndex(0);

						UpdateEvents.this.dispose();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage(), "Update Event", 0);
				}
			}
		});
		pnlSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlSubmit.setBounds(85, 280, 210, 40);
		container.add(pnlSubmit);
		pnlSubmit.setLayout(null);

		JLabel lblSubmit = new JLabel("Submit");
		lblSubmit.setForeground(Color.WHITE);
		lblSubmit.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.setBounds(10, 0, 190, 40);
		pnlSubmit.add(lblSubmit);

		JLabel lblNewLabel = new JLabel("Update Event Name");
		lblNewLabel.setBounds(55, 40, 270, 50);
		container.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 23));

		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
