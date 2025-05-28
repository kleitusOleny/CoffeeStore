package utils;

import data.ReadFileJson;
import data.dto.AccountsDTO;
import data.dto.ClientDTO;
import data.dto.DiscountDTO;
import data.dto.EmployeeDTO;
import model.Date;
import model.customer_system.Customer;
import model.customer_system.NormalCustomer;
import model.customer_system.VIPCustomer;
import model.employee_system.Employee;
import model.employee_system.Seller;

import java.util.*;

public final class LoadDataToModel {
    private static List<ClientDTO> clientsData = ReadFileJson.readFileJSONForClient();
    private static List<EmployeeDTO> employeesData = ReadFileJson.readFileJSONForEmployee();
    private static List<DiscountDTO> discounts = ReadFileJson.readFileJSONForDiscount();
    private static List<AccountsDTO> accounts = ReadFileJson.readFileJSONForAccount();
    
    
    private static Map<String,List<Customer>> customers = new HashMap<>();
    private static List<Employee> employees = new ArrayList<>();
    private static Map<String,String[]> acc = new HashMap<>();
    
    public static void LoadCustomerDataToModel() {
        for (ClientDTO customer: clientsData){
            List<Customer> list = new ArrayList<>();
            list = customers.getOrDefault(customer.getTrangThai(), list);
            if (customer.getTrangThai().equals("Normal")){
                list.add(new NormalCustomer(customer.getHoTen(),null,customer.getSoDienThoai()));
            }else {
                list.add(new VIPCustomer(customer.getHoTen(),null,customer.getSoDienThoai(),Integer.parseInt(customer.getDiemTichLuy())));
            }
            customers.put(customer.getTrangThai(), list);
        }
    }
    
    public static void LoadEmployeeDataToModel() {
        for (EmployeeDTO employee: employeesData){
            String id = employee.getId();
            String name = employee.getName();
            String phone = employee.getPhoneNumber();
            String gmail = "Example@gmail.com";
            String address = employee.getAddress();
            Date birthday = Date.convertToDate(employee.getBirth());
            employees.add(new Seller(id,name,phone,gmail,address,birthday,0,0.0));
        }
    
    }
    
    public static Map<String, String[]> loadAccountDataToModel() {
        for (AccountsDTO account: accounts){
            String role = "";
            if (account.getRole().equals("Manager")){
                role += 1;
            }else {
                role += 2;
            }
            String[] inf = {account.getPassword(),role};
            acc.put(account.getUsername(),inf);
        }
        return acc;
    }
    
    public static Map<String, List<Customer>> getCustomers() {
        return customers;
    }
    
    public static List<Employee> getEmployees() {
        return employees;
    }
    
    public static Map<String, String[]> getAcc() {
        return acc;
    }
    public static void main(String[] args) {
        LoadCustomerDataToModel();
//        for(String key: customers.keySet()){
//            System.out.println(key);
//            List<Customer> p = customers.get(key);
//            for(Customer c: p){
//                System.out.println(c.toString());
//            }
//        }
        LoadEmployeeDataToModel();
        for (Employee employee: employees){
            System.out.println(employee);
        }
    }
}
