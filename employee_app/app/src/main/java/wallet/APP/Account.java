package wallet.APP;

import wallet.APP.AccountData;
import wallet.API.JsonReader;

public class Account implements JsonReader {
   
   private AccountData data = new AccountData();

   @Override
   public void read_data(String json) {
   }

   @Override
   public String toString() {
      return data.toString();
   }

}
