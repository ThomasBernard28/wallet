package wallet.APP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import wallet.API.JsonReader;
import wallet.APP.HistoryData;

public class History implements JsonReader {
   
   HistoryData data = new HistoryData();

   @Override
   public void read_data(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            data = mapper.readValue(json, HistoryData.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
   }

   public String get_ibanSender() {
      return data.getIbanSender();
   }

   public String get_ibanReceiver() {
      return data.getIbanReceiver();
   }

   public String get_amount() {
      return data.getAmount();
   }

   public String get_prevBalance() {
      return data.getPrevBalance();
   }

   public String get_nextBalance() {
      return data.getNextBalance();
   }

   public String get_dateTime() {
      return data.getDateTime();
   }

   public String get_comments() {
      return data.getComments();
   }

   @Override
   public String toString() {
      return data.toString();
   }
}
