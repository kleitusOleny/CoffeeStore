package model.order_system;

public class Flan extends Topping{
    private double toppingPrice;
    
    public Flan(IProduct baseProduct, double price) {
        super(baseProduct, price);
    }
    
    @Override
    public String getInformation() {
        return "Bánh flan x" + quantity;
    }
}
