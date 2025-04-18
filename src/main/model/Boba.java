package model;

public class Boba extends Topping{
    public Boba(Product product, int quantity) {
        super(product, quantity);
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
