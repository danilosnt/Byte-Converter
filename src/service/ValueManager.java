package service;

import java.sql.*;
import java.util.ArrayList;

public class ValueManager {
    public static boolean registerValue(long byteValue, int userId) {
        String insertQuery = "INSERT INTO values (byte_value, user_id) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertQuery)) {

            ps.setLong(1, byteValue);
            ps.setInt(2, userId);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error during value registration: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<Value> listValues(int userId) {
        ArrayList<Value> values = new ArrayList<>();
        String selectQuery = "SELECT * FROM values WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectQuery)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                values.add(new Value(rs.getLong("byte_value"), rs.getInt("user_id")));
            }

        } catch (SQLException e) {
            System.out.println("Error during value listing: " + e.getMessage());
        }

        return values;
    }

    public static boolean modifyValue(long newByteValue, int valueId, int userId) {
        String updateQuery = "UPDATE values SET byte_value = ? WHERE id = ? AND user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateQuery)) {

            ps.setLong(1, newByteValue);
            ps.setInt(2, valueId);
            ps.setInt(3, userId);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error during value modification: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteValue(int valueId, int userId) {
        String deleteQuery = "DELETE FROM values WHERE id = ? AND user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteQuery)) {

            ps.setInt(1, valueId);
            ps.setInt(2, userId);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error during value deletion: " + e.getMessage());
            return false;
        }
    }
}
