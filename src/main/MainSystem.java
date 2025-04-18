import model.employeeSystem.EmployeeSystem;

import java.util.List;

public class MainSystem implements Subject{
    private List<Obsever> listObsever;
    private EmployeeSystem empSys;
    private OrderSystem orderSystem;
    private ReservationSystem reservationSystem;
    private CustomerSystem customerSystem;
    
    public MainSystem(List<Obsever> listObsever, EmployeeSystem empSys, OrderSystem orderSystem, ReservationSystem reservationSystem, CustomerSystem customerSystem) {
        this.listObsever = listObsever;
        this.empSys = empSys;
        this.orderSystem = orderSystem;
        this.reservationSystem = reservationSystem;
        this.customerSystem = customerSystem;
    }
    
    
    @Override
    public void addObsever(Obsever o) {
    
    }
    
    @Override
    public void removeObsever(Obsever o) {
    
    }
}
