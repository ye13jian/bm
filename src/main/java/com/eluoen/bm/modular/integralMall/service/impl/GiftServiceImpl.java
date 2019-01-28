package com.eluoen.bm.modular.integralMall.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eluoen.bm.modular.integralMall.service.IGiftService;
import com.eluoen.bm.modular.system.dao.GiftMapper;
import com.eluoen.bm.modular.system.model.Gift;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商城礼品管理 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-03
 */
@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements IGiftService {

    @Resource
    private GiftMapper giftMapper;

    @Override
    public List<Gift> selectList_U(Integer userId, String condition) {
        return this.giftMapper.selectList_U(userId,condition);

    }
}
