package wallet;

import java.util.ArrayList;


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


    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();

        // FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
        Parent root = fxmlLoader.load(getFileFromResourceAsStream("GUI/fxml/mainmenu2.fxml"));
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





}
