import model.OrderSystem;
import model.customerSystem.*;
import model.employeeSystem.*;
import model.reservationSystem.*;
import model.customerSystem.Observer;

import java.util.List;

public class MainSystem implements Subject{
    private List<Observer> listObsever;
    private EmployeeSystem empSys;
    private OrderSystem orderSystem;
    private ReservationSystem reservationSystem;
    private CustomerSystem customerSystem;
    
    public MainSystem(List<Observer> listObsever, EmployeeSystem empSys, OrderSystem orderSystem, ReservationSystem reservationSystem, CustomerSystem customerSystem) {
        this.listObsever = listObsever;
        this.empSys = empSys;
        this.orderSystem = orderSystem;
        this.reservationSystem = reservationSystem;
        this.customerSystem = customerSystem;
    }


    @Override
    public void addObsever(Observer o) {
        listObsever.add(o);
    }

    @Override
    public void removeObsever(Observer o) {
        listObsever.remove(o);
    }
}
