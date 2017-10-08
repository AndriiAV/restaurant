package com.andrii.restaurant.model;

public class Admin {
    private Long id;
    private String login;
    private String password;

    public Long getId() {
        return id;
    }

    public Admin setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Admin setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (login != null ? !login.equals(admin.login) : admin.login != null) return false;
        return password != null ? password.equals(admin.password) : admin.password == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
