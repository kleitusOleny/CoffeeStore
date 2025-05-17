package model.payments;

import model.customer_system.Customer;

public interface PaymentStrategy {
    public double pay(double amount, Customer customer);
    
    
}

