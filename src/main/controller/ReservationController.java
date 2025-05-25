package controller;

import model.reservation_system.Reservation;
import model.reservation_system.ReservationSystem;
import model.reservation_system.Table;
import view.IView;
import view.Staff.DiscountPanel;
import view.Staff.TablePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationController implements IController {
    ReservationSystem reservationSystem;
    TablePanel view;

    public ReservationController(ReservationSystem reservationSystem, TablePanel view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }
    
    public void showStatusDialog(JButton button, int index) {
        String currentStatus = getTableStatus(index);
        List<String> options = new ArrayList<>();
        
        if ("Trống".equals(currentStatus)) {
            options = Arrays.asList("Chọn bàn", "Đặt bàn");
        } else if ("Chọn bàn".equals(currentStatus)) {
            options = Arrays.asList("Trống", "Đặt bàn");
        } else if ("Đã đặt".equals(currentStatus)) {
            options = Arrays.asList("Trống", "Chọn bàn");
        } else if ("Đang sử dụng".equals(currentStatus)) {
            options = Arrays.asList("Trống");
        }
        
        Object newStatus = JOptionPane.showInputDialog(
                view,
                "Chọn trạng thái mới:",
                "Cập nhật trạng thái",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options.toArray(),
                options.get(0)
        );
        
        if (newStatus != null) {
            handleStatusChange(index, newStatus.toString());
            view.setTableNo(String.valueOf(index));
        }
    }
    
    private String getTableStatus(int tableId) {
        for (Table table : reservationSystem.getTables()) {
            if (table.getIdTable() == tableId) {
                if (table.isStatus()) {
                    for (Reservation reservation : reservationSystem.getListReser()) {
                        if (reservation.getTable().getIdTable() == tableId) {
                            return "Đã đặt";
                        }
                    }
                    return "Đang sử dụng";
                } else {
                    return "Trống";
                }
            }
        }
        return "Trống";
    }
    
    private void handleStatusChange(int tableId, String newStatus) {
        switch (newStatus) {
            case "Trống":
                Reservation reservationToRemove = null;
                for (Reservation reservation : reservationSystem.getListReser()) {
                    if (reservation.getTable().getIdTable() == tableId) {
                        reservationToRemove = reservation;
                        break;
                    }
                }
                if (reservationToRemove != null) {
                    reservationSystem.removeReservation(reservationToRemove);
                } else {
                    reservationSystem.updateTableStatus(tableId, false);
                }
                view.updateTableStatus(tableId, "Trống");
                break;
            case "Chọn bàn":
                reservationSystem.updateTableStatus(tableId, true);
                view.updateTableStatus(tableId, "Chọn bàn");
                break;
            case "Đặt bàn":
                Table table = null;
                for (Table t : reservationSystem.getTables()) {
                    if (t.getIdTable() == tableId) {
                        table = t;
                        break;
                    }
                }
                if (table != null) {
                    String numOfPersonsStr = JOptionPane.showInputDialog(view, "Nhập số người:");
                    int numOfPersons = Integer.parseInt(numOfPersonsStr);
                    reservationSystem.updateTableStatus(tableId, true);
                    Reservation reservation = new Reservation(table, "NV01", "KH01", DiscountPanel.customer[0], numOfPersons);
                    reservationSystem.makeReservation(reservation);
                    view.updateTableStatus(tableId, "Đã đặt");
                }
                break;
        }
    }
}
