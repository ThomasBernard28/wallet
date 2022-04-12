package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.collections.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import wallet.App;
import wallet.APP.Bank;
import wallet.APP.Wallet;

public class AddWalletMenuController {
    @FXML
    Button back;
    @FXML
    Button add;
    @FXML
    ComboBox box;

    @FXML
    private void initialize() {
        ArrayList<String> names = new ArrayList();
        for (Bank b : App.banksList) {
            boolean subscribed = false;
            ArrayList<Wallet> walletsList = App.currentUser.get_walletsList();
            for (Wallet w : walletsList) {
                if (w.get_bic().equals(b.get_bic())) {
                    subscribed = true;
                }
            }
            if (!subscribed) {
                names.add(b.get_name());
            }
        }
        box.setItems(FXCollections.observableArrayList(names));
    }

    @FXML
    private void onAddButtonClick() throws IOException {
        // App.currentUser.add_wallet("GKCCBEBB");
        onBackButtonClick();
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/walletgrid.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }
}
