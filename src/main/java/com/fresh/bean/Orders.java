package com.fresh.bean;

import java.util.Date;
import java.util.List;

/**
 * 订单类
 *    >映射为订单表
 *
 * @author ygh
 * @date 2019/7/3
 */
public class Orders {

    private String oid;            //主键：日期加随机码
    private Date  order_time;       //下单施加
    private Double oprice;          //订单的总价
    private User user;              //用户引用：主键
    private Location location;      //地址引用：主键

    // 保存订单详情的list，订单与订单详情的关系为：1——m
    private List<OrderItem> orderItemList;

    public Orders(String oid, Date order_time, Double oprice, User user, Location location, List<OrderItem> orderItemList) {
        this.oid = oid;
        this.order_time = order_time;
        this.oprice = oprice;
        this.user = user;
        this.location = location;
        this.orderItemList = orderItemList;
    }

    public Orders() {
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Double getOprice() {
        return oprice;
    }

    public void setOprice(Double oprice) {
        this.oprice = oprice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", order_time=" + order_time +
                ", oprice=" + oprice +
                ", user=" + user +
                ", location=" + location +
                ", orderItemList=" + orderItemList +
                '}';
    }
}
