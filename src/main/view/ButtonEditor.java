package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public abstract class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;

    public ButtonEditor(JTable table) {
        super(new JCheckBox());
        this.table = table;

        button = new JButton();
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);

        // Resize icon nhỏ lại (cùng size với renderer)
        ImageIcon icon = new ImageIcon("src\\main\\image\\bin.png");
        Image scaled = icon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaled));
        button.setPreferredSize(new Dimension(24, 24));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Đảm bảo thoát khỏi chế độ chỉnh sửa trước khi xóa
                fireEditingStopped();
                int row = table.getSelectedRow();
                if (row >= 0) {
                    // Dùng invokeLater để tránh lỗi khi JTable vẫn đang xử lý
                    SwingUtilities.invokeLater(() -> {
                        ((DefaultTableModel) table.getModel()).removeRow(row);
                    });
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }

    protected abstract void performAction(int row);
}
