package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
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
    private Button back;
    @FXML
    private Button add;
    @FXML
    private ComboBox box;
    @FXML
    private Label selectInstitution;
    @FXML
    private BorderPane borderPane;

    @FXML
    private void initialize() {
        // set language
        back.setText(App.currentLanguage.get("back"));
        add.setText(App.currentLanguage.get("addWallet"));
        selectInstitution.setText(App.currentLanguage.get("selectInstitution") + " : ");

        // set comboBox values (banks in which the user has no wallet yet)
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
        if (App.dark) {
            borderPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    @FXML
    private void onAddButtonClick() throws IOException {
        App.currentUser.add_wallet(App.get_bankBic((String) box.getValue()));
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
