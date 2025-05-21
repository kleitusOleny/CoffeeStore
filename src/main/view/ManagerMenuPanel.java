package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerMenuPanel extends JPanel{
    public CustomButton datMon, datBan, apDungKM, thanhToan, dangXuat;
    private CustomButton selectedButton = null;

    public ManagerMenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(250, 900));
        setBackground(new Color(166, 123, 91));

        // Avatar và tên (demo đơn giản)
//        JLabel avatar = new JLabel("👤", SwingConstants.CENTER);
//        avatar.setFont(new Font("Arial", Font.PLAIN, 50));
//        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel name = new JLabel("Thống Nhất", SwingConstants.CENTER);
        name.setFont(new Font("Roboto", Font.BOLD, 30));
        name.setForeground(Color.white);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel role = new JLabel("MANAGER", SwingConstants.CENTER);
        role.setForeground(Color.white);
        role.setAlignmentX(Component.CENTER_ALIGNMENT);

//        add(Box.createRigidArea(new Dimension(0, 20)));
//        add(avatar);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(name);
        add(role);
        add(Box.createRigidArea(new Dimension(0, 20)));

        // ✅ Thêm vạch kẻ đen ngăn cách
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        separator.setForeground(Color.BLACK);  // Màu đen
        add(separator);

        add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách dưới vạch

        // Buttons
        datMon = createMenuButton("Menu");
        datBan = createMenuButton("Nhân Viên");
        apDungKM = createMenuButton("Khuyến Mãi");
        thanhToan = createMenuButton("Thống Kê");
        dangXuat = createMenuButton("Đăng Xuất");

        add(datMon);
        add(Box.createRigidArea(new Dimension(5, 15)));
        add(datBan);
        add(Box.createRigidArea(new Dimension(5, 15)));
        add(apDungKM);
        add(Box.createRigidArea(new Dimension(5, 15)));
        add(thanhToan);
        add(Box.createRigidArea(new Dimension(5, 15)));
        add(Box.createVerticalStrut(20));

        add(Box.createVerticalGlue());
        // ✅ Thêm vạch kẻ đen ngăn cách
        JSeparator separator1 = new JSeparator();
        separator1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        separator1.setForeground(Color.BLACK);  // Màu đen
        add(separator1);

        add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách dưới vạch

        add(dangXuat);
        add(Box.createRigidArea(new Dimension(0, 10)));


        // chen icon vao cac button
        ImageIcon iconMenu = new ImageIcon("src\\main\\image\\quanLyMenu.png");
        Image image1 = iconMenu.getImage();
        Image newImage1 = image1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(newImage1);
        datMon.setIcon(icon1);

        ImageIcon iconTable = new ImageIcon("src\\main\\image\\quanLyNhanVien.png");
        Image image2 = iconTable.getImage();
        Image newImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(newImage2);
        datBan.setIcon(icon2);

        ImageIcon iconDiscount = new ImageIcon("src\\main\\image\\quanLyKhuyenMai.png");
        Image image3 = iconDiscount.getImage();
        Image newImage3 = image3.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon icon3 = new ImageIcon(newImage3);
        apDungKM.setIcon(icon3);

        ImageIcon iconPay = new ImageIcon("src\\main\\image\\thongKeDoanhThu.png");
        Image image4 = iconPay.getImage();
        Image newImage4 = image4.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon icon4 = new ImageIcon(newImage4);
        thanhToan.setIcon(icon4);

        ImageIcon iconDangXuat = new ImageIcon("src\\main\\image\\dangXuat.png");
        Image image6 = iconDangXuat.getImage();
        Image newImage6 = image6.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon icon6 = new ImageIcon(newImage6);
        dangXuat.setIcon(icon6);

        // Footer
//        JLabel credit = new JLabel("Group 2", SwingConstants.CENTER);
//        credit.setAlignmentX(Component.CENTER_ALIGNMENT);
//        add(credit);
        datMon.addActionListener(e -> handleButtonSelection(datMon));
        datBan.addActionListener(e -> handleButtonSelection(datBan));
        apDungKM.addActionListener(e -> handleButtonSelection(apDungKM));
        thanhToan.addActionListener(e -> handleButtonSelection(thanhToan));
        dangXuat.addActionListener(e -> handleButtonSelection(dangXuat));

    }

    private CustomButton createMenuButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 80));   // Nút to hơn (rộng 300, cao 80)
        button.setPreferredSize(new Dimension(300, 80)); // Đặt preferred size để layout dễ hiểu
        button.setFont(new Font("Roboto", Font.BOLD, 20));  // Chữ to hơn chút
        button.setBackgroundColor(new Color(166, 123, 91));
        button.setHoverColor(new Color(255, 224, 178));
        button.setGradientColors(Color.CYAN, Color.BLUE);
        button.setTextColor(Color.WHITE);
        button.setBorderRadius(20);

        button.setHorizontalAlignment(SwingConstants.LEFT);     // Căn chữ trái trong button
        button.setIconTextGap(10);                               // Khoảng cách giữa icon và chữ
        button.setHorizontalTextPosition(SwingConstants.RIGHT); // Chữ nằm bên phải icon
        return button;
    }


    public void setProductCatalogBtListener(ActionListener listener) {
        datMon.addActionListener(listener);
    }

    public void setNotificationBtListener(ActionListener listener) {
        datBan.addActionListener(listener);
    }

    public void setPurchasedBtListener(ActionListener listener) {
        apDungKM.addActionListener(listener);
    }

    public void setChangeInfoBtListener(ActionListener listener) {
        thanhToan.addActionListener(listener);
    }
    public void setLogoutBtListener(ActionListener listener) {
        dangXuat.addActionListener(listener);
    }

    private void handleButtonSelection(CustomButton button) {
        if (selectedButton != null) {
            selectedButton.setSelected(false);  // Bỏ chọn nút trước
        }
        button.setSelected(true);               // Chọn nút hiện tại
        selectedButton = button;
    }
    
    
    
}
