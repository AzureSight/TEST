package frontend.UIcomponents;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Design_TableHeader extends JLabel{

	public Design_TableHeader (String text) {
		
	super(text);
     setOpaque(true);
     setBackground(Color.WHITE);
     setFont(new Font("sansserif", 1, 15));
     setForeground(new Color(102, 102, 102));
     setBorder(new EmptyBorder(10, 5, 10, 5));
     
	}
	 
	 @Override
	    protected void paintComponent(Graphics grphcs) {
	        super.paintComponent(grphcs);
	        grphcs.setColor(new Color(0, 0, 0));
	        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
	    }
}
