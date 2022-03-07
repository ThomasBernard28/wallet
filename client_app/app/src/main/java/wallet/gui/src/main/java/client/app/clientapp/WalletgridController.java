package client.app.clientapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
        Stage stage= (Stage)(back.getScene().getWindow());
        Scene scene = new Scene(fxmlLoader.load(),320,320);
        stage.setScene(scene);
        stage.show();
    }
}








