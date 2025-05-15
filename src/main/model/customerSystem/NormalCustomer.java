package model.customerSystem;

public class NormalCustomer extends Customer {
    private boolean flag;// xem khach hang co dang ky nhan thong bao khuyen mai khong

    public NormalCustomer(String name, String idCus, String numsPhone, boolean flag) {
        super(name, idCus, numsPhone);
        this.flag = false;
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
    public void updateNotify(String nameTB, String ndTB) {
        if (isRegister() == true){
            System.out.println("Thông báo mới cho khách hàng: "+this.name+": ");
            System.out.println("Tiêu đề: "+nameTB);
            System.out.println("Nội dung: "+ndTB);
        }else {
            System.out.println("Bạn cần đăng ký để có thể nhận được thông báo từ cửa hàng! ");
        }

    }


    /**
     * true: da dang ky
     * false: chua dang ky
     * @param trangThaiDangKy
     */
    @Override
    protected void setRegisterStatus(boolean trangThaiDangKy) {
        this.flag = trangThaiDangKy;
    }

    @Override
    public void updatePoint(int point1) {

    }

    @Override
    public String getType() {
        return "Normal";
    }

    /**
     * kiem tra khach hang thuong co dang ky chuong trinh nhan thong bao khuyen mai khong
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
