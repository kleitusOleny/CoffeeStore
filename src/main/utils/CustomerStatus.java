package utils;

import model.customer_system.Customer;

public class CustomerStatus {
    private String action;
    private Customer customer;
    
    public CustomerStatus(String addCustomer, Customer customer) {
        this.action = addCustomer;
        this.customer = customer;
    }
    public String getAction() {
        return action;
    }
    public Customer getCustomer() {
        return customer;
    }
}
