package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import wallet.App;
import wallet.APP.Transaction;

public class PerformTransactionController {
    @FXML
    private TextField amount;
    @FXML
    private Button back;
    @FXML
    private TextArea communication;
    @FXML
    private Button confirm;
    @FXML
    private TextField iban;
    @FXML
    private TextField receiverName;
    @FXML
    private Label receiverLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ibanLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label communicationLabel;
    @FXML
    private BorderPane borderPane;

    @FXML
    private void initialize() {
        // set language
        back.setText(App.currentLanguage.get("back"));
        confirm.setText(App.currentLanguage.get("confirm"));
        receiverLabel.setText(App.currentLanguage.get("receiver") + " : ");
        nameLabel.setText(App.currentLanguage.get("firstName") + ", " + App.currentLanguage.get("lastName") + " : ");
        amountLabel.setText(App.currentLanguage.get("amount") + " : ");
        communicationLabel.setText(App.currentLanguage.get("communication") + " : ");
        if (App.dark) {
            borderPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

    @FXML
    private void onConfirmButtonClick() throws IOException {
        boolean cancel = false;
        if (iban.getText().equals("")) {
            cancel = true;
            Alert a = new Alert(AlertType.WARNING);
            a.setContentText(App.currentLanguage.get("transactionWarning1"));
            a.show();
        } else if (amount.getText().equals("") || Float.valueOf(amount.getText()).floatValue() > App.currentAccount.get_avgBalance()) {
            cancel = true;
            Alert a = new Alert(AlertType.WARNING);
            a.setContentText(App.currentLanguage.get("transactionWarning2"));
            a.show();
        }
        // to do : date time control 
        if (!cancel) {
            Transaction transaction = new Transaction(App.currentAccount.get_iban(),
                    iban.getText(),
                    "SEPA",
                    App.currentAccount.get_localCurr(),
                    Float.valueOf(amount.getText()).floatValue(),
                    LocalDateTime.now().minusMinutes(122),
                    communication.getText());
            App.currentUser.perform_transaction(transaction);
            onBackButtonClick();
        }
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/productmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

}
