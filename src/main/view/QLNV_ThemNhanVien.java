package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class QLNV_ThemNhanVien extends JDialog {

	private CustomPanel jPanel1, jPanel2, jPanel3;
	private CustomButton btnThem;
	private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6,
			jLabel7, jLabel8, jLabel9, jLabel10;
	private JSeparator jSeparator1;
	private CustomTextField jTextField1, jTextField2, jTextField3, jTextField4,
			jTextField5, jTextField6, jTextField7, jTextField8, jTextField9;
	private DefaultTableModel tableModel;

	public QLNV_ThemNhanVien(JFrame parent, boolean modal, DefaultTableModel model) {
		super(parent, modal);
		this.tableModel = model;
		initComponents();
		setLocationRelativeTo(parent); // căn giữa dialog theo frame cha
	}

	private void initComponents() {
		// Panel chính với hiệu ứng shadow, gradient
		jPanel1 = new CustomPanel();
		jPanel1.setDrawShadow(true);
		jPanel1.setBorderRadius(25);
		jPanel1.setBackgroundColor(new Color(255, 245, 204));
		jPanel1.setBorderColor(new Color(200, 170, 120));
		jPanel1.setBorderThickness(3);
		// Nếu có hover effect trên panel chính, tắt nếu muốn:
		// jPanel1.setDrawHover(false);

		// Labels và CustomTextField phần Thông tin nhân viên
		jLabel1 = new JLabel("Tên NV");
		jLabel2 = new JLabel("Mã NV");
		jLabel3 = new JLabel("SĐT");
		jLabel4 = new JLabel("Số CCCD/CMND");
		jLabel5 = new JLabel("Địa chỉ");
		jLabel6 = new JLabel("Ngày sinh(dd/mm/yyyy)");

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

		// Panel chứa phần Thông tin nhân viên (jPanel2)
		jPanel2 = new CustomPanel();
		jPanel2.setDrawShadow(false);
		jPanel2.setBorderRadius(30);
		jPanel2.setBorderColor(new Color(180, 180, 180));
//		jPanel2.setBorderThickness(1);
		// Tắt hover nếu có
		// jPanel2.setDrawHover(false);

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

		// Phần Trạng thái chấm công
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
//		jPanel3.setBorderThickness(1);
		// Tắt hover nếu có
		// jPanel3.setDrawHover(false);

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

		// Nút Thêm
		btnThem = new CustomButton("Thêm");
		btnThem.setBackgroundColor(new Color(166, 123, 91));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Roboto", Font.BOLD, 16));
		btnThem.setBorderRadius(20);
		btnThem.addActionListener(evt -> btnThemActionPerformed(evt));

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
										.addGap(100, 100, 100)
										.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
		);

		layout1.setVerticalGroup(
				layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jPanel2)
						.addGroup(layout1.createSequentialGroup()
								.addComponent(jPanel3)
								.addGap(30)
								.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
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

	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {
		String ten = jTextField1.getText();
		String ma = jTextField2.getText();
		String sdt = jTextField3.getText();
		String ngaySinh = jTextField6.getText();
		String luong = jTextField9.getText();

		// Thêm dòng vào model
		tableModel.addRow(new Object[]{ten, ma, sdt, ngaySinh, luong});

		JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
		dispose();
	}

	private void styleCustomField(CustomTextField tf) {
		tf.setPreferredSize(new Dimension(300, 30));
		tf.setFont(new Font("Roboto", Font.BOLD, 14));
		tf.setBorderRadius(20);
		tf.setForeground(new Color(166, 123, 91));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			QLNV_ThemNhanVien dialog = new QLNV_ThemNhanVien(null, true,null);
			dialog.setVisible(true);
		});
	}
}
