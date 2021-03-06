package wallet.GUI.controllers;

import javafx.animation.PauseTransition;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.collections.*;

import java.io.*;

import wallet.App;

public class SettingController {
    @FXML
    BorderPane borderPane;
    @FXML
    Button back;
    @FXML
    ComboBox language;

    @FXML
    CheckBox theme;
    @FXML
    Label languageLabel;
    @FXML
    Label darkThemeLabel;

    @FXML
    private void initialize() {
        // set language
        back.setText(App.currentLanguage.get("back"));
        languageLabel.setText(App.currentLanguage.get("language") + " :");
        darkThemeLabel.setText(App.currentLanguage.get("darkTheme") + " : ");
        language.setPromptText(App.currentLanguage.get("selectLanguage"));

        String[] languages = {"EN", "FR"}; // to do : check for languages json 
        language.setItems(FXCollections.observableArrayList(languages));
        if (App.dark) {
            borderPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    private void OnThemeCheckBoxChecked() {
        if (App.dark) {
            App.dark = false;
        } else {
            App.dark = true;
        }
        initialize();
    }

    @FXML
    private void onLanguageClick() {
        App.set_currentLanguage((String) language.getValue());
        initialize();
    }

    @FXML
    private void onBackButtonCLick() throws IOException {
        if (App.loged) {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) (back.getScene().getWindow());
            Scene scene = new Scene(root, 320, 320);
            stage.setScene(scene);
            stage.show();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/hello-view.fxml").toURL());
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) (back.getScene().getWindow());
            Scene scene = new Scene(root, 320, 320);
            stage.setScene(scene);
            stage.show();
        }
    }


}
