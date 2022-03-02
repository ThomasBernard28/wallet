package wallet.APP;

import java.util.ArrayList;
import java.io.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.util.ArrayList;

import wallet.APP.WalletData;
import wallet.API.JsonReader;

public class Wallet implements JsonReader {

    private WalletData data         = new WalletData();
    private ArrayList  ProductsList = new ArrayList();

    @Override
    public void read_data(String filePath) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(Paths.get(filePath).toFile(), WalletData.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return data.toString();
    }


}
