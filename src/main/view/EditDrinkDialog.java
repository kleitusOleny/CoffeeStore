package view;

import javax.swing.*;
import java.awt.*;

public class EditDrinkDialog extends javax.swing.JDialog {
    public EditDrinkDialog(JFrame parent, String[] drinkInfo, Runnable refreshCallback) {
        super(parent, "Chỉnh sửa đồ uống", true);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel name = new JLabel("Tên đồ uống:");
        name.setFont(new Font("Roboto", Font.PLAIN, 20));
        add(name, gbc);
        JTextField nameField = new JTextField(drinkInfo[0]);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel price = new JLabel("Giá:");
        price.setFont(new Font("Roboto", Font.PLAIN, 20));
        add(price, gbc);
        JTextField priceField = new JTextField(drinkInfo[1]);
        gbc.gridx = 1;
        add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel img = new JLabel("Hình ảnh:");
        img.setFont(new Font("Roboto", Font.PLAIN, 20));
        add(img, gbc);
        JComboBox<String> imageCombo = new JComboBox<>(new String[] {
                "src\\main\\image\\coffee.png", "src\\main\\image\\milkcoffee.png",
                "src\\main\\image\\expresso.png", "src\\main\\image\\peachtea.png",
                "src\\main\\image\\milktea.png", "src\\main\\image\\boba.png",
                "src\\main\\image\\creamcheese.png"
        });
        imageCombo.setFont(new Font("Roboto", Font.PLAIN, 20));
        imageCombo.setSelectedItem(drinkInfo[2]);
        gbc.gridx = 1;
        add(imageCombo, gbc);

        CustomButton saveBtn = new CustomButton("Lưu thay đổi");
        saveBtn.setBackgroundColor(new Color(255, 180, 100));
        saveBtn.setTextColor(Color.WHITE);
        saveBtn.setBorderRadius(20);
        saveBtn.setPreferredSize(new Dimension(150, 40));

        CustomButton cancelBtn = new CustomButton("Hủy");
        cancelBtn.setBackgroundColor(new Color(244, 67, 54));
        cancelBtn.setTextColor(Color.WHITE);
        cancelBtn.setBorderRadius(20);
        cancelBtn.setPreferredSize(new Dimension(80, 40));

        JPanel btnPanel = new JPanel();
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnPanel, gbc);

        saveBtn.addActionListener(e -> {
            drinkInfo[0] = nameField.getText();
            drinkInfo[1] = priceField.getText();
            drinkInfo[2] = (String) imageCombo.getSelectedItem();
            dispose();
            refreshCallback.run();
        });

        cancelBtn.addActionListener(e -> dispose());
    }
}
