package com.fresh.service;

import com.fresh.bean.Location;
import com.fresh.bean.User;


/**
 * @author ygh
 * @date 2019/7/3
 */
public interface UserService {

    /**
     * 登录操作
     *
     * @param user
     * @return
     *      0：未插入数据
     *      1：插入数据成功
     */
    int login(User user);

    /**
     * 修改用户的地址
     * @param user
     * @param location
     * @return
     *      0：修改失败
     *      1：修改成功
     */
   int updateLocation(User user, Location location);

    /**
     * 修改用户的基本信息
     * @param user
     * @return
     *      0：修改失败
     *      1：修改成功
     */
   int updateInformation(User user);

}
