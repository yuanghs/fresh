package com.fresh.vo;

/**
 * 对地址信息进行封装返回
 *
 * @author ygh
 * @date 2019/7/8
 */
public class UserVO {

    private String sex;             //用户的性别
    private String nickname;        //用户的昵称
    private String phone;           //用户的电话：phone = account

    public UserVO() {
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
