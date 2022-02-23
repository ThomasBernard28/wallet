package wallet.APP;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import wallet.APP.Product;
import wallet.API.YamlReader;

public class CheckingAccount extends Product implements YamlReader {

   private CheckingAccountData data = new CheckingAccountData();

   @Override
   public void read_data(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            File yamlFile = new File(filePath);
            data = objectMapper.readValue(yamlFile, CheckingAccountData.class);
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
