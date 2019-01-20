package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * BBS 用户贴回复
 * </p>
 *
 * @author eluoen
 * @since 2018-12-11
 */
@TableName("tbl_bbs_wx_reply")
public class BbsWxReply extends Model<BbsWxReply> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户贴id
     */
    private Integer bbswxid;
    /**
     * 富文本内容
     */
    private String content;
    /**
     * 回复用户
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBbswxid() {
        return bbswxid;
    }

    public void setBbswxid(Integer bbswxid) {
        this.bbswxid = bbswxid;
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
        return "BbsWxReply{" +
                "id=" + id +
                ", bbswxid=" + bbswxid +
                ", content='" + content + '\'' +
                ", openid='" + openid + '\'' +
                ", createdate=" + createdate +
                ", createtime=" + createtime +
                '}';
    }
}
