package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class QuanLyNhanVien_dsnv extends JPanel {

    private JButton jbutThemNV;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable EmsTable;
    private JTextField jtextTimKiem;

    public QuanLyNhanVien_dsnv() {
        initComponents();

        // Tùy chỉnh tiêu đề bảng
        JTableHeader header = EmsTable.getTableHeader();
        header.setDefaultRenderer(new CustomHeaderRenderer());

        // Tạo tùy chỉnh cho các cột dựa vào class CustomTableCellRenderer
        for (int i = 0; i < EmsTable.getColumnCount(); i++) {
            EmsTable.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
        }
    }

    private void initComponents() {
        setBackground(new Color(254, 216, 177));

        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        EmsTable = new JTable();
        jbutThemNV = new JButton();
        jtextTimKiem = new JTextField();

        jPanel1.setBackground(new Color(254, 216, 177));

        EmsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null }
                },
                new String[] { "Tên", "Mã NV", "SĐT", "Ngày Sinh", "Lương" }));

        jbutThemNV.setBackground(new Color(166, 123, 91));
        jbutThemNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbutThemNV.setText("Thêm nhân viên");

        jbutThemNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QLNV_ThemNhanVien themNhanVienFrame = new QLNV_ThemNhanVien();
                themNhanVienFrame.setVisible(true);
                // Không gọi dispose() vì đây là JPanel
            }
        });

        jtextTimKiem.setBackground(new Color(166, 123, 91));
        jtextTimKiem.setFont(new java.awt.Font("Segoe UI Light", 2, 12)); // NOI18N
        jtextTimKiem.setText("Tìm kiếm nhân viên.....");

        jScrollPane1.setViewportView(EmsTable);

        // Layout cho jPanel1
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jbutThemNV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtextTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jbutThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtextTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        // Layout cho JPanel chính (this)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
}
