package com.epammurodil.model.dao.impl;

import com.epammurodil.model.EntityQueries.OrderQueries;
import com.epammurodil.model.dao.EntityDao;
import com.epammurodil.model.database.DatabaseConnection;
import com.epammurodil.model.entity.Account;
import com.epammurodil.model.entity.Medicine;
import com.epammurodil.model.entity.Order;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersDao implements EntityDao<Order> {
    public static OrdersDao instance = new OrdersDao();

    private OrdersDao() {};

    public static OrdersDao getInstance() {
        return instance;
    }

    public List<Order> parseOrders(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int medicine_id = resultSet.getInt("medicine_id");
            int customer_id = resultSet.getInt("customer_id");
            BigDecimal amount = resultSet.getBigDecimal("amount");
            BigDecimal dosage = resultSet.getBigDecimal("dosage");
            BigDecimal price = resultSet.getBigDecimal("price");
            String delivery_address = resultSet.getString("delivery_address");
            String status = resultSet.getString("status");
            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            String email = resultSet.getString("email");
            String mtitle = resultSet.getString("mtitle");
            Account customer = new Account(fname, lname, email, null, null, null);
            Medicine medicine = new Medicine(mtitle, null, null, null, false);
            Order order = new Order(id, medicine_id, customer_id, amount, dosage, price, delivery_address, status, customer, medicine);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public boolean insert(Order order) throws UnexpectedException {
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
            throw new RuntimeException(e);
        }
    }

    public boolean userOrdered(int medicine_id, int account_id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.USER_ORDER);
            preparedStatement.setInt(1, medicine_id);
            preparedStatement.setInt(2, account_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? true : false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(int id) throws UnexpectedException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.DELETE_ONE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Order> findById(int id) throws UnexpectedException {
        return Optional.empty();
    }

    @Override
    public boolean update(Order order) throws UnexpectedException {
        return false;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(OrderQueries.GET_ALL_MAIN);
            orders = parseOrders(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public List<Order> getUserOrders(int account_id) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.GET_ORDERS_MAIN);
            preparedStatement.setInt(1, account_id);
            System.out.println("ST " + preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            orders = parseOrders(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public boolean updateStatus(int order_id, String status) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.UPDATE_STATUS);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, order_id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
