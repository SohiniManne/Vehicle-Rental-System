// VehicleIO.java
import java.io.*;
import java.util.*;

public class VehicleIO {
    public static void saveVehicles(String filename, List<Vehicle> vehicles) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Vehicle v : vehicles) {
                writer.println(v.getId() + "," + v.getBrand() + "," + v.getModel() + "," + v.getPricePerDay() + "," + v.isAvailable());
            }
        } catch (IOException e) {
            System.out.println("Error saving vehicles: " + e.getMessage());
        }
    }

    public static List<Vehicle> loadVehicles(String filename) {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Vehicle v = new Vehicle(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
                    if (!Boolean.parseBoolean(parts[4])) {
                        v.rent(); // mark it as rented
                    }
                    vehicles.add(v);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading vehicles: " + e.getMessage());
        }
        return vehicles;
    }
}
