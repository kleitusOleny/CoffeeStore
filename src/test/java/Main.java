import model.MainSystem;
import model.Subject;
import model.customerSystem.*;
import model.employeeSystem.EmployeeSystem;
import model.employeeSystem.ManagerAttendent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        
        MainSystem subject = new MainSystem(null,null,null,null);
        NormalCustomer normalCustomer1 = new NormalCustomer("Vinh","B01","0374205336",subject,false);
        NormalCustomer normalCustomer2 = new NormalCustomer("Trinh","B02","0374205336",subject,false);
        
        VIPCustomer vipCustomer = new VIPCustomer("ABC", "A01","20220202",subject,100);
        
        subject.getListObsever().forEach(System.out::println);
    }
}
