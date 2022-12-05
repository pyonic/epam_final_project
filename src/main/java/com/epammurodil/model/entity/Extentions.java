package com.epammurodil.model.entity;

import java.sql.Timestamp;

public class Extentions {
    private int medicine_id;
    private int customer_id;
    private Timestamp from;
    private Timestamp to;
    boolean accepted;

    public Extentions(int medicine_id, int customer_id, Timestamp from, Timestamp to, boolean accepted) {
        this.medicine_id = medicine_id;
        this.customer_id = customer_id;
        this.from = from;
        this.to = to;
        this.accepted = accepted;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
