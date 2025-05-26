package model.payments;

import model.customer_system.Customer;
import utils.PaymentStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private double amount;
    private PaymentStrategy paymentStrategy;

    private Customer customer;
    private int earnedPoints = 0;

    public Invoice(double amount, PaymentStrategy paymentStrategy, Customer customer, int earnedPoints) {
        this.amount = amount;
        this.paymentStrategy = paymentStrategy;
        this.customer = customer;
        this.earnedPoints = earnedPoints;
    }

    public Invoice(double amount, PaymentStrategy paymentStrategy, Customer customer) {
        this.amount = amount;
        this.paymentStrategy = paymentStrategy;
        this.customer = customer;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public double pay() {
        if (paymentStrategy == null) {
            return 0.0;
        }
        return paymentStrategy.pay(amount, customer);
    }
}