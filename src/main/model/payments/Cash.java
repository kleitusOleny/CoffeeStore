package model.payments;

import model.customer_system.Customer;

public class Cash implements PaymentStrategy {

    @Override
    public double pay(double amount, Customer customer) {
        System.out.println("Thanh toán tiền mặt: " + amount + " VND");
        return amount;

    }
}
