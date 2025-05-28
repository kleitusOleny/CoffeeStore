package view.Manager;

import data.ReadFileJson;
import view.CustomButton;
import view.CustomPanel;
import view.CustomTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChangforInforEmployeeDialog extends JDialog {

    private CustomPanel mainPanel, personalInfoPanel, workInfoPanel;
    private CustomButton btnCapNhat, btnXoa;
    private JLabel nameLabel, idLabel, phoneLabel, cccdLabel, addressLabel, dobLabel,
            timekeepingStatus, startDateLabel, shiftLabel, baseSalaryLabel;
    private JSeparator jSeparator1;
    private CustomTextField nameField, idField, phoneField, cccdField,
            addressField, dobField, startDateField, shiftField, baseSalaryField;
    private DefaultTableModel model;

    public ChangforInforEmployeeDialog(JFrame parent, boolean modal, DefaultTableModel model) {
        super(parent, modal);
        this.model = model;
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        mainPanel = new CustomPanel();
        mainPanel.setDrawShadow(true);
        mainPanel.setBorderRadius(25);
        mainPanel.setBackgroundColor(new Color(255, 245, 204));
        mainPanel.setBorderColor(new Color(200, 170, 120));
        mainPanel.setBorderThickness(3);

        nameLabel = new JLabel("Tên NV");
        idLabel = new JLabel("Mã NV");
        phoneLabel = new JLabel("SĐT");
        cccdLabel = new JLabel("Số CCCD/CMND");
        addressLabel = new JLabel("Địa chỉ");
        dobLabel = new JLabel("Ngày sinh");

        nameField = new CustomTextField(20);
        idField = new CustomTextField(20);
        phoneField = new CustomTextField(20);
        cccdField = new CustomTextField(20);
        addressField = new CustomTextField(20);
        dobField = new CustomTextField(20);

        styleCustomField(nameField);
        styleCustomField(idField);
        styleCustomField(phoneField);
        styleCustomField(cccdField);
        styleCustomField(addressField);
        styleCustomField(dobField);

        personalInfoPanel = new CustomPanel();
        personalInfoPanel.setDrawShadow(false);
        personalInfoPanel.setBorderRadius(30);
        personalInfoPanel.setBorderColor(new Color(180, 180, 180));

        GroupLayout layout2 = new GroupLayout(personalInfoPanel);
        personalInfoPanel.setLayout(layout2);
        layout2.setAutoCreateGaps(true);
        layout2.setAutoCreateContainerGaps(true);

        layout2.setHorizontalGroup(
                layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel)
                        .addComponent(nameField)
                        .addComponent(idLabel)
                        .addComponent(idField)
                        .addComponent(phoneLabel)
                        .addComponent(phoneField)
                        .addComponent(cccdLabel)
                        .addComponent(cccdField)
                        .addComponent(addressLabel)
                        .addComponent(addressField)
                        .addComponent(dobLabel)
                        .addComponent(dobField)
        );

        layout2.setVerticalGroup(
                layout2.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(idLabel)
                        .addComponent(idField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(phoneLabel)
                        .addComponent(phoneField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cccdLabel)
                        .addComponent(cccdField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(addressLabel)
                        .addComponent(addressField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dobLabel)
                        .addComponent(dobField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        );

        timekeepingStatus = new JLabel("Trạng thái chấm công");
        timekeepingStatus.setFont(timekeepingStatus.getFont().deriveFont(Font.BOLD, 16f));
        jSeparator1 = new JSeparator();

        startDateLabel = new JLabel("Ngày vào làm(dd/mm/yyyy)");
        shiftLabel = new JLabel("Ca làm");
        baseSalaryLabel = new JLabel("Lương Cơ Bản");

        startDateField = new CustomTextField(20);
        shiftField = new CustomTextField(20);
        baseSalaryField = new CustomTextField(20);

        styleCustomField(startDateField);
        styleCustomField(shiftField);
        styleCustomField(baseSalaryField);

        workInfoPanel = new CustomPanel();
        workInfoPanel.setDrawShadow(false);
        workInfoPanel.setBorderRadius(30);
        workInfoPanel.setBorderColor(new Color(180, 180, 180));

        GroupLayout layout3 = new GroupLayout(workInfoPanel);
        workInfoPanel.setLayout(layout3);
        layout3.setAutoCreateGaps(true);
        layout3.setAutoCreateContainerGaps(true);

        layout3.setHorizontalGroup(
                layout3.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(timekeepingStatus)
                        .addComponent(jSeparator1)
                        .addComponent(startDateLabel)
                        .addComponent(startDateField)
                        .addComponent(shiftLabel)
                        .addComponent(shiftField)
                        .addComponent(baseSalaryLabel)
                        .addComponent(baseSalaryField)
        );

        layout3.setVerticalGroup(
                layout3.createSequentialGroup()
                        .addComponent(timekeepingStatus)
                        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                        .addComponent(startDateLabel)
                        .addComponent(startDateField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(shiftLabel)
                        .addComponent(shiftField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(baseSalaryLabel)
                        .addComponent(baseSalaryField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        );

        // Nút chức năng
        btnCapNhat = new CustomButton("Cập Nhật");
        btnCapNhat.setBackgroundColor(new Color(166, 123, 91));
        btnCapNhat.setForeground(Color.WHITE);
        btnCapNhat.setFont(new Font("Roboto", Font.BOLD, 16));
        btnCapNhat.setBorderRadius(20);
        btnCapNhat.addActionListener(this::updateEmployeeInTable);

        btnXoa = new CustomButton("Xoá");
        btnXoa.setBackgroundColor(Color.red);
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFont(new Font("Roboto", Font.BOLD, 16));
        btnXoa.setBorderRadius(20);
        btnXoa.addActionListener(this::btnXoaActionPerformed);

        GroupLayout layout1 = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout1);
        layout1.setAutoCreateGaps(true);
        layout1.setAutoCreateContainerGaps(true);

        layout1.setHorizontalGroup(
                layout1.createSequentialGroup()
                        .addComponent(personalInfoPanel)
                        .addGap(50)
                        .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(workInfoPanel)
                                .addGroup(layout1.createSequentialGroup()
                                        .addComponent(btnCapNhat, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                        .addGap(20)
                                        .addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
        );

        layout1.setVerticalGroup(
                layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(personalInfoPanel)
                        .addGroup(layout1.createSequentialGroup()
                                .addComponent(workInfoPanel)
                                .addGap(20)
                                .addGap(10)
                                .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCapNhat, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel)
        );

        pack();
    }

    private void updateEmployeeInTable(ActionEvent actionEvent) {
        String idEmp = idField.getText();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).toString().equals(idEmp)) {
                model.setValueAt(nameField.getText(), i, 0);
                model.setValueAt(phoneField.getText(), i, 2);
                model.setValueAt(dobField.getText(), i, 3);
                model.setValueAt(baseSalaryField.getText(), i, 4);
                break;
            }
        }
        dispose();

    }


    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá nhân viên này?", "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String idEmp = idField.getText();
            ReadFileJson.deleteEmployee(idEmp); // Xoá trong file

            // Xoá trong bảng
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 1).toString().equals(idEmp)) {
                    model.removeRow(i);
                    break;
                }
            }

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

    public void setData(String ten, String ma, String sdt, String cccd, String diaChi, String ngaySinh, String ngayVaoLam, String caLam, String luong) {
        nameField.setText(ten);
        idField.setText(ma);
        phoneField.setText(sdt);
        dobField.setText(ngaySinh);
        baseSalaryField.setText(luong);
        cccdField.setText(cccd);
        addressField.setText(diaChi);
        startDateField.setText(ngayVaoLam);
        shiftField.setText(caLam);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChangforInforEmployeeDialog dialog = new ChangforInforEmployeeDialog(null, true, null);
            dialog.setVisible(true);
        });
    }
    
    
}
