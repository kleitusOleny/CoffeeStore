package model.employeeSystem;

public class Seller extends WageEmployee{
    public Seller(String emp_no, String name, String numsPhone, String email, String address, Date dayOfBirth, int hours, double hourlyRate) {
        super(emp_no, name, numsPhone, email, address, dayOfBirth, hours, hourlyRate);
    }
    
    
    //Tính Lương
    public double totalSalary(){
        return hourlyRate*hours;
    }
    
    @Override
    public void setSalary(double salary) {
        this.hourlyRate = salary;
    }
}
