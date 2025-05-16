package CarDealership;

public class SalesContract extends Contract{
    private boolean finance;
    private String addOnOptions;
    private Vehicle vehicle;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold,
                         boolean finance, String addOnOptions) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;
        this.addOnOptions = addOnOptions;
        this.vehicle = vehicleSold;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getOriginalPrice() {
        return getVehicleSold().getPrice();
    }

    public double getTaxAmount() {
        return getOriginalPrice() * 0.05;
    }

    public double getRecordingFee() {
        return 100;
    }

    public double getProcessingFee() {
        return getOriginalPrice() >= 10000 ? 495 : 295;
    }

    public boolean isFinance() {
        return finance;
    }

    public String getFinance(){
        return isFinance() ? "Yes" : "No";
    }

    public String getAddOnOptions() {

        String addOn = "";
            if (addOnOptions.trim().equals("1")) {
                addOn = "Nitrogen tires";
            } else if (addOnOptions.trim().equals("2")) {
                addOn = "All-season floor mats";
            } else if (addOnOptions.trim().equals("3")) {
                addOn = "Cargo tray";
            } else if (addOnOptions.trim().equals("4")) {
                addOn = "Wheel locks";
            } else if (addOnOptions.trim().equals("5")) {
                addOn = "window tinting";
            } else if (addOnOptions.trim().equals("6")) {
                addOn = "Splash guards";
            } else if (addOnOptions.trim().equals("7")) {
                addOn = "No add-ons";
            }
        return addOn;
    }

    @Override
    public double getTotalPrice() {
        return getOriginalPrice() + getTaxAmount() + getRecordingFee() + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment() {

        if (isFinance() && getOriginalPrice() >= 10000) {
            return (double) Math.round((getTotalPrice() * 1.0425) * 4 / 48);
        } else if (isFinance()){
            return (double) Math.round((getTotalPrice() * 1.0525) * 2 / 24);
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString(){
        return "SALES" + "|" + getDate() + "|" + getCustomerName() + "|" + getCustomerEmail() + "|" + getVehicle().toString() + "|" + getTaxAmount() + "|"
                + getRecordingFee() + "|" + getProcessingFee() + "|" + getTotalPrice() + "|" + getFinance() + "|" + getMonthlyPayment() + "|" + getAddOnOptions();

    }
}
