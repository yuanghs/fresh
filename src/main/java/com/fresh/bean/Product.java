package com.fresh.bean;

/**
 * 商品类
 *     > 映射为商品表
 *
 * @author ygh
 * @date 2019/7/3
 */
public class Product {

    private Integer pid;        //主键
    private String pname;       //商品名称
    private Double price;       //商品单价(单位：元/件)
    private String pinfo;       //商品详情
    private String plink;       //图片链接
    private Integer inventory;  //商品库存
    private Category category;  //分类的引用：获取分类主键

    public Product(Integer pid, String pname, Double price, String pinfo, String plink, Integer inventory, Category category) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.pinfo = pinfo;
        this.plink = plink;
        this.inventory = inventory;
        this.category = category;
    }

    public Product() {
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPinfo() {
        return pinfo;
    }

    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    public String getPlink() {
        return plink;
    }

    public void setPlink(String plink) {
        this.plink = plink;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", pinfo='" + pinfo + '\'' +
                ", plink='" + plink + '\'' +
                ", inventory=" + inventory +
                ", category=" + category +
                '}';
    }
}
