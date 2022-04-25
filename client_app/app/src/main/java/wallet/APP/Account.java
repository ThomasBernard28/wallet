package wallet.APP;

import java.util.ArrayList;
import java.io.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.nio.file.Paths;
import java.util.ArrayList;

import wallet.APP.AccountData;
import wallet.API.JsonReader;
import wallet.API.Api;

public class Account implements JsonReader {

   private AccountData data = new AccountData();

   public Account() {}

   public Account(String type) {
      data.setType(type);
   }

    /* 
     * read the given json file and save the data in the 
     * data instance of the current user 
     * @param json : the object's data as a Json
     */
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

    /*
     * @return the account's data as a json 
     */
   public String write_data() {
      return "{\"bic\":\""+get_bic()+"\", \"userID\":\""+get_userID()+"\", \"type\":\""+get_type()+"\"}";
   }

   public String get_bic() {
      return data.getBic();
   }

   public String get_userID() {
      return data.getUserID();
   }

   public float get_avgBalance() {
      return data.getAvgBalance();
   }

   public String get_localCurr() {
      return data.getLocalCurr();
   }

   public String get_iban() {
      return data.getIban();
   }

   public String get_type() {
      return data.getType();
   }

   @Override
   public String toString() {
      return data.toString();
   }
    
}
