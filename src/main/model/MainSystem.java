package model;

import model.order_system.OrderSystem;
import model.customer_system.*;
import model.employee_system.*;
import model.reservation_system.*;
import model.customer_system.Observer;
import utils.LoginHandle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class MainSystem implements Subject,IModel{
    private LoginHandle loginModel;
    private List<Observer> listObsever;
    private EmployeeSystem empSys;
    private OrderSystem orderSystem;
    private ReservationSystem reservationSystem;
    private CustomerSystem customerSystem;
    private static final String FILE_PATH = "users.txt";
    public MainSystem(EmployeeSystem empSys, OrderSystem orderSystem, ReservationSystem reservationSystem, CustomerSystem customerSystem) {
        this.listObsever = new ArrayList<Observer>();
        this.empSys = empSys;
        this.orderSystem = orderSystem;
        this.reservationSystem = reservationSystem;
        this.customerSystem = customerSystem;
        this.loginModel = new LoginHandle();
    }

    public MainSystem() {
        this.listObsever = new ArrayList<Observer>();
        this.empSys = new EmployeeSystem();
        this.orderSystem = new OrderSystem();
        this.reservationSystem = new ReservationSystem();
        this.customerSystem = new CustomerSystem();
        this.loginModel = new LoginHandle();
    }

    @Override
    public void addObserver(Observer o) {
        listObsever.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        listObsever.remove(o);
    }
    
    public List<Observer> getListObsever() {
        return listObsever;
    }
    
    public void setListObsever(List<Observer> listObsever) {
        this.listObsever = listObsever;
    }


    public String validateUser(String userName, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String fileUser = parts[0].trim();
                    String filePass = parts[1].trim();
                    String role = parts[2].trim();
                    if (fileUser.equals(userName) && filePass.equals(password)) {
                        return role;// tra ve role employee or mananger
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;// sai username hoac password
    }

    
    public EmployeeSystem getEmployeeSystem() {
        return empSys;
    }
    
    public void setEmpSys(EmployeeSystem empSys) {
        this.empSys = empSys;
    }
    
    public OrderSystem getOrderSystem() {
        return orderSystem;
    }
    
    public void setOrderSystem(OrderSystem orderSystem) {
        this.orderSystem = orderSystem;
    }
    
    public ReservationSystem getReservationSystem() {
        return reservationSystem;
    }
    
    public void setReservationSystem(ReservationSystem reservationSystem) {
        this.reservationSystem = reservationSystem;
    }
    
    public CustomerSystem getCustomerSystem() {
        return customerSystem;
    }
    
    public void setCustomerSystem(CustomerSystem customerSystem) {
        this.customerSystem = customerSystem;

    }
    
    @Override
    public LoginHandle getLoginModel() {
        return loginModel;
    }
}
