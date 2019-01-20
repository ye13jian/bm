package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 意见建议
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-12
 */
@TableName("tbl_opinion")
public class Opinion extends Model<Opinion> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 意见内容
     */
    private String content;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 创建日期
     */
    private String createdate;
    /**
     * 创建时间
     */
    private String createtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
        return "Opinion{" +
        "id=" + id +
        ", content=" + content +
        ", openid=" + openid +
        ", mobile=" + mobile +
        ", createdate=" + createdate +
        ", createtime=" + createtime +
        "}";
    }
}
