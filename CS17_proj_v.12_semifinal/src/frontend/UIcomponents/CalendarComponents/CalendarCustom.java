package frontend.UIcomponents.CalendarComponents;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import backend.User;
import frontend.Login;
import frontend.Scheduling_Frame;
import frontend.UIcomponents.RoundButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class CalendarCustom extends javax.swing.JPanel {

    private RoundButton cmdBack;
    private RoundButton cmdNext;
    private int month;
    private int year;
    private JButton cell;

    private User user;

    public CalendarCustom(User user, Scheduling_Frame sched_frame) {
        initComponents(sched_frame);
        thisMonth();
        slide.show(new PanelDate(month, year, user, sched_frame), PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd/MM-yyyy");
                    String time = tf.format(date);
                    lbTime.setText(time.split(" ")[0]);
                    lbType.setText(time.split(" ")[1]);
                    lbDate.setText(df.format(date));
                }
            }
        }).start();

        this.user = user;
    }

    @SuppressWarnings("")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents(Scheduling_Frame sched_frame) {

        slide = new PanelSlide();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbTime = new javax.swing.JLabel();
        lbType = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        cmdBack = new RoundButton("back", 75);
        cmdBack.setText("");
        lbMonthYear = new javax.swing.JLabel();
        cmdNext = new RoundButton("next", 75);
        cmdNext.setText("");

        setBackground(new java.awt.Color(255, 255, 255));

        slide.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout slideLayout = new javax.swing.GroupLayout(slide);
        slide.setLayout(slideLayout);
        slideLayout.setHorizontalGroup(
                slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));
        slideLayout.setVerticalGroup(
                slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 321, Short.MAX_VALUE));

        jPanel1.setBackground(new Color(18, 72, 157));

        jPanel2.setBackground(new java.awt.Color(19, 59, 82));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 6, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 391, Short.MAX_VALUE));

        lbTime.setFont(new java.awt.Font("sansserif", 1, 40)); // NOI18N
        lbTime.setForeground(new Color(255, 255, 255));
        lbTime.setHorizontalAlignment(SwingConstants.CENTER);
        lbTime.setText("9:32:00");

        lbType.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lbType.setForeground(new java.awt.Color(255, 255, 255));
        lbType.setText("PM");

        lbDate.setFont(new Font("SansSerif", Font.BOLD, 15)); // NOI18N
        lbDate.setForeground(new java.awt.Color(255, 255, 255));
        lbDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDate.setText("Sunday, 30/05/2021");

        panel = new JPanel();
        panel.setBackground(new Color(18, 72, 157));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lbTime, GroupLayout.PREFERRED_SIZE, 167,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(lbType, GroupLayout.PREFERRED_SIZE, 57,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(37)
                                                .addComponent(lbDate))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(jPanel2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lbTime, GroupLayout.PREFERRED_SIZE, 69,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbType))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lbDate)
                                .addGap(40)
                                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                                .addContainerGap()));
        panel.setLayout(null);

        panel_1 = new JPanel();
        panel_1.setBackground(new Color(18, 72, 157));
        panel_1.setBounds(0, 0, 265, 146);
        panel.add(panel_1);
        panel_1.setLayout(null);

        iconRed = new JLabel("RED");
        iconRed.setBounds(0, 15, 25, 25);
        iconRed.setIcon(new ImageIcon(img_Red));
        panel_1.add(iconRed);

        iconGreen = new JLabel("GREEN");
        iconGreen.setBounds(0, 50, 25, 25);
        iconGreen.setIcon(new ImageIcon(img_Green));
        panel_1.add(iconGreen);

        lblRedtxt = new JLabel("Date is already booked.\r\n");
        lblRedtxt.setForeground(Color.WHITE);
        lblRedtxt.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblRedtxt.setBounds(27, 15, 238, 25);
        panel_1.add(lblRedtxt);

        lblGreentxt = new JLabel("Date is available.\r\n");
        lblGreentxt.setForeground(Color.WHITE);
        lblGreentxt.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblGreentxt.setBounds(27, 50, 238, 25);
        panel_1.add(lblGreentxt);

        iconBlack = new JLabel("GREEN");
        iconBlack.setBounds(0, 85, 25, 25);
        iconBlack.setIcon(new ImageIcon(img_Blue));
        panel_1.add(iconBlack);

        lblBlacktxt = new JLabel("Current date.");
        lblBlacktxt.setForeground(Color.WHITE);
        lblBlacktxt.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblBlacktxt.setBounds(27, 85, 238, 25);
        panel_1.add(lblBlacktxt);
        jPanel1.setLayout(jPanel1Layout);

        cmdBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/resources/backCalendar.png"))); // NOI18N
        cmdBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdBack.setContentAreaFilled(false);
        cmdBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt, sched_frame);
            }
        });

        lbMonthYear.setFont(new java.awt.Font("sansserif", 1, 30)); // NOI18N
        lbMonthYear.setForeground(new Color(18, 72, 157));
        lbMonthYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMonthYear.setText("Month - Year");

        cmdNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/resources/nextCalendar.png"))); // NOI18N
        cmdNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdNext.setContentAreaFilled(false);
        cmdNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNextActionPerformed(evt, sched_frame);
            }
        });

        jLayeredPane1.setLayer(cmdBack, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lbMonthYear, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cmdNext, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1Layout.setHorizontalGroup(
                jLayeredPane1Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmdBack, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lbMonthYear, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(cmdNext, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
        jLayeredPane1Layout.setVerticalGroup(
                jLayeredPane1Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lbMonthYear, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addComponent(cmdNext, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addComponent(cmdBack, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE));
        jLayeredPane1.setLayout(jLayeredPane1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(slide, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                                        .addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(13)
                                .addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(slide, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(jPanel1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE));
        this.setLayout(layout);
    }

    private void cmdNextActionPerformed(java.awt.event.ActionEvent evt, Scheduling_Frame sched_frame) {
        if (month == 12) {
            month = 1;
            year++;
        } else {
            month++;
        }
        slide.show(new PanelDate(month, year, user, sched_frame), PanelSlide.AnimateType.TO_LEFT);
        showMonthYear();
    }

    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt, Scheduling_Frame sched_frame) {
        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
        slide.show(new PanelDate(month, year, user, sched_frame), PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
    }

    private void thisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // today
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
    }

    private void showMonthYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DATE, 1);
        SimpleDateFormat df = new SimpleDateFormat("MMMM-yyyy");
        lbMonthYear.setText(df.format(calendar.getTime()));
    }

    public JButton getCell() {
        return cell;
    }

    public void setCell(JButton cell) {
        this.cell = cell;
    }

    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbMonthYear;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lbType;
    private PanelSlide slide;
    private JPanel panel;
    private JPanel panel_1;
    private JLabel iconRed;
    private JLabel iconGreen;
    private JLabel lblRedtxt;
    private JLabel lblGreentxt;
    private JLabel iconBlack;
    private JLabel lblBlacktxt;
    private Image img_Red = new ImageIcon(Login.class.getResource("resources/Red.png")).getImage().getScaledInstance(25,
            25, Image.SCALE_SMOOTH);
    private Image img_Green = new ImageIcon(Login.class.getResource("resources/Green.png")).getImage()
            .getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    private Image img_Blue = new ImageIcon(Login.class.getResource("resources/Blue.png")).getImage()
            .getScaledInstance(25, 25, Image.SCALE_SMOOTH);
}