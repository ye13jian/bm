package com.eluoen.bm.modular.market.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eluoen.bm.modular.market.service.IQrcodeAuthService;
import com.eluoen.bm.modular.system.dao.QrcodeAuthMapper;
import com.eluoen.bm.modular.system.model.QrcodeAuth;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 二维码认证 服务实现类
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-08
 */
@Service
public class QrcodeAuthServiceImpl extends ServiceImpl<QrcodeAuthMapper, QrcodeAuth> implements IQrcodeAuthService {

}
