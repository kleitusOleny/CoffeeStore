package model.orderSystem;

public class HoneyBoba extends Topping{
   private double toppingPrice;

    public HoneyBoba(IProduct product, double toppingPrice) {
        super(product);
        this.toppingPrice = toppingPrice;
    }

    @Override
    public String getInformation() {
        return product.getInformation() + " HoneyBoba";
    }
    public double getPrice(){
        return product.getPrice() + (toppingPrice * product.getQuantity());
    }
}
