package wallet.APP;

import java.util.Map;

public class CoOwnerData {

   private String coOwnerID;

   public void setCoOwnerID(Map coOwnerID) {
      this.coOwnerID = (String) coOwnerID.get("userID_coOwner");
   }

   public String getCoOwnerID() {
      return coOwnerID;
   }

   @Override
   public String toString() {
      return coOwnerID + '\n';
   }

}
