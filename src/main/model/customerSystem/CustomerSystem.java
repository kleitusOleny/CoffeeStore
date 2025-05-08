package model.customerSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSystem {
    private Map<String, List<Customer>> listCus;

    public CustomerSystem(Map<String, List<Customer>> listCus) {
        this.listCus = new HashMap<>();
    }

    public CustomerSystem() {
        this.listCus = new HashMap<>();
    }

    public Map<String, List<Customer>> getListCus() {
        return listCus;
    }

    /**
     *
     */

    public  void addCustomer(Customer customer) {
        String type = customer.getType(); // adjust according to your Customer class
        listCus.put(type, new ArrayList<>());
        listCus.get(type).add(customer);
    }
    public void removeCustomer(Customer customer) {
        String type = customer.getType();// getType(): lay ra khach hang do la thuong hay vip
        List<Customer> customers = listCus.get(type);
        if (customers != null){
            customers.remove(customer);
            if (customers.isEmpty()){
                listCus.remove(type);// xoa luon key neu khong con khach hang nao
            }
        }
    }
}
