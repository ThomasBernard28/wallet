package wallet.APP;

import java.util.ArrayList;
import java.io.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;

import wallet.APP.EmployeeData;
import wallet.APP.Client;
import wallet.APP.ClientData;
import wallet.API.JsonReader;

public class Employee implements JsonReader {

    private EmployeeData      data         = new EmployeeData();
    private ArrayList<Client> clientsList = new ArrayList<>();

    @Override
    public void read_data(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(json, EmployeeData.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set_CliensList(String bic) {
         // API CALL 
    }

    public ArrayList get_clientsList() {
        return clientsList;
    }

    public String get_bic() {
        return data.getBic();
    }

    public String get_password() {
        return data.getPsswd();
    }

    public  void set_password(String password) {
        data.setPsswd(password);
    }

    public String get_language() {
        return data.getLanguage();
    }

    /* add a new client to the clientsList. It is not saved to the database (not yet) */
    public void add_client(String filePath) {
    }

    @Override
    public String toString() {
        return data.toString();
    }

 
}
