package model.Payment;

public class BankTransfer implements PaymentStrategy {
    private String bankNo, name;

    public BankTransfer(String bankNo, String name) {
        this.bankNo = bankNo;
        this.name = name;
    }



    @Override
    public double pay(double amount) {
        System.out.println("Chuyển khoản ngân hàng từ " + name + " (" + bankNo + ") số tiền: " + amount + " VND");
        return amount;
    }
}
