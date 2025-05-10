package model.customerSystem;

public class VIPCustomer extends Customer {
    private int accumulatedPoints;

    public VIPCustomer(String name, String idCus, String numsPhone, int accumulatedPoints) {
        super(name, idCus, numsPhone);
        this.accumulatedPoints = accumulatedPoints;
    }

    @Override
    public void updateNotify(String nameTB, String ndTB) {
        System.out.println("Thông báo gửi đến VIP " + this.name+ ":");
        System.out.println("Tiêu đề: " + nameTB);
        System.out.println("Nội dung: " + ndTB);
    }

    @Override
    protected void setRegisterStatus(boolean trangThaiDangKy) {

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
    public void updatePoint(int point1) {
        accumulatedPoints += point1;
    }

    /**
     * getType(): cho biet day la khach vip hay khach thuong
     * @return
     */
    @Override
    public String getType() {
        return "VIP";
    }

    /**
     * khach hang vip luon nhan duoc chuong trinh khuyen mai ma
     * khong can phai thong qua dang ky
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
}
