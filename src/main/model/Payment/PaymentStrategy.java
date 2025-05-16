package model.Payment;

import model.customerSystem.Customer;

public interface PaymentStrategy {
    public double pay(double amount, Customer customer);
    
    
}

