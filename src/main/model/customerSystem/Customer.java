package model.customerSystem;

public abstract class Customer implements Observer {
    protected String name;
    protected String idCus;
    protected String numsPhone;
    protected boolean flag;

    public Customer(String name, String idCus, String numsPhone, boolean flag) {
        this.name = name;
        this.idCus = idCus;
        this.numsPhone = numsPhone;
        this.flag = flag;
    }

    public Customer(String name, String idCus, String numsPhone) {
        this.name = name;
        this.idCus = idCus;
        this.numsPhone = numsPhone;
    }

    public Customer() {
    }

    public abstract void updateNotify(String nameTB, String ndTB);

    public void updateInforCustomer() {

    }

    public abstract void updatePoint(int point1);
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
}
