package utils;

import model.IModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class LoginHandle extends Observable implements IModel {
    
    private static Map<String,String[]> accounts = LoadDataToModel.loadAccountDataToModel();
    
    public int verifyLogin(String username, String password) {
        int statusCode;
        String message;
        
        if (accounts.containsKey(username)) {
            if (accounts.get(username)[0].equals(password)) {
                statusCode = Integer.parseInt(accounts.get(username)[1]);
                message = "Đăng nhập thành công";
            } else {
                statusCode = 0;
                message = "Sai mật khẩu";
                System.out.println(message);
            }
        } else {
            statusCode = 0;
            message = "Tài khoản không tồn tại";
            System.out.println(message);
        }
        
        // Notify observers with login status
        setChanged();
        notifyObservers(new LoginStatus(statusCode, message));
        return statusCode;
    }
    
    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

}
