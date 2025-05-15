package model.Payment;

import model.customerSystem.Customer;

import java.sql.SQLOutput;

public class Invoice {
    private double amount;// nguon tien ban dau
    private PaymentStrategy paymentStrategy;
    private BankAccount sourceAccount;
    private Customer customer;
    private int earnedPoints = 0;

    public Invoice(double amount, PaymentStrategy paymentStrategy, BankAccount sourceAccount, Customer customer) {
        this.amount = amount;
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
        double paidAmount = paymentStrategy.pay(amount);
        if (paidAmount > 0) {
            if (sourceAccount != null) {
                sourceAccount.deposite(paidAmount);//cong vao tai khoan nguon cua tien
            }
            // chi cong diem cho khach hang vip

            if (customer.isVIP()) {
                earnedPoints = (int) paidAmount / 10000;
                customer.updatePoint(earnedPoints);
            } else {
                System.out.println("Khách hàng không phải VIP -> không được tích điểm.");
            }
        }
        return paidAmount;
    }

    /**
     * them cac loai thu uong sau khi lam phuong thuc  ben order
     */
    public String printInvoice() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n========== HÓA ĐƠN THANH TOÁN ==========\n");
        sb.append("Khách hàng: ")
                .append(customer.getName())
                .append(customer.isVIP() ? " [VIP]" : "")
                .append("\n");

        sb.append(String.format("Số tiền thanh toán: %,d VND%n", (int) amount));

        // Hiển thị tên phương thức thanh toán
        String methodName = paymentStrategy.getClass().getSimpleName();
        switch (methodName) {
            case "Cash":
                methodName = "Tiền mặt";
                break;
            case "BankTransfer":
                methodName = "Chuyển khoản ngân hàng";
                break;
            case "CreditCard":
                methodName = "Thẻ tín dụng";
                break;
            default:
                methodName = "Không xác định";
        }
        sb.append("Phương thức thanh toán: ").append(methodName).append("\n");

        // Tính điểm nếu khách hàng VIP
        if (customer.isVIP()) {
            int earnedPoints = (int) (amount / 10000);
            sb.append("Điểm tích lũy được cộng: ").append(earnedPoints).append(" điểm\n");
            sb.append("Tổng điểm hiện tại: ").append(customer.pointAccumulated()).append("\n");
        } else {
            sb.append("Khách hàng không được tích điểm.\n");
        }

        sb.append("========================================\n");

        return sb.toString();
    }


}
