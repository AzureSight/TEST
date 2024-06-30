package frontend.UIcomponents.CalendarComponents;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import backend.User;
import backend.Event.EventSchedule;
import frontend.AddSchedule;
import frontend.EventToday;
import frontend.Scheduling_Frame;
import frontend.UpdateSchedule;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class PanelDate extends javax.swing.JLayeredPane {

    private int month;
    private int year;

    public PanelDate(int month, int year, User user, Scheduling_Frame sched_frame) {
        initComponents();
        this.month = month;
        this.year = year;
        this.user = user;
        init(sched_frame);
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return year;
    }

    private void init(Scheduling_Frame sched_frame) {
        for (int i = 0; i < dayheaders.length; i++) {
            dayheaders[i].asTitle();
        }
        setDate(sched_frame);
    }

    public void setDate(Scheduling_Frame sched_frame) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // month jan as 0 so start from 0
        calendar.set(Calendar.DATE, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1; // get day of week -1 to index
        calendar.add(Calendar.DATE, -startDay);
        ToDay today = getToDay();
        for (Cell cell : daynums) {
            if (!cell.isTitle()) {

                int selected_month = calendar.get(Calendar.MONTH) + 1;
                int selected_year = calendar.get(Calendar.YEAR);
                int selected_day = calendar.get(Calendar.DATE);

                cell.setText(selected_day + "");
                cell.setDate(calendar.getTime());

                cell.currentMonth(selected_month == month);
                if (today.isToday(new ToDay(selected_day, selected_month, selected_year))) {
                    cell.setAsToDay();
                }

                EventSchedule booked_sched = user.viewIfScheduleBooked(selected_month, selected_year, selected_day);
                if (selected_month == month) {
                    if (!cell.isToDay() && calendar.after(Calendar.getInstance())) {
                        Date thisdate = calendar.getTime();
                        if (booked_sched == null) {

                            cell.asAvailable(); // mark date as available
                            cell.setToolTipText("Available for Booking");

                        } else {
                            cell.asUnavailable(booked_sched);// mark date as unavailable
                            cell.setToolTipText("Already Booked");
                        }

                        cell.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (cell.isAvailable())
                                    // frame for available date
                                    new AddSchedule(user, thisdate, cell).setVisible(true);
                                else if (cell.isUnavailable())
                                    // frame for unavailable date
                                    new UpdateSchedule(user, cell.getBooked_schedule(), PanelDate.this, sched_frame)
                                            .setVisible(true);
                            }
                        });
                    } else if (cell.isToDay()) {
                        if (booked_sched == null) {
                            cell.addActionListener(new ActionListener() {
                                // block for today's event frame
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(null, "No scheduled event for today.");
                                }
                            });
                        } else {
                            cell.addActionListener(new ActionListener() {
                                // block for today's event frame
                                public void actionPerformed(ActionEvent e) {
                                    new EventToday(user, booked_sched).setVisible(true);
                                }
                            });
                        }
                    }
                }
                calendar.add(Calendar.DATE, 1); // up 1 day
            }
        }
    }

    private ToDay getToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }

    void initComponents() {

        setLayout(new GridLayout(7, 7));
        for (int i = 0; i < dayheaders.length; i++) {
            dayheaders[i] = new Cell(0);
            dayheaders[i].setFont(new Font("sanserif", 0, 14));
        }

        dayheaders[0].setForeground(new java.awt.Color(222, 12, 12));
        dayheaders[0].setText("Sun");
        add(dayheaders[0]);

        dayheaders[1].setText("Mon");
        add(dayheaders[1]);

        dayheaders[2].setText("Tue");
        add(dayheaders[2]);

        dayheaders[3].setText("Wed");
        add(dayheaders[3]);

        dayheaders[4].setText("Thu");
        add(dayheaders[4]);

        dayheaders[5].setText("Fri");
        add(dayheaders[5]);

        dayheaders[6].setText("Sat");
        add(dayheaders[6]);

        for (int i = 0; i < daynums.length; i++) {
            daynums[i] = new Cell(10);
            daynums[i].setFont(new Font("sanserif", 0, 14));

            add(daynums[i]);
        }

    }

    public Cell[] getDaynums() {
        return daynums;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Cell[] dayheaders = new Cell[7];
    private Cell[] daynums = new Cell[42];

    private User user;

}