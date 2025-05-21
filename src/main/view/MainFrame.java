package view;

import controller.IController;
import controller.MainController;
import model.MainSystem;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements IView{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    
    public static final String LOGIN = "login";
    public static final String EMPLOYEE = "employee";
    public static final String MANAGER = "manager";

    public MainFrame() {
        MainSystem mainSystem = new MainSystem();

        setTitle("Ứng dụng quản lý");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000,700));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this);
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
    
    @Override
    public IView getView() {
        return this;
    }
    
    public LoginPanel getLoginPanel() {
        return loginPanel;
    }
}
