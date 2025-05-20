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

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(255, 248, 220));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Logo icon trên cùng (cà phê)
        JLabel iconLabel = new JLabel("\u2615"); // Unicode cà phê
        iconLabel.setFont(new Font("Roboto", Font.PLAIN, 40));
        iconLabel.setForeground(new Color(166, 123, 91));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(iconLabel, gbc);

        // Tiêu đề lớn
        JLabel titleLabel = new JLabel("COFFEE THỐNG NHẤT");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setForeground(new Color(166, 123, 91));
        gbc.gridy++;
        mainPanel.add(titleLabel, gbc);

        JLabel subTitleLabel = new JLabel("HÓA ĐƠN THANH TOÁN");
        subTitleLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        subTitleLabel.setForeground(Color.BLACK);
        gbc.gridy++;
        gbc.insets = new Insets(15, 8, 15, 8);
        mainPanel.add(subTitleLabel, gbc);

        // Reset insets cho phần dưới
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;

        // Thông tin khách hàng, trạng thái, bàn
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Khách hàng:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JLabel("Nguyễn Văn A"), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Trạng thái:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JLabel("VIP"), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Bàn:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JLabel("Bàn 2"), gbc);

        // Dòng trống
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        mainPanel.add(Box.createVerticalStrut(15), gbc);

        // Bảng món
        String[] headers = {"Tên Món", "SL", "Đơn Giá", "Topping"};
        Object[][] data = {
                {"Cà phê sữa", "1", "25.000đ", ""},
                {"Trà đào", "2", "20.000đ", "+ Đào (2) - 5.000đ"}
        };

        DefaultTableModel model = new DefaultTableModel(data, headers) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setFont(new Font("Roboto", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(140);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(360, 100));

        gbc.gridy++;
        mainPanel.add(tableScroll, gbc);

        // Dòng trống dưới bảng
        gbc.gridy++;
        mainPanel.add(Box.createVerticalStrut(10), gbc);

        // Tạm tính, giảm giá, thành tiền
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Tạm tính:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JLabel("65.000đ"), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Giảm giá:"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JLabel("50%"), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel totalLabel = new JLabel("Thành tiền:");
        totalLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        mainPanel.add(totalLabel, gbc);
        gbc.gridx = 1;
        JLabel totalValueLabel = new JLabel("75.000đ");
        totalValueLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        mainPanel.add(totalValueLabel, gbc);

        // Dòng trống
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        mainPanel.add(Box.createVerticalStrut(15), gbc);

        // Lời cảm ơn
        gbc.gridy++;
        JLabel thanksLabel = new JLabel("<html><i>Cảm ơn quý khách và hẹn gặp lại!</i></html>");
        thanksLabel.setFont(new Font("Roboto", Font.ITALIC, 14));
        thanksLabel.setForeground(new Color(100, 100, 100));
        thanksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(thanksLabel, gbc);

        // Dòng trống
        gbc.gridy++;
        mainPanel.add(Box.createVerticalStrut(10), gbc);

        // Nút OK
        gbc.gridy++;
        JButton okBtn = new JButton("OK");
        okBtn.setBackground(new Color(166, 123, 91));
        okBtn.setForeground(Color.WHITE);
        okBtn.setFocusPainted(false);
        okBtn.setFont(new Font("Roboto", Font.BOLD, 14));
        okBtn.setPreferredSize(new Dimension(100, 35));
        okBtn.addActionListener(e -> dispose());
        mainPanel.add(okBtn, gbc);

        add(mainPanel);
    }

}
