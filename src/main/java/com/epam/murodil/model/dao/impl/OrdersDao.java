package com.epam.murodil.model.dao.impl;

import static com.epam.murodil.constants.QueryConstants.*;

import com.epam.murodil.exceptions.DaoException;
import com.epam.murodil.model.EntityQueries.OrderQueries;
import com.epam.murodil.model.dao.EntityDao;
import com.epam.murodil.model.database.DatabaseConnection;
import com.epam.murodil.model.entity.Account;
import com.epam.murodil.model.entity.Medicine;
import com.epam.murodil.model.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersDao implements EntityDao<Order> {
    private static final Logger logger = LogManager.getLogger();
    public static OrdersDao instance = new OrdersDao();
    private OrdersDao() {};

    public static OrdersDao getInstance() {
        return instance;
    }

    public List<Order> parseOrders(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(ID);
            int medicine_id = resultSet.getInt(MEDICINE_ID);
            int customer_id = resultSet.getInt(CUSTOMER_ID);
            BigDecimal amount = resultSet.getBigDecimal(AMOUNT);
            BigDecimal dosage = resultSet.getBigDecimal(DOSAGE);
            BigDecimal price = resultSet.getBigDecimal(PRICE);
            String delivery_address = resultSet.getString(DELIVERY_ADDRESS);
            String status = resultSet.getString(STATUS);
            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            String email = resultSet.getString(EMAIL);
            String mtitle = resultSet.getString("mtitle");
            Account customer = new Account(fname, lname, email, null, null, null);
            Medicine medicine = new Medicine(mtitle, null, null, null, false);
            Order order = new Order(id, medicine_id, customer_id, amount, dosage, price, delivery_address, status, customer, medicine);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public boolean insert(Order order) throws DaoException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.INSERT);
            preparedStatement.setInt(1, order.getMedicine_id());
            preparedStatement.setInt(2, order.getAccount_id());
            preparedStatement.setBigDecimal(3, order.getAmount());
            preparedStatement.setBigDecimal(4, order.getDosage());
            preparedStatement.setBigDecimal(5, order.getPrice());
            preparedStatement.setString(6, order.getDelivery_address());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.warn("Failed to insert Orders  {}", e.getMessage());
            throw new DaoException("Failed to insert Orders ", e);
        }
    }

    public boolean userOrdered(int medicine_id, int account_id) throws DaoException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.USER_ORDER);
            preparedStatement.setInt(1, medicine_id);
            preparedStatement.setInt(2, account_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? true : false;
        } catch (SQLException e) {
            logger.warn("Failed to insert Orders  {}", e.getMessage());
            throw new DaoException("Failed to insert Orders ", e);
        }
    }

    @Override
    public boolean delete(int id) throws DaoException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.DELETE_ONE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.warn("Failed to delete Orders {}", e.getMessage());
            throw new DaoException("Failed to delete Orders ", e);
        }
    }

    @Override
    public Optional<Order> findById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean update(Order order) throws DaoException {
        return false;
    }

    public List<Order> getAllOrders() throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(OrderQueries.GET_ALL_MAIN);
            orders = parseOrders(resultSet);
        } catch (SQLException e) {
            logger.warn("Failed to get Orders {}", e.getMessage());
            throw new DaoException("Failed to get Orders ", e);
        }
        return orders;
    }

    public List<Order> getUserOrders(int account_id) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.GET_ORDERS_MAIN);
            preparedStatement.setInt(1, account_id);
            System.out.println("ST " + preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            orders = parseOrders(resultSet);
        } catch (SQLException e) {
            logger.warn("Failed to delete Orders {}", e.getMessage());
            throw new DaoException("Failed to delete Orders ", e);
        }
        return orders;
    }

    public boolean updateStatus(int order_id, String status) throws DaoException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.UPDATE_STATUS);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, order_id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.warn("Failed to update status {}", e.getMessage());
            throw new DaoException("Failed to delete status ", e);
        }
    }
}
