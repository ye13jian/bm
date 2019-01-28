package com.eluoen.bm.modular.mp.service;

import com.eluoen.bm.modular.system.model.BbsWx;
import com.eluoen.bm.modular.system.model.BbsWxReply;
import com.eluoen.bm.modular.system.model.Opinion;
import com.eluoen.bm.modular.system.model.Wxcollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IMpService {

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
     * 查询收藏书籍列表
     * @param openid
     * @return
     */
    List<Map<String, Object>> searchCollectionList(@Param("openid") String openid);

    /**
     * 添加收藏
     * @param coll
     * @return
     */
    int addCollection(Wxcollection coll);

    /**
     * 取消收藏
     * @param coll
     * @return
     */
    int subCollection(Wxcollection coll);

    /**
     * 查询书籍目录是否收藏
     * @param coll
     * @return
     */
    Map<String, Object> searchCollection(Wxcollection coll);

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
     * @param map
     * @return
     */
    int addMemberAuth(Map<String, Object> map);


    /**
     * 添加播放记录
     * @param coll
     * @return
     */
    void addWxPlay(Wxcollection coll);

    /**
     * 查询某一播放次数
     * @param bookcatalogid
     * @return
     */
    int searchWxPlayCount(Integer bookcatalogid);

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
    Map<String, Object> searchBbsSysDetail(Integer bbsSysId);

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
    Map<String, Object> searchBbsWxDetail(Integer bbsWxId);

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
    void addBbsWx(BbsWx bbsWx);

    /**
     * 添加用户贴回复
     * @param bbsWxReply
     * @return
     */
    void addBbsWxReply(BbsWxReply bbsWxReply);

    /**
     * 添加用户意见或建议
     * @param opinion
     * @return
     */
    void addOpinion(Opinion opinion);

    /**
     * 新增积分
     * @param map
     */
    void addIntegral(Map<String, Object> map);
}
