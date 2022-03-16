package wallet.APP;

import java.util.ArrayList;
import java.io.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.nio.file.Paths;

import wallet.APP.UserData;
import wallet.APP.Wallet;
import wallet.APP.WalletData;
import wallet.API.JsonReader;

/* Represent a user of the application */
public class User implements JsonReader {

    private UserData          data        = new UserData();
    private ArrayList<Wallet> walletsList = new ArrayList<>();

    public User() {
    }

    /* read the given json file and save the data in the data instance of the current user */
    @Override
    public void read_data(String json) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(json, UserData.class);
        }
        catch(Exception e) {
        }
    }

    public void write_data(String userID) {
    }

    public void set_walletsList(String json) {
        walletsList.clear();
        json = json.substring(1, json.length()-1);
        int jsonStart = 0;
        
        for (int i=1; i<json.length()+1; i++) {
            if (i+1 == json.length()+1 || (json.charAt(i)==',' && json.charAt(i-1)=='}' && json.charAt(i+1)=='{')) {
                Wallet wallet = new Wallet();
                try {
                    wallet.read_data(json.substring(jsonStart, i));
                    walletsList.add(wallet);
                    jsonStart = i+1;
                }
                catch (Exception e) {
                }
            }
        }
    }

    public ArrayList get_walletsList() {
        return walletsList;
    }

    public String get_userID() {
        return data.getUserID();
    }

    public String get_firstName() {
        return data.getFirstName();
    }

    public String get_lastName() {
        return data.getLastName();
    }

    public int get_natID() {
        return data.getNatID();  
    }

    public String get_language() {
        return data.getLanguage();
    }

    public String get_password() {
        return data.getPsswd();
    }

    /* add a new wallet to the walletsList. It is not saved to the database (not yet) */
    public void add_wallet(String filePath) {
       Wallet wallet = new Wallet();
       try {
          wallet.read_data(filePath);
          walletsList.add(wallet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return data.toString();
    }

 
}
