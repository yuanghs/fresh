package com.fresh.bean;

import java.util.List;

/**
 * 用户类
 *     >映射为用户表
 *
 * @author ygh
 * @date 2019/7/3
 */
public class User {

    private Integer uid;        //主键
    private String account;     //登录账户：电话号码
    private String nickname;    //用户的昵称
    private String sex;         //性别

    //保存用户地址的list，用户和地址的关系为：1——m
    private List<Location> addressList;

    public User(Integer uid, String account, String nickname, String sex, List<Location> addressList) {
        this.uid = uid;
        this.account = account;
        this.nickname = nickname;
        this.sex = sex;
        this.addressList = addressList;
    }

    public User() {
    }

    public List<Location> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Location> addressList) {
        this.addressList = addressList;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", addressList=" + addressList +
                '}';
    }
}
