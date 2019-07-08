package com.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fresh.bean.Cart;
import com.fresh.bean.Product;
import com.fresh.bean.User;
import com.fresh.service.CartService;
import com.fresh.service.ProductService;
import com.fresh.util.JwtNut;
import com.fresh.vo.CartVO;
import com.fresh.vo.ProductDetailVO;
import jdk.nashorn.internal.parser.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private ProductService productService;

    private Cart cart = new Cart();

    private JSONObject jsonObject = new JSONObject();

    /**
     * 根据用户id获取购物车列表
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listById")
    public JSONObject getListById(@RequestBody User user) {
        User u = new User();

        //获取token中的信息
        String token = user.getToken();
        String id = JwtNut.getMes(token, "uid");
        Integer uid = Integer.parseInt(id);
        u.setUid(uid);

        //获取购物车列表
        cart.setUser(u);
        System.out.println("cart: " + cart);
        List<CartVO> byUid = cartService.getByUid(cart);
        if (byUid != null) {
            jsonObject.put("code", "S000");
            jsonObject.put("list", byUid);
        } else {
            jsonObject.put("code", "S001");
        }
        return jsonObject;
    }

    /**
     * 用户添加购物车
     * @param user
     * @param cart
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public JSONObject insert(@RequestBody User user, Cart cart) {
        //获取token信息
        String token = user.getToken();
        String id = JwtNut.getMes(token, "uid");
        Integer uid = Integer.parseInt(id);
        user.setUid(uid);
        cart.setUser(user);
        //根据pid获取商品信息，商品单价
        Product product = productService.getProductById(cart.getProduct());
        cart.setProduct(product);
        //添加记录
        int i = cartService.insertCart(cart);
        if (i == 1) {
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }

        return jsonObject;
    }

    @ResponseBody
    @RequestMapping("/update")
    public JSONObject update(@RequestParam(value = "token") String token,
                             @RequestParam(value = "pid") int pid,
                             @RequestParam(value = "count") int count) {

        User user = new User();
        Product product = new Product();

        product.setPid(pid);

        //获取token信息
        String id = JwtNut.getMes(token, "uid");
        Integer uid = Integer.parseInt(id);
        user.setUid(uid);
        System.out.println(user);

        cart.setCount(count);
        cart.setProduct(product);
        cart.setUser(user);
        System.out.println(cart);

        int i = cartService.updateCart(cart);
        if (i == 1) {
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public JSONObject delete(@RequestBody Cart cart) {
        int i = cartService.delete(cart);
        if (i == 1) {
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }
        return jsonObject;
    }

    /**
     * 删除用户购物车中所有条目
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteAll")
    public JSONObject deleteAll(@RequestBody User user) {
        //获取token信息
        String token = user.getToken();
        String id = JwtNut.getMes(token, "uid");
        Integer uid = Integer.getInteger(id);
        user.setUid(uid);
        cart.setUser(user);

        int i = cartService.deleteAll(cart);
        if (i == 1) {
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }

        return jsonObject;
    }
}
