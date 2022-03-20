package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import wallet.App;

public class AccountController {

    @FXML
    private Label age;

    @FXML
    private Button back;

    @FXML
    private Label date;

    @FXML
    private Label name;

    @FXML
    private Label number;

    @FXML
    private Button password;


    @FXML
    private void initialize() {
        name.setText(App.currentUser.get_firstName().concat(App.currentUser.get_lastName())); 
        number.setText(Integer.toString(App.currentUser.get_natID()));
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
