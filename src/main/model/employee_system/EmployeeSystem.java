package model.employee_system;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSystem {
    private List<Employee> listEmp;
    private ManagerAttendent managerAttendent;
    
    public EmployeeSystem() {
        this.listEmp = new ArrayList<>();
        this.managerAttendent = new ManagerAttendent();
    }
    
    
    
    //Them nhan vien vao danh sach
    public void addEmployee(Employee e){
        listEmp.add(e);
    }
    
    //Xoa nhan vien khoi danh sach
    public void removeEmployee(Employee e){
        listEmp.remove(e);
    }
    
    public double stateSalary(){
        return 0.0;
    }
    
}
