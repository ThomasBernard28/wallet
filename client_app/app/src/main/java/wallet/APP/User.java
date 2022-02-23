package wallet.APP;

import java.util.ArrayList;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import wallet.APP.UserData;
import wallet.APP.Wallet;
import wallet.APP.WalletData;
import wallet.API.YamlReader;

/* Represent a user of the application */
public class User implements YamlReader {

    private UserData          data        = new UserData();
    private ArrayList<Wallet> walletsList = new ArrayList<>();

    public User() {
    }

    /* read the given yaml file and save the data in the data instance of the current user */
    @Override
    public void read_data(String filePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            File yamlFile = new File(filePath);
            data = objectMapper.readValue(yamlFile, UserData.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set_walletsList(String nID) {
         // API CALL 
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
    }

    @Override
    public String toString() {
        return data.toString();
    }

 
}
