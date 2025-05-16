package model.Payment;

import model.employeeSystem.Date;

public class CreditCard implements PaymentStrategy {
    private String CardNo, name;
    private Date dateExpired;
    private int cvv;
    private BankAccount bankAccount;

    public CreditCard(String cardNo, String name, Date dateExpired, int cvv, BankAccount bankAccount) {
        CardNo = cardNo;
        this.name = name;
        this.dateExpired = dateExpired;
        this.cvv = cvv;
        this.bankAccount = bankAccount;
    }

    @Override
    public double pay(double amount) {
        System.out.println("Thanh toán bằng thẻ tín dụng: " + CardNo + " - " + name + " với số tiền " + amount + " VND");
        bankAccount.deposit(amount);
        return amount;
    }
}
