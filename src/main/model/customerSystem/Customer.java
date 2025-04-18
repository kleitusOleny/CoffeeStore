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
}
