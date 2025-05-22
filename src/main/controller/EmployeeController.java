package controller;

import model.employee_system.EmployeeSystem;
import view.IView;

public class EmployeeController{
    EmployeeSystem employeeSystem;
    IView view;
    public EmployeeController(EmployeeSystem employeeSystem, IView view) {
        this.employeeSystem = employeeSystem;
        this.view = view;
    }

}
