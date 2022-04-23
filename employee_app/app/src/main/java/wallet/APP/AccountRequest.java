package wallet.APP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import wallet.API.JsonReader;
import wallet.APP.AccountRequestData;

public class AccountRequest implements JsonReader {
   
   private AccountRequestData data = new AccountRequestData();

   @Override
   public void read_data(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            data = mapper.readValue(json, AccountRequestData.class);
        }
        catch(Exception e) {}
   }

   public int get_accRequestID() {
      return data.getAccRequestID();
   }

   @Override
   public String toString() {
      return data.toString();
   }
}
