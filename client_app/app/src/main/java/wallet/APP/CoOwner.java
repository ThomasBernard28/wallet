package wallet.APP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import wallet.API.JsonReader;
import wallet.APP.CoOwnerData;

public class CoOwner implements JsonReader {
   
    private CoOwnerData data = new CoOwnerData();
   
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
            data = mapper.readValue(json, CoOwnerData.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get_coOwnerID() {
        return data.getCoOwnerID();
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
