package wallet.APP;

import java.time.LocalDateTime;

public class TransactionData {
    
    private String        ibanSender;
    private String        ibanReceiver;
    private String        operType;
    private String        currency;
    private float         amount;
    private LocalDateTime dateTime;
    private int           weekend;
    private int           status = 0;
    private String        comments;

    public void setIbanSender(String ibanSender) {
        this.ibanSender = ibanSender;
    }

    public String getIbanSender() {
        return ibanSender;
    }

    public void setIbanReceiver(String ibanReceiver) {
        this.ibanReceiver = ibanReceiver;
    }

    public String getIbanReceiver() {
        return ibanReceiver;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String  getOperType() {
        return operType;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime.toString();
    }

    public void setWeekend(int weekend) {
        this.weekend = weekend;
    }

    public int getWeekend() {
        return weekend;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public String toString() {
        return ibanSender   + '\n' +
               ibanReceiver + '\n' +
               operType     + '\n' +
               currency     + '\n' +
               amount       + '\n' +
               dateTime     + '\n' +
               weekend      + '\n' +
               status       + '\n' +
               comments     + '\n';
    }


}
