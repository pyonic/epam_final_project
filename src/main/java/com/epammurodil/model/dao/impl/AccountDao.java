package com.epammurodil.model.dao.impl;

import com.epammurodil.model.EntityQueries.AccountQueries;
import com.epammurodil.model.dao.EntityDao;
import com.epammurodil.model.database.DatabaseConnection;
import com.epammurodil.model.entity.Account;

import java.rmi.UnexpectedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AccountDao implements EntityDao<Account> {
    private static AccountDao instance;

    private AccountDao() {
    }

    public List<Account> parseResult(ResultSet resultSet) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        while(resultSet.next()) {
            int userId = resultSet.getInt("id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            Account account = new Account(userId, first_name, last_name, email, phone,password, role);
            System.out.println("New acc " + account);
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
    public boolean insert(Account account) throws UnexpectedException {
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
            throw new RuntimeException(e);
        }
    }

    public List<Account> getList() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(AccountQueries.SELECT_ALL);
            List<Account> accounts = parseResult(resultSet);
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) throws UnexpectedException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AccountQueries.DELETE_ONE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteByMail(String email) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AccountQueries.DELETE_BY_EMAIL);
            preparedStatement.setString(1, email);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public Optional<Account> findById(int id) throws UnexpectedException {
        Optional<Account> account = Optional.empty();
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(AccountQueries.SELECT_ONE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Account> accounts = parseResult(resultSet);
            if (accounts.isEmpty() == false) account = Optional.of(accounts.get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public boolean update(Account account) throws UnexpectedException {
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
