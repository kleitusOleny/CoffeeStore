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
//    @Override
////    public double pay(double amount, Customer customer) {
////        System.out.println("Chuyển khoản ngân hàng từ " + name + " (" + bankNo + ") số tiền: " + amount + " VND");
////
////        String qrContent = String.format("STK: %s\nChủ TK: %s\nSố tiền: %.0f VND", bankNo, name, amount);
////
////        try {
////            BufferedImage qrImage = QRCodeGenerator.generateQRCode(qrContent, 300, 300);
////            JLabel picLabel = new JLabel(new ImageIcon(qrImage));
////            JOptionPane.showMessageDialog(null, picLabel, "Quét mã QR để thanh toán", JOptionPane.PLAIN_MESSAGE);
////
////            // Sau khi hiển thị mã QR và giả định khách đã quét thanh toán thành công
////            account.deposit(amount, this.getClass().getSimpleName(), customer);
////            return amount;
////        } catch (WriterException e) {
////            e.printStackTrace();
////            JOptionPane.showMessageDialog(null, "Không thể tạo mã QR", "Lỗi", JOptionPane.ERROR_MESSAGE);
////            return 0;
////        }
////    }
}
