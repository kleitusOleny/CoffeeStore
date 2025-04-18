package model;

public abstract class Product {
    protected String nameP, size, description;
    protected int quantity;

    public Product(String nameP, String size, String description, int quantity) {
        this.nameP = nameP;
        this.size = size;
        this.description = description;
        this.quantity = quantity;
    }
    public abstract double getPrice();
    public abstract String getInformation();
}
