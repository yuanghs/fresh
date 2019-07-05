package com.fresh.bean;

/**
 * 地址类
 *     > 映射为地址表
 *
 * @author ygh
 * @date 2019/7/3
 */
public class Location {

    private Integer lid;        //主键
    private String address;     //用户的地址
    private User user;         //用户引用：包含用户主键

    public Location(Integer lid, String address, User user) {
        this.lid = lid;
        this.address = address;
        this.user = user;
    }

    public Location() {
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lid=" + lid +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }
}
