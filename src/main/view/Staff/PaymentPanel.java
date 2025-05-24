package view.Staff;

import data.ReadFileJson;
import data.dto.FormatClient;
import data.dto.FormatDiscount;
import data.dto.FormatPay;
import view.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaymentPanel extends JPanel {

    public JLabel tenLabel = createBoldLabel("Cần cập nhật");
    public JLabel sdtLabel = createBoldLabel("Cần cập nhật");
    public JLabel trangThaiLabel = createBoldLabel("Cần cập nhật");
    private JLabel banLabel;
    private JLabel giamGiaLabel = createBoldLabel("Cần cập nhật");
    private JLabel tongTienLabel;

    private JPanel row1, row2;
    private JPanel contentPanel;

    private List<FormatPay> formatPayList = ReadFileJson.readFileJSONForPay();
    private List<FormatClient> formatClientList = ReadFileJson.readFileJSONForClient();
    private List<FormatDiscount> formatDiscountList = ReadFileJson.readFileJSONForDiscount();
    Object[][] payData = ReadFileJson.getPayData();
    Object[][] clientData = ReadFileJson.getKhachData();
    Object[][] discountData = ReadFileJson.getKmData();

    private CustomButton historyButton;
    private CustomButton updateButton;

    private CustomButton confirmBtn;
    private CustomButton invoiceBtn;
    private CustomCheckBox cash;
    private CustomCheckBox card;
    private CustomCheckBox bank;

    private CustomTable table;

    public PaymentPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 204));

        add(createMenuBar(), BorderLayout.NORTH); // thêm button vào menu bar ở trên
        add(createContentPanel(), BorderLayout.CENTER); // nội dung chính
    }

    private JPanel createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(255, 245, 204));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        contentPanel.add(Box.createVerticalStrut(10));

        JLabel titleLabel = new JLabel("Thông tin khách hàng");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Dòng 1: Thông tin cá nhân
        row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.setOpaque(false);
        row1.add(createBoldLabel("Tên:"));
        row1.add(tenLabel);

        row1.add(Box.createHorizontalStrut(30));
        row1.add(createBoldLabel("SĐT:"));
        row1.add(sdtLabel);

        row1.add(Box.createHorizontalStrut(30));
        row1.add(createBoldLabel("Trạng Thái:"));
        row1.add(trangThaiLabel);
        contentPanel.add(row1);

        // Dòng 2: Thông tin bàn và mã giảm giá
        row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.setOpaque(false);
        row2.add(createBoldLabel("Thông tin bàn:"));
        banLabel = createBoldLabel("Bàn 2");
        row2.add(banLabel);

        row2.add(Box.createHorizontalStrut(30));
        row2.add(createBoldLabel("Mã giảm giá:"));
        row2.add(giamGiaLabel);

        contentPanel.add(row2);
        contentPanel.add(Box.createVerticalStrut(15));

        // Bảng món ăn
        String[] headers = {"Tên món", "Số lượng", "Giá", "Topping (kèm giá)"};
//        Object[][] data = {
//                {"Cà phê sữa", 1, "25.000đ", "Không có"},
//                {"Trà đào", 2, "20.000đ", "Đào (2) - 5.000đ"}
//        };

        table = new CustomTable();  // Sử dụng CustomTable thay vì JTable
        DefaultTableModel model = new DefaultTableModel(payData, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
        Font tableFont = new Font("Roboto", Font.PLAIN, 18);
        Font headerFont = new Font("Roboto", Font.BOLD, 20);

        table.setFont(tableFont);
        table.setRowHeight(35);
        table.getTableHeader().setFont(headerFont);
        table.getTableHeader().setPreferredSize(new Dimension(100, 35));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(new Color(255, 224, 178));

        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(800, 250));
        tableScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        contentPanel.add(tableScroll);
        contentPanel.add(Box.createVerticalStrut(10));

        // Tổng tiền
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        totalPanel.setOpaque(false);
        JLabel tongLabel = new JLabel("Thành tiền:");
        tongLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalPanel.add(tongLabel);

        tongTienLabel = createBoldLabel("75.000đ");
        tongTienLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalPanel.add(tongTienLabel);
        contentPanel.add(totalPanel);

        // Nhãn phương thức thanh toán
        contentPanel.add(Box.createVerticalStrut(10));
        JPanel paymentLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paymentLabelPanel.setOpaque(false);
        JLabel paymentLabel = new JLabel("Chọn phương thức thanh toán:");
        paymentLabel.setFont(new Font("SansSerif", Font.PLAIN, 17));
        paymentLabelPanel.add(paymentLabel);
        contentPanel.add(paymentLabelPanel);

        // Checkboxes
        JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkBoxPanel.setOpaque(false);

        Font checkFont = new Font("SansSerif", Font.PLAIN, 16);
        Dimension checkSize = new Dimension(150, 30);

        cash = new CustomCheckBox("Tiền mặt");
        cash.setFont(checkFont);
        cash.setPreferredSize(checkSize);
        cash.setCheckColor(new Color(255, 167, 38));
        cash.setHoverColor(new Color(255, 224, 178));

        card = new CustomCheckBox("Thẻ tín dụng");
        card.setFont(checkFont);
        card.setPreferredSize(checkSize);
        card.setCheckColor(new Color(255, 167, 38));
        card.setHoverColor(new Color(255, 224, 178));

        bank = new CustomCheckBox("Chuyển khoản");
        bank.setFont(checkFont);
        bank.setPreferredSize(checkSize);
        bank.setCheckColor(new Color(255, 167, 38));
        bank.setHoverColor(new Color(255, 224, 178));

        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cash);
        paymentGroup.add(card);
        paymentGroup.add(bank);

        checkBoxPanel.add(cash);
        checkBoxPanel.add(card);
        checkBoxPanel.add(bank);
        contentPanel.add(checkBoxPanel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        confirmBtn = new CustomButton("XÁC NHẬN THANH TOÁN");
        confirmBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        confirmBtn.setPreferredSize(new Dimension(250, 45));
        confirmBtn.setBackgroundColor(new Color(236, 177, 118));
        confirmBtn.setHoverColor(new Color(254, 243, 226));
        confirmBtn.setTextColor(Color.BLACK);
        confirmBtn.setBorderRadius(20);
        confirmBtn.setFocusPainted(false);
        buttonPanel.add(confirmBtn);
        confirmBtn.addActionListener(e -> onConfirmButtonClicked());


        invoiceBtn = new CustomButton("In hóa đơn");
        invoiceBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        invoiceBtn.setPreferredSize(new Dimension(250, 45));
        invoiceBtn.setBackgroundColor(new Color(255, 255, 255));
        invoiceBtn.setHoverColor(new Color(254, 243, 226));
        invoiceBtn.setGradientColors(Color.YELLOW, new Color(166, 123, 91));
        invoiceBtn.setTextColor(Color.BLACK);
        invoiceBtn.setBorderRadius(20);
        invoiceBtn.setFocusPainted(false);

        invoiceBtn.addActionListener(e -> onInvoiceButtonClicked());


        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(invoiceBtn);
        contentPanel.add(buttonPanel);

        return contentPanel;
    }

    private void refreshClientInfoPanel() {
        boolean foundTickedClient = false;
        boolean foundDiscount = false;
        for (FormatClient formatClient : formatClientList){
            if (formatClient.isChon()) {
                tenLabel.setText(formatClient.getHoTen());
                sdtLabel.setText(formatClient.getSoDienThoai());
                trangThaiLabel.setText(formatClient.getTrangThai());
                foundTickedClient = true;
                break;
            }
        }
        if (!foundTickedClient) {
            tenLabel.setText("Unknown");
            sdtLabel.setText("Unknown");
            trangThaiLabel.setText("Unknown");
        }

        for (FormatDiscount formatDiscount : formatDiscountList){
            if (formatDiscount.isChon()) {
                giamGiaLabel.setText(formatDiscount.getTenKM() + "(" + formatDiscount.getNoiDung() + ")");
                foundDiscount = true;
                break;
            }
        }
        if (!foundDiscount){
            giamGiaLabel.setText("Không giảm giá");
        }
        contentPanel.revalidate();
        contentPanel.repaint();
        System.out.println(giamGiaLabel.getText());
    }

    private void onInvoiceButtonClicked() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        InvoiceDialog dialog = new InvoiceDialog(parentFrame);
        dialog.setVisible(true);
    }

    private void onConfirmButtonClicked() {
        JOptionPane.showMessageDialog(
                this,
                "Thanh toán thành công!",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE
        );

        JPanel contentPanel1 = (JPanel) confirmBtn.getParent().getParent();

        Component[] components = contentPanel1.getComponents();
        JPanel topLeftPanel1 = null;
        for (Component c : components) {
            if (c instanceof JPanel && ((JPanel) c).getComponentCount() > 0) {
                Component first = ((JPanel) c).getComponent(0);
                if (first == historyButton) {
                    topLeftPanel1 = (JPanel) c;
                    break;
                }
            }
        }

        contentPanel1.removeAll();
        if (topLeftPanel1 != null) {
            contentPanel1.add(topLeftPanel1);
            contentPanel1.add(Box.createVerticalStrut(20));
        }

        contentPanel1.setBackground(new Color(255, 248, 220));
        contentPanel1.revalidate();
        contentPanel1.repaint();
    }

    private void onHistoryButtonClicked() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        TransactionHistoryDialog dialog = new TransactionHistoryDialog(parentFrame);
        dialog.setVisible(true);
    }

    private JLabel createBoldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        Dimension size = new Dimension(120, 25);
        label.setPreferredSize(size);
        label.setMaximumSize(size);
        label.setMinimumSize(size);
        return label;
    }

    public JLabel getTenLabel() {
        return tenLabel;
    }

    public JLabel getSdtLabel() {
        return sdtLabel;
    }

    public JLabel getTrangThaiLabel() {
        return trangThaiLabel;
    }

    public JLabel getBanLabel() {
        return banLabel;
    }

    public JLabel getGiamGiaLabel() {
        return giamGiaLabel;
    }

    public JLabel getTongTienLabel() {
        return tongTienLabel;
    }

    public JButton getHistoryButton() {
        return historyButton;
    }

    public CustomButton getConfirmBtn() {
        return confirmBtn;
    }

    public CustomButton getInvoiceBtn() {
        return invoiceBtn;
    }

    public CustomCheckBox getCash() {
        return cash;
    }

    public CustomCheckBox getCard() {
        return card;
    }

    public CustomCheckBox getBank() {
        return bank;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(255, 224, 178));
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        historyButton = new CustomButton("Lịch Sử Giao Dịch");
        historyButton.setFont(new Font("Roboto", Font.BOLD, 14));
        historyButton.setPreferredSize(new Dimension(180, 30));
        historyButton.setFocusPainted(false);
        historyButton.setBackgroundColor(new Color(166, 123, 91));
        historyButton.setTextColor(Color.white);
        historyButton.setBorderRadius(20);

        updateButton = new CustomButton("Cập nhật");
        updateButton.setFont(new Font("Roboto", Font.BOLD, 14));
        updateButton.setPreferredSize(new Dimension(180, 30));
        updateButton.setFocusPainted(false);
        updateButton.setBackgroundColor(new Color(166, 123, 91));
        updateButton.setTextColor(Color.white);
        updateButton.setBorderRadius(20);

        ImageIcon icon = new ImageIcon("src\\main\\image\\history.png");
        Image avatarImg = icon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        historyButton.setIcon(new ImageIcon(avatarImg));

        historyButton.addActionListener(e -> onHistoryButtonClicked());
        updateButton.addActionListener(e -> refreshClientInfoPanel());
        // Thêm nút trực tiếp vào menu bar
        menuBar.add(historyButton);
        menuBar.add(Box.createHorizontalStrut(5));
        menuBar.add(updateButton);

        return menuBar;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thanh Toán");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1100, 650);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new PaymentPanel());
            frame.setVisible(true);
        });
    }
}
