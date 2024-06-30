package frontend.UIcomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class RoundedSubmit extends JPanel {
    private Color borderColor;
    private int borderRadius;
    private int borderThickness = 1; // Added border thickness
    private Color originalBorderColor;
    // private Color clickedBorderColor;
    private Color backgroundColor;
    private Color hoverColor;

    public RoundedSubmit(int borderRadius, int borderThickness, Color backgroundColor, Color hoverColor) {
        this.setBorderRadius(borderRadius);
        this.borderThickness = borderThickness; // Set the border thickness
        this.originalBorderColor = backgroundColor;
        this.borderColor = originalBorderColor;
        // this.clickedBorderColor = backgroundColor;
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;

        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorderColor(hoverColor);
                setBackgroundColor(hoverColor);
                setBorderThickness(2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorderColor(originalBorderColor);
                setBackgroundColor(backgroundColor);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBorderColor(originalBorderColor);
                setBackgroundColor(backgroundColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBorderColor(hoverColor);
                setBackgroundColor(hoverColor);
                setBorderThickness(2);
            }
        });
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        repaint();
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Adjust the border thickness
        int adjustedThickness = borderThickness / 2;

        // Create a rounded rectangle with adjusted dimensions for the border
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                adjustedThickness, adjustedThickness, width - borderThickness, height - borderThickness,
                borderRadius, borderRadius);

        // Fill the background
        g2d.setColor(backgroundColor);
        g2d.fill(roundedRectangle);

        // Set the stroke thickness for the border
        g2d.setStroke(new BasicStroke(borderThickness));

        // Draw the border
        g2d.setColor(borderColor);
        g2d.draw(roundedRectangle);

        g2d.dispose();
    }

	public Color getHoverColor() {
		return hoverColor;
	}

	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}

    }