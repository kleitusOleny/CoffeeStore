package view;

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

    public ManagerPanel() {
        setLayout(new BorderLayout());

        menuPanel = new ManagerMenuPanel();
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        // Thêm các panel con vào contentPanel
        contentPanel.add(new MenuEditorPanel(), QUAN_LY_MENU);
        contentPanel.add(new QuanLyNhanVien_dsnv(), QUAN_LY_NV);
        contentPanel.add(new PromotionManagement(), KHUYEN_MAI);
        contentPanel.add(new ThongKe(), THONG_KE);

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
        int confirmed = JOptionPane.showConfirmDialog(
                null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            // Nếu có thể, bạn nên gọi MainFrame.logOut() thay vì System.exit()
            System.exit(0);
        }
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
