package model.orderSystem;

import model.customerSystem.Customer;

import java.util.List;

import model.customerSystem.Customer;

import java.util.List;

public class Order {
    private Customer customer;
    private List<Product> listP;
    private Invoice invoice;

    public Order(Customer customer, List<Product> listP, Invoice invoice) {
        this.customer = customer;
        this.listP = listP;
        this.invoice = invoice;
    }
    public void addItem(Product p){
    }
    public void removeIten(Product p){
    }
    public double totalPrice(){
        return 0.0;
    }
}
