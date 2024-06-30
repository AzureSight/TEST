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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import frontend.Login;

import java.awt.geom.RoundRectangle2D;

public class Design_Back extends JLabel{
	private int borderRadius;
	private Color backgroundColor;
	private JFrame currentFrame;
	private Image img_Back;
		 
	public Design_Back(String text, int borderRadius, JFrame currentFrame, Color backgroundColor) {
        super(text);
        this.borderRadius = borderRadius;
        this.backgroundColor = backgroundColor;
    	this.img_Back = new ImageIcon(Login.class.getResource("resources/Back.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    	
    	setIcon(new ImageIcon(img_Back));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFont(new Font("Arial", Font.PLAIN, 23));
		addMouseListener(new MouseAdapter() {	
	
				@Override
				public void mouseClicked(MouseEvent e) {
					int option = JOptionPane.showConfirmDialog(
	                        null,
	                        "Are you sure you want to return to the previous screen?",
	                        "",
	                        JOptionPane.YES_NO_OPTION);

	                if (option == JOptionPane.YES_OPTION) {
	                	currentFrame.dispose(); 
	                }
					
				}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(Color.BLACK);
				setFont(new Font("Arial", Font.PLAIN, 23));
				setBackgroundColor(new Color(227, 244, 244));
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
	 
	 // Getter and setter for the borderRadius
	 	public JFrame getCurrentFrame () {
	 		return currentFrame;
	 	}
	 	
	 	public void setCurrentFrame(JFrame currentFrame) {
	 		this.currentFrame = currentFrame;
	 		repaint();
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
