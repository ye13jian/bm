package com.eluoen.bm.modular.integralMall.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eluoen.bm.modular.integralMall.service.IGiftExchangeService;
import com.eluoen.bm.modular.system.dao.GiftExchangeMapper;
import com.eluoen.bm.modular.system.model.GiftExchange;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 礼品兑换 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-03
 */
@Service
public class GiftExchangeServiceImpl extends ServiceImpl<GiftExchangeMapper, GiftExchange> implements IGiftExchangeService {

    @Resource
    private GiftExchangeMapper giftExchangeMapper;

    @Override
    public List<Map<String, Object>> selectList(String startdate, String enddate, String condition) {
        return this.giftExchangeMapper.selectList(startdate,enddate,condition);
    }

    @Override
    public Map<String, Object> selectById(Integer giftExchangeId) {
        return giftExchangeMapper.selectById(giftExchangeId);
    }
}
