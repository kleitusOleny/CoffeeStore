package utils;

public class PaymentStatus {
    private String action;
    private String message;
    private double total;
    
    public PaymentStatus(String action, String message, double total) {
        this.action = action;
        this.message = message;
        this.total = total;
    }
    
    public String getAction() {
        return action;
    }
    
    public String getMessage() {
        return message;
    }
    
    public double getTotal() {
        return total;
    }
}
