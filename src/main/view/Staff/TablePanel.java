package view.Staff;

import view.CustomButton;
import view.EmployeeMenuPanel;
import view.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablePanel extends JPanel {
    private JComboBox<String> statusFilter;
    private List<JButton> tableButtons = new ArrayList<>();
    private final Map<JButton, String> tableStatusMap = new HashMap<>();
    private JPanel topPanel, tableGrid;

    public TablePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 204));

        // Panel Trạng thái phía trên
        topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(new Color(255, 245, 204));
        statusFilter = new JComboBox<>(new String[]{"Tất cả", "Trống", "Đang sử dụng", "Đã đặt"});
//        statusFilter.addActionListener(e -> filterTables());
        addStatusFilterListener();
        topPanel.add(new JLabel("Trạng thái:"));
        topPanel.add(statusFilter);

        // Panel sơ đồ bàn trung tâm
//        tableGrid = new JPanel(new GridLayout(3, 4, 20, 20)); // 12 bàn demo
        tableGrid = new JPanel(new WrapLayout(FlowLayout.LEFT, 20, 20));
        tableGrid.setBackground(new Color(255, 245, 204));

        for (int i = 1; i <= 20; i++) {
            JButton tableButton = new JButton("Bàn " + i);
            tableButton.setPreferredSize(new Dimension(100, 60));
            tableButton.setBackground(Color.LIGHT_GRAY); // Trống mặc định
            tableStatusMap.put(tableButton, "Trống");
            tableButtons.add(tableButton);

//            tableButton.addActionListener(e -> showStatusDialog(tableButton));
            addTableButtonListener(tableButton);
            tableGrid.add(tableButton);
        }

        // Đặt tablePanel vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tableGrid);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(255, 245, 204));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        EmployeeMenuPanel managerMenuPanel = new EmployeeMenuPanel();
        add(topPanel, BorderLayout.NORTH);
//        add(managerMenuPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addTableButtonListener(JButton tableButton) {
        tableButton.addActionListener((ActionEvent e)-> showStatusDialog(tableButton));
    }

    private void addStatusFilterListener() {
        statusFilter.addActionListener((ActionEvent e) -> filterTables() );
    }

    private CustomButton createMenuButton(String text) {
        CustomButton button = new CustomButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(1000, 200));
        button.setBackgroundColor(Color.LIGHT_GRAY);
        button.setTextColor(Color.BLACK);
        button.setBorderRadius(20);
        return button;
    }
    private void setStatus(JButton button, String status) {
        tableStatusMap.put(button, status);

        switch (status) {
            case "Trống":
                button.setBackground(Color.LIGHT_GRAY);
                break;
            case "Đang sử dụng":
                button.setBackground(new Color(255, 160, 122)); // đỏ nhạt
                break;
            case "Đã đặt":
                button.setBackground(new Color(144, 238, 144)); // xanh lá nhạt
                break;
        }
    }

    private void showStatusDialog(JButton button) {
        String currentStatus = tableStatusMap.get(button);
        List<String> options = new ArrayList<>();

        if ("Trống".equals(currentStatus)) {
            options = Arrays.asList("Đang sử dụng", "Đã đặt");
        } else if ("Đang sử dụng".equals(currentStatus)) {
            options = Arrays.asList("Trống", "Đã đặt");
        } else if ("Đã đặt".equals(currentStatus)) {
            options = Arrays.asList("Trống", "Đang sử dụng");
        }

        Object newStatus = JOptionPane.showInputDialog(
                this,
                "Chọn trạng thái mới:",
                "Cập nhật trạng thái",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options.toArray(),
                options.get(0)
        );

        if (newStatus != null) {
            setStatus(button, newStatus.toString());
            filterTables(); // Cập nhật lại hiển thị nếu đang lọc
        }
    }

    private void filterTables() {
        String selected = (String) statusFilter.getSelectedItem();

        tableGrid.removeAll(); // Xoá hết bàn cũ

        for (JButton btn : tableButtons) {
            String status = tableStatusMap.get(btn);
            if ("Tất cả".equals(selected) || status.equals(selected)) {
                tableGrid.add(btn); // Chỉ add lại các bàn phù hợp
            }
        }

        tableGrid.revalidate();
        tableGrid.repaint();
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Đặt bàn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setContentPane(new TablePanel());
        frame.setVisible(true);
    }



}
