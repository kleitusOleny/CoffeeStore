package model.orderSystem;

public abstract class Topping implements IProduct {
    protected IProduct product;

    public Topping(IProduct product) {
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public String getSize() {
        return product.getSize();
    }

    @Override
    public String getDescription() {
        return product.getDescription();
    }

    @Override
    public int getQuantity() {
        return product.getQuantity();
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }

}
