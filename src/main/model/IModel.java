package model;

import model.customer_system.CustomerSystem;
import model.employee_system.EmployeeSystem;
import model.order_system.OrderSystem;
import model.reservation_system.ReservationSystem;
import utils.LoginHandle;

public interface IModel {
    public CustomerSystem getCustomerSystem();
    public EmployeeSystem getEmployeeSystem();
    public OrderSystem getOrderSystem();
    public ReservationSystem getReservationSystem();
    
    public String validateUser(String userName,String password);
    public LoginHandle getLoginModel();
//    public double pay(double amount, Customer customer);
}
