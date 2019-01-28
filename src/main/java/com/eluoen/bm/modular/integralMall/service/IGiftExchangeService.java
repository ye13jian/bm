package com.eluoen.bm.modular.integralMall.service;

import com.baomidou.mybatisplus.service.IService;
import com.eluoen.bm.modular.system.model.GiftExchange;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 礼品兑换 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-03
 */
public interface IGiftExchangeService extends IService<GiftExchange> {

    public List<Map<String,Object>> selectList_U(Integer userId, String startdate, String enddate, String condition);

    public Map<String,Object> selectById(Integer giftExchangeId);
}
