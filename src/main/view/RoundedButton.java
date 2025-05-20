package view;

import java.awt.*;
import javax.swing.*;

// class để bo tròn các Button
public class RoundedButton extends JButton {
    private int radius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setContentAreaFilled(false); 
        setFocusPainted(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker()); 
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter()); 
        } else {
            g2.setColor(getBackground()); 
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

        super.paintComponent(g);

        g2.dispose(); 
    }
    @Override
    public void paintBorder(Graphics g) {
    }

    @Override
    public boolean contains(int x, int y) {
        return new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius)
                .contains(x, y);
    }
}
