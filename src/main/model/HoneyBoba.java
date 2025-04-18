package model;

public class HoneyBoba extends Topping{
    public HoneyBoba(Product product, int quantity) {
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
