package controller;

import model.employee_system.EmployeeSystem;
import view.IView;
import view.Manager.EmployeeManagement;

public class EmployeeController{
    EmployeeSystem employeeSystem;
    EmployeeManagement view;
    public EmployeeController(EmployeeSystem employeeSystem, EmployeeManagement view) {
        this.employeeSystem = employeeSystem;
        this.view = view;
    }

    
}
