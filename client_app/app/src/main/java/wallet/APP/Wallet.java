package wallet.APP;

import java.util.ArrayList;
import java.io.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.nio.file.Paths;
import java.util.ArrayList;

import wallet.APP.WalletData;
import wallet.API.JsonReader;

public class Wallet implements JsonReader {

    private WalletData data         = new WalletData();
    private ArrayList  ProductsList = new ArrayList();

    @Override
    public void read_data(String json) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            data = mapper.readValue(json, WalletData.class);
        }
        catch (Exception e) {
            System.out.println("oopsie"); // debug
        }
    }

    @Override
    public String toString() {
        return data.toString();
    }


}
