package model.reservation_system;

import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {
    private List<Reservation> listReser;

    public ReservationSystem() {
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

    /**
     * tra ra danh sach cac ban chua co khach dat
     * @return
     */

    public List<Reservation> emptyTableList(){
       List<Reservation> result = new ArrayList<>();
       for (Reservation reservation : listReser) {
           if (!reservation.checkStatus()){
               result.add(reservation);
           }
       }
        return result;
    }
    /**
     * tra ra danh sach cac ban da co khach dat
     */
    public List<Reservation> getNonEmptyTables() {
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : listReser) {
            if (reservation.checkStatus()){
                result.add(reservation);
            }
        }
        return result;
    }

}
