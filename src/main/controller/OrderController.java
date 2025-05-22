package controller;

import model.order_system.*;
import view.IView;

public class OrderController {
    OrderSystem orderSystem;
    IView view;
    public OrderController(OrderSystem orderSystem, IView view) {
        this.orderSystem = orderSystem;
        this.view = view;
    }

}
