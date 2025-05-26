package controller;

import model.customer_system.*;
import view.Staff.DiscountPanel;

import javax.swing.*;

public class CustomerController {
    private CustomerSystem customerSystem;
    private DiscountPanel view;

    public CustomerController(CustomerSystem customerSystem, DiscountPanel view) {
        this.customerSystem = customerSystem;
        this.view = view;
    }

    public void addCustomer(String name, String phone) {
        if (name != null && !name.trim().isEmpty() && phone != null && !phone.trim().isEmpty()) {
            Customer customer = new NormalCustomer(name,"",phone);
            customerSystem.addCustomer(customer);
            ((DiscountPanel) view).refreshTable();
        }else {
            JOptionPane.showMessageDialog((JComponent) view, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removeCustomer(String phone) {
        Customer customer = customerSystem.findCustomerByNumPhone(phone);
        if (customer != null) {
            customerSystem.removeCustomer(customer);
            ((DiscountPanel) view).refreshTable();
        } else {
            JOptionPane.showMessageDialog((JComponent) view, "Không tìm thấy khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateCustomer(String oldPhone, String newName, String newPhone) {
        Customer customer = customerSystem.findCustomerByNumPhone(oldPhone);
        if (customer != null) {
            customerSystem.updateCustomer(customer, newName, newPhone);
            ((DiscountPanel) view).refreshTable();
        } else {
            JOptionPane.showMessageDialog((JComponent) view, "Không tìm thấy khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Customer findCustomerByPhone(String phone) {
        return customerSystem.findCustomerByNumPhone(phone);
    }
    
    public void upgradeToVIP(String phone) {
        Customer customer = customerSystem.findCustomerByNumPhone(phone);
        if (customer != null && !customer.isVIP()) {
            new model.customer_system.ServiceCustomer(customerSystem).upRank(customer);
            ((DiscountPanel) view).refreshTable();
        }
    }
}