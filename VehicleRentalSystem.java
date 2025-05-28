import java.util.List;

public class VehicleRentalSystem {
    private List<Vehicle> vehicles;
    private VehicleDAO dao;

    public VehicleRentalSystem() {
        dao = new VehicleDAO();
        vehicles = dao.getAllVehicles(); // Load from DB
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        // Optional: Add to database if needed
    }

    public void listAvailableVehicles() {
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                v.displayDetails();
            }
        }
    }

    public Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }

    public void rentVehicle(Vehicle vehicle) {
        vehicle.rent();
        dao.updateAvailability(vehicle.getId(), false);
    }

    public void returnVehicle(Vehicle vehicle) {
        vehicle.returnVehicle();
        dao.updateAvailability(vehicle.getId(), true);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }
}
