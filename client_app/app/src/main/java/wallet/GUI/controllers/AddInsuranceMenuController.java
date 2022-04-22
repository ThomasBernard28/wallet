package wallet.GUI.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class AddInsuranceMenuController {

    @FXML
    Button back;

    @FXML
    ComboBox box;

    @FXML
    Button sub;

    @FXML
    private void initialize(){
        String types[] = {"Pension saving"};
        box.setItems(FXCollections.observableArrayList(types));
    }

    @FXML
    private void onBackButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(new File("resources/GUI/fxml/insurancemenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }
}
