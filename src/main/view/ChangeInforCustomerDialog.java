package view;

import data.FormatClient;
import data.ReadFileJson;

import javax.swing.*;
import java.awt.*;

public class ChangeInforCustomerDialog extends JDialog {

    private JTextField tenField;
    private JTextField sdtField;
    private JTextField diemField;

    private boolean confirmed = false;
    private boolean deleted = false; // Cờ để kiểm tra nếu người dùng chọn xóa

    public ChangeInforCustomerDialog(Frame owner) {
        super(owner, "Thông tin khách hàng", true);
        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(owner);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông tin khách hàng");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 16f));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Tên khách hàng
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Tên khách hàng"), gbc);

        tenField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(tenField, gbc);

        // Số điện thoại
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Số điện thoại"), gbc);

        sdtField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(sdtField, gbc);

        // Điểm tích lũy
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Điểm tích lũy"), gbc);

        diemField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(diemField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Nút lưu, xóa, hủy
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        CustomButton luuButton = new CustomButton("Lưu thay đổi");
        luuButton.setBackgroundColor(new Color(166, 123, 91));
        luuButton.setTextColor(Color.WHITE);
        luuButton.setBorderRadius(15);

        CustomButton xoaButton = new CustomButton("Xóa");
        xoaButton.setBackgroundColor(new Color(255, 153, 0)); // màu cam
        xoaButton.setTextColor(Color.WHITE);
        xoaButton.setBorderRadius(15);

        CustomButton huyButton = new CustomButton("Hủy");
        huyButton.setBackgroundColor(new Color(200, 0, 0));
        huyButton.setTextColor(Color.WHITE);
        huyButton.setBorderRadius(15);

        buttonPanel.add(luuButton);
        buttonPanel.add(xoaButton);
        buttonPanel.add(huyButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Sự kiện nút Lưu
        luuButton.addActionListener(e -> {
            if (tenField.getText().trim().isEmpty() || sdtField.getText().trim().isEmpty() || diemField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                String ten = tenField.getText();
                String sdt = sdtField.getText();
                String diem = diemField.getText();
                ReadFileJson.saveChangedClientInformationAndOverwriteItOnClientJSON(ten, sdt, diem);
            }
            confirmed = true;
            deleted = false;
            setVisible(false);
        });

        // Sự kiện nút Xóa
        xoaButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa khách hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleted = true;
                confirmed = false;
                setVisible(false);
            }
        });

        // Sự kiện nút Hủy
        huyButton.addActionListener(e -> {
            confirmed = false;
            deleted = false;
            setVisible(false);
        });
    }

    public void setKhachHang(String ten, String sdt, String diem) {
        tenField.setText(ten);
        sdtField.setText(sdt);
        diemField.setText(diem);
    }

    public String getTenKhach() {
        return tenField.getText().trim();
    }

    public String getSDT() {
        return sdtField.getText().trim();
    }

    public String getDiem() {
        return diemField.getText().trim();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
