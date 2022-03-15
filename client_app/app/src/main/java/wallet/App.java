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
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class App extends Application {

    public static Api api = new Api();
    public static User currentUser;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        // FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
        Parent root = fxmlLoader.load(getFileFromResourceAsStream("GUI/fxml/mainmenu.fxml"));
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
            // connect(userID);
            //System.out.println(currentUser);
            //currentUser.set_walletsList(api.get_wallets(userID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // end tmp 

        launch();
    }

    public static void connect(String userID) {
        currentUser = new User();
        try {
            currentUser.read_data(api.get_user(userID));
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        currentUser = null;
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

}
