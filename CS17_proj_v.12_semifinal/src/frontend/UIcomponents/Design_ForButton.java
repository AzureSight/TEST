package frontend.UIcomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Design_ForButton extends JButton {
    private Color backgroundColor;
    private Color borderColor;
    private int borderRadius;

    public Design_ForButton(String text) {
        super(text);
        backgroundColor = Color.WHITE; // Default background color
        borderColor = Color.BLACK; // Default border color
        borderRadius = 10; // Default border radius
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, borderRadius, borderRadius);

        g2d.setColor(backgroundColor);
        g2d.fill(roundedRectangle);

        g2d.setColor(borderColor);
        g2d.draw(roundedRectangle);

        super.paintComponent(g);
    }

    @Override
    public Insets getInsets() {
        int value = borderRadius / 2;
        return new Insets(value, value, value, value);
    }
}