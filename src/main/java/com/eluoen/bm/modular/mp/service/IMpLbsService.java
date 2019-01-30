package com.eluoen.bm.modular.mp.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IMpLbsService {


    /**
     * 插入或更新用户位置
     * @param map
     * @return
     */
    public int replaceMemberLocation(Map<String,Object> map);


    /**
     * 同一本书，经纬度距离排序，6378.138是地球直径，distance是计算的距离(km)
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectMemberLocationByOpenid(Map<String,Object> map);

    /**
     * 广交天下，经纬度距离排序，6378.138是地球直径，distance是计算的距离(km)
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectMemberLocationByUserid(Map<String,Object> map);

}
