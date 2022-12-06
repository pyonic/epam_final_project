import com.epammurodil.constants.QueryConstants;
import com.epammurodil.model.entity.Account;
import com.epammurodil.model.entity.Medicine;
import com.epammurodil.model.entity.Rating;
import com.epammurodil.service.impl.AccountServiceImpl;
import com.epammurodil.service.impl.MedicineServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import com.epammurodil.service.impl.RatingServiceImpl;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicineTest {
    public static String MEDICINE_SLUG = "";
    public static Account ACCOUNT = null;
    public static Medicine MEDICINE = null;

    public static final BigDecimal DOSAGE = BigDecimal.valueOf(2);
    public static final String DELIVERY_ADDRESS = "177A Bleecker Street, New York City, NY 10012-1406";

    @BeforeAll
    static void prepareDatas() {
        Map signUpMap = AccountServiceImpl.getInstance().signUpAccount(TestDatasets.TEST_FNAME, TestDatasets.TEST_LNAME, TestDatasets.TEST_EMAIL, null, TestDatasets.TEST_PASSWORD, TestDatasets.TEST_PASSWORD);
        ACCOUNT = (Account) signUpMap.get(QueryConstants.SESSION_USER);
    }

    @Test
    @Order(1)
    void getOrderDatas() throws SQLException {
        MEDICINE_SLUG = MedicineServiceImpl.getInstance().insertMedicine(TestDatasets.MEDICINE_NAME,TestDatasets.MEDICINE_DESCRIPTION, TestDatasets.MEDICINE_PRICE, false);
        MEDICINE = MedicineServiceImpl.getInstance().getBySlag(MEDICINE_SLUG);
        assertNotNull(MEDICINE);
    }

    @Test
    @Order(2)
    void setRatingForMedicine() throws UnexpectedException {
        RatingServiceImpl.getInstance().insertRating(MEDICINE.getId(), 4, "Good product", ACCOUNT.getId());
        List<Rating> ratings = RatingServiceImpl.getInstance().getRatingsForMedicine(MEDICINE.getId());
        assertEquals(1, ratings.size());
    }

    @Test
    @Order(3)
    void testUserRated() {
        boolean rated = RatingServiceImpl.getInstance().userHasRated(MEDICINE.getId(), ACCOUNT.getId());
        assertTrue(rated);
    }

    @AfterAll
    static void deleteAllData() throws UnexpectedException {
        MedicineServiceImpl.getInstance().deleteOne(MEDICINE.getId());
        AccountServiceImpl.getInstance().deleteByMail(ACCOUNT.getEmail());
    }
}
