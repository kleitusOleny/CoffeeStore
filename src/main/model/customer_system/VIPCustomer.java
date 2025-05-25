package model.customer_system;

import javax.security.auth.Subject;

public class VIPCustomer extends Customer {
    private int accumulatedPoints;
    public VIPCustomer(String name, String idCus, String numsPhone, int accumulatedPoints) {
        super(name, idCus, numsPhone);
        this.accumulatedPoints = accumulatedPoints;
//        subject.addObserver((Observer)this);
    }
    
    @Override
    public String toString() {
        return "VIPCustomer{" +
                "accumulatedPoints=" + accumulatedPoints +
                ", name='" + name + '\'' +
                ", idCus='" + idCus + '\'' +
                ", numsPhone='" + numsPhone + '\'' +
                '}';
    }

    @Override
    public String updatePoint(int point1) {
        this.accumulatedPoints += point1;
        return "cập nhật điểm thành công.Bạn đã được thêm " + point1 + " điểm";
    }

    /**
     * getType(): cho biet day la khach vip hay khach thuong
     *
     * @return
     */
    @Override
    public String getType() {
        return "VIP";
    }
    

    @Override
    public int countProductedBuy() {
        return 0;
    }

    @Override
    public boolean isVIP() {
        return true;
    }

    /**
     * exchangePointsForRewards: la phuong thuc doi diem tich luy
     * khi khach hang muon doi diem tich luy ta tru vao diem trich luy
     *
     * @param points
     */

    public String exchangePointsForRewards(int points) {
        if (points <= accumulatedPoints) {
            accumulatedPoints -= points;
            System.out.println();
            return "Đổi điểm thành công. Điểm tích lũy của khách hàng " + this.name + " còn lại: " + accumulatedPoints;
        } else {
            return "Điểm của khách hàng không đủ để quy đổi";
        }
    }

    @Override
    public int pointAccumulated() {
        return this.accumulatedPoints;
    }

}
