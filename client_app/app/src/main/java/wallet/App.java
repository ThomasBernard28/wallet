package wallet;

import wallet.API.Api;
import wallet.APP.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.*;

public class App extends Application {

    public static Api api = new Api();
    public static User currentUser;
    public static Stage stage;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        // FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
        Parent root = fxmlLoader.load(getFileFromResourceAsStream("GUI/fxml/hello-view.fxml"));
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

    public static boolean connect(String userID) {
        currentUser = new User();
        try {
            currentUser.read_data(api.get_user(userID));
            if (currentUser.get_userID().equals(userID)) {
                return true;
            }
        }
        catch (Exception e) {
        }
        disconnect();
        return false;
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
