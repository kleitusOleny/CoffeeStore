package model.order_system;

public class HoneyBoba extends Topping{
    
    public HoneyBoba(IProduct baseProduct, double price) {
        super(baseProduct, price);
    }
    
    @Override
    public String getInformation() {
        return "Trân châu mật ong x" + quantity;
    }
}
