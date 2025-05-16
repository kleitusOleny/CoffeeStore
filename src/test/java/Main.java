import model.MainSystem;
import model.Payment.BankAccount;
import model.Payment.BankTransfer;
import model.Payment.Invoice;
import model.Payment.TransactionLog;
import model.Subject;
import model.customerSystem.*;
import model.employeeSystem.EmployeeSystem;
import model.employeeSystem.ManagerAttendent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Subject mainsys =new MainSystem(null,null,null,null);
        Customer customer = new VIPCustomer("AA","AA","00",mainsys,100);
        BankAccount bankAccount = new BankAccount();
        Invoice invoice1 = new Invoice(1000,new BankTransfer("AA","Vinh",bankAccount),customer);
        Invoice invoice2 = new Invoice(1000,new BankTransfer("AA","Vinh",bankAccount),customer);
        
        invoice1.pay();
        invoice2.pay();
        
        System.out.println(TransactionLog.showAll());
        System.out.println(TransactionLog.getTransactions());
    }
}
