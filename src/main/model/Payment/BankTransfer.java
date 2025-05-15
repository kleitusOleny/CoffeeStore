package model.Payment;

public class BankTransfer implements PaymentStrategy {
    private String bankNo, name;
    private BankAccount account;

    public BankTransfer(String bankNo, String name, BankAccount account) {
        this.bankNo = bankNo;
        this.name = name;
        this.account = account;
    }

    @Override
    public double pay(double amount) {
        System.out.println("Chuyển khoản ngân hàng từ " + name + " (" + bankNo + ") số tiền: " + amount + " VND");
        account.deposite(amount);
        return amount;
    }
}
