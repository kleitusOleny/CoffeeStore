package model.Payment;

import model.customerSystem.Customer;
import model.customerSystem.CustomerSystem;

import java.util.List;

public class Invoice {
    private double balance;// nguon tien ban dau
    private PaymentStrategy paymentStrategy;
    private BankAccount sourceAccount;
    private Customer customer;

    public Invoice(double balance, PaymentStrategy paymentStrategy, BankAccount sourceAccount, Customer customer) {
        this.balance = balance;
        this.paymentStrategy = paymentStrategy;
        this.sourceAccount = sourceAccount;
        this.customer = customer;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public double pay() {
        if (paymentStrategy == null) {
            System.out.println("Chưa chọn phương thức thanh toán!");
            return 0.0;
        }
        double paidAmount = paymentStrategy.pay(balance);
        if (paidAmount > 0) {
            if (sourceAccount != null) {
                sourceAccount.deposite(paidAmount);//cong vao tai khoan nguon cua tiem
            }
            // chi cong diem cho khach hang vip

            if (customer.isVIP()) {
                int earnedPoints = 10;
                customer.updatePoint(earnedPoints);
            } else {
                System.out.println("Khách hàng không phải VIP -> không được tích điểm.");
            }
        }
        return paidAmount;
    }
}
