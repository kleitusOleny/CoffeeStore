package model.Payment;

public class Invoice {
    private double amount;
    private PaymentStrategy paymentStrategy;

    public Invoice(double amount, PaymentStrategy paymentStrategy) {
        this.amount = amount;
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public double pay() {
        if (paymentStrategy == null) {
            System.out.println("Chưa chọn phương thức thanh toán!");
            return 0.0;
        }
        return paymentStrategy.pay(amount);
    }
}
