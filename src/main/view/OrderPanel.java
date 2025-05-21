package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrderPanel extends JPanel{
    JPanel toolbar, orderBillPanel, mainPanel, orderItemsPanel, searchResultPanel, searchPanel;
    JButton cafe, tea, topping, searchButton;
    private DefaultListModel<String> orderListModel = new DefaultListModel<>();
    private JLabel totalLabel, title;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private java.util.List<OrderItem> orderItems = new ArrayList<>();
    private JComboBox<String> priceFilter;
    private boolean hasSelectedTea = false;
    private CustomTextField searchField;
    private JScrollPane scrollPane;

    public OrderPanel(){
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 204));

        // Phần chính trung tâm: toolbar + menu
        add(createMainPanel(), BorderLayout.CENTER);

        // Order bill bên phải
        add(createOrderBillPanel(), BorderLayout.EAST);

    }

    public JButton getCafe() {
        return cafe;
    }

    public JButton getTea() {
        return tea;
    }

    public JButton getTopping() {
        return topping;
    }

    private JPanel createOrderBillPanel() {
        orderBillPanel = new JPanel();
        orderBillPanel.setLayout(new BorderLayout());
        orderBillPanel.setPreferredSize(new Dimension(280, 0));
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

        totalLabel = new JLabel("Tổng tiền: ...", JLabel.CENTER);
        totalLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        orderBillPanel.add(totalLabel, BorderLayout.SOUTH);

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
        toolbar.setBackground(new Color(240, 200, 160));
        toolbar.setPreferredSize(new Dimension(0, 50));

        cafe = createMenuButton("Cà phê");
        tea = createMenuButton("Trà");
        topping = createMenuButton("Topping");

        cafe.addActionListener(e -> cardLayout.show(cardPanel, "coffee"));
        tea.addActionListener(e -> cardLayout.show(cardPanel, "tea"));
        topping.addActionListener(e -> cardLayout.show(cardPanel, "topping"));

        searchPanel = createSearchBoxWithButton();


        priceFilter = new JComboBox<>(new String[]{"Tất cả", "< 25.000đ", "25.000 - 30.000đ", "> 30.000đ"});

        priceFilter.setFont(new Font("Roboto", Font.BOLD, 15));
        priceFilter.addActionListener(e -> {
            String selected = (String) priceFilter.getSelectedItem();
            JPanel filteredPanel = createFilteredPanel(selected);
            cardPanel.add(filteredPanel, "filter");
            cardLayout.show(cardPanel, "filter");

        });

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
        button.setBackgroundColor(new Color(166, 123, 91));
        button.setGradientColors(Color.YELLOW, Color.GRAY);
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(20);
        return button;
    }
    private CustomButton createDrinkBtn(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(1000, 200));
        button.setBackgroundColor(new Color(255, 245, 204));
        button.setGradientColors(Color.YELLOW, Color.GRAY);
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(20);
        return button;
    }

    private JPanel createSearchBoxWithButton() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(200, 28));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        searchField = new CustomTextField(10);

        searchField.setBorder(null);
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setOpaque(true);
        searchField.setFont(new Font("Roboto", Font.BOLD, 16));
        searchField.setForeground(new Color(166, 123, 91));

        // Tạo nút tìm kiếm có icon
        searchButton = new JButton();
        searchButton.setFocusable(false);
        searchButton.setBorder(null);
        searchButton.setContentAreaFilled(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Thêm icon tìm kiếm
        try {
            ImageIcon iconAdd = new ImageIcon("src\\main\\image\\search.png");
            Image image = iconAdd.getImage();
            Image newImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon icon1 = new ImageIcon(newImage);
            searchButton.setIcon(icon1);
        } catch (Exception e) {
            searchButton.setText("🔍"); // fallback nếu ảnh lỗi
        }

        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim().toLowerCase();
            if (keyword.isEmpty()) return;

            searchResultPanel = new JPanel(new GridLayout(3, 3, 10, 10));
            searchResultPanel.setBackground(new Color(255, 245, 204));
            searchResultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            java.util.List<String[]> allDrinks = new ArrayList<>();
            for (String[] drink : coffeeDrinks) allDrinks.add(drink);
            for (String[] drink : teaDrinks) allDrinks.add(drink);
            for (String[] drink : toppings) allDrinks.add(drink);

            for (String[] drink : allDrinks) {
                String name = drink[0].toLowerCase();
                if (name.contains(keyword)) {
                    JButton btn = createDrinkButton(drink[0], drink[1], drink[2]);
                    searchResultPanel.add(btn);
                }
            }

            cardPanel.add(searchResultPanel, "search");
            cardLayout.show(cardPanel, "search");
        });

        panel.add(searchField, BorderLayout.WEST);
        panel.add(searchButton, BorderLayout.EAST);

        return panel;
    }

    private final String[][] coffeeDrinks = {
            {"Cà phê đen", "25000", "src\\main\\image\\coffee.png"},
            {"Bạc xỉu", "22000", "src\\main\\image\\milkcoffee.png"},
            {"Expresso", "28000", "src\\main\\image\\expresso.png"},
            {"Americano", "28000", "src\\main\\image\\americano.png"},
    };

    private final String[][] teaDrinks = {
            {"Trà đào", "30000", "src\\main\\image\\peachtea.png"},
            {"Trà sữa trân châu", "32000", "src\\main\\image\\milktea.png"},
    };

    private final String[][] toppings = {
            {"Trân châu mật ong", "5000", "src\\main\\image\\honeyboba.png"},
            {"Trân châu", "5000", "src\\main\\image\\boba.png"},
            {"Kem cheese", "6000", "src\\main\\image\\creamcheese.png"},
            {"Bánh flan", "7000", "src\\main\\image\\flan.png"}
    };

    private JPanel createDrinkGridPanel(String type) {
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gridPanel.setBackground(new Color(255, 245, 204));

        // Dữ liệu các món
        String[][] drinks;

        switch (type) {
            case "coffee":
                drinks = coffeeDrinks;
                break;
            case "tea":
                drinks = teaDrinks;
                break;
            case "topping":
                drinks = toppings;
                break;
            default:
                drinks = new String[0][0]; // fallback
        }

        for (String[] drink : drinks) {
            JButton btn = createDrinkButton(drink[0], drink[1], drink[2]);
            gridPanel.add(btn);
        }

        return gridPanel;
    }

    private JButton createDrinkButton(String name, String priceStr, String imagePath) {
        int price = Integer.parseInt(priceStr); // Giá tiền dùng cho tính tổng
        JButton btn = createDrinkBtn(" ");
        btn.setLayout(new BorderLayout());
        btn.setBackground(new Color(255, 245, 204));

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

        JLabel priceLabel = new JLabel(price + "\u0111", JLabel.CENTER);
        priceLabel.setFont(new Font("Roboto", Font.PLAIN, 20));

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);

        btn.add(infoPanel, BorderLayout.SOUTH);

        //  Thêm chức năng khi bấm nút
        btn.addActionListener(e -> {

            // Kiểm tra nếu là topping thì phải chọn trà trước
            if (name.equals("Trân châu") || name.contains("Kem") || name.contains("Flan") || name.contains("mật ong")) {
                if (!hasSelectedTea) {
                    JOptionPane.showMessageDialog(this, "Bạn cần chọn loại trà trước khi thêm topping.");
                    return;
                }
            }

            if (name.contains("Trà")) {
                hasSelectedTea = true; // Đã chọn trà rồi
            }

            OrderItem orderItem = new OrderItem(name, price);
            JPanel itemPanel = orderItem.createPanel(
                    this::updateTotal,
                    () -> {
                        orderItemsPanel.remove(orderItem.panel);
                        orderItems.remove(orderItem);
                        updateTotal();
                        orderItemsPanel.revalidate();
                        orderItemsPanel.repaint();
                    }
            );
            orderItems.add(orderItem);
            orderItemsPanel.add(itemPanel);
            orderItemsPanel.revalidate();
            orderItemsPanel.repaint();
            updateTotal();

        });

        return btn;
    }

    private JPanel createFilteredPanel(String filter) {
        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        panel.setBackground(new Color(255, 245, 204));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Gộp toàn bộ drink lại để lọc
        java.util.List<String[]> allDrinks = new ArrayList<>();
        for (String[] drink : coffeeDrinks) allDrinks.add(drink);
        for (String[] drink : teaDrinks) allDrinks.add(drink);
        for (String[] drink : toppings) allDrinks.add(drink);

        for (String[] drink : allDrinks) {
            int price = Integer.parseInt(drink[1]);
            boolean match = switch (filter) {
                case "< 25.000đ" -> price < 25000;
                case "25.000 - 30.000đ" -> price >= 25000 && price <= 30000;
                case "> 30.000đ" -> price > 30000;
                default -> true;
            };
            if (match) {
                JButton btn = createDrinkButton(drink[0], drink[1], drink[2]);
                panel.add(btn);
            }
        }
        return panel;
    }



    private void updateTotal() {
        int total = 0;
        for (OrderItem item : orderItems) {
            total += item.getTotal();
        }
        totalLabel.setText("Tổng tiền: " + String.format("%,d", total) + "đ");
    }

}
