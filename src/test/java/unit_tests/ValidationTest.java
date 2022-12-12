package unit_tests;

import static org.testng.Assert.*;

import com.epam.murodil.constants.ErrorConstants;
import com.epam.murodil.utils.validators.CredentialValidators;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.epam.murodil.utils.AuthTools;

import java.util.Map;

public class ValidationTest {
    @DataProvider(name = "passwordsProvider")
    public Object[][] createPasswordEmail() {
        return new Object[][]{
                {"qwerty", ErrorConstants.NEED_SPECIAL_CHAR},
                {"Qwe5#tsy", null},
        };
    }
    @DataProvider(name = "emailProvider")
    public Object[][] createDataEmail() {
        return new Object[][]{
                {"epam.murodil.email.com",false},
                {"epam.murodil@.com",false},
                {"epam.murodil@com",false},
                {"epam.murodil@",false},
                {"epam.murodil@email.com",true},
        };
    }

    @Test(dataProvider = "emailProvider")
    public void testEmailValidation(String email, boolean expected) {
        boolean validation = CredentialValidators.validateEmail(email);
        assertEquals(validation, expected);
    }

    @Test (dataProvider = "passwordsProvider")
    void testPasswordValidation(String email, String expected) {
        Map<String, String> validation = CredentialValidators.validatePassword(email);
        assertEquals(validation.get(ErrorConstants.ERROR_KEY), expected);
    }

}
