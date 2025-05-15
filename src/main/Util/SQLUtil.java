package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtil {
    private static final String URL = "jdbc:sqlserver://DESKTOP-IHVAGMD\\SQLEXPRESS:1433;database=QLBH;user=sa;password=84642209;encrypt=true;trustServerCertificate=true";
    
    public Connection getConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }
    
    
}
