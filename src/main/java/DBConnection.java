import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Restaurant";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
        return conn;
    }
}
