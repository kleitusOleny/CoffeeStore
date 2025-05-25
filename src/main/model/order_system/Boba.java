package model.order_system;

public class Boba extends Topping {
    public Boba(IProduct baseProduct, double price) {
        super(baseProduct, price);
    }
    
    @Override
    public String getInformation() {
        return "Trân châu x" + quantity;
    }
}