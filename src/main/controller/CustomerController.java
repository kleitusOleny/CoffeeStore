package controller;

import model.customer_system.CustomerSystem;
import view.IView;

public class CustomerController {
    CustomerSystem customerSystem;
    IView view;
    public CustomerController(CustomerSystem customerSystem, IView view) {
        this.customerSystem = customerSystem;
        this.view = view;
    }
    
}
