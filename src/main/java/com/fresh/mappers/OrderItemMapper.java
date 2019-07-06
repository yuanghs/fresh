package com.fresh.mappers;

import com.fresh.bean.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemMapper {

    /**
     * 根据订单主键删除订单详情
     * @param orderItem
     * @return
     */
    int deleteOrderItemByOid(OrderItem orderItem);

    /**
     * 插入订单详情
     *      订单详情是一个list
     * @param list
     */
    int insertOrderItem(List<OrderItem> list);

    /**
     * 根据订单主键返回订单详情
     * @param orderItem
     * @return
     */
    List<OrderItem> selectOrderItemByOid(OrderItem orderItem);

}