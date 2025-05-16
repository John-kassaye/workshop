package CarDealership;

public class LeaseContract extends Contract{
    private double originalPrice;
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail,
                         Vehicle vehicleSold, double originalPrice) {
        super(date, customerName, customerEmail, vehicleSold);
        this.originalPrice = originalPrice;
        this.expectedEndingValue = originalPrice * 0.5;
        this.leaseFee = originalPrice * 0.07;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return originalPrice + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {

        double paymentPerYear = (getTotalPrice() * 0.04) * 3;
        return getTotalPrice() + paymentPerYear / 36;
    }

    public String format(){
        return getDate() + "|" + getCustomerName() + "|" + getCustomerEmail() + "|" + getMonthlyPayment() + "|" + getExpectedEndingValue() + "|" + getTotalPrice();

    }
}
