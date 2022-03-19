package wallet.APP;

public  class ClientData {
   
   private String userID;
   private int    natID;
   private String firstName;
   private String lastName;

   public void setUserID(String userID) {
      this.userID = userID;
   }

   public String getUserID() {
      return userID;
   }

   public void setNatID(int natID) {
      this.natID = natID;
   }

   public int getNatID() {
      return natID;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getLastName() {
      return lastName;
   }
}
