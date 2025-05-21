package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTable extends JTable {

    private Color selectedColor = new Color(236, 177, 118); // màu vàng nhạt khi chọn

    public CustomTable() {
        setRowHeight(30);
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
        setSelectionBackground(selectedColor);
        setFillsViewportHeight(true);

        // Cài đặt cell renderer để tô màu nền xen kẽ và màu nền khi chọn
        setDefaultRenderer(Object.class, new RoundedCellRenderer());
    }

    class RoundedCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                c.setBackground(selectedColor);
            } else {
                // Màu nền xen kẽ giữa các dòng
                if (row % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {

                    c.setBackground(new Color(245, 245, 220)); // màu be nhạt
                }
            }

            return c;
        }

        @Override
        protected void paintComponent(Graphics g) {
            // Chỉ tô màu nền, không vẽ viền hay bo tròn
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());

            super.paintComponent(g);
        }
    }
}
