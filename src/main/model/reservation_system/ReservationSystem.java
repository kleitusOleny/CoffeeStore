package model.reservation_system;

import model.IModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ReservationSystem extends Observable implements IModel{
    private List<Reservation> listReser;
    private List<Table> tables = new ArrayList<>();

    public ReservationSystem() {
        this.listReser = new ArrayList<>();
    }

    public void makeReservation(Reservation reservation) {
        if (reservation.checkStatus()){
            System.out.println("This reservation has already been made");
        } else {
            reservation.getTable().setStatus(true);
            listReser.add(reservation);
            System.out.println("Reservation has been made successfully");
        }
    }

    public void removeReservation(Reservation reservation) {
        listReser.remove(reservation);
    }

    /**
     * tra ra danh sach cac ban chua co khach dat
     *
     * @return
     */

    public List<Table> emptyTableList(){
       List<Table> result = new ArrayList<>();
       for (Table table : tables) {
           if (!table.isStatus()){
               result.add(table);
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

    public List<Reservation> getListReser() {
        return listReser;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void addTable(Table table) {
        tables.add(table);

    }

}
