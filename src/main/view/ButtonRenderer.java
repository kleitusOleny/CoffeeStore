package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setHorizontalAlignment(SwingConstants.CENTER);

        // Resize icon nhỏ lại (ví dụ 16x16)
        ImageIcon icon = new ImageIcon("src\\main\\image\\bin.png");
        Image scaled = icon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaled));

        setPreferredSize(new Dimension(24, 24));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
