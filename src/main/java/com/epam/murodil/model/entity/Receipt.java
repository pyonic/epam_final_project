package com.epam.murodil.model.entity;

public class Receipt extends AbstractEntity {
    int id;
    int customer_id;
    String description;
    String status;

    public Receipt(int id, int customer_id, String description, String status) {
        this.id = id;
        this.customer_id = customer_id;
        this.description = description;
        this.status = status;
    }

    public Receipt(int customer_id, String description, String status) {
        this.customer_id = customer_id;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
