package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import wallet.APP.Insurance;
import wallet.App;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InsuranceMenuController {
    @FXML
    Button back;
    @FXML
    GridPane grid;
    @FXML
    Button addNewInsurance;
    @FXML
    Button insurance;

    private ArrayList<Insurance> insuranceList;

    @FXML
    private void initialize(){
        try{
            App.currentUser.set_insurances_List(App.api.get_insurances(App.currentWallet.get_walletID()));
            insuranceList = App.currentUser.get_insuranceList();

            int row = 1;

            Insurance first = insuranceList.get(0);
            insurance.setText("Pension Saving \n" + "Subcribe date : " +
                    first.get_subDate() + "\n" + "Renew date : " + first.get_renewDate());
            insuranceList.remove(0);

            for (Insurance i : insuranceList){
                Button button = new Button();
                switch(i.get_type()){
                    case "PEN30":
                        button.setText("Pension Saving \n" + "Subcribe date : " +
                                i.get_subDate() + "\n" + "Renew date : " + i.get_renewDate());
                        break;
                    case "PEN25":
                        button.setText("Pension Saving \n" + "Subcribe date : " +
                                i.get_subDate() + "\n" + "Renew date : " + i.get_renewDate());
                        break;
                }
            }
        }catch (Exception e){

        }
    }
    /*
    private void switchToInsurance(Insurance i) throws IOException{
        if (i.get_type().equals("PEN30") || i.get_type().equals("PEN25")){
            App.currentInsurance = i;
            FXMLLoader fxmlLoader = new FXMLLoader()
        }
    }

     */
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
