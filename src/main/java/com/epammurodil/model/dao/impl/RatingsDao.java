package com.epammurodil.model.dao.impl;

import com.epammurodil.constants.QueryConstants;
import com.epammurodil.model.EntityQueries.RatingsQueries;
import com.epammurodil.model.dao.EntityDao;
import com.epammurodil.model.database.DatabaseConnection;
import com.epammurodil.model.entity.AbstractEntity;
import com.epammurodil.model.entity.Rating;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.rmi.UnexpectedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RatingsDao implements EntityDao<Rating> {
    private static RatingsDao instance;

    private RatingsDao() {}

    public static RatingsDao getInstance() {
        if (instance == null) {
            instance = new RatingsDao();
        }
        return instance;
    }

    @Override
    public boolean insert(Rating rating) throws UnexpectedException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(RatingsQueries.INSERT_ONE);
            preparedStatement.setInt(1, rating.getMedicine_id());
            preparedStatement.setInt(2, rating.getAccount_id());
            preparedStatement.setInt(3, rating.getRating());
            preparedStatement.setString(4, rating.getBody());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Rating> getRatingForMedicine(int medicine_id) {
        List<Rating> ratings = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(RatingsQueries.GET_FOR_MEDICINE);
            preparedStatement.setInt(1, medicine_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int medicineId = resultSet.getInt(QueryConstants.MEDICINE_ID);
                int accountId = resultSet.getInt(QueryConstants.ACCOUNT_ID);
                int rate = resultSet.getInt(QueryConstants.RATING);
                String body = resultSet.getString(QueryConstants.BODY);
                String autorName = resultSet.getString(QueryConstants.FIRST_NAME);
                String autorSurName = resultSet.getString(QueryConstants.LAST_NAME);
                Rating rating = new Rating(medicineId, accountId, rate, body, autorName, autorSurName);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ratings;
    }

    @Override
    public boolean delete(int id) throws UnexpectedException {
        return false;
    }

    public Map<String, Object> getRating(int medicine_id) {
        Map<String, Object> resultMap = new HashMap<>();
        try(Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(RatingsQueries.GET_AVG_COUNT_RATING);
            preparedStatement.setInt(1, medicine_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Float rating = resultSet.getFloat("rating");
                Integer count = resultSet.getInt("count");
                resultMap.put("rating", rating);
                resultMap.put("count", count);
            }
            return resultMap;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean userRated(int medicine_id, int account_id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(RatingsQueries.USER_MEDICINE_REVIEW);
            preparedStatement.setInt(1, medicine_id);
            preparedStatement.setInt(2, account_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    @Override
    public Optional<Rating> findById(int id) throws UnexpectedException {
        return Optional.empty();
    }

    @Override
    public boolean update(Rating ratings) throws UnexpectedException {
        return false;
    }
}
