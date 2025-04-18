package model.orderSystem;

public class Americano extends Product {
    public Americano(String nameP, String size, String description, int quantity) {
        super(nameP, size, description, quantity);
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getInformation() {
        return "";
    }
}
