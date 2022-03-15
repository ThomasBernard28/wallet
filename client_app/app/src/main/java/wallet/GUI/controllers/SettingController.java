package wallet.GUI.controllers;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;


public class SettingController {

    @FXML
    Button back;
    @FXML
    ComboBox language;
    @FXML
    CheckBox sound;
    @FXML
    CheckBox theme;
    @FXML
    Label sike;


    @FXML
    private void initialize() {
        /*ObservableList<String> langues = null;
        langues.add("English");
        langues.add("Français");
        langues.add("Chinois");
        language.setItems(langues);*/

    }

    @FXML
    private void OnThemeCheckBoxChecked() {
        sike.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(
                event -> sike.setVisible(false)
        );
        visiblePause.play();
    }

    @FXML
    private void onBackButtonCLick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }


}


