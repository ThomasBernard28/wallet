package wallet.GUI.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import wallet.APP.User;
import wallet.App;

import java.io.File;
import java.io.IOException;

public class ForgottenPasswordController {

    @FXML
    private Button back;

    @FXML
    private Label fnameL;

    @FXML
    private TextField fnameTF;

    @FXML
    private Label lnameL;

    @FXML
    private TextField lnameTF;

    @FXML
    private Label natidL;

    @FXML
    private TextField natidTF;

    @FXML
    private GridPane passwordL;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Label passwordbisL;

    @FXML
    private Label wrong;

    @FXML
    private PasswordField passwordbisPF;

    @FXML
    private Button reset;


    @FXML
    private void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/hello-view.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onResetButtonClick() throws IOException {

        // if an entry is empty
        if (natidTF.getText().equals("") || fnameTF.getText().equals("") || lnameTF.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(App.currentLanguage.get("registerWarning1"));
            a.show();
        }
        // if the national register numner is not 11 numbers long
        else if (natidTF.getText().length() != 11) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(App.currentLanguage.get("registerWarning2"));
            a.show();
        }
        // everything is ok and the passwords are matching
        //Methode pour changer le mdp
        // the passwords aren't matching
        if (!passwordPF.getText().equals(passwordbisPF)) {
            wrong.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
            visiblePause.setOnFinished(
                    event -> wrong.setVisible(false)
            );
            visiblePause.play();
        }
    }

}


