package com.eluoen.bm.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.eluoen.bm.modular.system.model.BbsSys;
import com.eluoen.bm.modular.system.model.Gift;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * BBS置顶文章 Mapper 接口
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-10
 */
public interface BbsSysMapper extends BaseMapper<BbsSys> {

    List<BbsSys> selectList_U(@Param("userId") Integer userId, @Param("condition") String condition);
}
