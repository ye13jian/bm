package com.eluoen.bm.modular.integralMall.service;

import com.baomidou.mybatisplus.service.IService;
import com.eluoen.bm.modular.system.model.GiftIntegral;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 积分记录 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-03
 */
public interface IGiftIntegralService extends IService<GiftIntegral> {

    public List<Map<String,Object>> selectList(String startdate, String enddate, String condition);

}
