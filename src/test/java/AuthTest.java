import static org.junit.jupiter.api.Assertions.*;
import com.epammurodil.constants.ErrorConstants;
import com.epammurodil.constants.QueryConstants;
import com.epammurodil.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthTest {
    @Test
    @Order(1)
    void testRegistration() {
        Map inserted = AccountServiceImpl.getInstance().signUpAccount(TestDatasets.TEST_FNAME, TestDatasets.TEST_LNAME, TestDatasets.TEST_EMAIL, null, TestDatasets.TEST_PASSWORD, TestDatasets.TEST_PASSWORD);
        assertNotEquals(null, inserted.getOrDefault(QueryConstants.SESSION_USER_FULL_NAME, null));
    }

    @Test
    void testRegistrationErrors() {
        Map duplicateUserInsert = AccountServiceImpl.getInstance().signUpAccount(TestDatasets.TEST_FNAME, TestDatasets.TEST_LNAME, TestDatasets.TEST_EMAIL, null, TestDatasets.TEST_PASSWORD, TestDatasets.TEST_PASSWORD);
        assertEquals(ErrorConstants.DUPLICATE_USER, duplicateUserInsert.getOrDefault(ErrorConstants.ERROR_KEY, null));
    }

    @Test
    @Order(2)
    void testLogin() throws SQLException {
        Map signIn = AccountServiceImpl.getInstance().signIn(TestDatasets.TEST_EMAIL, TestDatasets.TEST_PASSWORD);
        assertNotEquals(null, signIn.getOrDefault(QueryConstants.SESSION_USER, null));
    }

    @Test
    @Order(3)
    void testLoginIncorrect() throws SQLException {
        Map signIn = AccountServiceImpl.getInstance().signIn(TestDatasets.TEST_EMAIL, TestDatasets.TEST_PASSWORD + "_INCORRECT");
        assertNotEquals(null, signIn.getOrDefault(ErrorConstants.ERROR_KEY, null));
    }

    @AfterAll
    static void deleteAccount() {
        AccountServiceImpl.getInstance().deleteByMail(TestDatasets.TEST_EMAIL);
    }

}
