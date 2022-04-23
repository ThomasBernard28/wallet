package wallet.GUI.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import wallet.App;
import wallet.APP.AccountRequest;

public class RequestMenuController {

   @FXML
   private Button back;
   @FXML 
   private VBox list;

   private ArrayList<AccountRequest> requestsList = new ArrayList();

   @FXML
   private void initialize() {
      // set values
      try {
         App.currentEmployee.set_requestsList(App.api.get_accountRequests(App.currentEmployee.get_bic()));
         requestsList = App.currentEmployee.get_requestsList();
      } catch (Exception e) {}

      for (AccountRequest ar : requestsList) {
      
         HBox hbox = new HBox(5);
         hbox.getChildren().add(new Label("Request id : " + Integer.toString(ar.get_accRequestID())));

         // accept request button
         Button accept = new Button("Accept");
          accept.setOnAction(event -> {
              try {
                  acceptRequest(ar);
              } catch (IOException e) {}
          });
         hbox.getChildren().add(accept);

         // refuse request button
         Button refuse = new Button("Refuse");
         refuse.setOnAction(event -> {
              try {
                  refuseRequest(ar);
              } catch (IOException e) {}
          });
         hbox.getChildren().add(refuse);

         // add the hbox to the list of requests
         list.getChildren().add(hbox);
      }
   }

   private void acceptRequest(AccountRequest ar) throws IOException {
      System.out.println("accept");
   }

   private void refuseRequest(AccountRequest ar) throws IOException {
      System.out.println("refuse");
   }

   @FXML
   private void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) (back.getScene().getWindow());
        Scene scene = new Scene(root, 320, 320);
        stage.setScene(scene);
        stage.show();
   }

}
