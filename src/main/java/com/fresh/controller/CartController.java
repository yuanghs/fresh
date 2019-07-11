package com.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fresh.bean.Cart;
import com.fresh.bean.Product;
import com.fresh.bean.User;
import com.fresh.service.CartService;
import com.fresh.service.ProductService;
import com.fresh.util.JwtNut;
import com.fresh.vo.CartVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private ProductService productService;

    /**
     * 根据用户id获取购物车列表
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listById")
    public JSONObject getListById(@RequestBody User user) {
        Cart cart = new Cart();
        JSONObject jsonObject = new JSONObject();
        User u = new User();

        //获取token中的信息
        String token = user.getToken();
        String id = JwtNut.getMes(token, "uid");
        Integer uid = Integer.parseInt(id);
        u.setUid(uid);

        //获取购物车列表
        cart.setUser(u);
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
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public JSONObject insert(@RequestParam(value = "token") String token,
                             @RequestParam(value = "pid") int pid,
                             @RequestParam(value = "count") int count) {
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        Cart cart = new Cart();

        //获取token信息
        String id = JwtNut.getMes(token, "uid");
        Integer uid = Integer.parseInt(id);

        //设置user对象的id
        user.setUid(uid);

        //设置cart对象中的user
        cart.setUser(user);

        //设置cart中的product对象
        Product product = new Product();
        product.setPid(pid);
        cart.setProduct(product);

        //根据pid获取商品信息，商品单价
        product = productService.getProductById(cart.getProduct());

        //向cart中添加参数
        cart.setProduct(product);
        cart.setCount(count);

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
        Cart cart = new Cart();
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        Product product = new Product();

        product.setPid(pid);

        //获取token信息
        String id = JwtNut.getMes(token, "uid");
        Integer uid = Integer.parseInt(id);
        user.setUid(uid);

        cart.setCount(count);
        cart.setProduct(product);
        cart.setUser(user);

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
        JSONObject jsonObject = new JSONObject();
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
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteAll")
    public JSONObject deleteAll(@RequestBody User user) {
        User u = new User();
        Cart cart = new Cart();
        JSONObject jsonObject = new JSONObject();
        //获取token信息
        String token = user.getToken();
        String id = JwtNut.getMes(token, "uid");
        Integer uid = Integer.parseInt(id);
        u.setUid(uid);
        cart.setUser(u);

        int i = cartService.deleteAll(cart);
        if (i != 0) {
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }

        return jsonObject;
    }
}
