package wallet.API;

import java.util.ArrayList;

public class JsonTools {

    /* 
     * When the api gives multiple objects in one json, this method is used to split them
     * ex: if I want the wallets of an user, this method will split the json so I'll have one json for each wallet
     * @return an arrayList of json
     */ 
    public static ArrayList splitJson(String json) {
        ArrayList<String> jsonList = new ArrayList();
        json = json.substring(1, json.length()-1); // remove the [ and ] 
        int jsonStart = 0;
        for (int i=1; i<=json.length(); i++) {
            if (i == json.length() || ( json.charAt(i)==',' && json.charAt(i-1)=='}' && json.charAt(i+1)=='{' )) {
               jsonList.add(json.substring(jsonStart, i));
               jsonStart = i+1;
            }
        }
        return jsonList;
    }


}
