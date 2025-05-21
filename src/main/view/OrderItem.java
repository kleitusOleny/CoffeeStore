package view;

import javax.swing.*;
import java.awt.*;

public class OrderItem {
    String name;
    int unitPrice;
    int quantity = 1;

    JLabel quantityLabel;
    JPanel panel;

    public OrderItem(String name, int unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public JPanel createPanel(Runnable updateTotalCallback, Runnable removeCallback) {
        panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(260, 40));
        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel nameLabel = new JLabel(" " + name);
        nameLabel.setPreferredSize(new Dimension(80, 20));

        // Nút xóa
        JButton deleteBtn = new JButton();
        ImageIcon iconDelete = new ImageIcon("src\\main\\image\\bin.png");
        Image image = iconDelete.getImage();
        Image newImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImage);
        deleteBtn.setIcon(icon);
        deleteBtn.setForeground(Color.RED);
        deleteBtn.setMargin(new Insets(2, 6, 2, 6));
//        deleteBtn.addActionListener(e -> {
//            removeCallback.run();
//        });
        deleteBtn.addActionListener(e -> onDeleteClicked(removeCallback));

        // Nút tăng giảm
        JButton plusBtn = new JButton();
        ImageIcon iconPlus = new ImageIcon("src\\main\\image\\plus.png");
        Image image2 = iconPlus.getImage();
        Image newImage2 = image2.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(newImage2);
        plusBtn.setIcon(icon2);
        plusBtn.setMargin(new Insets(2, 6, 2, 6));
//        plusBtn.addActionListener(e -> {
//            quantity++;
//            quantityLabel.setText("x" + quantity);
//            updateTotalCallback.run();
//        });
        plusBtn.addActionListener(e -> onPlusClicked(updateTotalCallback));

        JButton minusBtn = new JButton();
        ImageIcon iconMinus = new ImageIcon("src\\main\\image\\minus.png");
        Image image3 = iconMinus.getImage();
        Image newImage3 = image3.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(newImage3);
        minusBtn.setIcon(icon3);
        minusBtn.setMargin(new Insets(2, 6, 2, 6));
//        minusBtn.addActionListener(e -> {
//            if (quantity > 1) {
//                quantity--;
//                quantityLabel.setText("x" + quantity);
//                updateTotalCallback.run();
//            }
//        });
        minusBtn.addActionListener(e -> onMinusClicked(updateTotalCallback));

        quantityLabel = new JLabel("x" + quantity);
        quantityLabel.setPreferredSize(new Dimension(30, 20));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 2));
        controlPanel.setOpaque(false);
        controlPanel.add(minusBtn);
        controlPanel.add(quantityLabel);
        controlPanel.add(plusBtn);
        controlPanel.add(deleteBtn);

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(controlPanel, BorderLayout.EAST);

        return panel;
    }

    private void onMinusClicked(Runnable updateTotalCallback) {
        if (quantity > 1){
            quantity--;
            quantityLabel.setText("x" + quantity);
            updateTotalCallback.run();
        }
    }

    private void onPlusClicked(Runnable updateTotalCallback) {
        quantity++;
        quantityLabel.setText("x" + quantity);
        updateTotalCallback.run();
    }

    private void onDeleteClicked(Runnable removeCallback) {
        removeCallback.run();
    }

    public int getTotal() {
        return unitPrice * quantity;
    }
}
