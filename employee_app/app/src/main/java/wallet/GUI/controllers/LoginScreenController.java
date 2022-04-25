package wallet.GUI.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import wallet.App;

import java.io.IOException;
import java.io.File;

import wallet.App;

public class LoginScreenController {
   @FXML
   private Label wrong;
   @FXML
   private TextField id;
   @FXML
   private PasswordField password;
   @FXML
   private Button login;


   @FXML 
   protected void onLoginButtonClick() throws IOException {
      boolean connected;

      connected = App.connect(id.getText());

      if (connected) {
         if (App.currentEmployee.get_password().equals(password.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("build/resources/main/GUI/fxml/mainmenu.fxml").toURL());
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) (login.getScene().getWindow());
            Scene scene = new Scene(root, 320, 320);
            stage.setScene(scene);
            stage.show();
         }
      }
      wrong.setVisible(true);
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
      visiblePause.setOnFinished(
            event -> wrong.setVisible(false)
            );
      visiblePause.play();
   }

}
