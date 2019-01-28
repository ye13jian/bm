package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 微信设置信息
 * </p>
 *
 * @author eluoen
 * @since 2018-12-22
 */
@TableName("tbl_mp_info")
public class MpInfo extends Model<MpInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账户id
     */
    private Integer userid;
    /**
     * 广告栏1
     */
    private String ad1_img;
    private String ad1_link;
    /**
     * 广告栏2
     */
    private String ad2_img;
    private String ad2_link;
    /**
     * 广告栏3
     */
    private String ad3_img;
    private String ad3_link;
    /**
     * 广告栏4
     */
    private String ad4_img;
    private String ad4_link;
    /**
     * 广告栏5
     */
    private String ad5_img;
    private String ad5_link;
    /**
     * 广告栏5
     */
    private String ad6_img;
    private String ad6_link;
    /**
     * 广告栏5
     */
    private String ad7_img;
    private String ad7_link;

    private String ad8_img;
    private String ad8_link;

    private String ad9_img;
    private String ad9_link;

    private String ad10_img;
    private String ad10_link;

    /**
     * 标题
     */
    private String title;
    /**
     * 系统logo
     */
    private String logo_img;
    /**
     * 资源类型（reslink：外链 resurl：oss）
     */
    private String restype;

    private String tel;
    /**
     * 资源库
     */
    private String resource_img;
    /**
     * 我的收藏
     */
    private String collection_img;
    /**
     * 个人中心
     */
    private String center_img;
    /**
     * 积分商城
     */
    private String integralmall_img;
    /**
     * 在线论坛
     */
    private String bbs_img;
    /**
     * 意见建议
     */
    private String opinion_img;

    /**
     * 会员权益说明
     */
    private String rights;
    /**
     * 公众号名称
     */
    private String wxname;
    /**
     * 公众号二维码图片
     */
    private String wxqrcode_img;
    /**
     * 拓展功能
     */
    private String tz1_name;
    private String tz1_img;
    private String tz1_link;
    private String tz2_name;
    private String tz2_img;
    private String tz2_link;
    private String tz3_name;
    private String tz3_img;
    private String tz3_link;
    private String tz4_name;
    private String tz4_img;
    private String tz4_link;
    private String tz5_name;
    private String tz5_img;
    private String tz5_link;
    private String tz6_name;
    private String tz6_img;
    private String tz6_link;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAd1_img() {
        return ad1_img;
    }

    public void setAd1_img(String ad1_img) {
        this.ad1_img = ad1_img;
    }

    public String getAd1_link() {
        return ad1_link;
    }

    public void setAd1_link(String ad1_link) {
        this.ad1_link = ad1_link;
    }

    public String getAd2_img() {
        return ad2_img;
    }

    public void setAd2_img(String ad2_img) {
        this.ad2_img = ad2_img;
    }

    public String getAd2_link() {
        return ad2_link;
    }

    public void setAd2_link(String ad2_link) {
        this.ad2_link = ad2_link;
    }

    public String getAd3_img() {
        return ad3_img;
    }

    public void setAd3_img(String ad3_img) {
        this.ad3_img = ad3_img;
    }

    public String getAd3_link() {
        return ad3_link;
    }

    public void setAd3_link(String ad3_link) {
        this.ad3_link = ad3_link;
    }

    public String getAd4_img() {
        return ad4_img;
    }

    public void setAd4_img(String ad4_img) {
        this.ad4_img = ad4_img;
    }

    public String getAd4_link() {
        return ad4_link;
    }

    public void setAd4_link(String ad4_link) {
        this.ad4_link = ad4_link;
    }

    public String getAd5_img() {
        return ad5_img;
    }

    public void setAd5_img(String ad5_img) {
        this.ad5_img = ad5_img;
    }

    public String getAd5_link() {
        return ad5_link;
    }

    public void setAd5_link(String ad5_link) {
        this.ad5_link = ad5_link;
    }

    public String getAd6_img() {
        return ad6_img;
    }

    public void setAd6_img(String ad6_img) {
        this.ad6_img = ad6_img;
    }

    public String getAd6_link() {
        return ad6_link;
    }

    public void setAd6_link(String ad6_link) {
        this.ad6_link = ad6_link;
    }

    public String getAd7_img() {
        return ad7_img;
    }

    public void setAd7_img(String ad7_img) {
        this.ad7_img = ad7_img;
    }

    public String getAd7_link() {
        return ad7_link;
    }

    public void setAd7_link(String ad7_link) {
        this.ad7_link = ad7_link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo_img() {
        return logo_img;
    }

    public void setLogo_img(String logo_img) {
        this.logo_img = logo_img;
    }

    public String getRestype() {
        return restype;
    }

    public void setRestype(String restype) {
        this.restype = restype;
    }

    public String getResource_img() {
        return resource_img;
    }

    public void setResource_img(String resource_img) {
        this.resource_img = resource_img;
    }

    public String getCollection_img() {
        return collection_img;
    }

    public void setCollection_img(String collection_img) {
        this.collection_img = collection_img;
    }

    public String getCenter_img() {
        return center_img;
    }

    public void setCenter_img(String center_img) {
        this.center_img = center_img;
    }

    public String getIntegralmall_img() {
        return integralmall_img;
    }

    public void setIntegralmall_img(String integralmall_img) {
        this.integralmall_img = integralmall_img;
    }

    public String getBbs_img() {
        return bbs_img;
    }

    public void setBbs_img(String bbs_img) {
        this.bbs_img = bbs_img;
    }

    public String getOpinion_img() {
        return opinion_img;
    }

    public void setOpinion_img(String opinion_img) {
        this.opinion_img = opinion_img;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname;
    }

    public String getWxqrcode_img() {
        return wxqrcode_img;
    }

    public void setWxqrcode_img(String wxqrcode_img) {
        this.wxqrcode_img = wxqrcode_img;
    }

    public String getTz1_name() {
        return tz1_name;
    }

    public void setTz1_name(String tz1_name) {
        this.tz1_name = tz1_name;
    }

    public String getTz1_img() {
        return tz1_img;
    }

    public void setTz1_img(String tz1_img) {
        this.tz1_img = tz1_img;
    }

    public String getTz1_link() {
        return tz1_link;
    }

    public void setTz1_link(String tz1_link) {
        this.tz1_link = tz1_link;
    }

    public String getTz2_name() {
        return tz2_name;
    }

    public void setTz2_name(String tz2_name) {
        this.tz2_name = tz2_name;
    }

    public String getTz2_img() {
        return tz2_img;
    }

    public void setTz2_img(String tz2_img) {
        this.tz2_img = tz2_img;
    }

    public String getTz2_link() {
        return tz2_link;
    }

    public void setTz2_link(String tz2_link) {
        this.tz2_link = tz2_link;
    }

    public String getTz3_name() {
        return tz3_name;
    }

    public void setTz3_name(String tz3_name) {
        this.tz3_name = tz3_name;
    }

    public String getTz3_img() {
        return tz3_img;
    }

    public void setTz3_img(String tz3_img) {
        this.tz3_img = tz3_img;
    }

    public String getTz3_link() {
        return tz3_link;
    }

    public void setTz3_link(String tz3_link) {
        this.tz3_link = tz3_link;
    }

    public String getTz4_name() {
        return tz4_name;
    }

    public void setTz4_name(String tz4_name) {
        this.tz4_name = tz4_name;
    }

    public String getTz4_img() {
        return tz4_img;
    }

    public void setTz4_img(String tz4_img) {
        this.tz4_img = tz4_img;
    }

    public String getTz4_link() {
        return tz4_link;
    }

    public void setTz4_link(String tz4_link) {
        this.tz4_link = tz4_link;
    }

    public String getTz5_name() {
        return tz5_name;
    }

    public void setTz5_name(String tz5_name) {
        this.tz5_name = tz5_name;
    }

    public String getTz5_img() {
        return tz5_img;
    }

    public void setTz5_img(String tz5_img) {
        this.tz5_img = tz5_img;
    }

    public String getTz5_link() {
        return tz5_link;
    }

    public void setTz5_link(String tz5_link) {
        this.tz5_link = tz5_link;
    }

    public String getTz6_name() {
        return tz6_name;
    }

    public void setTz6_name(String tz6_name) {
        this.tz6_name = tz6_name;
    }

    public String getTz6_img() {
        return tz6_img;
    }

    public void setTz6_img(String tz6_img) {
        this.tz6_img = tz6_img;
    }

    public String getTz6_link() {
        return tz6_link;
    }

    public void setTz6_link(String tz6_link) {
        this.tz6_link = tz6_link;
    }

    public String getAd8_img() {
        return ad8_img;
    }

    public void setAd8_img(String ad8_img) {
        this.ad8_img = ad8_img;
    }

    public String getAd8_link() {
        return ad8_link;
    }

    public void setAd8_link(String ad8_link) {
        this.ad8_link = ad8_link;
    }

    public String getAd9_img() {
        return ad9_img;
    }

    public void setAd9_img(String ad9_img) {
        this.ad9_img = ad9_img;
    }

    public String getAd9_link() {
        return ad9_link;
    }

    public void setAd9_link(String ad9_link) {
        this.ad9_link = ad9_link;
    }

    public String getAd10_img() {
        return ad10_img;
    }

    public void setAd10_img(String ad10_img) {
        this.ad10_img = ad10_img;
    }

    public String getAd10_link() {
        return ad10_link;
    }

    public void setAd10_link(String ad10_link) {
        this.ad10_link = ad10_link;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "MpInfo{" +
                "id=" + id +
                ", userid=" + userid +
                ", ad1_img='" + ad1_img + '\'' +
                ", ad1_link='" + ad1_link + '\'' +
                ", ad2_img='" + ad2_img + '\'' +
                ", ad2_link='" + ad2_link + '\'' +
                ", ad3_img='" + ad3_img + '\'' +
                ", ad3_link='" + ad3_link + '\'' +
                ", ad4_img='" + ad4_img + '\'' +
                ", ad4_link='" + ad4_link + '\'' +
                ", ad5_img='" + ad5_img + '\'' +
                ", ad5_link='" + ad5_link + '\'' +
                ", ad6_img='" + ad6_img + '\'' +
                ", ad6_link='" + ad6_link + '\'' +
                ", ad7_img='" + ad7_img + '\'' +
                ", ad7_link='" + ad7_link + '\'' +
                ", ad8_img='" + ad8_img + '\'' +
                ", ad8_link='" + ad8_link + '\'' +
                ", ad9_img='" + ad9_img + '\'' +
                ", ad9_link='" + ad9_link + '\'' +
                ", ad10_img='" + ad10_img + '\'' +
                ", ad10_link='" + ad10_link + '\'' +
                ", title='" + title + '\'' +
                ", logo_img='" + logo_img + '\'' +
                ", restype='" + restype + '\'' +
                ", tel='" + tel + '\'' +
                ", resource_img='" + resource_img + '\'' +
                ", collection_img='" + collection_img + '\'' +
                ", center_img='" + center_img + '\'' +
                ", integralmall_img='" + integralmall_img + '\'' +
                ", bbs_img='" + bbs_img + '\'' +
                ", opinion_img='" + opinion_img + '\'' +
                ", rights='" + rights + '\'' +
                ", wxname='" + wxname + '\'' +
                ", wxqrcode_img='" + wxqrcode_img + '\'' +
                ", tz1_name='" + tz1_name + '\'' +
                ", tz1_img='" + tz1_img + '\'' +
                ", tz1_link='" + tz1_link + '\'' +
                ", tz2_name='" + tz2_name + '\'' +
                ", tz2_img='" + tz2_img + '\'' +
                ", tz2_link='" + tz2_link + '\'' +
                ", tz3_name='" + tz3_name + '\'' +
                ", tz3_img='" + tz3_img + '\'' +
                ", tz3_link='" + tz3_link + '\'' +
                ", tz4_name='" + tz4_name + '\'' +
                ", tz4_img='" + tz4_img + '\'' +
                ", tz4_link='" + tz4_link + '\'' +
                ", tz5_name='" + tz5_name + '\'' +
                ", tz5_img='" + tz5_img + '\'' +
                ", tz5_link='" + tz5_link + '\'' +
                ", tz6_name='" + tz6_name + '\'' +
                ", tz6_img='" + tz6_img + '\'' +
                ", tz6_link='" + tz6_link + '\'' +
                '}';
    }
}
