package view;

import data.FormatAccounts;
import data.ReadFileJson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginPanel extends JPanel {
    public CustomButton loginBtn;
    public CustomTextField userField;
    public CustomPasswordField passField;
    public List<FormatAccounts> accountsList = ReadFileJson.readFileJSONForAccount();
    public MainFrame frame;

    public LoginPanel(MainFrame mainFrame) {
        this.frame = mainFrame;
        setLayout(new BorderLayout());

        // Panel trái - chiếm 1/3 chiều rộng
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(162, 120, 90)); // nâu
        leftPanel.setLayout(new BorderLayout());

        // Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setVerticalAlignment(SwingConstants.TOP);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        ImageIcon logoIcon = new ImageIcon("src\\main\\image\\avatar.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH); // đổi kích thước tùy ý
        logoLabel.setIcon(new ImageIcon(scaledImage));

        // Dòng chào
        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Gộp logo và dòng chào
        JPanel topLeftPanel = new JPanel(new BorderLayout());
        topLeftPanel.setOpaque(false);
        topLeftPanel.add(logoLabel, BorderLayout.NORTH);
        topLeftPanel.add(welcomeLabel, BorderLayout.CENTER);
        leftPanel.add(topLeftPanel, BorderLayout.NORTH);

        // Panel phải (form)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(255, 239, 201)); // kem
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 30, 7, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel titleLabel = new JLabel("Đăng Nhập");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 42));
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
        userLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        rightPanel.add(userLabel, gbc);

        gbc.gridy++;
        userField = new CustomTextField(20);
        userField.setFont(new Font("Roboto", Font.PLAIN, 20));
        userField.setPreferredSize(new Dimension(400, 50));
        userField.setDrawBorder(true);
        userField.setBorderRadius(30);
        userField.setBackgroundColor(Color.WHITE);
        userField.setHoverColor(new Color(245, 245, 245));
        userField.setBorderThickness(2);
        userField.setDrawBorder(false);
        userField.setGradientColors(new Color(162, 120, 90), new Color(162, 120, 90));
        rightPanel.add(userField, gbc);

        // Mật khẩu
        gbc.gridy++;
        JLabel passLabel = new JLabel("Mật khẩu:");
        passLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        rightPanel.add(passLabel, gbc);

        gbc.gridy++;
        passField = new CustomPasswordField(20);
        passField.setFont(new Font("Roboto", Font.PLAIN, 20));
        passField.setPreferredSize(new Dimension(400, 50));
        passField.setDrawBorder(true);
        passField.setBorderRadius(30);
        passField.setBackgroundColor(Color.WHITE);
        passField.setHoverColor(new Color(245, 245, 245));
        passField.setBorderThickness(2);//do dam cua vien
        passField.setDrawBorder(false);
        passField.setGradientColors(new Color(162, 120, 90), new Color(162, 120, 90));
        rightPanel.add(passField, gbc);

        // Nút đăng nhập
        gbc.gridy++;
        loginBtn = new CustomButton("Đăng Nhập");
        loginBtn.setPreferredSize(new Dimension(400, 55));
        loginBtn.setBackgroundColor(new Color(243, 170, 108));
        loginBtn.setFont(new Font("Roboto", Font.BOLD, 28));
        loginBtn.setTextColor(Color.BLACK);
        loginBtn.setHoverColor(new Color(255, 200, 130));
        loginBtn.setBorderRadius(30);
        loginBtn.setBorderThickness(2);
        loginBtn.setDrawBorder(true);
        loginBtn.setGradientColors(new Color(255, 200, 130), new Color(243, 170, 108));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(loginBtn, gbc);


        // Gộp các panel vào LoginPanel
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Lắng nghe thay đổi kích thước để luôn giữ leftPanel = 1/3 chiều rộng
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                leftPanel.setPreferredSize(new Dimension(panelWidth / 3, panelHeight));
                revalidate();
            }
        });

    }

    public void setLoginListener(ActionListener actionListener) {
        loginBtn.addActionListener(actionListener);
    }

    public MainFrame getMainFrame() {
        return frame;
    }

    public CustomTextField getUserField() {
        return userField;
    }

    public CustomPasswordField getPassField() {
        return passField;
    }
}


