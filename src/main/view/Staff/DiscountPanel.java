package view.Staff;


import controller.CustomerController;
import data.dto.ClientDTO;
import data.dto.DiscountDTO;
import data.ReadFileJson;
import model.customer_system.Customer;
import model.customer_system.CustomerSystem;
import utils.CustomerStatus;
import view.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class DiscountPanel extends JPanel implements Observer {
    private List<ClientDTO> clientDTOList = ReadFileJson.readFileJSONForClient();
    private List<DiscountDTO> discountsListDTO = ReadFileJson.readFileJSONForDiscount();
    Object[][] kmData = ReadFileJson.getKmData();
    Object[][] khachData = ReadFileJson.getKhachData();
    public CustomTable khachTable;
    private JTextField searchField;
    private CustomTable kmTable;
    private TableRowSorter<TableModel> sorter;

//    private PaymentPanel paymentPanel;
    
    private CustomButton tatCaButton;
    private CustomButton khachThuongButton;
    private CustomButton khachVIPButton;
    private CustomButton themKHButton;
    private CustomButton timButton;
    private CustomButton apDung;
    private AddCustomerDialog dialog;
    private ChangeInforCustomerDialog dialog1;
    
    private JScrollPane khachScroll;
    private JScrollPane kmScroll;
    
    private List<CustomCheckBox> khachCheckBoxes = new ArrayList<>();
    private List<CustomCheckBox> kmCheckBoxes = new ArrayList<>();
    
    private DefaultTableModel kmModel;
    private static DefaultTableModel khachModel;
    
    public static String[] customer = new String[4];
    private CustomerSystem model;
    private CustomerController controller;
    
    public DiscountPanel(CustomerSystem model) {
        this.model = model;
        this.model.addObserver(this);
        this.controller = new CustomerController(model, this);
        
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(255, 245, 204));
        
        JPanel danhSachKHPanel = new JPanel(new BorderLayout());
        danhSachKHPanel.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
        danhSachKHPanel.setBackground(new Color(255, 245, 204));
        
        JPanel topButtonPanel = new JPanel(new BorderLayout());
        topButtonPanel.setBackground(new Color(255, 235, 238));
        
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        leftButtonPanel.setBackground(new Color(255, 235, 238));
        
        // ✅ Gọi createTopButton và gán nút
        leftButtonPanel.add(createTopButton("Tất cả"));
        leftButtonPanel.add(createTopButton("khách thường"));
        leftButtonPanel.add(createTopButton("khách vip"));
        leftButtonPanel.add(createTopButton("Thêm KH"));
//        leftButtonPanel.add(createTopButton("Áp Dụng"));
        
        JPanel rightSearchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        rightSearchPanel.setBackground(new Color(255, 235, 238));
        
        JPanel searchBoxPanel = createSearchBoxWithButton();
        searchBoxPanel.setOpaque(false); // để đồng bộ màu nền
        
        rightSearchPanel.add(searchBoxPanel);
        
        
        topButtonPanel.add(leftButtonPanel, BorderLayout.WEST);
        topButtonPanel.add(rightSearchPanel, BorderLayout.EAST);
        danhSachKHPanel.add(topButtonPanel, BorderLayout.NORTH);
        
        // Bảng khách hàng
        String[] khachHeaders = {"Họ Tên", "SĐT", "Điểm Tích Lũy", "Trạng Thái", "Chọn"};
        khachModel = new DefaultTableModel(khachData, khachHeaders) {
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
        
        khachTable.getTableHeader().setBackground(new Color(255, 224, 178));
        khachTable.getTableHeader().setReorderingAllowed(false);
        
        khachTable.getColumnModel().getColumn(4).setCellRenderer(new CustomCheckBoxRenderer(khachCheckBoxes));
        khachTable.getColumnModel().getColumn(4).setCellEditor(new CustomCheckBoxEditor());
        khachScroll = new JScrollPane(khachTable);
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
                    String diem = (String) String.valueOf(model.getValueAt(modelRow, 2));
                    String state = (String) model.getValueAt(modelRow, 3);
                    Boolean isSelected = (Boolean) model.getValueAt(modelRow, 4);
                    
                    if (isSelected) {
                        customer[0] = ten;
                        customer[1] = sdt;
                        customer[2] = diem;
                        customer[3] = state;
                    }
                    
                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(DiscountPanel.this);
                    dialog1 = new ChangeInforCustomerDialog(topFrame);
                    dialog1.setKhachHang(ten, sdt, diem);
                    
                    System.out.println("ten: " + ten + " sdt: " + sdt + " diem: " + diem + " state: " + state);
                    dialog1.setVisible(true);
                    if (dialog1.isDeleted()) {
                        SwingUtilities.invokeLater(() -> {
                            ((DefaultTableModel) khachTable.getModel()).removeRow(row);
                        });
                    }
                    
                    if (dialog1.isConfirmed()) {
                        model.setValueAt(dialog1.getTenKhach(), modelRow, 0);
                        model.setValueAt(dialog1.getSDT(), modelRow, 1);
                        model.setValueAt(dialog1.getDiem(), modelRow, 2);
                    }
                }
            }
        });
        
        // Bảng khuyến mãi giữ nguyên
        JPanel kmPanel = new JPanel(new BorderLayout());
        kmPanel.setBackground(new Color(255, 204, 204));
        kmPanel.setBorder(BorderFactory.createTitledBorder("Danh sách khuyến mãi"));
        
        String[] kmHeaders = {"Mã KM", "Tên KM", "Nội Dung", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Chọn"};
        kmModel = new DefaultTableModel(kmData, kmHeaders) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };
        kmModel.addTableModelListener(e -> {
            if (e.getColumn() == 5) {
                ReadFileJson.updateFormatDiscountsFromTable(kmModel);
            }
        });
        kmTable = new CustomTable();
        kmTable.setModel(kmModel);
        kmTable.setRowHeight(30);
        kmTable.setFont(new Font("Roboto", Font.PLAIN, 16));
        kmTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 16));
        kmTable.getTableHeader().setReorderingAllowed(false);
        
        kmTable.getTableHeader().setBackground(new Color(255, 224, 178));
        kmTable.getColumnModel().getColumn(5).setCellRenderer(new CustomCheckBoxRenderer(kmCheckBoxes));
        kmTable.getColumnModel().getColumn(5).setCellEditor(new CustomCheckBoxEditor());
        
        kmScroll = new JScrollPane(kmTable);
        kmPanel.add(kmScroll, BorderLayout.CENTER);
        
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(kmPanel);
        
        add(mainPanel, BorderLayout.CENTER);
        
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
                themKHButton = button;
                try {
                    ImageIcon iconAdd = new ImageIcon("src\\main\\image\\add.png");
                    Image image = iconAdd.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    button.setIcon(new ImageIcon(image));
                    button.setHorizontalTextPosition(SwingConstants.RIGHT); // chữ nằm bên phải icon
                } catch (Exception e) {
                    System.out.println("Không tìm thấy icon add.png");
                }
                button.addActionListener(e -> addKhachHang());
                break;
            case "khách thường":
                khachThuongButton = button;
                button.addActionListener(e -> filterByTrangThai("Normal"));
                break;
            case "khách vip":
                khachVIPButton = button;
                button.addActionListener(e -> filterByTrangThai("VIP"));
                break;
            case "Tất cả":
                tatCaButton = button;
                button.addActionListener(e -> filterByTrangThai(null));
                break;
        }
        
        return button;
    }
    
    //Xu Li tren Controller
    private void addKhachHang() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        dialog = new AddCustomerDialog(topFrame);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            String ten = dialog.getTenKhach();
            String sdt = dialog.getSDT();
            controller.addCustomer(ten, sdt);
        }
    }
    
    private void performSearch() {
        String keyword = searchField.getText().trim().toLowerCase();
        
        if (keyword.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 1));
        }
    }
    
    private void filterByTrangThai(String trangThai) {
        if (trangThai == null) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)^" + trangThai + "$", 3));
        }
    }
    
    private void XuLyApDungKhuyenMai() {
        DefaultTableModel khackModel = (DefaultTableModel) khachTable.getModel();
        DefaultTableModel kmModel = (DefaultTableModel) kmTable.getModel();
        
        int khachCount = khackModel.getRowCount();
        StringBuilder notify = new StringBuilder();
        
        for (int i = 0; i < khachCount; i++) {
            boolean chonKH = (boolean) khackModel.getValueAt(i, 4);
            if (chonKH) {
                String ten = (String) khackModel.getValueAt(i, 0);
                notify.append("Áp dụng khuyến mãi cho: ").append(ten).append("\n");
            }
        }
        
        kmModel.setRowCount(0);
        JOptionPane.showMessageDialog(this, notify.isEmpty() ? "Chưa chọn khách hàng nào." : notify.toString(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void refreshTable() {
        khachModel.setRowCount(0);
//        for (Customer customer : model.getNormalCustomers()) {
//            khachModel.addRow(new Object[]{customer.getName(), customer.getNumsPhone(), customer.pointAccumulated(), "Bình Thường", false});
//        }
//        for (Customer customer : model.getVIPCustomers()) {
//            khachModel.addRow(new Object[]{customer.getName(), customer.getNumsPhone(), customer.pointAccumulated(), "VIP", false});
//        }
        for (String key : model.getListCus().keySet()){
            for (Customer customer: model.getListCus().get(key)){
                if (customer.getType().equals("VIP")){
                    khachModel.addRow(new Object[]{customer.getName(), customer.getNumsPhone(), customer.pointAccumulated(), "VIP", false});
                }else {
                    khachModel.addRow(new Object[]{customer.getName(), customer.getNumsPhone(), customer.pointAccumulated(), "Normal", false});
                }
            }
        }
    }
    

    
    
    // Custom renderer và editor giữ nguyên như cũ...
    private static class CustomCheckBoxRenderer extends JPanel implements TableCellRenderer {
        private List<CustomCheckBox> checkBoxList;
        
        public CustomCheckBoxRenderer(List<CustomCheckBox> checkBoxList) {
            this.checkBoxList = checkBoxList;
            setLayout(new GridBagLayout());
            setOpaque(false);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            CustomCheckBox checkBox;
            if (checkBoxList.size() > row) {
                checkBox = checkBoxList.get(row);
            } else {
                checkBox = new CustomCheckBox("");
                checkBox.setOpaque(false);
                checkBoxList.add(checkBox);
            }
            
            if (value instanceof Boolean) {
                checkBox.setSelected((Boolean) value);
            } else {
                checkBox.setSelected(false);
            }
            
            removeAll();
            add(checkBox);
            
            return this;
        }
    }
    
    
    private static class CustomCheckBoxEditor extends AbstractCellEditor implements TableCellEditor {
        private final CustomCheckBox checkBox;
        
        public CustomCheckBoxEditor() {
            checkBox = new CustomCheckBox("");
            checkBox.setOpaque(false);
            checkBox.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    checkBox.setSelected(!checkBox.isSelected());
                    fireEditingStopped(); // Dừng chỉnh sửa để JTable cập nhật giá trị ngay
                }
            });
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
            } else {
                checkBox.setSelected(false);
            }
            
            return checkBox;
        }
    }
    
    private JPanel createSearchBoxWithButton() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 28));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        searchField = new JTextField(20); // dùng biến toàn cục
        searchField.setBorder(null);
        searchField.setPreferredSize(new Dimension(140, 28));
        searchField.setOpaque(true);
        searchField.setFont(new Font("Roboto", Font.BOLD, 16));
        searchField.setForeground(new Color(166, 123, 91));
        
        JButton searchButton = new JButton();
        timButton = new CustomButton("Tìm Button"); // vẫn tạo nút CustomButton nếu cần getter
        
        searchButton.setFocusable(false);
        searchButton.setBorder(null);
        searchButton.setContentAreaFilled(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        try {
            ImageIcon iconAdd = new ImageIcon("src\\main\\image\\search.png");
            Image image = iconAdd.getImage();
            Image newImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon icon1 = new ImageIcon(newImage);
            searchButton.setIcon(icon1);
        } catch (Exception e) {
            searchButton.setText("🔍");
        }
        
        searchButton.addActionListener(e -> performSearch());
        
        panel.add(searchField, BorderLayout.CENTER);
        panel.add(searchButton, BorderLayout.EAST);
        
        return panel;
    }
    
    public CustomTable getKmTable() {
        return kmTable;
    }
    
    public CustomButton getApDung() {
        return apDung;
    }
    
    public AddCustomerDialog getDialog() {
        return dialog;
    }
    
    public ChangeInforCustomerDialog getDialog1() {
        return dialog1;
    }
    
    public DefaultTableModel getKmModel() {
        return kmModel;
    }
    
    public CustomButton getTatCaButton() {
        return tatCaButton;
    }
    
    public CustomButton getKhachThuongButton() {
        return khachThuongButton;
    }
    
    public CustomButton getKhachVIPButton() {
        return khachVIPButton;
    }
    
    public CustomButton getThemKHButton() {
        return themKHButton;
    }
    
    public CustomButton getTimButton() {
        return timButton;
    }
    
    public JTextField getSearchField() {
        return searchField;
    }
    
    public JTable getKhachTable() {
        return khachTable;
    }
    
    public TableRowSorter<TableModel> getSorter() {
        return sorter;
    }
    
    public static DefaultTableModel getKhachModel() {
        return khachModel;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof CustomerStatus) {
            CustomerStatus status = (CustomerStatus) arg;
            switch (status.getAction()) {
                case "ADD_CUSTOMER":
                case "REMOVE_CUSTOMER":
                case "UPDATE_CUSTOMER":
                    refreshTable();
                    break;
            }
        }
    }
}
