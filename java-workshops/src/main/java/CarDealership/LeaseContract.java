package CarDealership;

public class LeaseContract extends Contract{
    private final double expectedEndingValue = 0.5;
    private final double leaseFee = 0.07;
    private Vehicle vehicle;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.vehicle = vehicleSold;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getExpectedEndingValue() {
        return getVehicleSold().getPrice() * expectedEndingValue;
    }

    public double getLeaseFee() {
        return Math.floor(getVehicleSold().getPrice() * leaseFee);
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        return (double) Math.round((getTotalPrice() * (1 + 0.04) * 3) / 36);
    }

    @Override
    public String toString(){
        return "LEASE" + "|" + getDate() + "|" + getCustomerName() + "|" + getCustomerEmail() + "|" + getVehicle().toString() + "|"
                + getExpectedEndingValue() + "|" + getLeaseFee() + "|" + getTotalPrice() + "|" + getMonthlyPayment();

    }
}
