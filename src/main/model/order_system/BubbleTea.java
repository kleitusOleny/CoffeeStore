package model.order_system;

public class BubbleTea extends BaseProduct {

    public BubbleTea(String nameP, String size, String description, int quantity, double price) {
        super(nameP, size, description, quantity, price);
    }

    @Override
    public String getInformation() {
        return "Bubble tea";
    }

    @Override
    public String getName() {
        return "";
    }
}
