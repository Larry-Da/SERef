package org.qmbupt.grp105.backend.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * class for customer
 * @author Lingsong Feng
 * @version 5.3
 */
public class Customer {

    @JSONField(name = "cusId")
    public String cusId;

    @JSONField(name = "name")
    public String name;

    @JSONField(name = "password")
    public String password;

    @JSONField(name = "phoneNo")
    public String phoneNo;

    @JSONField(name = "email")
    public String email;

    @JSONField(name = "gender")
    public String gender;

    @JSONField(name = "dateOfBirth", format = "yyyy-MM-dd")
    public Date dateOfBirth;

    @JSONField(name = "level")
    public int level;

    @JSONField(name = "expireDate", format = "yyyy-MM-dd")
    public Date expireDate;

    @JSONField(name = "balance")
    public int balance;

    @JSONField(name = "xp")
    public int xp;

    @JSONField(name = "videosHistory")
    public ArrayList<String> videosHistory;

    @JSONField(name = "bookedSessions")
    public  ArrayList<String> bookedSessions;

    @JSONField(name = "favoriteVideos")
    public ArrayList<String> favoriteVideos;

    public org.qmbupt.grp105.Entity.Customer converter() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String expireDateF = sdf.format(this.expireDate);
        return new org.qmbupt.grp105.Entity.Customer(this.cusId, 10, this.name, this.password,
                this.phoneNo, this.email, this.gender.charAt(0), this.dateOfBirth, this.level + "", expireDateF,
                this.balance, this.xp, this.videosHistory, this.bookedSessions, this.favoriteVideos);
    }
}
