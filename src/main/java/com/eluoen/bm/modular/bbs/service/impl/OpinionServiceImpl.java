package com.eluoen.bm.modular.bbs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eluoen.bm.modular.bbs.service.IOpinionService;
import com.eluoen.bm.modular.system.dao.BbsWxMapper;
import com.eluoen.bm.modular.system.dao.OpinionMapper;
import com.eluoen.bm.modular.system.model.BbsWx;
import com.eluoen.bm.modular.system.model.Opinion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 意见建议 服务实现类
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-12
 */
@Service
public class OpinionServiceImpl extends ServiceImpl<OpinionMapper, Opinion> implements IOpinionService {

    @Resource
    private OpinionMapper opinionMapper;

    @Override
    public List<Opinion> selectList_U(Integer userId, String condition) {
        return opinionMapper.selectList_U(userId,condition);
    }
}
