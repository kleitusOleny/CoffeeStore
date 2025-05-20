package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PaymentPanel extends JPanel {

    private JLabel tenLabel;
    private JLabel sdtLabel;
    private JLabel trangThaiLabel;
    private JLabel banLabel;
    private JLabel giamGiaLabel;
    private JLabel tongTienLabel;

    private JButton historyButton;

    private CustomButton confirmBtn;
    private CustomButton invoiceBtn;
    private CustomCheckBox cash;
    private CustomCheckBox card;
    private CustomCheckBox bank;


    public PaymentPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 204)); // Màu nền

//        EmployeeMenuPanel menuPanel = new EmployeeMenuPanel(); // Menu bên trái
//        add(menuPanel, BorderLayout.WEST);

        add(createContentPanel(), BorderLayout.CENTER); // Nội dung chính
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(255, 245, 204));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Nút "Xem Lịch Sử Giao Dịch"
        JPanel topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topLeftPanel.setOpaque(false);

        historyButton = new JButton("Xem Lịch Sử Giao Dịch");
        historyButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        historyButton.setPreferredSize(new Dimension(200, 30));
        historyButton.setFocusPainted(false);
        historyButton.setBackground(new Color(255, 204, 153));
        historyButton.setForeground(Color.BLACK);
        topLeftPanel.add(historyButton);

        historyButton.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            TransactionHistoryDialog dialog = new TransactionHistoryDialog(parentFrame);
            dialog.setVisible(true);
        });

        contentPanel.add(topLeftPanel);
        contentPanel.add(Box.createVerticalStrut(10));

        JLabel titleLabel = new JLabel("Thông tin khách hàng");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Dòng 1: Thông tin cá nhân
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.setOpaque(false);
        row1.add(createBoldLabel("Tên:"));
        tenLabel = createBoldLabel("Nguyễn Văn A");
        row1.add(tenLabel);

        row1.add(Box.createHorizontalStrut(30));
        row1.add(createBoldLabel("SĐT:"));
        sdtLabel = createBoldLabel("0393445667");
        row1.add(sdtLabel);

        row1.add(Box.createHorizontalStrut(30));
        row1.add(createBoldLabel("Trạng Thái:"));
        trangThaiLabel = createBoldLabel("VIP");
        row1.add(trangThaiLabel);

        contentPanel.add(row1);

        // Dòng 2: Thông tin bàn và mã giảm giá
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.setOpaque(false);
        row2.add(createBoldLabel("Thông tin bàn:"));
        banLabel = createBoldLabel("Bàn 2");
        row2.add(banLabel);

        row2.add(Box.createHorizontalStrut(30));
        row2.add(createBoldLabel("Mã giảm giá:"));
        giamGiaLabel = createBoldLabel("50%");
        row2.add(giamGiaLabel);

        contentPanel.add(row2);
        contentPanel.add(Box.createVerticalStrut(15));

        // Bảng món ăn
        String[] headers = {"Tên món", "Số lượng", "Giá", "Topping (kèm giá)"};
        Object[][] data = {
                {"Cà phê sữa", 1, "25.000đ", "Không có"},
                {"Trà đào", 2, "20.000đ", "Đào (2) - 5.000đ"}
        };

        JTable table = new JTable(new DefaultTableModel(data, headers)) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Font tableFont = new Font("SansSerif", Font.PLAIN, 18);
        Font headerFont = new Font("SansSerif", Font.BOLD, 20);

        table.setFont(tableFont);
        table.setRowHeight(35);
        table.getTableHeader().setFont(headerFont);
        table.getTableHeader().setPreferredSize(new Dimension(100, 35));
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

        confirmBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Thanh toán thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE
            );

            Container content = (Container) confirmBtn.getParent().getParent();
            content.removeAll();
            content.setBackground(new Color(255, 248, 220)); // màu nền gốc
            content.revalidate();
            content.repaint();
        });

        invoiceBtn = new CustomButton("In hóa đơn");
        invoiceBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        invoiceBtn.setPreferredSize(new Dimension(250, 45));
        invoiceBtn.setBackgroundColor(new Color(255, 255, 255));
        invoiceBtn.setHoverColor(new Color(254, 243, 226));
        invoiceBtn.setGradientColors(Color.YELLOW, new Color(166, 123, 91));
        invoiceBtn.setTextColor(Color.BLACK);
        invoiceBtn.setBorderRadius(20);
        invoiceBtn.setFocusPainted(false);

        invoiceBtn.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            InvoiceDialog dialog = new InvoiceDialog(parentFrame);
            dialog.setVisible(true);
        });

        buttonPanel.add(confirmBtn);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(invoiceBtn);
        contentPanel.add(buttonPanel);

        return contentPanel;
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
