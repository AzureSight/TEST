package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableModel;

import backend.User.Manager;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Back;
import frontend.UIcomponents.Design_PanelBorder;
import frontend.UIcomponents.Design_SearchText;
import frontend.UIcomponents.RoundedSearchPnl;
import frontend.UIcomponents.TableFormats;

public class Stockouttable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent;
	private Image img_Search = new ImageIcon(Login.class.getResource("resources/SearchUser.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Design_SearchText tfSearch;
	protected CustomTable table;
	private JScrollPane scrollpane;

	/**
	 * Create the frame.
	 */
	public Stockouttable(Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 460);
		
		pnlContent = new JPanel();
		pnlContent.setBorder(new LineBorder(new Color(18, 72, 107), 5));
		pnlContent.setBackground(new Color(255, 255, 255));
		
		setContentPane(pnlContent);
		pnlContent.setLayout(null);
				
		Design_PanelBorder container = new Design_PanelBorder();
		container.setBackground(new Color(244, 244, 255));
		container.setBounds(5, 5, 890, 440);
		pnlContent.add(container);
		container.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(18, 72, 107));
		panel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(18, 72, 107)));
		panel.setBounds(0, 0, 890, 75);
		container.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Stock Out Table");
		lblNewLabel.setForeground(new Color(18, 72, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 40, 291, 30);
		panel.add(lblNewLabel);
		
		RoundedSearchPnl pnlSearch = new RoundedSearchPnl (25, 2, Color.white, new Color(18, 72, 107), new Color(18, 72, 107));
		pnlSearch.setBackground(Color.WHITE);
		pnlSearch.setBounds(630, 30, 250, 40);
		pnlSearch.setLayout(null);
		panel.add(pnlSearch);
		
		CustomTable table = new CustomTable(5, 14) {
			@Override
			public void setModel(TableModel dataModel) {
				super.setModel(dataModel);
				if(dataModel.getColumnCount() > 0)
					TableFormats.toStockOutTable(this);
			}
		};
		table.setModel(manager.viewStockouts());
		scrollpane = new JScrollPane(table, 20, 31);

		scrollpane.setBounds(5, 90, 880, 350);
		container.add(scrollpane);
		
		tfSearch = new Design_SearchText();
		tfSearch.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				String search = tfSearch.getText();
				if (search.equals("")) {
					table.setModel(manager.viewStockouts());
				} else {
					try {			
						table.setModel(manager.searchStockouts(search));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "A search problem has occured!\n"+e1.getMessage(), "Search Stock out", 1);
					}
				}
			}
			
		});
		tfSearch.setForeground(new Color (106, 106, 106));
		tfSearch.setFont(new Font("SansSerif", Font.PLAIN, 15));
		tfSearch.setBorder(null);
		tfSearch.setBounds(18, 10, 180, 20);
		pnlSearch.add(tfSearch);
		tfSearch.setColumns(10);
		
		JLabel iconSearch = new JLabel("");
		iconSearch.setHorizontalAlignment(SwingConstants.CENTER);
		iconSearch.setBounds(210, 5, 40, 30);
		iconSearch.setIcon(new ImageIcon(img_Search));
		pnlSearch.add(iconSearch);
		
		Design_Back lbl_Back = new Design_Back ("Back" , 0, this, new Color(238, 237, 237));
		lbl_Back.setBackgroundColor(new Color(240, 240, 240));
		lbl_Back.setBackground(new Color(240, 240, 240));
		lbl_Back.setBorderRadius(50);
		lbl_Back.setText("");
		lbl_Back.setBounds(0, 0, 40, 40);
		panel.add(lbl_Back);
		
		
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

}
