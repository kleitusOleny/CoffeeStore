package view;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Đăng Nhập");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel trái - chiếm 1/3 chiều rộng frame
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(162, 120, 90)); // nâu
        leftPanel.setLayout(new BorderLayout());

        // Logo ở đầu panel trái
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setVerticalAlignment(SwingConstants.TOP);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        ImageIcon logoIcon = new ImageIcon("src/main/java/Icon/logo-fit.png");
        logoLabel.setIcon(logoIcon);

        // Dòng chào mừng
        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Thêm logo và dòng chữ vào panel trái
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new BorderLayout());
        topLeftPanel.setOpaque(false);
        topLeftPanel.add(logoLabel, BorderLayout.NORTH);
        topLeftPanel.add(welcomeLabel, BorderLayout.CENTER);
        leftPanel.add(topLeftPanel, BorderLayout.NORTH);

        // Panel phải (form đăng nhập)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(255, 239, 201)); // kem
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 30, 15, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề "Đăng Nhập"
        JLabel titleLabel = new JLabel("Đăng Nhập");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 42));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(titleLabel, gbc);

        // Tên đăng nhập
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel userLabel = new JLabel("Tên đăng nhập:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanel.add(userLabel, gbc);

        gbc.gridy++;
        CustomTextField userField = new CustomTextField(20);
        userField.setFont(new Font("Arial", Font.PLAIN, 20));
        userField.setPreferredSize(new Dimension(400, 50));
        userField.setDrawBorder(true);
        userField.setBorderRadius(30);
        userField.setBackgroundColor(Color.WHITE);
        userField.setHoverColor(new Color(245, 245, 245));
        userField.setBorderThickness(2);
        userField.setGradientColors(new Color(200, 200, 200), new Color(180, 180, 180));
        rightPanel.add(userField, gbc);

        // Mật khẩu
        gbc.gridy++;
        JLabel passLabel = new JLabel("Mật khẩu:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanel.add(passLabel, gbc);

        gbc.gridy++;
        CustomPasswordField passField = new CustomPasswordField(20);
        passField.setFont(new Font("Arial", Font.PLAIN, 20));
        passField.setPreferredSize(new Dimension(400, 50));
        passField.setDrawBorder(true);
        passField.setBorderRadius(30);
        passField.setBackgroundColor(Color.WHITE);
        passField.setHoverColor(new Color(245, 245, 245));
        passField.setBorderThickness(2);
        passField.setGradientColors(new Color(200, 200, 200), new Color(180, 180, 180));
        rightPanel.add(passField, gbc);

        // Nút Đăng Nhập
        gbc.gridy++;
        CustomButton loginBtn = new CustomButton("Đăng Nhập");
        loginBtn.setPreferredSize(new Dimension(400, 55));
        loginBtn.setBackgroundColor(new Color(243, 170, 108)); // cam nhạt
        loginBtn.setTextColor(Color.BLACK);
        loginBtn.setHoverColor(new Color(255, 200, 130));
        loginBtn.setBorderRadius(30);
        loginBtn.setBorderThickness(2);
        loginBtn.setDrawBorder(true);
        loginBtn.setGradientColors(new Color(255, 200, 130), new Color(243, 170, 108));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(loginBtn, gbc);

        // Gộp vào frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Đặt lại kích thước panel trái sau khi frame đã có kích thước
        SwingUtilities.invokeLater(() -> {
            int frameWidth = getWidth();
            leftPanel.setPreferredSize(new Dimension(frameWidth / 3, getHeight()));
            revalidate();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
