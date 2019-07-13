package com.fresh.data_connect;

import com.fresh.bean.*;
import com.fresh.mappers.OrderItemMapper;
import com.fresh.mappers.OrdersMapper;
import com.fresh.util.OrderUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ygh
 * @date 2019/7/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class OrderDao {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 测试 selectOrdersByUid()
     *             > 成功！
     */
    @Test
    public void test1() {
        User user = new User();
        user.setUid(1);

        Orders orders = new Orders();
        orders.setUser(user);

        List<Orders> list = ordersMapper.selectOrdersByUid(orders);

//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getLocation());
//        }

        list.forEach(e -> System.out.println(e));

    }

    /**
     * 测试 insertOrder()
     *             > 成功！
     */
    @Test
    public void test2() {

        User user = new User();
        user.setUid(1);

        Location location = new Location();
        location.setLid(2);

        Product product1 = new Product();
        product1.setPid(3);

        Product product2 = new Product();
        product2.setPid(5);

        Product product3 = new Product();
        product3.setPid(6);

        Product product4 = new Product();
        product4.setPid(5);


        // 把订单插入到订单表
        Orders orders = new Orders();
        orders.setOid(OrderUtil.getOrderNumber(new Date()));
        orders.setUser(user);
        orders.setLocation(location);
        orders.setOprice(456.8);
        orders.setOrder_time(new Date());


        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrders(orders);
        orderItem1.setCount(3);
        orderItem1.setSubtotal(13.6d);
        orderItem1.setProduct(product1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setOrders(orders);
        orderItem2.setCount(12);
        orderItem2.setSubtotal(176.5d);
        orderItem2.setProduct(product2);

        OrderItem orderItem3 = new OrderItem();
        orderItem3.setOrders(orders);
        orderItem3.setCount(2);
        orderItem3.setSubtotal(17.5d);
        orderItem3.setProduct(product3);

        OrderItem orderItem4 = new OrderItem();
        orderItem4.setOrders(orders);
        orderItem4.setCount(12);
        orderItem4.setSubtotal(145d);
        orderItem4.setProduct(product4);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);
        orderItemList.add(orderItem3);
        orderItemList.add(orderItem4);

        orders.setOrderItemList(orderItemList);

        int a = ordersMapper.insertOrder(orders);

        int b = orderItemMapper.insertOrderItem(orderItemList);

        System.out.println(a);

        System.out.println(b);


    }
    /**
     * 测试 insertOrder()
     *             > 成功！
     */
    @Test
    public void test4() {

        User user = new User();
        user.setUid(1);

        Location location = new Location();
        location.setLid(2);

        // 把订单插入到订单表
        Orders orders = new Orders();
        orders.setUser(user);
        orders.setLocation(location);
        orders.setOprice(456.8);
        orders.setOrder_time(new Date());


        int a = ordersMapper.insertOrder(orders);


        System.out.println(a);

    }

    /**
     * 测试 selectMaxOid()
     *           > 成功！
     */
    @Test
    public void test3() {

//        int a = ordersMapper.selectMaxOid();

//        System.out.println(a);
    }

}
