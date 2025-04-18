package model.orderSystem;

public class Flan extends Topping{
    public Flan(Product product, int quantity) {
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
