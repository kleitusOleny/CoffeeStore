package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

// Lớp CustomHeaderRenderer để tùy chỉnh tiêu đề
public class CustomHeaderRenderer extends DefaultTableCellRenderer {
    public CustomHeaderRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa văn bản
        setBackground(Color.DARK_GRAY); // Màu nền đen
        setForeground(Color.WHITE); // Màu chữ trắng
        setFont(getFont().deriveFont(Font.BOLD)); // In đậm
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        // In hoa tiêu đề
        setText(value != null ? value.toString().toUpperCase() : "");
        return this;
    }
}

