package frontend.UIcomponents;

import javax.swing.*;
import java.awt.*;

public class Design_PanelBorder extends JPanel {
	
	 public Design_PanelBorder() {
		 initComponents();
	     setOpaque(false);
	    }
	 
	 private void initComponents() {
			 
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 361, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 197, Short.MAX_VALUE)
	        );
	    }

	 @Override
	    protected void paintComponent(Graphics grphcs) {
	        Graphics2D g2 = (Graphics2D) grphcs;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(getBackground());
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
	        super.paintComponent(grphcs);
	    }
	 
	 protected void paintBorder(Graphics grphcs) {
	        Graphics2D g2 = (Graphics2D) grphcs;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(getBackground());
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
	        super.paintComponent(grphcs);
	    }
}
