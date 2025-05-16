package CarDealership;

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
                   fileWriter.write(contract1.format());
               } else if (contract1 instanceof SalesContract) {
                   SalesContract salesContract = (SalesContract) contract1;
                   fileWriter.write(contract1.format());
               }
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
