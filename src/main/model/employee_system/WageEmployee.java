package model.employee_system;

public abstract class WageEmployee extends Employee{
    protected int hours;//So gio lam viec
    protected double hourlyRate;//Lương tính theo từng giờ
    
    public WageEmployee(String emp_no, String name, String numsPhone, String email, String address, Date dayOfBirth, int hours, double hourlyRate) {
        super(emp_no, name, numsPhone, email, address, dayOfBirth);
        this.hours = hours;
        this.hourlyRate = hourlyRate;
    }
    
    //đặt lại số giờ làm việc
    public void resetHours(){
        this.hours = 0;
    }
    
    @Override
    public abstract double totalSalary();
    
    @Override
    public abstract void setSalary(double salary);
    
    @Override
    public boolean isManager() {
        return false;
    }
}
