package model.payments;

import model.customer_system.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private double amount;
    private String paymentMethod;
    private String name;
    private String phoneNumber;
    private String dateTime;
    
    public Transaction(double amount, String dateTime, String name, String paymentMethod, String phoneNumber) {
        this.amount = amount;
        this.dateTime = dateTime;
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.phoneNumber = phoneNumber;
    }
    
    public LocalDate getDateTime() {
        return LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    }
    public double getAmount() {
        return amount;
    }
    
    public  String getPaymentMethod() {
        return paymentMethod;
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
