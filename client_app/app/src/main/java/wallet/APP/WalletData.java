package wallet.APP;

import java.util.Map;

public class WalletData {

    private int     walletID;
    private String  userID;
    private String  bic;
    private String  openingDate;
    private boolean activity;

    public WalletData() {}

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setUser(Map user) {
        this.userID = (String) user.get("userID");
    }

    public String getUserID() {
        return userID;
    }
    
    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBic() {
        return bic;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setActivity(int activity) {
        this.activity = false;
        if (activity == 1) {
            this.activity = true;
        }
    }

    public boolean getActivity() {
        return activity;
    }

    @Override
    public String toString() {
        return ("walletID    : " + walletID    + "\n" +
                "userID      : " + userID      + "\n" +
                "bic         : " + bic         + "\n" +
                "openingDate : " + openingDate + "\n" +
                "activity    : " + activity    + "\n");
    }


}
