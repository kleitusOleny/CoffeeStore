package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MenuEditorPanel extends JPanel {
    private JPanel mainPanel, toolbar;

    private JPanel cardPanel;
    private CardLayout cardLayout;

    private JButton cafeBtn, teaBtn, toppingBtn, addButton, deleteBtn, drinkBtn, searchButton;
    private JLabel nameLabel, priceLabel;

    private CustomTextField searchField;

    private final java.util.List<String[][]> drinkData = new ArrayList<>();
    private final String[] types = {"coffee", "tea", "topping"};

    public MenuEditorPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 204));

        // Dummy drink data
        drinkData.add(new String[][]{
                {"Cà phê đen", "25000", "src\\main\\image\\coffee.png"},
                {"Bạc xỉu", "22000", "src\\main\\image\\milkcoffee.png"},
                {"Expresso", "28000", "src\\main\\image\\expresso.png"},
                {"Americano", "28000", "src\\main\\image\\americano.png"}
        });
        drinkData.add(new String[][]{
                {"Trà đào", "30000", "src\\main\\image\\peachtea.png"},
                {"Trà sữa trân châu", "32000", "src\\main\\image\\milktea.png"}
        });
        drinkData.add(new String[][]{
                {"Trân châu mật ong", "5000", "src\\main\\image\\honeyboba.png"},
                {"Trân châu", "5000", "src\\main\\image\\boba.png"},
                {"Kem cheese", "6000", "src\\main\\image\\creamcheese.png"},
                {"Bánh flan", "7000", "src\\main\\image\\flan.png"}
        });

        add(createMainPanel(), BorderLayout.CENTER);

    }

    private JPanel createMainPanel() {
        mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(createToolbar(), BorderLayout.NORTH);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        for (int i = 0; i < types.length; i++) {
            cardPanel.add(createDrinkGridPanel(drinkData.get(i)), types[i]);
        }

        mainPanel.add(new JScrollPane(cardPanel), BorderLayout.CENTER);
        return mainPanel;
    }


    private JPanel createToolbar() {
        toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.setBackground(new Color(240, 200, 160));
        toolbar.setPreferredSize(new Dimension(0, 50));

        cafeBtn = createMenuButton("Cà phê");
        teaBtn = createMenuButton("Trà");
        toppingBtn = createMenuButton("Topping");

        cafeBtn.addActionListener(e -> showDrinkCategory("coffee"));
        teaBtn.addActionListener(e -> showDrinkCategory("coffee"));
        toppingBtn.addActionListener(e -> showDrinkCategory("coffee"));

        JPanel searchPanel = createSearchBoxWithButton();

        addButton = createMenuButton("Thêm đồ uống");
        addButton.setPreferredSize(new Dimension(200, 30));
        ImageIcon iconAdd = new ImageIcon("src\\main\\image\\add.png");
        Image image1 = iconAdd.getImage();
        Image newImage1 = image1.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(newImage1);
        addButton.setIcon(icon1);
        addButton.addActionListener(e ->{
            AddDrinkDialog dialog = new AddDrinkDialog((JFrame) SwingUtilities.getWindowAncestor(this),
                    this::refreshCardPanel);
            dialog.setDrinkData(drinkData);            // truyền dữ liệu
            dialog.setVisible(true);
                });


        toolbar.add(cafeBtn);
        toolbar.add(teaBtn);
        toolbar.add(toppingBtn);
        toolbar.add(Box.createHorizontalStrut(30));
        toolbar.add(Box.createHorizontalStrut(30));
        toolbar.add(Box.createHorizontalStrut(30));
        toolbar.add(Box.createHorizontalStrut(30));
        toolbar.add(searchPanel, BorderLayout.EAST);
        toolbar.add(addButton);

        return toolbar;
    }

    private void showDrinkCategory(String type) {
        cardLayout.show(cardPanel, type);
    }

    private JPanel createDrinkGridPanel(String[][] drinks) {
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 20, 20)); // spacing
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        gridPanel.setBackground(new Color(255, 245, 204));

        for (int i = 0; i < drinks.length; i++) {
            int drinkIndex = i;
            String[] drink = drinks[i];
            JButton drinkButton = createDrinkButton(drink[0], drink[1], drink[2], drink, drinkIndex);
            gridPanel.add(drinkButton);
        }

        return gridPanel;
    }

    private JButton createDrinkButton(String name, String priceStr, String imagePath, String[] drinkInfo, int index) {
        drinkBtn = createDrinkBtn(" ");
        drinkBtn.setPreferredSize(new Dimension(140, 180)); // hình vuông
        drinkBtn.setMaximumSize(new Dimension(100, 100));
        drinkBtn.setLayout(new BorderLayout());
        drinkBtn.setBackground(new Color(255, 245, 204));

        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaled));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            drinkBtn.add(imageLabel, BorderLayout.NORTH);
        } catch (Exception e) {
            JLabel placeholder = new JLabel("[ảnh]", JLabel.CENTER);
            drinkBtn.add(placeholder, BorderLayout.NORTH);
        }

        nameLabel = new JLabel(name, JLabel.CENTER);

        nameLabel.setFont(new Font("Roboto", Font.BOLD, 25));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        priceLabel = new JLabel(priceStr + "đ", JLabel.CENTER);
        priceLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);


        //  Thêm chức năng khi bấm nút
        deleteBtn = createMenuButton("");
        deleteBtn.setForeground(Color.RED);
        deleteBtn.setPreferredSize(new Dimension(24, 24)); // Kích thước cố định
        deleteBtn.setMaximumSize(new Dimension(24, 24));
        deleteBtn.setContentAreaFilled(false);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setOpaque(false);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ImageIcon iconRemove = new ImageIcon("src\\main\\image\\rubbish-bin.png");
        Image image2 = iconRemove.getImage();
        Image newImage2 = image2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(newImage2);
        deleteBtn.setIcon(icon2);
//        deleteBtn.setMargin(new Insets(2, 5, 2, 5));
        deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

//        deleteBtn.addActionListener(e -> {
//            int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa món này?", "Xóa", JOptionPane.YES_NO_OPTION);
//            if (confirm == JOptionPane.YES_OPTION) {
//                // Xóa khỏi dữ liệu
//                for (int i = 0; i < drinkData.size(); i++) {
//                    String[][] data = drinkData.get(i);
//                    if (data != null) {
//                        ArrayList<String[]> list = new ArrayList<>(Arrays.asList(data));
//                        list.removeIf(drink -> drink[0].equals(name));
//                        drinkData.set(i, list.toArray(new String[0][0]));
//                    }
//                }
//
//                // Cập nhật UI
//                cardPanel.removeAll();
//                for (int i = 0; i < types.length; i++) {
//                    cardPanel.add(createDrinkGridPanel(drinkData.get(i)), types[i]);
//                }
//                cardPanel.revalidate();
//                cardPanel.repaint();
//            }
//
//        });
        deleteBtn.addActionListener(e ->deleteDrink(name));


        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(deleteBtn);

        drinkBtn.add(infoPanel, BorderLayout.CENTER);

        // chuc nang sua thong tin mon
        drinkBtn.addActionListener(e ->{

                    EditDrinkDialog editDialog = new EditDrinkDialog(
                            (JFrame) SwingUtilities.getWindowAncestor(this),
                            drinkInfo,
                            this::refreshCardPanel
                    );
                    editDialog.setVisible(true);
                }
        );

        return drinkBtn;
    }

    private void deleteDrink(String drinkName) {
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận xóa món này?", "Xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            for (int i = 0; i < drinkData.size(); i++) {
                String[][] data = drinkData.get(i);
                if (data != null) {
                    ArrayList<String[]> list = new ArrayList<>(Arrays.asList(data));
                    list.removeIf(drink -> drink[0].equals(drinkName));
                    drinkData.set(i, list.toArray(new String[0][0]));
                }
            }
            refreshCardPanel();
        }
    }

    private void openEditDrinkDialog(String[] drinkInfo, int index) {
        JDialog dialog = new JDialog((Frame) null, "Chỉnh sửa đồ uống", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tên
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("Tên đồ uống:"), gbc);
        JTextField nameField = new JTextField(drinkInfo[0]);
        gbc.gridx = 1;
        dialog.add(nameField, gbc);

        // Giá
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(new JLabel("Giá:"), gbc);
        JTextField priceField = new JTextField(drinkInfo[1]);
        gbc.gridx = 1;
        dialog.add(priceField, gbc);

        // Hình ảnh
        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(new JLabel("Hình ảnh:"), gbc);
        JComboBox<String> imageCombo = new JComboBox<>(new String[]{
                "src\\main\\image\\coffee.png", "src\\main\\image\\milkcoffee.png",
                "src\\main\\image\\expresso.png", "src\\main\\image\\peachtea.png",
                "src\\main\\image\\milktea.png", "src\\main\\image\\boba.png",
                "src\\main\\image\\creamcheese.png"
        });
        imageCombo.setSelectedItem(drinkInfo[2]);
        gbc.gridx = 1;
        dialog.add(imageCombo, gbc);

        // Nút
        JButton saveBtn = new JButton("Lưu thay đổi");
        JButton cancelBtn = new JButton("Hủy");
        JPanel btnPanel = new JPanel();
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        dialog.add(btnPanel, gbc);

        saveBtn.addActionListener(e -> {
            drinkInfo[0] = nameField.getText();
            drinkInfo[1] = priceField.getText();
            drinkInfo[2] = (String) imageCombo.getSelectedItem();
            dialog.dispose();
            refreshCardPanel();
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }


    private void refreshCardPanel() {
        cardPanel.removeAll();
        for (int i = 0; i < types.length; i++) {
            cardPanel.add(createDrinkGridPanel(drinkData.get(i)), types[i]);
        }
        cardPanel.revalidate();
        cardPanel.repaint();
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

        searchButton.addActionListener(e -> searchDrink(searchField.getText()));


        panel.add(searchField, BorderLayout.WEST);
        panel.add(searchButton, BorderLayout.EAST);

        return panel;
    }

    private void searchDrink(String keyword) {
        JOptionPane.showMessageDialog(this, "Đang tìm: " + keyword);
    }


    private void openAddDrinkDialog() {
        dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm đồ uống", true);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JComboBox<String> typeBox = new JComboBox<>(types);
        JComboBox<String> imageBox = new JComboBox<>(new String[]{
                "src\\main\\image\\coffee.png", "src\\main\\image\\milkcoffee.png",
                "src\\main\\image\\expresso.png", "src\\main\\image\\peachtea.png",
                "src\\main\\image\\milktea.png", "src\\main\\image\\boba.png",
                "src\\main\\image\\creamcheese.png", "src\\main\\image\\flan.png"
        });

        dialog.add(new JLabel("Tên đồ uống:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Giá:"));
        dialog.add(priceField);
        dialog.add(new JLabel("Loại:"));
        dialog.add(typeBox);
        dialog.add(new JLabel("Ảnh:"));
        dialog.add(imageBox);

        JButton addBtn = new JButton("Thêm");
//        addBtn.addActionListener(e -> {
//            String name = nameField.getText().trim();
//            String price = priceField.getText().trim();
//            String type = (String) typeBox.getSelectedItem();
//            String image = (String) imageBox.getSelectedItem();
//
//            if (!name.isEmpty() && !price.isEmpty()) {
//                int index = switch (type) {
//                    case "coffee" -> 0;
//                    case "tea" -> 1;
//                    case "topping" -> 2;
//                    default -> 0;
//                };
//                String[][] data = drinkData.get(index);
//                ArrayList<String[]> list = new ArrayList<>(Arrays.asList(data));
//                list.add(new String[]{name, price, image});
//                drinkData.set(index, list.toArray(new String[0][0]));
//
//                // Refresh UI
//                cardPanel.removeAll();
//                for (int i = 0; i < types.length; i++) {
//                    cardPanel.add(createDrinkGridPanel(drinkData.get(i)), types[i]);
//                }
//                cardPanel.revalidate();
//                cardPanel.repaint();
//                dialog.dispose();
//            } else {
//                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đủ thông tin.");
//            }
//        });
        addBtn.addActionListener(e -> handleAddDrink(nameField, priceField, typeBox, imageBox));


        dialog.add(new JLabel());
        dialog.add(addBtn);
        dialog.setVisible(true);
    }

    private void handleAddDrink(JTextField nameField, JTextField priceField, JComboBox<String> typeBox, JComboBox<String> imageBox) {
        String name = nameField.getText().trim();
        String price = priceField.getText().trim();
        String type = (String) typeBox.getSelectedItem();
        String image = (String) imageBox.getSelectedItem();
        if (!name.isEmpty() && !price.isEmpty()){
            int index = switch (type) {
                case "coffee" -> 0;
                case "tea" -> 1;
                case "topping" -> 2;
                default -> 0;
            };
            ArrayList<String[]> list = new ArrayList<>(Arrays.asList(drinkData.get(index)));
            list.add(new String[]{name, price, image});
            drinkData.set(index, list.toArray(new String[0][0]));
            refreshCardPanel();
            dialog.dispose();
        } else {
            JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đủ thông tin.");
        }
    }


        panel.add(searchField, BorderLayout.WEST);
        panel.add(searchButton, BorderLayout.EAST);

        return panel;
    }
}
