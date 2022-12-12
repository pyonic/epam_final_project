package com.epam.murodil.model.entity;

public class Account extends AbstractEntity {
    private int id;
    private String role;
    private String last_name;
    private String email;
    private String phone;
    private String password;

    private String first_name;
    public static final String[] ACCOUNT_ROLES = new String[] {"CUSTOMER", "PHARMACIST", "DOCTOR", "ADMIN"};

    public Account(int id, String first_name, String last_name, String email, String phone, String password, String role) {
        this.id = id;
        this.role = role;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.first_name = first_name;
    }

    public Account(String first_name, String last_name, String email, String phone, String password, String role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone != null ? phone : "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFullName() {
        return this.first_name + " " + this.last_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account user = (Account) o;
        return user.getId() == this.getId();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(this.getId());
        sb.append(", email=").append(this.getEmail()).append('\'');
        sb.append(", password=").append(this.getPassword()).append('\'');
        sb.append(", role=").append(this.getRole()).append('\'');
        sb.append(", phone=").append(this.getPhone()).append('\'');
        return sb.toString();
    }
}
