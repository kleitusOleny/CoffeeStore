package view;

import javax.swing.*;

public class QLNV_ChinhSuaNhanVien extends JDialog {

    public QLNV_ChinhSuaNhanVien(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent); // căn giữa dialog theo frame cha
    }

    private void initComponents() {
        // Khởi tạo components
        jPanel1 = new JPanel();
        jPanel1.setBackground(new java.awt.Color(254, 216, 177));

        // Labels và textfields phần Thông tin nhân viên
        jLabel1 = new JLabel("Tên NV");
        jLabel2 = new JLabel("Mã NV");
        jLabel3 = new JLabel("SĐT");
        jLabel4 = new JLabel("Số CCCD/CMND");
        jLabel5 = new JLabel("Địa chỉ");
        jLabel6 = new JLabel("Ngày sinh");

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();

        // Thiết lập kích thước đồng đều
        int txtWidth = 300, txtHeight = 30;
        jTextField1.setPreferredSize(new java.awt.Dimension(txtWidth, txtHeight));
        jTextField2.setPreferredSize(new java.awt.Dimension(txtWidth, txtHeight));
        jTextField3.setPreferredSize(new java.awt.Dimension(txtWidth, txtHeight));
        jTextField4.setPreferredSize(new java.awt.Dimension(txtWidth, txtHeight));
        jTextField5.setPreferredSize(new java.awt.Dimension(txtWidth, txtHeight));
        jTextField6.setPreferredSize(new java.awt.Dimension(txtWidth, txtHeight));

        // Panel chứa thông tin nhân viên
        jPanel2 = new JPanel();
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
                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, txtHeight, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, txtHeight, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, txtHeight, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, txtHeight, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, txtHeight, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, txtHeight, GroupLayout.PREFERRED_SIZE)
        );

        // Phần Trạng thái chấm công
        jLabel7 = new JLabel("Trạng thái chấm công");
        jLabel7.setFont(jLabel7.getFont().deriveFont(java.awt.Font.BOLD, 16f));

        jSeparator1 = new JSeparator();

        jLabel8 = new JLabel("Ngày vào làm");
        jLabel9 = new JLabel("Ca làm");
        jLabel10 = new JLabel("Lương");

        jTextField7 = new JTextField();
        jTextField8 = new JTextField();
        jTextField9 = new JTextField();

        jTextField7.setPreferredSize(new java.awt.Dimension(txtWidth, txtHeight));
        jTextField8.setPreferredSize(new java.awt.Dimension(txtWidth, txtHeight));
        jTextField9.setPreferredSize(new java.awt.Dimension(txtWidth, txtHeight));

        jPanel3 = new JPanel();
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
                        .addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, txtHeight, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jTextField8, GroupLayout.PREFERRED_SIZE, txtHeight, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jTextField9, GroupLayout.PREFERRED_SIZE, txtHeight, GroupLayout.PREFERRED_SIZE)
        );

        // 2 Nút Tính lương và Xoá
        btnTinhLuong = new JButton("Tính lương");
        btnTinhLuong.setFont(btnTinhLuong.getFont().deriveFont(java.awt.Font.BOLD, 14f));
        btnTinhLuong.setBackground(new java.awt.Color(217, 217, 217));
        btnTinhLuong.addActionListener(evt -> btnTinhLuongActionPerformed(evt));

        btnXoa = new JButton("Xoá");
        btnXoa.setFont(btnXoa.getFont().deriveFont(java.awt.Font.BOLD, 14f));
        btnXoa.setBackground(new java.awt.Color(217, 217, 217));
        btnXoa.addActionListener(evt -> btnXoaActionPerformed(evt));

        // Layout tổng thể panel 1
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
                                        .addComponent(btnTinhLuong, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                        .addGap(30)
                                        .addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
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

        // Đặt layout cho dialog
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
        // TODO: Xử lý tính lương ở đây
        JOptionPane.showMessageDialog(this, "Tính lương nhân viên.");
    }

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO: Xử lý xoá nhân viên ở đây
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Nhân viên đã được xoá.");
            dispose();
        }
    }

    // Biến thành phần
    private JButton btnTinhLuong, btnXoa;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6,
            jLabel7, jLabel8, jLabel9, jLabel10;
    private JPanel jPanel1, jPanel2, jPanel3;
    private JSeparator jSeparator1;
    private JTextField jTextField1, jTextField2, jTextField3, jTextField4,
            jTextField5, jTextField6, jTextField7, jTextField8, jTextField9;

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        QLNV_ChinhSuaNhanVien dialog = new QLNV_ChinhSuaNhanVien(frame, true);
        dialog.setTitle("Chỉnh sửa nhân viên");
        dialog.setVisible(true);
    }
}
