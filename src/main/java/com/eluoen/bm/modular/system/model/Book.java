package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 书籍表
 * </p>
 *
 * @author eluoen
 * @since 2018-11-17
 */
@TableName("tbl_book")
public class Book extends Model<Book> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 书名
     */
    private String name;
    /**
     * 书籍类别
     */
    private String category;
    /**
     * 书籍系列
     */
    private String series;
    /**
     * ISBN号
     */
    private String isbn;
    /**
     * 书籍图片
     */
    private String imgurl;
    /**
     * 作者
     */
    private String author;
    /**
     * 编辑
     */
    private String editor;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 积分
     */
    private BigDecimal score;
    /**
     * 出版日期
     */
    private String publicationdate;
    /**
     * 出版社
     */
    private String publicationpress;
    /**
     * 书籍描述
     */
    private String describes;
    /**
     * 创建人
     */
    private String createuser;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getSeries() { return series; }

    public void setSeries(String series) { this.series = series; }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getPublicationpress() {
        return publicationpress;
    }

    public void setPublicationpress(String publicationpress) {
        this.publicationpress = publicationpress;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(String publicationdate) {
        this.publicationdate = publicationdate;
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
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", series='" + series + '\'' +
                ", isbn='" + isbn + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", author='" + author + '\'' +
                ", editor='" + editor + '\'' +
                ", price=" + price +
                ", score=" + score +
                ", publicationdate='" + publicationdate + '\'' +
                ", publicationpress='" + publicationpress + '\'' +
                ", describes='" + describes + '\'' +
                ", createuser='" + createuser + '\'' +
                ", status=" + status +
                ", createdate='" + createdate + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
