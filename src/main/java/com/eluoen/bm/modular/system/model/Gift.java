package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 商城礼品管理
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-03
 */
@TableName("tbl_gift")
public class Gift extends Model<Gift> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 参数
     */
    private String attributes;
    /**
     * 描述
     */
    private String describes;
    private String imgurl1;
    private String imgurl2;
    private String imgurl3;
    private String imgurl4;
    /**
     * 分值
     */
    private Integer score;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 销量
     */
    private Integer sale;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     */
    private Integer status;
    private String createdate;
    private String createtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getImgurl1() {
        return imgurl1;
    }

    public void setImgurl1(String imgurl1) {
        this.imgurl1 = imgurl1;
    }

    public String getImgurl2() {
        return imgurl2;
    }

    public void setImgurl2(String imgurl2) {
        this.imgurl2 = imgurl2;
    }

    public String getImgurl3() {
        return imgurl3;
    }

    public void setImgurl3(String imgurl3) {
        this.imgurl3 = imgurl3;
    }

    public String getImgurl4() {
        return imgurl4;
    }

    public void setImgurl4(String imgurl4) {
        this.imgurl4 = imgurl4;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Gift{" +
        "id=" + id +
        ", name=" + name +
        ", attributes=" + attributes +
        ", describes=" + describes +
        ", imgurl1=" + imgurl1 +
        ", imgurl2=" + imgurl2 +
        ", imgurl3=" + imgurl3 +
        ", imgurl4=" + imgurl4 +
        ", score=" + score +
        ", stock=" + stock +
        ", sale=" + sale +
        ", remark=" + remark +
        ", status=" + status +
        ", createdate=" + createdate +
        ", createtime=" + createtime +
        "}";
    }
}
