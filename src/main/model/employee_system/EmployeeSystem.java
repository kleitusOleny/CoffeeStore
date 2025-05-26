package model.employee_system;

import model.IModel;
import utils.LoadDataToModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class EmployeeSystem extends Observable implements IModel {
    private List<Employee> listEmp;
    private ManagerAttendent managerAttendent;
    
    public EmployeeSystem() {
        LoadDataToModel.LoadEmployeeDataToModel();
        this.listEmp = LoadDataToModel.getEmployees();
        this.managerAttendent = new ManagerAttendent();
    }

    public ManagerAttendent getManagerAttendent() {
        return managerAttendent;
    }

    public List<Employee> getListEmp() {
        return listEmp;
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
