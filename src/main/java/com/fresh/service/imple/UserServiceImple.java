package com.fresh.service.imple;

import com.fresh.bean.Location;
import com.fresh.bean.User;
import com.fresh.mappers.LocationMapper;
import com.fresh.mappers.UserMapper;
import com.fresh.service.UserService;
import com.fresh.util.JwtNut;
import com.fresh.util.PetName;
import com.fresh.util.SendSms;
import com.fresh.vo.LocationVO;
import com.fresh.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ygh
 * @date 2019/7/3
 */
@Service("userService")
public class UserServiceImple implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LocationMapper locationMapper;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public String login(User user) {
        // 随机生成也一个昵称
        String nickname = PetName.create();
        System.out.println(nickname);
        user.setNickname(nickname);
        user.setSex("女");

        // 初始化token签名
        JwtNut jwtNut = new JwtNut();
        jwtNut.init("heeeyou", "chestnut&youyinnn");

        //第一次登录：需要把电话，昵称，性别插入数据库
        if (userMapper.selectUserByAccount(user) == null) {
            int i = userMapper.insertUser(user);
            if (i == 1) {
                // 从数据库查询出用户id，加密成token串，返回
                User userData = userMapper.selectUserByAccount(user);
                // 设置token中的参数
                jwtNut.setClaim("uid", userData.getUid().toString());
                // 生成token
                String token = jwtNut.getToken();
                System.out.println(token);
                return "P000" + token;      // 成功！
            } else {
                return "P001";              // 失败！
            }
        } else {
            // 从数据库查询出用户id，加密成token串，返回
            User userData = userMapper.selectUserByAccount(user);
            // 设置token中的参数
            jwtNut.setClaim("uid", userData.getUid().toString());
            // 生成token
            String token = jwtNut.getToken();
            System.out.println(token);
            return "P000" + token;          // 成功！
        }
    }

    /**
     * 发送验证码
     * @param user
     * @return
     */
    @Override
    public String getVerifyCode(User user) {
        // 生成随机验证码
        String verifyCode = SendSms.verifyCreator();
        // 添加要发送的手机号
        SendSms.sendSms(user.getAccount(), verifyCode);
        // 返回验证码
        return verifyCode;
    }

    /**
     * 添加地址
     * @param address
     * @param token
     * @return
     */
    @Override
    public String addLocation(String token, String address) {
        // 解析token中的用户uid
        String uidString = JwtNut.getMes(token, "uid");
        // 转为Inter个
        Integer uid = Integer.parseInt(uidString);
        // 用User对象封装uid
        User userData = new User();
        userData.setUid(uid);

        // 用Location对象封装
        Location locationData = new Location();
        locationData.setUser(userData);
        locationData.setAddress(address);
        // 添加地址
        int a = locationMapper.insertLocation(locationData);
        if (a == 1) {
            return "S000";  //成功！
        } else {
            return "S0001"; //失败
        }
    }

    /**
     * 获取用户的详细信息
     * @param user
     * @return
     */
    @Override
    public List<LocationVO> getUserLocationList(User user) {
        // 解析token中的用户uid
        String uidString = JwtNut.getMes(user.getToken(), "uid");
        // 转为Inter
        Integer uid = Integer.parseInt(uidString);
        // 用User对象封装uid
        User user1 = new User();
        user1.setUid(uid);

        // 准备一个VO对象的list，用来填充
        List<LocationVO> listVo = new ArrayList<>();

        // 根据用户id得到用户信息
        User userData = userMapper.selectUserByPrimaryKey(user1);

        // 得到用户的地址列表
        List<Location> listLocation = userData.getAddressList();

        System.out.println(listLocation);

        // 新建 listLocation.size() 个UserVo对象
        for (int i = 0; i < listLocation.size(); i++) {
            listVo.add(new LocationVO());
        }

        // 循环填充listVo
        for (int i = 0; i < listLocation.size(); i++) {
            listVo.get(i).setLid(listLocation.get(i).getLid());
            listVo.get(i).setAddress(listLocation.get(i).getAddress());
            listVo.get(i).setNickname(userData.getNickname());
            listVo.get(i).setPhone(userData.getAccount());
        }
        // 返回地址列表
        return listVo;
    }

    /**
     * 得到用户的详细信息
     * @param user
     * @return
     */
    @Override
    public UserVO getUserInformation(User user) {
        // 解析token中的用户uid
        String uidString = JwtNut.getMes(user.getToken(), "uid");
        // 转为Inter个
        Integer uid = Integer.parseInt(uidString);
        // 用User对象封装uid
        User user1 = new User();
        user1.setUid(uid);

        // 通过uid查询出用户的信息
        User userData = userMapper.selectUserByPrimaryKey(user1);

        System.out.println(userData);

        // 封装到 UserVO
        UserVO userVO = new UserVO();
        userVO.setPhone(userData.getAccount());
        userVO.setNickname(userData.getNickname());
        userVO.setSex(userData.getSex());

        return userVO;
    }

}
