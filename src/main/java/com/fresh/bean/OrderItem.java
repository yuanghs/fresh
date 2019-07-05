package com.fresh.bean;

/**
 * 订单详情类
 *       > 映射成订单表
 *
 * @author ygh
 * @date 2019/7/3
 */
public class OrderItem {

    private Integer iid;            //主键
    private Double subtotal;        //商品的价格(单件或多件)
    private Integer count;          //商品的件数
    private Orders orders;          //订单引用：主键
    private Product product;        //商品引用：主键

    public OrderItem(Integer iid, Double subtotal, Integer count, Orders orders, Product product) {
        this.iid = iid;
        this.subtotal = subtotal;
        this.count = count;
        this.orders = orders;
        this.product = product;
    }

    public OrderItem() {
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "iid=" + iid +
                ", subtotal=" + subtotal +
                ", count=" + count +
                ", orders=" + orders +
                ", product=" + product +
                '}';
    }
}
