package model.customerSystem;

import model.Subject;

public class NormalCustomer extends Customer {
    private boolean flag;// xem khach hang co dang ky nhan thong bao khuyen mai khong

    public NormalCustomer(String name, String idCus, String numsPhone, Subject subject, boolean flag) {
        super(name, idCus, numsPhone, subject);
        this.flag = flag;
        if (flag) {
            subject.addObserver(this);
        }

    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return "NormalCustomer{" +
                "flag=" + flag +
                ", name='" + name + '\'' +
                ", idCus='" + idCus + '\'' +
                ", numsPhone='" + numsPhone + '\'' +
                '}';
    }


    @Override
    public String updateNotify(String nameTB, String ndTB) {
        String result = "";
        if (isRegister() == true) {
            result = result + "Thông báo mới cho khách hàng: " + this.name + ": " + "\n" + "Tiêu đề: " + nameTB + "\n" + "Nội dung: " + ndTB;
            return result;
        } else {
            return result;
        }
    }


    /**
     * true: da dang ky
     * false: chua dang ky
     *
     * @param trangThaiDangKy
     */
    @Override
    public void setRegisterStatus(boolean trangThaiDangKy) {
        this.flag = trangThaiDangKy;

    }

    @Override
    public String updatePoint(int point1) {
        return "Bạn cần đăng ký VIP để đổi điểm";
    }

    @Override
    public String getType() {
        return "Normal";
    }

    /**
     * kiem tra khach hang thuong co dang ky chuong trinh nhan thong bao khuyen mai khong
     *
     * @return
     */
    @Override
    public boolean isRegister() {
        return flag == true;
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
