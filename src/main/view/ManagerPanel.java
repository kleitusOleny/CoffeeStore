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

        // Gán sự kiện cho các nút
        menuPanel.setProductCatalogBtListener(e -> {
            showPanel(QUAN_LY_MENU);
            setHover(QUAN_LY_MENU);
        });

        menuPanel.setNotificationBtListener(e -> {
            showPanel(QUAN_LY_NV);
            setHover(QUAN_LY_NV);
        });

        menuPanel.setPurchasedBtListener(e -> {
            showPanel(KHUYEN_MAI);
            setHover(KHUYEN_MAI);
        });

        menuPanel.setChangeInfoBtListener(e -> {
            showPanel(THONG_KE);
            setHover(THONG_KE);
        });

        menuPanel.setLogoutBtListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(
                    null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                // Ví dụ: chuyển về màn hình đăng nhập nếu bạn có lớp MainFrame
                // MainFrame.getInstance().showPanel(MainFrame.LOGIN);
                System.exit(0); // hoặc thoát tạm thời
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
