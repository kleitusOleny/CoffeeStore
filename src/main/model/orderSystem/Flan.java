package model.orderSystem;

public class Flan extends Topping{
    private double toppingPrice;

    public Flan(IProduct product, double toppingPrice) {
        super(product);
        this.toppingPrice = toppingPrice;
    }

    @Override
    public String getInformation() {
        return product.getInformation()+ " Flan";
    }
    public  double getPrice(){
        return product.getPrice() + (toppingPrice * product.getQuantity());
    }
}
