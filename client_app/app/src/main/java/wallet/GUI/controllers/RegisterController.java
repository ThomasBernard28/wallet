package wallet.GUI.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class RegisterController {
    @FXML
    private Label wrong;
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
    private void onBackButtonCLicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(fxmlLoader.load(), 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onRegisterClicked() {
        if (password.getText().equals(confirmPassword.getText())) {
            HelloApplication.dic.put(nationalId.getText(), password.getText());
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
