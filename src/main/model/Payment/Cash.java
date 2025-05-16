package model.Payment;

import model.customerSystem.Customer;

public class Cash implements PaymentStrategy {

    @Override
    public double pay(double amount, Customer customer) {
        System.out.println("Thanh toán tiền mặt: " + amount + " VND");
        return amount;

    }
}
