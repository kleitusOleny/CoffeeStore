package model;

public class Cash implements PaymentStrategy {
    @Override
    public double pay() {
        return 0;
    }
}
