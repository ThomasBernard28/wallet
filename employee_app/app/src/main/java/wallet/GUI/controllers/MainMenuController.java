package wallet.GUI.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;



public class MainMenuController {
    @FXML
    Label time;
    @FXML
    Label date;
    @FXML
    Button wallets;
    @FXML
    Button accounts;
    @FXML
    Button settings;
    @FXML
    Button singout;
    @FXML
    Button leave;
    @FXML
    Button sidebutton;
    @FXML
    VBox sidemenu;
    int a;


    @FXML
    private void initialize() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            time.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        Date datum = new Date();
        String s;
        Format formatter = new SimpleDateFormat("E, dd MMM yyyy");
        s = formatter.format(datum);
        date.setText(s);
        a=1;


    }
    @FXML
    private void onSettingsButtonClick() throws IOException {
    }

    @FXML
    private void onSideMenuButtonClick() {
        TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(500), sidemenu);
        menuTranslation.setFromX(-200);
        menuTranslation.setToX(0);
        if (a == -1) {
            menuTranslation.setRate(1);
            menuTranslation.play();
            a=1;
        } else {

            menuTranslation.setRate(-1);
            menuTranslation.play();
            a=-1;
        }
    }

    @FXML
    private void onLeaveButtonCLick(){
        Stage stage= (Stage)(leave.getScene().getWindow());
        stage.close();
    }

}
