package com.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fresh.bean.User;
import com.fresh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ygh
 * @date 2019/7/3
 */
@Controller
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    /**
     * 提交订单
     * @param
     * @return
     */
    @RequestMapping(value = "/subOrder", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject subOrder(@RequestParam(value = "token") String token,
                               @RequestParam(value = "lid") Integer lid) {

        JSONObject jsonObject = new JSONObject();

        User user = new User();
        user.setToken(token);

        String codeData = orderService.addOrder(user, lid);

        jsonObject.put("code", codeData);

        return jsonObject;
    }

    /**
     * 获取订单列表
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/orderList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject orderList(@RequestBody User user) {

        JSONObject jsonObject = new JSONObject();

        Map<Integer, Object> map = orderService.getOrderItem(user);

        if (map.get(0).equals("S000")) {
            // 返回S000，说明该用户还没有订单
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("list", map.get(0));
        }

        return jsonObject;
    }

}
