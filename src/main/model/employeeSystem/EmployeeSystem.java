package model.employeeSystem;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSystem {
    private List<Employee> listEmp;
    private ManagerAttendent managerAttendent;
    
    public EmployeeSystem( ManagerAttendent managerAttendent) {
        this.listEmp = new ArrayList<>();
        this.managerAttendent = managerAttendent;
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
