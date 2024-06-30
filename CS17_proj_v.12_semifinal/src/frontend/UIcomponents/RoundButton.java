package frontend.UIcomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class RoundButton extends JButton{
	private int borderRadius;
	private Color backgroundColor;
	
	public RoundButton(String text, int borderRadius) {
		
		super(text);
        this.borderRadius = borderRadius;
        backgroundColor = new Color(255, 255, 255);
		setFocusable(false);
        
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

}
