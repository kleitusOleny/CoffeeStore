package controller;

import data.ReadFileJson;
import model.Date;
import model.employee_system.Employee;
import model.employee_system.EmployeeSystem;
import model.employee_system.Manager;
import model.employee_system.Seller;
import view.ButtonEditor;
import view.IView;
import view.Manager.EmployeeManagement;
import view.Manager.MenuEditorPanel;
import view.Manager.PromotionManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeController {
    EmployeeSystem employeeSystem;
    IView view;
    EmployeeManagement employeeManagementView;
    MenuEditorPanel menuEditorPanel;
    PromotionManagement promotionManagementView;
    private DefaultTableModel model;

    public EmployeeController(EmployeeSystem employeeSystem, IView view) {
        this.employeeSystem = employeeSystem;
        this.view = view;
        init();
    }

    public void init() {
        // Khởi tạo các thành phần khung nhìn nếu chúng là một phần của IView được cung cấp
        if (view instanceof EmployeeManagement) {
            this.employeeManagementView = (EmployeeManagement) view;
            setupEmployeeManagementActions();
        } else if (view instanceof MenuEditorPanel) {
            this.menuEditorPanel = (MenuEditorPanel) view;
            //hanh dong menu da duoc xu ly trong menuEditorPanel
        } else if (view instanceof PromotionManagement) {
            this.promotionManagementView = (PromotionManagement) view;
            setupPromotionManagementActions();
        }
    }

    private void setupEmployeeManagementActions() {
        // Nút "Thêm nhân viên" đã được thiết lập trong EmployeeManagement
        // Xử lý sự kiện click vào hàng để chỉnh sửa thông tin nhân viên
        DefaultTableModel tableModel = (DefaultTableModel) employeeManagementView.getModel();
    }

    private void setupPromotionManagementActions() {
        // Xử lý nút "Thêm khuyến mãi"
        promotionManagementView.getJButton1().addActionListener(e -> {
            String ma = promotionManagementView.getjTextField1().getText().trim();
            String ten = promotionManagementView.getjTextField2().getText().trim();
            String loai = promotionManagementView.getjTextField3().getText().trim();
            String bd = promotionManagementView.getjTextField4().getText().trim();
            String kt = promotionManagementView.getjTextField5().getText().trim();
            if (ma.isEmpty() || ten.isEmpty() || loai.isEmpty() || bd.isEmpty() || kt.isEmpty()) {
                JOptionPane.showMessageDialog(promotionManagementView, "Vui long nhap day du thong tin!");
                return;
            }
            // them vao file JSON
            ReadFileJson.addDiscount(ma, ten, loai, bd, kt);
            //cap nhat bang
            model = (DefaultTableModel) promotionManagementView.getTable().getModel();
            model.addRow(new Object[]{ma, ten, loai, bd, kt});

            // xoa cac truong nhap lieu
            promotionManagementView.getjTextField1().setText("");
            promotionManagementView.getjTextField2().setText("");
            promotionManagementView.getjTextField3().setText("");
            promotionManagementView.getjTextField4().setText("");
            promotionManagementView.getjTextField5().setText("");
        });

        // xu ly xoa khuyen mai
        promotionManagementView.getTable().getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(promotionManagementView.getTable()) {
            @Override
            protected void performAction(int row) {
                String ma = (String) model.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(promotionManagementView,
                        "Bạn có chắc muốn xóa khuyến mãi này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ReadFileJson.deleteEmployee(ma);
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(promotionManagementView, "Xóa khuyến mãi thành công!");
                }
            }
        });
    }

    //them nhan vien
    public void addEmployee(String ten, String ma, String sdt, String cccd, String diaChi, String ngaySinh,
                            String ngayVaoLam, String caLam, String luong) {
        try {
            //chueyn doi chuoi ngay sang Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Date dob = new Date(LocalDate.parse(ngaySinh, formatter));

            // xac dinh loai nhan vien
            Employee employee;
            if (caLam != null && caLam.isEmpty()) {
                // Nhân viên bán hàng với ca làm và lương theo giờ
                int hours = parseShiftToHours(caLam);
                double hourlyRate = Double.parseDouble(luong) / hours;
                employee = new Seller(ma, ten, sdt, "", diaChi, dob, hours, hourlyRate);
            } else {
                // quan ly voi luong co dinh
                double salary = Double.parseDouble(luong);
                employee = new Manager(ma, ten, sdt, "", diaChi, dob, salary);
            }
            // them vao employeeSystem
            employeeSystem.addEmployee(employee);
            // them vao file JSON
            ReadFileJson.addEmployee(ten, ma, sdt, cccd, diaChi, ngaySinh, ngayVaoLam, caLam, luong);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm nhân viên: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // cap nhat thong tin nhan vien
    public void updateEmployee(String ma, String ten, String sdt, String cccd, String diaChi,
                               String ngaySinh, String ngayVaoLam, String caLam, String luong) {
        try {
            // tim nhan vien trong employeeSystem
            for (Employee emp : employeeSystem.getListEmp()) {
                if (emp.getEmp_no().equals(ma)){
                    //chuyen doi ngay sinhcua nhan cien
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    Date dob = new Date(LocalDate.parse(ngaySinh,formatter));
                    Employee updateedEmployee;
                    if (emp.isManager()){
                        double salary = Double.parseDouble(luong);
                        updateedEmployee = new Manager(ma, ten, sdt, "", diaChi, dob, salary);
                    }else {
                        int hours = parseShiftToHours(caLam);
                        double hourlyRate = Double.parseDouble(luong) / hours;
                        updateedEmployee = new Seller(ma, ten, sdt, "", diaChi, dob, hours, hourlyRate);
                    }
                    // xoa nhan vien cu , them nha vien moi
                    employeeSystem.removeEmployee(emp);
                    employeeSystem.addEmployee(updateedEmployee);

                    //cap nhat file JSON
                    ReadFileJson.deleteEmployee(ma);
                    ReadFileJson.addEmployee(ten, ma, sdt, cccd, diaChi, ngaySinh, ngayVaoLam, caLam, luong);
                    break;
                }
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật nhân viên: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }
    // xoa nhan vien
    public void deleteEmployee(String ma) {
        try {
            for (Employee emp : employeeSystem.getListEmp()) {
                if (emp.getEmp_no().equals(ma)) {
                    employeeSystem.removeEmployee(emp);
                    ReadFileJson.deleteEmployee(ma);
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa nhân viên: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Phương thức điểm danh nhân viên
    public void checkInEmployee(String employeeID) {
        for (Employee emp : employeeSystem.getListEmp()) {
            if (emp.getEmp_no().equals(employeeID)) {
                employeeSystem.getManagerAttendent().checkIn(emp);
                break;
            }
        }
    }
    // tinh luong
    public double calculateSalary(String employeeID) {
        for (Employee emp : employeeSystem.getListEmp()) {
            if (emp.getEmp_no().equals(employeeID)) {
                return emp.totalSalary();
            }
        }
        return 0.0;
    }
    // Phương thức chuyển đổi ca làm sang số giờ
    private int parseShiftToHours(String caLam) {
        // Ví dụ: "Sáng" = 4 giờ, "Chiều" = 4 giờ, "Cả ngày" = 8 giờ
        return switch (caLam.toLowerCase()) {
            case "sáng", "chiều" -> 4;
            case "cả ngày" -> 8;
            default -> 8; // Mặc định 8 giờ nếu ca làm không xác định
        };
    }

    public EmployeeSystem getEmployeeSystem() {
        return employeeSystem;
    }

    public IView getView() {
        return view;
    }

    public EmployeeManagement getEmployeeManagementView() {
        return employeeManagementView;
    }

    public MenuEditorPanel getMenuEditorPanel() {
        return menuEditorPanel;
    }

    public PromotionManagement getPromotionManagementView() {
        return promotionManagementView;
    }

    public DefaultTableModel getModel() {
        return model;
    }
}
