package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testconn {
    // Sửa chuỗi kết nối theo định dạng JDBC
	private static final String CONNECTION_URL = "jdbc:sqlserver://DESKTOP-IHTM6J0\\SQLEXPRESS;databaseName=Restaurant;integratedSecurity=true;TrustServerCertificate=true";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL)) {
            if (connection != null) {
                System.out.println("Kết nối thành công đến SQL Server!");
            }
        } catch (SQLException e) {
            System.out.println("Không thể kết nối đến SQL Server.");
            e.printStackTrace();
        }
    }
}
