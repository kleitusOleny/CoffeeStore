package model.orderSystem;

public class Espresso extends BaseProduct {
    public Espresso(String nameP, String size, String description, int quantity, double price) {
        super(nameP, size, description, quantity, price);
    }

    @Override
    public String getInformation() {
        return "Espresso";
    }

    @Override
    public String getName() {
        return "";
    }
}
