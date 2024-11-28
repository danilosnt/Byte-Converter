package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static int authenticate(String username, String password) {
        String query = "SELECT id FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error during authentication: " + e.getMessage());
        }

        return -1;  // User not found
    }

    public static boolean registerUser(String username, String password) {
        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertQuery)) {

            ps.setString(1, username);
            ps.setString(2, password);
            int result = ps.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error during user registration: " + e.getMessage());
            return false;
        }
    }
}
