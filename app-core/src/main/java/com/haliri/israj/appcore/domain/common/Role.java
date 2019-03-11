package com.haliri.israj.appcore.domain.common;

/**
 * Created by israjhaliri on 8/25/17.
 */
public class Role {

    private String id;
    private String role;
    private String userId;

    public Role() {
    }

    public Role(String id, String role, String userId) {
        this.id = id;
        this.role = role;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
