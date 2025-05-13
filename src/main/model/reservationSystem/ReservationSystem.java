package model.reservationSystem;

import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {
    private List<Reservation> listReser;

    public ReservationSystem(List<Reservation> listReser) {
        this.listReser = new ArrayList<>();
    }

    public void makeReservation(Reservation reservation) {
        if (!reservation.checkStatus()){
            System.out.println("This reservation has already been made");
        } else {
            listReser.add(reservation);
            System.out.println("Reservation has been made successfully");
        }
    }

    public void removeReservation(Reservation reservation) {
        listReser.remove(reservation);
    }

}
