package unit_tests;

import static org.testng.Assert.*;
import com.epam.murodil.utils.AuthTools;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AuthToolsTest {
    public static String PASSWORD = "QwertyUI777#";
    public static String INCORRECT_PASSWORD = "OtherPassword";
    public static String HASHED_PASSWORD = "";

    public static AuthTools AuthTools_INSTANCE;

    @BeforeTest
    static public void prepareHashedData() {
        AuthTools_INSTANCE = AuthTools.getInstance();
        HASHED_PASSWORD = AuthTools_INSTANCE.hash(PASSWORD);
    }

    @Test
    public void validateHashedPassword() {
        boolean isCorrect = AuthTools_INSTANCE.verifyHash(PASSWORD, HASHED_PASSWORD);
        boolean notCorrect = AuthTools_INSTANCE.verifyHash(INCORRECT_PASSWORD, HASHED_PASSWORD);
        assertTrue(isCorrect);
        assertFalse(notCorrect);
    }
}
