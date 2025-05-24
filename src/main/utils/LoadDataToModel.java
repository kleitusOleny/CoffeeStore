package utils;

import data.ReadFileJson;
import data.dto.FormatClient;
import data.dto.FormatDiscount;
import data.dto.FormatEmployee;
import model.Date;
import model.IModel;
import model.customer_system.Customer;
import model.customer_system.NormalCustomer;
import model.customer_system.VIPCustomer;
import model.employee_system.Employee;
import model.employee_system.Seller;

import java.util.*;

public class LoadDataToModel {
    private static List<FormatClient> clientsData = ReadFileJson.readFileJSONForClient();
    private static List<FormatEmployee> employeesData = ReadFileJson.readFileJSONForEmployee();
    private static List<FormatDiscount> discounts = ReadFileJson.readFileJSONForDiscount();
    
    private static Map<String,List<Customer>> customers = new HashMap<>();
    private static List<Employee> employees = new ArrayList<>();
    public static void LoadCustomerDataToModel() {
        for (FormatClient customer: clientsData){
            List<Customer> list = new ArrayList<>();
            list = customers.getOrDefault(customer.getTrangThai(), list);
            if (customer.getTrangThai().equals("Bình Thường")){
                list.add(new NormalCustomer(customer.getHoTen(),null,customer.getSoDienThoai(),null,false));
            }else {
                list.add(new VIPCustomer(customer.getHoTen(),null,customer.getSoDienThoai(),null,Integer.parseInt(customer.getDiemTichLuy())));
            }
            customers.put(customer.getTrangThai(), list);
        }
    }
    
    public static void LoadEmployeeDataToModel() {
        for (FormatEmployee employee: employeesData){
            String id = employee.getId();
            String name = employee.getName();
            String phone = employee.getPhoneNumber();
            String gmail = "Example@gmail.com";
            String address = employee.getAddress();
            Date birthday = Date.convertToDate(employee.getBirth());
            employees.add(new Seller(id,name,phone,gmail,address,birthday,0,0.0));
        }
    
    }
    
    public static Map<String, List<Customer>> getCustomers() {
        return customers;
    }
    
    public static List<Employee> getEmployees() {
        return employees;
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
