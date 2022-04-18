package wallet;

import java.util.ArrayList;

import wallet.APP.Employee;
import wallet.API.Api;

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

    public static Api      api = new Api();
    public static Employee currentEmployee;
    public static Stage    stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = fxmlLoader.load(getFileFromResourceAsStream("GUI/fxml/loginscreen.fxml"));
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

    public static boolean connect(String bic) {
        currentEmployee = new Employee();
        try {
            currentEmployee.read_data(api.get_employee(bic));
            if (currentEmployee.get_bic().equals(bic)) {
                return true;
            }
        }
        catch (Exception e) {}
        disconnect();
        return false;
    }

    public static void disconnect() {
        currentEmployee = null;
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
