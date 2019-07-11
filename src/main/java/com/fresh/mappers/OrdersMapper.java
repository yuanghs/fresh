package com.fresh.mappers;

import com.fresh.bean.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单接口
 */
@Repository
public interface OrdersMapper {

    /**
     * 根据主键修改
     * @param orders
     * @return
     */
    int deleteOrderByOid(Orders orders);

    /**
     * 根据订单号删除订单详情
     * @param orders
     * @return
     */
    int deleteOrderItemByOid(Orders orders);

    /**
     * 生成订单：向orders表中添加一条记录
     * @param orders
     * @return
     */
    int insertOrder(Orders orders);

    /**
     * 根据用户主键查询出所有的订单
     * @param orders
     * @return
     */
    List<Orders> selectOrdersByUid(Orders orders);

    /**
     * 根据订单主键查询出所有的订单详情
     * @param orders
     * @return
     */
    Orders selectByOid(Orders orders);

    /**
     * 根据订单主键查询出所有的订单详情
     * @return
     */
    int selectMaxOid();


}