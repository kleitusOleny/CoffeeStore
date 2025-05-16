package model;

import model.customerSystem.Observer;

public interface Subject {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
}
