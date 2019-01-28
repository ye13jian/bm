package com.eluoen.bm.modular.bbs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eluoen.bm.modular.bbs.service.IBbsWxService;
import com.eluoen.bm.modular.system.dao.BbsSysMapper;
import com.eluoen.bm.modular.system.dao.BbsWxMapper;
import com.eluoen.bm.modular.system.model.BbsWx;
import com.eluoen.bm.modular.system.model.Gift;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 微信用户帖子 服务实现类
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-12
 */
@Service
public class BbsWxServiceImpl extends ServiceImpl<BbsWxMapper, BbsWx> implements IBbsWxService {

    @Resource
    private BbsWxMapper bbsWxMapper;

    @Override
    public List<BbsWx> selectList_U(Integer userId, String condition) {
        return bbsWxMapper.selectList_U(userId,condition);
    }

}
