package model.reservation_system;

public class ReservationStatus {
    private String action;
    private Reservation reservation;
    
    public ReservationStatus(String action, Reservation reservation) {
        this.action = action;
        this.reservation = reservation;
    }
    
    public String getAction() {
        return action;
    }
    public Reservation getReservation() {
        return reservation;
    }
}
