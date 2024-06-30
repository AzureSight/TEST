package frontend.UIcomponents;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollPaneUI extends BasicScrollBarUI {
	

    @Override
    protected void configureScrollBarColors() {
        trackColor = new Color(240, 240, 240); // Background color of the scroll track
        thumbColor = new Color(100, 100, 100); // Color of the scroll thumb
        thumbDarkShadowColor = new Color(50, 50, 50); // Color of the shadow on the thumb
        thumbHighlightColor = new Color(150, 150, 150); // Color of the highlight on the thumb
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createEmptyButton(); // Remove the decrease button
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createEmptyButton(); // Remove the increase button
    }

    private JButton createEmptyButton() {
        JButton button = new JButton();
        Dimension zeroDim = new Dimension(0, 0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;
    }
}