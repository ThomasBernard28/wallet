package wallet.APP;

import java.util.Map;

import wallet.App;

public class AccountData {
   
   private String  userID = App.currentUser.get_userID();
   private int     walletID;
   private float   avgBalance;
   private String  localCurr;
   private int     activity;
   private String  type; 
   private String  bic = App.currentWallet.get_bic();
   private String  iban;

   public String getUserID() {
      return userID;
   }

   public String getBic() {
      return bic;
   }

   public void setIban(String iban) {
      this.iban = iban;
   }

   public String getIban() {
      return iban;
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

   public void setLocalCurr(String localCurr) {
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
             iban       + '\n' +
             avgBalance + '\n' +
             localCurr  + '\n' +
             type       + '\n' +
             bic        + '\n' +
             activity   + '\n';
   }


}
