package com.epam.murodil.model.EntityQueries;

public class OrderQueries {
    public static final String TABLE_NAME = "orders";
    public static final String INSERT = String.format("INSERT INTO %s(medicine_id, customer_id, amount, dosage, price, delivery_address) VALUES(?,?,?,?,?,?)", TABLE_NAME);
    public static final String SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);
    public static final String USER_ORDER = String.format("%s WHERE medicine_id=? and customer_id=? and status = 'DELIVERED'", SELECT_ALL);
    public static final String GET_ORDER_STATUS = String.format("SELECT status FROM %s WHERE medicine_id=? AND customer_id=?", TABLE_NAME);
    public static final String GET_USER_ORDERS = String.format("SELECT * FROM %s WHERE customer_id=?", TABLE_NAME);
    public static final String GET_ORDERS_MAIN = String.format("SELECT o.*, a.first_name as fname, a.last_name as lname, a.email as email, m.title as mtitle FROM %s o LEFT JOIN accounts a on a.id = o.customer_id LEFT JOIN medicines m on m.id = o.medicine_id where a.id=?", TABLE_NAME);
    public static final String GET_ALL_MAIN = String.format("SELECT o.*, a.first_name as fname, a.last_name as lname, a.email as email, m.title as mtitle FROM %s o LEFT JOIN accounts a on a.id = o.customer_id LEFT JOIN medicines m on m.id = o.medicine_id ORDER BY o.id", TABLE_NAME);
    public static final String UPDATE_STATUS = String.format("UPDATE %s SET status = ? WHERE id = ?", TABLE_NAME);
    public static final String DELETE_ONE = String.format("DELETE FROM %s WHERE id = ?", TABLE_NAME);
}
