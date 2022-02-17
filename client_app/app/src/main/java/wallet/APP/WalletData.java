package wallet.APP;

public class WalletData {

    private int     walletID;
    private String  userID;
    private String  bic;
    private int     openingDate;
    private boolean activity;

    public WalletData() {}

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public void setOpeningDate(int openingDate) {
        this.openingDate = openingDate;
    }

    public int getOpeningDate() {
        return openingDate;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
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
