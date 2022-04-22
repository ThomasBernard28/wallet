package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import wallet.App;
import wallet.APP.CoOwner;

public class CoownerController {
    @FXML
    Button back;
    @FXML
    TextField firstname;
    @FXML
    TextField lastname;
    @FXML
    TextField natregnumb;
    @FXML
    Button add;
    @FXML
    VBox vbox;
    @FXML
    Label firstnameLabel;
    @FXML
    Label lastnameLabel;
    @FXML
    Label natIDLabel;
    @FXML
    Label addCoOwnerLabel;
    @FXML
    private SplitPane splitPane;

    ArrayList<CoOwner> coOwnersList = new ArrayList();

    @FXML
    private void initialize() {
        // set language
        back.setText(App.currentLanguage.get("back"));
        firstnameLabel.setText(App.currentLanguage.get("firstName") + " : ");
        lastnameLabel.setText(App.currentLanguage.get("lastName") + " : ");
        natIDLabel.setText(App.currentLanguage.get("nationalID") + " : ");
        addCoOwnerLabel.setText(App.currentLanguage.get("addCoOwner") + " : ");
        add.setText(App.currentLanguage.get("add"));
        if (App.dark) {
            splitPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            splitPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        try {
            App.currentUser.set_coOwnersList(App.api.get_coOwners(App.currentAccount.get_iban()));
            coOwnersList = App.currentUser.get_coOwnersList();
        } catch (Exception e) {
        }
        for (CoOwner co : coOwnersList) {
            Label l = new Label(co.get_coOwnerID());
            vbox.getChildren().add(l);
        }
    }

    @FXML
    private void onAddButtonClick() throws IOException {
        try {
            App.api.post_coOwner(App.currentWallet.get_walletID(), App.currentAccount.get_iban(), firstname.getText() + lastname.getText() + natregnumb.getText(), App.currentWallet.get_bic(), App.currentUser.get_userID());
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("New co-owner added.");
            a.show();
            initialize();
        } catch (Exception e) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Cannot add this user to the co-owners.");
            a.show();
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
