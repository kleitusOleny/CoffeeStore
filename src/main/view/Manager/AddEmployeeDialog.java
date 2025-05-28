package view.Manager;

import data.ReadFileJson;
import view.CustomButton;
import view.CustomPanel;
import view.CustomTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddEmployeeDialog extends JDialog {

	private CustomPanel dialogPanel, PersionalPanel, timeKeepingPanel;

	private CustomButton btnThem;
	private JLabel nameE, idE, phoneNumber, CCCD, Address, dateOfBirth,
			timeKeepingStatus, DateStart, workShift, Salary;
	private JSeparator jSeparator1;
	private CustomTextField nameField, idField, phoneNumberField, cccdField,
			addressField, dateOfBirthField, dateStartField, workShiftField, salaryField;
	private DefaultTableModel tableModel;
	private boolean isAdd = false;
	public AddEmployeeDialog(JFrame parent, boolean modal, DefaultTableModel model) {
		super(parent, modal);
		this.tableModel = model;
		initComponents();
		setLocationRelativeTo(null); // căn giữa dialog theo frame cha
	}

	private void initComponents() {
		// Panel chính với hiệu ứng shadow, gradient
		dialogPanel = new CustomPanel();
		dialogPanel.setDrawShadow(true);
		dialogPanel.setBorderRadius(25);
		dialogPanel.setBackgroundColor(new Color(255, 245, 204));
		dialogPanel.setBorderColor(new Color(200, 170, 120));
		dialogPanel.setBorderThickness(3);
		// Nếu có hover effect trên panel chính, tắt nếu muốn:
		// jPanel1.setDrawHover(false);

		// Labels và CustomTextField phần Thông tin nhân viên
		nameE = new JLabel("Tên NV");
		idE = new JLabel("Mã NV");
		phoneNumber = new JLabel("SĐT");
		CCCD = new JLabel("Số CCCD/CMND");
		Address = new JLabel("Địa chỉ");
		dateOfBirth = new JLabel("Ngày sinh(dd/mm/yyyy)");

		nameField = new CustomTextField(20);
		idField = new CustomTextField(20);
		phoneNumberField = new CustomTextField(20);
		cccdField = new CustomTextField(20);
		addressField = new CustomTextField(20);
		dateOfBirthField = new CustomTextField(20);

		styleCustomField(nameField);
		styleCustomField(idField);
		styleCustomField(phoneNumberField);
		styleCustomField(cccdField);
		styleCustomField(addressField);
		styleCustomField(dateOfBirthField);

		// Panel chứa phần Thông tin nhân viên (jPanel2)
		PersionalPanel = new CustomPanel();
		PersionalPanel.setDrawShadow(false);
		PersionalPanel.setBorderRadius(30);
		PersionalPanel.setBorderColor(new Color(180, 180, 180));
//		jPanel2.setBorderThickness(1);
		// Tắt hover nếu có
		// jPanel2.setDrawHover(false);

		GroupLayout layout2 = new GroupLayout(PersionalPanel);
		PersionalPanel.setLayout(layout2);
		layout2.setAutoCreateGaps(true);
		layout2.setAutoCreateContainerGaps(true);

		layout2.setHorizontalGroup(
				layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(nameE)
						.addComponent(nameField)
						.addComponent(idE)
						.addComponent(idField)
						.addComponent(phoneNumber)
						.addComponent(phoneNumberField)
						.addComponent(CCCD)
						.addComponent(cccdField)
						.addComponent(Address)
						.addComponent(addressField)
						.addComponent(dateOfBirth)
						.addComponent(dateOfBirthField)
		);

		layout2.setVerticalGroup(
				layout2.createSequentialGroup()
						.addComponent(nameE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(idE)
						.addComponent(idField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(phoneNumber)
						.addComponent(phoneNumberField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(CCCD)
						.addComponent(cccdField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(Address)
						.addComponent(addressField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateOfBirth)
						.addComponent(dateOfBirthField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
		);

		// Phần Trạng thái chấm công
		timeKeepingStatus = new JLabel("Trạng thái chấm công");
		timeKeepingStatus.setFont(timeKeepingStatus.getFont().deriveFont(Font.BOLD, 16f));

		jSeparator1 = new JSeparator();

		DateStart = new JLabel("Ngày vào làm(dd/mm/yyyy)");
		workShift = new JLabel("Ca làm");
		Salary = new JLabel("Lương Cơ Bản");

		dateStartField = new CustomTextField(20);
		workShiftField = new CustomTextField(20);
		salaryField = new CustomTextField(20);

		styleCustomField(dateStartField);
		styleCustomField(workShiftField);
		styleCustomField(salaryField);

		timeKeepingPanel = new CustomPanel();
		timeKeepingPanel.setDrawShadow(false);
		timeKeepingPanel.setBorderRadius(30);
		timeKeepingPanel.setBorderColor(new Color(180, 180, 180));
//		jPanel3.setBorderThickness(1);
		// Tắt hover nếu có
		// jPanel3.setDrawHover(false);

		GroupLayout layout3 = new GroupLayout(timeKeepingPanel);
		timeKeepingPanel.setLayout(layout3);
		layout3.setAutoCreateGaps(true);
		layout3.setAutoCreateContainerGaps(true);

		layout3.setHorizontalGroup(
				layout3.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(timeKeepingStatus)
						.addComponent(jSeparator1)
						.addComponent(DateStart)
						.addComponent(dateStartField)
						.addComponent(workShift)
						.addComponent(workShiftField)
						.addComponent(Salary)
						.addComponent(salaryField)
		);

		layout3.setVerticalGroup(
				layout3.createSequentialGroup()
						.addComponent(timeKeepingStatus)
						.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
						.addComponent(DateStart)
						.addComponent(dateStartField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(workShift)
						.addComponent(workShiftField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(Salary)
						.addComponent(salaryField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
		);

		// Nút Thêm
		btnThem = new CustomButton("Thêm");
		btnThem.setBackgroundColor(new Color(166, 123, 91));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Roboto", Font.BOLD, 16));
		btnThem.setBorderRadius(20);
		btnThem.addActionListener(evt -> btnThemActionPerformed(evt));

		// Layout tổng thể panel 1
		GroupLayout layout1 = new GroupLayout(dialogPanel);
		dialogPanel.setLayout(layout1);
		layout1.setAutoCreateGaps(true);
		layout1.setAutoCreateContainerGaps(true);

		layout1.setHorizontalGroup(
				layout1.createSequentialGroup()
						.addComponent(PersionalPanel)
						.addGap(50)
						.addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(timeKeepingPanel)
								.addGroup(layout1.createSequentialGroup()
										.addGap(100, 100, 100)
										.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
		);

		layout1.setVerticalGroup(
				layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(PersionalPanel)
						.addGroup(layout1.createSequentialGroup()
								.addComponent(timeKeepingPanel)
								.addGap(30)
								.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
		);

		// Đặt layout cho dialog
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dialogPanel)
		);

		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dialogPanel)
		);

		pack();
	}

	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {
		String ten = nameField.getText();
		String ma = idField.getText();
		String sdt = phoneNumberField.getText();
		String cccd = cccdField.getText();
		String diaChi = addressField.getText();
		String ngaySinh = dateOfBirthField.getText();
		String ngayVaoLam = dateStartField.getText();
		String caLam = workShiftField.getText();
		String luong = salaryField.getText();
		isAdd = true;

		// Thêm dòng vào model
//		tableModel.addRow(new Object[]{ten, ma, sdt, ngaySinh, luong, cccd, ngayVaoLam, caLam, diaChi});

		JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
		dispose();
	}

	private void styleCustomField(CustomTextField tf) {
		tf.setPreferredSize(new Dimension(300, 30));
		tf.setFont(new Font("Roboto", Font.BOLD, 14));
		tf.setBorderRadius(20);
		tf.setForeground(new Color(166, 123, 91));
	}
	
	public String getAddressField() {
		return addressField.getText();
	}

	public String getCccdField() {
		return cccdField.getText();
	}

	public String getDateOfBirthField() {
		return dateOfBirthField.getText();
	}

	public String getDateStartField() {
		return dateStartField.getText();
	}

	public String getIdField() {
		return idField.getText();
	}

	public String getNameField() {
		return nameField.getText();
	}

	public String getPhoneNumberField() {
		return phoneNumberField.getText();
	}

	public String getSalaryField() {
		return salaryField.getText();
	}
	
	public String getWorkShiftField() {
		return workShiftField.getText();
	}
	public boolean getIsAdd(){
		return isAdd;
	}
}
