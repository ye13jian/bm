package com.eluoen.bm.modular.data.service;

import java.util.List;
import java.util.Map;

public interface IDataService {

    /**
     * 查询会员分布城市
     * @return
     */
    List<Map<String, Object>> searchMemberDataByCity(Integer userId);

    /**
     * 查询最后几个认证会员信息
     * @return
     */
    List<Map<String, Object>> searchMemberLast(Integer userId);

    /**
     * 查询会员活跃度分布城市
     * @return
     */
    List<Map<String, Object>> searchWxPlayDataByCity(Integer userId);
}
