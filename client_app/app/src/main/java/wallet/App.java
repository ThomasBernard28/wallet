package wallet;

import java.util.ArrayList;

import wallet.API.Api;
import wallet.APP.User;
import wallet.APP.UserData;
import wallet.GUI.controllers.MainMenuController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.File;

public class App extends Application {

    public static Api   api         = new Api();
    public static User  currentUser;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
        Parent root = fxmlLoader.load(); 
        Scene scene = new Scene(root, 320, 240);

        this.stage = stage;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);

        stage.show();
    }
   
    public static void main(String[] args) {
        // start tmp 
        String userID = "thomasbernard02032811708"; // ceci doit etre recupere via le gui ( tiramisu )
        try {
            connect(userID);
            System.out.println(currentUser);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // end tmp 

        launch();
    }

    public static void connect(String userID) {
        currentUser = new User();
        try {
            currentUser.read_data(api.get_user(userID));;    
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        currentUser = null;
    }


}
