package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.Event;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import wallet.App;

public class CardConnectController {

    @FXML
    private Button back;
    
    @FXML
    private void initialize() {
    }

    @FXML
    private void onLoginButtonClick() throws IOException {
    }

    @FXML
    private void onGridButtonClick(Event event) throws IOException {
        Button b = (Button) event.getSource();
        System.out.println(b.getId()); // tmp
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/hello-view.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

}
