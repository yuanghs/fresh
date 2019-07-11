package com.fresh.service;

import com.fresh.bean.User;
import com.fresh.vo.UserVO;

import java.util.Map;


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
     */
    String login(User user);

    /**
     * 获取收集验证码
     * @param user
     * @return
     */
    String getVerifyCode(User user);

    /**
     * 添加地址
     * @param address
     * @return
     */
    public String addLocation(String token, String address);

    /**
     * 获取用户地址列表
     * @param user
     * @return
     */
    Map<Integer, Object> getUserLocationList(User user);


    /**
     * 获取用户信息
     * @param user
     * @return
     */
  UserVO getUserInformation(User user);

}
