package view;

import javax.swing.*;
import java.awt.*;

public class ThongKe extends JPanel {

    private ManagerMenuPanel menuPanel;
    private JPanel contentPanel;

    public ThongKe() {
        setLayout(new BorderLayout());

        // Menu bên trái
//        menuPanel = new ManagerMenuPanel();
//        add(menuPanel, BorderLayout.WEST);

        // Panel nội dung bên phải
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Thêm nội dung thống kê giả lập
        JLabel title = new JLabel("Thống Kê Doanh Thu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        JTextArea reportArea = new JTextArea();
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        reportArea.setEditable(false);
        reportArea.setText(
                "• Doanh thu tháng 1: 12,000,000 VND\n" +
                        "• Doanh thu tháng 2: 15,500,000 VND\n" +
                        "• Doanh thu tháng 3: 18,200,000 VND\n" +
                        "• Tổng doanh thu: 45,700,000 VND\n"
        );

        JScrollPane scrollPane = new JScrollPane(reportArea);

        contentPanel.add(title, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }

    public ManagerMenuPanel getMenuPanel() {
        return menuPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thống Kê Doanh Thu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null); // Căn giữa màn hình

            ThongKe thongKePanel = new ThongKe();
            frame.setContentPane(thongKePanel);
            frame.setVisible(true);
        });
    }
}
