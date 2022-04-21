package wallet;

import java.util.ArrayList;

import wallet.APP.Employee;
import wallet.API.Api;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.File;
import java.io.*;
import java.nio.file.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.core.type.TypeReference;  
import com.fasterxml.jackson.databind.*;  

public class App extends Application {

    public static Api      api = new Api();
    public static Employee            currentEmployee;
    public static Map<String, String> currentLanguage;
    public static Stage               stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = fxmlLoader.load(getFileFromResourceAsStream("GUI/fxml/loginscreen.fxml"));
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

        // launch GUI (see start method above)
        launch();
    }

    public static boolean connect(String bic) {
        currentEmployee = new Employee();
        try {
            currentEmployee.read_data(api.get_employee(bic));
            if (currentEmployee.get_bic().equals(bic)) {
                return true;
            }
        }
        catch (Exception e) {}
        disconnect();
        return false;
    }

    public static void disconnect() {
        currentEmployee = null;
    }

    public static void set_currentLanguage(String language) {
        // if there is no logged in employee 
        if (currentEmployee != null) {
            currentEmployee.set_language(language);
            try {
                api.put_language(currentEmployee.get_bic(), language);
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

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

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
