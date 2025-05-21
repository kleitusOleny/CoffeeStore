package view;

import javax.swing.*;
import java.awt.*;

public class QLNV_ChinhSuaNhanVien extends JDialog {

    private CustomPanel jPanel1, jPanel2, jPanel3;
    private CustomButton btnTinhLuong, btnXoa;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6,
            jLabel7, jLabel8, jLabel9, jLabel10;
    private JSeparator jSeparator1;
    private CustomTextField jTextField1, jTextField2, jTextField3, jTextField4,
            jTextField5, jTextField6, jTextField7, jTextField8, jTextField9;

    public QLNV_ChinhSuaNhanVien(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent); // căn giữa dialog theo frame cha
    }

    private void initComponents() {
        jPanel1 = new CustomPanel();
        jPanel1.setDrawShadow(true);
        jPanel1.setBorderRadius(25);
        jPanel1.setBackgroundColor(new Color(255, 245, 204));
        jPanel1.setBorderColor(new Color(200, 170, 120));
        jPanel1.setBorderThickness(3);

        // Label & textfield thông tin nhân viên
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

        jLabel8 = new JLabel("Ngày vào làm");
        jLabel9 = new JLabel("Ca làm");
        jLabel10 = new JLabel("Lương");

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

        // Các nút chức năng
        btnTinhLuong = new CustomButton("Tính lương");
        btnTinhLuong.setBackgroundColor(new Color(166, 123, 91));
        btnTinhLuong.setForeground(Color.WHITE);
        btnTinhLuong.setFont(new Font("Roboto", Font.BOLD, 16));
//        button.setFocusPainted(false);
        btnTinhLuong.setBorderRadius(20);
        btnTinhLuong.addActionListener(evt -> btnTinhLuongActionPerformed(evt));

        btnXoa = new CustomButton("Xoá");
        btnXoa.setBackgroundColor(Color.red);
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFont(new Font("Roboto", Font.BOLD, 16));
//        button.setFocusPainted(false);
        btnXoa.setBorderRadius(20);
        btnXoa.addActionListener(evt -> btnXoaActionPerformed(evt));

        // Layout tổng thể panel1
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
                                .addGap(30)
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
        // TODO: Xử lý tính lương cho nhân viên
        JOptionPane.showMessageDialog(this, "Tính lương thành công!");
    }

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá nhân viên này?", "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // TODO: Xử lý xoá nhân viên
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
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            QLNV_ChinhSuaNhanVien dialog = new QLNV_ChinhSuaNhanVien(null, true);
            dialog.setVisible(true);
        });
    }
}
