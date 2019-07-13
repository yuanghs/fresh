package com.fresh.service.imple;

import com.fresh.bean.*;
import com.fresh.mappers.*;
import com.fresh.service.OrderService;
import com.fresh.util.JwtNut;
import com.fresh.util.OrderUtil;
import com.fresh.vo.OrderItemVO;
import com.fresh.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LocationMapper locationMapper;

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
        // 把lid封装到Location中
        Location location = new Location();
        location.setLid(lid);

        // 定义一个订单对象，用于封装订单信息
        Orders orders = new Orders();
        orders.setOid(OrderUtil.getOrderNumber(new Date()));
        orders.setOrder_time(new Date());
        orders.setUser(user1);
        orders.setLocation(location);

        // 定义Oprice
        Double oprice = 0d;
        // 遍历 OrderItemList
        // 1.得到订单总价：加入到订单表中
        // 2.在订单条目中设置订单号
        for (OrderItem orderItem1 : orderItemList) {
            oprice += orderItem1.getSubtotal();
            orderItem1.setOrders(orders);
        }
        // 封装订单总价
        orders.setOprice(oprice);

        // 将订单插入订单表
        int i = ordersMapper.insertOrder(orders);
        int k = orderItemMapper.insertOrderItem(orderItemList);

        // 插入成功，删除购物车
        if (i !=0 && k != 0) {
            // 遍历购物车：修改库存
            // 1. 得到商品的pid
            // 2. 得到商品的数量
            // 3. 修改商品表中的对应商品的库存
            for (Cart cart2 : cartList) {
                Product product = new Product();
                // 得到商品pid
                Integer pid = cart2.getProduct().getPid();
                // 得到商品数量
                Integer count = cart2.getCount();
                // 封装
                product.setInventory(count);
                product.setPid(pid);
                // 修改库存
                productMapper.updateInventoryByPrimaryKey(product);
            }
            // 清空购物车
            cartMapper.deleteAllProductsByUid(cart);
            return "S000";
        } else {
            return "S001";
        }
    }

    /**
     * 得到用户所有的订单及对应订单下的订单条目
     *
     * @param user
     * @return
     */
    @Override
    public Map<Integer, Object> getOrderItem(User user) {
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

        Map<Integer, Object> map = new HashMap<>();

        // 封装orderVO的list
        List<OrderVO> orderVOList = new ArrayList<>();

        // 通过uid查询出所有的订单
        List<Orders> ordersList = ordersMapper.selectOrdersByUid(orders);

        // 非空进入
        if (ordersList.size() != 0) {
            // 遍历ordersList，得到所有的订单号，同时得到每个订单下对应的订单条目
            for(Orders orders1 : ordersList){
                // 新建一个OrderVO对象用来封装信息
                OrderVO orderVO = new OrderVO();
                // 设置订单号
                orderVO.setOid(orders1.getOid());
                // 设置交易时间
                orderVO.setDate(OrderUtil.getOrderTime(orders1.getOrder_time()));
                // 设置订单价格
                orderVO.setOprice(orders1.getOprice());
                // 设置收件人姓名
                String userName = userMapper.selectUserByPrimaryKey(user1).getNickname();
                orderVO.setUserName(userName);
                // 设置收件人地址
                String address = locationMapper.selectByPrimaryKey(orders1.getLocation()).getAddress();
                orderVO.setAddress(address);

                // 使用OrderItem封装
                OrderItem orderItem = new OrderItem();
                orderItem.setOrders(orders1);
                // 通过订单号查询出订单条目
                List<OrderItem> orderItemList = orderItemMapper.selectOrderItemByOid(orderItem);

                // 用于封装OrderItemVO的list
                List<OrderItemVO> orderItemVOList = new ArrayList<>();

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
                    orderItemVO.setPid(orderItem1.getProduct().getPid());
                    // 填充到orderItemVOList
                    orderItemVOList.add(orderItemVO);
                }
                // 将orderItemVOList封装到OrderVO中
                orderVO.setOrderItemVOList(orderItemVOList);
                // 封装到 orderVOList 中
                orderVOList.add(orderVO);
            }
            map.put(0, orderVOList);
        } else {
            map.put(0, "S000");     // 订单为空
        }
        return map;
    }
}
