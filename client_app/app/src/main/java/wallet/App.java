package wallet;

import java.io.FileNotFoundException;

import wallet.APP.User;
import wallet.APP.UserData;

public class App {

    public static User currentUser;
    
    public static void main(String[] args) {
        connect();
        System.out.println(currentUser);
    }

    public static void connect() {
        // userYamlFile = API CALL
        String userFile = "build/resources/main/yaml/userExample.yaml"; // tmp
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
