package wallet;

import java.util.ArrayList;

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
        launch();
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
