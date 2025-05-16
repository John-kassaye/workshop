package CarDealership;

import java.util.ArrayList;
import java.util.List;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double totalPrice;
    private double monthlyPayment;


    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public String format(){
        return String.format("%-45s %s","Date",getDate()) +
                String.format("%-45s %s","Customer name",getCustomerName()) +
                String.format("%-45s %s","Email",getCustomerEmail()) +
                String.format("%-45s %s","Total price",totalPrice) +
                String.format("%-45s %s","Monthly payment",getMonthlyPayment());

    }

    public abstract String toString();
}
