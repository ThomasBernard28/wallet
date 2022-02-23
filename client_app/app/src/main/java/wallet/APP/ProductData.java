package wallet.APP;

public class ProductData {
    
    private int    activity;
    private int    walletID; 
    private String userID;

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getActivity() {
        return activity;
    }

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

    @Override
    public String toString() {
        return walletID + '\n' +
               userID   + '\n' +
               activity;
    }
        

}
