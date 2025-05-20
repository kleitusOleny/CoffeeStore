package controller;

import model.IModel;
import model.MainSystem;
import view.IView;
import view.MainFrame;

import javax.swing.*;
import java.io.FileNotFoundException;

public class LoginController {
//    private IView view;
//    private IModel mainSystem;
//
//    public LoginController(IView view, IModel mainSystem) {
//        this.view = view;
//        this.mainSystem = mainSystem;
//    }
//
//    public void handleLogin(String userName, String password) throws FileNotFoundException {
//        String role = mainSystem.validateUser(userName, password);
//        if (role == null) {
//            JOptionPane.showMessageDialog(view,
//                    "Tên đăng nhập hoặc mật khẩu không đúng!",
//                    "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        switch (role.toUpperCase()) {
//            case "MANAGER":
//                view.showPanel(MainFrame.MANAGER);
//                break;
//            case "EMPLOYEE":
//                view.showPanel(MainFrame.EMPLOYEE);
//                break;
//            default:
//                JOptionPane.showMessageDialog(view, "Vai trò không xác định: " +
//                        role, "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
//        }
//    }
}
