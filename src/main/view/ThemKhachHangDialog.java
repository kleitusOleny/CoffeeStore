package view;

import javax.swing.*;
import java.awt.*;

public class ThemKhachHangDialog extends JDialog {
    private JTextField tenField;
    private JTextField sdtField;
    private boolean isConfirmed = false;

    public ThemKhachHangDialog(JFrame parent) {
        super(parent, "Thông tin khách hàng", true);
        setLayout(new GridBagLayout());
        setSize(350, 250);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Thông tin khách hàng", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Tên
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel("Tên khách hàng"), gbc);
        tenField = new JTextField();
        gbc.gridx = 1;
        add(tenField, gbc);

        // SĐT
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Số điện thoại"), gbc);
        sdtField = new JTextField();
        gbc.gridx = 1;
        add(sdtField, gbc);

        // Nút Thêm
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        CustomButton btnThem = new CustomButton("Thêm");
        btnThem.setBackgroundColor(new Color(255, 180, 100));
        btnThem.setTextColor(Color.BLACK);
        btnThem.setBorderRadius(30);
        btnThem.setPreferredSize(new Dimension(100, 40));
        btnThem.addActionListener(e -> {
            isConfirmed = true;
            dispose();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(btnThem);
        add(buttonPanel, gbc);
    }

    public String getTenKhach() {
        return tenField.getText().trim();
    }

    public String getSDT() {
        return sdtField.getText().trim();
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }
}
