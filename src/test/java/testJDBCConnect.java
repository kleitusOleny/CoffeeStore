import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;

public class testJDBCConnect  {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-IHVAGMD\\SQLEXPRESS:1433;database=QLBH";
            String username = "sa";
            String password = "84642209";
            
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully");
        }catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}
