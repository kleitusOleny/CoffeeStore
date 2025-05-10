package model.Payment;

import model.employeeSystem.Date;

public class CreditCard implements PaymentStrategy {
    private String CardNo, name;
    private Date dateExpired;
    private int cvv;

    public CreditCard(String cardNo, String name, Date dateExpired, int cvv) {
        CardNo = cardNo;
        this.name = name;
        this.dateExpired = dateExpired;
        this.cvv = cvv;
    }

    @Override
    public double pay(double amount) {
        System.out.println("Thanh toán bằng thẻ tín dụng: " + CardNo + " - " + name + " với số tiền " + amount + " VND");
        return amount;
    }
}
