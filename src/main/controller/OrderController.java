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
        boolean isTopping = itemType.equals("Boba") || itemType.equals("Flan") ||
                itemType.equals("HoneyBoba") || itemType.equals("CreamCheese");

        String[][] drinkList = isTopping ? menu.getToppings() :
                (name.contains("Trà") ? menu.getTeaDrinks() : menu.getCoffeeDrinks());

        for (String[] drink : drinkList) {
            if ((isTopping && drink[0].equals(name)) ||
                    (!isTopping && drink[0].equals(itemType))) {
                double basePrice = Double.parseDouble(drink[1]);
                if (!isTopping) {
                    if (itemType.equals("Cà phê đen")) {
                        product = new BlackCoffee(name, size, "", 1, price);
                    } else if (itemType.equals("Bạc xỉu")) {
                        product = new WhiteCoffee(name, size, "", 1, price);
                    } else if (itemType.equals("Espresso")) {
                        product = new Espresso(name, size, "", 1, price);
                    } else if (itemType.equals("Americano")) {
                        product = new Americano(name, size, "", 1, price);
                    } else if (itemType.equals("Trà đào")) {
                        product = new PeachTea(name, size, "", 1, price);
                    } else if (itemType.equals("Trà sữa trân châu")) {
                        product = new BubbleTea(name, size, "", 1, price);
                    }
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
                    switch (itemType) {
                        case "Boba":
                            product = new Boba(lastBaseProduct, basePrice);
                            break;
                        case "Flan":
                            product = new Flan(lastBaseProduct, basePrice);
                            break;
                        case "HoneyBoba":
                            product = new HoneyBoba(lastBaseProduct, basePrice);
                            break;
                        case "CreamCheese":
                            product = new CreamCheese(lastBaseProduct, basePrice);
                            break;
                    }
                    if (product instanceof Topping) {
                        ((Topping) product).applyToBaseProduct();
                        view.refreshOrderItems(); // Cập nhật giao diện ngay lập tức sau khi thêm topping
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
            view.refreshOrderItems(); // Cập nhật giao diện ngay lập tức
            orderSystem.notifyObservers();
        } else {
            orderSystem.removeProductFromOrder(currentOrder, product);
            if (product == lastBaseProduct) {
                lastBaseProduct = null;
                view.setLastBaseProduct(null);
            }
            view.refreshOrderItems(); // Cập nhật giao diện ngay lập tức
        }
    }

    public void updateProductQuantity(IProduct product, int quantity) {
        if (product instanceof Topping) {
            ((Topping) product).updateQuantity(quantity);
            view.refreshOrderItems(); // Cập nhật giao diện ngay lập tức
            orderSystem.notifyObservers();
        } else {
            orderSystem.updateProductQuantity(currentOrder, product, quantity);
            view.refreshOrderItems(); // Cập nhật giao diện ngay lập tức
        }
    }
}