package controller;


import model.IModel;
import view.IView;

public class MainController implements IController{
    IModel model;
    IView view;
    
    private CustomerController customerController;
    private EmployeeController employeeController;
    private ReservationController reservationController;
    private OrderController orderController;
    private LoginController loginController;
    
    public MainController(IModel model, IView view) {
        customerController = new CustomerController(model.getCustomerSystem(),view.getView());
        employeeController = new EmployeeController(model.getEmployeeSystem(),view.getView());
        reservationController = new ReservationController(model.getReservationSystem(),view.getView());
        orderController = new OrderController(model.getOrderSystem(),view.getView());
//        loginController = new LoginController(view,model);
    }
    
}
