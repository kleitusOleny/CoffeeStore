package controller;

import model.order_system.Menu;
import model.order_system.*;
import view.Staff.OrderPanel;

import java.awt.event.ActionListener;

public class OrderController implements IController {
    private OrderSystem orderSystem;
    private OrderPanel view;
    private Order currentOrder;
    private final Menu menu;
    private IProduct lastBaseProduct;
    
    public OrderController(OrderSystem orderSystem, OrderPanel view, Menu menu) {
        this.orderSystem = orderSystem;
        this.view = view;
        this.menu = menu;
        this.currentOrder = new Order(null, null, null);
        orderSystem.addOrder(currentOrder);
        init();
    }
    
    private void init() {
        view.getCafe().addActionListener(e -> view.showCard("coffee"));
        view.getTea().addActionListener(e -> view.showCard("tea"));
        view.getTopping().addActionListener(e -> view.showCard("topping"));
        
        view.setSearchListener(e -> {
            String keyword = view.getSearchField().getText().trim().toLowerCase();
            view.showSearchResults(keyword);
        });
        
        view.setPriceFilterListener(e -> {
            String filter = (String) view.getPriceFilter().getSelectedItem();
            view.showFilteredPanel(filter);
        });
    }
    
    public void addProduct(String name, String size, double price, String itemType) {
        IProduct product = null;
        boolean isTopping = itemType.equals("Boba") || itemType.equals("Bánh flan") ||
                itemType.equals("HoneyBoba") || itemType.equals("CreamCheese");
        
        String[][] drinkList = isTopping ? menu.getToppings() :
                (name.contains("Trà") ? menu.getTeaDrinks() : menu.getCoffeeDrinks());
        
        for (String[] drink : drinkList) {
            if ((isTopping && drink[0].equals(name)) ||
                    (!isTopping && drink[0].equals(itemType))) {
                double basePrice = Double.parseDouble(drink[1]);
                if (!isTopping) {
                    product = switch (itemType) {
                        case "Cà phê đen" -> new BlackCoffee(name, size, "", 1, price);
                        case "Bạc xỉu" -> new WhiteCoffee(name, size, "", 1, price);
                        case "Expresso" -> new Espresso(name, size, "", 1, price);
                        case "Americano" -> new Americano(name, size, "", 1, price);
                        case "Trà đào" -> new PeachTea(name, size, "", 1, price);
                        case "Trà sữa trân châu" -> new BubbleTea(name, size, "", 1, price);
                        default -> product;
                    };
                    orderSystem.addProductToOrder(currentOrder, product);
                    lastBaseProduct = product;
                    view.setLastBaseProduct(product);
                    view.refreshOrderItems(); // Cập nhật giao diện ngay lập tức
                    for (IProduct product1 : currentOrder.getListP()){
                        System.out.print(product1.getInformation() + " | ");
                    }
                    System.out.println();
                } else {
                    if (lastBaseProduct == null) {
                        view.showMessage("Vui lòng chọn một món chính (cà phê hoặc trà) trước khi thêm topping.");
                        return;
                    }
                    product = switch (itemType) {
                        case "Boba" -> new Boba(lastBaseProduct, basePrice);
                        case "Bánh flan" -> new Flan(lastBaseProduct, basePrice);
                        case "HoneyBoba" -> new HoneyBoba(lastBaseProduct, basePrice);
                        case "CreamCheese" -> new CreamCheese(lastBaseProduct, basePrice);
                        default -> product;
                    };
                    if (product instanceof Topping) {
                        ((Topping) product).applyToBaseProduct();
                        view.refreshOrderItems();
                    }
                    orderSystem.notifyObservers();
                }
                break;
            }
        }
        
        if (product == null && !isTopping) {
            view.showMessage("Không tìm thấy món: " + name);
        }
    }
    
    public void removeProduct(IProduct product) {
        if (product instanceof Topping) {
            ((Topping) product).removeFromBaseProduct();
            view.refreshOrderItems();
            orderSystem.notifyObservers();
        } else {
            orderSystem.removeProductFromOrder(currentOrder, product);
            if (product == lastBaseProduct) {
                lastBaseProduct = null;
                view.setLastBaseProduct(null);
            }
            view.refreshOrderItems();
        }
    }
    
    public void updateProductQuantity(IProduct product, int quantity) {
        if (product instanceof Topping) {
            ((Topping) product).updateQuantity(quantity);
            view.refreshOrderItems();
            orderSystem.notifyObservers();
        } else {
            orderSystem.updateProductQuantity(currentOrder, product, quantity);
            view.refreshOrderItems();
        }
    }
    
    
}