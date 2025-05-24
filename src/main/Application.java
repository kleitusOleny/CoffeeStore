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
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN

    public static void execute() {
        EmployeeSystem employeeSystem = new EmployeeSystem();
        OrderSystem orderSystem = new OrderSystem();
        CustomerSystem customerSystem = new CustomerSystem();
        ReservationSystem reservationSystem = new ReservationSystem();
        
        IModel model = new MainSystem(employeeSystem,orderSystem,reservationSystem,customerSystem);
        IView view = new MainFrame();
        IController controller = new MainController(model,view);
        controller.run();
    }

    public static void warningBegin(){
        System.out.println(RED_BRIGHT + "APP ĐƯỢC SỬ DỤNG DATA HOÀN TOÀN TỪ GSON LIBRARY (JSON), PLEASE ĐỪNG CÓ THẢ CÁI FILE TXT NÀO VÌ NÓ MÉO WORK ĐÂU\nhttps://google.github.io/gson/" + GREEN_BRIGHT);
        System.out.println("Đây là danh sách tài khoản và mật khẩu để test app: " + CYAN_BRIGHT);
        System.out.println("Role: Seller/Staff, Username: nhanvien, Password: 1234");
        System.out.println("Role: Manager/Owner, Username: quanly, Password: 123");
    }
}
