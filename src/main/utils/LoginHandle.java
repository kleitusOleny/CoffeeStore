package utils;

import java.util.HashMap;
import java.util.Map;

public class LoginHandle {
    
    private static Map<String,String[]> accounts = LoadDataToModel.loadAccountDataToModel();
    
    public static int verifyLogin(String username, String password) {
        if (accounts.containsKey(username)) {
            if (accounts.get(username)[0].equals(password)) {
                return Integer.parseInt(accounts.get(username)[1]);
            }else {
                System.out.println("Sai mật khẩu");
                return 0;
            }
        }else {
            System.out.println("Tài khoản không tồn tại");
            return 0;
        }
    }


}
