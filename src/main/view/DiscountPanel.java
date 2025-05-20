package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// ... Các import giữ nguyên

public class DiscountPanel extends JPanel {

    private CustomTable khachTable;
    private JTextField searchField;
    private TableRowSorter<TableModel> sorter;

    public DiscountPanel() {
        setLayout(new BorderLayout());

//        EmployeeMenuPanel menuPanel = new EmployeeMenuPanel();
//        add(menuPanel, BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(255, 235, 193));

        JPanel danhSachKHPanel = new JPanel(new BorderLayout());
        danhSachKHPanel.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
        danhSachKHPanel.setBackground(new Color(255, 235, 193));

        JPanel topButtonPanel = new JPanel(new BorderLayout());
        topButtonPanel.setBackground(new Color(255, 235, 238));

        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        leftButtonPanel.setBackground(new Color(255, 235, 238));
        leftButtonPanel.add(createTopButton("Tất cả"));
        leftButtonPanel.add(createTopButton("khách thường"));
        leftButtonPanel.add(createTopButton("khách vip"));
        leftButtonPanel.add(createTopButton("Thêm KH"));

        JPanel rightSearchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        rightSearchPanel.setBackground(new Color(255, 235, 238));
        searchField = new JTextField(20);
        CustomButton searchButton = new CustomButton("Tìm");
        searchButton.setBackgroundColor(new Color(166, 123, 91));
        searchButton.setTextColor(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorderRadius(20);

        rightSearchPanel.add(new JLabel("Tìm kiếm: "));
        rightSearchPanel.add(searchField);
        rightSearchPanel.add(searchButton);

        topButtonPanel.add(leftButtonPanel, BorderLayout.WEST);
        topButtonPanel.add(rightSearchPanel, BorderLayout.EAST);
        danhSachKHPanel.add(topButtonPanel, BorderLayout.NORTH);

        // Bảng khách hàng
        String[] khachHeaders = {"Họ Tên", "SĐT", "Điểm Tích Lũy", "Trạng Thái", "Chọn"};
        Object[][] khachData = {
                {"Nguyễn Văn A", "1234567891", "70", "Bình Thường", false},
                {"Trần Thị B", "0987654321", "120", "VIP", false},
                {"Lê Văn C", "0911222333", "45", "Bình Thường", false},
                {"Phạm Thị D", "0999888777", "200", "VIP", false},
        };

        DefaultTableModel khachModel = new DefaultTableModel(khachData, khachHeaders) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 4 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        khachTable = new CustomTable();
        khachTable.setModel(khachModel);
        khachTable.setRowHeight(30);
        khachTable.setFont(new Font("SansSerif", Font.PLAIN, 16));
        khachTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));

        sorter = new TableRowSorter<>(khachModel);
        khachTable.setRowSorter(sorter);

        khachTable.getColumnModel().getColumn(4).setCellRenderer(new CustomCheckBoxRenderer());
        khachTable.getColumnModel().getColumn(4).setCellEditor(new CustomCheckBoxEditor());

        JScrollPane khachScroll = new JScrollPane(khachTable);
        danhSachKHPanel.add(khachScroll, BorderLayout.CENTER);
        mainPanel.add(danhSachKHPanel);

        khachTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = khachTable.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    int modelRow = khachTable.convertRowIndexToModel(row);
                    DefaultTableModel model = (DefaultTableModel) khachTable.getModel();

                    String ten = (String) model.getValueAt(modelRow, 0);
                    String sdt = (String) model.getValueAt(modelRow, 1);
                    String diem = (String) model.getValueAt(modelRow, 2);

                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(DiscountPanel.this);
                    ChangeInforCustomerDialog dialog = new ChangeInforCustomerDialog(topFrame);
                    dialog.setKhachHang(ten, sdt, diem);
                    dialog.setVisible(true);

                    if (dialog.isConfirmed()) {
                        model.setValueAt(dialog.getTenKhach(), modelRow, 0);
                        model.setValueAt(dialog.getSDT(), modelRow, 1);
                        model.setValueAt(dialog.getDiem(), modelRow, 2);
                    }
                }
            }
        });

        // ==== Danh sách khuyến mãi giữ nguyên như cũ ====

        JPanel kmPanel = new JPanel(new BorderLayout());
        kmPanel.setBackground(new Color(255, 204, 204));
        kmPanel.setBorder(BorderFactory.createTitledBorder("Danh sách khuyến mãi"));

        String[] kmHeaders = {"Mã KM", "Tên KM", "Nội Dung", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Chọn"};
        Object[][] kmData = {
                {"KM01", "Tết Sale", "Giảm 10%", "2025-01-01", "2025-01-10", false},
                {"KM02", "Hè Rực Rỡ", "Tặng quà", "2025-06-01", "2025-06-30", false}
        };

        DefaultTableModel kmModel = new DefaultTableModel(kmData, kmHeaders) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };

        CustomTable kmTable = new CustomTable();
        kmTable.setModel(kmModel);
        kmTable.setRowHeight(30);
        kmTable.setFont(new Font("SansSerif", Font.PLAIN, 16));
        kmTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        kmTable.getColumnModel().getColumn(5).setCellRenderer(new CustomCheckBoxRenderer());
        kmTable.getColumnModel().getColumn(5).setCellEditor(new CustomCheckBoxEditor());

        JScrollPane kmScroll = new JScrollPane(kmTable);
        kmPanel.add(kmScroll, BorderLayout.CENTER);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(kmPanel);

        add(mainPanel, BorderLayout.CENTER);

        // === Sự kiện tìm kiếm ===
        searchButton.addActionListener(e -> performSearch());
    }

    private CustomButton createTopButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(1000, 200));
        button.setBackgroundColor(new Color(166, 123, 91));
        button.setGradientColors(Color.YELLOW, new Color(142, 50, 0));
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(20);

        switch (text) {
            case "Thêm KH":
                button.addActionListener(e -> addKhachHang());
                break;
            case "khách thường":
                button.addActionListener(e -> filterByTrangThai("Bình Thường"));
                break;
            case "khách vip":
                button.addActionListener(e -> filterByTrangThai("VIP"));
                break;
            case "Tất cả":
                button.addActionListener(e -> filterByTrangThai(null));
                break;
        }

        return button;
    }

    private void addKhachHang() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddCustomerDialog dialog = new AddCustomerDialog(topFrame);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            String ten = dialog.getTenKhach();
            String sdt = dialog.getSDT();

            if (!ten.isEmpty() && !sdt.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) khachTable.getModel();
                model.addRow(new Object[]{ten, sdt, "0", "Bình Thường", false});
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void performSearch() {
        String keyword = searchField.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 0)); // cột 0 là tên
        }
    }

    private void filterByTrangThai(String trangThai) {
        if (trangThai == null) {
            sorter.setRowFilter(null);  // Hiện tất cả
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)^" + trangThai + "$", 3)); // Cột 3 là "Trạng Thái"
        }
    }

    // Custom renderer và editor giữ nguyên như cũ...
    private static class CustomCheckBoxRenderer extends JPanel implements TableCellRenderer {
        private CustomCheckBox checkBox;

        public CustomCheckBoxRenderer() {
            setLayout(new GridBagLayout());
            checkBox = new CustomCheckBox("");
            checkBox.setOpaque(false);
            add(checkBox);
            setOpaque(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {

            if (value instanceof Boolean) {
                checkBox.setSelected((Boolean) value);
            } else {
                checkBox.setSelected(false);
            }

            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getBackground());
            }

            return this;
        }
    }

    private static class CustomCheckBoxEditor extends AbstractCellEditor implements TableCellEditor {
        private CustomCheckBox checkBox;

        public CustomCheckBoxEditor() {
            checkBox = new CustomCheckBox("");
            checkBox.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Object getCellEditorValue() {
            return checkBox.isSelected();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (value instanceof Boolean) {
                checkBox.setSelected((Boolean) value);
            }
            return checkBox;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Khuyến Mãi");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1100, 700);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new DiscountPanel());
            frame.setVisible(true);
        });
    }
}
