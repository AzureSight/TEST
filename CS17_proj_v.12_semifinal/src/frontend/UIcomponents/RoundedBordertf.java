package frontend.UIcomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class RoundedBordertf extends JTextField {

    private int arcWidth;
    private int arcHeight;

    public RoundedBordertf(int columns, int arcWidth, int arcHeight) {
        super(columns);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape border = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2d.setColor(getBackground());
        g2d.fill(border);

        super.paintComponent(g);

        g2d.setColor(getForeground());
        g2d.draw(border);

        g2d.dispose();
    }
}