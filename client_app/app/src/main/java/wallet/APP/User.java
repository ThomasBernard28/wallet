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

    @Override
    public void read_data(String filePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            File yamlFile = new File(filePath);
            data = objectMapper.readValue(yamlFile, UserData.class);
        }
        catch (Exception e) {
            System.out.println("file not found : " + filePath);
        }
    }

    @Override
    public String toString() {
        String s = ("--- USER INFO --- \n" + data.toString() + "\n" +
                    "---  WALLETS  --- \n");
        for (int i = 0; i < walletsList.size(); i++) {
            s += (walletsList.get(i).toString() + "\n");
        }
        return s;
    }

 
}
