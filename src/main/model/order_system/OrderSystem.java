package model.order_system;

import java.util.ArrayList;
import java.util.List;

public class OrderSystem {
    private List<Order> listStoreOrder;
    //private Queue<Order> queueStoreOrder;   --It's needed?

    public OrderSystem() {
        this.listStoreOrder = new ArrayList<>();
    }

    public double stateOrder(){
        return listStoreOrder.stream().mapToDouble(Order::totalPrice).sum();
    }
    
    public void addOrder(Order o){
        listStoreOrder.add(o);
    }

    public List<Order> getListStoreOrder() {
        return listStoreOrder;
    }
}
