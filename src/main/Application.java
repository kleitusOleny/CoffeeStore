import controller.IController;
import controller.MainController;
import model.IModel;
import model.MainSystem;
import model.customer_system.CustomerSystem;
import model.employee_system.EmployeeSystem;
import model.employee_system.ManagerAttendent;
import model.order_system.OrderSystem;
import model.reservation_system.ReservationSystem;
import view.IView;
import view.MainFrame;

public class Application {
    public static void execute() {
        EmployeeSystem employeeSystem = new EmployeeSystem(new ManagerAttendent());
        OrderSystem orderSystem = new OrderSystem();
        CustomerSystem customerSystem = new CustomerSystem();
        ReservationSystem reservationSystem = new ReservationSystem();
        
        IModel model = new MainSystem(employeeSystem,orderSystem,reservationSystem,customerSystem);
        IView view = new MainFrame();
        IController controller = new MainController(model,view);
        controller.run();
    }
}
