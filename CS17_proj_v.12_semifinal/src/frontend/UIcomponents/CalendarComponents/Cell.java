package frontend.UIcomponents.CalendarComponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import backend.Event.EventSchedule;

public class Cell extends JButton{
	
	private int borderRadius;
	private Color backgroundColor;
	
	protected Date date;
	private JButton cell;
	private boolean title;
	private boolean isToDay;
    private boolean unavailable;
    private boolean available;
    private EventSchedule booked_schedule = null;
	
	public Cell(int borderRadius) {
		setContentAreaFilled(false);
		setBorder(null);
		setHorizontalAlignment(JLabel.CENTER);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setFocusable(false);
				
        this.borderRadius = borderRadius;
        this.backgroundColor = new Color(255, 255, 255);
        
        addMouseListener(new MouseAdapter() {		     
            @Override
            public void mouseEntered(MouseEvent e) {
                backgroundColor = new Color(227, 244, 244);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backgroundColor = new Color(255, 255, 255);;
            }
	    });        
        
	}
	
	public int getBorderRadius() {
	        return borderRadius;
	}

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    // Getter and setter for the backgroundColor
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
	
	public void asTitle() {
        title = true;
    }

    public boolean isTitle() {
        return title;
    }

    public void asAvailable() {
        available = true;
        unavailable = false;
    }

    public boolean isAvailable() {
        return available;
    }

    public void asUnavailable(EventSchedule booked_sched) {
        unavailable = true;
        available = false;
        this.booked_schedule = booked_sched;
    }

    public boolean isUnavailable() {
        return unavailable;
    }    

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public boolean isDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        int thisDay = calendar.get(Calendar.DATE);
        int thisMonth = calendar.get(Calendar.MONTH) + 1;
        int thisYear = calendar.get(Calendar.YEAR);
        return thisDay == day && thisMonth == month && thisYear == year;
    }

    public void currentMonth(boolean act) {
        if (act) {
            setForeground(new Color(0, 0, 0));
            setFont(new Font("SansSerif", Font.BOLD, 16));
              
        } else {
            setForeground(new Color(169, 169, 169));
        }
    }
    
    public void setAsToDay() {
        isToDay = true;
        setForeground(Color.WHITE);
    }
    
    public boolean isToDay() {
        return isToDay;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2;
        int x = getWidth() / 2 - 17, y = getHeight() / 2 - 17;
        if (title) {
            grphcs.setColor(new Color(0, 0, 0));
            grphcs.drawLine(5, getHeight() - 1, getWidth(), getHeight() - 1);
        }
        if (isToDay) {
            g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(71, 180, 206));
            g2.fillRoundRect(x, y, 35, 35, 100, 100);
        }
        else if (isAvailable()) {
            g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(80, 216, 144));
            g2.fillRoundRect(x, y, 35, 35, 100, 100);
        }
        else if (isUnavailable()) {
            g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(168, 0, 56));
            g2.fillRoundRect(x, y, 35, 35, 100, 100);
        }
        
        else {
        	Graphics2D g2d = (Graphics2D) grphcs;
        	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Create a rounded rectangle shape for the label
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

            // Set the background color
            g2d.setColor(backgroundColor);
            g2d.fill(shape);
        }
        super.paintComponent(grphcs);

        grphcs.dispose();
    }

	public JButton getCell() {
		return cell;
	}

	public void setCell(JButton cell) {
		this.cell = cell;
	}

    public EventSchedule getBooked_schedule() {
        return booked_schedule;
    }
	
}