package model.orderSystem;

public class CreamCheese extends Topping{
    public CreamCheese(Product product, int quantity) {
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
