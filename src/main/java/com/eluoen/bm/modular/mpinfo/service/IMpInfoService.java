package com.eluoen.bm.modular.mpinfo.service;

import com.eluoen.bm.modular.system.model.MpInfo;

import java.util.Map;

/**
 * <p>
 * 微信设置（主要是广告图链接等）
 * </p>
 *
 * @author eluoen
 * @since 2018-12-22
 */
public interface IMpInfoService {

    /**
     * 查询微信设置信息
     * @return
     */
//    MpInfo getMpInfo();
    Map<String,Object> getMpInfo(Integer userId);

    /**
     * 更新微信设置信息
     * @param mpInfo
     * @return
     */
    int setMpInfo(MpInfo mpInfo);
}
