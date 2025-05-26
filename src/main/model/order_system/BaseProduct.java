package model.order_system;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseProduct implements IProduct {
    private String name;
    private String size;
    private String note;
    private int quantity;
    private double price;
    private List<Topping> toppings;
    
    public BaseProduct(String name, String size, String note, int quantity, double price) {
        this.name = name;
        this.size = size;
        this.note = note;
        this.quantity = quantity;
        this.price = price;
        this.toppings = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public String getSize() {
        return size;
    }
    
    public String getDe() {
        return note;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getPrice() {
        double totalPrice = price * quantity;
        for (Topping topping : toppings) {
            totalPrice += topping.getPrice() * topping.getQuantity();
        }
        return totalPrice;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public List<Topping> getToppings() {
        return toppings;
    }
    
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }
    
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }
    
    @Override
    public abstract String getInformation();
}