package model.order_system;

import model.IModel;
import utils.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class OrderSystem extends Observable implements IModel {
    private List<Order> listStoreOrder;
    
    public OrderSystem() {
        this.listStoreOrder = new ArrayList<>();
    }
    
    public void addOrder(Order order) {
        listStoreOrder.add(order);
        setChanged();
        notifyObservers(new OrderStatus("ADD_ORDER", "Đã thêm đơn hàng", stateOrder()));
    }
    
    public void addProductToOrder(Order order, IProduct product) {
        order.addItem(product);
        setChanged();
        notifyObservers(new OrderStatus("ADD_PRODUCT", "Đã thêm món: " + product.getInformation(), stateOrder()));
    }
    
    public void removeProductFromOrder(Order order, IProduct product) {
        order.removeItem(product);
        setChanged();
        notifyObservers(new OrderStatus("REMOVE_PRODUCT", "Đã xóa món: " + product.getInformation(), stateOrder()));
    }
    
    public void updateProductQuantity(Order order, IProduct product, int quantity) {
        order.updateQuantity(product, quantity);
        setChanged();
        notifyObservers(new OrderStatus("UPDATE_QUANTITY", "Đã cập nhật số lượng món: " + product.getInformation(), stateOrder()));
    }
    
    public double stateOrder() {
        return listStoreOrder.stream().mapToDouble(Order::totalPrice).sum();
    }
    
    public List<Order> getListStoreOrder() {
        return listStoreOrder;
    }
}