package model;

import model.customer_system.CustomerSystem;
import model.employee_system.EmployeeSystem;
import model.order_system.OrderSystem;
import model.reservation_system.ReservationSystem;
import utils.LoginHandle;

import java.util.Observer;

public interface IModel {
    public void notifyObservers(Object arg);
    public void addObserver(Observer o);
}
