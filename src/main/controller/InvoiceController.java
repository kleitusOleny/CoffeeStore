package controller;

import model.payments.Invoice;
import view.Staff.PaymentPanel;

public class InvoiceController implements IController{
    private PaymentPanel view;
    private Invoice model;
    public InvoiceController( Invoice model,PaymentPanel view) {
        this.model = model;
        this.view = view;
    }
    
}
