package CarDealership;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private static Scanner scanner = new Scanner(System.in);

    public UserInterface() {
    }

    private void init() {
        this.dealership = DealershipFileManagement.getDealerShip();
    }

    public void display() {
        init();
        boolean keepGoing = true;
        while(keepGoing) {
            displayMenu();
            keepGoing = processUserChoice(Integer.parseInt(scanner.nextLine()));
        }
        System.out.println("Thank you for using our app! Bye now.");
    }

    public void displayMenu() {
        System.out.println( """
                1 - Find vehicles within a price range
                2 - Find vehicles by make / model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type (car, truck, SUV, van) 
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                10 - Sale
                11 - Lease
                12 - Admin Access
                99 - Quit
                """);
    }

    public boolean processUserChoice(int choice) {
        switch(choice) {
            case 1:
                processGetByPriceRequest();
                break;
            case 2:
                processGetByMakeModelRequest();
                break;
            case 3:
                processGetByYearRequest();
                break;
            case 4:
                processGetByColorRequest();
                break;
            case 5:
                processGetByMileageRequest();
                break;
            case 6:
                processGetByTypeRequest();
                break;
            case 7:
                displayVehicles(dealership.getInventory());
                break;
            case 8:
                processAddVehicleRequest();
                break;
            case 9:
                processRemoveVehicleRequest();
                break;
            case 10:
                processSales();
                break;
            case 11:
                processLease();
                break;
            case 12:
                AdminUserInterface.signInCheck();
                break;
            case 99:
                return false;
            default:
                System.out.println("Invalid option");
        }
        return true;
    }

    public void processGetByPriceRequest() {
        System.out.println("What is the min price?");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.println("What is the max price?");
        double max = Double.parseDouble(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest() {
        System.out.println("What is the make?");
        String make = scanner.nextLine();
        System.out.println("What is the model?");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest() {
        System.out.println("What is the start year?");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.println("What is the end year?");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    public void processGetByColorRequest() {
        System.out.println("What is the color?");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        System.out.println("What is the min mileage?");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.println("What is the max mileage?");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByTypeRequest() {
        System.out.println("What is the type?");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByType(color));
    }

    public void processAddVehicleRequest() {
        System.out.println("Vin:");
        String vin = scanner.nextLine();
        System.out.println("Year:");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Make:");
        String make = scanner.nextLine();
        System.out.println("Model:");
        String model = scanner.nextLine();
        System.out.println("Type:");
        String type = scanner.nextLine();
        System.out.println("Color:");
        String color = scanner.nextLine();
        System.out.println("Odometer:");
        int odometer = Integer.parseInt(scanner.nextLine());
        System.out.println("Price:");
        double price = Double.parseDouble(scanner.nextLine());
        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        this.dealership.addVehicle(vehicle);

    }

    public void processRemoveVehicleRequest() {
        System.out.println("Vin:");
        String vin = scanner.nextLine();
        Vehicle vehicle = new Vehicle(vin, 0, null, null, null, null, 0, 0);
        this.dealership.removeVehicle(vehicle);
    }

//    public void processOfLease(){
//        System.out.println("Enter the vin");
//        String vin = scanner.nextLine();
//        this.dealership.getByVin(vin);
//    }

    private void displayVehicles(List<Vehicle> vehicleList) {
        for(Vehicle vehicle : vehicleList) {
            System.out.println(vehicle.toFileString());
        }
    }

    public void processLease(){
        System.out.println(" Enter the date");
        String date = scanner.nextLine();
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        System.out.println("Enter your Email");
        String email = scanner.nextLine();
        System.out.println("Enter the vin");
        String vin = scanner.nextLine();

        List<Vehicle> vehicleList = this.dealership.getByVin(vin);
        List<Contract> contractList = new ArrayList<>();

        String vinn = "";
        int year = 0;
        String make = "";
        String model = "";
        String vehicleType = "";
        String color = "";
        int odometer = 0;
        double originalPrice = 0;

        for (Vehicle vehicle : vehicleList){
             vinn = vehicle.getVin();
             year = vehicle.getYear();
             make = vehicle.getMake();
             model = vehicle.getModel();
             vehicleType = vehicle.getVehicleType();
             color = vehicle.getColor();
             odometer = vehicle.getOdometer();
             originalPrice = vehicle.getPrice();
        }
//        Vehicle vehicle = vehicleList.get(0);
//        double original = vehicleList.get(0).getPrice();

        Vehicle vehicle = new Vehicle(vinn,year,make,model,vehicleType,color,odometer,originalPrice);
        contractList.add(new LeaseContract(date,name,email,vehicle,originalPrice));

        for (Contract contract : contractList){
            ContractDataManager.saveContract(contract);
        }

        System.out.println("Successfully recorded");
    }

    public void processSales(){
        System.out.println(" Enter the date");
        String date = scanner.nextLine();
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        System.out.println("Enter your Email");
        String email = scanner.nextLine();
        System.out.println("Financed? (yes/no)");
        String finance = scanner.nextLine();

       boolean isFinance = false;
        if (finance.equalsIgnoreCase("yes")){
            isFinance = true;
        }
        System.out.println("Enter the vin");
        String vin = scanner.nextLine();

        List<Vehicle> vehicleList = this.dealership.getByVin(vin);
        List<Contract> contractList = new ArrayList<>();

        String vinn = "";
        int year = 0;
        String make = "";
        String model = "";
        String vehicleType = "";
        String color = "";
        int odometer = 0;
        double originalPrice = 0;

        for (Vehicle vehicle : vehicleList){
            vinn = vehicle.getVin();
            year = vehicle.getYear();
            make = vehicle.getMake();
            model = vehicle.getModel();
            vehicleType = vehicle.getVehicleType();
            color = vehicle.getColor();
            odometer = vehicle.getOdometer();
            originalPrice = vehicle.getPrice();
        }

        Vehicle vehicle = new Vehicle(vinn,year,make,model,vehicleType,color,odometer,originalPrice);
        contractList.add(new SalesContract(date,name,email,vehicle,isFinance,originalPrice));


        for (Contract contract : contractList){
            ContractDataManager.saveContract(contract);
        }
         this.dealership.removeVehicle(vehicle);

    }
}
