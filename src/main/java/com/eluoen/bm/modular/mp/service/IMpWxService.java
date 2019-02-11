package com.eluoen.bm.modular.mp.service;

import java.util.List;

public interface IMpWxService {

    /**
     * 查询会员应该打的标签名称 province,bookname
     * @param openid
     * @return
     */
    public List<String> searchMemberTagName(String openid);

    /**
     * 查询会员省份标签id
     * @param tagName
     * @return
     */
    Integer searchTag(String tagName);


    /**
     * 插入标签信息
     * @param tagName
     * @param tagId
     * @return
     */
    void addTag(String tagName, Integer tagId);

    /**
     * 关键字回复
     * @param keyword
     * @return
     */
    public String searchWxReply(String keyword);

}
