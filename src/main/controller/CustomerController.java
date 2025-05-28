package controller;

import data.ReadFileJson;
import model.customer_system.*;
import view.Staff.DiscountPanel;

import javax.swing.*;

public class CustomerController implements IController{
    private CustomerSystem model;
    private DiscountPanel view;

    public CustomerController(CustomerSystem model, DiscountPanel view) {
        this.model = model;
        this.view = view;
    }

    public void init(){
    
    }
    
    public void addCustomer(String name, String phone) {
        if (name != null && !name.trim().isEmpty() && phone != null && !phone.trim().isEmpty()) {
            Customer customer = new NormalCustomer(name,"",phone);
            model.addCustomer(customer);
            ReadFileJson.addClient(name,phone);
        }else {
            JOptionPane.showMessageDialog((JComponent) view, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void removeCustomer(String phone) {
        Customer customer = model.findCustomerByNumPhone(phone);
        if (customer != null) {
            model.removeCustomer(customer);
            ReadFileJson.deteleClientInformation(customer.getName(),phone);
        } else {
            JOptionPane.showMessageDialog((JComponent) view, "Không tìm thấy khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateCustomer(String oldPhone, String newName, String newPhone) {
        Customer customer = model.findCustomerByNumPhone(oldPhone);
        if (customer != null) {
            model.updateCustomer(customer, newName, newPhone);
            ReadFileJson.deteleClientInformation(customer.getName(),newPhone);
            ReadFileJson.addClient(newName,newPhone);
        } else {
            JOptionPane.showMessageDialog((JComponent) view, "Không tìm thấy khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void upgradeToVIP(String phone) {
        Customer customer = model.findCustomerByNumPhone(phone);
        if (customer != null && !customer.isVIP()) {
            new model.customer_system.ServiceCustomer(model).upRank(customer);
        }
    }
}