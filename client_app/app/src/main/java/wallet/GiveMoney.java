package wallet;

import wallet.API.Api;

/*
 * This class is used to simulate a cash deposit (see the giveMoney gradle task)
 */
public class GiveMoney {

    /*
     * @param args[0] : the iban of the account that receive the money
     * @param args[1] : the amount of money
     */
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
