package com.epammurodil.bean;

public class AcountType {
    private Integer id;
    private String name;

    public AcountType(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
