package com.epam.murodil.service.impl;

import com.epam.murodil.constants.EntityConstants;
import com.epam.murodil.constants.ErrorConstants;
import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.exceptions.ServiceException;
import com.epam.murodil.utils.AuthTools;
import com.epam.murodil.utils.validators.CredentialValidators;
import com.epam.murodil.model.dao.impl.AccountDao;
import com.epam.murodil.model.entity.Account;
import com.epam.murodil.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.*;

public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LogManager.getLogger();

    private static AccountServiceImpl instance = new AccountServiceImpl();


    private AccountServiceImpl() {}

    public static AccountServiceImpl getInstance() {
        return instance;
    }

    public Map<String, Object> signUpAccount(String first_name, String last_name, String email, String phone, String password, String password_confirm) throws ServiceException {
        Map<String, Object> resultMap = new HashMap<>();
        if (first_name == null || last_name == null || email == null || password == null) {
            resultMap.put(ErrorConstants.ERROR_KEY, ErrorConstants.DATA_NOT_PROVIDED);
        } else if (!password.equals(password_confirm)) {
            resultMap.put(ErrorConstants.ERROR_KEY, ErrorConstants.PASSWORD_MISMATCH);
        } else if (!CredentialValidators.validateEmail(email)) {
            resultMap.put(ErrorConstants.ERROR_KEY, ErrorConstants.EMAIL_INCORRECT);
        } else {
            Map<String, String> errorMap = CredentialValidators.validatePassword(password);
            if (errorMap.containsKey(ErrorConstants.ERROR_KEY)) {
                resultMap.put(ErrorConstants.ERROR_KEY, errorMap.get(ErrorConstants.ERROR_KEY));
                return resultMap;
            }

            String hashedPassword = AuthTools.getInstance().hash(password);
            try {
                boolean inserted = AccountServiceImpl.getInstance().insertAccount(first_name, last_name, email, phone, hashedPassword);
                if (!inserted) {
                    resultMap.put(ErrorConstants.ERROR_KEY, ErrorConstants.DUPLICATE_USER);
                } else {
                    Account account = AccountServiceImpl.getInstance().getByEmail(email);
                    resultMap.put(QueryConstants.SESSION_USER, account);
                    resultMap.put(QueryConstants.SESSION_USER_EMAIL, email);
                    resultMap.put(QueryConstants.SESSION_USER_FULL_NAME, first_name + " " + last_name);
                }
            } catch (Exception e) {
                logger.warn("Error during inserting Account {}", e.getMessage());
                throw new ServiceException("Error during inserting Account ", e);
            }
        }
        return resultMap;
    }

    public Map<String, List<Account>> getMapUsers() throws DaoException {
        List<Account> accounts = getAll();
        List<Account> customers = new ArrayList<>();
        List<Account> pharmacists = new ArrayList<>();
        List<Account> doctors = new ArrayList<>();
        List<Account> admins = new ArrayList<>();
        accounts.stream().forEach(account -> {
            switch (account.getRole()) {
                case EntityConstants.CUSTOMER_ROLE:
                    customers.add(account);
                    break;
                case EntityConstants.PHARMACIST_ROLE:
                    pharmacists.add(account);
                    break;
                case EntityConstants.DOCTOR_ROLE:
                    doctors.add(account);
                    break;
                case EntityConstants.ADMIN_ROLE:
                    admins.add(account);
                    break;
            }
        });
        Map<String, List<Account>> usersMap = new HashMap<String, List<Account>>() {{
            put("customers", customers);
            put("pharmacists", pharmacists);
            put("doctors", doctors);
            put("admins", admins);
        }};
        return usersMap;
    }
    public List<Account> getAll() throws DaoException {
        return AccountDao.getInstance().getList();
    }
    public boolean updateRole(int account_id, String role) {
        return AccountDao.getInstance().updateRole(account_id, role);
    }
    @Override
    public boolean insertAccount(String first_name, String last_name, String email, String phone, String password) throws DaoException {
        Account account = new Account(first_name, last_name, email, phone, password, "CUSTOMER");
        return AccountDao.getInstance().insert(account);
    }

    public Account getByEmail(String email) throws SQLException {
        Optional<Account> acc = AccountDao.getInstance().findByEmail(email);
        return acc.isPresent() ? acc.get() : null;
    }

    @Override
    public Optional<Account> findById(int userId) throws UnexpectedException {
        return Optional.empty();
    }

    public boolean deleteByMail(String email) throws DaoException {
        return AccountDao.getInstance().deleteByMail(email);
    }

    public Map<String, Object> signIn(String email, String password) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        Account account = AccountServiceImpl.getInstance().getByEmail(email);
        if (account != null) {
            boolean passwordCorrect = AuthTools.getInstance().verifyHash(password, account.getPassword());
            if (passwordCorrect) {
                response.put(QueryConstants.SESSION_USER, account);
                response.put(QueryConstants.SESSION_USER_FULL_NAME, account.getFullName());
                return response;
            }
        }
        response.put(ErrorConstants.ERROR_KEY, ErrorConstants.AUTH_FAILURE);
        return response;
    }
}
