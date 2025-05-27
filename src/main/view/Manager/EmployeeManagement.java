package view.Manager;

import data.ReadFileJson;
import data.dto.FormatEmployee;
import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EmployeeManagement extends JPanel {

    private CustomButton jbutThemNV;
    private CustomTable emsTable;
    private JScrollPane tableScrollPane;

    private List<FormatEmployee> formatEmployeeList = ReadFileJson.readFileJSONForEmployee();
    Object[][] employeeData = ReadFileJson.getEmployeeData();

    private JTextField searchField;
    private CustomButton timButton;

    public EmployeeManagement() {
        setLayout(new BorderLayout());
        setBackground(new Color(254, 216, 177));
        initComponents();
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
        CustomButton button = new CustomButton("Thêm nhân viên");
        button.setBackgroundColor(new Color(166, 123, 91));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Roboto", Font.BOLD, 14));
//        button.setFocusPainted(false);
        button.setBorderRadius(20);
        button.addActionListener((ActionEvent e) -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            AddEmployeeDialog themNhanVienFrame = new AddEmployeeDialog(parentFrame, true, (DefaultTableModel) emsTable.getModel());
            themNhanVienFrame.setVisible(true);
        });
        return button;
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
//        Object[][] data = {
//                { "Nguyễn Văn A", "NV01", "0909123456", "01/01/1990", "10.000.000đ" },
//                { "Trần Thị B", "NV02", "0988123456", "15/03/1992", "12.000.000đ" },
//        };

        DefaultTableModel model = new DefaultTableModel(employeeData, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        emsTable = new CustomTable();
        emsTable.getTableHeader().setBackground(new Color(255, 224, 178));
        emsTable.getTableHeader().setReorderingAllowed(false);
        emsTable.setModel(model);
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
            List<FormatEmployee> employeeList = ReadFileJson.readFileJSONForEmployee();

            // Tìm đối tượng nhân viên tương ứng
            for (FormatEmployee emp : employeeList) {
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

        for (FormatEmployee emp : formatEmployeeList) {
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

    public Object getModel() {
        return this.getModel();
    }

}
