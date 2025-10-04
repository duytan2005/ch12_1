package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:postgresql://dpg-d3ghdu7fte5s73c5aon0-a.singapore-postgres.render.com:5432/bai8?sslmode=require";
    private static final String USER = "bai8_user";
    private static final String PASSWORD = "BZjODcHRjcrLTLLzJDC6Gciq9gcLODUy"; // copy từ Render

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // load driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy driver PostgreSQL!", e);
        }
    }

    // Test kết nối
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("✅ Kết nối PostgreSQL Render thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
