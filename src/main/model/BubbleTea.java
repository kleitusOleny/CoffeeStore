package model;

public class BubbleTea extends Product {

    public BubbleTea(String nameP, String size, String description, int quantity) {
        super(nameP, size, description, quantity);
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getInformation() {
        return "";
    }
}
