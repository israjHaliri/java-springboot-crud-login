package com.haliri.israj.appcore.domain.content;

import java.sql.Date;

/**
 * Created by israjhaliri on 11/21/17.
 */
public class Profile {

    private String address;
    private String phone;
    private String email;
    private Float lat;
    private Float lon;
    private Date createDate;
    private Date updateDate;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "address='" + address + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", creataDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
