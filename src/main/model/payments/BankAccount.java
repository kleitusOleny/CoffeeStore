package model.payments;

import model.customer_system.Customer;

public class BankAccount {
    private double balance;
    private TransactionLog transactionLog;
    
    public BankAccount() {
        this.balance = 0.0;
    }

    
    public void deposit(double amount,String payment,Customer customer) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Đã cộng " + amount + " VND vào tài khoản nguồn. Số dư hiện tại: " + balance + " VND");
        }
    }

    public double getBalance() {
        return balance;
    }
    
    public static void main(String[] args) {
        BankTransfer bankTransfer = new BankTransfer("11","AB",new BankAccount());
        String a = bankTransfer.getClass().getSimpleName();
        System.out.println(a);
    }
}
