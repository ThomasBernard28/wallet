package wallet.APP;

public class CardData {
   
   private String cardNo;
   private String iban;
   private String type;

   public void setCardNo(String cardNo) {
      this.cardNo = cardNo;
   }

   public String getCardNo() {
      return cardNo;
   }

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

}
