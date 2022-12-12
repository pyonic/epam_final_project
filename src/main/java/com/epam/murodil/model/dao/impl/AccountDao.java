package com.epam.murodil.model.dao.impl;

import static com.epam.murodil.constants.QueryConstants.*;

import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.model.EntityQueries.AccountQueries;
import com.epam.murodil.model.dao.EntityDao;
import com.epam.murodil.model.database.DatabaseConnection;
import com.epam.murodil.model.entity.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.UnexpectedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDao implements EntityDao<Account> {
    private static final Logger logger = LogManager.getLogger();
    private static AccountDao instance;

    private AccountDao() {
    }

    public List<Account> parseResult(ResultSet resultSet) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        while(resultSet.next()) {
            int userId = resultSet.getInt(ID);
            String first_name = resultSet.getString(FIRST_NAME);
            String last_name = resultSet.getString(LAST_NAME);
            String email = resultSet.getString(EMAIL);
            String phone = resultSet.getString(PHONE);
            String password = resultSet.getString(PASSWORD);
            String role = resultSet.getString(ROLE);
            Account account = new Account(userId, first_name, last_name, email, phone,password, role);
            accounts.add(account);
        }
        return accounts;
    }

    public static AccountDao getInstance() {
        if (instance == null) {
            instance = new AccountDao();
        }
        return instance;
    }

    @Override
    public boolean insert(Account account) throws DaoException {
        Account existingUser = null;
        try {
            Optional<Account> ac = findUser(account.getEmail(), account.getPhone());
            if (ac.isPresent()) existingUser = ac.get();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (existingUser != null) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AccountQueries.INSERT_ONE);
            preparedStatement.setString(1, account.getFirst_name());
            preparedStatement.setString(2, account.getLast_name());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getPassword());
            preparedStatement.setString(5, account.getRole());
            preparedStatement.setString(6, account.getPhone());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.warn("Error while inserting Account {}", e.getMessage());
            throw new DaoException("Error while inserting account", e);
        }
    }

    public List<Account> getList() throws DaoException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(AccountQueries.SELECT_ALL);
            List<Account> accounts = parseResult(resultSet);
            return accounts;
        } catch (SQLException e) {
            logger.warn("Failed to fetch list of Accounts {}", e.getMessage());
            throw new DaoException("Error while getting list of Accounts ", e);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AccountQueries.DELETE_ONE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.warn("Failed to fetch list of Accounts {}", e.getMessage());
            throw new DaoException("Error while getting list of Accounts ", e);
        }
    }

    public boolean deleteByMail(String email) throws DaoException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AccountQueries.DELETE_BY_EMAIL);
            preparedStatement.setString(1, email);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.warn("Failed to fetch list of Accounts {}", e.getMessage());
            throw new DaoException("Error while getting list of Accounts ", e);
        }
    }

    public Optional<Account> findUser(String email, String phone) throws SQLException {
        Optional<Account> account = Optional.empty();
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AccountQueries.SELECT_BY_EMAIL_PHONE);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = parseResult(resultSet);
            if (accounts.isEmpty() == false) account = Optional.of(accounts.get(0));
        }
        return account;
    }

    public Optional<Account> findByEmail(String email) throws SQLException {
        Optional<Account> account = Optional.empty();
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AccountQueries.SELECT_BY_EMAIL);
            preparedStatement.setString(1, email);
            System.out.println("RS: " + preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = parseResult(resultSet);
            if (accounts.isEmpty() == false) account = Optional.of(accounts.get(0));
        }
        return account;
    }
    @Override
    public Optional<Account> findById(int id) throws DaoException {
        Optional<Account> account = Optional.empty();
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AccountQueries.SELECT_ONE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = parseResult(resultSet);
            if (accounts.isEmpty() == false) account = Optional.of(accounts.get(0));
        } catch (SQLException e) {
            logger.warn("Failed to find Account by id {}", e.getMessage());
            throw new DaoException("Failed to find Account by id ", e);
        }
        return account;
    }

    @Override
    public boolean update(Account account) throws DaoException {
        return false;
    }

    public boolean updateRole(int account_id, String role) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AccountQueries.UPDATE_ROLE);
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, account_id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
