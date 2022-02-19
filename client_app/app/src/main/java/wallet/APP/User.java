package wallet.APP;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.ArrayList;

import wallet.API.YamlReader;
import wallet.APP.UserData;
import wallet.APP.Wallet;

/* Represent a user of the application */
public class User implements YamlReader {

    private UserData          data;
    private ArrayList<Wallet> walletsList = new ArrayList<>();
    private String            username;

    public User(String username) {
        this.username = username;
    }

    /* add a new wallet to the walletsList. It is not saved to the database (not yet) */
    public void add_wallet(String filePath) {
        Wallet wallet = new Wallet();
        try {
            wallet.read_data(filePath);
        }
        catch (Exception e) {
            System.out.println("error while adding a wallet. file path : " + filePath);
        }
        walletsList.add(wallet);
    }

    /* read the given yaml file and save the data in the userdata instance of the current user */
    @Override
    public String read_data(String filePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            File yamlFile = new File(filePath);
            data = objectMapper.readValue(yamlFile, UserData.class);
        }
        catch (Exception e) {
            System.out.println("file not found : " + filePath);
        }
        return data.toString();
    }

    @Override
    public String toString() {
        return data.toString();
    }

 
}
