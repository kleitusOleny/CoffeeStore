package view.Staff;

import data.ReadFileJson;
import data.dto.TransactionHistoryDTO;
import view.CustomTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TransactionHistoryDialog extends JDialog {
    private List<TransactionHistoryDTO> transactionHistoryDTOList = ReadFileJson.readFileJSONForTransactionHistory();
    Object[][] transactionData = ReadFileJson.getTransactionData();
    public TransactionHistoryDialog(JFrame parent) {
        super(parent, "Lịch Sử Giao Dịch", true);
        setSize(900, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 248, 220));

        String[] columns = {"STT", "Tên", "SĐT", "Số Tiền", "Phương Thức Thanh Toán", "Ngày Thanh Toán"};


        CustomTable table = new CustomTable();  // Sử dụng CustomTable thay vì JTable
        DefaultTableModel model = new DefaultTableModel(transactionData, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);

        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.setRowHeight(35);
        table.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 18));
        table.getTableHeader().setReorderingAllowed(false);

        table.getTableHeader().setPreferredSize(new Dimension(100, 40));
        table.getTableHeader().setBackground(new Color(255, 224, 178)); // Tuỳ chọn thẩm mỹ
        table.getTableHeader().setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
    }
}
