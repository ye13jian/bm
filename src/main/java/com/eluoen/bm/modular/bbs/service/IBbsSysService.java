package com.eluoen.bm.modular.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.eluoen.bm.modular.system.model.BbsSys;
import com.eluoen.bm.modular.system.model.Gift;

import java.util.List;

/**
 * <p>
 * BBS置顶文章 服务类
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-10
 */
public interface IBbsSysService extends IService<BbsSys> {

    List<BbsSys> selectList_U(Integer userId, String condition);

}
