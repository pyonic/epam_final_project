package com.epam.murodil.model.EntityQueries;

public class RatingsQueries {
    private static RatingsQueries instance = new RatingsQueries();

    private RatingsQueries() {};

    public static RatingsQueries getInstance() {
        return instance;
    }
    public static String TABLE_NAME = "ratings";
    public static final String INSERT_ONE = String.format("INSERT INTO %s VALUES(?,?,?,?)", TABLE_NAME);
    public static final String GET_FOR_MEDICINE = String.format("SELECT r.*, a.first_name, a.last_name FROM %s r LEFT JOIN accounts a ON a.id = r.account_id WHERE r.medicine_id = ?", TABLE_NAME);

    public static final String USER_MEDICINE_REVIEW = String.format("SELECT * FROM %s WHERE medicine_id = ? and account_id = ?", TABLE_NAME);
    public static final String GET_AVG_COUNT_RATING = String.format("select round(avg(rating), 1) as rating, count(account_id) as count from %s where medicine_id = ?", TABLE_NAME);
}
