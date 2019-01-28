package com.eluoen.bm.modular.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.eluoen.bm.modular.system.model.BbsWx;
import com.eluoen.bm.modular.system.model.Opinion;

import java.util.List;

/**
 * <p>
 * 意见建议 服务类
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-12
 */
public interface IOpinionService extends IService<Opinion> {

    List<Opinion> selectList_U(Integer userId, String condition);
}
