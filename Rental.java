public class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        vehicle.rent();
    }

    public void returnVehicle() {
        vehicle.returnVehicle();
    }

    public void printInvoice() {
        double cost = days * vehicle.getPricePerDay();
        System.out.println("Customer: " + customer.getName());
        vehicle.displayDetails();
        System.out.println("Rental days: " + days + ", Total cost: ₹" + cost);
    }
}
