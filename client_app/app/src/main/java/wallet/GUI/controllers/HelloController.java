package wallet.GUI.controllers;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import wallet.App;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.File;

import wallet.App;

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
    private Label idLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button settings;

    @FXML
    private BorderPane borderPane;

    @FXML
    public void initialize() {
        // set language
        login.setText(App.currentLanguage.get("login"));
        register.setText(App.currentLanguage.get("register"));
        idLabel.setText(App.currentLanguage.get("id")+" :");
        passwordLabel.setText(App.currentLanguage.get("password")+" :");
        if (App.dark){
            borderPane.setBackground(new Background( new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            borderPane.setBackground(new Background( new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    private void onLoginButtonClick() throws IOException {
        boolean connected;

        connected = App.connect(id.getText());

        if (connected) {
            if (App.currentUser.get_password().equals(password.getText())) {
                FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
                Parent root = fxmlLoader.load();
                App.loged = true;
                Stage stage = (Stage) (login.getScene().getWindow());
                Scene scene = new Scene(root, 320, 320);
                stage.setScene(scene);
                stage.show();
            }
        }

        wrong.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
        visiblePause.setOnFinished(
                event -> wrong.setVisible(false)
        );
        visiblePause.play();
    }

    @FXML
    private void onRegisterButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/register.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (register.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void onSettingsButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/settings.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (register.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

}
