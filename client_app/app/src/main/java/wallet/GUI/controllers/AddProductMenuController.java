package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
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
/**
 * Controller containing the methods used for addproductmenu.fxml
 */
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
    private Label selectLabel;

    @FXML
    private void initialize() {
        /**
         * Automatically executed at the launch of the scene (addprodutmenu.fxml)
         * */
        // set language
        back.setText(App.currentLanguage.get("back"));
        subscribe.setText(App.currentLanguage.get("subscribe"));
        selectLabel.setText(App.currentLanguage.get("selectProduct"));

        // set comboBox values
        String types[] = {App.currentLanguage.get("checkingAccount")};
        box.setItems(FXCollections.observableArrayList(types));

        //change background theme
        if (App.dark) {
            borderPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    private void onSubscribeButtonClick() throws IOException {
        /***
         * Methode used when the subscribe button is pressed
         * adds a product to the user's wallet
         */
        if (box.getValue() == null) {
            Alert a = new Alert(AlertType.WARNING);
            a.setContentText(App.currentLanguage.get("addProductWarning1"));
            a.show();
        }

        else {
            String type = "";
            String selected = (String) box.getValue();
            if (selected.equals(App.currentLanguage.get("checkingAccount"))) {
                type = "CA";
            }
            boolean requested = App.currentUser.add_account(type);
            if (requested) {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText(App.currentLanguage.get("addProductMessage1"));
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
        /***
         * Methode used when the back button is pressed
         * it changes the scene to productlist.fxml
         */
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/productlist.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

}
