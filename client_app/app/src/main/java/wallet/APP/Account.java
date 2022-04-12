package wallet.APP;

import java.util.ArrayList;
import java.io.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.nio.file.Paths;
import java.util.ArrayList;

import wallet.APP.AccountData;
import wallet.API.JsonReader;

public class Account implements JsonReader {

   private AccountData data = new AccountData();

   @Override
   public void read_data(String json) {
      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         data = mapper.readValue(json, AccountData.class);
      }
      catch (Exception e) {
         System.out.println("failed to read account data"); // debug
      }
   }

   @Override
   public String toString() {
      return data.toString();
   }
    
}
