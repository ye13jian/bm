package com.eluoen.bm.modular.market.service.impl;

import com.eluoen.bm.modular.system.model.ExpressUser;
import com.eluoen.bm.modular.system.dao.ExpressUserMapper;
import com.eluoen.bm.modular.market.service.IExpressUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 我的快递 服务实现类
 * </p>
 *
 * @author eluoen
 * @since 2019-01-28
 */
@Service
public class ExpressUserServiceImpl extends ServiceImpl<ExpressUserMapper, ExpressUser> implements IExpressUserService {

    @Resource
    private ExpressUserMapper expressUserMapper;

    @Override
    public List<Map<String, Object>> selectExpressList() {
        return this.expressUserMapper.selectExpressList();
    }

    @Override
    public List<Map<String, Object>> selectList_U(Integer userId, String condition) {
        return this.expressUserMapper.selectList_U(userId,condition);
    }
}
