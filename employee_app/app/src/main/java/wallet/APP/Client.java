package wallet.APP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import wallet.APP.ClientData;
import wallet.API.JsonReader;

public class Client implements JsonReader {

   private ClientData data = new ClientData();

    @Override
    public void read_data(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            data = mapper.readValue(json, ClientData.class);
        }
        catch(Exception e) {}
    }

    public String get_userID() {
      return data.getUserID();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
