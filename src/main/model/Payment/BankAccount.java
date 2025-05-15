package model.Payment;

public class BankAccount {
    private double balance;

    public BankAccount() {
        this.balance = 0.0;
    }
    public void deposite(double amount) {
        if (amount > 0 ){
            balance += amount;
            System.out.println("Đã cộng " + amount + " VND vào tài khoản nguồn. Số dư hiện tại: " + balance + " VND");
        }
    }


    public double getBalance() {
        return balance;
    }
}
