package wallet.APP;

import java.util.Map;

public class AccountData {
   
   private String iban;
   private String type;
   private String localCurr;
   private int    activity;
   private float  avgBalance;
   private String clientID;

   public void setIban(String iban) {
      this.iban = iban;
   }

   public String getIban() {
      return iban;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getType() {
      return type;
   }

   public void setLocalCurr(String localCurr) {
      this.localCurr = localCurr;
   }

   public String getLocalCurr() {
      return localCurr;
   }

   public void setActivity(int activity) {
      this.activity = activity;
   }

   public int getActivity() {
      return activity;
   }

   public void setAvgBalance(float avgBalance) {
      this.avgBalance = avgBalance;
   }

   public float getAvgBalance() {
      return avgBalance;
   }

   public void setClientID(Map client) {
      clientID = (String) client.get("userID");
   }

   public String getClientID() {
      return clientID;
   }

   @Override
   public String toString() {
      return iban       + '\n' +
             localCurr  + '\n' +
             avgBalance + '\n' +
             type       + '\n' +
             clientID   + '\n' +
             activity   + '\n';
   }

}
