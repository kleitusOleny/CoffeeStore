package view.Manager;

import controller.EmployeeController;
import data.ReadFileJson;

import data.dto.EmployeeDTO;
import model.employee_system.Employee;
import model.employee_system.EmployeeSystem;

import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class EmployeeManagement extends JPanel implements Observer {

    private CustomButton jbutThemNV;
    private CustomTable emsTable;
    private JScrollPane tableScrollPane;

    private CustomButton btnAddEmployee;
    private DefaultTableModel modelTable;
    
    private List<EmployeeDTO> employeeDTOList = ReadFileJson.readFileJSONForEmployee();
    private Object[][] employeeData = ReadFileJson.getEmployeeData();
    private EmployeeSystem model;
    private EmployeeController controller;
    private AddEmployeeDialog themNhanVienFrame;
    

    private JTextField searchField;
    private CustomButton timButton;

    public EmployeeManagement(EmployeeSystem model) {
        this.model = model;
        model.addObserver(this);
        setLayout(new BorderLayout());
        setBackground(new Color(254, 216, 177));
        initComponents();
        this.controller = new EmployeeController(model,this);
    }

    private void initComponents() {
        // === THANH CÔNG CỤ PHÍA TRÊN ===
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(254, 216, 177));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jbutThemNV = createAddEmployeeButton();
        JPanel searchPanel = createSearchBoxWithButton();

        // Căn trái nút, căn phải ô tìm kiếm
        topPanel.add(jbutThemNV, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        // === BẢNG DỮ LIỆU ===
        initEmployeeTable();

        // === THÊM VÀO GIAO DIỆN CHÍNH ===
        add(topPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }



    private CustomButton createAddEmployeeButton() {
        btnAddEmployee = new CustomButton("Thêm nhân viên");
        btnAddEmployee.setBackgroundColor(new Color(166, 123, 91));
        btnAddEmployee.setForeground(Color.WHITE);
        btnAddEmployee.setFont(new Font("Roboto", Font.BOLD, 14));
//        button.setFocusPainted(false);
        btnAddEmployee.setBorderRadius(20);
        btnAddEmployee.addActionListener((ActionEvent e) -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            themNhanVienFrame = new AddEmployeeDialog(parentFrame, true, modelTable);
            themNhanVienFrame.setVisible(true);
            controller.init();
        });
        return btnAddEmployee;
    }

    private JPanel createSearchBoxWithButton() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(260, 26)); // ✅ Giảm chiều dài

        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        searchField = new JTextField(15); // ✅ Giảm chiều dài text field
        searchField.setBorder(null);
        searchField.setPreferredSize(new Dimension(110, 26));
        searchField.setOpaque(true);
        searchField.setFont(new Font("Roboto",Font.BOLD, 16));
        searchField.setForeground(new Color(166, 123, 91));

        JButton searchIconButton = new JButton();
        searchIconButton.setFocusable(false);
        searchIconButton.setBorder(null);
        searchIconButton.setContentAreaFilled(false);
        searchIconButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        try {
            ImageIcon iconAdd = new ImageIcon("src\\main\\image\\search.png");
            Image newImage = iconAdd.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH);
            searchIconButton.setIcon(new ImageIcon(newImage));
        } catch (Exception e) {
            searchIconButton.setText("🔍");
        }

        searchIconButton.addActionListener(e -> performSearch());

        panel.add(searchField, BorderLayout.CENTER);
        panel.add(searchIconButton, BorderLayout.EAST);
        return panel;
    }


    private void initEmployeeTable() {
        String[] columns = { "Tên", "Mã NV", "SĐT", "Ngày Sinh", "Lương" };

        modelTable = new DefaultTableModel(employeeData, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        emsTable = new CustomTable();
        emsTable.getTableHeader().setBackground(new Color(255, 224, 178));
        emsTable.getTableHeader().setReorderingAllowed(false);
        emsTable.setModel(modelTable);
        emsTable.setFont(new Font("Roboto", Font.PLAIN, 15));

        JTableHeader header = emsTable.getTableHeader();
        header.setFont(new Font("Roboto", Font.BOLD, 16));
        header.setBackground(new Color(255, 224, 178));
        header.setForeground(Color.BLACK);

        emsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showEditDialogOnRowClick();
            }
        });

        tableScrollPane = new JScrollPane(emsTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tableScrollPane.getViewport().setBackground(Color.WHITE);
    }

    private void showEditDialogOnRowClick() {
        int selectedRow = emsTable.getSelectedRow();
        if (selectedRow >= 0) {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

            // Lấy mã nhân viên từ dòng đã chọn để tìm trong danh sách JSON
            String ma = emsTable.getValueAt(selectedRow, 1).toString();

            // Đọc danh sách nhân viên từ JSON
            List<EmployeeDTO> employeeList = ReadFileJson.readFileJSONForEmployee();

            // Tìm đối tượng nhân viên tương ứng
            for (EmployeeDTO emp : employeeList) {
                if (emp.getId().equals(ma)) {
                    // Tạo và hiển thị dialog chỉnh sửa
                    ChangforInforEmployeeDialog dialog = new ChangforInforEmployeeDialog(parentFrame, true, (DefaultTableModel) emsTable.getModel());

                    // Truyền dữ liệu đầy đủ vào dialog
                    dialog.setData(
                            emp.getName(),
                            emp.getId(),
                            emp.getPhoneNumber(),
                            emp.getIdentifyNumber(),
                            emp.getAddress(),
                            emp.getBirth(),
                            emp.getStartingDate(),
                            emp.getShift(),
                            emp.getSalary()
                    );

                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }


    private void performSearch() {
        String keyword = searchField.getText().trim().toLowerCase();

        DefaultTableModel model = (DefaultTableModel) emsTable.getModel();
        model.setRowCount(0); // Xóa hết dữ liệu cũ

        for (EmployeeDTO emp : employeeDTOList) {
            String ten = emp.getName().toLowerCase();
            String ma = emp.getId().toLowerCase();
            String sdt = emp.getPhoneNumber().toLowerCase();

            if (ten.contains(keyword) || ma.contains(keyword) || sdt.contains(keyword)) {
                model.addRow(new Object[]{
                        emp.getName(), emp.getId(), emp.getPhoneNumber(), emp.getBirth(), emp.getSalary()
                });
            }
        }
    }


    // === Getter nếu cần dùng bên ngoài ===
    public JTextField getSearchField() {
        return searchField;
    }

    public CustomButton getTimButton() {
        return timButton;
    }

    public Object getModelTable() {
        return this.modelTable;
    }
    
    public void refreshTable() {
        modelTable.setRowCount(0);
        
        for (Employee employee: model.getListEmp()){
            modelTable.addRow(new Object[]{employee.getName(),employee.getNumsPhone(),employee.getDayOfBirth(),employee.totalSalary()});
        }
    }
    
    public AddEmployeeDialog getThemNhanVienFrame() {
        return themNhanVienFrame;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("updated Employee Management");
        refreshTable();
    }
}
