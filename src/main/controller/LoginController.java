package controller;

import data.dto.FormatAccounts;
import utils.LoginHandle;
import view.LoginPanel;
import view.MainFrame;

import javax.swing.*;
import java.util.Observable;

public class LoginController extends Observable {
    private LoginHandle model;
    private LoginPanel view;
    
    public LoginController(LoginHandle loginModel, LoginPanel loginPanel) {
        this.model = loginModel;
        this.view = loginPanel;
    }
    
    public void Login() {
        view.setLoginListener(e -> {
            String username = view.userField.getText().trim();
            String password = new String(view.passField.getPassword()).trim();
            int process = LoginHandle.verifyLogin(username, password);
            switch (process){
                case 0: JOptionPane.showMessageDialog(null, "Bạn đã nhập sai tài khoản hoặc mật khẩu \n                Vui lòng nhập lại", "Xác thực không thành công", JOptionPane.ERROR_MESSAGE); break;
                case 1: view.getMainFrame().showPanel(MainFrame.MANAGER); break;
                case 2: view.getMainFrame().showPanel(MainFrame.EMPLOYEE);break;
            }
        });
        
    }
}
