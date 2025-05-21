package view;

import controller.IController;
import controller.MainController;
import model.MainSystem;
import model.reservation_system.Reservation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame implements IView{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private IView view;
    private  IController controller;
    public static final String LOGIN = "login";
    public static final String EMPLOYEE = "employee";
    public static final String MANAGER = "manager";

    public MainFrame() {
        MainSystem mainSystem = new MainSystem();
        view = this;
        controller = new MainController(mainSystem, view);
        setTitle("Ứng dụng quản lý");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000,700));
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

    @Override
    public void displayMessage(String s) {
        JOptionPane.showMessageDialog(this,s,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void displayEmptyTables(List<Reservation> emptyTable) {
        if (emptyTable.isEmpty() || emptyTable == null){
            displayMessage("Không có bàn trống.");
            return;
        }
        StringBuilder sb = new StringBuilder("Danh sách bàn trống:\n");
        for (Reservation r : emptyTable){
            sb.append("Bàn số: ").append(r.getTable().getIdTable())
                    .append(" | Sức chứa: ").append(r.getTable().getCapacity())
                    .append("\n");
        }
        showTextDialog("Bàn trống", sb.toString());
    }

    @Override
    public void displayReservedTables(List<Reservation> reservedTables) {
        if (reservedTables == null || reservedTables.isEmpty()) {
            displayMessage("Không có bàn đã đặt.");
            return;
        }

        StringBuilder sb = new StringBuilder("Danh sách bàn đã được đặt:\n");
        for (Reservation r : reservedTables) {
            sb.append("Bàn số: ").append(r.getTable().getIdTable()).append("\n");
        }

        showTextDialog("Bàn đã đặt", sb.toString());
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
    
    @Override
    public IView getView() {
        return this;
    }
    public void logOut() {
        showPanel(LOGIN);
    }
    public void showTextDialog(String title, String content) {
        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
