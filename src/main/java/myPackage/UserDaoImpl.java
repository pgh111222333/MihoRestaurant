package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl {
    private static final String connStr = "jdbc:sqlserver://localhost:1433;databaseName=YourDatabaseName;integratedSecurity=true;";

    public User findByUserName(String username) {
        String sql = "SELECT * FROM [User] WHERE username = ?";
        User user = null;

        try (Connection conn = DriverManager.getConnection(connStr);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUserName(rs.getString("username"));
                    user.setFullName(rs.getString("fullname"));
                    user.setPassWord(rs.getString("password"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setRoleid(rs.getInt("roleid"));
                    user.setPhone(rs.getString("phone"));
                    user.setCreatedDate(rs.getDate("createdDate"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
        return user;
    }
}
