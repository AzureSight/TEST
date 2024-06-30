package frontend.UIcomponents;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Design_btnSidebar extends JPanel {
	
	public Design_btnSidebar()
	{
		
		setBackground(new Color(71, 180, 206));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(107, 195, 215));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Color(71, 180, 206));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(new Color(71, 180, 206));
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(new Color(112, 200, 220));
			}
		});
		
	}
    
    

    
}
