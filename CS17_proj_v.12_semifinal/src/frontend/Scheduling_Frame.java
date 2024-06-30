package frontend;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.User;
import frontend.UIcomponents.Design_Close;
import frontend.UIcomponents.Minimize;
import frontend.UIcomponents.Sidebar;
import frontend.UIcomponents.CalendarComponents.CalendarCustom;

public class Scheduling_Frame extends JFrame {

    private JPanel Content, pnlSidebar, pnlContent;
    private CalendarCustom calendar;
    private Design_Close lbl_Close;
    private Minimize lbl_Minimize;

    public Scheduling_Frame(User user) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 700);

        Content = new JPanel();
        Content.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(Content);
        Content.setLayout(null);

        pnlSidebar = new Sidebar(user, this);
        Content.add(pnlSidebar);

        pnlContent = new JPanel();
        pnlContent.setBackground(new Color(224, 224, 224));
        pnlContent.setBounds(300, 0, 980, 700);
        Content.add(pnlContent);

        lbl_Close = new Design_Close("Close", 0, this, new Color(224, 224, 224));
        lbl_Close.setText("");
        lbl_Close.setBounds(950, 0, 30, 30);
        pnlContent.setLayout(null);
        pnlContent.add(lbl_Close);

        lbl_Minimize = new Minimize("Minimize", 0, this, new Color(224, 224, 224), new Color(227, 244, 244),
                Color.white);
        lbl_Minimize.setText("");
        lbl_Minimize.setBounds(920, 0, 30, 30);
        pnlContent.setLayout(null);
        pnlContent.add(lbl_Minimize);

        calendar = new CalendarCustom(user, this);
        calendar.setBounds(30, 30, 920, 640);

        pnlContent.add(calendar);

        setUndecorated(true);
        setLocationRelativeTo(null);
    }
}
