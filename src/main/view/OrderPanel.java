package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class OrderPanel extends JPanel{
    JPanel toolbar, orderBillPanel, mainPanel;
    JButton cafe, tea, topping;
    private DefaultListModel<String> orderListModel = new DefaultListModel<>();
    private JLabel totalLabel;
    private JPanel cardPanel;
    private CardLayout cardLayout;


    public OrderPanel(){
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 204));

        // JPanel ben trai
        ManagerMenuPanel sidebarPanel = new ManagerMenuPanel();
        add(sidebarPanel, BorderLayout.WEST);

        // Phần chính trung tâm: toolbar + menu
        add(createMainPanel(), BorderLayout.CENTER);

        // Order bill bên phải
        add(createOrderBillPanel(), BorderLayout.EAST);

    }

    private JPanel createOrderBillPanel() {
        orderBillPanel = new JPanel();
        orderBillPanel.setLayout(new BorderLayout());
        orderBillPanel.setPreferredSize(new Dimension(220, 0));
        orderBillPanel.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel("Order bill", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        orderBillPanel.add(title, BorderLayout.NORTH);

        JList<String> orderList = new JList<>(orderListModel);
        JScrollPane scrollPane = new JScrollPane(orderList);
        orderBillPanel.add(scrollPane, BorderLayout.CENTER);

        totalLabel = new JLabel("Tổng tiền: ...", JLabel.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
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

        JPanel searchPanel = createSearchBoxWithButton();

        toolbar.add(cafe);
        toolbar.add(tea);
        toolbar.add(topping);
        toolbar.add(Box.createHorizontalStrut(30));
        toolbar.add(searchPanel);

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
        panel.setPreferredSize(new Dimension(160, 28));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JTextField searchField = new JTextField();
        searchField.setBorder(null);
        searchField.setPreferredSize(new Dimension(130, 28));
        searchField.setOpaque(true);

        // Tạo nút tìm kiếm có icon
        JButton searchButton = new JButton();
        searchButton.setFocusable(false);
        searchButton.setBorder(null);
        searchButton.setContentAreaFilled(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Thêm icon tìm kiếm
        try {
            ImageIcon iconAdd = new ImageIcon("src\\image\\search.png");
            Image image = iconAdd.getImage();
            Image newImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon icon1 = new ImageIcon(newImage);
            searchButton.setIcon(icon1);
        } catch (Exception e) {
            searchButton.setText("🔍"); // fallback nếu ảnh lỗi
        }

        searchButton.addActionListener(e -> {
            String keyword = searchField.getText();
            JOptionPane.showMessageDialog(this, "Đang tìm: " + keyword);
        });

        panel.add(searchField, BorderLayout.WEST);
        panel.add(searchButton, BorderLayout.EAST);

        return panel;
    }

    private final String[][] coffeeDrinks = {
            {"Cà phê đen", "25000", "src\\image\\coffee.png"},
            {"Bạc xỉu", "22000", "src\\image\\milkcoffee.png"},
            {"Expresso", "28000", "src\\image\\expresso.png"},
            {"Americano", "28000", "src\\image\\americano.png"},
    };

    private final String[][] teaDrinks = {
            {"Trà đào", "30000", "src\\image\\peachtea.png"},
            {"Trà sữa trân châu", "32000", "src\\image\\milktea.png"},
    };

    private final String[][] toppings = {
            {"Trân châu mật ong", "5000", "src\\image\\honeyboba.png"},
            {"Trân châu", "5000", "src\\image\\boba.png"},
            {"Kem cheese", "6000", "src\\image\\creamcheese.png"},
            {"Bánh flan", "7000", "src\\image\\flan.png"}
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
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel priceLabel = new JLabel(price + "\u0111", JLabel.CENTER);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 11));

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);

        btn.add(infoPanel, BorderLayout.SOUTH);

        //  Thêm chức năng khi bấm nút
        btn.addActionListener(e -> {
            orderListModel.addElement(name + " - " + price + "\u0111");
            updateTotal();
        });

        return btn;
    }

    private void updateTotal() {
        int total = 0;
        for (int i = 0; i < orderListModel.getSize(); i++) {
            String item = orderListModel.getElementAt(i);
            String[] parts = item.split(" - ");
            if (parts.length == 2) {
                String priceStr = parts[1].replace("đ", "").replace(".", "").trim();
                try {
                    total += Integer.parseInt(priceStr);
                } catch (NumberFormatException ignored) {
                }
            }
        }
        totalLabel.setText("Tổng tiền: " + total + "đ");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Đặt món - Giao diện hoàn chỉnh");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setContentPane(new OrderPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
