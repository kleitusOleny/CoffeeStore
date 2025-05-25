package utils;

public class OrderStatus {
    private final String action;
    private final String message;
    private final double totalPrice;
    
    public OrderStatus(String action, String message, double totalPrice) {
        this.action = action;
        this.message = message;
        this.totalPrice = totalPrice;
    }
    
    public String getAction() {
        return action;
    }
    
    public String getMessage() {
        return message;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
}
