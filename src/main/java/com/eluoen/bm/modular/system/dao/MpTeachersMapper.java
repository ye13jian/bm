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
public interface MpTeachersMapper {

    public Map<String,Object> selectTeacherByMobile(@Param("mobile") String mobile);

    public List<Map<String,Object>> selectTeacherMapList();

}
