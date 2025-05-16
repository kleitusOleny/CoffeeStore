package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransactionHistoryDialog extends JDialog {

    public TransactionHistoryDialog(JFrame parent) {
        super(parent, "Lịch Sử Giao Dịch", true);
        setSize(900, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 248, 220));

        String[] columns = {"STT", "Tên", "SĐT", "Số Tiền", "Phương Thức Thanh Toán", "Ngày Thanh Toán"};
        Object[][] data = {
                {"1", "Nguyễn Văn A", "0393445667", "75.000đ", "Tiền mặt", "15/05/2025"},
                {"2", "Trần Thị B", "0933447788", "100.000đ", "Thẻ tín dụng", "16/05/2025"}
        };

        JTable table = new JTable(new DefaultTableModel(data, columns)) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.setRowHeight(35);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        table.getTableHeader().setPreferredSize(new Dimension(100, 40));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
    }
}
