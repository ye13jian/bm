package com.eluoen.bm.modular.system.dao;

import com.eluoen.bm.modular.system.model.ExpressUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 我的快递 Mapper 接口
 * </p>
 *
 * @author eluoen
 * @since 2019-01-28
 */
public interface ExpressUserMapper extends BaseMapper<ExpressUser> {

    List<Map<String,Object>> selectExpressList();

    List<Map<String,Object>> selectList_U(@Param("userId") Integer userId, @Param("condition") String condition);
}
