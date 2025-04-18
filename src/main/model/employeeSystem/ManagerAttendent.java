package model.employeeSystem;

import java.nio.file.FileSystemNotFoundException;
import java.util.List;

public class ManagerAttendent {
    private List<Attendent> list;
    
    public ManagerAttendent(List<Attendent> list) {
        this.list = list;
    }
    
    //diem danh
    public void checkIn(Employee e){
    
    }
    
    //kiem tra nhan vien co diem danh chua
    private boolean hasCheckedInToday(String employeeID){
        return false;
    }
}
