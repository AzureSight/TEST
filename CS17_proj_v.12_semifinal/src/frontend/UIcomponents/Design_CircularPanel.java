package frontend.UIcomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Design_CircularPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Convert the Graphics object to Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLACK); // Change the color as desired

        // Calculate the position and size of the circle
        int circleX = 300; // X-coordinate of the circle's top-left corner
        int circleY = 450; // Y-coordinate of the circle's top-left corner
        int circleSize = 500; // Diameter of the circle

        // Draw a filled circle
        Ellipse2D.Double circle = new Ellipse2D.Double(circleX, circleY, circleSize, circleSize);
        g2d.fill(circle);
    }
    
}