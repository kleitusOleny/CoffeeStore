package model.customer_system;

import javax.security.auth.Subject;

public class NormalCustomer extends Customer {
    public NormalCustomer(String name, String idCus, String numsPhone) {
        super(name, idCus, numsPhone);
    }
    

    @Override
    public String toString() {
        return "NormalCustomer{" +
                ", name='" + name + '\'' +
                ", idCus='" + idCus + '\'' +
                ", numsPhone='" + numsPhone + '\'' +
                '}';
    }
    

    @Override
    public String updatePoint(int point1) {
        return "Bạn cần đăng ký VIP để đổi điểm";
    }

    @Override
    public String getType() {
        return "Normal";
    }
    
    @Override
    public int countProductedBuy() {
        return 0;
    }

    @Override
    public boolean isVIP() {
        return false;
    }

    @Override
    public int pointAccumulated() {
        return 0;
    }
}
