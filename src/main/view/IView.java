package view;

import model.customer_system.Customer;

import model.reservation_system.Reservation;

import java.util.List;

import model.reservation_system.Table;


public interface IView {
    public IView getView();
    public void showPanel(String namePanel);

    void displayMessage(String s);

    void displayEmptyTables(List<Table> emptyTable);

    void displayReservedTables(List<Reservation> reservedTables);

    public LoginPanel getLoginPanel();
    void displayCustomer(Customer customer); // Hiển thị thông tin một khách hàng
    void displayCustomerList(List<Customer> customers); // Hiển thị danh sách khách hàng


    void updatetableStatus(int tableId, String newStatus);
}
