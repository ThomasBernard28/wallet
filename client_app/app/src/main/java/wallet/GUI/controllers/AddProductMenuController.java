package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.collections.*;

import java.io.File;
import java.io.IOException;

import wallet.App;

public class AddProductMenuController {
    @FXML
    private Button back;
    @FXML
    private ComboBox box;
    @FXML
    private Button subscribe;
    @FXML
    private BorderPane borderPane;


    @FXML
    private void initialize() {
        // set comboBox values
        String types[] = {"Checking account", "Saving account"};
        box.setItems(FXCollections.observableArrayList(types));
        if (App.dark) {
            borderPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    private void onSubscribeButtonClick() throws IOException {
        if (box.getValue() == null) {
            Alert a = new Alert(AlertType.WARNING);
            a.setContentText("Please select an account type.");
            a.show();
        } else {
            boolean requested = App.currentUser.add_account((String) box.getValue());
            if (requested) {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText("The creation of your account will be verified and confirmed soon. Please be patient.");
                a.show();
            } else {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Your account has not been created.");
                a.show();
            }
            onBackButtonClick();
        }
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
