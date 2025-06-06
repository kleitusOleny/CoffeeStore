package view;

import model.employee_system.EmployeeSystem;
import view.Manager.EmployeeManagement;
import view.Manager.MenuEditorPanel;
import view.Manager.PromotionManagement;
import view.Manager.Statistical;
import view.Staff.PaymentPanel;

import javax.swing.*;
import java.awt.*;

public class ManagerPanel extends JPanel {
    public static final String QUAN_LY_MENU = "MENU";
    public static final String QUAN_LY_NV = "EMPLOYEE";
    public static final String KHUYEN_MAI = "DISCOUNT";
    public static final String THONG_KE = "STATISTICS";

    private ManagerMenuPanel menuPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private MainFrame mainFrame;
    private EmployeeSystem employeeSystem = new EmployeeSystem();
    private MenuEditorPanel menuEditorPanel = new MenuEditorPanel();
    public ManagerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        menuPanel = new ManagerMenuPanel();
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        // Thêm các panel con vào contentPanel
        contentPanel.add(menuEditorPanel, QUAN_LY_MENU);
        contentPanel.add(new EmployeeManagement(employeeSystem), QUAN_LY_NV);
        contentPanel.add(new PromotionManagement(), KHUYEN_MAI);
        contentPanel.add(new Statistical(), THONG_KE);

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Gán sự kiện bằng phương thức riêng
        initListeners();
    }

    private void initListeners() {
        menuPanel.setProductCatalogBtListener(e -> handleShowMenuPanel());
        menuPanel.setNotificationBtListener(e -> handleShowEmployeePanel());
        menuPanel.setPurchasedBtListener(e -> handleShowDiscountPanel());
        menuPanel.setChangeInfoBtListener(e -> handleShowStatisticsPanel());
        menuPanel.setLogoutBtListener(e -> handleLogout());
    }

    private void handleShowMenuPanel() {
        contentPanel.remove(menuEditorPanel);
        menuEditorPanel = new MenuEditorPanel(); // Tạo lại PaymentPanel
        contentPanel.add(menuEditorPanel, QUAN_LY_MENU);
        showPanel(QUAN_LY_MENU);
        setHover(QUAN_LY_MENU);
    }

    private void handleShowEmployeePanel() {
        showPanel(QUAN_LY_NV);
        setHover(QUAN_LY_NV);
    }

    private void handleShowDiscountPanel() {
        showPanel(KHUYEN_MAI);
        setHover(KHUYEN_MAI);
    }

    private void handleShowStatisticsPanel() {
        showPanel(THONG_KE);
        setHover(THONG_KE);
    }

    private void handleLogout() {

        menuPanel.setLogoutBtListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(
                    null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                mainFrame.showPanel(MainFrame.LOGIN); // Quay lại màn hình login
                mainFrame.getLoginPanel().getUserField().setText("");
                mainFrame.getLoginPanel().getPassField().setText("");
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
                QUAN_LY_MENU,
                QUAN_LY_NV,
                KHUYEN_MAI,
                THONG_KE
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(panelName.equals(names[i]) ? Color.CYAN : null);
        }
    }
}
