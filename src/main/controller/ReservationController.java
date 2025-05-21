package controller;

import model.reservation_system.Reservation;
import model.reservation_system.ReservationSystem;
import view.IView;

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
        reservationSystem.makeReservation(reservation);
        view.displayMessage("Đặt bàn thành công!");
    }
    // huy dat ban
    public void cancelReservation(Reservation reservation) {
        reservationSystem.removeReservation(reservation);
        view.displayMessage("Đã hủy đặt bàn.");
    }
    // lay danh sach ban chua duoc kich hoat
    public void showEmptyTables(){
        List<Reservation> emptyTable =  reservationSystem.emptyTableList();
        view.displayEmptyTables(emptyTable);
    }
    public void showNonEmptyTables(){
        List<Reservation> reservedTables = reservationSystem.getNonEmptyTables();
        view.displayReservedTables(reservedTables);
    }

//    public void excuteReservation() {
//        this.makeReservation(this.);
//        this.cancelReservation(reservation);
//        this.showNonEmptyTables();
//        this.showEmptyTables();
//    }
}
