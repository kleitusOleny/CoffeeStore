package model.order_system;

public class Americano extends BaseProduct {
    public Americano(String nameP, String size, String description, int quantity, double price) {
        super(nameP, size, description, quantity, price);
    }

    @Override
    public String getInformation() {
        return "Americano";
    }

    @Override
    public String getName() {
        return "";
    }
}
