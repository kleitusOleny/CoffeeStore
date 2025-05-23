package view.Manager;

import view.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddDrinkDialog extends JDialog{
    private java.util.List<String[][]> drinkData = new ArrayList<>();
    private int categoryIndex;

    public AddDrinkDialog(JFrame parent, Runnable refreshCallback) {
        super(parent, "Thêm đồ uống", true);
        setLayout(new GridLayout(5, 0, 10, 10));
        setSize(500, 400);
        setLocationRelativeTo(parent);

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        String[] types = {"coffee", "tea", "topping"};
        JComboBox<String> typeBox = new JComboBox<>(types);
        typeBox.setFont(new Font("Roboto", Font.PLAIN, 20));
        JComboBox<String> imageBox = new JComboBox<>(new String[]{
                "src\\main\\image\\coffee.png", "src\\main\\image\\milkcoffee.png",
                "src\\main\\image\\expresso.png", "src\\main\\image\\peachtea.png",
                "src\\main\\image\\milktea.png", "src\\main\\image\\boba.png",
                "src\\main\\image\\creamcheese.png", "src\\main\\image\\flan.png"
        });
        imageBox.setFont(new Font("Roboto", Font.PLAIN, 20));

        add(new JLabel("Tên đồ uống:")).setFont(new Font("Roboto", Font.PLAIN, 20));
        add(nameField);
        add(new JLabel("Giá:")).setFont(new Font("Roboto", Font.PLAIN, 20));
        add(priceField);
        add(new JLabel("Loại:")).setFont(new Font("Roboto", Font.PLAIN, 20));
        add(typeBox);
        add(new JLabel("Ảnh:")).setFont(new Font("Roboto", Font.PLAIN, 20));
        add(imageBox);

        CustomButton addBtn = new CustomButton("Thêm");
        addBtn.setBackgroundColor(new Color(255, 180, 100));
        addBtn.setTextColor(Color.WHITE);
        addBtn.setBorderRadius(20);
        addBtn.setPreferredSize(new Dimension(80, 40));

        addBtn.addActionListener(e -> {
            if (drinkData == null) {                     // chưa gán -> báo lỗi
                JOptionPane.showMessageDialog(this,
                        "Chưa truyền drinkData vào dialog!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = nameField.getText().trim();
            String price = priceField.getText().trim();
            String type = (String) typeBox.getSelectedItem();
            String image = (String) imageBox.getSelectedItem();

            if (!name.isEmpty() && !price.isEmpty()) {
                int index = switch (type) {
                    case "coffee" -> 0;
                    case "tea" -> 1;
                    case "topping" -> 2;
                    default -> 0;
                };
                String[][] data = drinkData.get(index);
                ArrayList<String[]> list = new ArrayList<>(Arrays.asList(data));
                list.add(new String[]{name, price, image});
                drinkData.set(index, list.toArray(new String[0][0]));

                refreshCallback.run();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin.");
            }
        });

        add(new JLabel());
        add(addBtn);
    }

    /* ➋ SETTER để truyền dữ liệu từ MenuEditorPanel */
    public void setDrinkData(List<String[][]> drinkData) {
        this.drinkData = (java.util.List<String[][]>) drinkData;
    }
}
