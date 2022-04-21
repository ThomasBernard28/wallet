package wallet.GUI.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.File;

import wallet.App;
import wallet.APP.User;

public class RegisterController {
    @FXML
    private Label wrong;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField nationalId;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Button back;
    @FXML
    private Button register;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label nationalIdLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private BorderPane borderPane;


    @FXML 
    private void initialize() {
        // set language
        wrong.setText(App.currentLanguage.get("pwMissmatch"));
        back.setText(App.currentLanguage.get("back"));
        register.setText(App.currentLanguage.get("register"));
        firstNameLabel.setText(App.currentLanguage.get("firstName")+" :");
        lastNameLabel.setText(App.currentLanguage.get("lastName")+" :");
        nationalIdLabel.setText(App.currentLanguage.get("nationalID")+" :");
        passwordLabel.setText(App.currentLanguage.get("password")+" :");
        confirmPasswordLabel.setText(App.currentLanguage.get("confirmPassword")+" :");
        if (App.dark){
            borderPane.setBackground(new Background( new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            borderPane.setBackground(new Background( new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    private void onBackButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/hello-view.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onRegisterClicked() {
        // if an entry is empty
        if (nationalId.getText().equals("") || firstName.getText().equals("") || lastName.getText().equals("") || password.getText().equals("")) {
            Alert a = new Alert(AlertType.WARNING);
            a.setContentText(App.currentLanguage.get("registerWarning1"));
            a.show();
        }
        // if the national register numner is not 11 numbers long
        else if (nationalId.getText().length() != 11) {
            Alert a = new Alert(AlertType.WARNING);
            a.setContentText(App.currentLanguage.get("registerWarning2"));
            a.show();
        }
        // everything is ok and the passwords are matching
        else if (password.getText().equals(confirmPassword.getText())) {
            App.currentUser = new User(firstName.getText().toLowerCase(), lastName.getText().toLowerCase(), nationalId.getText(), password.getText());
            try {
                App.api.post_user(App.currentUser.write_data());
                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText(App.currentLanguage.get("registerMessage")+App.currentUser.get_userID());
                a.show();
                onBackButtonClicked();
            }
            catch (Exception e) {
            }
        // the passwords aren't matching
        } else {
            wrong.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
            visiblePause.setOnFinished(
                    event -> wrong.setVisible(false)
            );
            visiblePause.play();
        }
    }

}
