package com.eluoen.bm.modular.mp.service.impl;

import com.eluoen.bm.modular.mp.service.IMpIntegralMallService;
import com.eluoen.bm.modular.system.dao.MpIntegralMallMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IMpIntegralMallServiceImpl implements IMpIntegralMallService {

    @Resource
    private MpIntegralMallMapper mpIntegralMallMapper;

    @Override
    public List<Map<String,Object>> searchGiftList(Integer userId) {
        return this.mpIntegralMallMapper.searchGiftList(userId);
    }

    @Override
    public Map<String, Object> searchGift(String giftId) {
        return this.mpIntegralMallMapper.searchGift(giftId);
    }

    @Override
    public int insertExchange(Map<String, Object> map) {
        this.mpIntegralMallMapper.insertExchange(map);
        this.mpIntegralMallMapper.subMemberScore(map);
        this.mpIntegralMallMapper.updateGiftStockAndSale(map);
        return 1;
    }

    @Override
    public List<Map<String, Object>> searchOrderList(String openid) {
        return this.mpIntegralMallMapper.searchOrderList(openid);
    }
}
