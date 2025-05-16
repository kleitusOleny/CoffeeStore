package model.Payment;

import model.Date;
import model.MainSystem;
import model.customerSystem.Customer;
import model.customerSystem.NormalCustomer;

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

    @Override
    public String toString() {
        return String.format("Ngày: %s | Khách: %s | Phương thức: %s | Số tiền: %,d VND",
                dateTime,
                customer.getName() + (customer.isVIP() ? " [VIP]" : ""),
                paymentMethod,
                (int) amount);
    }


}
