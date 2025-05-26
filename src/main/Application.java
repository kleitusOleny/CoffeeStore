import view.MainFrame;

import java.io.*;

public class Application {
    public static final String ANSI_PINK = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN

    public static void execute() {
        new MainFrame();
    }

    public static void warningBegin(){
        System.out.println(RED_BRIGHT + "APP ĐƯỢC SỬ DỤNG DATA HOÀN TOÀN TỪ GSON LIBRARY (JSON)\nhttps://google.github.io/gson/" + GREEN_BRIGHT);
        System.out.println("Đây là danh sách tài khoản và mật khẩu để test app: " + CYAN_BRIGHT);
        System.out.println("Role: Seller/Staff, Username: nhanvien, Password: 1234");
        System.out.println("Role: Manager/Owner, Username: quanly, Password: 123");
    }
    
    public static void Credit() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/data/ascii_art_full.txt"));
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(ANSI_PINK + line + ANSI_RESET);
        }
        System.out.println(ANSI_PINK+ "https://github.com/kleitusOleny/CoffeeStore" + ANSI_RESET);
    }
}
