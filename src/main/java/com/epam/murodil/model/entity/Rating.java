package com.epam.murodil.model.entity;

public class Rating extends AbstractEntity{
    private int medicine_id;
    private int account_id;
    private int rating;
    private String body = "";

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    private String authorFirstName;
    private String authorLastName;

    public Rating(int medicine_id, int account_id, int rating, String body, String authorFirstName, String authorLastName) {
        this.medicine_id = medicine_id;
        this.account_id = account_id;
        this.rating = rating;
        this.body = body;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public Rating(int medicine_id, int account_id, int rating, String body) {
        this.medicine_id = medicine_id;
        this.account_id = account_id;
        this.rating = rating;
        this.body = body;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
