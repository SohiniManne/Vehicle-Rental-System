import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VehicleRentalSystem system = new VehicleRentalSystem();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Vehicle Rental System =====");
            System.out.println("1. List Available Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. Return a Vehicle");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    system.listAvailableVehicles();
                    break;

                case 2:
                    System.out.print("Enter Vehicle ID to rent: ");
                    String rentId = sc.nextLine();
                    Vehicle rentVehicle = system.findVehicleById(rentId);

                    if (rentVehicle != null && rentVehicle.isAvailable()) {
                        System.out.print("Enter Customer ID: ");
                        String custId = sc.nextLine();
                        System.out.print("Enter Customer Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter number of days: ");
                        int days = sc.nextInt();
                        sc.nextLine(); // Clear buffer

                        system.rentVehicle(rentVehicle); // Mark as rented in DB
                        Rental rental = new Rental(rentVehicle, new Customer(custId, name), days);
                        System.out.println("\n--- Invoice ---");
                        rental.printInvoice();
                    } else {
                        System.out.println("Vehicle not available or invalid ID.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Vehicle ID to return: ");
                    String returnId = sc.nextLine();
                    Vehicle returnVehicle = system.findVehicleById(returnId);

                    if (returnVehicle != null && !returnVehicle.isAvailable()) {
                        system.returnVehicle(returnVehicle); // Mark as available in DB
                        System.out.println("Vehicle returned successfully.");
                    } else {
                        System.out.println("Invalid ID or vehicle is not currently rented.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}
