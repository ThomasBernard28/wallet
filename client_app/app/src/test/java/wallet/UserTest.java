package wallet;

import wallet.APP.User;
import wallet.APP.Wallet;

//import org.junit.Test;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.nio.file.Files;  
import java.nio.file.Paths;

public class UserTest {
	/*
	*/

	@Test
	public void testData() {
		User user = new User();
		try {
			user.read_data(new String(Files.readAllBytes(Paths.get("build/resources/test/json/userData.json"))));
		} catch (Exception e) {
		}
		Assertions.assertEquals("uid69420", user.get_userID());
		Assertions.assertEquals("u", user.get_firstName());
		Assertions.assertEquals("id", user.get_lastName());
		Assertions.assertEquals("1234", user.get_password());
		Assertions.assertEquals(69420, user.get_natID());
		Assertions.assertEquals("FR", user.get_language());
	}

	@Test
	public void testFetchWallet() {
		User user = new User();
		try {
			user.read_data(new String(Files.readAllBytes(Paths.get("build/resources/test/json/userData.json"))));
			user.set_walletsList(new String(Files.readAllBytes(Paths.get("build/resources/test/json/oneWalletData.json"))));
		} catch (Exception e) {
		}
		ArrayList<Wallet> walletsList = user.get_walletsList();
		Wallet wallet = walletsList.get(0);
		System.out.println(wallet);
		Assertions.assertEquals(1, wallet.get_walletID());
		Assertions.assertEquals("uid69420", wallet.get_userID());
		Assertions.assertEquals("CPHBBE75", wallet.get_bic());
		Assertions.assertEquals("2022-03-08", wallet.get_openingDate());
		Assertions.assertEquals(1, wallet.get_activity());
	}			

	@Test
	public void testFetchWallets() {
		User user = new User();
		try {
			user.read_data(new String(Files.readAllBytes(Paths.get("build/resources/test/json/userData.json"))));
			user.set_walletsList(new String(Files.readAllBytes(Paths.get("build/resources/test/json/twoWalletsData.json"))));
		} catch (Exception e) {
		}
		ArrayList<Wallet> walletsList = user.get_walletsList();
		Wallet wallet = walletsList.get(1);
		Assertions.assertEquals(2, wallet.get_walletID());
		Assertions.assertEquals("uid69420", wallet.get_userID());
		Assertions.assertEquals("GKCCBEBB", wallet.get_bic());
		Assertions.assertEquals("2022-03-08", wallet.get_openingDate());
		Assertions.assertEquals(1, wallet.get_activity());
	}			


}
