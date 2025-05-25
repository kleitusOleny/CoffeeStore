package controller;

import model.customer_system.*;
import view.IView;

import javax.security.auth.Subject;
import java.util.List;

public class CustomerController {
    private CustomerSystem customerSystem;
    private IView view;

    public CustomerController(CustomerSystem customerSystem, IView view) {
        this.customerSystem = customerSystem;
        this.view = view;
    }

    
}