package model.Payment;

public class Cash implements PaymentStrategy {

    @Override
    public double pay(double amount) {
        System.out.println("Thanh toán tiền mặt: " + amount + " VND");
        return amount;

    }
}
