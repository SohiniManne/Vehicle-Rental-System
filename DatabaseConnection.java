import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/vehicle_rental", "root", "Ammu@2005"
            );
            System.out.println("✅ Connection successful!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
    }
}
