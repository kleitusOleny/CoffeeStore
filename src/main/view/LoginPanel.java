package view;


//import controller.IController;
//import model.data.FormatAccounts;
//import com.google.gson.Gson;
//import model.data.ReadFileJson;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LoginPanel extends JPanel {
//
//
//    public LoginPanel(MainFrame mainFrame) {

 import controller.IController;
 import model.data.FormatAccounts;
 import model.data.ReadFileJson;
 
 import javax.swing.*;
 import java.awt.*;
 import java.io.FileNotFoundException;
 import java.util.List;

public class LoginPanel extends JPanel {
     List<FormatAccounts> accountsList = ReadFileJson.readFileJSON();
     public LoginPanel(MainFrame mainFrame) {

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
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
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
        gbc.insets = new Insets(15, 30, 15, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Tiêu đề
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
        
        // Nút đăng nhập
        gbc.gridy++;
        CustomButton loginBtn = new CustomButton("Đăng Nhập");
        loginBtn.setPreferredSize(new Dimension(400, 55));
        loginBtn.setBackgroundColor(new Color(243, 170, 108));
        loginBtn.setTextColor(Color.BLACK);
        loginBtn.setHoverColor(new Color(255, 200, 130));
        loginBtn.setBorderRadius(30);
        loginBtn.setBorderThickness(2);
        loginBtn.setDrawBorder(true);
        loginBtn.setGradientColors(new Color(255, 200, 130), new Color(243, 170, 108));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(loginBtn, gbc);
        
        // Sự kiện đăng nhập
        // Sự kiện đăng nhập
        loginBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            for (FormatAccounts accounts : accountsList) {
                if (username.equals(accounts.getUsername()) && password.equals(accounts.getPassword())) {
                    if (accounts.getRole().equals("Manager")){
                        mainFrame.showPanel(MainFrame.MANAGER);
                    }else{
                        mainFrame.showPanel(MainFrame.EMPLOYEE);
                    }
                    
                    return;
                }


//             if (username.equals("nhanvien") && password.equals("123")) {
//                 mainFrame.showPanel(MainFrame.EMPLOYEE);
//             } else if (username.equals("quanly") && password.equals("123")) {
//                 mainFrame.showPanel(MainFrame.MANAGER);
//             } else {
//                 JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!",
//                         "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);

            }
            JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!",
                    "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
//            if (username.equals("nhanvien") && password.equals("123")) {
//                mainFrame.showPanel(MainFrame.MANAGER);
//            } else {
//                JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!",
//                        "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
//            }
        });

        // Gộp các panel vào LoginPanel
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

//        // Cập nhật kích thước panel trái
//        SwingUtilities.invokeLater(() -> {
//            int panelWidth = getWidth();
//            leftPanel.setPreferredSize(new Dimension(panelWidth / 3, getHeight()));
//            revalidate();
//        });
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


//        loginBtn.addActionListener(e ->{
//            String username = userField.getText().trim();
//            String password = new String(passField.getPassword()).trim();
//            try {
//                controller.handleLogin(username,password);
//            } catch (FileNotFoundException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
    }
    
}


