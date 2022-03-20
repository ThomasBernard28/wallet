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

import java.io.*;


public class SettingController {
    @FXML
    BorderPane borderPane;
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

        borderPane.backgroundProperty().bind(Bindings.when(theme.selectedProperty())
                .then(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)))
                .otherwise(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY))));

    }

    @FXML
    private void OnThemeCheckBoxChecked() {
        sike.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(1.5));
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
    public boolean getThemeValue(){
        return theme.isSelected();
    }
    public CheckBox getTheme(){
        return theme;
    }


}


