package wallet.GUI.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.io.File;

import jdk.jshell.execution.StreamingExecutionControl;
import wallet.App;
/**
 * Controller containing the methods used for mainmenu.fxml
 */
public class MainMenuController {
    @FXML
    BorderPane borderpane;
    @FXML
    Label time;
    @FXML
    Label date;
    @FXML
    Button wallets;
    @FXML
    Button account;
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
    @FXML
    private BorderPane borderPane;
    int a;

    @FXML
    private void initialize() throws MalformedURLException { /***
     /**
     * Automatically executed at the launch of the scene (mainmenu.fxml)
     * */

        // set language text :
        wallets.setText(App.currentLanguage.get("myWallets"));
        account.setText(App.currentLanguage.get("myProfile"));
        settings.setText(App.currentLanguage.get("settings"));
        signout.setText(App.currentLanguage.get("signOut"));
        leave.setText(App.currentLanguage.get("leave"));

        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/settings.fxml").toURL());
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
        a = 1;
        sidebutton.setText("<");
        if (App.dark){
            borderPane.setBackground(new Background( new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            borderPane.setBackground(new Background( new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    private void onSettingsButtonClick() throws IOException {
    /***
    * Methode used when the settings button is pressed
    * it changes the scene to settings.fxml
    */
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/settings.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (wallets.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void onSignOutButtonClick() throws IOException {
        /***
         * Methode used when the sign out button is pressed
         * it changes the scene to hello-view.fxml and signs the user out
         */
        App.disconnect();
        App.loged=false;
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/hello-view.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (wallets.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onSideMenuButtonClick() {
        /***
         * Methode used when the side menu button is pressed
         * makes the side menu appear or disappear
         */
        TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(500), sidemenu);
        menuTranslation.setFromX(-200);
        menuTranslation.setToX(0);
        if (a == -1) {
            menuTranslation.setRate(1);
            menuTranslation.play();
            a = 1;
            sidebutton.setText("<");
        } else {
            menuTranslation.setRate(-1);
            menuTranslation.play();

            sidebutton.setText(">");
            a = -1;
        }
    }

    @FXML
    private void onLeaveButtonCLick() {
        /***
         * Methode used when the leave button is pressed
         * closes the app
         */
        Stage stage = (Stage) (leave.getScene().getWindow());
        stage.close();
    }

    @FXML
    private void onWalletButtonClick() throws IOException {
        /***
         * Methode used when the wallet button is pressed
         * it changes the scene to walletgrid.fxml
         */
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/walletgrid.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (wallets.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onAccountButtonClick() throws IOException {
        /***
         * Methode used when the account button is pressed
         * it changes the scene to account.fxml
         */
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/account.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (account.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

}
