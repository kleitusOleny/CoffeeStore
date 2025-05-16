package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class KhachHangPanel extends JPanel {
    private CustomTable table;
    private DefaultTableModel model;
    private JTextField txtTimKiem;
    private Vector<Vector<Object>> originalData;
    private String[] columns = {"Họ và tên", "Số điện thoại", "Điểm tích lũy", "Trạng thái", "Đã gửi"};

    public KhachHangPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 243, 216));

        // Sidebar
        JPanel cus = new ManMenuPanel();
        add(cus, BorderLayout.WEST);

        // Nội dung chính
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(255, 230, 180));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(contentPanel, BorderLayout.CENTER);

        // Panel top chứa nút và ô tìm
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 200, 180));

        // Bên trái: nút
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftPanel.setOpaque(false);
        CustomButton btnKhachThuong = createActionButton("khách thường");
        CustomButton btnKhachVip = createActionButton("khách vip");
        CustomButton btnTatCa = createActionButton("Tất cả");
        CustomButton btnXoa = createActionButton("Xóa");
        CustomButton btnGuiThongBao = createActionButton("Gửi Thông Báo");
        leftPanel.add(btnKhachThuong);
        leftPanel.add(btnKhachVip);
        leftPanel.add(btnTatCa);
        leftPanel.add(btnXoa);
        leftPanel.add(btnGuiThongBao);

        // Bên phải: tìm kiếm
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        rightPanel.setOpaque(false);
        txtTimKiem = new JTextField(15);
        txtTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        CustomButton btnTimKiem = createActionButton("Tìm Kiếm");
        rightPanel.add(txtTimKiem);
        rightPanel.add(btnTimKiem);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);
        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Dữ liệu mẫu
        Object[][] data = {
                {"Nguyễn Văn A", "1234567891", 49, "Bình thường", false},
                {"Nguyễn Văn B", "1234567892", 38, "Bình thường", false},
                {"Nguyễn Văn C", "1234567893", 70, "VIP", false},
                {"Nguyễn Văn D", "1234567894", 70, "VIP", false},
        };

        // Lưu dữ liệu gốc
        originalData = new Vector<>();
        for (Object[] row : data) {
            Vector<Object> rowData = new Vector<>();
            for (Object cell : row) {
                rowData.add(cell);
            }
            originalData.add(rowData);
        }

        // Tạo model từ dữ liệu gốc
        model = new DefaultTableModel(new Vector<>(originalData), new Vector<>(List.of(columns))) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 4 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        table = new CustomTable();
        table.setModel(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 17));
        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // ==== XỬ LÝ SỰ KIỆN ====

        // Tìm kiếm theo số điện thoại
        btnTimKiem.addActionListener(e -> {
            String phone = txtTimKiem.getText().trim();
            if (phone.isEmpty()) {
                model.setDataVector(new Vector<>(originalData), new Vector<>(List.of(columns)));
                return;
            }

            Vector<Vector<Object>> filtered = new Vector<>();
            for (Vector<Object> row : originalData) {
                if (row.get(1).toString().equals(phone)) {
                    filtered.add(row);
                }
            }

            if (filtered.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy số điện thoại: " + phone);
            }

            model.setDataVector(filtered, new Vector<>(List.of(columns)));
        });

        // Xóa khách hàng
        btnXoa.addActionListener(e -> {
            int[] selectedRows = table.getSelectedRows();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để xóa.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa các khách hàng đã chọn?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int modelIndex = table.convertRowIndexToModel(selectedRows[i]);
                    Vector<Object> row = (Vector<Object>) model.getDataVector().get(modelIndex);
                    originalData.remove(row);
                    model.removeRow(modelIndex);
                }
            }
        });

        // Gửi thông báo
        btnGuiThongBao.addActionListener(e -> {
            String[] options = {"Gửi tất cả", "Gửi khách thường", "Gửi khách VIP"};
            int choice = JOptionPane.showOptionDialog(
                    this,
                    "Chọn đối tượng muốn gửi thông báo:",
                    "Gửi Thông Báo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice >= 0) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    String trangThai = model.getValueAt(i, 3).toString();
                    if (choice == 0 // tất cả
                            || (choice == 1 && trangThai.equalsIgnoreCase("Bình thường"))
                            || (choice == 2 && trangThai.equalsIgnoreCase("VIP"))) {
                        model.setValueAt(true, i, 4);
                    }
                }
                JOptionPane.showMessageDialog(this, "Thông báo đã được gửi.");
            }
        });

        // Lọc khách thường
        btnKhachThuong.addActionListener(e -> {
            Vector<Vector<Object>> filtered = new Vector<>();
            for (Vector<Object> row : originalData) {
                if ("Bình thường".equalsIgnoreCase(row.get(3).toString())) {
                    filtered.add(row);
                }
            }
            model.setDataVector(filtered, new Vector<>(List.of(columns)));
        });

        // Lọc khách VIP
        btnKhachVip.addActionListener(e -> {
            Vector<Vector<Object>> filtered = new Vector<>();
            for (Vector<Object> row : originalData) {
                if ("VIP".equalsIgnoreCase(row.get(3).toString())) {
                    filtered.add(row);
                }
            }
            model.setDataVector(filtered, new Vector<>(List.of(columns)));
        });

        // Hiện lại tất cả
        btnTatCa.addActionListener(e -> {
            model.setDataVector(new Vector<>(originalData), new Vector<>(List.of(columns)));
        });
    }

    private CustomButton createActionButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(140, 35));
        button.setBackgroundColor(new Color(166, 123, 91));
        button.setHoverColor(new Color(200, 230, 255));
        button.setGradientColors(Color.CYAN, Color.BLUE);
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(20);
        button.setDrawBorder(true);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản lý Khách Hàng");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new KhachHangPanel());
            frame.setVisible(true);
        });
    }
}
