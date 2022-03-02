package wallet;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import wallet.APP.User;
import wallet.APP.UserData;

public class App {

    public static User currentUser;
    
    public static void main(String[] args) {
        connect();
        currentUser.add_wallet("build/resources/main/yaml/walletExample.json");
        currentUser.add_wallet("build/resources/main/yaml/walletExample.json");

        System.out.println("----- USER -----");
        System.out.println(currentUser);
        ArrayList walletsList = currentUser.get_walletsList();
        for(int i = 0; i < walletsList.size(); i++) {
            System.out.println("----- WALLET -----");
            System.out.println(walletsList.get(i));
        }
    }

    public static void connect() {
        // userYamlFile = API CALL
        String userFile = "build/resources/main/yaml/userExample.json"; // tmp
        currentUser = new User();
        try {
            currentUser.read_data(userFile);    
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        currentUser = null;
    }


}
