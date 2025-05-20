package model.employee_system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerAttendent {
    private List<Attendent> list;
    
    public ManagerAttendent() {
        this.list = new ArrayList<>();
    }
    
    //diem danh
    public void checkIn(Employee e){
        if (list.contains(e)) {
            System.out.println("Checked");
        } else {
            System.out.println("Not checked");
        }
    }
    
    //kiem tra nhan vien co diem danh chua
    private boolean hasCheckedInToday(String employeeID) {
        try {
            LocalDate dateNow = LocalDate.now();
            if (list.isEmpty()) {
                return false;
            } else {
                for (Attendent attendent : list) {
                    if (employeeID.equals(attendent.getEmployeeID())) {
                        LocalDate checkInDate = attendent.getCheckInTime();
                        if (dateNow.equals(checkInDate)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
        return false;
    }
}
