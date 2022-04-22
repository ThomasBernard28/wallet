package wallet.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    Button newInsurance;
    @FXML
    Button firstInsurance;
    @FXML
    Label insurance1;
    @FXML
    Label insurance2;
    @FXML
    Label insurance3;

    private ArrayList<Insurance> insuranceList;

    @FXML
    private void initialize(){
        try{
            App.currentUser.set_insurancesList(App.api.get_insurances(App.currentWallet.get_walletID()));
            insuranceList = App.currentUser.get_insuranceList();

            int row = 0;

            for (Insurance i : insuranceList){
                if(row == 0){
                    if(i.get_type().equals("PEN30") || i.get_type().equals("PEN25")){
                        firstInsurance.setText("Pension Saving");
                        addInsuranceLabel(i, row);
                    }
                }
                else{
                    Button thirdInsurance = new Button();
                    thirdInsurance.setPrefHeight(61);
                    thirdInsurance.setPrefWidth(150);
                    thirdInsurance.setAlignment(Pos.CENTER);
                    thirdInsurance.setTranslateX(43);
                    thirdInsurance.setText(i.get_type());

                    addInsuranceLabel(i, row);
                }
                row ++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void addInsuranceLabel(Insurance insurance, int row){
        switch (row){
            case 0 :
                insurance1.setText("Pension Saving \n" + "Subscription date : " +
                        insurance.get_subDate() + "\n" + "Renew date : " + insurance.get_renewDate());
                break;
            case 1 :
                insurance2.setText("Pension Saving \n" + "Subscription date : " +
                        insurance.get_subDate() + "\n" + "Renew date : " + insurance.get_renewDate());
                break;
            case 2 :
                insurance3.setText("Pension Saving \n" + "Subscription date : " +
                        insurance.get_subDate() + "\n" + "Renew date : " + insurance.get_renewDate());
                break;
            default:
                throw new IllegalStateException("The row number is incorrect");
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

    @FXML
    private void onNewInsuranceButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/newInsuranceScene.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
    }
}
