package model.order_system;

import model.payments.Invoice;
import model.customer_system.Customer;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private List<IProduct> listP;
    private Invoice invoice;

    public Order(Customer customer, List<IProduct> listP, Invoice invoice) {
        this.customer = customer;
        this.listP = listP != null ? listP : new ArrayList<>();
        this.invoice = invoice;
    }
    public void addItem(IProduct p){
        listP.add(p);
    }
    public void removeItem(IProduct p){
        listP.remove(p);
    }
    public double totalPrice(){

        return listP.stream().mapToDouble(IProduct::getPrice).sum();
    }

    public List<IProduct> getListP() {
        return listP;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public Invoice getInvoice() {
        return invoice;
    }
    
    public void updateQuantity(IProduct p, int quantity) {
        // Tìm sản phẩm trong danh sách và cập nhật số lượng
        for (IProduct item : listP) {
            if (item == p) {
                try {
                    java.lang.reflect.Field quantityField = BaseProduct.class.getDeclaredField("quantity");
                    quantityField.setAccessible(true);
                    quantityField.setInt(item, quantity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
