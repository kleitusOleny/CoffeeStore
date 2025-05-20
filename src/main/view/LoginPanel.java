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
    public List<FormatAccounts> accountsList = ReadFileJson.readFileJSON();
    public MainFrame frame;
     public LoginPanel(MainFrame mainFrame) {
         this.frame = mainFrame;

        setLayout(new BorderLayout());

        JPanel leftPanel = buildLeftPanel();
        JPanel rightPanel = buildRightPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Resize listener để giữ 1/3 chiều rộng
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

    private JPanel buildLeftPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(162, 120, 90)); // nâu

        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setVerticalAlignment(SwingConstants.TOP);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        ImageIcon logoIcon = new ImageIcon("src\\main\\image\\avatar.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(scaledImage));

        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JPanel topLeftPanel = new JPanel(new BorderLayout());
        topLeftPanel.setOpaque(false);
        topLeftPanel.add(logoLabel, BorderLayout.NORTH);
        topLeftPanel.add(welcomeLabel, BorderLayout.CENTER);
        leftPanel.add(topLeftPanel, BorderLayout.NORTH);

        return leftPanel;
    }

    private JPanel buildRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(255, 239, 201)); // kem
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 30, 15, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Đăng Nhập");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 42));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel userLabel = new JLabel("Tên đăng nhập:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanel.add(userLabel, gbc);

        gbc.gridy++;
        userField = new CustomTextField(20);

        setupInputField(userField);

        rightPanel.add(userField, gbc);

        gbc.gridy++;
        JLabel passLabel = new JLabel("Mật khẩu:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanel.add(passLabel, gbc);

        gbc.gridy++;
        passField = new CustomPasswordField(20);

        setupInputField(passField);

        rightPanel.add(passField, gbc);

        gbc.gridy++;
        CustomButton loginBtn = new CustomButton("Đăng Nhập");
        setupLoginButton(loginBtn);
        rightPanel.add(loginBtn, gbc);

        return rightPanel;
    }

    private void setupInputField(JTextField field) {
        field.setFont(new Font("Arial", Font.PLAIN, 20));
        field.setPreferredSize(new Dimension(400, 50));
        if (field instanceof CustomTextField custom) {
            custom.setDrawBorder(true);
            custom.setBorderRadius(30);
            custom.setBackgroundColor(Color.WHITE);
            custom.setHoverColor(new Color(245, 245, 245));
            custom.setBorderThickness(2);
            custom.setGradientColors(new Color(200, 200, 200), new Color(180, 180, 180));
        } else if (field instanceof CustomPasswordField custom) {
            custom.setDrawBorder(true);
            custom.setBorderRadius(30);
            custom.setBackgroundColor(Color.WHITE);
            custom.setHoverColor(new Color(245, 245, 245));
            custom.setBorderThickness(2);
            custom.setGradientColors(new Color(200, 200, 200), new Color(180, 180, 180));
        }
    }

    private void setupLoginButton(CustomButton loginBtn) {
        loginBtn.setPreferredSize(new Dimension(400, 55));
        loginBtn.setBackgroundColor(new Color(243, 170, 108));
        loginBtn.setTextColor(Color.BLACK);
        loginBtn.setHoverColor(new Color(255, 200, 130));
        loginBtn.setBorderRadius(30);
        loginBtn.setBorderThickness(2);
        loginBtn.setDrawBorder(true);
        loginBtn.setGradientColors(new Color(255, 200, 130), new Color(243, 170, 108));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.addActionListener(e -> handleLogin());
    }


    private void handleLogin() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        for (FormatAccounts account : accountsList) {
            if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
                if (account.getRole().equalsIgnoreCase("Manager")) {
                    mainFrame.showPanel(MainFrame.MANAGER);
                } else {
                    mainFrame.showPanel(MainFrame.EMPLOYEE);
                }
                return;
            }

        }

        JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!",
                "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
        });
        
    }
    public void setLoginListener(ActionListener actionListener) {
        loginBtn.addActionListener(actionListener);
    }
    
    public MainFrame getMainFrame() {
        return frame;
    }
}
