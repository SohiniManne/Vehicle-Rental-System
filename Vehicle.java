public class Vehicle {
    private String id;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;

    public Vehicle(String id, String brand, String model, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    // Used during loading from file
    public Vehicle(String id, String brand, String model, double pricePerDay, boolean isAvailable) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = isAvailable;
    }
    
    public String getBrand() {
    return brand;
}

public String getModel() {
    return model;
}


    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnVehicle() {
        isAvailable = true;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void displayDetails() {
        System.out.println("ID: " + id + ", " + brand + " " + model + ", ₹" + pricePerDay + "/day, " +
                           (isAvailable ? "Available" : "Rented"));
    }

    // For saving to file
    @Override
    public String toString() {
        return id + "," + brand + "," + model + "," + pricePerDay + "," + isAvailable;
    }

    // For loading from file
    public static Vehicle fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
            String id = parts[0];
            String brand = parts[1];
            String model = parts[2];
            double pricePerDay = Double.parseDouble(parts[3]);
            boolean isAvailable = Boolean.parseBoolean(parts[4]);
            return new Vehicle(id, brand, model, pricePerDay, isAvailable);
        }
        return null; // Invalid line
    }
}
