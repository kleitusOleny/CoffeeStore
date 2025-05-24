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
    
    public static void main(String[] args) {
       IProduct p1 = new Boba(new Boba(new BubbleTea("Trà Sữa","M","ádsadd",1,20000),5000),5000);
       IProduct p2 = new BubbleTea("Trà Sữa","M","ádsadd",1,20000);
       List<IProduct> baseProducts = new ArrayList<>();
       baseProducts.add(p1);
       baseProducts.add(p2);
       
    }
}
