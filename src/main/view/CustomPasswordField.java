package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CustomPasswordField extends JPasswordField {
    private Color borderColor = new Color(162, 120, 90);
    private Color startGradientColor = new Color(162, 120, 90);
    private Color endGradientColor = new Color(162, 120, 90);
    private Color backgroundColor = Color.WHITE;
    private Color hoverColor;
    private Color textColor = Color.BLACK;
    private int thickness = 3;
    private int borderRadius = 15;
    private boolean drawBorder = false;
    private boolean isHovered = false;

    public CustomPasswordField(int columns) {
        super(columns);
        setOpaque(false);
        setBorder(new EmptyBorder(5, 10, 5, 10));
        setForeground(textColor);
        setFont(new Font("Arial", Font.PLAIN, 16));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                isHovered = false;
                repaint();
            }
        });
        // Thêm FocusListener để hiện viền khi được focus
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                setDrawBorder(true);  // hiện viền khi focus
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                setDrawBorder(false); // ẩn viền khi mất focus
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isHovered && hoverColor != null) {
            g2d.setColor(hoverColor);
        } else {
            g2d.setColor(backgroundColor);
        }

        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

        super.paintComponent(g);
        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (drawBorder==true) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(thickness));

            GradientPaint gradient = new GradientPaint(0, 0, startGradientColor, getWidth(), getHeight(), endGradientColor, true);
            g2d.setPaint(gradient);
            g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, borderRadius, borderRadius);
        }
    }

    public void setTextColor(Color color) {
        this.textColor = color;
        setForeground(color);
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        this.startGradientColor = color;
        this.endGradientColor = color;
        repaint();
    }

    //Do Dam Cua Vien
    public void setBorderThickness(int thickness) {
        this.thickness = thickness;
        repaint();
    }

    public void setGradientColors(Color startColor, Color endColor) {
        this.startGradientColor = startColor;
        this.endGradientColor = endColor;
        repaint();
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public void setHoverColor(Color color) {
        this.hoverColor = color;
        repaint();
    }

    public void setBorderRadius(int radius) {
        this.borderRadius = radius;
        repaint();
    }

    public void setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
        repaint();
    }
}
