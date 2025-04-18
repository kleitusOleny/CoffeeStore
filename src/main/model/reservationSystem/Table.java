package model.reservationSystem;

public class Table {
    private int idTable;
    private int capacity;
    private boolean status;

    public Table(int idTable, int capacity, boolean status) {
        this.idTable = idTable;
        this.capacity = capacity;
        this.status = status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdTable() {
        return idTable;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isStatus() {
        return status;
    }
}
