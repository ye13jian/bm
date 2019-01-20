package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 礼品兑换
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-03
 */
@TableName("tbl_gift_exchange")
public class GiftExchange extends Model<GiftExchange> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String date;
    private String time;
    private String openid;
    /**
     * 礼品id
     */
    private String giftid;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 分值
     */
    private Integer score;
    /**
     * 留言
     */
    private String message;

    /**
     * 收货信息
     */
    private String province;
    private String city;
    private String country;
    private String detailaddress;
    private String fullname;
    private String mobile;

    /**
     * 快递信息
     */
    private String express;
    private String expressno;
    private String expressdate;

    /**
     * 状态1已下单，2已发货，0已关闭
     */
    private Integer status;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGiftid() {
        return giftid;
    }

    public void setGiftid(String giftid) {
        this.giftid = giftid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getExpressno() {
        return expressno;
    }

    public void setExpressno(String expressno) {
        this.expressno = expressno;
    }

    public String getExpressdate() {
        return expressdate;
    }

    public void setExpressdate(String expressdate) {
        this.expressdate = expressdate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GiftExchange{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", openid='" + openid + '\'' +
                ", giftid='" + giftid + '\'' +
                ", count=" + count +
                ", score=" + score +
                ", message='" + message + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", detailaddress='" + detailaddress + '\'' +
                ", fullname='" + fullname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", express='" + express + '\'' +
                ", expressno='" + expressno + '\'' +
                ", expressdate='" + expressdate + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}
