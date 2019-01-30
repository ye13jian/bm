package com.eluoen.bm.modular.market.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eluoen.bm.modular.market.service.IQrcodeAuthService;
import com.eluoen.bm.modular.system.dao.QrcodeAuthMapper;
import com.eluoen.bm.modular.system.model.QrcodeAuth;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Resource
    private QrcodeAuthMapper qrcodeAuthMapper;

    @Override
    public List<Map<String, Object>> selectList_U(Integer userId, String condition) {
        return this.qrcodeAuthMapper.selectList_U(userId,condition);
    }
}
