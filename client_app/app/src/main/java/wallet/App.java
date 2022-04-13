package wallet;

import wallet.API.Api;
import wallet.API.JsonTools;
import wallet.APP.User;
import wallet.APP.Bank;
import wallet.APP.Wallet;
import wallet.APP.Account;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class App extends Application {

    public static Api api = new Api();
    public static ArrayList<Bank> banksList = new ArrayList();   // all supported banks ( given by the api )
    public static User    currentUser;
    public static Wallet  currentWallet;
    public static Account currentAccount;
    public static Stage   stage;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getFileFromResourceAsStream("GUI/fxml/hello-view.fxml"));
        Scene scene = new Scene(root, 320, 240);

        this.stage = stage;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);

        stage.show();
    }

    public static void main(String[] args) {
        // creating a list of banks
        String json;
        try {
            json = api.get_banks();
            ArrayList<String> strings = JsonTools.splitJson(json);
            for (String s : strings) {
                Bank bank = new Bank();
                bank.read_data(s);
                banksList.add(bank);
            }
        } catch (Exception e) {}

        // launch GUI ( see start method )
        launch();
    }

    public static boolean connect(String userID) {
        currentUser = new User();
        try {
            currentUser.read_data(api.get_user(userID));
            if (currentUser.get_userID().equals(userID)) {
                return true;
            }
        }
        catch (Exception e) {
        }
        disconnect();
        return false;
    }

    public static void register(String id, String password) {
             
    }

    public static void disconnect() {
        currentUser = null;
    }

    public static String get_bankBic(String name) {
        for (Bank b : banksList) {
            if (b.get_name().equals(name)) {
                return b.get_bic();
            }
        }
        return "Unknown bank, name: "+name;
    }

    public static String get_bankName(String bic) {
        for (Bank b : banksList) {
            if (b.get_bic().equals(bic)) {
                return b.get_name();
            }
        }
        return "Unknown bank, bic: "+bic;
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    public InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

}
