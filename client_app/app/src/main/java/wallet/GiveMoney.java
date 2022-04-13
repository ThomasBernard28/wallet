package wallet;

import wallet.API.Api;

public class GiveMoney {


    public static void main(String[] args) {
        Api api = new Api();
        try {
            api.post_deposit(args[0], args[1]);
            System.out.println(args[1] + " deposited to " + args[0]);
        }
        catch (Exception e) {
            System.out.println("The money could not be deposited to the account");
        }
    }
}
