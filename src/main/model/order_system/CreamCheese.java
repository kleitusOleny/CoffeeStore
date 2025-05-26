package model.order_system;

public class CreamCheese extends Topping {
    public CreamCheese(IProduct baseProduct, double price) {
        super(baseProduct, price);
    }
    
    @Override
    public String getInformation() {
        return "Kem cheese x" + quantity;
    }
}