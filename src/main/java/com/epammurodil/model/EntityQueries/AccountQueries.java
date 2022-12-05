package com.epammurodil.model.EntityQueries;

public class AccountQueries {
    public final static String TABLE_NAME = "accounts";
    public final static String INSERT_ONE = String.format("INSERT INTO %s(first_name, last_name, email, password, role, phone) VALUES(?,?,?,?,?,?)", TABLE_NAME);
    public final static String SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);
    public final static String SELECT_ONE = String.format("%s WHERE id=?", SELECT_ALL);
    public final static String SELECT_BY_EMAIL_PHONE = String.format("%s WHERE email=? or phone=? and phone != ''", SELECT_ALL);

    public final static String SELECT_BY_EMAIL = String.format("%s WHERE email=?", SELECT_ALL);
    public final static String SELECT_ALL_NO_PASSWORD = String.format("SELECT id,first_name,last_name,email,phone,role FROM %s", TABLE_NAME);
    public final static String SELECT_ONE_NO_PASSWORD = String.format("%s WHERE id=?", SELECT_ALL_NO_PASSWORD);
    public final static String DELETE_ONE = String.format("DELETE FROM %s WHERE id=?", TABLE_NAME);
    public final static String DELETE_BY_EMAIL = String.format("DELETE FROM %s WHERE email=?", TABLE_NAME);
    public final static String UPDATE_ONE = "";
    public final static String GET_PASSWORD = String.format("SELECT password FROM %s  WHERE id=?", TABLE_NAME);
    public final static String UPDATE_ROLE = String.format("UPDATE %s SET role = ? WHERE id = ?", TABLE_NAME);

}
