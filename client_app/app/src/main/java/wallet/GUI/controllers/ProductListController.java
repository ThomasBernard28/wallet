package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import wallet.App;
import wallet.API.Api;
import wallet.APP.Account;

public class ProductListController {
    @FXML
    Button back;
    @FXML
    GridPane grid;
    @FXML
    Button plus;
    @FXML
    private BorderPane borderPane;

    private ArrayList<Account> accountsList;

    @FXML
    private void initialize() {
        if (App.dark) {
            borderPane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        // set language
        back.setText(App.currentLanguage.get("back"));

        try {
            App.currentUser.set_accountsList(App.api.get_products(App.currentWallet.get_walletID()));
            accountsList = App.currentUser.get_accountsList();
            int row = 0;
            for (Account a : accountsList) {
                Button button = new Button();
                switch (a.get_type()) {
                    case "CA":
                        button.setText(App.currentLanguage.get("checkingAccount"));
                        break;
                }
                button.setOnAction(event -> {
                    try {
                        switchToProduct(a);
                    } catch (IOException e) {
                    }
                });
                grid.add(button, 0, row++);
            }
        } catch (Exception e) {
        }
    }

    private void switchToProduct(Account a) throws IOException {
        App.currentAccount = a;
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/productmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
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

    @FXML
    private void onProductButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/productmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onPlusButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/addproductmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }
}
