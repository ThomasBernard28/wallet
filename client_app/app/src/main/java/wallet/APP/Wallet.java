package wallet.APP;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.ArrayList;

import wallet.API.YamlReader;
import wallet.APP.WalletData;

public class Wallet implements YamlReader {

    private WalletData data;

    @Override
    public void read_data(String filePath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            File yamlFile = new File(filePath);
            data = objectMapper.readValue(yamlFile, WalletData.class);
        }
        catch (Exception e) {
            System.out.println("file not found : " + filePath);
        }
    }

    @Override
    public String toString() {
        return data.toString();
    }


}
