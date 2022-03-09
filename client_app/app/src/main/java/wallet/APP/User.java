package wallet.APP;

import java.util.ArrayList;
import java.io.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void read_data(String filePath) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(Paths.get(filePath).toFile(), UserData.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write_data(String data) {
    }

    public void set_walletsList(String nID) {
         // API CALL 
    }

    public ArrayList get_walletsList() {
        return walletsList;
    }

    public String get_userID() {
        return data.getFirstName() + data.getLastName() + data.getNationalID();
    }

    public String get_firstName() {
        return data.getFirstName();
    }

    public String get_lastName() {
        return data.getLastName();
    }

    public int get_nationalID() {
        return data.getNationalID();  
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
