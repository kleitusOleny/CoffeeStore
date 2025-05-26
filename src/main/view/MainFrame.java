package view;

import controller.IController;
import controller.MainController;
import model.MainSystem;
import model.customer_system.Customer;
import model.reservation_system.Reservation;
import model.reservation_system.Table;
import view.Staff.EmployeePanel;
import view.Staff.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame implements IView{
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private IView view;
    private  IController controller;
    private LoginPanel loginPanel;
  
    public static final String LOGIN = "login";
    public static final String EMPLOYEE = "employee";
    public static final String MANAGER = "manager";

    public MainFrame() {
        MainSystem mainSystem = new MainSystem();
        view = this;
        controller = new MainController(mainSystem, view);
        setTitle("Hệ Thống Quản Lý Quán Cà Phê");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000,700));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this);
        EmployeePanel managerPanel = new EmployeePanel(this);
        ManagerPanel managerPanel1 = new ManagerPanel(this);

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
    public void displayEmptyTables(List<Table> emptyTable) {
        if (emptyTable.isEmpty() || emptyTable == null){
            displayMessage("Không có bàn trống.");
            return;
        }
        StringBuilder sb = new StringBuilder("Danh sách bàn trống:\n");
        for (Table r : emptyTable){
            sb.append("Bàn số: ").append(r.getIdTable())

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
    
    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    @Override
    public void displayCustomer(Customer customer) {
        if (customer == null) {
            displayMessage("Không tìm thấy khách hàng.");
            return;
        }
        StringBuilder sb = new StringBuilder("Thông tin khách hàng:\n");
        sb.append("Tên: ").append(customer.getName()).append("\n")
                .append("Số điện thoại: ").append(customer.getNumsPhone()).append("\n")
                .append("Loại: ").append(customer.getType()).append("\n")
                .append("Đăng ký nhận thông báo: ").append(customer.isRegister() ? "Có" : "Không").append("\n");
        if (customer.isVIP()) {
            sb.append("Điểm tích lũy: ").append(customer.pointAccumulated()).append("\n");
        }
        showTextDialog("Thông tin khách hàng", sb.toString());
    }


    @Override
    public void displayCustomerList(List<Customer> customers) {
        if (customers == null || customers.isEmpty()) {
            displayMessage("Không có khách hàng trong danh sách.");
            return;
        }
        StringBuilder sb = new StringBuilder("Danh sách khách hàng:\n");
        for (Customer customer : customers) {
            sb.append("Tên: ").append(customer.getName())
                    .append(", SĐT: ").append(customer.getNumsPhone())
                    .append(", Loại: ").append(customer.getType())
                    .append(", Đăng ký: ").append(customer.isRegister() ? "Có" : "Không");
            if (customer.isVIP()) {
                sb.append(", Điểm: ").append(customer.pointAccumulated());
            }
            sb.append("\n");
        }
        showTextDialog("Danh sách khách hàng", sb.toString());
    }

    @Override
    public void updatetableStatus(int tableId, String newStatus) {
        TablePanel tablePanel = (TablePanel) mainPanel.getComponent(tableId);
        tablePanel.updatetableStatus(tableId,newStatus);

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
