package view;

import model.customer_system.Customer;

import model.reservation_system.Reservation;

import java.util.List;
import utils.LoginModel;


public interface IView {
    public IView getView();
    public void showPanel(String namePanel);

    void displayMessage(String s);

    void displayEmptyTables(List<Reservation> emptyTable);

    void displayReservedTables(List<Reservation> reservedTables);

    public LoginPanel getLoginPanel();
    void displayCustomer(Customer customer); // Hiển thị thông tin một khách hàng
    void displayCustomerList(List<Customer> customers); // Hiển thị danh sách khách hàng


}
