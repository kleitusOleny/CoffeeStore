package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.JTableHeader;




public class QuanLyNhanVien_dsnv extends javax.swing.JFrame {

 
 
    public QuanLyNhanVien_dsnv() {
        initComponents();
        setLocationRelativeTo(null); // căn giữa màn hình
        setVisible(true);
    	// Tùy chỉnh tiêu đề bảng
		JTableHeader header =EmsTable .getTableHeader();
		header.setDefaultRenderer(new CustomHeaderRenderer());

		// Tạo tùy chỉnh cho các cột dựa vào class CustomTableCellRenderer
		for (int i = 0; i < EmsTable.getColumnCount(); i++) {
			EmsTable.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
		}
    }
                   
    private void initComponents() {
    
    	setBackground(new Color(254, 216, 177));
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        EmsTable = new javax.swing.JTable();
        jbutThemNV = new javax.swing.JButton();
        jtextTimKiem = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(254, 216, 177));

    
        EmsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                    "Tên", "Mã NV", "SĐT", "Ngày Sinh" , "Lương"
                }
        ));
        jbutThemNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QLNV_ThemNhanVien themNhanVienFrame = new QLNV_ThemNhanVien();
                themNhanVienFrame.setVisible(true);
                dispose(); 
            }
        });

     
        jScrollPane1.setViewportView(EmsTable);

        jbutThemNV.setBackground(new Color(166, 123, 91));
        jbutThemNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbutThemNV.setText("Thêm nhân viên");

        jtextTimKiem.setBackground(new Color(166, 123, 91));
        jtextTimKiem.setFont(new java.awt.Font("Segoe UI Light", 2, 12)); // NOI18N
        jtextTimKiem.setText("Tìm kiếm nhân viên.....");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    
    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien_dsnv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien_dsnv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien_dsnv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien_dsnv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien_dsnv().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jbutThemNV;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable EmsTable;
    private javax.swing.JTextField jtextTimKiem;
    // End of variables declaration                   
}
