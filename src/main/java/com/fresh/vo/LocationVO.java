package com.fresh.vo;

/**
 * 对地址信息进行封装返回
 *
 * @author ygh
 * @date 2019/7/8
 */
public class LocationVO {

    private Integer lid;            //地址id
    private String address;         //用户的地址
    private String nickname;        //用户的昵称
    private String phone;           //用户的电话：phone = account

    public LocationVO() {
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
