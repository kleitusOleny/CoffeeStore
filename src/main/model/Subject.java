package model;

import model.customer_system.Observer;

public interface Subject {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
}
