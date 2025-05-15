package model.customerSystem;


import model.Subject;

public abstract class Customer implements Observer {
    protected String name;
    protected String idCus;
    protected String numsPhone;
    protected Subject subject;
    public Customer(String name, String idCus, String numsPhone) {
        this.name = name;
        this.idCus = idCus;
        this.numsPhone = numsPhone;
    }

    public Customer(String name, String idCus, String numsPhone, Subject subject) {
        this.name = name;
        this.idCus = idCus;
        this.numsPhone = numsPhone;
        subject.addObserver(this);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", idCus='" + idCus + '\'' +
                ", numsPhone='" + numsPhone + '\'' +
                '}';
    }
    public Customer() {
    }

    public abstract String updateNotify(String nameTB, String ndTB);

    public void updateInforCustomer(String newName, String newSDT, boolean trangThaiDangKy) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName;
        }
        if (newSDT != null && !newSDT.trim().isEmpty()) {
            this.numsPhone = newSDT;
        }

        // Gọi phương thức trừu tượng hoặc được override ở lớp con để cập nhật trạng thái đăng ký
        setRegisterStatus(trangThaiDangKy);
    }

    public abstract void setRegisterStatus(boolean trangThaiDangKy) ;

    public abstract String updatePoint(int point1);
    public abstract String getType();
    /**
     * isRegister
     * @return
     * true: khach hang da dang ky chuong trinh khuyen mai
     * false: khach hang chua dang ky chuong trinh khuyen mai
     */
    public abstract boolean isRegister();// kiem tra co dăng ky thong bao hay khong

    /**
     *countProductedBuy : đếm số lượng sản phẩm được mua bởi từng khách
     * @return
     */
    public abstract int countProductedBuy();

    public String getName() {
        return name;
    }

    public String getIdCus() {
        return idCus;
    }

    public String getNumsPhone() {
        return numsPhone;
    }

    public abstract boolean isVIP() ;
    public  abstract  int pointAccumulated ();

}
