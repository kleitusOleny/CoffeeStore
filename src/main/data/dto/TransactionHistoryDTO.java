package data.dto;

public class TransactionHistoryDTO {
    private String name, phoneNumber, money, method, paymentDate;

    public TransactionHistoryDTO(String name, String phoneNumber, String money, String method, String paymentDate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.money = money;
        this.method = method;
        this.paymentDate = paymentDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
