package com.epammurodil.model.EntityQueries;

public class MedicineQueries {
    public static String TABLE_NAME = "medicines";
    public static String INSERT_ONE = String.format("INSERT INTO %s(title, description, price, slug, need_receipt) VALUES(?, ?, ?, ?, ?)", TABLE_NAME);
    public static String SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);
    public static String SELECT_ALL_ORDERED = String.format("SELECT * FROM %s ORDER BY created_at DESC", TABLE_NAME);
    public static String SELECT_ONE = SELECT_ALL + " WHERE id = ?";
    public static String SELECT_ONE_BY_SLUG = String.format("SELECT * FROM %s WHERE slug = ?", TABLE_NAME);
    public static String UPDATE_ONE = String.format("UPDATE %s SET title=?,description=?,price=?,need_receipt=? WHERE id=?", TABLE_NAME);
    public static String DELETE_ONE = String.format("DELETE FROM %s WHERE id=?", TABLE_NAME);
    public static String SEARCH_BY_NAME_AND_DESCRIPTION = "SELECT * FROM " + TABLE_NAME + " WHERE title ilike '?%' or description ilike '?%' ";
    public static String GENERATE_SEARCH_QUERY(String filter) {
        return "SELECT * FROM " + TABLE_NAME + " WHERE title ilike '" + filter + "%' or description ilike '" + filter + "%' LIMIT 4";
    }
}
