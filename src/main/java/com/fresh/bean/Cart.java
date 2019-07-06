package com.fresh.bean;

import java.util.List;

/**
 * 购物车类
 *      > 映射为购物车表
 *
 * @author ygh
 * @date 2019/7/3
 */
public class Cart {
    private Integer cid;            //主键
    private Integer count;          //商品件数
    private Product product;        //商品引用：主键
    private User user;              //用户引用：主键


    public Cart(Integer cid, Integer count, Product product, User user) {
        this.cid = cid;
        this.count = count;
        this.product = product;
        this.user = user;
    }

    public Cart() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", count=" + count +
                ", product=" + product +
                ", user=" + user +
                '}';
    }
}
