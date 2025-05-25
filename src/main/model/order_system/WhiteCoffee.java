package model.order_system;

public class WhiteCoffee extends BaseProduct {
    public WhiteCoffee(String name, String size, String note, int quantity, double price) {
        super(name, size, note, quantity, price);
    }
    
    @Override
    public String getInformation() {
        return getName() + " (Size " + getSize() + ") x" + getQuantity();
    }
}