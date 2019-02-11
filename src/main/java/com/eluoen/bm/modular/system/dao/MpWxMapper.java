package com.eluoen.bm.modular.system.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 微信对接 Mapper 接口
 * </p>
 *
 * @author eluoen
 * @since 2018-11-27
 */
public interface MpWxMapper {

    /**
     * 查询会员应该打的标签名称 province,bookname
     * @param openid
     * @return
     */
    public List<String> searchMemberTagName(@Param("openid") String openid);


    /**
     * 查询会员标签id
     * @param tagName
     * @return
     */
    Integer searchTag(@Param("tagName") String tagName);


    /**
     * 插入标签信息
     * @param tagName
     * @param tagId
     * @return
     */
    void addTag(@Param("tagName") String tagName, @Param("tagId") Integer tagId);


    /**
     * 关键字回复
     * @param keyword
     * @return
     */
    public String searchWxReply(@Param("keyword") String keyword);

}
