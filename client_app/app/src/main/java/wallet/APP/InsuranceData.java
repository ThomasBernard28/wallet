package wallet.APP;

import wallet.App;

import java.time.LocalDate;
import java.util.Map;

public class InsuranceData {

    private String    userID = App.currentUser.get_userID();
    private int       insID;
    private int       walletID;
    private String    bic = App.currentWallet.get_bic();
    private String    type;
    private LocalDate subDate;
    private LocalDate renewDate;
    private int       activity;

    public String getUserID() {
        return userID;
    }

    public int getInsID() {
        return insID;
    }

    public void setInsID(int insID){
        this.insID = insID;
    }

    public void setWallet(Map wallet){
        this.walletID = (int) wallet.get("walletID");
    }

    public void setWalletID(int walletID){
        this.walletID = walletID;
    }

    public int getWalletID() {
        return walletID;
    }

    public String getBic() {
        return bic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){this.type = type;}

    public LocalDate getSubDate() {
        return subDate;
    }

    public void setSubDate(LocalDate subDate) {
        this.subDate = subDate;
    }

    public LocalDate getRenewDate() {
        return renewDate;
    }

    public void setRenewDate(LocalDate renewDate) {
        this.renewDate = renewDate;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity){
        this.activity = activity;
    }

    @Override
    public String toString(){
        return  userID     + '\n' +
                walletID   + '\n' +
                insID      + '\n' +
                subDate    + '\n' +
                renewDate  + '\n' +
                type       + '\n' +
                bic        + '\n' +
                activity   + '\n';
    }
}
