package model.Payment;

import model.customerSystem.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private double amount;
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

    private String getPaymentMethodName() {
        String methodName = paymentStrategy.getClass().getSimpleName();
        switch (methodName) {
            case "Cash":
                return "Tiền mặt";
            case "BankTransfer":
                return "Chuyển khoản ngân hàng";
            case "CreditCard":
                return "Thẻ tín dụng";
            default:
                return "Không xác định";
        }
    }

    public double pay() {
        if (paymentStrategy == null) {
            System.out.println("Vui lòng chọn phương thức thanh toán!");
            return 0.0;
        }
        if (amount <= 0) {
            System.out.println("Số tiền thanh toán không hợp lệ!");
            return 0.0;
        }

        double paidAmount = paymentStrategy.pay(amount);
        if (paidAmount > 0) {
            if (sourceAccount != null) {
                sourceAccount.deposit(paidAmount);//cong vao tai khoan nguon cua tien
            }
            // chi cong diem cho khach hang vip

            if (customer.isVIP()) {
                earnedPoints = (int) paidAmount / 10000;
                customer.updatePoint(earnedPoints);
            } else {
                System.out.println("Khách hàng không phải VIP -> không được tích điểm.");
            }
            //ghi lai gia dich vao log
            String methodName = getPaymentMethodName();
            String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            TransactionLog.log(new Transaction(paidAmount, methodName, customer, dateTime));
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
        String methodName = getPaymentMethodName();

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
