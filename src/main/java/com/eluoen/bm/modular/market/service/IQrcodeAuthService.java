package com.eluoen.bm.modular.market.service;

import com.baomidou.mybatisplus.service.IService;
import com.eluoen.bm.modular.system.model.QrcodeAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 二维码认证 服务类
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-08
 */
public interface IQrcodeAuthService extends IService<QrcodeAuth> {

    List<Map<String,Object>> selectList_U(Integer userId, String condition);

}
