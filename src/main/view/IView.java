package view;

import model.customer_system.Customer;
import utils.LoginModel;

public interface IView {
    public IView getView();
    public void showPanel(String namePanel);
    public LoginPanel getLoginPanel();
}
