package wallet;

import javafx.beans.Observable;
import javafx.beans.value.ObservableBooleanValue;
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

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.core.type.TypeReference;  
import com.fasterxml.jackson.databind.*;  

public class App extends Application {

    public static Api api = new Api();
    public static ArrayList<Bank> banksList = new ArrayList();   // all supported banks ( given by the api )
    public static User                currentUser; 
    public static Wallet              currentWallet;             
    public static Account             currentAccount;
    public static Map<String, String> currentLanguage;
    public static Stage               stage;
    public static boolean dark;
    public static boolean loged;


    @Override
    public void start(Stage stage) throws IOException {
        dark= false;
        loged = false;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getFileFromResourceAsStream("GUI/fxml/settings.fxml"));
        Scene scene = new Scene(root, 320, 240);
        this.stage = stage;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        // set default language (saved in options.json)
        File file = new File("build/resources/main/options.json");
        if (!file.exists()) {
            try {
                Files.writeString(Paths.get("build/resources/main/options.json"), "{\"language\":\"EN\"}");
                set_currentLanguage("EN");
            } catch (Exception e) {}
        }
        try {
            Reader reader = Files.newBufferedReader(Paths.get("build/resources/main/options.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode parser = objectMapper.readTree(reader);
            set_currentLanguage(parser.path("language").asText());
            reader.close();
        } catch (Exception e) {}

        // creating the list of banks
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

        // launch GUI (see start method above)
        launch();
    }

    /* This method make an api call to get one user's data and create a currentUser with them.
     * It also set the currentLanguage of the app with the user's prefered language.
     * @param Stirng userID : precise for which user we want to get data 
     * @return true if the user's data are correctly retrived from the api
     */
    public static boolean connect(String userID) {
        currentUser = new User();
        try {
            currentUser.read_data(api.get_user(userID));
            if (currentUser.get_userID().equals(userID)) {
                set_currentLanguage(currentUser.get_language());
                return true;
            }
        }
        catch (Exception e) {
        }
        disconnect();
        return false;
    }

    public static void disconnect() {
        currentUser = null;
    }

    public static void set_currentLanguage(String language) {
        // if there is no logged in user
        if (currentUser != null) {
            currentUser.set_language(language);
            try {
                api.put_language(currentUser.get_userID(), language);
            } catch (Exception e) {}
        }

        // create a Map object (currentLanguage) from a json file
        ObjectMapper mapper = new ObjectMapper(); 
        File json;
        try {
            json = new File("build/resources/main/languages/"+language+".json");
        }
        catch (Exception e) {
            json = new File("build/resources/main/languages/EN.json");
        }
        try {  
            currentLanguage = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
        }
        catch (Exception e) {}

        // save the currently used language in the options json file
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("build/resources/main/options.json"));
            Map<String, String> options = new HashMap<>();
            options.put("language", language);
            writer.write(mapper.writeValueAsString(options));
            writer.close();
        } catch (Exception e) {}
    }

    /*
     * @param name : a bank's name
     * @return the bank's bic linked to the given name
     */
    public static String get_bankBic(String name) {
        for (Bank b : banksList) {
            if (b.get_name().equals(name)) {
                return b.get_bic();
            }
        }
        return "Unknown bank, name: "+name;
    }

    /*
     * @param bic : a bank's bic
     * @return the bank's name linked to the given bic
     */
    public static String get_bankName(String bic) {
        for (Bank b : banksList) {
            if (b.get_bic().equals(bic)) {
                return b.get_name();
            }
        }
        return "Unknown bank, bic: "+bic;
    }

    /*
     * @return the LocalDateTime of the server in a String
     */
    public static String get_apiTime() {
        String json ="";
        try {
            json = api.get_apiTime();
        }
        catch (Exception e) {}
        return json;
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
