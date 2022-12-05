package com.epammurodil.service.impl;

import static com.epammurodil.constants.ErrorConstants.*;

import com.epammurodil.libs.AuthTools;
import com.epammurodil.libs.validators.CredentialValidators;
import com.epammurodil.model.dao.impl.AccountDao;
import com.epammurodil.model.entity.Account;
import com.epammurodil.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.UnexpectedException;
import java.sql.SQLException;
import java.util.*;

import static com.epammurodil.constants.QueryConstants.*;
import static com.epammurodil.constants.QueryConstants.SESSION_USER_FULL_NAME;

public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LogManager.getLogger();

    private static AccountServiceImpl instance = new AccountServiceImpl();


    private AccountServiceImpl() {}

    public static AccountServiceImpl getInstance() {
        return instance;
    }

    public Map<String, Object> signUpAccount(String first_name, String last_name, String email, String phone, String password, String password_confirm) {
        Map<String, Object> resultMap = new HashMap<>();
        if (first_name == null || last_name == null || email == null || password == null) {
            resultMap.put(ERROR_KEY, DATA_NOT_PROVIDED);
        } else if (!password.equals(password_confirm)) {
            resultMap.put(ERROR_KEY, PASSWORD_MISMATCH);
        } else if (!CredentialValidators.validateEmail(email)) {
            resultMap.put(ERROR_KEY, EMAIL_INCORRECT);
        } else {
            Map<String, String> errorMap = CredentialValidators.validatePassword(password);
            if (errorMap.containsKey(ERROR_KEY)) {
                resultMap.put(ERROR_KEY, errorMap.get(ERROR_KEY));
                return resultMap;
            }

            String hashedPassword = AuthTools.getInstance().hash(password);
            try {
                boolean inserted = AccountServiceImpl.getInstance().insertAccount(first_name, last_name, email, phone, hashedPassword);
                if (!inserted) {
                    resultMap.put(ERROR_KEY, DUPLICATE_USER);
                } else {
                    Account account = AccountServiceImpl.getInstance().getByEmail(email);
                    resultMap.put(SESSION_USER, account);
                    resultMap.put(SESSION_USER_EMAIL, email);
                    resultMap.put(SESSION_USER_FULL_NAME, first_name + " " + last_name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Result pan: " + resultMap);
        return resultMap;
    }

    public Map<String, List<Account>> getMapUsers() {
        List<Account> accounts = getAll();
        List<Account> customers = new ArrayList<>();
        List<Account> pharmacists = new ArrayList<>();
        List<Account> doctors = new ArrayList<>();
        List<Account> admins = new ArrayList<>();
        accounts.stream().forEach(account -> {
            switch (account.getRole()) {
                case "CUSTOMER":
                    customers.add(account);
                    break;
                case "PHARMACIST":
                    pharmacists.add(account);
                    break;
                case "DOCTOR":
                    doctors.add(account);
                    break;
                case "ADMIN":
                    System.out.println("ADMIN_______ADMIN");
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
    public List<Account> getAll() {
        return AccountDao.getInstance().getList();
    }
    public boolean updateRole(int account_id, String role) {
        return AccountDao.getInstance().updateRole(account_id, role);
    }
    @Override
    public boolean insertAccount(String first_name, String last_name, String email, String phone, String password) throws UnexpectedException {
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

    public boolean deleteByMail(String email) {
        return AccountDao.getInstance().deleteByMail(email);
    }

    public Map<String, Object> signIn(String email, String password) throws SQLException {
        Map<String, Object> respone = new HashMap<>();
        Account account = AccountServiceImpl.getInstance().getByEmail(email);
        if (account != null) {
            boolean passwordCorrect = AuthTools.getInstance().verifyHash(password, account.getPassword());
            if (passwordCorrect) {
                respone.put(SESSION_USER, account);
                respone.put(SESSION_USER_FULL_NAME, account.getFullName());
                return respone;
            }
        }
        respone.put("error", "Auth failed");
        return respone;
    }
}
