package com.epam.murodil.model.dao.impl;

import com.epam.murodil.constants.QueryConstants;
import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.model.EntityQueries.ReceiptQueries;
import com.epam.murodil.model.dao.EntityDao;
import com.epam.murodil.model.database.DatabaseConnection;
import com.epam.murodil.model.entity.Receipt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.UnexpectedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiptsDao implements EntityDao<Receipt> {
    private static final Logger logger = LogManager.getLogger();
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
    public boolean insert(Receipt receipt) throws DaoException {
       try(Connection con = DatabaseConnection.getConnection()) {
           PreparedStatement preparedStatement = con.prepareStatement(ReceiptQueries.INSERT_ONE);
           preparedStatement.setInt(1, receipt.getCustomer_id());
           preparedStatement.setString(2, receipt.getDescription());
           preparedStatement.setString(3, receipt.getStatus());
           return preparedStatement.executeUpdate() == 1;
       } catch (SQLException e) {
           logger.warn("Failed to insert receipt {}", e.getMessage());
           throw new DaoException("Failed to insert receipt ", e);
       }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ReceiptQueries.DELETE_ONE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.warn("Failed to delete receipt {}", e.getMessage());
            throw new DaoException("Failed to delete receipt ", e);
        }
    }

    @Override
    public Optional<Receipt> findById(int id) throws DaoException {
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
            logger.warn("Failed to find receipt {}", e.getMessage());
            throw new DaoException("Failed to find receipt ", e);
        }
        return  receipt;
    }

    @Override
    public boolean update(Receipt receipt) throws DaoException {
        return false;
    }

    public List<Receipt> getForUser(int customer_id) throws DaoException {
        List<Receipt> receipts = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ReceiptQueries.GET_FOR_USER);
            preparedStatement.setInt(1, customer_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            receipts = parseReceipts(resultSet);
        } catch (SQLException e) {
            logger.warn("Failed to get receipt {}", e.getMessage());
            throw new DaoException("Failed to get receipt ", e);
        }
        return receipts;
    }

    public List<Receipt> getList() throws DaoException {
        List<Receipt> receipts = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ReceiptQueries.GET_ALL);
            receipts = parseReceipts(resultSet);
        } catch (SQLException e) {
            logger.warn("Failed to delete receipt list {}", e.getMessage());
            throw new DaoException("Failed to delete receipt list ", e);
        }
        return receipts;
    }
}
