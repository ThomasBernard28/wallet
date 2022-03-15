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
import javafx.scene.Parent;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.io.File;

import wallet.App;


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
    Button signout;
    @FXML
    Button leave;
    @FXML
    Button sidebutton;
    @FXML
    VBox sidemenu;
    int a;


    @FXML
    private void initialize() {
        sidemenu.setStyle("css/mainmenustyle.css");

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
        sidebutton.setText("<");


    }
    @FXML
    private void onSettingsButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/settings.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage= (Stage)(wallets.getScene().getWindow());
        Scene scene = new Scene(root,320,320);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void onSignOutButtonClick() throws IOException {
        App.disconnect();
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/hello-view.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage= (Stage)(wallets.getScene().getWindow());
        Scene scene = new Scene(root,320,320);
        stage.setScene(scene);
        stage.show();
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
            sidebutton.setText("<");
        } else {

            menuTranslation.setRate(-1);
            menuTranslation.play();

            sidebutton.setText(">");
            a=-1;
        }

    }
    @FXML
    private void onLeaveButtonCLick(){
        Stage stage= (Stage)(leave.getScene().getWindow());
        stage.close();
    }
    @FXML
    private void onWalletButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/walletgrid.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (wallets.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();

    }
}
