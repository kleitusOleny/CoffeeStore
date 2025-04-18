import model.customerSystem.Observer;

public interface Subject {
    public void addObsever(Observer o);
    
    public void removeObsever(Observer o);
}
