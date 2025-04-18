package model.orderSystem;

import java.util.List;

public class OrderSystem {
    private List<Order> listStoreOrder;
    //private Queue<Order> queueStoreOrder;   --It's needed?

    public OrderSystem(List<Order> listStoreOrder) {
        this.listStoreOrder = listStoreOrder;
    }
    public double stateOrder(){
        return 0.0;
    }
    public void addOrder(Order o){
    }
}
