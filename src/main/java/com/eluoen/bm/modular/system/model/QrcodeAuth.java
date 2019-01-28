package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 二维码认证
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-08
 */
@TableName("tbl_qrcode_auth")
public class QrcodeAuth extends Model<QrcodeAuth> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 二维码
     */
    private String qrcode;
    /**
     * 书籍id
     */
    private Integer bookid;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 认证日期
     */
    private String createdate;
    /**
     * 认证时间
     */
    private String createtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
        return "QrcodeAuth{" +
        "id=" + id +
        ", qrcode=" + qrcode +
        ", bookid=" + bookid +
        ", openid=" + openid +
        ", createdate=" + createdate +
        ", createtime=" + createtime +
        "}";
    }
}
