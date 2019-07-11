package com.fresh.service.imple;

import com.fresh.bean.*;
import com.fresh.mappers.CartMapper;
import com.fresh.mappers.OrderItemMapper;
import com.fresh.mappers.OrdersMapper;
import com.fresh.mappers.ProductMapper;
import com.fresh.service.OrderService;
import com.fresh.util.JwtNut;
import com.fresh.vo.OrderItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ygh
 * @date 2019/7/3
 */
@Service("orderService")
public class OrderServiceImple implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 插入订单
     *
     * @param user
     * @param lid
     * @return
     */
    @Override
    public String addOrder(User user, Integer lid) {
        // 解析token中的用户uid
        String uidString = JwtNut.getMes(user.getToken(), "uid");
        // 转为Inter
        Integer uid = Integer.parseInt(uidString);
        // 用User对象封装uid
        User user1 = new User();
        user1.setUid(uid);

        // 使用Cart封装
        Cart cart = new Cart();
        cart.setUser(user1);

        // 通过uid查询购物车中的条目
        List<Cart> cartList = cartMapper.selectProductByUid(cart);

        // 准备一个OrderItemList
        List<OrderItem> orderItemList = new LinkedList<>();

        // 遍历cartList
        // 1. 取出pid，通过pid查询数据库得到单价
        // 2. 取出count，得到subtotal
        // 3. 每次产生一个OrderItem对象，将subtotal，count，lid封装
        for (Cart cart1 : cartList) {
            OrderItem orderItem = new OrderItem();
            // 得到商品单价
            Product product = productMapper.selectProductByPrimaryKey(cart1.getProduct());
            // 得到subtotal
            Double subtotal = product.getPrice() * cart1.getCount();
            // 填充到OrderItem
            orderItem.setProduct(cart1.getProduct());
            orderItem.setCount(cart1.getCount());
            orderItem.setSubtotal(subtotal);
            // 加入到OrderItemList
            orderItemList.add(orderItem);
        }

        // 得到Oid
        Integer oid = ordersMapper.selectMaxOid() + 1;

        // 使用Order封装
        Orders orders = new Orders();
        orders.setOid(oid);

        // 定义Oprice
        Double oprice = 0d;

        // 遍历 OrderItemList
        // 1.得到订单总价：加入到订单表中
        // 2.在订单条目中设置订单号
        for (OrderItem orderItem1 : orderItemList) {
            oprice += orderItem1.getSubtotal();
            orderItem1.setOrders(orders);
        }

        // 把lid封装到Location中
        Location location = new Location();
        location.setLid(lid);

        // 定义一个订单对象，用于封装订单信息
        Orders orders1 = new Orders();
        orders1.setOid(oid);
        orders1.setOprice(oprice);
        orders1.setOrder_time(new Date());
        orders1.setUser(user1);
        orders1.setLocation(location);

        // 将订单插入订单表
        int i = ordersMapper.insertOrder(orders1);
        int k = orderItemMapper.insertOrderItem(orderItemList);

        // 插入成功，删除购物车
        if (i !=0 && k != 0) {
            cartMapper.deleteAllProductsByUid(cart);
            return "S000";
        } else {
            return "S001";
        }
    }

    /**
     * 得到订单条目
     *
     * @param user
     * @return
     */
    @Override
    public Map<Integer, Object> getOrderItem(User user) {

        Map<Integer, Object> map = new HashMap<>();

        List<OrderItemVO> orderItemVOList = new ArrayList<>();

        // 解析token中的用户uid
        String uidString = JwtNut.getMes(user.getToken(), "uid");
        // 转为Inter
        Integer uid = Integer.parseInt(uidString);
        // 用User对象封装uid
        User user1 = new User();
        user1.setUid(uid);

        // 用Orders封装用户的uid
        Orders orders = new Orders();
        orders.setUser(user1);

        // 通过uid查询出所有的订单
        List<Orders> ordersList = ordersMapper.selectOrdersByUid(orders);

        // 非空进入
        if (ordersList.size() != 0) {
            // 遍历ordersList，得到所有的订单号，每个订单下对应的订单条目
            for(Orders orders1 : ordersList){
                // 使用OrderItem封装
                OrderItem orderItem = new OrderItem();
                orderItem.setOrders(orders1);
                // 通过订单号查询出订单条目
                List<OrderItem> orderItemList = orderItemMapper.selectOrderItemByOid(orderItem);

                // 遍历orderItemList
                // 1. 得到subtotal
                // 2. 得到商品的pname, plink
                for(OrderItem orderItem1 : orderItemList) {
                    OrderItemVO orderItemVO = new OrderItemVO();
                    // 得到subtotal
                    Double subtotal = orderItem1.getSubtotal();
                    // 得到商品
                    Product product = productMapper.selectProductByPrimaryKey(orderItem1.getProduct());
                    // 填充orderItemVO
                    orderItemVO.setSubtotal(subtotal);
                    orderItemVO.setPname(product.getPname());
                    orderItemVO.setPlink(product.getPlink());
                    // 填充到orderItemVOList
                    orderItemVOList.add(orderItemVO);
                }
            }
            map.put(0, orderItemVOList);
        } else {
            map.put(0, "S000");
        }
        return map;
    }
}
