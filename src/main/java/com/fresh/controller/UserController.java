package com.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fresh.bean.User;
import com.fresh.service.UserService;
import com.fresh.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ygh
 * @date 2019/7/3
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录操作
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        // 得到返回串
        String returnString = userService.login(user);

        // 如果不是4，说明带有token串
        // 分割串，然后分别添加到 jsonObject 中返回
        if (returnString.length() != 4) {
            String codeString = returnString.substring(0, 4);
            String tokenString = returnString.substring(4);
            jsonObject.put("code", codeString);
            jsonObject.put("token", tokenString);
        // 如果是4，说明登录失败
        } else {
            jsonObject.put("code", returnString);
        }
        // 返回信息给前端
        return jsonObject;
    }

    /**
     * 添加用户地址
     * @return
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject addAddress(@RequestParam(value = "token") String token,
                                 @RequestParam(value = "address") String address) {
        JSONObject jsonObject = new JSONObject();

        String codeString = userService.addLocation(token, address);

        jsonObject.put("code", codeString);

        return jsonObject;
    }

    /**
     * 获取验证码
     * @param user
     * @return
     */
    @RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject sendVerifyCode(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();

        // 得到随机验证码
        String verifyCode = userService.getVerifyCode(user);

        jsonObject.put("code", "S000");
        jsonObject.put("verify", verifyCode);

        return jsonObject;
    }

    /**
     * 获取用户详细信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getById(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();

        UserVO userVO = userService.getUserInformation(user);

        jsonObject.put("user", userVO);

        return jsonObject;
    }

    /**
     * 获取用户地址列表
     * @param user
     * @return
     */
    @RequestMapping(value = "/addressList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addressList(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();

        Map<Integer, Object> map = new HashMap<>();

        map = userService.getUserLocationList(user);

        if (map.get(0).equals("P001")) {
            jsonObject.put("code", "P001");
            return jsonObject;
        } else {
            jsonObject.put("locationList", map.get(0));
            return jsonObject;
        }
    }
}
