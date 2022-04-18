package wallet.APP;

import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import wallet.APP.TransactionData;

public class Transaction {

    private TransactionData data = new TransactionData();

    public Transaction(String is, String ir, String type, String curr, float amount, LocalDateTime dt, String com) {
        data.setIbanSender(is);
        data.setIbanReceiver(ir);
        data.setOperType(type);
        data.setCurrency(curr);
        data.setAmount(amount);
        data.setDateTime(dt);
        data.setWeekend(isWeekend(dt));
        data.setComments(com);
    }

    /*
     * @return 1 if the given date is a weekend day
     */
    private int isWeekend(LocalDateTime dateTime){
        int we = 0;
        switch(dateTime.getDayOfWeek()){
            case FRIDAY:
                if (dateTime.getHour() >= 22) { we=1; }
                break;
            case SATURDAY:
                we = 1;
                break;
            case SUNDAY:
                we = 1;
                break;
            default:
                we = 0;
                break;
        }
        return we;
    }

    /*
     * @return the transaction's data as a json 
     */
    public String write_data() {
        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(data);
        }
        catch (Exception e) {
        }
        return json;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
