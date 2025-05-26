package view.Staff;

import model.customer_system.CustomerSystem;
import model.order_system.OrderSystem;
import model.reservation_system.ReservationSystem;
import view.EmployeeMenuPanel;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends JPanel {
    public static final String DAT_MON = "PRODUCT";
    public static final String DAT_BAN = "TABLE";
    public static final String KHUYEN_MAI = "DISCOUNT";
    public static final String THANH_TOAN = "PAYMENT";

    private EmployeeMenuPanel menuPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private MainFrame mainFrame;
    private OrderSystem orderSystem = new OrderSystem();
    private CustomerSystem customerSystem = new CustomerSystem();
    private ReservationSystem reservationSystem = new ReservationSystem();
    private PaymentPanel paymentPanel = new PaymentPanel(customerSystem,orderSystem, reservationSystem);
    private DiscountPanel discountPanel = new DiscountPanel(new CustomerSystem());
    public EmployeePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        menuPanel = new EmployeeMenuPanel();
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        // Thêm các panel con vào CardLayout
        contentPanel.add(new OrderPanel(orderSystem), DAT_MON);
        contentPanel.add(new TablePanel(new ReservationSystem()), DAT_BAN);
        contentPanel.add(discountPanel, KHUYEN_MAI);
        contentPanel.add(paymentPanel, THANH_TOAN);

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Gán sự kiện các nút
        menuPanel.setProductCatalogBtListener(e -> {
            showPanel(DAT_MON);
            setHover(DAT_MON);
        });

        menuPanel.setPurchasedBtListener(e -> {
            contentPanel.remove(discountPanel);
            discountPanel = new DiscountPanel(new CustomerSystem());
            contentPanel.add(discountPanel, KHUYEN_MAI);
            showPanel(KHUYEN_MAI);
            setHover(KHUYEN_MAI);
        });

        menuPanel.setNotificationBtListener(e -> {
            
            showPanel(DAT_BAN);
            setHover(DAT_BAN);
        });

        menuPanel.setChangeInfoBtListener(e -> {
            showPanel(THANH_TOAN);
            setHover(THANH_TOAN);
            contentPanel.remove(paymentPanel);
            paymentPanel = new PaymentPanel(customerSystem,orderSystem,reservationSystem); // Tạo lại PaymentPanel
            contentPanel.add(paymentPanel, THANH_TOAN);
            showPanel(THANH_TOAN);
            setHover(THANH_TOAN);
        });

        menuPanel.setLogoutBtListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(
                    null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                mainFrame.showPanel(MainFrame.LOGIN); // Quay lại màn hình login
                mainFrame.getLoginPanel().getPassField().setText("");
                mainFrame.getLoginPanel().getUserField().setText("");
            }
        });
    }

    public void showPanel(String name) {
        cardLayout.show(contentPanel, name);
    }

    private void setHover(String panelName) {
        JButton[] buttons = {
                menuPanel.datMon,
                menuPanel.datBan,
                menuPanel.apDungKM,
                menuPanel.thanhToan
        };
        String[] names = {
                DAT_MON,
                DAT_BAN,
                KHUYEN_MAI,
                THANH_TOAN
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(panelName.equals(names[i]) ? Color.CYAN : null);
        }
    }
}
