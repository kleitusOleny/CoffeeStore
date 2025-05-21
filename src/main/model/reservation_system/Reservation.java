package model.reservation_system;

public class Reservation {
    private Table table;
    private String idEmp;
    private String idCus;
    private String nameCus;
    private int numOfPersons;

    public Reservation() {
    }

    public Reservation(Table table, String idEmp, String idCus, String nameCus, int numOfPersons) {
        this.table = table;
        this.idEmp = idEmp;
        this.idCus = idCus;
        this.nameCus = nameCus;
        this.numOfPersons = numOfPersons;
    }

    public Table getTable() {
        return table;
    }

    // kiem tra trang thai ban da dat chua
    public boolean checkStatus(){
        return table.isStatus() ? true : false;
    }
}
