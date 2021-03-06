package wallet.GUI.controllers;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import wallet.App;
import wallet.APP.History;
/**
 * Controller containing the methods used for producthistory.fxml
 */
public class ProductHistoryController {
    @FXML
    Button back;
    @FXML
    GridPane grid;
    @FXML
    private StackPane stackPane;
    ArrayList<History> historyList = new ArrayList();

    @FXML
    private void initialize() throws IOException {
        /**
         * Automatically executed at the launch of the scene (producthistory.fxml)
         * */
        // set language
        back.setText(App.currentLanguage.get("back"));

        // set values
        try {
            App.currentUser.set_historyList(App.api.get_history(App.currentAccount.get_iban()));
            historyList = App.currentUser.get_historyList();
        } catch (Exception e) {
        }

        int row = 0;
        for (History h : historyList) {
            String direction = h.get_ibanSender() + " ---> " + h.get_ibanReceiver() + '\n';
            if (h.get_ibanReceiver().equals(App.currentAccount.get_iban())) {
                direction = h.get_ibanReceiver() + " <--- " + h.get_ibanSender() + '\n';
            }
            String amount = App.currentLanguage.get("amount") + " : " + h.get_amount() + " " + App.currentAccount.get_localCurr() + '\n';
            String newBal = App.currentLanguage.get("balance") + " : " + h.get_prevBalance() + " ---> " + h.get_nextBalance() + '\n';
            String date = App.currentLanguage.get("date") + " : " + h.get_dateTime().substring(0, 10) + '\n';
            String com = App.currentLanguage.get("communication") + " : \n" + h.get_comments();
            Label l = new Label(direction + amount + newBal + date + com);
            l.setStyle("-fx-border-color: grey;");
            grid.add(l, 0, row++);
            if (App.dark) {
                stackPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
            } else {
                stackPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        /***
         * Methode used when the back button is pressed
         * it changes the scene to productmenu.fxml
         */
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/productmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }
}
