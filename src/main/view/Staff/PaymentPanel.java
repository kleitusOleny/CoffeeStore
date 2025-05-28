package view.Staff;

import controller.OrderController;
import controller.PaymentController;
import controller.ReservationController;
import data.ReadFileJson;
import data.dto.ClientDTO;
import data.dto.DiscountDTO;
import data.dto.PayDTO;
import model.customer_system.Customer;
import model.customer_system.CustomerSystem;
import model.order_system.*;
import model.reservation_system.ReservationStatus;
import model.reservation_system.ReservationSystem;
import utils.CustomerStatus;
import utils.OrderStatus;
import view.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class PaymentPanel extends JPanel implements Observer {
    private JLabel tenLabel = createBoldLabel("<<Unknown>>");
    private JLabel sdtLabel = createBoldLabel("<<Unknown>>");
    private JLabel trangThaiLabel = createBoldLabel("<<Unknown>>");
    private JLabel giamGiaLabel = createBoldLabel("<<Unknown>>");
    private JLabel tongTienLabel = createBoldLabel("0đ");
    private JLabel banLabel;
    

    private List<PayDTO> payDTOList = ReadFileJson.readFileJSONForPay();
    private List<ClientDTO> clientDTOList = ReadFileJson.readFileJSONForClient();
    private List<DiscountDTO> discountDTOList = ReadFileJson.readFileJSONForDiscount();
    Object[][] payData = ReadFileJson.getPayData();
    Object[][] clientData = ReadFileJson.getKhachData();
    Object[][] discountData = ReadFileJson.getKmData();
    
    public static double totalPrice;
    public static DefaultTableModel tableModel;
    private CustomerSystem customerModel;
    private OrderSystem orderModel;
    private ReservationSystem reservationModel;
    private PaymentController controller;
    private ReservationController reservationController;
    private OrderPanel orderPanel;
    
    private CustomButton historyButton;

    private CustomButton confirmBtn;
    private CustomButton invoiceBtn;
    private CustomCheckBox cash;
    private CustomCheckBox card;
    private CustomCheckBox bank;
    private CustomTable table;
    
    public PaymentPanel(CustomerSystem customerModel, OrderSystem orderModel, ReservationSystem reservationModel, OrderPanel orderPanel) {
        this.customerModel = customerModel;
        this.orderModel = orderModel;
        this.reservationModel = reservationModel;
        this.orderPanel = orderPanel;
        // Đăng ký làm Observer cho các mô hình
        customerModel.addObserver(this);
        orderModel.addObserver(this);
        reservationModel.addObserver(this);
        
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 204));
        
        add(createMenuBar(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
        
        this.controller = new PaymentController(this, customerModel, orderModel, reservationModel);
        updateView(); // Khởi tạo giao diện với dữ liệu ban đầu
    }
    
    public void updateView() {
        boolean customerFound = false;
        try {
            Map<String,List<Customer>> customerMap = customerModel.getListCus();
            List<Customer>customersList = customerMap.get(DiscountPanel.customer[3]);
            for (Customer customer : customersList) {
                if (customer.getNumsPhone().equals(DiscountPanel.customer[1])) {
                    customerFound = true;
                    tenLabel.setText(customer.getName());
                    sdtLabel.setText(customer.getNumsPhone());
                    trangThaiLabel.setText(customer.getType());
                    break;
                }
            }
            if (!customerFound) {
                tenLabel.setText("<<Unknown>>");
                sdtLabel.setText("<<Unknown>>");
                trangThaiLabel.setText("<<Unknown>>");
            }
        }catch (Exception e){
        
        }
        
        
        boolean discountFound = false;
        for (DiscountDTO discountDTO : discountDTOList) {
            if (discountDTO.isChon()) {
                giamGiaLabel.setText(discountDTO.getTenKM() + " (" + discountDTO.getNoiDung() + ")");
                discountFound = true;
                break;
            }
        }
        if (!discountFound) {
            giamGiaLabel.setText("<<Unknown>>");
        }
        try {
            boolean tableFound = false;
            if (Integer.parseInt(TablePanel.getTableNo()) > 0) {
                tableFound = true;
                banLabel.setText(TablePanel.getTableNo());
            }
            if (!tableFound) {
                banLabel.setText("<<Unknown>>");
            }
        }catch (Exception e) {
        }
        
        // 4. Lấy danh sách món và tổng tiền từ OrderSystem
        updateOrderTable();
    }
    
    // Cập nhật bảng món ăn và tổng tiền
    private void updateOrderTable() {
        tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        
        totalPrice = 0.0;
        if (!orderModel.getListStoreOrder().isEmpty()) {
            List<IProduct> products = orderModel.getListStoreOrder().get(0).getListP();
            for (IProduct product : products) {
                String toppingInfo = "";
                if (product instanceof BaseProduct) {
                    List<Topping> toppings = ((BaseProduct) product).getToppings();
                    if (!toppings.isEmpty()) {
                        StringBuilder toppingStr = new StringBuilder();
                        for (Topping topping : toppings) {
                            toppingStr.append(topping.getInformation())
                                    .append(" (")
                                    .append(String.format("%,.0f", topping.getPrice()))
                                    .append("đ), ");
                        }
                        toppingInfo = toppingStr.substring(0, toppingStr.length() - 2);
                    }
                }
                tableModel.addRow(new Object[]{
                        product.getInformation(),
                        product.getQuantity(),
                        String.format("%,.0f", product.getPrice()),
                        toppingInfo
                });
                totalPrice += product.getPrice() * product.getQuantity();
            }
        }
        
        // Cập nhật tổng tiền
        tongTienLabel.setText(String.format("%,.0f", totalPrice) + "đ");
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
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
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        for (DiscountDTO discountDTO : discountDTOList){
            boolean found = false;
            if (discountDTO.isChon()){
                giamGiaLabel = createBoldLabel(discountDTO.getTenKM() + "(" + discountDTO.getNoiDung() + ")");
                found = true;
            }
        }
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.setOpaque(false);
        row2.add(createBoldLabel("Thông tin bàn:"));

        banLabel = createBoldLabel("Chưa có");

        row2.add(banLabel);

        row2.add(Box.createHorizontalStrut(30));
        row2.add(createBoldLabel("Mã giảm giá:"));
        row2.add(giamGiaLabel);

        contentPanel.add(row2);
        contentPanel.add(Box.createVerticalStrut(15));

        // Bảng món ăn
        String[] headers = {"Tên món", "Số lượng", "Giá", "Topping (kèm giá)"};

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

        tongTienLabel = createBoldLabel("");
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

    public void onInvoiceButtonClicked() {
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
        OrderController.setCurrentOrder(new Order(null,null,null));
        this.updateOrderTable();
        orderPanel.refreshOrderItems();
    }
    
    
    public void onHistoryButtonClicked() {
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

    public JMenuBar createMenuBar() {
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
        

        ImageIcon icon = new ImageIcon("src\\main\\image\\history.png");
        Image avatarImg = icon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        historyButton.setIcon(new ImageIcon(avatarImg));
        
        // Thêm nút trực tiếp vào menu bar
        menuBar.add(historyButton);
        menuBar.add(Box.createHorizontalStrut(5));

        return menuBar;
    }
    
    
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof CustomerStatus) {
            CustomerStatus status = (CustomerStatus) arg;
            switch (status.getAction()) {
                case "ADD_CUSTOMER":
                case "UPDATE_CUSTOMER":
                case "REMOVE_CUSTOMER":
                    clientDTOList = ReadFileJson.readFileJSONForClient();
                    updateView();
                    break;
            }
        } else if (arg instanceof OrderStatus) {
            OrderStatus status = (OrderStatus) arg;
            switch (status.getAction()) {
                case "ADD_PRODUCT":
                case "REMOVE_PRODUCT":
                case "UPDATE_QUANTITY":
                    updateOrderTable();
                    break;
            }
        } else if (arg instanceof ReservationStatus) {
            ReservationStatus status = (ReservationStatus) arg;
            switch (status.getAction()) {
                case "ADD_RESERVATION":
                case "REMOVE_RESERVATION":
                case "UPDATE_TABLE_STATUS":
                    updateView();
                    break;
            }
        }
    }
}
