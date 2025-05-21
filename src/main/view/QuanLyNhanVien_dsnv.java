package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;

public class QuanLyNhanVien_dsnv extends JPanel {

    private JButton jbutThemNV;
    private CustomTable emsTable;
    private JScrollPane tableScrollPane;

    // Biến toàn cục cho thanh tìm kiếm
    private CustomTextField searchField;
    private CustomButton timButton;

    public QuanLyNhanVien_dsnv() {
        setLayout(new BorderLayout());
        setBackground(new Color(254, 216, 177));
        initComponents();
    }

    private void initComponents() {
        // === THANH CÔNG CỤ PHÍA TRÊN ===
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(254, 216, 177));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nút Thêm nhân viên
        jbutThemNV = new JButton("Thêm nhân viên");
        jbutThemNV.setBackground(new Color(166, 123, 91));
        jbutThemNV.setForeground(Color.WHITE);
        jbutThemNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jbutThemNV.setFocusPainted(false);
        jbutThemNV.addActionListener((ActionEvent e) -> {
            QLNV_ThemNhanVien themNhanVienFrame = new QLNV_ThemNhanVien();
            themNhanVienFrame.setVisible(true);
        });

        topPanel.add(jbutThemNV);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(createSearchBoxWithButton());

        // === DỮ LIỆU MẪU CHO BẢNG ===
        String[] columns = { "Tên", "Mã NV", "SĐT", "Ngày Sinh", "Lương" };
        Object[][] data = {
                { "Nguyễn Văn A", "NV01", "0909123456", "01/01/1990", "10.000.000đ" },
                { "Trần Thị B", "NV02", "0988123456", "15/03/1992", "12.000.000đ" },
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        emsTable = new CustomTable();
        emsTable.setModel(model);
        emsTable.setFont(new Font("SansSerif", Font.PLAIN, 15));

        JTableHeader header = emsTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setBackground(new Color(255, 224, 178));
        header.setForeground(Color.BLACK);

        tableScrollPane = new JScrollPane(emsTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tableScrollPane.getViewport().setBackground(Color.WHITE);

        add(topPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    private JPanel createSearchBoxWithButton() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(220, 26)); // Giảm chiều rộng
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        searchField = new CustomTextField(20);
        searchField.setBorder(null);
        searchField.setPreferredSize(new Dimension(120, 26)); // Giảm chiều rộng
        searchField.setOpaque(true);
        searchField.setBorderRadius(20);
        searchField.setForeground(new Color(166, 123, 91));

        JButton searchButton = new JButton();
        timButton = new CustomButton("Tìm Button");

        searchButton.setFocusable(false);
        searchButton.setBorder(null);
        searchButton.setContentAreaFilled(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        try {
            ImageIcon iconAdd = new ImageIcon("src\\main\\image\\search.png");
            Image image = iconAdd.getImage();
            Image newImage = image.getScaledInstance(14, 14, Image.SCALE_SMOOTH); // icon nhỏ hơn 1 chút
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


    // Hàm xử lý tìm kiếm – bạn sẽ hiện thực thêm logic tại đây
    private void performSearch() {
        String keyword = searchField.getText().trim().toLowerCase();
        System.out.println("Tìm kiếm: " + keyword);
        // TODO: lọc lại dữ liệu bảng theo từ khoá
    }

    // Getter nếu muốn gọi từ nơi khác
    public CustomTextField getSearchField() {
        return searchField;
    }

    public CustomButton getTimButton() {
        return timButton;
    }
}
