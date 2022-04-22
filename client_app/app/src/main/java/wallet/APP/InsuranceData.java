package wallet.APP;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InsuranceData {

    private String    userID;
    private int       insID;
    private int       walletID;
    private String    bic;
    private String    type;
    private String subDate;
    private String endDate;
    private int       activity;

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getUserID() {
        return userID;
    }

    public void setInsID(int insID){
        this.insID = insID;
    }

    public int getInsID() {
        return insID;
    }

    public void setWalletID(int walletID){
        this.walletID = walletID;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBic() {
        return bic;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setSubDate(String subDate) {
        this.subDate = subDate;
    }

    public String getSubDate() {
        return subDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setActivity(int activity){
        this.activity = activity;
    }

    public int getActivity() {
        return activity;
    }

    @Override
    public String toString(){
        return  userID     + '\n' +
                walletID   + '\n' +
                insID      + '\n' +
                subDate    + '\n' +
                endDate    + '\n' +
                type       + '\n' +
                bic        + '\n' +
                activity   + '\n';
    }
}