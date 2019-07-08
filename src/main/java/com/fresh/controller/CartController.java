package com.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fresh.bean.Cart;
import com.fresh.bean.User;
import com.fresh.service.CartService;
import com.fresh.util.JwtNut;
import com.fresh.vo.CartVO;
import jdk.nashorn.internal.parser.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sdy
 * @date 2019/7/3
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService;

    @Resource
    private Cart cart;

    JSONObject jsonObject = new JSONObject();

    @ResponseBody
    @RequestMapping(value = "/listById")
    public JSONObject getListById(@RequestBody User user) {
        String token = user.getToken();
        String id = JwtNut.getMes(token, "uid");
        Integer uid = Integer.getInteger(id);
        user.setUid(uid);
        cart.setUser(user);
        List<CartVO> byUid = cartService.getByUid(cart);
        jsonObject.put("code", "S000");
        jsonObject.put("list", byUid);
        return jsonObject;
    }

//    @Resource
//    @RequestMapping("/insert")
//    public JSONObject insert(@RequestBody User user, Cart cart) {
//        String token = user.getToken();
//        String id = JwtNut.getMes(token, "uid");
//        Integer uid = Integer.getInteger(id);
//        user.setUid(uid);
//        cart.setUser(user);
//
//    }
}
