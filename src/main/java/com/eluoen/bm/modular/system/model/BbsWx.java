package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 微信用户帖子
 * </p>
 *
 * @author eluoen
 * @since 2018-12-12
 */
@TableName("tbl_bbs_wx")
public class BbsWx extends Model<BbsWx> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片
     */
    private String imgurls;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 创建日期
     */
    private String createdate;
    /**
     * 创建时间
     */
    private String createtime;
    /**
     * 最后回复时间
     */
    @TableField("last_reply")
    private String lastReply;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgurls() {
        return imgurls;
    }

    public void setImgurls(String imgurls) {
        this.imgurls = imgurls;
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

    public String getLastReply() {
        return lastReply;
    }

    public void setLastReply(String lastReply) {
        this.lastReply = lastReply;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BbsWx{" +
        "id=" + id +
        ", title=" + title +
        ", content=" + content +
        ", imgurls=" + imgurls +
        ", openid=" + openid +
        ", createdate=" + createdate +
        ", createtime=" + createtime +
        ", lastReply=" + lastReply +
        "}";
    }
}
