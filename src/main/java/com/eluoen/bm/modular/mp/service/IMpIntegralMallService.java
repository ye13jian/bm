package com.eluoen.bm.modular.mp.service;

import java.util.List;
import java.util.Map;

public interface IMpIntegralMallService {

    /**
     * 查询账号下所有礼品
     * @param userId
     * @return
     */
    public List<Map<String,Object>> searchGiftList(Integer userId);

    /**
     * 查询礼品
     * @param giftId
     * @return
     */
    public Map<String,Object> searchGift(String giftId);

    /**
     * 插入兑换记录
     * @param map
     * @return
     */
    public int insertExchange(Map<String, Object> map);

    /**
     * 查询订单
     * @param openid
     * @return
     */
    public List<Map<String,Object>> searchOrderList(String openid);

}
