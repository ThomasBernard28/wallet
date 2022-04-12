package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    Button product;
    @FXML
    Button plus;

    private ArrayList<Account> accountsList;

    @FXML
    private void initialize() {
        try {
            App.currentUser.set_accountsList(App.api.get_products(App.currentWallet.get_walletID()));
            accountsList = App.currentUser.get_accountsList();
            for (Account a : accountsList) {
                System.out.println(a); // debug
            }

        } catch (Exception e) {}
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
    private void onPlusButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/addproductmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }
}
