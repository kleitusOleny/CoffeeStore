package controller;

import model.customer_system.Customer;
import model.payments.PaymentStrategy;
import view.IView;
import view.PaymentPanel;

import javax.swing.table.DefaultTableModel;

public class PaymentController {
    private PaymentPanel paymentPanel;
    private Customer customer;
    private double totalAmount;
    private DefaultTableModel tableModel;

    public PaymentController(PaymentPanel paymentPanel, Customer customer, double totalAmount, DefaultTableModel tableModel) {
        this.paymentPanel = paymentPanel;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.tableModel = tableModel;

//        initializeView();
    }
}
