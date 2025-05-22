package view;

import javax.swing.*;
import java.awt.*;

public class ChangeInforCustomerDialog extends JDialog {

    private CustomTextField tenField;
    private CustomTextField sdtField;
    private CustomTextField diemField;

    private boolean confirmed = false;
    private boolean deleted = false;

    public ChangeInforCustomerDialog(Frame owner) {
        super(owner, "Thông tin khách hàng", true);
        setSize(420, 320);
        setLocationRelativeTo(owner);
        getContentPane().setBackground(new Color(255, 245, 204));
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông tin khách hàng", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(255, 245, 204));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        // Tên khách hàng
        formPanel.add(createFieldPanel("Tên khách hàng", tenField = new CustomTextField(20)));

        // Số điện thoại
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(createFieldPanel("Số điện thoại", sdtField = new CustomTextField(20)));

        // Điểm tích lũy
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(createFieldPanel("Điểm tích lũy", diemField = new CustomTextField(20)));

        add(formPanel, BorderLayout.CENTER);

        // Nút điều khiển
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        CustomButton luuButton = new CustomButton("Lưu thay đổi");
        luuButton.setBackgroundColor(new Color(166, 123, 91));
        luuButton.setTextColor(Color.WHITE);
        luuButton.setBorderRadius(15);

        CustomButton xoaButton = new CustomButton("Xóa");
        xoaButton.setBackgroundColor(new Color(255, 153, 0));
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

        // Sự kiện nút
        luuButton.addActionListener(e -> {
            if (tenField.getText().trim().isEmpty() || sdtField.getText().trim().isEmpty() || diemField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            confirmed = true;
            deleted = false;
            setVisible(false);
        });

        xoaButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc muốn xóa khách hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleted = true;
                confirmed = false;
                setVisible(false);
            }
        });

        huyButton.addActionListener(e -> {
            confirmed = false;
            deleted = false;
            setVisible(false);
        });
    }

    private JPanel createFieldPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 245, 204));
        JLabel label = new JLabel(labelText);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        textField.setPreferredSize(new Dimension(300, 30));
        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
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
