package controller;

import model.reservation_system.Reservation;
import model.reservation_system.ReservationSystem;
import model.reservation_system.Table;
import view.IView;
import view.Staff.TablePanel;

import java.util.List;

public class ReservationController {
    ReservationSystem reservationSystem;
    IView view;

    public ReservationController(ReservationSystem reservationSystem, IView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

    // goi de tao ban moi
    public void makeReservation(Reservation reservation) {
        if (reservation == null || reservation.getTable() == null) {
            view.displayMessage("Thông tin đặt bàn không hợp lệ!");
            return;
        }
        if (reservation.getTable().isStatus()) {
            view.displayMessage("Bàn " + reservation.getTable().getIdTable() + " đã được đặt!");
            return;
        }
        reservationSystem.makeReservation(reservation);
        view.displayMessage("Đặt bàn thành công!");
    }

    // huy dat ban
    public void cancelReservation(Reservation reservation) {
        if (reservation == null || reservation.getTable() == null) {
            view.displayMessage("Thông tin đặt bàn không hợp lệ!");
            return;
        }
        if (!reservationSystem.getListReser().contains(reservation)) {
            view.displayMessage("Không tìm thấy đặt bàn để hủy!");
            return;
        }
        reservationSystem.removeReservation(reservation);
        reservation.getTable().setStatus(false);
        view.displayMessage("Đã hủy đặt bàn.");
    }

    // lay danh sach ban chua duoc kich hoat
    public void showEmptyTables() {
        List<Table> emptyTable = reservationSystem.emptyTableList();
        view.displayEmptyTables(emptyTable);
    }

    public void showNonEmptyTables() {
        List<Reservation> reservedTables = reservationSystem.getNonEmptyTables();
        view.displayReservedTables(reservedTables);
    }

    public void handleTableButtonClick(int tableId, String newStatus, String idEmp, String idCus, String nameCus, int numOfPersons) {
        Table table = findTableById(tableId); // Cần phương thức tìm bàn theo ID
        if (table == null) {
            view.displayMessage("Bàn không tồn tại!");
            return;
        }
        Reservation reservation = new Reservation(table, idEmp, idCus, nameCus, numOfPersons);
        if ("Đã đặt".equals(newStatus)) {
            makeReservation(reservation);
            view.displayMessage("Bàn đã được đặt trước!");
        } else if ("Trống".equals(newStatus)) {
            cancelReservation(reservation);
//            view.displayMessage("");
        }
        updateTableStatus(tableId, newStatus); // Cập nhật giao diện
    }

    public void updateTableStatus(int tableId, String newStatus) {
        view.updatetableStatus(tableId, newStatus);
    }

    public Table findTableById(int tableId) {
        Table result = null;
        for (Reservation reservation : reservationSystem.getListReser()) {
            if (reservation.getTable().getIdTable() == tableId) {
                result = reservation.getTable();
                return result;
            }
        }
        view.displayMessage("Bàn "+result.getIdTable()+" không tồn tại!");
        return null; // Hoặc tạo bàn mới nếu cần
    }

    /**
     * khoi tao danh sach ban
     */
    public void initializeTable(int numberOftable) {
        for (int i = 0; i < numberOftable; i++) {
            Table table = new Table(i,false);//Tạo bàn trống
            reservationSystem.addTable(table);//
        }
        view.displayMessage("Đã tạo "+numberOftable+" bàn.");
    }
    public  boolean isTableReserved(int tableId) {
        Table table = findTableById(tableId);
        if (table == null){
            return false;
        }
        return table.isStatus();
    }
    public  Reservation getReservationByTableId(int tableId) {
        for (Reservation reservation : reservationSystem.getListReser()) {
            if (reservation.getTable().getIdTable() == tableId){
                return reservation;
            }
        }
        return null;
    }
    public void updateReservation(int tableId, String idEmp, String idCus, String nameCus, int numOfPersons) {
        Reservation reservation = getReservationByTableId(tableId);
        if (reservation == null) {
            view.displayMessage("Không tìm thấy đặt bàn cho bàn " + tableId);
            return;
        }
        reservationSystem.removeReservation(reservation);
        Reservation updatedReservation = new Reservation(findTableById(tableId), idEmp, idCus, nameCus, numOfPersons);
        reservationSystem.makeReservation(updatedReservation);
        view.displayMessage("Cập nhật đặt bàn thành công!");
    }
}
