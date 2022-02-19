package wallet;

import wallet.APP.User;
import java.io.FileNotFoundException;

public class App {

    public static User currentUser;
    
    public static void main(String[] args) {
        connect("theo", "linux4life");
        System.out.println(currentUser);
    }

    public static boolean connect(String username, String passwd) {
        // userYamlFile = API CALL
        String userFile = "build/resources/main/yaml/userExample.yaml"; // tmp
        currentUser = new User(username);
        try {
            currentUser.read_data(userFile);
        }
        catch (Exception e) {
            System.out.println("User data file not found");
            disconnect();
            return false;
        }
        return true;
    }

    public static void disconnect() {
        currentUser = null;
    }

    public static void change_password(String new_passwd) {
        // API CALL
    }


}
