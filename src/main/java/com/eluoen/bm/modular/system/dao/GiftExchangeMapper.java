package com.eluoen.bm.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.eluoen.bm.modular.system.model.GiftExchange;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 礼品兑换 Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2019-01-03
 */
public interface GiftExchangeMapper extends BaseMapper<GiftExchange> {


    public List<Map<String,Object>> selectList(@Param("startdate") String startdate, @Param("enddate") String enddate, @Param("condition") String condition);

    public Map<String,Object> selectById(@Param("giftExchangeId") Integer giftExchangeId);

}
