package com.eluoen.bm.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 快递鸟
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-14
 */
@TableName("tbl_express")
public class Express extends Model<Express> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 快递编号
     */
    @TableField("exp_code")
    private String expCode;
    /**
     * 快递名称
     */
    @TableField("exp_name")
    private String expName;
    /**
     * logo图标
     */
    private String logo;
    /**
     * 服务电话
     */
    private String tel;
    /**
     * 状态1启用0停用
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpCode() {
        return expCode;
    }

    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Express{" +
        "id=" + id +
        ", expCode=" + expCode +
        ", expName=" + expName +
        ", logo=" + logo +
        ", tel=" + tel +
        ", status=" + status +
        "}";
    }
}
