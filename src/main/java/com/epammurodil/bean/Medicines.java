package com.epammurodil.bean;

import com.github.slugify.Slugify;

import java.math.BigDecimal;
import java.util.UUID;

public class Medicines {
    private Slugify slg = Slugify.builder().underscoreSeparator(true).build();
    private String uuid;
    private boolean needReceipt = false;
    private String slug = "";
    private String title;
    private String description;
    private BigDecimal price;
    private Float quantity;

    public Medicines(boolean need_receipt, String title, String description, BigDecimal price, Float quantity) {
        this.uuid = UUID.randomUUID().toString();
        this.needReceipt = need_receipt;
        this.slug = this.slg.slugify(title);
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isNeedReceipt() {
        return needReceipt;
    }

    public void setNeedReceipt(boolean need_receipt) {
        this.needReceipt = need_receipt;
    }

    public String getSlug() {
        return slug;
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

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }
}
