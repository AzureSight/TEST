package frontend.UIcomponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import frontend.Login;

import java.awt.geom.RoundRectangle2D;

public class Minimize extends JLabel{
	private int borderRadius;
	private Color backgroundColor;
	private Color colorClicked;
	private Image img_Minimize;
	private Color hoverColor;

		 
	public Minimize(String text, int borderRadius, JFrame frame, Color backgroundColor, Color hoverColor, Color colorClicked) {
        super(text);
        this.borderRadius = borderRadius;
        this.colorClicked = Color.white;
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
    	this.img_Minimize = new ImageIcon(Login.class.getResource("resources/Minimize.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    	
    	setIcon(new ImageIcon(img_Minimize));
		setForeground(Color.BLACK);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFont(new Font("Arial", Font.PLAIN, 23));
		addMouseListener(new MouseAdapter() {	
	
				@Override
				public void mouseClicked(MouseEvent e) {
					 if (e.getButton() == MouseEvent.BUTTON1) {
		                    // Minimize the frame
		                    frame.setExtendedState(JFrame.ICONIFIED);
		                }
		            }
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(Color.BLACK);
				setFont(new Font("Arial", Font.PLAIN, 23));
				setBackgroundColor(hoverColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(Color.BLACK);
				setFont(new Font("Arial", Font.PLAIN, 23));
				setBackgroundColor(backgroundColor);
			}
		});
		
		
		setForeground(new Color(0, 0, 0));
		setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	
	 @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g.create();
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        // Create a rounded rectangle shape for the label
	        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

	        // Set the background color
	        g2d.setColor(backgroundColor);
	        g2d.fill(shape);

	        // Call the paintComponent of the parent class to draw the text
	        super.paintComponent(g2d);

	        g2d.dispose();
	    }
	 
	    public int getBorderRadius() {
	        return borderRadius;
	    }

	    public void setBorderRadius(int borderRadius) {
	        this.borderRadius = borderRadius;
	        repaint();
	    }

	    // Getter and setter for the backgroundColor
	    public Color getBackgroundColor() {
	        return backgroundColor;
	    }

	    public void setBackgroundColor(Color backgroundColor) {
	        this.backgroundColor = backgroundColor;
	        repaint();
	    }

		public Color getHoverColor() {
			return hoverColor;
		}

		public void setHoverColor(Color hoverColor) {
			this.hoverColor = hoverColor;
		}

		public Color getColorClicked() {
			return colorClicked;
		}

		public void setColorClicked(Color colorClicked) {
			this.colorClicked = colorClicked;
		}
}
