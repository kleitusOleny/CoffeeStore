package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomCheckBox extends JCheckBox {
    private Color backgroundColor = Color.WHITE;
    private Color checkColor = new Color(79, 195, 247); // Màu khi được chọn
    private Color hoverColor = new Color(224, 242, 241); // Màu khi hover
    private Color borderColor = Color.GRAY;
    private Color textColor = Color.BLACK;
    private int borderRadius = 8;
    private boolean hovered = false;

    public CustomCheckBox(String text) {
        super(text);
        setOpaque(false);
        setFocusPainted(false);
        setFont(new Font("SansSerif", Font.PLAIN, 16));
        setForeground(textColor);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setIcon(new CheckBoxIcon());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    private class CheckBoxIcon implements Icon {
        private final int size = 22;

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Màu nền khi hover hoặc chọn
            if (isSelected()) {
                g2.setColor(checkColor);
            } else if (hovered) {
                g2.setColor(hoverColor);
            } else {
                g2.setColor(backgroundColor);
            }

            g2.fillRoundRect(x, y, size, size, borderRadius, borderRadius);

            // Border
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRoundRect(x, y, size, size, borderRadius, borderRadius);

            // Dấu check
            if (isSelected()) {
                g2.setStroke(new BasicStroke(2.5f));
                g2.setColor(Color.WHITE);
                g2.drawLine(x + 5, y + 12, x + 10, y + 17);
                g2.drawLine(x + 10, y + 17, x + 17, y + 6);
            }

            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }
    }

    // ===== Setter tùy biến =====
    public void setCheckColor(Color color) {
        this.checkColor = color;
        repaint();
    }

    public void setHoverColor(Color color) {
        this.hoverColor = color;
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setBorderRadius(int radius) {
        this.borderRadius = radius;
        repaint();
    }

    public void setTextColor(Color color) {
        this.textColor = color;
        setForeground(color);
        repaint();
    }

    public void setFontSize(int size) {
        setFont(getFont().deriveFont((float) size));
    }
}
