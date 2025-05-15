package model.customerSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSystem {
    public Map<String, List<Customer>> listCus;

    public CustomerSystem(Map<String, List<Customer>> listCus) {
        this.listCus = new HashMap<>();
    }

    @Override
    public String toString() {
        return "CustomerSystem{" +
                "listCus=" + listCus +
                '}';
    }

    public CustomerSystem() {
        this.listCus = new HashMap<>();
    }

    public Map<String, List<Customer>> getListCus() {
        return listCus;
    }

    /**
     *phuong thuc them khach hang
     */

    public void addCustomer(Customer customer) {
        String type = customer.getType(); // adjust according to your Customer class
        listCus.put(type, new ArrayList<>());
        listCus.get(type).add(customer);
    }

    /**
     * xoa mot khach hang khoi danh sach khach hang
     * @param customer
     */
    public void removeCustomer(Customer customer) {
        String type = customer.getType();// getType(): lay ra khach hang do la thuong hay vip
        List<Customer> customers = listCus.get(type);
        if (customers != null) {
            customers.remove(customer);
            if (customers.isEmpty()) {
                listCus.remove(type);//o xoa luon key neu khong con khach hang nao
            }
        }
    }

    /**
     * tim khach hang bang so dien thoai
     * @param numPhone
     * @return
     */
    public Customer findCustomerByNumPhone(String numPhone) {
        for (List<Customer> customers : listCus.values()) {
            for (Customer customer : customers) {
                if (customer.getNumsPhone().equals(numPhone)) {
                    return customer;
                }
            }
        }
        System.out.println("Không tìm thấy khách hàng");
        return null;
    }

    /**
     * loc ra danh sach khach hang vip
     * @return List<Customer>
     */
    public List<Customer> getVIPCustomers() {
        return listCus.getOrDefault("VIP", new ArrayList<>());
    }

    /**
     * loc ra danh sach khach hang thuong
     * @return List<Customer>
     */
    public List<Customer> getNormalCustomers() {
        return listCus.getOrDefault("Normal", new ArrayList<>());
    }

}
