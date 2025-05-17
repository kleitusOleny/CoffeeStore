package model.order_system;

public class Boba extends Topping{
    private double toppingPrice;

    public Boba(IProduct product, double toppingPrice) {
        super(product);
        this.toppingPrice = toppingPrice;
    }

    @Override
    public String getInformation() {
        return product.getInformation() + " + Boba";
    }
    public double getPrice(){
        return product.getPrice() + (toppingPrice * product.getQuantity());
    }
}

