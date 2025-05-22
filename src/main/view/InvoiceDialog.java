package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InvoiceDialog extends JDialog {

    public InvoiceDialog(JFrame parent) {
        super(parent, "Hóa đơn thanh toán", true);
        setSize(420, 580);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(255, 248, 220));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Logo cà phê
        JLabel iconLabel = new JLabel("\u2615", SwingConstants.CENTER);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setFont(new Font("Roboto", Font.PLAIN, 40));
        iconLabel.setForeground(new Color(166, 123, 91));
        mainPanel.add(iconLabel);

        // Tiêu đề
        JLabel titleLabel = new JLabel("COFFEE THỐNG NHẤT", SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setForeground(new Color(166, 123, 91));
        mainPanel.add(titleLabel);

        JLabel subTitleLabel = new JLabel("HÓA ĐƠN THANH TOÁN", SwingConstants.CENTER);
        subTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subTitleLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        subTitleLabel.setBorder(new EmptyBorder(10, 0, 15, 0));
        mainPanel.add(subTitleLabel);

        // Panel thông tin
        mainPanel.add(createInfoRow("Khách hàng:", "Nguyễn Văn A"));
        mainPanel.add(createInfoRow("Trạng thái:", "VIP"));
        mainPanel.add(createInfoRow("Bàn:", "Bàn 2"));

        mainPanel.add(Box.createVerticalStrut(15));

        // Bảng món
        String[] headers = {"Tên Món", "SL", "Đơn Giá", "Topping"};
        Object[][] data = {
                {"Cà phê sữa", "1", "25.000đ", ""},
                {"Trà đào", "2", "20.000đ", "+ Đào (2) - 5.000đ"}
        };

        JTable table = new JTable(new DefaultTableModel(data, headers) {
            public boolean isCellEditable(int row, int col) { return false; }
        });

        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        table.setFont(new Font("Roboto", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(360, 100));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(scrollPane);

        mainPanel.add(Box.createVerticalStrut(10));

        // Tạm tính, giảm giá, thành tiền
        mainPanel.add(createInfoRow("Tạm tính:", "65.000đ"));
        mainPanel.add(createInfoRow("Giảm giá:", "50%"));

        JPanel totalPanel = createInfoRow("Thành tiền:", "75.000đ");
        JLabel label = (JLabel) totalPanel.getComponent(0);
        JLabel value = (JLabel) totalPanel.getComponent(1);
        label.setFont(new Font("Roboto", Font.BOLD, 14));
        value.setFont(new Font("Roboto", Font.BOLD, 14));
        mainPanel.add(totalPanel);

        mainPanel.add(Box.createVerticalStrut(15));

        // Cảm ơn
        JLabel thanksLabel = new JLabel("<html><i>Cảm ơn quý khách và hẹn gặp lại!</i></html>", SwingConstants.CENTER);
        thanksLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        thanksLabel.setFont(new Font("Roboto", Font.ITALIC, 14));
        thanksLabel.setForeground(new Color(100, 100, 100));
        mainPanel.add(thanksLabel);

        mainPanel.add(Box.createVerticalStrut(10));

        // Nút OK
        JButton okBtn = new JButton("OK");
        okBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        okBtn.setBackground(new Color(166, 123, 91));
        okBtn.setForeground(Color.WHITE);
        okBtn.setFocusPainted(false);
        okBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        okBtn.setPreferredSize(new Dimension(100, 35));
        okBtn.addActionListener(e -> dispose());
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(okBtn);

        add(mainPanel);
    }

    private JPanel createInfoRow(String label, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.setOpaque(false);

        JLabel left = new JLabel(label);
        JLabel right = new JLabel(value);
        panel.add(left, BorderLayout.WEST);
        panel.add(right, BorderLayout.EAST);

        return panel;
    }
}
