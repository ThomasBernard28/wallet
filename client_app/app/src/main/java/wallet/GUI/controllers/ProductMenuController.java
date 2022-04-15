package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import wallet.App;

public class ProductMenuController {
    @FXML
    private Button back;
    @FXML
    private Button coowner;
    @FXML
    private Button delete;
    @FXML
    private Button history;
    @FXML
    private Button transaction;
    @FXML
    private Label balance;
    @FXML
    private Label curr;
    @FXML
    private Label iban;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label ibanLabel;

    @FXML
    private void initialize() {
        // set language
        back.setText(App.currentLanguage.get("back"));
        coowner.setText(App.currentLanguage.get("coOwners"));
        history.setText(App.currentLanguage.get("history"));
        transaction.setText(App.currentLanguage.get("transaction"));
        delete.setText(App.currentLanguage.get("delete"));
        balanceLabel.setText(App.currentLanguage.get("balance")+" : ");
        ibanLabel.setText(App.currentLanguage.get("iban")+" : ");

        // set values
        balance.setText(Float.toString(App.currentAccount.get_avgBalance()));
        curr.setText(App.currentAccount.get_localCurr());
        iban.setText(App.currentAccount.get_iban());
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/productlist.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onDeleteButtonClick() throws IOException {
        App.currentUser.disable_account(App.currentAccount);
        onBackButtonClick();
    }

    @FXML
    private void onCoownerButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/co-owner.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onHistoryButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/producthistory.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onTransactionButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/performtransaction.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }
}
