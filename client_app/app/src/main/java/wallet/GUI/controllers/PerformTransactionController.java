package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private void onConfirmButtonClick() throws IOException {
        boolean cancel = false;
        if (Float.valueOf(amount.getText()).floatValue() > App.currentAccount.get_avgBalance()) {
            cancel = true;
        }
        // to do : date time control 
        if (!cancel) {
            Transaction transaction = new Transaction(App.currentAccount.get_iban(),
                                                      iban.getText(),
                                                      "Money transfer",
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
        Stage stage= (Stage)(back.getScene().getWindow());
        Scene scene = new Scene(root,320,320);
        stage.setScene(scene);
        stage.show();
    }
}
