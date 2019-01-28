package com.eluoen.bm.modular.system.dao;

import com.eluoen.bm.modular.system.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微信对接 Mapper 接口
 * </p>
 *
 * @author eluoen
 * @since 2018-11-27
 */
public interface MpMapper {

    /**
     * 查询二维码
     * @param qrcode
     * @return
     */
    Map<String, Object> searchQrcode(@Param("qrcode") String qrcode);

    /**
     * 查询认证信息
     * @param qrcode
     * @return
     */
    Map<String, Object> searchQrcodeAuth(@Param("qrcode") String qrcode);

    /**
     * 查询书籍信息
     * @param bookid
     * @return
     */
    Map<String, Object> searchBook(@Param("bookid") String bookid);

    /**
     * 查询会员可看的书籍信息
     * @param openid
     * @return
     */
    List<Map<String, Object>> searchBookList(@Param("openid") String openid);

    /**
     * 查询书籍目录
     * @param bookid
     * @return
     */
    List<Map<String, Object>> searchBookCatalogList(@Param("bookid") String bookid);

    /**
     * 查询收藏的书籍目录
     * @param openid
     * @return
     */
    List<Map<String, Object>> searchCollectionList(@Param("openid") String openid);

    /**
     * 添加收藏
     * @param coll
     * @return
     */
    int addCollection(@Param("coll") Wxcollection coll);

    /**
     * 取消收藏
     * @param coll
     * @return
     */
    int subCollection(@Param("coll") Wxcollection coll);

    /**
     * 查询书籍目录是否收藏
     * @param coll
     * @return
     */
    Map<String, Object> searchCollection(@Param("coll") Wxcollection coll);

    /**
     * 查询书籍目录详细信息
     * @param bookCatalogId
     * @return
     */
    Map<String, Object> searchBookCatalogDetail(@Param("bookCatalogId") Integer bookCatalogId);

    /**
     * 查询会员信息
     * @param openid
     * @return
     */
    Map<String, Object> searchMember(@Param("openid") String openid);

    /**
     * 添加二维码授权
     * @param qrcodeAuth
     * @return
     */
    void addQrcodeAuth(@Param("map") Map<String, Object> qrcodeAuth);

    /**
     * 添加会员信息
     * @param member
     * @return
     */
    void addMember(@Param("map") Map<String, Object> member);

    /**
     * 添加播放记录
     * @param coll
     * @return
     */
    void addWxPlay(@Param("coll") Wxcollection coll);

    /**
     * 查询某一播放次数
     * @param bookcatalogid
     * @return
     */
    int searchWxPlayCount(@Param("bookcatalogid") Integer bookcatalogid);

    /**
     * 查询置顶文章列表
     * @return
     */
    List<Map<String, Object>> searchBbsSysList();

    /**
     * 查询置顶文章
     * @param bbsSysId
     * @return
     */
    Map<String, Object> searchBbsSysDetail(@Param("bbsSysId") Integer bbsSysId);

    /**
     * 查询用户贴列表前100
     * @return
     */
    List<Map<String, Object>> searchBbsWxList();

    /**
     * 查询用户贴
     * @param bbsWxId
     * @return
     */
    Map<String, Object> searchBbsWxDetail(@Param("bbsWxId") Integer bbsWxId);

    /**
     * 查询用户贴回复列表
     * @return
     */
    List<Map<String, Object>> searchBbsWxReplyList(@Param("bbsWxId") Integer bbsWxId);


    /**
     * 添加用户贴
     * @param bbsWx
     * @return
     */
    void addBbsWx(@Param("bbsWx") BbsWx bbsWx);

    /**
     * 添加用户贴回复
     * @param bbsWxReply
     * @return
     */
    void addBbsWxReply(@Param("reply") BbsWxReply bbsWxReply);

    /**
     * 添加用户意见或建议
     * @param opinion
     * @return
     */
    void addOpinion(@Param("opinion") Opinion opinion);

    /**
     * 新增积分
     * @param map
     */
    void addIntegral(@Param("map") Map<String, Object> map);

    /**
     * 更新会员积分
     * @param map
     */
    void updateMemberScore(@Param("map") Map<String, Object> map);

}
