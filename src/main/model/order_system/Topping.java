package model.order_system;

public abstract class Topping implements IProduct {
    protected IProduct baseProduct;
    protected double price;
    protected int quantity;
    
    public Topping(IProduct baseProduct, double price) {
        this.baseProduct = baseProduct;
        this.price = price;
        this.quantity = 1;
    }
    
    public void applyToBaseProduct() {
        if (baseProduct instanceof BaseProduct) {
            ((BaseProduct) baseProduct).addTopping(this);
        }
    }
    
    public void removeFromBaseProduct() {
        if (baseProduct instanceof BaseProduct) {
            ((BaseProduct) baseProduct).removeTopping(this);
        }
    }
    
    public void updateQuantity(int quantity) {
        this.quantity = quantity;
        if (quantity <= 0) {
            removeFromBaseProduct();
        }
    }
    
    @Override
    public double getPrice() {
        return price * quantity;
    }
    
    @Override
    public int getQuantity() {
        return quantity;
    }
    
    @Override
    public abstract String getInformation();
    
    @Override
    public String getName() {
        return "";
    }
    
    @Override
    public String getSize() {
        return "";
    }
}