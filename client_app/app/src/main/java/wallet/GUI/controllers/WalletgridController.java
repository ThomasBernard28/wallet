package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import wallet.App;
import wallet.APP.Wallet;

public class WalletgridController {
    @FXML
    GridPane grid;
    @FXML
    Button back;
    @FXML
    Button plus;
    @FXML
    Button wallet;
    @FXML
    private StackPane stackPane;

    int a = 1;
    int ligne = 0;
    int colonne = 0;
    int pq = 0;

    private ArrayList<Wallet> walletsList;
    Boolean bool;

    @FXML
    private void initialize() {
        // set language
        back.setText(App.currentLanguage.get("back"));
        if (App.dark){
            stackPane.setBackground(new Background( new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            stackPane.setBackground(new Background( new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        // set values
        try {
            App.currentUser.set_walletsList(App.api.get_wallets(App.currentUser.get_userID()));
            walletsList = App.currentUser.get_walletsList();
            for (Wallet w : walletsList) {
                addWalletButton(App.get_bankName(w.get_bic()), w);
            }
        } catch (Exception e) {
        }
    }

    private void addWalletButton(String bic, Wallet wallet) {
        Button temp = plus;
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
            Button button = new Button(bic);
            a++;
            button.setOnMouseClicked(event -> {
                try {
                    switchToProduct(wallet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            grid.add(button, past_column, past_row);
            button.setMaxWidth(temp.getMaxWidth());
            button.setMaxHeight(temp.getMaxHeight());
            bool = true;
        }
        if (ligne == 2 && bool) {
            bool = false;
            grid.getChildren().remove(temp);
        }
    }

    @FXML
    private void onPlusButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/addwalletmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
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

    private void switchToProduct(Wallet wallet) throws IOException {
        App.currentWallet = wallet;
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/productlist.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }

}
