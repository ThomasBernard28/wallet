package wallet.APP;

public class AccountRequestData {
   
   private int    accRequestID;
   private String type;
   private int    status;
   private int    validator;
   private String comments;

   public void setAccRequestID(int accRequestID) {
      this.accRequestID = accRequestID;
   }

   public int getAccRequestID() {
      return accRequestID;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getType() {
      return type;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public int getStatus() {
      return status;
   }

   public void setValidator(int validator) {
      this.validator = validator;
   }

   public int validator() {
      return validator;
   }

   public void setComments(String comments) {
      this.comments = comments;
   }

   public String getComments() {
      return comments;
   }

   @Override
   public String toString() {
      return accRequestID + '\n' +
             type         + '\n' +
             status       + '\n' +
             validator    + '\n' +
             comments     + '\n';
   }

}
