package view.Staff;

import controller.ReservationController;
import model.reservation_system.Reservation;
import model.reservation_system.ReservationStatus;
import model.reservation_system.ReservationSystem;
import model.reservation_system.Table;
import view.CustomButton;
import view.EmployeeMenuPanel;
import view.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class TablePanel extends JPanel implements Observer {
    private JComboBox<String> statusFilter;
    private List<JButton> tableButtons = new ArrayList<>();
    private final Map<JButton, String> tableStatusMap = new HashMap<>();
    private JPanel topPanel, tableGrid;
    private JLabel statusLabel;
    private ReservationSystem model;
    private ReservationController controller;
    
    private static String tableNo;
    public TablePanel(ReservationSystem model) {
        this.model = model;
        this.model.addObserver(this);
        
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 204));

        // Panel Trạng thái phía trên
        topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(new Color(255, 245, 204));
        statusFilter = new JComboBox<>(new String[]{"Tất cả", "Trống", "Đang sử dụng", "Đã đặt"});
        statusFilter.setFont(new Font("Roboto", Font.BOLD, 15));
        addStatusFilterListener();
        statusLabel = new JLabel("Trạng thái:");
        statusLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        topPanel.add(statusLabel);
        topPanel.add(statusFilter);

        // Panel sơ đồ bàn trung tâm
        tableGrid = new JPanel(new WrapLayout(FlowLayout.LEFT, 20, 20));
        tableGrid.setBackground(new Color(255, 245, 204));
        
        initializeTableButtons();

        // Đặt tablePanel vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tableGrid);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(255, 245, 204));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        this.controller = new ReservationController(model,this);
    }

    private void initializeTableButtons() {
        List<Table> tables = model.getTables();
        for (Table table : tables) {
            JButton tableButton = new JButton("Bàn " + table.getIdTable());
            tableButton.setFont(new Font("Roboto", Font.BOLD, 15));
            tableButton.setPreferredSize(new Dimension(100, 60));
            String status = table.isStatus() ? "Đã đặt" : "Trống";
            setStatus(tableButton, status);
            tableButtons.add(tableButton);
            addTableButtonListener(tableButton, table.getIdTable());
            tableGrid.add(tableButton);
        }
    }
    
    private void addTableButtonListener(JButton tableButton,int index) {
        tableButton.addActionListener((ActionEvent e)-> {
            controller.showStatusDialog(tableButton, index);
            if ("Đặt bàn".equals(tableStatusMap.get(tableButton)) || "Chọn bàn".equals(tableStatusMap.get(tableButton))) {
                tableNo = index+"";
                System.out.println(tableNo);
            }
        });
    }

    private void addStatusFilterListener() {
        statusFilter.addActionListener((ActionEvent e) -> filterTables() );
    }

    private void setStatus(JButton button, String status) {
        tableStatusMap.put(button, status);
        switch (status) {
            case "Trống":
                button.setBackground(Color.LIGHT_GRAY);
                break;
            case "Chọn bàn":
                button.setBackground(new Color(255, 160, 122)); // đỏ nhạt
                break;
            case "Đặt bàn":
                button.setBackground(new Color(144, 238, 144)); // xanh lá nhạt
                break;
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
    
    public void updateTableStatus(int tableId, String newStatus) {
        for (JButton btn : tableButtons) {
            if (btn.getText().equals("Bàn " + tableId)) {
                setStatus(btn, newStatus);
                break;
            }
        }
        filterTables();
    }
    public static String getTableNo(){
        return tableNo;
    }
    
    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ReservationStatus) {
            ReservationStatus status = (ReservationStatus) arg;
            switch (status.getAction()){
                case "ADD_RESERVATION":
                case "REMOVE_RESERVATION":
                case "UPDATE_TABLE_STATUS":
                    for (Table table : model.getTables()) {
                        int tableId = table.getIdTable();
                        String newStatus = table.isStatus() ? "Đặt bàn" : "Trống";
                        updateTableStatus(tableId, newStatus);
                    }
                    break;
            }
        }
    }
}
