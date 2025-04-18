package model;

public abstract class Topping {
    protected Product product;
    protected int quantity;

    public Topping(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public abstract double getPrice();
    public abstract String getInformation();
}
