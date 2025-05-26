package model.employee_system;

import model.Date;

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

    public String getEmp_no() {
        return emp_no;
    }

    public String getName() {
        return name;
    }

    public String getNumsPhone() {
        return numsPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    //Tính Lương cho nhân viên
    public abstract double totalSalary();
    
    //Thay đổi lương cho nhân viên
    public abstract void setSalary(double salary);
    
    public abstract boolean isManager();
    
    @Override
    public String toString() {
        return "Employee{" +
                "emp_no='" + emp_no + '\'' +
                ", name='" + name + '\'' +
                ", numsPhone='" + numsPhone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", dayOfBirth=" + dayOfBirth +
                '}';
    }
}
