import java.sql.*;

public class testJDBCConnect  {
    /*
    Cách dùng JDBC
    gọi Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
    tạo url = "jdbc:sqlserver://<Tên Sever>\\<SQLEXPRESS>:<Port>;database=<Tên DB>;user=sa; password = pass;encrypt=true;trustServerCertificate=true";
    Tạo biến Connection connection = new DriverManager.getConnection(url)
    
    cách chạy DDL
    tạo lệnh
    String <tên lệnh> = "<Lệnh>"
    Statement <ten Phuong thuc> = connection.createStatement();
    Result rs = statement.executeQuery(<Ten Lenh>)
    
    Cach lay du lieu
    While(rs.next()){
        String a = rs.getString(1)
        ...
        //Với 1 là chỉ số cột trong bảng
    }
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-IHVAGMD\\SQLEXPRESS:1433;database=QLBH;user=sa;password=84642209;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "84642209";
            
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Connected to the database successfully");
            
            String sqlDDL = "SELECT * FROM CTHD";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlDDL);
            while (rs.next()) {
                System.out.print(rs.getString(1)+ "|");
                System.out.print(rs.getString(2)+ "|");
                System.out.print(rs.getString(3)+ "|");
                System.out.println();
            }
        }catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}
