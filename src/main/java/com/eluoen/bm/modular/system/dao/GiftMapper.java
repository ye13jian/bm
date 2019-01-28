package com.eluoen.bm.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.eluoen.bm.modular.system.model.Gift;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商城礼品管理 Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-03
 */
public interface GiftMapper extends BaseMapper<Gift> {

    List<Gift> selectList_U(@Param("userId") Integer userId, @Param("condition") String condition);
}
