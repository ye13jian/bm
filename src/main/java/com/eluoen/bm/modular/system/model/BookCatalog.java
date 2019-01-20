package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 书籍目录
 * </p>
 *
 * @author eluoen
 * @since 2018-12-14
 */
@TableName("tbl_book_catalog")
public class BookCatalog extends Model<BookCatalog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 书籍的id
     */
    private Integer bookid;
    /**
     * 目录名
     */
    private String name;
    /**
     * 资源描述
     */
    private String describes;
    /**
     * 资源类型
     */
    private String restype;
    /**
     * 资源地址
     */
    private String resurl;
    /**
     * 外链地址
     */
    private String reslink;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 状态
     */
    private Integer status;
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

    public Integer getBookid() { return bookid; }

    public void setBookid(Integer bookid) { this.bookid = bookid; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribes() { return describes; }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getResurl() {
        return resurl;
    }

    public void setResurl(String resurl) {
        this.resurl = resurl;
    }

    public String getReslink() { return reslink; }

    public void setReslink(String reslink) { this.reslink = reslink; }

    public String getRestype() { return restype; }

    public void setRestype(String restype) { this.restype = restype; }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        return "BookCatalog{" +
        "id=" + id +
        ", bookid=" + bookid +
        ", name=" + name +
        ", describes=" + describes +
        ", price=" + price +
        ", restype=" + restype +
        ", resurl=" + resurl +
        ", reslink=" + reslink +
        ", status=" + status +
        ", createdate=" + createdate +
        ", createtime=" + createtime +
        "}";
    }
}
