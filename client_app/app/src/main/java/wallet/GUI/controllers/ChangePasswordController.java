package wallet.GUI.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

import wallet.App;

public class ChangePasswordController {


    @FXML
    private Button back;

    @FXML
    private Button changepassword;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private Label oldPasswordLabel;
    @FXML
    private Label newPasswordLabel;
    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private Label match;

    @FXML
    private void initialize() {
        // set language
        back.setText(App.currentLanguage.get("back"));
        changepassword.setText(App.currentLanguage.get("changePassword"));
        match.setText(App.currentLanguage.get("pwMissmatch"));
        oldPasswordLabel.setText(App.currentLanguage.get("oldPassword")+" :");
        newPasswordLabel.setText(App.currentLanguage.get("newPassword")+" :");
        confirmPasswordLabel.setText(App.currentLanguage.get("confirmPassword")+" :");
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/account.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onChangePasswordButtonClick() throws Exception {
        if (newPassword.getText().equals(confirmPassword.getText()) && oldPassword.getText().equals(App.currentUser.get_password())) {
            App.currentUser.change_password(newPassword.getText()); 
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText(App.currentLanguage.get("changePasswordMessage"));
            a.show();
            onBackButtonClick();
        } else {
            match.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
            visiblePause.setOnFinished(
                    event -> match.setVisible(false)
            );
            visiblePause.play();
        }
    }

}

