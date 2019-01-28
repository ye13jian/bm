package com.eluoen.bm.modular.system.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据统计 Mapper 接口
 * </p>
 *
 * @author eluoen
 * @since 2018-11-27
 */
public interface DataMapper {

    /**
     * 查询会员分布城市
     * @return
     */
    List<Map<String, Object>> searchMemberDataByCity(@Param("userId") Integer userId);

    /**
     * 查询最后几个认证会员信息
     * @return
     */
    List<Map<String, Object>> searchMemberLast(@Param("userId") Integer userId);

    /**
     * 查询会员活跃度分布城市
     * @return
     */
    List<Map<String, Object>> searchWxPlayDataByCity(@Param("userId") Integer userId);


}
