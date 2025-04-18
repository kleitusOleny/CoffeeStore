package model.orderSystem;

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
    public double pay() {
        return 0;
    }
}
