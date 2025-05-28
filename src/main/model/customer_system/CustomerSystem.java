package model.customer_system;

import model.IModel;
import utils.CustomerStatus;
import utils.LoadDataToModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;


public class CustomerSystem extends Observable implements IModel {
    public Map<String, List<Customer>> listCus;
    
    public CustomerSystem() {
        LoadDataToModel.LoadCustomerDataToModel();
        this.listCus = LoadDataToModel.getCustomers();
    }

    @Override
    public String toString() {
        return "CustomerSystem{" +
                "listCus=" + listCus +
                '}';
    }

    public Map<String, List<Customer>> getListCus() {
        return listCus;
    }
    

    public void addCustomer(Customer customer) {
        String type = customer.getType(); // adjust according to your Customer class
        listCus.get(type).add(customer);
        setChanged();
        notifyObservers(new CustomerStatus("ADD_CUSTOMER",customer));
    }
    
    public void removeCustomer(Customer customer) {
        String type = customer.getType();
        List<Customer> customers = listCus.get(type);
        if (customers != null && customers.contains(customer)) {
            customers.remove(customer);
            setChanged();
            notifyObservers(new CustomerStatus("REMOVE_CUSTOMER",customer));
        }
    }
    
    public void updateCustomer(Customer customer,String newName, String newPhone) {
        customer.updateInforCustomer(newName,newPhone);
        setChanged();
        notifyObservers(new CustomerStatus("UPDATE_CUSTOMER",customer));
    }
    
    public Customer findCustomerByNumPhone(String numPhone) {
        for (String key : listCus.keySet()) {
            for (Customer customer : listCus.get(key)) {
                if (customer.getNumsPhone().equals(numPhone)) {
                    return customer;
                }
            }
        }
        System.out.println("Không tìm thấy khách hàng");
        return null;
    }
    
    public List<Customer> getVIPCustomers() {
        return listCus.getOrDefault("VIP", new ArrayList<>());
    }
    
    public List<Customer> getNormalCustomers() {
        return listCus.getOrDefault("Normal", new ArrayList<>());
    }
    
    public static void main(String[] args) {
        CustomerSystem customerSystem = new CustomerSystem();
        Customer customer = customerSystem.findCustomerByNumPhone("09846725615");
        System.out.println(customer);
    }
}
