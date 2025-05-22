package controller;

import data.FormatAccounts;
import utils.LoginModel;
import view.LoginPanel;
import view.MainFrame;

public class LoginController {
    private LoginModel model;
    private LoginPanel view;
    
    public LoginController(LoginModel loginModel, LoginPanel loginPanel) {
        this.model = loginModel;
        this.view = loginPanel;
    }
    
    public void Login() {
        view.setLoginListener(e -> {
            String username = view.userField.getText().trim();
            String password = new String(view.passField.getPassword()).trim();
            
            for (FormatAccounts accounts : view.accountsList) {
                if (username.equals(accounts.getUsername()) && password.equals(accounts.getPassword())) {
                    if (accounts.getRole().equals("Manager")) {
                        view.getMainFrame().showPanel(MainFrame.MANAGER);
                    } else {
                        view.getMainFrame().showPanel(MainFrame.EMPLOYEE);
                    }
                    
                    return;
                }
            }
        });
        
    }
}
