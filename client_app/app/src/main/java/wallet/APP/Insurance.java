package wallet.APP;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import wallet.API.JsonReader;

import java.time.LocalDate;

public class Insurance implements JsonReader {

    private InsuranceData data = new InsuranceData();

    public Insurance(){}

    public Insurance(String type){
        data.setType(type);
    }

    @Override
    public void read_data(String json){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            data = mapper.readValue(json, InsuranceData.class);
        }
        catch (Exception e){
            System.out.println("failed to read insurance data");//debug
        }
    }

    public String write_data() {
        return "{\"walletID\":\""+get_walletID()+"\", \"userID\":\""+get_userID()+"\", \"bic\":\""+get_bic()+"\", \"subDate\":\""+get_subDate()+"\", \"balance\":\""+0+"\", \"type\":\""+get_type()+"\"}";
    }

    public String get_bic(){
        return data.getBic();
    }

    public String get_userID(){
        return data.getUserID();
    }

    public int get_walletID(){
        return data.getWalletID();
    }

    public int get_insID(){
        return data.getInsID();
    }

    public String get_type(){
        return data.getType();
    }

    public LocalDate get_subDate(){
        return data.getSubDate();
    }

    public LocalDate get_renewDate(){
        return data.getEndDate();
    }

    @Override
    public String toString(){
        return data.toString();
    }


}
