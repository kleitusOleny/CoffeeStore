package model.employee_system;

public class Manager extends Employee{
    private double salary;//Lương cố đính của quản lí
    
    public Manager(String emp_no, String name, String numsPhone, String email, String address, Date dayOfBirth, double salary) {
        super(emp_no, name, numsPhone, email, address, dayOfBirth);
        this.salary = salary;
    }
    
    //trả về lương theo giờ của quản lí
    public double totalSalary(){
        return this.salary;
    }
    
    //thay đổi lương của quản lí
    @Override
    public void setSalary(double salary){
        this.salary = salary;
    }
    
    //Kiểm tra xem có phải quản lí không
    @Override
    public boolean isManager(){
        return true;
    }
}
