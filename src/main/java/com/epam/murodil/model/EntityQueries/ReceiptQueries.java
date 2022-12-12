package com.epam.murodil.model.EntityQueries;

public class ReceiptQueries {
    public static final String TABLE_NAME = "receipt_requests";
    public static final String INSERT_ONE = String.format("INSERT INTO %s(customer_id, description, status) values(?,?,?)", TABLE_NAME);
    public static final String GET_ONE = String.format("SELECT * FROM %s WHERE id = ?", TABLE_NAME);
    public static final String GET_FOR_USER = String.format("SELECT * FROM %s WHERE customer_id = ? ORDER BY id DESC", TABLE_NAME);
    public static final String GET_ALL = String.format("SELECT * FROM %s WHERE status = 'NEW'", TABLE_NAME);
    public static final String UPDATE_ONE = String.format("UPDATE %s SET status = ? WHERE id = ?", TABLE_NAME);
    public static final String DELETE_ONE = String.format("DELETE FROM %s WHERE id = ?", TABLE_NAME);
}
