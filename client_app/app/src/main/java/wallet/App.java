package wallet;

import wallet.APP.User;

public class App {
    
    public static void main(String[] args) {
        User user = new User();
        try {
            user.read_data("build/resources/main/yaml/userExample.yaml");
        }
        catch (Exception e) {
            System.out.println("Cannot read data");
            System.out.println(e);
        }
        user.add_wallet("build/resources/main/yaml/walletExample.yaml");
        System.out.println(user);

    }
}
