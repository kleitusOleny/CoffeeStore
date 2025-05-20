package model.payments;

import model.customer_system.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private double amount;
    private String paymentMethod;
    private Customer customer;
    private String dateTime;

    public Transaction(double amount, String paymentMethod, Customer customer, String dateTime) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.customer = customer;
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

    }
    public double getAmount() {
        return amount;
    }
    @Override
    public String toString() {
        return String.format("Ngày: %s | Khách: %s | Phương thức: %s | Số tiền: %,d VND",
                dateTime,
                customer.getName() + (customer.isVIP() ? " [VIP]" : ""),
                paymentMethod,
                (int) amount);
    }
    public  String getPaymentMethod() {
        return paymentMethod;
    }


}
