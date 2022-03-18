package wallet;

import wallet.APP.User;
import wallet.APP.Wallet;

//import org.junit.Test;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

public class UserTest {

	String userData    = "{'userID':'uid69420','firstName':'u','lastName':'id','psswd':'1234','natID':'69420',{'language':'FR'}}";
	String oneWalletData  = "[{'walletID':1,'user':{'userID':'uid69420','firstName':'u','lastName':'id','psswd':'1234','natID':'69420',{'language':'FR'}},'bic':'CPHBBE75','openingData':'2022-03-08','activity':1}]";
	String twoWalletsData  = "[{'walletID':1,'user':{'userID':'uid69420','firstName':'u','lastName':'id','psswd':'1234','natID':'69420',{'language':'FR'}},'bic':'CPHBBE75','openingData':'2022-03-08','activity':1},{'walletID':2,'user':{'userID':'uid69420','firstName':'u','lastName':'id','psswd':'1234','natID':'69420',{'language':'FR'}},'bic':'CPHBBE75','openingData':'2022-03-08','activity':1}]";

	public void testData() {
		User user = new User();
		user.read_data(userData);
		System.out.println(user.get_userID());
		Assertions.assertEquals("uid69420", user.get_userID());
		Assertions.assertEquals("u", user.get_firstName());
		Assertions.assertEquals("id", user.get_lastName());
		Assertions.assertEquals("1234", user.get_password());
		Assertions.assertEquals("69420", user.get_natID());
		Assertions.assertEquals("FR", user.get_language());
	}

	public void testFetchWallet() {
		User user = new User();
		user.read_data(userData);
		user.set_walletsList(oneWalletData);
		ArrayList<Wallet> walletsList = user.get_walletsList();
		Wallet wallet = walletsList.get(0);
		Assertions.assertEquals(1, wallet.get_walletID());
		Assertions.assertEquals("uid69420", wallet.get_userID());
		Assertions.assertEquals("CPHBBE75", wallet.get_bic());
		Assertions.assertEquals("2022-03-08", wallet.get_openingDate());
		Assertions.assertEquals(true, wallet.get_activity());
	}			

	public void testFetchWallets() {
		User user = new User();
		user.read_data(userData);
		user.set_walletsList(twoWalletsData);
		ArrayList<Wallet> walletsList = user.get_walletsList();
		Wallet wallet = walletsList.get(1);
		Assertions.assertEquals(2, wallet.get_walletID());
		Assertions.assertEquals("uid69420", wallet.get_userID());
		Assertions.assertEquals("CPHBBE75", wallet.get_bic());
		Assertions.assertEquals("2022-03-08", wallet.get_openingDate());
		Assertions.assertEquals(true, wallet.get_activity());
	}			


}
