package com.fresh.data_connect;

import com.fresh.bean.OrderItem;
import com.fresh.bean.Orders;
import com.fresh.mappers.OrderItemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author ygh
 * @date 2019/7/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class OrderItemDao {

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 测试 selectOrderItemByOid()
     *          > 成功！
     */
    @Test
    public void test1(){

        Orders orders = new Orders();
        orders.setOid(3);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(orders);

        List<OrderItem> orderItemList = orderItemMapper.selectOrderItemByOid(orderItem);

        orderItemList.forEach(e -> System.out.println(e));
    }

    /**
     * 测试 insertOrderItem()
     *          > 成功！
     */
    @Test
    public void test2(){


    }

    /**
     * 测试 deleteOrderItemByOid()
     *          > 成功！
     */
    @Test
    public void test3(){

        Orders orders =  new Orders();
        orders.setOid(4);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(orders);

        int a = orderItemMapper.deleteOrderItemByOid(orderItem);

        System.out.println(a);

    }






}
