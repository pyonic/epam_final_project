package com.epam.murodil.model.entity;

import com.github.slugify.Slugify;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Medicine extends AbstractEntity {
    private Slugify slg = Slugify.builder().underscoreSeparator(true).build();
    private int id;
    private boolean needReceipt = false;
    private String slug = "";
    private String title;
    private String description;
    private BigDecimal price;

    private Timestamp created_at;
    private Timestamp updated_at;


    public Medicine(int id, String title, String description, BigDecimal price,String slug, Boolean need_receipt) {
        this.id = id;
        this.needReceipt = need_receipt;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Medicine(String title, String description, BigDecimal price,String slug, Boolean need_receipt, Timestamp created_at, Timestamp updated_at) {
        this.needReceipt = need_receipt;
        this.slug = this.slg.slugify(title);
        this.title = title;
        this.description = description;
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Medicine(int id, String title, String description, BigDecimal price,String slug, Boolean need_receipt, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.needReceipt = need_receipt;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.price = price;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Medicine(String title, String description, BigDecimal price,String slug, Boolean need_receipt) {
        this.needReceipt = need_receipt;
        this.slug = this.slg.slugify(title);
        this.title = title;
        this.description = description;
        this.price = price;
    }
    public int getId() {
        return id;
    }

    public void setId(int uuid) {
        this.id = id;
    }

    public boolean isNeedReceipt() {
        return needReceipt;
    }

    public void setNeedReceipt(boolean need_receipt) {
        this.needReceipt = need_receipt;
    }
    public boolean getNeedReceipt() {
        return this.needReceipt;
    }
    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
