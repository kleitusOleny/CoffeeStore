package model.customerSystem;

import java.util.ArrayList;
import java.util.List;

public class ServiceCustomer {
        private CustomerSystem customerSystem;

    public ServiceCustomer(CustomerSystem customerSystem) {
        this.customerSystem = new CustomerSystem();
    }

    @Override
    public String toString() {
        return "ServiceCustomer{" +
                "customerSystem=" + customerSystem +
                '}';
    }

    // nag cap khach normal thanh khach vip
    public void upRank(Customer customer){
        if (customer.getType().equals("Normal") && customer.countProductedBuy() >= 10){
            Customer vip = new VIPCustomer(customer.name, customer.idCus, customer.numsPhone,0);
            // xoa khoi danh sach normal
            List<Customer> normalList = customerSystem.getListCus().get("Normal");
            if (normalList != null){
                normalList.remove(customer);
            }
            // them vao list khach hang vip
            List<Customer> vipList = customerSystem.getListCus().get("VIP");
            if (vipList == null){
                vipList = new ArrayList<>();
                customerSystem.getListCus().put("VIP", vipList);
            }
            vipList.add(vip);
            System.out.println(customer.name + " đã được nâng cấp lên VIP.");
        }else {
            System.out.println("Không đủ điều kiện nâng cấp hoặc đã là VIP.");
        }
    }

    // cap nhat lai diem cho khach hang vip sau moi lan mua hang
    public void  updatePoint(Customer customer,int p){
        if (customer instanceof VIPCustomer){
            customer.updatePoint(p);
        }else {
            System.out.println("Khách hàng không phải VIP, không thể cộng điểm.");
        }
    }
}
