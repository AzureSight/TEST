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

public class Design_Close extends JLabel{
	private int borderRadius;
	private Color backgroundColor;
	private JFrame frame;
	private Image img_Close;
		 
	public Design_Close(String text, int borderRadius, JFrame frame, Color backgroundColor) {
        super(text);
        this.borderRadius = borderRadius;
        this.backgroundColor = backgroundColor;
        this.img_Close = new ImageIcon(Login.class.getResource("resources/Close.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    	
    	setIcon(new ImageIcon(img_Close));
		setFont(new Font("Arial", Font.PLAIN, 23));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new MouseAdapter() {		
	
				@Override
				public void mouseClicked(MouseEvent e) {
					int option = JOptionPane.showConfirmDialog(
	                        null,
	                        "Are you sure you want to close this window?",
	                        "",
	                        JOptionPane.YES_NO_OPTION);

	                if (option == JOptionPane.YES_OPTION) {
	                   frame.dispose(); 
	                }
					
				}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(Color.WHITE);
				setFont(new Font("Arial", Font.PLAIN, 23));
				setIcon(new ImageIcon(img_Close));
				setBackgroundColor(new Color(253, 138, 138));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(Color.BLACK);
				setFont(new Font("Arial", Font.PLAIN, 23));
				setIcon(new ImageIcon(img_Close));
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
	 	public JFrame getFrame () {
	 		return frame;
	 	}
	 	
	 	public void setFrame(JFrame frame) {
	 		this.frame = frame;
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
