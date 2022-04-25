package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import wallet.App;

/**
 * R
 */
public class AccountController {
    @FXML
    private Button back;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label number;
    @FXML
    private Button password;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label natIDLabel;

    @FXML
    private BorderPane borderPane;

    @FXML
    private void initialize() {
        // set language
        firstNameLabel.setText(App.currentLanguage.get("firstName") + " : ");
        lastNameLabel.setText(App.currentLanguage.get("lastName") + " : ");
        natIDLabel.setText(App.currentLanguage.get("nationalID") + " : ");
        back.setText(App.currentLanguage.get("back"));
        password.setText(App.currentLanguage.get("changePassword"));

        // set values
        firstName.setText(App.currentUser.get_firstName());
        lastName.setText(App.currentUser.get_lastName());
        number.setText(App.currentUser.get_natID());

        //change background theme
        if (App.dark) {
            borderPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onPasswordButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/changepassword.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (password.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

}
