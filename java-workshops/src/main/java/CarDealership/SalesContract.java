package CarDealership;

public class SalesContract extends Contract{
    private double originalPrice;
    private double taxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;
    private Vehicle vehicle;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold,
                         boolean finance, double originalPrice) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;
        this.originalPrice = originalPrice;
        this.recordingFee = 100;
        this.processingFee = originalPrice < 10000 ? 295 : 495;;
        this.taxAmount = originalPrice * 0.05;
        this.vehicle = vehicleSold;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public boolean isFinance() {
        return finance;
    }

    public String getFinance(){
        return isFinance() ? "Yes" : "No";
    }

    @Override
    public double getTotalPrice() {
        return originalPrice + taxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {

        double paymentPerYear = 0;
        double monthlyPayment = 0;

        if (isFinance() && originalPrice > 10000){
             paymentPerYear = (getTotalPrice() * 0.0425) * 4;
            monthlyPayment = (getTotalPrice() + paymentPerYear) / 48;
        } else if (isFinance()){
             paymentPerYear = (getTotalPrice() * 0.0525) * 2;
             monthlyPayment = (getTotalPrice() + paymentPerYear) / 24;
        }

        return Math.round(monthlyPayment);
    }

    @Override
    public String toString(){
        return "SALES" + "|" + getDate() + "|" + getCustomerName() + "|" + getCustomerEmail() + "|" + getVehicle().toString() + "|" + getTaxAmount() + "|"
                + getRecordingFee() + "|" + getProcessingFee() + "|" + getTotalPrice() + "|" + getFinance() + "|" + getMonthlyPayment();

    }
}
