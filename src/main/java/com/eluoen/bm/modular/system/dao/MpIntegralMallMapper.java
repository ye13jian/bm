package com.eluoen.bm.modular.system.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 积分商城
 * </p>
 *
 * @author eluoen
 * @since 2019-01-12
 */
public interface MpIntegralMallMapper {

    /**
     * 查询账户下所有礼品
     * @param userId
     * @return
     */
    public List<Map<String,Object>> searchGiftList(@Param("userId") Integer userId);

    /**
     * 查询礼品
     * @param giftId
     * @return
     */
    public Map<String,Object> searchGift(@Param("giftId") String giftId);

    /**
     * 插入兑换记录
     * @param map
     * @return
     */
    public int insertExchange(@Param("map") Map<String, Object> map);

    /**
     * 减少用户积分
     * @param map
     * @return
     */
    public int subMemberScore(@Param("map") Map<String, Object> map);

    /**
     * 减少库存，增加销量
     * @param map
     * @return
     */
    public int updateGiftStockAndSale(@Param("map") Map<String, Object> map);

    /**
     * 查询订单
     * @param openid
     * @return
     */
    public List<Map<String,Object>> searchOrderList(@Param("openid") String openid);
}
