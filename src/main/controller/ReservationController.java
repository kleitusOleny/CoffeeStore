package controller;

import model.reservation_system.ReservationSystem;
import view.IView;

public class ReservationController {
    ReservationSystem reservationSystem;
    IView view;
    public ReservationController(ReservationSystem reservationSystem, IView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }
}
