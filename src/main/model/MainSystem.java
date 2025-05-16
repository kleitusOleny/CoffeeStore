package model;

import model.orderSystem.OrderSystem;
import model.customerSystem.*;
import model.employeeSystem.*;
import model.reservationSystem.*;
import model.customerSystem.Observer;

import java.util.ArrayList;
import java.util.List;

public class MainSystem implements Subject{
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

}
