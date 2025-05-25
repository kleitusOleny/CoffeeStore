package model.payments;

import com.google.zxing.WriterException;
import model.customer_system.Customer;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class BankTransfer implements PaymentStrategy {
    private String bankNo, name;
    private BankAccount account;

    public BankTransfer(String bankNo, String name, BankAccount account) {
        this.bankNo = bankNo;
        this.name = name;
        this.account = account;
    }

    @Override
    public double pay(double amount, Customer customer) {
        System.out.println("Chuyển khoản ngân hàng từ " + name + " (" + bankNo + ") số tiền: " + amount + " VND");
        account.deposit(amount,this.getClass().getSimpleName(),customer);






        return amount;
    }
}
