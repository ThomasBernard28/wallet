package wallet.GUI.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.collections.*;

import java.io.File;
import java.io.IOException;

public class AddProductMenuController {
    @FXML
    Button back;
    @FXML
    ComboBox box;
    @FXML
    Button subscribe;

    @FXML
    private void initialize() {
        String types[] = {"Checking account", "Saving account"};
        box.setItems(FXCollections.observableArrayList(types));
    }

    @FXML
    private void onSubscribeButtonClick() throws IOException {
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/productlist.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

}
