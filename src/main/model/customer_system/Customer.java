package model.customer_system;


import javax.security.auth.Subject;

public abstract class Customer{
    protected String name;
    protected String idCus;
    protected String numsPhone;
    protected Subject subject;

    public Customer(String name, String idCus, String numsPhone) {
        this.name = name;
        this.idCus = idCus;
        this.numsPhone = numsPhone;
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
    

    public void updateInforCustomer(String newName, String newSDT) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName;
        }
        if (newSDT != null && !newSDT.trim().isEmpty()) {
            this.numsPhone = newSDT;
        }
    }
    

    public abstract String updatePoint(int point1);

    public abstract String getType();

    /**
     * isRegister
     *
     * @return true: khach hang da dang ky chuong trinh khuyen mai
     * false: khach hang chua dang ky chuong trinh khuyen mai
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

    public abstract boolean isVIP();

    public abstract int pointAccumulated();


}
