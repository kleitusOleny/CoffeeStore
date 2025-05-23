package view.Manager;

import view.CustomButton;

import javax.swing.*;
import java.awt.*;

public class EditDrinkDialog extends javax.swing.JDialog {
    private JLabel name, priceMLabel, priceLLabel, imageLable;
    private JTextField nameField, priceMField;
    private JLabel priceLValueLabel;
    private JPanel btnPanel;
    private CustomButton saveBtn, cancelBtn;
    public EditDrinkDialog(JFrame parent, String[] drinkInfo, Runnable refreshCallback) {
        super(parent, "Chỉnh sửa đồ uống", true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tên
        gbc.gridx = 0; gbc.gridy = 0;
        name = new JLabel("Tên đồ uống:");
        name.setFont(new Font("Roboto", Font.PLAIN, 20));
        add(name, gbc);
        nameField = new JTextField(drinkInfo[0]);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Giá
        gbc.gridx = 0; gbc.gridy = 1;
        priceMLabel = new JLabel("Giá size M:");
        priceMLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        add(priceMLabel, gbc);

        priceMField = new JTextField(drinkInfo[1]);
        gbc.gridx = 1;
        add(priceMField, gbc);

        // Giá size L (tự động +5000)
        gbc.gridx = 0; gbc.gridy = 2;
        priceLLabel = new JLabel("Giá size L:");
        priceLLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        add(priceLLabel, gbc);

        priceLValueLabel = new JLabel(); // sẽ cập nhật sau
        priceLValueLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        gbc.gridx = 1;
        add(priceLValueLabel, gbc);


        // Hình ảnh
        gbc.gridx = 0; gbc.gridy = 3;
        imageLable = new JLabel("Hình ảnh:");
        imageLable.setFont(new Font("Roboto", Font.PLAIN, 20));
        add(imageLable, gbc);
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

        saveBtn = new CustomButton("Lưu thay đổi");
        saveBtn.setBackgroundColor(new Color(255, 180, 100));
        saveBtn.setTextColor(Color.WHITE);
        saveBtn.setBorderRadius(20);
        saveBtn.setPreferredSize(new Dimension(150, 40));

        cancelBtn = new CustomButton("Hủy");
        cancelBtn.setBackgroundColor(new Color(244, 67, 54));
        cancelBtn.setTextColor(Color.WHITE);
        cancelBtn.setBorderRadius(20);
        cancelBtn.setPreferredSize(new Dimension(80, 40));

        btnPanel = new JPanel();
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(btnPanel, gbc);

        // Cập nhật giá L ban đầu
        updatePriceL();

        // Lắng nghe thay đổi giá M
        priceMField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updatePriceL(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updatePriceL(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updatePriceL(); }
        });

        saveBtn.addActionListener(e -> {
            drinkInfo[0] = nameField.getText();
            drinkInfo[1] = priceMField.getText();
            drinkInfo[2] = (String) imageCombo.getSelectedItem();
            dispose();
            refreshCallback.run();
        });
        cancelBtn.addActionListener(e -> dispose());
    }

    private void updatePriceL() {
        try {
            int priceM = Integer.parseInt(priceMField.getText().trim());
            int priceL = priceM + 5000;
            priceLValueLabel.setText(priceL + " đ");
        } catch (NumberFormatException e) {
            priceLValueLabel.setText("Không hợp lệ");
        }
    }
}
