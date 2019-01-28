package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author eluoen
 * @since 2018-12-09
 */
@TableName("tbl_wxcollection")
public class Wxcollection extends Model<Wxcollection> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 书籍列表id
     */
    private Integer bookcatalogid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getBookcatalogid() { return bookcatalogid; }

    public void setBookcatalogid(Integer bookcatalogid) { this.bookcatalogid = bookcatalogid; }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Collection{" +
        "id=" + id +
        ", openid=" + openid +
        ", bookcatalogid=" + bookcatalogid +
        "}";
    }


}
