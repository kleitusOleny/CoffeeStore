package model.employeeSystem;

import java.time.LocalDate;

public class Attendent {
    private String employeeID;
    private LocalDate checkInTime;
    
    public Attendent(String employeeID, LocalDate checkInTime) {
        this.employeeID = employeeID;
        this.checkInTime = checkInTime;
    }
    
    public String getEmployeeID() {
        return employeeID;
    }
    
    public LocalDate getCheckInTime() {
        return checkInTime;
    }
}
