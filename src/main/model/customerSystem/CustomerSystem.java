package model.customerSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSystem {
    private Map<String, List<Customer>> listCus;

    public CustomerSystem(Map<String, List<Customer>> listCus) {
        this.listCus = new HashMap<>();
    }

    public Map<String, List<Customer>> getListCus() {
        return listCus;
    }

    /**
     *
     */

    public  void addCustomer(Customer customer) {

    }
    public void removeCustomer(Customer customer) {

    }
}
