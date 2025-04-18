package model;

public class BankTransfer implements PaymentStrategy {
    private String bankNo, name;

    public BankTransfer(String bankNo, String name) {
        this.bankNo = bankNo;
        this.name = name;
    }

    @Override
    public double pay() {
        return 0;
    }
}
