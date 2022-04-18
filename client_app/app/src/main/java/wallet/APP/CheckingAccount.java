package wallet.APP;

import java.io.File;

import wallet.API.JsonReader;

public class CheckingAccount implements JsonReader {

   private CheckingAccountData data = new CheckingAccountData();

   @Override
   public void read_data(String filePath) {
   }

   @Override
   public String toString() {
      return data.toString();
   }
    
}
