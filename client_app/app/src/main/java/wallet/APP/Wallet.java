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

    public Wallet() {}

    public Wallet(String userID, String bic, String openingDate, int activity) {
        data.setUserID(userID);
        data.setBic(bic);
        data.setOpeningDate(openingDate);
        data.setActivity(activity);
    }

    @Override
    public void read_data(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            data = mapper.readValue(json, WalletData.class);
        }
        catch (Exception e) {
            System.out.println("oopsie"); // debug
        }
    }

    public String write_data() {
        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(data);
            json = "{" + json.substring(14, json.length()); // remove "walletID":"0", from the json
        }
        catch (Exception e) {
            System.out.println("failed to write wallet data");
        }
        return json;
    }

    public int get_walletID() {
        return data.getWalletID();
    }

    public String get_userID() {
        return data.getUserID();
    }

    public String get_bic() {
        return data.getBic();
    }

    public String get_openingDate() {
        return data.getOpeningDate();
    }

    public int get_activity() {
        return data.getActivity();
    }

    @Override
    public String toString() {
        return data.toString();
    }


}
