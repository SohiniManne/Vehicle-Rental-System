import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/vehicle_rental";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    // Get a list of all vehicles
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vehicles")) {

            while (rs.next()) {
                String id = rs.getString("id");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                double pricePerDay = rs.getDouble("price_per_day");
                boolean isAvailable = rs.getBoolean("is_available");

                Vehicle v = new Vehicle(id, brand, model, pricePerDay);
                if (!isAvailable) v.rent();
                vehicles.add(v);
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to load vehicles from DB");
            e.printStackTrace();
        }

        return vehicles;
    }

    // Update availability of a vehicle
    public void updateAvailability(String vehicleId, boolean available) {
        String sql = "UPDATE vehicles SET is_available = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, available);
            stmt.setString(2, vehicleId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Failed to update vehicle availability");
            e.printStackTrace();
        }
    }

    // Optionally, add insertVehicle, deleteVehicle methods here
}
