package controller;

import model.customer_system.Customer;
import model.customer_system.NormalCustomer;
import model.payments.*;

public class PaymentController {
    private Invoice currentInvoice;

    /**
     * tao hoa don moi
     */
    public void createInvoice(double amount, PaymentStrategy strategy, Customer customer) {
        this.currentInvoice = new Invoice(amount, strategy, customer);
    }

    /**
     * Thực hiện thanh toán.
     */
    public double processPayment() {
        if (currentInvoice == null) {
            System.out.println("Không có hóa đơn nào được tạo!");
            return 0.0;
        }
        return currentInvoice.pay();
    }

    /**
     * In hóa đơn ra màn hình (hoặc trả về chuỗi cho hiển thị).
     */
    public String printInvoice() {
        if (currentInvoice != null) {
            return currentInvoice.printInvoice();
        }
        return "Không có hóa đơn để in!";
    }

    /**
     * Xem toàn bộ giao dịch đã thực hiện.
     */
    public String viewAllTransactions() {
        return TransactionLog.showAll();
    }

    /**
     * Xóa hóa đơn hiện tại (sau khi thanh toán xong).
     */
    public void clearCurrentInvoice() {
        this.currentInvoice = null;
    }



}
