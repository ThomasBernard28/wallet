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
    private void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/productmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage= (Stage)(back.getScene().getWindow());
        Scene scene = new Scene(root,320,320);
        stage.setScene(scene);
        stage.show();
    }
}
