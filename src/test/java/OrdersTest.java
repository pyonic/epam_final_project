import static org.junit.jupiter.api.Assertions.*;
import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.exceptions.ServiceException;
import com.epam.murodil.model.entity.Account;
import com.epam.murodil.model.entity.Medicine;
import com.epam.murodil.service.impl.AccountServiceImpl;
import com.epam.murodil.service.impl.MedicineServiceImpl;
import com.epam.murodil.service.impl.OrdersServiceImpl;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdersTest {
    public static String MEDICINE_SLUG = "";
    public static Account ACCOUNT = null;
    public static Medicine MEDICINE = null;
    public static int ORDER_ID = 0;

    public static final BigDecimal DOSAGE = BigDecimal.valueOf(2);
    public static final String DELIVERY_ADDRESS = "177A Bleecker Street, New York City, NY 10012-1406";

    @BeforeAll
    static void getOrderDatas() throws DaoException, ServiceException {
        MEDICINE_SLUG = MedicineServiceImpl.getInstance().insertMedicine(TestDatasets.MEDICINE_NAME,TestDatasets.MEDICINE_DESCRIPTION, TestDatasets.MEDICINE_PRICE, false);
        MEDICINE = MedicineServiceImpl.getInstance().getBySlag(MEDICINE_SLUG);

        Map signUpMap = AccountServiceImpl.getInstance().signUpAccount(TestDatasets.TEST_FNAME, TestDatasets.TEST_LNAME, TestDatasets.TEST_EMAIL, null, TestDatasets.TEST_PASSWORD, TestDatasets.TEST_PASSWORD);
        ACCOUNT = (Account) signUpMap.get(QueryConstants.SESSION_USER);
    }

    @Test
    @Order(1)
    void makeOrder() throws UnexpectedException, DaoException {
        boolean inserted = OrdersServiceImpl.getInstance().insert(MEDICINE.getId(), ACCOUNT.getId(), DOSAGE, DOSAGE, DELIVERY_ADDRESS);
        assertEquals(true, inserted);
    }

    @Test
    @Order(2)
    void checkAccountOrders() throws DaoException {
        List<com.epam.murodil.model.entity.Order> orders = OrdersServiceImpl.instance.getUserOrders(ACCOUNT.getId());
        com.epam.murodil.model.entity.Order order = orders.get(0);
        assertEquals(1, orders.size());
        assertEquals(DOSAGE.multiply(MEDICINE.getPrice()) , order.getPrice().multiply(BigDecimal.valueOf(1.0)));
        ORDER_ID = orders.get(0).getId();
    }

    @AfterAll
    static void deleteAllTestDatas() throws UnexpectedException, DaoException {
        MedicineServiceImpl.getInstance().deleteOne(MEDICINE.getId());
        AccountServiceImpl.getInstance().deleteByMail(ACCOUNT.getEmail());
        OrdersServiceImpl.getInstance().deleteById(ORDER_ID);
    }

}
