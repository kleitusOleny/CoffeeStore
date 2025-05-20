package model;

import model.order_system.OrderSystem;
import model.customer_system.*;
import model.employee_system.*;
import model.reservation_system.*;
import model.customer_system.Observer;

import java.util.ArrayList;
import java.util.List;

public class MainSystem implements Subject,IModel{
    private List<Observer> listObsever;
    private EmployeeSystem empSys;
    private OrderSystem orderSystem;
    private ReservationSystem reservationSystem;
    private CustomerSystem customerSystem;
    
    public MainSystem(EmployeeSystem empSys, OrderSystem orderSystem, ReservationSystem reservationSystem, CustomerSystem customerSystem) {
        this.listObsever = new ArrayList<Observer>();
        this.empSys = empSys;
        this.orderSystem = orderSystem;
        this.reservationSystem = reservationSystem;
        this.customerSystem = customerSystem;
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
}
