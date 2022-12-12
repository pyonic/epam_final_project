package com.epam.murodil.utils;

import com.epam.murodil.model.database.DatabaseConnection;

import java.sql.*;
import java.util.Map;

public class TableManager {
    private static Connection connection;

    {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getOne(String table, Map<String, String> filters) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM " + table);
        StringBuilder filter = new StringBuilder();

        for(Map.Entry<String, String> fl: filters.entrySet()) {
            filter.append(fl.getKey()+"="+fl.getValue()+",");
        }
        query.append(" " + filter.substring(0, filter.length() - 1) + " LIMIT 1");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query.toString());

        ResultSet result = null;
        if (resultSet.next()) result = resultSet;
        return result;
    }

    public static ResultSet getAll(String table, Map<String, String> filters) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM " + table);
        StringBuilder filter = new StringBuilder();

        for(Map.Entry<String, String> fl: filters.entrySet()) {
            filter.append(fl.getKey()+"="+fl.getValue()+",");
        }
        query.append(" " + filter.substring(0, filter.length() - 1));

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query.toString());

        return resultSet;
    }

    public static ResultSet executeStatement(String query, Boolean update) throws SQLException {
        Connection connection1 = DatabaseConnection.getConnection();
        Statement statement = connection1.createStatement();
        ResultSet resultSet = null;
        if (update) {
            statement.executeUpdate(query);
        } else {
            resultSet = statement.executeQuery(query);
        }
        return resultSet;
    }

    public static ResultSet executePreparedStatement(String query, String[] params, Boolean update) throws SQLException {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(int i = 0; i < params.length; i++) {
                preparedStatement.setString(i+1, params[i]);
            }
            if (update) preparedStatement.executeUpdate();
            else resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static boolean update(String table, Map<String, String> updateFields, Map<String, String> filters) throws SQLException {
        StringBuilder UPDATE_QUERY = new StringBuilder("UPDATE " + table + " SET ");
        for(Map.Entry<String, String> f: updateFields.entrySet()) {
            UPDATE_QUERY.append(f.getKey() + "=" + f.getValue() + ",");
        }
        UPDATE_QUERY.deleteCharAt(UPDATE_QUERY.length() - 1);
        UPDATE_QUERY.append(" WHERE ");
        for(Map.Entry<String, String> f: filters.entrySet()) {
            UPDATE_QUERY.append(f.getKey() + "=" + f.getValue() + ",");
        }
        UPDATE_QUERY.deleteCharAt(UPDATE_QUERY.length() - 1);
        System.out.println(UPDATE_QUERY);
        Statement statement = connection.createStatement();
        return statement.executeUpdate(UPDATE_QUERY.toString()) == 1;
    }
}
