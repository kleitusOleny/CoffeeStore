package view;

import javax.swing.*;
import java.awt.*;

public class AddCustomerDialog extends JDialog {
    private CustomTextField tenField;
    private CustomTextField sdtField;
    private boolean isConfirmed = false;

    public AddCustomerDialog(JFrame parent) {
        super(parent, "Thông tin khách hàng", true);
        setSize(420, 250);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(new Color(255, 245, 204));
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông tin khách hàng", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Nội dung nhập liệu
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(255, 245, 204));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Tên khách hàng
        JPanel tenPanel = new JPanel(new BorderLayout());
        tenPanel.setBackground(new Color(255, 245, 204));
        JLabel tenLabel = new JLabel("Tên khách hàng");
        tenField = new CustomTextField(20);
        tenField.setPreferredSize(new Dimension(300, 30));
        tenPanel.add(tenLabel, BorderLayout.NORTH);
        tenPanel.add(tenField, BorderLayout.CENTER);
        formPanel.add(tenPanel);
        formPanel.add(Box.createVerticalStrut(15));

        // Số điện thoại
        JPanel sdtPanel = new JPanel(new BorderLayout());
        sdtPanel.setBackground(new Color(255, 245, 204));
        JLabel sdtLabel = new JLabel("Số điện thoại");
        sdtField = new CustomTextField(20);
        sdtField.setPreferredSize(new Dimension(300, 30));
        sdtPanel.add(sdtLabel, BorderLayout.NORTH);
        sdtPanel.add(sdtField, BorderLayout.CENTER);
        formPanel.add(sdtPanel);

        add(formPanel, BorderLayout.CENTER);

        // Nút Thêm
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
        buttonPanel.setBackground(new Color(255, 245, 204));
        buttonPanel.add(btnThem);
        add(buttonPanel, BorderLayout.SOUTH);
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
