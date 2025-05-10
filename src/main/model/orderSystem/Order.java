package model.orderSystem;

import model.Payment.Invoice;
import model.customerSystem.Customer;

import java.util.List;

public class Order {
    private Customer customer;
    private List<BaseProduct> listP;
    private Invoice invoice;

    public Order(Customer customer, List<BaseProduct> listP, Invoice invoice) {
        this.customer = customer;
        this.listP = listP;
        this.invoice = invoice;
    }
    public void addItem(BaseProduct p){
        listP.add(p);
    }
    public void removeIten(BaseProduct p){
        listP.remove(p);
    }
    public double totalPrice(){

        return listP.stream().mapToDouble(BaseProduct::getPrice).sum();
    }

    public List<BaseProduct> getListP() {
        return listP;
    }
}
