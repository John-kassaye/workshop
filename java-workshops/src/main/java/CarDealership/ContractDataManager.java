package CarDealership;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ContractDataManager {

    public static void saveContract(Contract contract){
        List<Contract> contractList = new ArrayList<>();
        contractList.add(contract);

        try(FileWriter fileWriter = new FileWriter("contracts.csv",true)){
           for (Contract contract1 : contractList){
               if (contract1 instanceof LeaseContract){
                   LeaseContract leaseContract = (LeaseContract) contract1;
                   fileWriter.write("\n" + contract1.toString());
               } else if (contract1 instanceof SalesContract) {
                   SalesContract salesContract = (SalesContract) contract1;
                   fileWriter.write("\n" + contract1.toString());
               }
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    public static List<Contract> getContract(){

        List<Contract> contractList = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("contracts.csv"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] split = line.split("\\|");
                if (split.length == 16){
                    String lease = split[0];
                    String date = split[1];
                    String name = split[2];
                    String email = split[3];
                    String vin = split[4];
                    int year = Integer.parseInt(split[5]);
                    String make = split[6];
                    String model = split[7];
                    String vehicleType = split[8];
                    String color = split[9];
                    int odometer = Integer.parseInt(split[10]);
                    double price = Double.parseDouble(split[11]);
                    String value = split[12];
                    String leaseFee = split[13];
                    String totalPrice = split[14];
                    String month = split[15];

                    Vehicle vehicle = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);
                    contractList.add(new LeaseContract(date,name,email,vehicle,price));
                } else if (split.length == 18) {
                    String sales = split[0];
                    String date = split[1];
                    String name = split[2];
                    String email = split[3];
                    String vin = split[4];
                    int year = Integer.parseInt(split[5]);
                    String make = split[6];
                    String model = split[7];
                    String vehicleType = split[8];
                    String color = split[9];
                    int odometer = Integer.parseInt(split[10]);
                    double price = Double.parseDouble(split[11]);
                    String tax = split[12];
                    String recordingFee = split[13];
                    String processingFee = split[14];
                    String totalPrice = split[15];
                    boolean finance = Boolean.parseBoolean(split[16]);
                    String month = split[17];

                    Vehicle vehicle = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);
                    contractList.add(new SalesContract(date,name,email,vehicle,finance,price));
                } else {
                    System.out.println("No no no!!1");
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        return contractList;
    }
}
