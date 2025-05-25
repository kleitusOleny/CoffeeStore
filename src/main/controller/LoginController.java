package controller;

import utils.LoginHandle;
import view.LoginPanel;

public class LoginController implements IController {
    private LoginHandle model;
    private LoginPanel view;
    
    public LoginController(LoginHandle loginModel, LoginPanel loginPanel) {
        this.model = loginModel;
        this.view = loginPanel;
        init();
    }
    
    private void init() {
        view.setLoginListener(e -> {
            String username = view.getUserField().getText().trim();
            String password = new String(view.getPassField().getPassword()).trim();
            model.verifyLogin(username, password);
        });
    }
}
