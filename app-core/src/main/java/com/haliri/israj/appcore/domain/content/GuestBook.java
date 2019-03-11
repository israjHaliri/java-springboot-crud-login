package com.haliri.israj.appcore.domain.content;

import java.sql.Date;

/**
 * Created by israjhaliri on 11/23/17.
 */
public class GuestBook {

    private String username;
    private String idGuestBook;
    private Date createDate;
    private Integer rn;
    private Integer totalCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdGuestBook() {
        return idGuestBook;
    }

    public void setIdGuestBook(String idGuestBook) {
        this.idGuestBook = idGuestBook;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getRn() {
        return rn;
    }

    public void setRn(Integer rn) {
        this.rn = rn;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GuestBook{" +
                "username='" + username + '\'' +
                ", idGuestBook='" + idGuestBook + '\'' +
                ", createDate=" + createDate +
                ", rn=" + rn +
                ", total_count=" + totalCount +
                '}';
    }
}
