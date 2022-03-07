package wallet;

import java.util.ArrayList;

import wallet.APP.User;
import wallet.APP.UserData;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static User currentUser;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(wallet.GUI.app.HelloApplication.class.getResource("build/resources/GUI/mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);

        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
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
