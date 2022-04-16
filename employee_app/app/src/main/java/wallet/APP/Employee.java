package wallet.APP;

import java.util.ArrayList;
import java.io.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;

import wallet.APP.EmployeeData;
import wallet.APP.Client;
import wallet.APP.AccountRequest;
import wallet.API.JsonReader;
import wallet.API.JsonTools;

public class Employee implements JsonReader {

    private EmployeeData              data         = new EmployeeData();

    private ArrayList<Client>         clientsList  = new ArrayList();
    private ArrayList<AccountRequest> requestsList = new ArrayList();

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

    public void set_clientsList(String json) {
        try {
            clientsList.clear();
            ArrayList<String> jsonList = JsonTools.splitJson(json);
            for (String jl : jsonList) {
                Client client = new Client();
                try {
                    client.read_data(jl.substring(15, jl.length()-1));
                    clientsList.add(client);
                }
                catch (Exception e) {}
            }
        }
        catch (Exception e) {}
    }

    public ArrayList get_clientsList() {
        return clientsList;
    }

    public void set_requestsList(String json) {
        try {
            requestsList.clear();
            ArrayList<String> jsonList = JsonTools.splitJson(json);
            for (String jl : jsonList) {
                AccountRequest request = new AccountRequest();
                try {
                    request.read_data(jl);
                    requestsList.add(request);
                }
                catch (Exception e) {}
            }
        }
        catch (Exception e) {}
    }

    public ArrayList get_requestsList() {
        return requestsList;
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
