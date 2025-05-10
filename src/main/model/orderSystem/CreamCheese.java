package model.orderSystem;

public class CreamCheese extends Topping{
    private double toppingPrice;

    public CreamCheese(IProduct product, double toppingPrice) {
        super(product);
        this.toppingPrice = toppingPrice;
    }

    @Override
    public String getInformation() {
        return product.getInformation() + " CreamCheese";
    }
    @Override
    public double getPrice() {
        return product.getPrice() + (toppingPrice * product.getQuantity());
    }
}
