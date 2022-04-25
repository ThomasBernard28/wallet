package wallet.APP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import wallet.API.JsonReader;
import wallet.APP.BankData;

public class Bank implements JsonReader {
   
    private BankData data = new BankData();

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
            data = mapper.readValue(json, BankData.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get_name() {
        return data.getName();
    }

    public String get_bic() {
        return data.getBic();
    }

    @Override
    public String toString() {
        return get_name() + '\n' +
               get_bic()  + '\n';
    }
   
}
