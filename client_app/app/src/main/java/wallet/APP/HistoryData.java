package wallet.APP;

public class HistoryData {

   private String ibanSender;
   private String ibanReceiver;
   private String amount;
   private String prevBalance;
   private String nextBalance;
   private String dateTime;
   private String comments;

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

   public void setAmount(String amount) {
      this.amount = amount;
   }

   public String getAmount() {
      return amount;
   }

   public void setPrevBalance(String prevBalance) {
      this.prevBalance = prevBalance;
   }

   public String getPrevBalance() {
      return prevBalance;
   }

   public void setNextBalance(String nextBalance) {
      this.nextBalance = nextBalance;
   }

   public String getNextBalance() {
      return nextBalance;
   }

   public void setDateTime(String dateTime) {
      this.dateTime = dateTime;
   }

   public String getDateTime() {
      return dateTime;
   }

   public void setComments(String comments) {
      this.comments = comments;
   }

   public String getComments() {
      return comments;
   }

   @Override
   public String toString() {
      return ibanSender   + '\n' +
             ibanReceiver + '\n' +
             amount       + '\n' +
             prevBalance  + '\n' +
             nextBalance  + '\n' +
             dateTime     + '\n' +
             comments     +'\n';
   }

}
