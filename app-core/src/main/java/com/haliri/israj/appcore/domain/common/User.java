package com.haliri.israj.appcore.domain.common;

import java.util.List;

/**
 * Created by israjhaliri on 8/27/17.
 */
public class User {

    String id;
    String username;
    String password;
    Boolean enable;
    String token;
    private List<Role> roles;

    public User() {
    }

    public User(String id, String username, String password, Boolean enable, String token, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enable = enable;
        this.token = token;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enable +
                ", token='" + token + '\'' +
                ", roles=" + roles +
                '}';
    }
}
