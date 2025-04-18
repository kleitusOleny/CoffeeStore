package model.employeeSystem;

public abstract class Employee {
    protected String emp_no;
    protected String name;
    protected String numsPhone;
    protected String email;
    protected String address;
    protected Date dayOfBirth;
    
    public Employee(String emp_no, String name, String numsPhone, String email, String address, Date dayOfBirth) {
        this.emp_no = emp_no;
        this.name = name;
        this.numsPhone = numsPhone;
        this.email = email;
        this.address = address;
        this.dayOfBirth = dayOfBirth;
    }
    
    //Tính Lương cho nhân viên
    public abstract double totalSalary();
    
    //Thay đổi lương cho nhân viên
    public abstract void setSalary(double salary);
    
    public abstract boolean isManager();
}
