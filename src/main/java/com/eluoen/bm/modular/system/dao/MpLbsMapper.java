package com.eluoen.bm.modular.system.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * lbs服务
 * </p>
 *
 * @author eluoen
 * @since 2019-01-30
 */
public interface MpLbsMapper {

    /**
     * 插入或更新用户位置
     * @param map
     * @return
     */
    public int replaceMemberLocation(@Param("map") Map<String,Object> map);

    /**
     * 同一本书，经纬度距离排序，6378.138是地球直径，distance是计算的距离(km)
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectMemberLocationByOpenid(@Param("map") Map<String,Object> map);

    /**
     * 广交天下，经纬度距离排序，6378.138是地球直径，distance是计算的距离(km)
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectMemberLocationByUserid(@Param("map") Map<String,Object> map);

    /**
     * 获取模板信息
     * @param id
     * @return
     */
    public Map<String,Object> getTemplateInfo(@Param("id") Integer id);

    /**
     * 获取用户位置信息
     * @param openid
     * @return
     */
    public Map<String,Object> getMemberLocation(@Param("openid") String openid);

    /**
     * 添加留言
     * @param map
     * @return
     */
    public int addDialogue(@Param("map") Map<String,Object> map);

    /**
     * 获获取当天最后一条对话记录
     * @param map
     * @return
     */
    public List<Map<String,Object>> getDialogueList(@Param("map") Map<String,Object> map);

    /**
     * 更新获取过的信息状态
     * @param ids
     * @return
     */
    public int updateDialogueStatus(@Param("ids") String ids);
}
