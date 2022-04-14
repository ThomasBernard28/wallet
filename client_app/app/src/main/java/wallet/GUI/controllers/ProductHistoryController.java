package wallet.GUI.controllers;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import wallet.App;
import wallet.APP.History;

public class ProductHistoryController {
    @FXML
    Button back;
    @FXML 
    GridPane grid;

    ArrayList<History> historyList = new ArrayList();

    @FXML
    private void initialize() throws IOException {
        try {
            App.currentUser.set_historyList(App.api.get_history(App.currentAccount.get_iban()));
            historyList = App.currentUser.get_historyList();
        } catch (Exception e) {}

        int row = 0;
        for (History h : historyList) {
            Label l = new Label(h.toString());
            grid.add(l, 0, row++);
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
