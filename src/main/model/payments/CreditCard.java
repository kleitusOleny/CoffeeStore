package model.payments;

import model.Date;
import model.customer_system.Customer;

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
    public double pay(double amount, Customer customer) {
        System.out.println("Thanh toán bằng thẻ tín dụng: " + CardNo + " - " + name + " với số tiền " + amount + " VND");
        bankAccount.deposit(amount,this.getClass().getSimpleName(),customer);
        return amount;
    }

}
