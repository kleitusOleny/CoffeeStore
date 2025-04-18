package model.customerSystem;

public class VIPCustomer extends Customer {
    private int accumulatedPoints;

    public VIPCustomer(String name, String idCus, String numsPhone, int accumulatedPoints) {
        super(name, idCus, numsPhone);
        this.accumulatedPoints = accumulatedPoints;
    }

    @Override
    public void updateNotify(String nameTB, String ndTB) {

    }

    @Override
    public void updatePoint(int point1) {

    }
}
