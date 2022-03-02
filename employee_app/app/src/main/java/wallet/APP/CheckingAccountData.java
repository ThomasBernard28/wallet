package wallet.APP;

public class CheckingAccountData {
   
   private String  userID;
   private int     walletID;
   private float   balance;
   private String  currency;
   private boolean activity;

   public void setUserID(String userID) {
      this.userID = userID;
   }

   public String getUserID() {
      return userID;
   }

   public void setWalletID(int walletID) {
      this.walletID = walletID;
   }

   public int getWalletID() {
      return walletID;
   }

   public void setBalance(float balance) {
      this.balance = balance;
   }

   public float getBlance() {
      return balance;
   }

   public void setCurrency(String currency) {
      this.currency = currency;
   }

   public String getCurrency() {
      return currency;
   }

   public void setActivity(boolean activity) {
      this.activity = activity;
   }

   public boolean getActivity() {
      return activity;
   }

   @Override
   public String toString() {
      return userID   + '\n' +
             walletID + '\n' +
             balance  + '\n' +
             currency + '\n' +
             activity + '\n';
   }


}
