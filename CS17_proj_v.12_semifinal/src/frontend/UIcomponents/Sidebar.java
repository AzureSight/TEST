package frontend.UIcomponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import backend.User;
import backend.User.Employee;
import backend.User.Manager;
import frontend.EventPackages_Frame;
import frontend.Home_Frame;
import frontend.Inventory_Frame;
import frontend.Login;
import frontend.ManageStock_Frame;
import frontend.ManageUser_Frame;
import frontend.Scheduling_Frame;
import frontend.Transaction_Frame;

public class Sidebar extends JPanel {
    
    private Design_btnSidebar btnHome, btnSchedule, btnLogout, btnTransaction, btnEandP, btnManageUser, btnInventory, btnManageStock;
    private JLabel lblLogo, iconHome, lblHome, iconSchedule, lblSchedule, iconLogout, lblLogout;

    public Sidebar(User user, JFrame currentFrame) {
        setBackground(new Color(71, 180, 206));
        setBounds(0, 0, 300, 700);
        setLayout(null);

        lblLogo = new JLabel();
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(10, 5, 280, 191);
        lblLogo.setIcon(new ImageIcon(SidebarIcons.LOGO));
        add(lblLogo);

        btnHome = new Design_btnSidebar();
        btnHome.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Home_Frame(user).setVisible(true);
                currentFrame.dispose();
            }
        });
        btnHome.setLayout(null);

        iconHome = new JLabel();
        iconHome.setHorizontalAlignment(SwingConstants.CENTER);
        iconHome.setBounds(40, 0, 60, 55);
        iconHome.setIcon(new ImageIcon(SidebarIcons.HOME));
        btnHome.add(iconHome);

        lblHome = new JLabel("Home");
        lblHome.setForeground(Color.WHITE);
        lblHome.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblHome.setBounds(100, 0, 185, 55);
        btnHome.add(lblHome);

        btnSchedule = new Design_btnSidebar();
        btnSchedule.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                currentFrame.dispose();
                new Scheduling_Frame(user).setVisible(true);
            }
        });
        btnSchedule.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSchedule.setLayout(null);

        iconSchedule = new JLabel();
        iconSchedule.setHorizontalAlignment(SwingConstants.CENTER);
        iconSchedule.setBounds(40, 0, 60, 55);
        iconSchedule.setIcon(new ImageIcon(SidebarIcons.SCHEDULE));
        btnSchedule.add(iconSchedule);

        lblSchedule = new JLabel("Scheduling");
        lblSchedule.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblSchedule.setForeground(new Color(255, 255, 255));
        lblSchedule.setBounds(100, 0, 185, 55);
        btnSchedule.add(lblSchedule);

        add(btnHome);
        add(btnSchedule);

        if (user instanceof Employee) {
            
            btnHome.setBounds(0, 240, 300, 55);
            btnSchedule.setBounds(0, 310, 300, 55);

            btnTransaction = new Design_btnSidebar();
            btnTransaction.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new Transaction_Frame((Employee) user).setVisible(true);
                    currentFrame.dispose();
                }
            });
            btnTransaction.setLayout(null);
            btnTransaction.setBounds(0, 380, 300, 55);
            add(btnTransaction);

            JLabel iconTransaction = new JLabel();
            iconTransaction.setHorizontalAlignment(SwingConstants.CENTER);
            iconTransaction.setBounds(40, 0, 60, 55);
            iconTransaction.setIcon(new ImageIcon(SidebarIcons.TRANSACTION));
            btnTransaction.add(iconTransaction);

            JLabel lblTransaction = new JLabel("Cashier");
            lblTransaction.setFont(new Font("SansSerif", Font.BOLD, 22));
            lblTransaction.setForeground(new Color(255, 255, 255));
            lblTransaction.setBounds(100, 0, 185, 55);
            btnTransaction.add(lblTransaction);

        } else {
            btnHome.setBounds(0, 220, 300, 55);
            btnSchedule.setBounds(0, 290, 300, 55);
            
            btnEandP = new Design_btnSidebar();
            btnEandP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnEandP.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new EventPackages_Frame((Manager) user).setVisible(true);
                    currentFrame.dispose();
                }
            });
            btnEandP.setBounds(0, 360, 300, 55);
            add(btnEandP);
            btnEandP.setLayout(null);

            JLabel iconEandP = new JLabel();
            iconEandP.setHorizontalAlignment(SwingConstants.CENTER);
            iconEandP.setBounds(40, 0, 60, 55);
            iconEandP.setIcon(new ImageIcon(SidebarIcons.EVENT_PACKAGES));
            btnEandP.add(iconEandP);

            JLabel lblEandP = new JLabel("Event & Packages");
            lblEandP.setForeground(new Color(255, 255, 255));
            lblEandP.setFont(new Font("SansSerif", Font.BOLD, 20));
            lblEandP.setBounds(100, 0, 185, 55);
            btnEandP.add(lblEandP);
            
            btnManageUser = new Design_btnSidebar();
            btnManageUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnManageUser.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new ManageUser_Frame((Manager) user).setVisible(true);
                    currentFrame.dispose();
                }
            });
            btnManageUser.setLayout(null);
            btnManageUser.setBounds(0, 430, 300, 55);
            add(btnManageUser);

            JLabel iconManageUser = new JLabel();
            iconManageUser.setHorizontalAlignment(SwingConstants.CENTER);
            iconManageUser.setBounds(39, 0, 60, 55);
            iconManageUser.setIcon(new ImageIcon(SidebarIcons.MANAGE_USER));
            btnManageUser.add(iconManageUser);

            JLabel lblManageUser = new JLabel("Manage User");
            lblManageUser.setFont(new Font("SansSerif", Font.BOLD, 22));
            lblManageUser.setForeground(new Color(255, 255, 255));
            lblManageUser.setBounds(100, 0, 185, 55);
            btnManageUser.add(lblManageUser);

            btnInventory = new Design_btnSidebar();
            btnInventory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnInventory.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new Inventory_Frame((Manager) user).setVisible(true);
                    currentFrame.dispose();
                }
            });
            btnInventory.setLayout(null);
            btnInventory.setBounds(0, 500, 300, 55);
            add(btnInventory);

            JLabel iconInventory = new JLabel();
            iconInventory.setHorizontalAlignment(SwingConstants.CENTER);
            iconInventory.setBounds(40, 0, 60, 55);
            iconInventory.setIcon(new ImageIcon(SidebarIcons.INVENTORY));
            btnInventory.add(iconInventory);

            JLabel lblInventory = new JLabel("Inventory");
            lblInventory.setFont(new Font("SansSerif", Font.BOLD, 22));
            lblInventory.setForeground(new Color(255, 255, 255));
            lblInventory.setBounds(100, 0, 185, 55);
            btnInventory.add(lblInventory);
            
            btnManageStock = new Design_btnSidebar();
            btnManageStock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnManageStock.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new ManageStock_Frame((Manager) user).setVisible(true);
                    currentFrame.dispose();
                }
            });
            btnManageStock.setBounds(0, 570, 300, 55);
            add(btnManageStock);
            btnManageStock.setLayout(null);

            JLabel iconManageStock = new JLabel();
            iconManageStock.setHorizontalAlignment(SwingConstants.CENTER);
            iconManageStock.setBounds(40, 0, 60, 55);
            iconManageStock.setIcon(new ImageIcon(SidebarIcons.MANAGE_STOCK));
            btnManageStock.add(iconManageStock);

            JLabel lblManageStock = new JLabel("Stock Management");
            lblManageStock.setForeground(new Color(255, 255, 255));
            lblManageStock.setFont(new Font("SansSerif", Font.BOLD, 20));
            lblManageStock.setBounds(100, 0, 185, 55);
            btnManageStock.add(lblManageStock);
        }

        btnLogout = new Design_btnSidebar();
        btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login(null).setVisible(true);
                currentFrame.dispose();
            }
        });
        btnLogout.setLayout(null);
        btnLogout.setBounds(0, 640, 300, 55);
        add(btnLogout);

        iconLogout = new JLabel();
        iconLogout.setHorizontalAlignment(SwingConstants.CENTER);
        iconLogout.setBounds(40, 0, 60, 55);
        iconLogout.setIcon(new ImageIcon(SidebarIcons.LOGOUT));
        btnLogout.add(iconLogout);

        lblLogout = new JLabel("Logout");
        lblLogout.setFont(new Font("SansSerif", Font.BOLD, 21));
        lblLogout.setForeground(new Color(255, 255, 255));
        lblLogout.setBounds(100, 0, 185, 55);
        btnLogout.add(lblLogout);

        if(currentFrame instanceof Home_Frame)
            btnHome.setBackground(new Color(107, 195, 215));
        else if(currentFrame instanceof Scheduling_Frame)
            btnSchedule.setBackground(new Color(107, 195, 215));
        else if(currentFrame instanceof Transaction_Frame)
            btnTransaction.setBackground(new Color(107, 195, 215));
        else if(currentFrame instanceof EventPackages_Frame)
            btnEandP.setBackground(new Color(107, 195, 215));
        else if(currentFrame instanceof ManageUser_Frame)
            btnManageUser.setBackground(new Color(107, 195, 215));
        else if(currentFrame instanceof Inventory_Frame)
            btnInventory.setBackground(new Color(107, 195, 215));
        else if(currentFrame instanceof ManageStock_Frame)
            btnManageStock.setBackground(new Color(107, 195, 215));
    }

}