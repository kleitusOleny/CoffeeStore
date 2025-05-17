package model.order_system;

public abstract class BaseProduct implements IProduct {
    protected String nameP, size, description;
    protected int quantity;
    protected double price;

    public BaseProduct(String nameP, String size, String description, int quantity, double price) {
        this.nameP = nameP;
        this.size = size;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public String getNameP() {
        return nameP;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public double getPrice() {
        return price  * quantity;
    }
}