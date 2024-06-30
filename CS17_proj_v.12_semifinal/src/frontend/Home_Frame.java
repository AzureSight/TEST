package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import backend.User;
import backend.User.Employee;
import backend.User.Manager;
import frontend.UIcomponents.CustomTable;
import frontend.UIcomponents.Design_Close;
import frontend.UIcomponents.Minimize;
import frontend.UIcomponents.RoundedClearpnl;
import frontend.UIcomponents.Sidebar;
import frontend.UIcomponents.TableFormats;

public class Home_Frame extends JFrame {

    private Image img_ViewAU = new ImageIcon(Login.class.getResource("resources/viewAU.png")).getImage()
            .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    private Image img_ViewOD = new ImageIcon(Login.class.getResource("resources/viewOD.png")).getImage()
            .getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    private Image img_ViewES = new ImageIcon(Login.class.getResource("resources/viewES.png")).getImage()
            .getScaledInstance(100, 100, Image.SCALE_SMOOTH);

    private JPanel content, pnltblAU, pnltblVO, pnltblES, pnlSidebar, pnlColorClick, pnlColorClick_1, pnlColorClick_2,
            pnlColorClick_3;

    private CustomTable tableActiveUser, tableViewOrders, tableEventsSchedule;

    private JScrollPane spActiveUser, spViewOrders, spEventsSchedule, spOutofStock;

    public Home_Frame(User user) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 700);

        content = new JPanel();
        content.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(content);
        content.setLayout(null);

        pnlSidebar = new Sidebar(user, this);
        content.add(pnlSidebar);

        JPanel pnlContent = new JPanel();
        pnlContent.setBackground(new Color(224, 224, 224));
        pnlContent.setBounds(300, 0, 980, 700);
        content.add(pnlContent);

        Design_Close lbl_Close = new Design_Close("Close", 0, this, new Color(224, 224, 224));
        lbl_Close.setText("");
        lbl_Close.setBounds(950, 0, 30, 30);
        pnlContent.setLayout(null);
        pnlContent.add(lbl_Close);

        Minimize lbl_Minimize = new Minimize("Minimize", 0, this, new Color(224, 224, 224), new Color(227, 244, 244),
                Color.white);
        lbl_Minimize.setText("");
        lbl_Minimize.setBounds(920, 0, 30, 30);
        pnlContent.setLayout(null);
        pnlContent.add(lbl_Minimize);

        RoundedClearpnl pnlHome = new RoundedClearpnl(20, 3, new Color(240, 240, 240), new Color(240, 240, 240));
        pnlHome.setBounds(10, 30, 960, 644);
        pnlContent.add(pnlHome);
        pnlHome.setLayout(null);

        RoundedClearpnl pnlGreetings = new RoundedClearpnl(20, 3, new Color(255, 255, 255), new Color(255, 255, 255));
        pnlGreetings.setBackground(Color.WHITE);
        pnlGreetings.setBounds(10, 11, 940, 50);
        pnlHome.add(pnlGreetings);
        pnlGreetings.setLayout(null);

        JLabel lblGreetings = new JLabel();
        lblGreetings.setText("Hello, and Welcome Back! ");
        lblGreetings.setForeground(Color.DARK_GRAY);
        lblGreetings.setFont(new Font("SansSerif", Font.BOLD, 23));
        lblGreetings.setBounds(15, 5, 294, 40);
        pnlGreetings.add(lblGreetings);

        JLabel lblName = new JLabel("");
        lblName.setVerticalAlignment(SwingConstants.TOP);
        lblName.setForeground(Color.DARK_GRAY);
        lblName.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblName.setBounds(315, 0, 350, 50);
        pnlGreetings.add(lblName);

        RoundedClearpnl pnlShortcut = new RoundedClearpnl(20, 3, new Color(255, 255, 255), new Color(255, 255, 255));
        pnlShortcut.setBackground(Color.WHITE);
        pnlShortcut.setBounds(7, 70, 943, 250);
        pnlHome.add(pnlShortcut);
        pnlShortcut.setLayout(null);

        RoundedClearpnl btnActiveUsers = new RoundedClearpnl(50, 3, new Color(116, 140, 241), new Color(126, 160, 255));
        btnActiveUsers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActiveUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pnltblAU.setVisible(true);
                pnltblAU.setEnabled(true);
                pnltblVO.setVisible(false);
                pnltblVO.setEnabled(false);
                pnltblES.setVisible(false);
                pnltblES.setEnabled(false);

                pnlColorClick.setVisible(true);
                pnlColorClick_1.setVisible(false);
                pnlColorClick_2.setVisible(false);
                if (pnlColorClick_3 != null)
                    pnlColorClick_3.setVisible(false);
                pnlColorClick.setBackground(new Color(198, 209, 249));
            }
        });
        btnActiveUsers.setBackground(new Color(116, 140, 241));

        JLabel lblNewLabel = new JLabel("View");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 32));
        lblNewLabel.setBounds(7, 11, 80, 39);
        btnActiveUsers.add(lblNewLabel);

        JLabel lblActiveUsers = new JLabel("Active Users");
        lblActiveUsers.setForeground(Color.WHITE);
        lblActiveUsers.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lblActiveUsers.setBounds(15, 45, 180, 39);
        btnActiveUsers.add(lblActiveUsers);

        JLabel iconViewAU = new JLabel("");
        iconViewAU.setHorizontalAlignment(SwingConstants.CENTER);
        iconViewAU.setBounds(90, 119, 100, 100);
        iconViewAU.setIcon(new ImageIcon(img_ViewAU));
        btnActiveUsers.add(iconViewAU);

        pnlColorClick = new JPanel();
        pnlColorClick.setVisible(false);
        pnlColorClick.setBounds(25, 220, 152, 10);
        btnActiveUsers.add(pnlColorClick);

        RoundedClearpnl btnViewOrders = new RoundedClearpnl(50, 3, new Color(243, 249, 251), new Color(233, 229, 241));
        btnViewOrders.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnViewOrders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pnltblAU.setVisible(false);
                pnltblAU.setEnabled(false);
                pnltblVO.setVisible(true);
                pnltblVO.setEnabled(true);
                pnltblES.setVisible(false);
                pnltblES.setEnabled(false);

                pnlColorClick.setVisible(false);
                pnlColorClick_1.setVisible(true);
                pnlColorClick_2.setVisible(false);
                if (pnlColorClick_3 != null)
                    pnlColorClick_3.setVisible(false);
                pnlColorClick_1.setBackground(new Color(230, 242, 247));
            }
        });
        btnViewOrders.setBackground(new Color(243, 249, 251));

        JLabel lblOrders = new JLabel("Orders");
        lblOrders.setForeground(Color.DARK_GRAY);
        lblOrders.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lblOrders.setBounds(20, 45, 180, 39);
        btnViewOrders.add(lblOrders);

        JLabel lblNewLabel_1 = new JLabel("View");
        lblNewLabel_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 32));
        lblNewLabel_1.setBounds(7, 11, 80, 39);
        btnViewOrders.add(lblNewLabel_1);

        JLabel iconViewOD = new JLabel("");
        iconViewOD.setHorizontalAlignment(SwingConstants.CENTER);
        iconViewOD.setBounds(90, 119, 100, 100);
        iconViewOD.setIcon(new ImageIcon(img_ViewOD));
        btnViewOrders.add(iconViewOD);

        pnlColorClick_1 = new JPanel();
        pnlColorClick_1.setVisible(false);
        pnlColorClick_1.setBounds(25, 220, 152, 10);
        btnViewOrders.add(pnlColorClick_1);

        RoundedClearpnl btnEventSchedule = new RoundedClearpnl(50, 3, new Color(250, 237, 240),
                new Color(220, 217, 231));
        btnEventSchedule.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEventSchedule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pnltblAU.setVisible(false);
                pnltblAU.setEnabled(false);
                pnltblVO.setVisible(false);
                pnltblVO.setEnabled(false);
                pnltblES.setVisible(true);
                pnltblES.setEnabled(true);

                pnlColorClick.setVisible(false);
                pnlColorClick_1.setVisible(false);
                if (pnlColorClick_3 != null)
                    pnlColorClick_3.setVisible(false);
                pnlColorClick_2.setVisible(true);
                pnlColorClick_2.setBackground(new Color(248, 228, 233));
            }
        });
        btnEventSchedule.setBackground(new Color(250, 237, 240));
        btnEventSchedule.setBounds(703, 11, 200, 230);
        pnlShortcut.add(btnEventSchedule);
        btnEventSchedule.setLayout(null);

        JLabel lblActiveUsers_1_1 = new JLabel("Event Schedule");
        lblActiveUsers_1_1.setForeground(Color.DARK_GRAY);
        lblActiveUsers_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lblActiveUsers_1_1.setBounds(15, 45, 180, 39);
        btnEventSchedule.add(lblActiveUsers_1_1);

        JLabel lblNewLabel_1_1 = new JLabel("View");
        lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 32));
        lblNewLabel_1_1.setBounds(7, 11, 80, 39);
        btnEventSchedule.add(lblNewLabel_1_1);

        JLabel iconViewES = new JLabel("");
        iconViewES.setHorizontalAlignment(SwingConstants.CENTER);
        iconViewES.setBounds(90, 119, 100, 100);
        iconViewES.setIcon(new ImageIcon(img_ViewES));
        btnEventSchedule.add(iconViewES);

        pnlColorClick_2 = new JPanel();
        pnlColorClick_2.setVisible(false);
        pnlColorClick_2.setBounds(25, 220, 152, 10);
        btnEventSchedule.add(pnlColorClick_2);

        pnltblAU = new JPanel();
        pnltblAU.setBackground(Color.white);
        pnltblAU.setVisible(false);
        pnltblAU.setEnabled(false);
        pnltblAU.setBounds(7, 325, 943, 310);
        pnlHome.add(pnltblAU);
        pnltblAU.setLayout(null);

        pnltblVO = new JPanel();
        pnltblVO.setBackground(Color.white);
        pnltblVO.setVisible(false);
        pnltblVO.setEnabled(false);
        pnltblVO.setBounds(7, 325, 943, 310);
        pnlHome.add(pnltblVO);
        pnltblVO.setLayout(null);

        pnltblES = new JPanel();
        pnltblES.setBackground(Color.white);
        pnltblES.setVisible(false);
        pnltblES.setEnabled(false);
        pnltblES.setBounds(7, 325, 943, 310);
        pnlHome.add(pnltblES);
        pnltblES.setLayout(null);

        tableActiveUser = new CustomTable(3, 14) {
            @Override
            public void setModel(TableModel dataModel) {
                super.setModel(dataModel);
                if (dataModel.getColumnCount() > 0)
                    TableFormats.toActiveUsers(this);
            };
        };
        tableViewOrders = new CustomTable(3, 14) {
            @Override
            public void setModel(TableModel dataModel) {
                super.setModel(dataModel);
                if (dataModel.getColumnCount() > 0)
                    TableFormats.toViewOrders(this);
            };
        };
        tableEventsSchedule = new CustomTable(3, 14) {
            @Override
            public void setModel(TableModel dataModel) {
                super.setModel(dataModel);
                if (dataModel.getColumnCount() > 0)
                    TableFormats.toEventSchedules(this);
            };
        };

        if (user instanceof Employee) {

            Employee employee = (Employee) user;

            lblName.setText(employee.getFname() + " " + employee.getLname());

            btnActiveUsers.setBounds(40, 11, 200, 230);
            pnlShortcut.add(btnActiveUsers);
            btnActiveUsers.setLayout(null);

            btnViewOrders.setBounds(375, 11, 200, 230);
            pnlShortcut.add(btnViewOrders);
            btnViewOrders.setLayout(null);

            btnEventSchedule.setBounds(703, 11, 200, 230);
            pnlShortcut.add(btnEventSchedule);
            btnEventSchedule.setLayout(null);

            tableActiveUser.setModel(employee.viewAllActiveUsers());
            tableViewOrders.setModel(employee.viewAllOrders());
            tableEventsSchedule.setModel(employee.viewAllEventSchedules());

        } else {

            Manager manager = (Manager) user;

            lblName.setText(manager.getFname() + " " + manager.getLname());

            btnActiveUsers.setBounds(20, 11, 200, 230);
            pnlShortcut.add(btnActiveUsers);
            btnActiveUsers.setLayout(null);

            btnViewOrders.setBounds(250, 11, 200, 230);
            pnlShortcut.add(btnViewOrders);
            btnViewOrders.setLayout(null);

            btnEventSchedule.setBounds(490, 11, 200, 230);
            pnlShortcut.add(btnEventSchedule);
            btnEventSchedule.setLayout(null);

            tableActiveUser.setModel(manager.viewAllActiveUsers());
            tableViewOrders.setModel(manager.viewAllOrders());
            tableEventsSchedule.setModel(manager.viewAllEventSchedules());

            JPanel pnltblOS = new JPanel();
            pnltblOS.setBackground(Color.white);
            pnltblOS.setVisible(false);
            pnltblOS.setEnabled(false);
            pnltblOS.setBounds(7, 325, 943, 310);
            pnlHome.add(pnltblOS);
            pnltblOS.setLayout(null);

            pnlColorClick_3 = new JPanel();
            pnlColorClick_3.setBounds(25, 220, 152, 10);
            pnlColorClick_3.setVisible(false);

            CustomTable tableOutofStock = new CustomTable(3, 14) {
                @Override
                public void setModel(TableModel dataModel) {
                    super.setModel(dataModel);
                    if (dataModel.getColumnCount() > 0)
                        TableFormats.toOutofStock(this);
                };
            };
            tableOutofStock.setModel(manager.viewOutOfStockItems());
            spOutofStock = new JScrollPane(tableOutofStock, 20, 31);
            spOutofStock.setBounds(10, 10, 925, 290);
            pnltblOS.add(spOutofStock);

            RoundedClearpnl btnStocks = new RoundedClearpnl(50, 3, new Color(236, 37, 90), new Color(206, 17, 80));
            btnStocks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnStocks.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    pnltblAU.setVisible(false);
                    pnltblAU.setEnabled(false);
                    pnltblVO.setVisible(false);
                    pnltblVO.setEnabled(false);
                    pnltblES.setVisible(false);
                    pnltblES.setEnabled(true);
                    pnltblOS.setVisible(true);
                    pnltblOS.setEnabled(true);
                    pnlColorClick.setVisible(false);
                    pnlColorClick_1.setVisible(false);
                    pnlColorClick_2.setVisible(false);
                    pnlColorClick_3.setVisible(true);
                    pnlColorClick_3.setBackground(new Color(250, 190, 206));
                }
            });
            btnStocks.setBackground(new Color(236, 37, 90));
            btnStocks.setBounds(723, 11, 200, 230);

            btnStocks.add(pnlColorClick_3);
            pnlShortcut.add(btnStocks);
            btnStocks.setLayout(null);

            Image img_ViewOSI = new ImageIcon(Login.class.getResource("resources/viewOSI.png")).getImage()
                    .getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            JLabel lblActiveUsers_1_2 = new JLabel("Out of Stock Items");
            lblActiveUsers_1_2.setForeground(Color.WHITE);
            lblActiveUsers_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            lblActiveUsers_1_2.setBounds(12, 45, 180, 39);
            btnStocks.add(lblActiveUsers_1_2);

            JLabel lblNewLabel_1_2 = new JLabel("View");
            lblNewLabel_1_2.setForeground(Color.WHITE);
            lblNewLabel_1_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 32));
            lblNewLabel_1_2.setBounds(6, 11, 80, 39);
            btnStocks.add(lblNewLabel_1_2);

            JLabel iconViewOSI = new JLabel("");
            iconViewOSI.setHorizontalAlignment(SwingConstants.CENTER);
            iconViewOSI.setBounds(92, 119, 100, 100);
            iconViewOSI.setIcon(new ImageIcon(img_ViewOSI));
            btnStocks.add(iconViewOSI);

        }

        spActiveUser = new JScrollPane(tableActiveUser, 20, 31);
        spActiveUser.setBounds(10, 10, 925, 290);
        pnltblAU.add(spActiveUser);

        spViewOrders = new JScrollPane(tableViewOrders, 20, 31);
        spViewOrders.setBounds(10, 10, 925, 290);
        pnltblVO.add(spViewOrders);

        spEventsSchedule = new JScrollPane(tableEventsSchedule, 20, 31);
        spEventsSchedule.setBounds(10, 10, 925, 290);
        pnltblES.add(spEventsSchedule);

        setUndecorated(true);
        setLocationRelativeTo(null);

    }
}
