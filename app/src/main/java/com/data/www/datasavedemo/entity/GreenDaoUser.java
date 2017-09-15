package com.data.www.datasavedemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by qwh on 2017/9/15.
 */
@Entity
public class GreenDaoUser {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String address;
    private String phone;

    @Generated(hash = 1979061328)
    public GreenDaoUser(Long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Generated(hash = 83249558)
    public GreenDaoUser() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
