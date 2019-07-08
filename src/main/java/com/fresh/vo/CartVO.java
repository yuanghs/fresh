package com.fresh.vo;

public class CartVO {
    private Integer cid;
    private Integer count;
    private Double price;
    private Integer pid;
    private String pname;
    private String plink;

    public CartVO(Integer cid, Integer count, Double price, Integer pid, String pname, String plink) {
        this.cid = cid;
        this.count = count;
        this.price = price;
        this.pid = pid;
        this.pname = pname;
        this.plink = plink;
    }

    public CartVO() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getPlink() {
        return plink;
    }

    public void setPlink(String plink) {
        this.plink = plink;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cid=" + cid +
                ", count=" + count +
                ", price=" + price +
                ", pid=" + pid +
                ", pname='" + pname + '\'' +
                ", plink='" + plink + '\'' +
                '}';
    }
}
