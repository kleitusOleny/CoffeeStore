package model.orderSystem;

public class Invoice {
    private int amount;
    private PaymentStrategy paymentStrategy;

    public Invoice(int amount, PaymentStrategy paymentStrategy) {
        this.amount = amount;
        this.paymentStrategy = paymentStrategy;
    }
    public double pay(){
        return 0.0;
    }
}
