package com.eluoen.bm.modular.bbs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eluoen.bm.modular.bbs.service.IBbsSysService;
import com.eluoen.bm.modular.system.dao.BbsSysMapper;
import com.eluoen.bm.modular.system.model.BbsSys;
import com.eluoen.bm.modular.system.model.Gift;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * BBS置顶文章 服务实现类
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-10
 */
@Service
public class BbsSysServiceImpl extends ServiceImpl<BbsSysMapper, BbsSys> implements IBbsSysService {

    @Resource
    private BbsSysMapper bbsSysMapper;

    @Override
    public List<BbsSys> selectList_U(Integer userId, String condition) {
        return bbsSysMapper.selectList_U(userId,condition);
    }
}
