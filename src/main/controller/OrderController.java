package controller;

import model.order_system.*;
import view.IView;
import view.Staff.OrderItem;
import view.Staff.OrderPanel;

import java.util.List;

public class OrderController {
    OrderSystem orderSystem;
    IView view;
    public OrderController(OrderSystem orderSystem, IView view) {
        this.orderSystem = orderSystem;
        this.view = view;
    }

    public int[][] analysisInput() {
        
        List<OrderItem> list =  OrderPanel.getOrderItems();
        int size = 0;
        int[][]res = new int[list.size()][];
        return res;
    }
}
