package com.eluoen.bm.modular.integralMall.service;

import com.baomidou.mybatisplus.service.IService;
import com.eluoen.bm.modular.system.model.Gift;

import java.util.List;

/**
 * <p>
 * 商城礼品管理 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-03
 */
public interface IGiftService extends IService<Gift> {

    List<Gift> selectList_U(Integer userId,String condition);

}
