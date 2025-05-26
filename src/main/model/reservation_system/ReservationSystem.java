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
        this.tables = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            tables.add(new Table(i, false));
        }
    }

    public void makeReservation(Reservation reservation) {
        if (reservation.checkStatus()){
            System.out.println("This reservation has already been made");
        } else {
            reservation.getTable().setStatus(true);
            listReser.add(reservation);
            System.out.println("Reservation has been made successfully");
            setChanged();
            notifyObservers(new ReservationStatus("ADD_RESERVATION", reservation));
        }
    }

    public void removeReservation(Reservation reservation) {
        if (listReser.contains(reservation)) {
            reservation.getTable().setStatus(false);
            listReser.remove(reservation);
            System.out.println("Reservation has been removed successfully");
            setChanged();
            notifyObservers(new ReservationStatus("REMOVE_RESERVATION", reservation));
        }
    }
    
    public List<Table> emptyTableList(){
       List<Table> result = new ArrayList<>();
       for (Table table : tables) {
           if (!table.isStatus()){
               result.add(table);
           }
       }

        return result;
    }

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

    public void addTable(Table table){
        tables.add(table);
        setChanged();
        notifyObservers(new ReservationStatus("ADD_TABLE",null));
    }
    
    public void updateTableStatus(int id,boolean status) {
        for (Table table : tables) {
            if (table.getIdTable() == id) {
                table.setStatus(status);
                setChanged();
                notifyObservers(new ReservationStatus("UPDATE_TABLE_STATUS",null));
                break;
            }
        }
    }
}
