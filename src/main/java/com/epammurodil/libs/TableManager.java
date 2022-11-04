package com.epammurodil.libs;

import com.epammurodil.database.DatabaseConnection;

import java.sql.*;
import java.util.Map;

public class TableManager {
    private Connection connection = null;

    public TableManager() throws SQLException {
        DatabaseConnection dbcon = new DatabaseConnection("jdbc:postgresql://localhost/test", "postgres", "root");
        this.connection = (Connection) dbcon;
    }

    public ResultSet getOne(String table, Map<String, String> filters) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM " + table);
        StringBuilder filter = new StringBuilder();

        for(Map.Entry<String, String> fl: filters.entrySet()) {
            filter.append(fl.getKey()+"="+fl.getValue()+",");
        }
        query.append(" " + filter.substring(0, filter.length() - 1) + " LIMIT 1");

        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query.toString());

        ResultSet result = null;
        if (resultSet.next()) result = resultSet;
        return result;
    }

    public ResultSet getAll(String table, Map<String, String> filters) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM " + table);
        StringBuilder filter = new StringBuilder();

        for(Map.Entry<String, String> fl: filters.entrySet()) {
            filter.append(fl.getKey()+"="+fl.getValue()+",");
        }
        query.append(" " + filter.substring(0, filter.length() - 1));

        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query.toString());

        return resultSet;
    }

    public ResultSet executeStatement(String query, Boolean update) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = null;
        if (update) {
            statement.executeUpdate(query);
        } else {
            resultSet = statement.executeQuery(query);
        }
        return resultSet;
    }

    public ResultSet executePreparedStatement(String query, String[] params, Boolean update) throws SQLException {
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
}
