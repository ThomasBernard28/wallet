/*
 * Extension 1 : Godin Theo
 */

package wallet.APP;

import wallet.API.JsonReader;
import wallet.APP.CardData;

public class Card implements JsonReader {
   
   private CardData data = new CardData();

   @Override
   public void read_data(String json) {
   }

   @Override
   public String toString() {
      return data.toString();
   }

}
