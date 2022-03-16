package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import wallet.App;

public class WalletgridController {
    @FXML
    GridPane grid;
    @FXML
    Button back;
    @FXML
    Button plus;
    @FXML
    Button wallet;
    int a = 2;
    int ligne = 0;
    int colonne = 1;
    int pq = 0;
    Boolean bool;


    @FXML
    private void initialize() {
        try {
            App.currentUser.set_walletsList(App.api.get_wallets(App.currentUser.get_userID()));
        }
        catch (Exception e) {
        }
    }
        
    @FXML
    private void onPlusButtonClick() {
        Button temp = plus;
        System.out.println(ligne);

        if (ligne <= 1) {
            Integer past_row = GridPane.getRowIndex(plus);
            Integer past_column = GridPane.getColumnIndex(plus);
            if (past_column == null)
                past_column = 0;
            if (past_row == null)
                past_row = 0;
            colonne++;
            if (colonne == 2) {
                ligne++;
                colonne = 0;
            }
            grid.getChildren().remove(plus);
            grid.add(temp, colonne, ligne);
            Button button = new Button("Wallet " + a++);
            grid.add(button, past_column, past_row);
            button.setMaxWidth(wallet.getMaxWidth());
            button.setMaxHeight(wallet.getMaxHeight());
            System.out.println(pq++);
            bool =true;
        }
        if (ligne == 2 && bool) {
            bool=false;
            grid.getChildren().remove(temp);
        }

    }
    @FXML
    private void onBackButtonCLick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }
}








