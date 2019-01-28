package com.eluoen.bm.modular.system.dao;

import com.eluoen.bm.modular.system.model.MpInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 微信里设置（主要是广告）
 * </p>
 *
 * @author eluoen
 * @since 2018-12-22
 */
public interface MpInfoMapper {

    /**
     * 查询微信设置信息
     * @return
     */
    /*MpInfo getMpInfo();*/

    Map<String,Object> getMpInfo(@Param("userId") Integer userId);

    /**
     * 更新微信设置信息
     * @param mpInfo
     * @return
     */
    int setMpInfo(@Param("mpInfo") MpInfo mpInfo);

}
