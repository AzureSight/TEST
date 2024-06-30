package frontend;

import java.awt.Font;
import java.awt.Image;

import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

import backend.Event;
import backend.Event.EventPackage;
import backend.Event.EventPackage.IncludedItem;
import backend.Item;
import backend.User.Manager;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Close;
import frontend.UIcomponents.Design_Minimize;
import frontend.UIcomponents.Design_SearchText;
import frontend.UIcomponents.Minimize;
import frontend.UIcomponents.RoundedBorderPanel;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.Sidebar;
import frontend.UIcomponents.TableFormats;

import javax.swing.border.MatteBorder;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.event.CaretEvent;

public class EventPackages_Frame extends JFrame {

	private Image img_Add = new ImageIcon(Login.class.getResource("resources/Add.png")).getImage().getScaledInstance(23,
			23, Image.SCALE_SMOOTH);
	private Image img_Update = new ImageIcon(Login.class.getResource("resources/Update.png")).getImage()
			.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
	private Image img_Delete = new ImageIcon(Login.class.getResource("resources/Remove.png")).getImage()
			.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
	private Image img_Search = new ImageIcon(Login.class.getResource("resources/SearchUser.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_ManageEN = new ImageIcon(Login.class.getResource("resources/Festival.png")).getImage()
			.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	private Image img_ManageEP = new ImageIcon(Login.class.getResource("resources/Package.png")).getImage()
			.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

	private JPanel Content, pnlSidebar;
	private JPanel pnlEvent, pnlEventPackage, pnlClicked, pnlClicked1, pnlItems;
	private JLabel lblAdd, lblUpdate;
	protected JLabel lblPriceHere;
	private Design_SearchText tfSearch;
	private JTextField tfPD;
	protected CustomTable tableEvent;
	protected CustomTable tableItems;
	protected CustomTable tableEventPackage;
	private JScrollPane spEvent, spEventPackage, spItems;

	public EventPackages_Frame(Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 700);

		Content = new JPanel();
		Content.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Content);
		Content.setLayout(null);

		pnlSidebar = new Sidebar(manager, this);
		pnlSidebar.setBounds(0, 0, 300, 700);
		pnlSidebar.setBackground(new Color(71, 180, 206));
		Content.add(pnlSidebar);

		JPanel pnlContent = new JPanel();
		pnlContent.setBackground(new Color(224, 224, 224));
		pnlContent.setBounds(300, 0, 980, 700);
		Content.add(pnlContent);

		Design_Close lbl_Close = new Design_Close("Close", 0, this, new Color(224, 224, 224));
		lbl_Close.setText("");
		lbl_Close.setBounds(950, 0, 30, 30);
		pnlContent.setLayout(null);
		pnlContent.add(lbl_Close);

		Minimize lbl_MinimizeFrame = new Minimize("Minimize", 0, this, new Color(224, 224, 224),
				new Color(227, 244, 244), Color.white);
		lbl_MinimizeFrame.setText("");
		lbl_MinimizeFrame.setBounds(920, 0, 30, 30);
		pnlContent.setLayout(null);
		pnlContent.add(lbl_MinimizeFrame);

		RoundedClearpnl panel = new RoundedClearpnl(15, 3, new Color(240, 240, 240), new Color(240, 240, 240));
		panel.setBounds(10, 30, 960, 660);
		pnlContent.add(panel);
		panel.setLayout(null);

		// pnlWhiteBG = new JPanel();
		// pnlWhiteBG.setVisible(false);
		// pnlWhiteBG.setEnabled(false);
		// pnlWhiteBG.setBackground(Color.WHITE);
		// pnlWhiteBG.setBounds(0, 96, 960, 544);
		// panel.add(pnlWhiteBG);
		// pnlWhiteBG.setLayout(null);

		RoundedClearpnl pnlHeader = new RoundedClearpnl(15, 3, new Color(255, 255, 255), new Color(255, 255, 255));
		pnlHeader.setBackground(Color.WHITE);
		pnlHeader.setBounds(10, 10, 940, 90);
		panel.add(pnlHeader);
		pnlHeader.setLayout(null);

		tableEvent = new CustomTable(4, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if (dataModel.getColumnCount() > 0)
					TableFormats.toEventTable(this);
			};
		};

		tableEventPackage = new CustomTable(4, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if (dataModel.getColumnCount() > 0)
					TableFormats.toEventPackageTable(this);
			};
		};

		RoundedClearpnl btnEvent = new RoundedClearpnl(50, 3, new Color(116, 140, 241), new Color(106, 120, 221));
		btnEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pnlEvent.setVisible(true);
				pnlEvent.setEnabled(true);
				pnlEventPackage.setVisible(false);
				pnlEventPackage.setEnabled(false);
				pnlItems.setVisible(false);
				pnlItems.setEnabled(false);
				pnlClicked.setBackground(new Color(230, 242, 247));
				pnlClicked1.setBackground(new Color(255, 255, 255));
			}
		});
		btnEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEvent.setBackground(new Color(116, 140, 241));
		btnEvent.setBounds(140, 10, 200, 56);
		pnlHeader.add(btnEvent);
		btnEvent.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Manage");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 32));
		lblNewLabel_1.setBounds(15, 0, 115, 40);
		btnEvent.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Event Name");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(25, 28, 115, 40);
		btnEvent.add(lblNewLabel_1_1);

		JLabel iconManageEN = new JLabel();
		iconManageEN.setIcon(new ImageIcon(img_ManageEN));
		iconManageEN.setBounds(130, 3, 50, 50);
		btnEvent.add(iconManageEN);

		RoundedClearpnl btnEventPackage = new RoundedClearpnl(50, 3, new Color(236, 37, 90), new Color(216, 07, 80));
		btnEventPackage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pnlEventPackage.setVisible(true);
				pnlEventPackage.setEnabled(true);
				// pnlItems.setVisible(true);
				// pnlItems.setEnabled(true);
				pnlEvent.setVisible(false);
				pnlEvent.setEnabled(false);
				pnlClicked.setBackground(new Color(255, 255, 255));
				pnlClicked1.setBackground(new Color(248, 228, 233));
			}
		});
		btnEventPackage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEventPackage.setToolTipText("Click here to manage event packages.");
		btnEventPackage.setBackground(new Color(236, 37, 90));
		btnEventPackage.setBounds(620, 10, 200, 56);
		pnlHeader.add(btnEventPackage);
		btnEventPackage.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("Manage");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 32));
		lblNewLabel_1_2.setBounds(15, 0, 115, 40);
		btnEventPackage.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Package");
		lblNewLabel_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(25, 28, 115, 40);
		btnEventPackage.add(lblNewLabel_1_1_1);

		JLabel iconManageEP = new JLabel();
		iconManageEP.setHorizontalAlignment(SwingConstants.LEFT);
		iconManageEP.setIcon(new ImageIcon(img_ManageEP));
		iconManageEP.setBounds(130, 3, 50, 50);
		btnEventPackage.add(iconManageEP);

		pnlClicked = new JPanel();
		pnlClicked.setBackground(new Color(255, 255, 255));
		pnlClicked.setBounds(150, 65, 180, 10);
		pnlHeader.add(pnlClicked);

		pnlClicked1 = new JPanel();
		pnlClicked1.setBackground(new Color(255, 255, 255));
		pnlClicked1.setBounds(630, 65, 180, 10);
		pnlHeader.add(pnlClicked1);

		// ------------------------------------------------------------------------------------------------------------------//

		pnlEvent = new JPanel();
		pnlEvent.setVisible(true);
		pnlEvent.setEnabled(true);
		pnlEvent.setBackground(Color.WHITE);
		pnlEvent.setBounds(100, 130, 760, 500);
		panel.add(pnlEvent);
		pnlEvent.setLayout(null);

		JPanel pnlOperation1 = new JPanel();
		pnlOperation1.setBounds(0, 0, 760, 95);
		pnlOperation1.setBackground(Color.WHITE);
		pnlOperation1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(194, 194, 194)));
		pnlEvent.add(pnlOperation1);
		pnlOperation1.setLayout(null);

		RoundedBorderPanel btnAdd = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnAdd.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				AddEvents add = new AddEvents(manager, EventPackages_Frame.this);
				add.setVisible(true);

			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(10, 50, 120, 40);
		pnlOperation1.add(btnAdd);
		btnAdd.setLayout(null);

		lblAdd = new JLabel("Add Item");
		lblAdd.setForeground(Color.WHITE);
		lblAdd.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblAdd.setBounds(47, 5, 70, 30);
		btnAdd.add(lblAdd);

		JLabel iconAdd = new JLabel("");
		iconAdd.setHorizontalAlignment(SwingConstants.CENTER);
		iconAdd.setBounds(13, 5, 40, 30);
		iconAdd.setIcon(new ImageIcon(img_Add));
		btnAdd.add(iconAdd);

		RoundedBorderPanel btnUpdate = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnUpdate.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (ev == null)
					JOptionPane.showMessageDialog(null, "No row selected", "Update Event", 2);
				else {
					UpdateEvents up = new UpdateEvents(manager, ev, EventPackages_Frame.this);
					up.setVisible(true);
					ev = null;
				}
			}
		});
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setLayout(null);
		btnUpdate.setBounds(138, 50, 120, 40);
		pnlOperation1.add(btnUpdate);

		lblUpdate = new JLabel("Update");
		lblUpdate.setForeground(Color.WHITE);
		lblUpdate.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUpdate.setBounds(50, 5, 70, 30);
		btnUpdate.add(lblUpdate);

		JLabel iconUpdate = new JLabel("");
		iconUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		iconUpdate.setBounds(15, 5, 40, 30);
		iconUpdate.setIcon(new ImageIcon(img_Update));
		btnUpdate.add(iconUpdate);

		// RoundedClearpnl btnDelete = new RoundedClearpnl(40, 0, new Color(225, 127,
		// 107), new Color(220, 144, 127));
		// btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// btnDelete.setLayout(null);
		// btnDelete.setBackground(Color.WHITE);
		// btnDelete.setBounds(268, 50, 120, 40);
		// btnDelete.addMouseListener(new MouseAdapter() {
		// public void mouseClicked(MouseEvent e) {
		// if (ev == null)
		// JOptionPane.showMessageDialog(null, "No row selected!", "Remove Event", 2);
		// else {
		// int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to
		// remove this event?");
		// if (confirm == 0) {
		// try {
		// manager.deleteEvent(ev);
		// tableEvent.setModel(manager.viewAllEvents());
		// } catch (Exception e1) {
		// JOptionPane.showMessageDialog(null, "Something went wrong!\n" +
		// e1.getMessage(),
		// "Remove Event", 0);
		// }
		// }
		// }
		// }
		// });
		// pnlOperation1.add(btnDelete);

		// lblDelete = new JLabel("Remove");
		// lblDelete.setForeground(Color.WHITE);
		// lblDelete.setFont(new Font("SansSerif", Font.BOLD, 14));
		// lblDelete.setBounds(50, 5, 70, 30);
		// btnDelete.add(lblDelete);

		// JLabel iconDelete = new JLabel("");
		// iconDelete.setHorizontalAlignment(SwingConstants.CENTER);
		// iconDelete.setBounds(15, 5, 40, 30);
		// iconDelete.setIcon(new ImageIcon(img_Delete));
		// btnDelete.add(iconDelete);

		RoundedSearchPnl pnlSearch = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlSearch.setBackground(Color.WHITE);
		pnlSearch.setBounds(507, 50, 243, 40);
		pnlSearch.setLayout(null);
		pnlOperation1.add(pnlSearch);

		tfSearch = new Design_SearchText();
		tfSearch.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				String search = tfSearch.getText();
				if (search.equals(""))
					tableEvent.setModel(manager.viewAllEvents());
				else {
					try {
						tableEvent.setModel(manager.searchEvents(search));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "A search problem has occured!\n" + e1.getMessage(),
								"Search Event", 1);
					}
				}
			}
		});
		tfSearch.setForeground(new Color(106, 106, 106));
		tfSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfSearch.setBorder(null);
		tfSearch.setBounds(10, 10, 189, 20);
		pnlSearch.add(tfSearch);
		tfSearch.setColumns(10);

		JLabel iconSearch = new JLabel("");
		iconSearch.setHorizontalAlignment(SwingConstants.CENTER);
		iconSearch.setBounds(200, 5, 40, 30);
		iconSearch.setIcon(new ImageIcon(img_Search));
		pnlSearch.add(iconSearch);

		JLabel lblTitle = new JLabel("Manage Event Name");
		lblTitle.setForeground(Color.DARK_GRAY);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitle.setBounds(0, 10, 192, 20);
		pnlOperation1.add(lblTitle);

		Design_Minimize lbl_Minimize = new Design_Minimize("Minimize", 0, pnlEvent, new Color(255, 255, 255),
				new Color(227, 244, 244), pnlClicked, Color.white);
		lbl_Minimize.setBounds(730, 0, 30, 30);
		pnlOperation1.add(lbl_Minimize);
		lbl_Minimize.setToolTipText("Minimize");
		lbl_Minimize.setText("");

		tableEvent.setModel(manager.viewAllEvents());
		spEvent = new JScrollPane(tableEvent, 20, 31);
		spEvent.setBounds(10, 100, 740, 390);
		tableEvent.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				int i = tableEvent.getSelectedRow();
				TableModel table_model = tableEvent.getModel();
				int id = Integer.parseInt(table_model.getValueAt(i, 0).toString());
				String desc = table_model.getValueAt(i, 1).toString();

				ev = new Event(id, desc);
			}
		});
		pnlEvent.add(spEvent);

		pnlEventPackage = new JPanel();
		pnlEventPackage.setVisible(false);
		pnlEventPackage.setEnabled(false);
		pnlEventPackage.setBackground(Color.WHITE);
		pnlEventPackage.setBounds(0, 115, 960, 544);
		panel.add(pnlEventPackage);
		pnlEventPackage.setLayout(null);

		JPanel pnlOperation_1 = new JPanel();
		pnlOperation_1.setBounds(0, 0, 539, 150);
		pnlOperation_1.setBackground(Color.WHITE);
		pnlOperation_1.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(194, 194, 194)));
		pnlEventPackage.add(pnlOperation_1);
		pnlOperation_1.setLayout(null);

		RoundedSearchPnl pnlSelectEvent = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlSelectEvent.setBounds(10, 40, 255, 40);
		pnlOperation_1.add(pnlSelectEvent);
		pnlSelectEvent.setLayout(null);

		cevents = manager.chooseEvent();
		cbSelectEvent = new JComboBox<String>();
		cbSelectEvent.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbSelectEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbSelectEvent.setBackground(Color.WHITE);
		for (Event event : cevents) {
			cbSelectEvent.addItem(event.getDesc());
		}
		cbSelectEvent.setSelectedIndex(0);
		cbSelectEvent.setBounds(2, 2, 251, 36);
		pnlSelectEvent.add(cbSelectEvent);

		selectedEvent = cevents[cbSelectEvent.getSelectedIndex()];

		cbSelectEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (cbSelectEvent.getItemCount() != 0) {
						selectedEvent = cevents[cbSelectEvent.getSelectedIndex()];
						tableEventPackage.setModel(manager.searchEventPackages(selectedEvent.getId()));
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});

		JLabel lblSelectEvent = new JLabel("Select Event");
		lblSelectEvent.setForeground(new Color(102, 102, 102));
		lblSelectEvent.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblSelectEvent.setBounds(10, 20, 147, 20);
		lblSelectEvent.setVisible(true);
		pnlOperation_1.add(lblSelectEvent);

		JLabel lblPD = new JLabel("Package Detail");
		lblPD.setForeground(new Color(102, 102, 102));
		lblPD.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPD.setBounds(275, 20, 147, 20);
		pnlOperation_1.add(lblPD);

		RoundedSearchPnl pnlPD = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlPD.setBounds(275, 40, 254, 40);
		pnlOperation_1.add(pnlPD);
		pnlPD.setLayout(null);

		tfPD = new JTextField();
		tfPD.setForeground(Color.DARK_GRAY);
		tfPD.setBorder(null);
		tfPD.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				tfPD.selectAll();
			}
		});
		tfPD.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				try {
					if (tfPD.getText().equals(""))
						tfPrice.setText("");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		tfPD.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfPD.setBounds(10, 5, 234, 30);
		pnlPD.add(tfPD);
		tfPD.setColumns(10);

		RoundedSearchPnl pnlPrice = new RoundedSearchPnl(25, 2, Color.white, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlPrice.setLayout(null);
		pnlPrice.setBounds(10, 105, 150, 40);
		pnlOperation_1.add(pnlPrice);

		tfPrice = new JTextField();
		tfPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				tfPrice.selectAll();
			}

		});
		tfPrice.setForeground(Color.DARK_GRAY);
		tfPrice.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfPrice.setColumns(10);
		tfPrice.setBorder(null);
		tfPrice.setBounds(10, 5, 130, 30);
		pnlPrice.add(tfPrice);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(102, 102, 102));
		lblPrice.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPrice.setBounds(10, 85, 147, 20);
		pnlOperation_1.add(lblPrice);

		RoundedBorderPanel btnCreate = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				try {
					if (tfPD.getText().equals("") && tfPrice.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "", 1);
					} else {
						String packagedesc = tfPD.getText();
						double price = Double.parseDouble(tfPrice.getText());
						manager.createEventPackages(packagedesc, price, selectedEvent);
						tableEventPackage.setModel(manager.searchEventPackages(selectedEvent.getId()));
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage(),
							"Create Event Package", 0);
				}
			}
		});
		btnCreate.setLayout(null);
		btnCreate.setBackground(Color.WHITE);
		btnCreate.setBounds(175, 108, 105, 35);
		pnlOperation_1.add(btnCreate);

		JLabel lblCreate = new JLabel("Create");
		lblCreate.setForeground(Color.WHITE);
		lblCreate.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblCreate.setBounds(40, 4, 69, 27);
		btnCreate.add(lblCreate);

		JLabel iconCreate = new JLabel("");
		iconCreate.setHorizontalAlignment(SwingConstants.CENTER);
		iconCreate.setBounds(8, 2, 38, 30);
		iconCreate.setIcon(new ImageIcon(img_Add));
		btnCreate.add(iconCreate);

		RoundedBorderPanel btnUpdate_1 = new RoundedBorderPanel(40, 0, new Color(71, 180, 206));
		btnUpdate_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ep == null)
					JOptionPane.showMessageDialog(null, "No row selected", "Update Eveny", 2);
				else {
					if (tfPD.getText().equals("") || tfPrice.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Kindly fill the empty fields", "Update Event Package", 1);
					else {
						String desc = tfPD.getText();
						double price = Double.parseDouble(tfPrice.getText());
						manager.updateEventPackage(ep.getId(), desc, price, selectedEvent);
						tableEventPackage.setModel(manager.searchEventPackages(selectedEvent.getId()));
					}
				}
			}
		});
		btnUpdate_1.setLayout(null);
		btnUpdate_1.setBackground(Color.WHITE);
		btnUpdate_1.setBounds(300, 108, 105, 35);
		pnlOperation_1.add(btnUpdate_1);

		JLabel lblUpdate_1 = new JLabel("Update");
		lblUpdate_1.setForeground(Color.WHITE);
		lblUpdate_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUpdate_1.setBounds(40, 4, 60, 27);
		btnUpdate_1.add(lblUpdate_1);

		JLabel iconUpdate_1 = new JLabel("");
		iconUpdate_1.setHorizontalAlignment(SwingConstants.CENTER);
		iconUpdate_1.setBounds(8, 2, 38, 30);
		iconUpdate_1.setIcon(new ImageIcon(img_Update));
		btnUpdate_1.add(iconUpdate_1);

		RoundedClearpnl btnDelete_1 = new RoundedClearpnl(40, 0, new Color(225, 127, 107), new Color(220, 144, 127));
		btnDelete_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (ep == null)
					JOptionPane.showMessageDialog(null, "No row selected!", "Remove Event", 2);
				else {
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this event?");
					if (confirm == 0) {
						try {
							manager.deleteEventPackage(ep);
							tableEventPackage.setModel(manager.searchEventPackages(selectedEvent.getId()));
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage(),
									"Remove Event Package", 0);
						}
					}
				}
			}
		});
		btnDelete_1.setLayout(null);
		btnDelete_1.setBackground(Color.WHITE);
		btnDelete_1.setBounds(424, 108, 105, 35);
		pnlOperation_1.add(btnDelete_1);

		JLabel lblDelete_1 = new JLabel("Remove");
		lblDelete_1.setForeground(Color.WHITE);
		lblDelete_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblDelete_1.setBounds(43, 4, 60, 27);
		btnDelete_1.add(lblDelete_1);

		JLabel iconDelete_1 = new JLabel("");
		iconDelete_1.setHorizontalAlignment(SwingConstants.CENTER);
		iconDelete_1.setBounds(10, 2, 38, 30);
		iconDelete_1.setIcon(new ImageIcon(img_Delete));
		btnDelete_1.add(iconDelete_1);

		JPanel pnlEventPackageTable = new JPanel();
		pnlEventPackageTable.setBorder(new MatteBorder(0, 0, 2, 2, (Color) new Color(192, 192, 192)));
		pnlEventPackageTable.setBackground(new Color(255, 255, 255));
		pnlEventPackageTable.setBounds(0, 150, 539, 355);
		pnlEventPackage.add(pnlEventPackageTable);
		pnlEventPackageTable.setLayout(null);

		lblPriceHere = new JLabel("PHP 0.00");
		lblPriceHere.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPriceHere.setForeground(new Color(102, 102, 102));
		lblPriceHere.setFont(new Font("SansSerif", Font.BOLD, 19));
		lblPriceHere.setBounds(808, 511, 127, 23);
		pnlEventPackage.add(lblPriceHere);

		tableEventPackage.setModel(manager.searchEventPackages(selectedEvent.getId()));
		spEventPackage = new JScrollPane(tableEventPackage, 20, 31);
		tableEventPackage.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				int i = tableEventPackage.getSelectedRow();
				TableModel table_model = tableEventPackage.getModel();
				int eventPackageID = Integer.parseInt(table_model.getValueAt(i, 2).toString());
				String desc = table_model.getValueAt(i, 3).toString();
				double price = Double.parseDouble(table_model.getValueAt(i, 4).toString());

				tfPD.setText(desc);
				tfPrice.setText(String.valueOf(price));
				ep = new EventPackage(eventPackageID);
				includeTable_model = manager.viewIncludedItems(eventPackageID);
				tableItems.setModel(includeTable_model);
				lblPriceHere.setText(df.format(calculateTotalAmount()));
			}
		});
		spEventPackage.setBounds(10, 30, 519, 320);
		pnlEventPackageTable.add(spEventPackage);

		JPanel pnlItemOperation = new JPanel();
		pnlItemOperation.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlItemOperation.setBackground(new Color(255, 255, 255));
		pnlItemOperation.setBounds(538, 0, 422, 150);
		pnlEventPackage.add(pnlItemOperation);
		pnlItemOperation.setLayout(null);

		RoundedBorderPanel btnAddItem = new RoundedBorderPanel(45, 0, new Color(71, 180, 206));
		btnAddItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (tableEventPackage.getSelectedRow() == -1)
						JOptionPane.showMessageDialog(null, "No package has been selected");
					else {
						IncludedItems addEP = new IncludedItems(manager, EventPackages_Frame.this, ep.getId());
						addEP.setVisible(true);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n");
					e1.printStackTrace();
				}
			}
		});
		btnAddItem.setBounds(30, 53, 150, 45);
		pnlItemOperation.add(btnAddItem);
		btnAddItem.setLayout(null);
		btnAddItem.setBackground(Color.WHITE);

		JLabel lblAddItem = new JLabel("Add Items");
		lblAddItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddItem.setForeground(Color.WHITE);
		lblAddItem.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblAddItem.setBounds(36, 2, 109, 43);
		btnAddItem.add(lblAddItem);

		JLabel iconAddItem = new JLabel("");
		iconAddItem.setHorizontalAlignment(SwingConstants.CENTER);
		iconAddItem.setBounds(5, 2, 45, 43);
		iconAddItem.setIcon(new ImageIcon(img_Add));
		btnAddItem.add(iconAddItem);

		RoundedClearpnl btnRemoveItem = new RoundedClearpnl(40, 0, new Color(225, 127, 107), new Color(220, 144, 127));
		btnRemoveItem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					if (tableItems.getSelectedRow() == -1)
						JOptionPane.showMessageDialog(null, "No included item has been selected");
					else {
						int i = tableItems.getSelectedRow();
						String iitemdesc = includeTable_model.getValueAt(i, 0).toString();
						int iiquantity = Integer.parseInt(includeTable_model.getValueAt(i, 1).toString());
						TableModel searchItem = manager.searchItem(iitemdesc);
						int itemID = Integer.parseInt(searchItem.getValueAt(0, 0).toString());

						int c = JOptionPane.showConfirmDialog(null, "Do you want to remove this item?\n" + iitemdesc);
						if (c == 0) {
							manager.deleteIncludedItem(
									new IncludedItem(new EventPackage(ep.getId()), new Item(itemID), iiquantity));
							includeTable_model.removeRow(i);
							tableItems.setModel(manager.viewIncludedItems(ep.getId()));

							lblPriceHere.setText(df.format(calculateTotalAmount()));
							JOptionPane.showMessageDialog(null, "Removed successfully!");
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something went wrong!\n" + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnRemoveItem.setLayout(null);
		btnRemoveItem.setBackground(Color.WHITE);
		btnRemoveItem.setBounds(240, 53, 150, 45);
		pnlItemOperation.add(btnRemoveItem);

		JLabel lblRemoveItem = new JLabel("Remove Item");
		lblRemoveItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveItem.setForeground(Color.WHITE);
		lblRemoveItem.setFont(new Font("SansSerif", Font.BOLD, 17));
		lblRemoveItem.setBounds(36, 2, 109, 43);
		btnRemoveItem.add(lblRemoveItem);

		JLabel iconRemoveItem = new JLabel("");
		iconRemoveItem.setHorizontalAlignment(SwingConstants.CENTER);
		iconRemoveItem.setBounds(3, 2, 45, 43);
		iconRemoveItem.setIcon(new ImageIcon(img_Delete));
		btnRemoveItem.add(iconRemoveItem);

		JPanel pnlViewItem = new JPanel();
		pnlViewItem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		pnlViewItem.setBackground(new Color(255, 255, 255));
		pnlViewItem.setBounds(538, 150, 422, 355);
		pnlEventPackage.add(pnlViewItem);
		pnlViewItem.setLayout(null);

		tableItems = new CustomTable(4, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if (dataModel.getColumnCount() > 0)
					TableFormats.toIncludedItemsTable(this);
			};
		};

		spItems = new JScrollPane(tableItems, 20, 31);
		spItems.setBounds(10, 30, 402, 320);
		pnlViewItem.add(spItems);

		JLabel lblTitleItem = new JLabel("Items Included");
		lblTitleItem.setBounds(0, 0, 422, 32);
		pnlViewItem.add(lblTitleItem);
		lblTitleItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleItem.setForeground(new Color(102, 102, 102));
		lblTitleItem.setFont(new Font("SansSerif", Font.BOLD, 18));

		JLabel lblTotal = new JLabel("Total Price of Included Items:");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setForeground(new Color(102, 102, 102));
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 19));
		lblTotal.setBounds(538, 510, 335, 23);
		pnlEventPackage.add(lblTotal);

		JLabel lblTitle1 = new JLabel("Manage Event Packages");
		pnlEventPackageTable.add(lblTitle1);
		lblTitle1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle1.setForeground(new Color(102, 102, 102));
		lblTitle1.setFont(new Font("SansSerif", Font.BOLD, 18));

		pnlItems = new JPanel();
		pnlItems.setVisible(false);
		pnlItems.setEnabled(false);
		pnlItems.setBackground(new Color(255, 255, 255));
		pnlItems.setBounds(605, 96, 355, 544);
		panel.add(pnlItems);
		pnlItems.setLayout(null);

		// JLabel lblTitle2 = new JLabel("Items Included for the Package");
		// lblTitle2.setBounds(0, 0, 355, 23);
		// pnlItemOperation.add(lblTitle2);
		// lblTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		// lblTitle2.setForeground(new Color(102, 102, 102));
		// lblTitle2.setFont(new Font("SansSerif", Font.BOLD, 18));

		JPanel pnlHeader1 = new JPanel();
		pnlHeader1.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(194, 194, 194)));
		pnlHeader1.setBackground(new Color(255, 255, 255));
		pnlHeader1.setBounds(0, 427, 355, 117);
		pnlItems.add(pnlHeader1);
		pnlHeader1.setLayout(null);

		RoundedSearchPnl pnlItem_1 = new RoundedSearchPnl(25, 2, Color.WHITE, new Color(71, 180, 206),
				new Color(71, 180, 206));
		pnlItem_1.setBounds(72, 48, 212, 40);
		pnlHeader1.add(pnlItem_1);
		pnlItem_1.setLayout(null);

		tfQuantity = new JTextField();
		tfQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		tfQuantity.setForeground(Color.DARK_GRAY);
		tfQuantity.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tfQuantity.setColumns(10);
		tfQuantity.setBorder(null);
		tfQuantity.setBounds(10, 5, 192, 30);
		pnlItem_1.add(tfQuantity);

		setUndecorated(true);
		setLocationRelativeTo(null);

	}

	private double calculateTotalAmount() {
		double totalAmount = 0;
		for (int j = 0; j < includeTable_model.getRowCount(); j++) {
			int quantity = Integer.parseInt(includeTable_model.getValueAt(j, 1).toString());
			double price = Double.parseDouble(includeTable_model.getValueAt(j, 2).toString());
			totalAmount += quantity * price;
		}
		return totalAmount;
	}

	protected Event[] cevents;
	protected JComboBox<String> cbSelectEvent;
	private Event ev;
	private EventPackage ep;
	private Event selectedEvent;
	protected DefaultTableModel includeTable_model;
	private JTextField tfQuantity, tfPrice;
	private DecimalFormat df = new DecimalFormat("PHP 0.00");
}
