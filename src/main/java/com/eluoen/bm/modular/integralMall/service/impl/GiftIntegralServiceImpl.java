package com.eluoen.bm.modular.integralMall.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eluoen.bm.modular.integralMall.service.IGiftIntegralService;
import com.eluoen.bm.modular.system.dao.GiftIntegralMapper;
import com.eluoen.bm.modular.system.model.GiftIntegral;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 积分记录 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-03
 */
@Service
public class GiftIntegralServiceImpl extends ServiceImpl<GiftIntegralMapper, GiftIntegral> implements IGiftIntegralService {

    @Resource
    private GiftIntegralMapper giftIntegralMapper;

    @Override
    public List<Map<String, Object>> selectList_U(Integer userId, String startdate, String enddate, String condition) {
        return this.giftIntegralMapper.selectList_U(userId,startdate,enddate,condition);
    }
}
