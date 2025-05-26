package view.Manager;

import data.ReadFileJson;
import data.dto.FormatDiscount;
import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class PromotionManagement extends JPanel {
    // Variables declaration - do not modify
    private CustomButton jButton1;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, tableTitle;
    private JPanel jPanel1, jPanel2, btnPanel, inputArea;
    private JSeparator jSeparator1;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private CustomTextField jTextField1, jTextField2, jTextField3, jTextField4, jTextField5;

    private List<FormatDiscount> formatDiscountList = ReadFileJson.readFileJSONForDiscount();
    Object[][] kmData = ReadFileJson.getKmData();

    public PromotionManagement() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 204));

        // Panel nhập liệu
        jPanel1 = new JPanel(new GridBagLayout());
        jPanel1.setBackground(new Color(255, 245, 204));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        jLabel1 = new JLabel("Mã khuyến mãi");
        jLabel2 = new JLabel("Tên khuyến mãi");
        jLabel3 = new JLabel("Loại khuyến mãi");
        jLabel4 = new JLabel("Ngày bắt đầu(dd/mm/yyyy)");
        jLabel5 = new JLabel("Ngày kết thúc(dd/mm/yyyy)");

        Font labelFont = new Font("Roboto", Font.BOLD, 15);
        jLabel1.setFont(labelFont);
        jLabel2.setFont(labelFont);
        jLabel3.setFont(labelFont);
        jLabel4.setFont(labelFont);
        jLabel5.setFont(labelFont);

        jTextField1 = new CustomTextField(20);
        jTextField1.setHorizontalAlignment(JTextField.LEFT);
        jTextField2 = new CustomTextField(20);
        jTextField2.setHorizontalAlignment(JTextField.LEFT);
        jTextField3 = new CustomTextField(20);
        jTextField3.setHorizontalAlignment(JTextField.LEFT);
        jTextField4 = new CustomTextField(20);
        jTextField4.setHorizontalAlignment(JTextField.LEFT);
        jTextField5 = new CustomTextField(20);
        jTextField5.setHorizontalAlignment(JTextField.LEFT);

        styleCustomField(jTextField1);
        styleCustomField(jTextField2);
        styleCustomField(jTextField3);
        styleCustomField(jTextField4);
        styleCustomField(jTextField5);

        int row = 0;
        gbc.gridx = 0;
        gbc.gridy = row;
        jPanel1.add(jLabel1, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField1, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        jPanel1.add(jLabel2, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField2, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        jPanel1.add(jLabel3, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField3, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        jPanel1.add(jLabel4, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField4, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        jPanel1.add(jLabel5, gbc);
        gbc.gridx = 1;
        jPanel1.add(jTextField5, gbc);

        // Nút thêm
        jButton1 = new CustomButton("Thêm Khuyến Mãi");
        jButton1.setBackgroundColor(new Color(166, 123, 91));
        jButton1.setTextColor(Color.WHITE);
        jButton1.setBorderRadius(20);
        jButton1.setPreferredSize(new Dimension(200, 40));

        btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(new Color(255, 245, 204));
        btnPanel.add(jButton1);

        inputArea = new JPanel(new BorderLayout());
        inputArea.setBackground(new Color(255, 245, 204));
        inputArea.add(jPanel1, BorderLayout.NORTH);
        inputArea.add(btnPanel, BorderLayout.CENTER);

        // Panel bảng
        String[] infor = {"Mã KM", "Tên KM", "Loại", "Ngày BĐ", "Ngày KT", ""};
        model = new DefaultTableModel(kmData, infor);
        table = new CustomTable();
        table.setModel(model);
        table.setFont(new Font("Roboto", Font.PLAIN, 16));
        table.getTableHeader().setBackground(new Color(255, 224, 178));
        table.getTableHeader().setReorderingAllowed(false);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Roboto", Font.BOLD, 16));
        header.setBackground(new Color(255, 224, 178));
        header.setForeground(Color.BLACK);


        // Gán renderer và editor cho cột cuối
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(table) {
            @Override
            protected void performAction(int row) {

            }
        });

        scrollPane = new JScrollPane(table);

        add(inputArea, BorderLayout.NORTH);
        // Label góc trái trên bảng
        JLabel tableTitle = new JLabel("Danh sách khuyến mãi");
        tableTitle.setFont(new Font("Roboto", Font.BOLD, 16));
        tableTitle.setForeground(new Color(102, 51, 0));
        tableTitle.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 0)); // căn trái và đệm

// Panel chứa label + bảng
        JPanel tableArea = new JPanel(new BorderLayout());
        tableArea.setBackground(new Color(255, 245, 204));
        tableArea.add(tableTitle, BorderLayout.NORTH);
        tableArea.add(scrollPane, BorderLayout.CENTER);

        add(tableArea, BorderLayout.CENTER);

        jButton1.addActionListener(e -> {
            String ma = jTextField1.getText().trim();
            String ten = jTextField2.getText().trim();
            String loai = jTextField3.getText().trim();
            String bd = jTextField4.getText().trim();
            String kt = jTextField5.getText().trim();

            if (ma.isEmpty() || ten.isEmpty() || loai.isEmpty() || bd.isEmpty() || kt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            } else {
                ReadFileJson.addDiscount(ma, ten, loai, bd, kt);
            }

            model.addRow(new Object[]{ma, ten, loai, bd, kt, "X"});

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
        });
    }

    private void styleCustomField(CustomTextField tf) {
        tf.setPreferredSize(new Dimension(300, 30));
        tf.setFont(new Font("Roboto", Font.BOLD, 14));
        tf.setBorderRadius(20);
        tf.setForeground(new Color(166, 123, 91));
        // Nếu có hover effect trên textfield, tắt nếu muốn:
        // tf.setDrawHover(false);
    }

    // Hàm main để chạy thử giao diện
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Khuyến Mãi");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new PromotionManagement());
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null); // căn giữa màn hình
            frame.setVisible(true);
        });
    }

    public JButton getJButton1() {
        return  jButton1;
    }

    public CustomButton getjButton1() {
        return jButton1;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public JLabel getjLabel4() {
        return jLabel4;
    }

    public JLabel getjLabel5() {
        return jLabel5;
    }

    public JLabel getTableTitle() {
        return tableTitle;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public JPanel getBtnPanel() {
        return btnPanel;
    }

    public JPanel getInputArea() {
        return inputArea;
    }

    public JSeparator getjSeparator1() {
        return jSeparator1;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public CustomTextField getjTextField1() {
        return jTextField1;
    }

    public CustomTextField getjTextField2() {
        return jTextField2;
    }

    public CustomTextField getjTextField3() {
        return jTextField3;
    }

    public CustomTextField getjTextField4() {
        return jTextField4;
    }

    public CustomTextField getjTextField5() {
        return jTextField5;
    }

    public List<FormatDiscount> getFormatDiscountList() {
        return formatDiscountList;
    }

    public Object[][] getKmData() {
        return kmData;
    }
}
