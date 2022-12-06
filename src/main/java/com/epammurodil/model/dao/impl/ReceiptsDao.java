package com.epammurodil.model.dao.impl;

import com.epammurodil.constants.QueryConstants;
import com.epammurodil.model.EntityQueries.ReceiptQueries;
import com.epammurodil.model.dao.EntityDao;
import com.epammurodil.model.database.DatabaseConnection;
import com.epammurodil.model.entity.Receipt;

import java.rmi.UnexpectedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiptsDao implements EntityDao<Receipt> {
    public static final ReceiptsDao instance = new ReceiptsDao();

    private ReceiptsDao() {}

    public static ReceiptsDao getInstance() {
        return instance;
    }

    public List<Receipt> parseReceipts(ResultSet resultSet) throws SQLException {
        List<Receipt> result = new ArrayList<>();
        while(resultSet.next()) {
            int id = resultSet.getInt(QueryConstants.ID);
            int customer_id = resultSet.getInt(QueryConstants.CUSTOMER_ID);
            String descriptions = resultSet.getString(QueryConstants.DESCRIPTION);
            String status = resultSet.getString(QueryConstants.STATUS);
            Receipt receipts1 = new Receipt(id, customer_id, descriptions, status);
            result.add(receipts1);
        }
        return result;
    }

    @Override
    public boolean insert(Receipt receipt) throws UnexpectedException {
       try(Connection con = DatabaseConnection.getConnection()) {
           PreparedStatement preparedStatement = con.prepareStatement(ReceiptQueries.INSERT_ONE);
           preparedStatement.setInt(1, receipt.getCustomer_id());
           preparedStatement.setString(2, receipt.getDescription());
           preparedStatement.setString(3, receipt.getStatus());
           return preparedStatement.executeUpdate() == 1;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public boolean delete(int id) throws UnexpectedException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ReceiptQueries.DELETE_ONE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Receipt> findById(int id) throws UnexpectedException {
        Optional<Receipt> receipt = Optional.empty();
        try(Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(ReceiptQueries.GET_ONE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                List<Receipt> receipt1 = parseReceipts(resultSet);
                receipt = Optional.of(receipt1.get(0));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  receipt;
    }

    @Override
    public boolean update(Receipt receipt) throws UnexpectedException {
        return false;
    }

    public List<Receipt> getForUser(int customer_id) {
        List<Receipt> receipts = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ReceiptQueries.GET_FOR_USER);
            preparedStatement.setInt(1, customer_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            receipts = parseReceipts(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receipts;
    }

    public List<Receipt> getList() {
        List<Receipt> receipts = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ReceiptQueries.GET_ALL);
            receipts = parseReceipts(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return receipts;
    }
}
