package wallet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
	@Test void UserReadData() {
		User user = new User();
        try {
		    user.read_data("build/resources/test/yaml/user.yaml");
        }
        catch (Exception e) {
        }

    @Test void UserAddWallet() {
    }
}
