package model.customerSystem;

import model.Subject;

public class VIPCustomer extends Customer {
    private int accumulatedPoints;
    public VIPCustomer(String name, String idCus, String numsPhone, int accumulatedPoints, Subject subject) {
        super(name, idCus, numsPhone); // không gọi super(..., subject)
        this.accumulatedPoints = accumulatedPoints;
        this.subject = subject;
        subject.addObserver(this); // ✅ luôn thêm
    }
    
    public VIPCustomer(String name, String idCus, String numsPhone, Subject subject, int accumulatedPoints) {
        super(name, idCus, numsPhone, subject);
        this.accumulatedPoints = accumulatedPoints;
        subject.addObserver((Observer)this);
    }

    @Override
    public String updateNotify(String nameTB, String ndTB) {
        String result = "";
        result = result + "Thông báo mới cho khách hàng: " + this.name + ": " + "\n" + "Tiêu đề: " + nameTB + "\n" + "Nội dung: " + ndTB;
        return result;

    }

    /**
     * khach hang vip da duoc thiet lap san tinh nang nhan thong bao tu quan tri
     * @param trangThaiDangKy
     */
    @Override
    public void setRegisterStatus(boolean trangThaiDangKy) {

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

    /**
     * khach hang vip luon nhan duoc chuong trinh khuyen mai ma
     * khong can phai thong qua dang ky
     *
     * @return
     */
    @Override
    public boolean isRegister() {
        return true;
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
