package com.fresh.service;


import com.fresh.bean.Orders;
import com.fresh.bean.User;
import com.fresh.vo.OrderItemVO;

import java.util.List;
import java.util.Map;

/**
 * @author ygh
 * @date 2019/7/3
 */
public interface OrderService {

    /**
     * 添加订单到数据库
     * @param user
     * @param lid
     * @return
     */
   String addOrder(User user, Integer lid);

    /**
     * 展示订单条目
     * @param user
     * @return
     */
    Map<Integer, Object> getOrderItem(User user);

}
