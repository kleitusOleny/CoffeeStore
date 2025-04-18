package model;

public class Espresso extends Product {
    public Espresso(String nameP, String size, String description, int quantity) {
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
