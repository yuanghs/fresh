package com.fresh.vo;

/**
 * @author sdy
 */
public class ProductDetailVO {
    private Integer pid;
    private String pname;
    private Double pice;
    private String pinfo;
    private String plink;
    private Integer inventory;
    private Integer cid;
    private String cname;

    public ProductDetailVO(Integer pid, String pname, Double pice, String pinfo, String plink, Integer inventory, Integer cid, String cname) {
        this.pid = pid;
        this.pname = pname;
        this.pice = pice;
        this.pinfo = pinfo;
        this.plink = plink;
        this.inventory = inventory;
        this.cid = cid;
        this.cname = cname;
    }

    public ProductDetailVO() {
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

    public Double getPice() {
        return pice;
    }

    public void setPice(Double pice) {
        this.pice = pice;
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
        return "ProductDetailVO{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", pice=" + pice +
                ", pinfo='" + pinfo + '\'' +
                ", plink='" + plink + '\'' +
                ", inventory=" + inventory +
                ", cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
