package com.epam.murodil.model.entity;

import java.math.BigDecimal;

public class Order extends AbstractEntity {
    private int id;
    private BigDecimal dosage;
    private BigDecimal amount;
    private int account_id;
    private int medicine_id;
    private BigDecimal price;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
    private Account customer;
    private Medicine medicine;

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    private String delivery_address;

    public Account getCustomer() {
        return this.customer;
    }
    public Medicine getMedicine() {
        return this.medicine;
    }
    public Order(int medicine_id, int account_id, BigDecimal amount, BigDecimal dosage,  BigDecimal total_price, String delivery_address, String status) {
        this.dosage = dosage;
        this.amount = amount;
        this.account_id = account_id;
        this.medicine_id = medicine_id;
        this.price = total_price;
        this.delivery_address = delivery_address;
        this.status = status;
    }

    public Order(int id, int medicine_id, int account_id, BigDecimal amount, BigDecimal dosage,  BigDecimal total_price, String delivery_address, String status) {
        this.id = id;
        this.dosage = dosage;
        this.amount = amount;
        this.account_id = account_id;
        this.medicine_id = medicine_id;
        this.price = total_price;
        this.delivery_address = delivery_address;
        this.status = status;
    }

    public Order(int id, int medicine_id, int account_id, BigDecimal amount, BigDecimal dosage,  BigDecimal total_price, String delivery_address, String status, Account customer, Medicine medicine) {
        this.id = id;
        this.dosage = dosage;
        this.amount = amount;
        this.account_id = account_id;
        this.medicine_id = medicine_id;
        this.price = total_price;
        this.delivery_address = delivery_address;
        this.status = status;
        this.customer = customer;
        this.medicine = medicine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getDosage() {
        return dosage;
    }

    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
