package view.Manager;

import data.ReadFileJson;
import view.CustomButton;
import view.CustomPanel;
import view.CustomTextField;

import javax.swing.*;
import java.awt.*;

public class ChangforInforEmployeeDialog extends JDialog {

    private CustomPanel jPanel1, jPanel2, jPanel3;
    private CustomButton btnTinhLuong, btnXoa;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6,
            jLabel7, jLabel8, jLabel9, jLabel10, jLabelTongLuong;
    private JSeparator jSeparator1;
    private CustomTextField jTextField1, jTextField2, jTextField3, jTextField4,
            jTextField5, jTextField6, jTextField7, jTextField8, jTextField9;

    public ChangforInforEmployeeDialog(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        jPanel1 = new CustomPanel();
        jPanel1.setDrawShadow(true);
        jPanel1.setBorderRadius(25);
        jPanel1.setBackgroundColor(new Color(255, 245, 204));
        jPanel1.setBorderColor(new Color(200, 170, 120));
        jPanel1.setBorderThickness(3);

        jLabel1 = new JLabel("Tên NV");
        jLabel2 = new JLabel("Mã NV");
        jLabel3 = new JLabel("SĐT");
        jLabel4 = new JLabel("Số CCCD/CMND");
        jLabel5 = new JLabel("Địa chỉ");
        jLabel6 = new JLabel("Ngày sinh");

        jTextField1 = new CustomTextField(20);
        jTextField2 = new CustomTextField(20);
        jTextField3 = new CustomTextField(20);
        jTextField4 = new CustomTextField(20);
        jTextField5 = new CustomTextField(20);
        jTextField6 = new CustomTextField(20);

        styleCustomField(jTextField1);
        styleCustomField(jTextField2);
        styleCustomField(jTextField3);
        styleCustomField(jTextField4);
        styleCustomField(jTextField5);
        styleCustomField(jTextField6);

        jPanel2 = new CustomPanel();
        jPanel2.setDrawShadow(false);
        jPanel2.setBorderRadius(30);
        jPanel2.setBorderColor(new Color(180, 180, 180));

        GroupLayout layout2 = new GroupLayout(jPanel2);
        jPanel2.setLayout(layout2);
        layout2.setAutoCreateGaps(true);
        layout2.setAutoCreateContainerGaps(true);

        layout2.setHorizontalGroup(
                layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1)
                        .addComponent(jLabel2)
                        .addComponent(jTextField2)
                        .addComponent(jLabel3)
                        .addComponent(jTextField3)
                        .addComponent(jLabel4)
                        .addComponent(jTextField4)
                        .addComponent(jLabel5)
                        .addComponent(jTextField5)
                        .addComponent(jLabel6)
                        .addComponent(jTextField6)
        );

        layout2.setVerticalGroup(
                layout2.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        );

        jLabel7 = new JLabel("Trạng thái chấm công");
        jLabel7.setFont(jLabel7.getFont().deriveFont(Font.BOLD, 16f));
        jSeparator1 = new JSeparator();

        jLabel8 = new JLabel("Ngày vào làm(dd/mm/yyyy)");
        jLabel9 = new JLabel("Ca làm");
        jLabel10 = new JLabel("Lương Cơ Bản");

        jTextField7 = new CustomTextField(20);
        jTextField8 = new CustomTextField(20);
        jTextField9 = new CustomTextField(20);

        styleCustomField(jTextField7);
        styleCustomField(jTextField8);
        styleCustomField(jTextField9);

        jPanel3 = new CustomPanel();
        jPanel3.setDrawShadow(false);
        jPanel3.setBorderRadius(30);
        jPanel3.setBorderColor(new Color(180, 180, 180));

        GroupLayout layout3 = new GroupLayout(jPanel3);
        jPanel3.setLayout(layout3);
        layout3.setAutoCreateGaps(true);
        layout3.setAutoCreateContainerGaps(true);

        layout3.setHorizontalGroup(
                layout3.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addComponent(jSeparator1)
                        .addComponent(jLabel8)
                        .addComponent(jTextField7)
                        .addComponent(jLabel9)
                        .addComponent(jTextField8)
                        .addComponent(jLabel10)
                        .addComponent(jTextField9)
        );

        layout3.setVerticalGroup(
                layout3.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jTextField8, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jTextField9, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        );

        // Thêm JLabel tổng lương
        jLabelTongLuong = new JLabel("Tổng Lương: ...");
        jLabelTongLuong.setFont(new Font("Roboto", Font.BOLD, 18));
        jLabelTongLuong.setForeground(new Color(166, 123, 91));

        // Nút chức năng
        btnTinhLuong = new CustomButton("Tính lương");
        btnTinhLuong.setBackgroundColor(new Color(166, 123, 91));
        btnTinhLuong.setForeground(Color.WHITE);
        btnTinhLuong.setFont(new Font("Roboto", Font.BOLD, 16));
        btnTinhLuong.setBorderRadius(20);
        btnTinhLuong.addActionListener(this::btnTinhLuongActionPerformed);

        btnXoa = new CustomButton("Xoá");
        btnXoa.setBackgroundColor(Color.red);
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFont(new Font("Roboto", Font.BOLD, 16));
        btnXoa.setBorderRadius(20);
        btnXoa.addActionListener(this::btnXoaActionPerformed);

        GroupLayout layout1 = new GroupLayout(jPanel1);
        jPanel1.setLayout(layout1);
        layout1.setAutoCreateGaps(true);
        layout1.setAutoCreateContainerGaps(true);

        layout1.setHorizontalGroup(
                layout1.createSequentialGroup()
                        .addComponent(jPanel2)
                        .addGap(50)
                        .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel3)
                                .addComponent(jLabelTongLuong)
                                .addGroup(layout1.createSequentialGroup()
                                        .addComponent(btnTinhLuong, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                        .addGap(20)
                                        .addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
        );

        layout1.setVerticalGroup(
                layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2)
                        .addGroup(layout1.createSequentialGroup()
                                .addComponent(jPanel3)
                                .addGap(20)
                                .addComponent(jLabelTongLuong)
                                .addGap(10)
                                .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnTinhLuong, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1)
        );

        pack();
    }

    private void btnTinhLuongActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            double luongCoBan = Double.parseDouble(jTextField9.getText().trim());
            jLabelTongLuong.setText("Tổng Lương: " + String.format("%.0f", luongCoBan) + " VND");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lương cơ bản không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá nhân viên này?", "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // TODO: Xử lý xoá nhân viên
            String idEmp = jTextField2.getText();
            ReadFileJson.deleteEmployee(idEmp);
            JOptionPane.showMessageDialog(this, "Xoá nhân viên thành công!");
            dispose();
        }
    }

    private void styleCustomField(CustomTextField tf) {
        tf.setPreferredSize(new Dimension(300, 30));
        tf.setFont(new Font("Roboto", Font.BOLD, 14));
        tf.setBorderRadius(20);
        tf.setForeground(new Color(166, 123, 91));
    }

    public void setData(String ten, String ma, String sdt, String ngaySinh, String luong) {
        jTextField1.setText(ten);
        jTextField2.setText(ma);
        jTextField3.setText(sdt);
        jTextField6.setText(ngaySinh);
        jTextField9.setText(luong);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChangforInforEmployeeDialog dialog = new ChangforInforEmployeeDialog(null, true);
            dialog.setVisible(true);
        });
    }
}
