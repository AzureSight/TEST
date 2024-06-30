package frontend.UIcomponents;

import javax.swing.*;
import java.awt.*;

class ShadowPanel extends JPanel {
    private int shadowSize = 500; // Adjust the shadow size as needed

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        // Draw the shadow
        g2d.setColor(new Color(0, 0, 0)); // Shadow color with transparency
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2, 10, 10);

        g2d.dispose();
    }
}