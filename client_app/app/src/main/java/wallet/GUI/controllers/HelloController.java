package wallet.GUI.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label wrong;
    @FXML
    private TextField id;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Button register;


    @FXML
    protected void onLoginButtonClick() throws IOException {
        if(id.getText().equals("Tiramisu") && password.getText().equals("mdp")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(""));
            Stage stage= (Stage)(login.getScene().getWindow());
            Scene scene = new Scene(fxmlLoader.load(),320,320);
            stage.setScene(scene);
            stage.show();
            }else{
            wrong.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
            visiblePause.setOnFinished(
                    event -> wrong.setVisible(false)
            );
            visiblePause.play();
        }

    }
    @FXML
    protected void onRegisterButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        Stage stage = (Stage) (login.getScene().getWindow());
        Scene scene = new Scene(fxmlLoader.load(), 320, 320);
        stage.setScene(scene);
        stage.show();

    }
}
