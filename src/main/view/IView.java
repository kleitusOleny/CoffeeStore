package view;

import model.customer_system.Customer;
import model.reservation_system.Reservation;

import java.util.List;

public interface IView {
    public IView getView();
    public void showPanel(String namePanel);

    void displayMessage(String s);

    void displayEmptyTables(List<Reservation> emptyTable);

    void displayReservedTables(List<Reservation> reservedTables);
}
