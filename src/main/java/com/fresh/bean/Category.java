package com.fresh.bean;

import java.util.List;

/**
 * 分类
 *   > 映射为分类表
 *
 * @author ygh
 * @date 2019/7/3
 */
public class Category {

    private Integer cid;        //主键
    private String cname;       //分类名称

    //保存分类下的商品list，分类和商品的关系：1——m
    private List<Product> productList;

    public Category(Integer cid, String cname, List<Product> productList) {
        this.cid = cid;
        this.cname = cname;
        this.productList = productList;
    }

    public Category() {
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", productList=" + productList +
                '}';
    }
}
