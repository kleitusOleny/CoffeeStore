package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public static final String LOGIN = "login";
    public static final String EMPLOYEE = "employee";
    public static final String MANAGER = "manager";

    public MainFrame() {
        setTitle("Ứng dụng quản lý");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel(this);
        EmployeePanel managerPanel = new EmployeePanel();
        ManagerPanel managerPanel1 = new ManagerPanel();

        mainPanel.add(loginPanel, LOGIN);
        mainPanel.add(managerPanel, EMPLOYEE);
        mainPanel.add(managerPanel1, MANAGER);

        add(mainPanel);
        cardLayout.show(mainPanel, LOGIN);
        setVisible(true);
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
