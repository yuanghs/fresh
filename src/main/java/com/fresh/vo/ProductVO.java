package com.fresh.vo;

/**
 * @author sdy
 */
public class ProductVO {
    private Integer pid;
    private String pname;
    private Double price;
    private String plink;

    public ProductVO(Integer pid, String pname, Double price, String plink) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.plink = plink;
    }

    public ProductVO() {
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

    public String getPlink() {
        return plink;
    }

    public void setPlink(String plink) {
        this.plink = plink;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", plink='" + plink + '\'' +
                '}';
    }
}
