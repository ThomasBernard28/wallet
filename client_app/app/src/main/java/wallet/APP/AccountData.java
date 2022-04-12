package wallet.APP;

import java.util.Map;

public class AccountData {
   
   private String  userID;
   private int     walletID;
   private float   avgBalance;
   private String  localCurr;
   private int     activity;
   private String  type; 
   private String  bic;

   public void setUser(Map wallet) {
      Map user = (Map) wallet.get("user");
      this.userID = (String) user.get("userID");
   }

   public void setUserID(String userID) {
      this.userID = userID;
   }

   public String getUserID() {
      return userID;
   }

   public void setBic(Map client) {
      Map clientID = (Map) client.get("clientIDEmb");
      this.bic = (String) clientID.get("bic");
   }

   public String getBic() {
      return bic;
   }

   public void setWallet(Map wallet) {
      this.walletID = (int) wallet.get("walletID");
   }

   public void setWalletID(int walletID) {
      this.walletID = walletID;
   }

   public int getWalletID() {
      return walletID;
   }

   public void setAvgbalance(float avgBalance) {
      this.avgBalance = avgBalance;
   }

   public float getAvgbalance() {
      return avgBalance;
   }

   public void setLocalcurr(String localCurr) {
      this.localCurr = localCurr;
   }

   public String getCurrency() {
      return localCurr;
   }

   public void setActivity(int activity) {
      this.activity = activity;
   }

   public int getActivity() {
      return activity;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getType() {
      return type;
   }

   @Override
   public String toString() {
      return userID     + '\n' +
             walletID   + '\n' +
             avgBalance + '\n' +
             localCurr  + '\n' +
             type       + '\n' +
             bic        + '\n' +
             activity   + '\n';
   }


}
