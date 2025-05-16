package CarDealership;

import java.io.*;
import java.util.Scanner;

public class AdminUserInterface {
    static Scanner scanner = new Scanner(System.in);
    public static void signInCheck() {

            System.out.println("Enter your password");
            String password = scanner.nextLine();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("admin password"))) {
                String line = bufferedReader.readLine();

                if (line.trim().equals(password)) {
                    home();
                } else {
                    System.out.println("Incorrect user name or password");
                }
            } catch (IOException e) {
                // Catch any file-related errors (like file not found) and show error message
                System.out.println("Error");

            }
        }

        public static void home(){
            System.out.println(" 1: List All Contracts");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                getContract();
                break;
                default:
                    System.out.println("Invalid input");
            }
        }

        public static void getContract(){

        for (Contract contract : ContractDataManager.getContract()){
            if (contract instanceof SalesContract){
                SalesContract salesContract = (SalesContract) contract;
                System.out.println(contract.toString());
            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                System.out.println(contract.toString());
            }
        }
    }
}
