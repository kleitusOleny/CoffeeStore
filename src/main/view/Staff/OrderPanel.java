package view.Staff;

import controller.OrderController;
import model.IModel;
import model.order_system.Menu;
import model.order_system.BaseProduct;
import model.order_system.IProduct;
import model.order_system.Topping;
import utils.OrderStatus;
import view.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OrderPanel extends JPanel implements Observer {
    private JPanel toolbar, orderBillPanel, mainPanel, orderItemsPanel, searchResultPanel, searchPanel, sizePanel, sumPanel;
    private CustomButton cafe, tea, topping;
    private JButton searchButton;
    private JLabel totalLabel, title;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JComboBox<String> priceFilter;
    private boolean hasSelectedTea = false;
    private JTextField searchField;
    private JScrollPane scrollPane;
    private JRadioButton sizeM, sizeL;
    private ButtonGroup sizeGroup;
    private IModel model;
    private OrderController controller;
    private IProduct lastBaseProduct;
    private final Menu menu;

    public OrderPanel(IModel model) {
        this.model = model;
        this.menu = new Menu();
        model.addObserver(this);
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 204));

        mainPanel = createMainPanel();
        orderBillPanel = createOrderBillPanel();
        add(mainPanel, BorderLayout.CENTER);
        add(orderBillPanel, BorderLayout.EAST);

        this.controller = new OrderController((model.order_system.OrderSystem) model, this, menu);
    }

    public CustomButton getCafe() {
        return cafe;
    }

    public CustomButton getTea() {
        return tea;
    }

    public CustomButton getTopping() {
        return topping;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JComboBox<String> getPriceFilter() {
        return priceFilter;
    }

    public void setSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void setPriceFilterListener(ActionListener listener) {
        priceFilter.addActionListener(listener);
    }

    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }

    public void showSearchResults(String keyword) {
        if (keyword.isEmpty()) return;

        searchResultPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        searchResultPanel.setBackground(new Color(255, 245, 204));
        searchResultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<String[]> allDrinks = new ArrayList<>();
        for (String[] drink : menu.getCoffeeDrinks()) allDrinks.add(drink);
        for (String[] drink : menu.getTeaDrinks()) allDrinks.add(drink);
        for (String[] drink : menu.getToppings()) allDrinks.add(drink);

        for (String[] drink : allDrinks) {
            String name = drink[0].toLowerCase();
            if (name.contains(keyword)) {
                CustomButton btn = createDrinkButton(drink[0], drink[1], drink[2]);
                searchResultPanel.add(btn);
            }
        }

        cardPanel.add(searchResultPanel, "search");
        cardLayout.show(cardPanel, "search");
    }

    public void showFilteredPanel(String filter) {
        JPanel filteredPanel = createFilteredPanel(filter);
        cardPanel.add(filteredPanel, "filter");
        cardLayout.show(cardPanel, "filter");
    }

    public IProduct getLastBaseProduct() {
        return lastBaseProduct;
    }

    public void setLastBaseProduct(IProduct product) {
        this.lastBaseProduct = product;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }

    private JPanel createOrderBillPanel() {
        orderBillPanel = new JPanel();
        orderBillPanel.setLayout(new BorderLayout());
        orderBillPanel.setPreferredSize(new Dimension(350, 0));
        orderBillPanel.setBackground(Color.WHITE);

        title = new JLabel("Order bill", JLabel.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 16));
        orderBillPanel.add(title, BorderLayout.NORTH);

        orderItemsPanel = new JPanel();
        orderItemsPanel.setLayout(new BoxLayout(orderItemsPanel, BoxLayout.Y_AXIS));
        orderItemsPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(orderItemsPanel);
        scrollPane.setBorder(null);
        orderBillPanel.add(scrollPane, BorderLayout.CENTER);

        totalLabel = new JLabel("Tổng tiền: 0đ", JLabel.CENTER);
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        sumPanel = new JPanel();
        sumPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        sumPanel.setBackground(new Color(166, 123, 91));
        sumPanel.add(totalLabel);
        orderBillPanel.add(sumPanel, BorderLayout.SOUTH);
        return orderBillPanel;
    }

    private JPanel createMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 245, 204));

        mainPanel.add(createToolbar(), BorderLayout.NORTH);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(new Color(255, 245, 204));

        cardPanel.add(createDrinkGridPanel("coffee"), "coffee");
        cardPanel.add(createDrinkGridPanel("tea"), "tea");
        cardPanel.add(createDrinkGridPanel("topping"), "topping");

        cardPanel.add(createFilteredPanel("<25000"), "<25000");
        cardPanel.add(createFilteredPanel("25-30"), "25-30");
        cardPanel.add(createFilteredPanel(">30000"), ">30000");

        mainPanel.add(new JScrollPane(cardPanel), BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createToolbar() {
        toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.setBackground(new Color(240,200,160));
        toolbar.setPreferredSize(new Dimension(0, 50));

        cafe = createMenuButton("Cà phê");
        tea = createMenuButton("Trà");
        topping = createMenuButton("Topping");

        searchPanel = createSearchBoxWithButton();

        priceFilter = new JComboBox<>(new String[]{"Tất cả", "< 25.000đ", "25.000 - 30.000đ", "> 30.000đ"});
        priceFilter.setFont(new Font("Roboto", Font.BOLD, 15));

        toolbar.add(cafe);
        toolbar.add(tea);
        toolbar.add(topping);
        toolbar.add(Box.createHorizontalStrut(30));
        toolbar.add(Box.createHorizontalStrut(30));
        toolbar.add(Box.createHorizontalStrut(30));
        toolbar.add(Box.createHorizontalStrut(30));
        toolbar.add(searchPanel);
        toolbar.add(Box.createHorizontalStrut(20));
        toolbar.add(priceFilter);

        return toolbar;
    }

    private CustomButton createMenuButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(1000, 200));
        button.setBackgroundColor(new Color(166,123,91));
        button.setTextColor(Color.BLACK);
        button.setBorderRadius(20);
        return button;
    }

    private CustomButton createDrinkButton(String name, String priceStr, String imagePath) {
        double price = Double.parseDouble(priceStr);
        CustomButton btn = new CustomButton(" ");
        btn.setLayout(new BorderLayout());
        btn.setBackgroundColor(new Color(255, 245, 204));

        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaled));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            btn.add(imageLabel, BorderLayout.NORTH);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("[ảnh]", JLabel.CENTER);
            btn.add(placeholder, BorderLayout.NORTH);
        }

        JLabel nameLabel = new JLabel(name, JLabel.CENTER);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 25));
        nameLabel.setForeground(Color.BLACK);

        JLabel priceLabel = new JLabel(String.format("%,.0f", price) + "\u0111", JLabel.CENTER);
        priceLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        priceLabel.setForeground(Color.BLACK);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);

        btn.add(infoPanel, BorderLayout.SOUTH);

        btn.addActionListener(e -> {
            boolean isTopping = name.equals("Trân châu") || name.contains("Kem") || name.contains("Bánh flan") || name.contains("mật ong");
            String itemType = isTopping ? getToppingType(name) : getItemType(name);
            if (isTopping) {
                controller.addProduct(name, null, price, itemType);
                return;
            }                
                sizeM = new JRadioButton("Size M (mặc định)");
                sizeL = new JRadioButton("Size L (+5.000đ)");
                sizeM.setSelected(true);
                sizeGroup = new ButtonGroup();
                sizeGroup.add(sizeM);
                sizeGroup.add(sizeL);
                
                sizePanel = new JPanel(new GridLayout(2, 1));
                sizePanel.add(sizeM);
                sizePanel.add(sizeL);
                
                int option = JOptionPane.showConfirmDialog(
                        this,
                        sizePanel,
                        "Chọn size cho " + name,
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
                
                if (option == JOptionPane.OK_OPTION) {
                    String size = sizeM.isSelected() ? "M" : "L";
                    double finalPrice = price + (size.equals("L") ? 5000 : 0);
                    controller.addProduct(name, size, finalPrice, itemType);
                }
        });

        return btn;
    }

    private String getToppingType(String name) {
        if (name.contains("Trân châu mật ong")) return "HoneyBoba";
        if (name.contains("Trân châu")) return "Boba";
        if (name.contains("Kem")) return "CreamCheese";
        if (name.contains("Bánh flan")) return "Bánh flan";
        return null;
    }

    private String getItemType(String name) {
        for (String[] drink : menu.getCoffeeDrinks()) {
            if (drink[0].equals(name)) return drink[0];
        }
        for (String[] drink : menu.getTeaDrinks()) {
            if (drink[0].equals(name)) return drink[0];
        }
        return name;
    }

    private JPanel createSearchBoxWithButton() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 28));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        searchField = new JTextField(20);
        searchField.setBorder(null);
        searchField.setPreferredSize(new Dimension(200, 28));
        searchField.setOpaque(true);
        searchField.setFont(new Font("Roboto", Font.BOLD, 16));
        searchField.setForeground(new Color(166, 123, 91));

        searchButton = new JButton();
        searchButton.setFocusable(false);
        searchButton.setBorder(null);
        searchButton.setContentAreaFilled(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        try {
            ImageIcon iconAdd = new ImageIcon("src\\main\\image\\search.png");
            Image image = iconAdd.getImage();
            Image newImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon icon1 = new ImageIcon(newImage);
            searchButton.setIcon(icon1);
        } catch (Exception e) {
            searchButton.setText("🔍");
        }

        panel.add(searchField, BorderLayout.WEST);
        panel.add(searchButton, BorderLayout.EAST);

        return panel;
    }

    private JPanel createDrinkGridPanel(String type) {
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gridPanel.setBackground(new Color(255, 245, 204));

        String[][] drinks;
        switch (type) {
            case "coffee":
                drinks = menu.getCoffeeDrinks();
                break;
            case "tea":
                drinks = menu.getTeaDrinks();
                break;
            case "topping":
                drinks = menu.getToppings();
                break;
            default:
                drinks = new String[0][0];
        }

        for (String[] drink : drinks) {
            CustomButton btn = createDrinkButton(drink[0], drink[1], drink[2]);
            gridPanel.add(btn);
        }

        return gridPanel;
    }

    private JPanel createFilteredPanel(String filter) {
        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        panel.setBackground(new Color(255, 245, 204));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<String[]> allDrinks = new ArrayList<>();
        for (String[] drink : menu.getCoffeeDrinks()) allDrinks.add(drink);
        for (String[] drink : menu.getTeaDrinks()) allDrinks.add(drink);
        for (String[] drink : menu.getToppings()) allDrinks.add(drink);

        for (String[] drink : allDrinks) {
            double price = Double.parseDouble(drink[1]);
            boolean match = switch (filter) {
                case "< 25.000đ" -> price < 25000;
                case "25.000 - 30.000đ" -> price >= 25000 && price <= 30000;
                case "> 30.000đ" -> price > 30000;
                default -> true;
            };
            if (match) {
                CustomButton btn = createDrinkButton(drink[0], drink[1], drink[2]);
                panel.add(btn);
            }
        }
        return panel;
    }

    private void updateTotal(double total) {
        totalLabel.setText("Tổng tiền: " + String.format("%,.0f", total) + "đ");
    }

    // Phương thức mới để làm mới danh sách đơn hàng
    public void refreshOrderItems() {
        orderItemsPanel.removeAll();
        List<IProduct> products = ((model.order_system.OrderSystem) model).getListStoreOrder().get(0).getListP();
        double totalPrice = 0.0;
        for (IProduct product : products) {
            JPanel itemPanel = createOrderItemPanel(product);
            orderItemsPanel.add(itemPanel);
            totalPrice += product.getPrice();
            // Hiển thị các topping nếu có
            if (product instanceof BaseProduct) {
                List<Topping> toppings = ((BaseProduct) product).getToppings();
                for (Topping topping : toppings) {
                    JPanel toppingPanel = createOrderItemPanel(topping);
                    orderItemsPanel.add(toppingPanel);
                }
            }
        }
        updateTotal(totalPrice);
        orderItemsPanel.revalidate();
        orderItemsPanel.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof OrderStatus) {
            OrderStatus status = (OrderStatus) arg;
            switch (status.getAction()) {
                case "ADD_PRODUCT":
                case "REMOVE_PRODUCT":
                case "UPDATE_QUANTITY":
                    refreshOrderItems();
                    JOptionPane.showMessageDialog(this, status.getMessage(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }
    }

    private JPanel createOrderItemPanel(IProduct product) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(260, 40));
        panel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(product.getInformation());
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        nameLabel.setPreferredSize(new Dimension(80, 20));

        JButton deleteBtn = new JButton();
        ImageIcon iconDelete = new ImageIcon("src\\main\\image\\bin.png");
        Image image = iconDelete.getImage();
        Image newImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImage);
        deleteBtn.setIcon(icon);
        deleteBtn.setForeground(Color.RED);
        deleteBtn.setMargin(new Insets(2, 6, 2, 6));
        deleteBtn.addActionListener(e -> controller.removeProduct(product));

        JButton plusBtn = new JButton();
        ImageIcon iconPlus = new ImageIcon("src\\main\\image\\plus.png");
        Image image2 = iconPlus.getImage();
        Image newImage2 = image2.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(newImage2);
        plusBtn.setIcon(icon2);
        plusBtn.setMargin(new Insets(2, 6, 2, 6));
        plusBtn.addActionListener(e -> controller.updateProductQuantity(product, product.getQuantity() + 1));

        JButton minusBtn = new JButton();
        ImageIcon iconMinus = new ImageIcon("src\\main\\image\\minus.png");
        Image image3 = iconMinus.getImage();
        Image newImage3 = image3.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(newImage3);
        minusBtn.setIcon(icon3);
        minusBtn.setMargin(new Insets(2, 6, 2, 6));
        minusBtn.addActionListener(e -> {
            if (product.getQuantity() > 1) {
                controller.updateProductQuantity(product, product.getQuantity() - 1);
            }
        });

        JLabel quantityLabel = new JLabel("x" + product.getQuantity());
        quantityLabel.setPreferredSize(new Dimension(30, 20));
        quantityLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 2));
        controlPanel.setOpaque(false);
        controlPanel.add(minusBtn);
        controlPanel.add(quantityLabel);
        controlPanel.add(plusBtn);
        controlPanel.add(deleteBtn);

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(controlPanel, BorderLayout.EAST);

        if (product instanceof model.order_system.Topping) {
            panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        }

        return panel;
    }
}