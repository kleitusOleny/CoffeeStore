package view;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class CustomPanel extends JPanel {

    private Color startGradientColor = null; // null = không vẽ gradient
    private Color endGradientColor = null;
    private Color backgroundColor = null;    // nếu set thì vẽ màu đơn

    private Color borderColor = new Color(0, 102, 204);
    private int borderRadius = 20;
    private boolean drawBorder = true;
    private int borderThickness = 2;

    // Shadow parameters
    private boolean drawShadow = true;
    private Color shadowColor = new Color(0, 0, 0, 50); // đen mờ
    private int shadowOffsetX = 4;
    private int shadowOffsetY = 4;
    private int shadowSize = 8;

    public CustomPanel() {
        setOpaque(false); // để trong suốt nếu không vẽ nền
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (drawShadow) {
            g2.setColor(shadowColor);
            RoundRectangle2D shadowRect = new RoundRectangle2D.Float(
                    shadowOffsetX, shadowOffsetY,
                    width - shadowSize, height - shadowSize,
                    borderRadius, borderRadius);
            g2.fill(shadowRect);
        }

        // Vẽ nền dựa trên màu đã set (ưu tiên gradient nếu cả 2 màu gradient khác null)
        if (startGradientColor != null && endGradientColor != null) {
            Paint paint = new GradientPaint(0, 0, startGradientColor, width, height, endGradientColor);
            g2.setPaint(paint);
            g2.fillRoundRect(0, 0, width, height, borderRadius, borderRadius);
        } else if (backgroundColor != null) {
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, width, height, borderRadius, borderRadius);
        }

        if (drawBorder) {
            g2.setStroke(new BasicStroke(borderThickness));
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, width - 1, height - 1, borderRadius, borderRadius);
        }

        g2.dispose();

        super.paintComponent(g);
    }

    // --- Setter và Getter ---

    // Set gradient màu nền
    public void setGradientColors(Color startGradientColor, Color endGradientColor) {
        this.startGradientColor = startGradientColor;
        this.endGradientColor = endGradientColor;
        this.backgroundColor = null; // bỏ màu nền đơn nếu có
        repaint();
    }

    // Set màu nền đơn
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.startGradientColor = null;
        this.endGradientColor = null;
        repaint();
    }

    public void clearBackground() {
        this.backgroundColor = null;
        this.startGradientColor = null;
        this.endGradientColor = null;
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

    public void setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
        repaint();
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint();
    }

    public void setDrawShadow(boolean drawShadow) {
        this.drawShadow = drawShadow;
        repaint();
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        repaint();
    }

    public void setShadowOffsetX(int shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        repaint();
    }

    public void setShadowOffsetY(int shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        repaint();
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
        repaint();
    }
}
