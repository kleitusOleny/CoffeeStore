package controller;

import model.reservation_system.Reservation;
import model.reservation_system.ReservationSystem;
import model.reservation_system.Table;
import view.IView;
import view.Staff.TablePanel;

import java.util.List;

public class ReservationController implements IController {
    ReservationSystem reservationSystem;
    TablePanel view;

    public ReservationController(ReservationSystem reservationSystem, TablePanel view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

}
