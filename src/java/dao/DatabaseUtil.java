package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/testDB";
    private static final String USER = "postgres";       // user pgAdmin
    private static final String PASSWORD = "123456"; // mật khẩu bạn đặt khi cài

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy driver PostgreSQL!", e);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("✅ Kết nối PostgreSQL thành công!");
        } catch (Exception e) {
        }
    }
}
