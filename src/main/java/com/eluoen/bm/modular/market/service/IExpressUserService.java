package com.eluoen.bm.modular.market.service;

import com.eluoen.bm.modular.system.model.ExpressUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 我的快递 服务类
 * </p>
 *
 * @author eluoen
 * @since 2019-01-28
 */
public interface IExpressUserService extends IService<ExpressUser> {

    List<Map<String,Object>> selectExpressList();

    List<Map<String,Object>> selectList_U(Integer userId, String condition);
}
