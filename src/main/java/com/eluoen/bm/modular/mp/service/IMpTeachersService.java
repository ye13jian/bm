package com.eluoen.bm.modular.mp.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IMpTeachersService {

    public Map<String,Object> selectTeacherByMobile(String mobile);

    public List<Map<String,Object>> selectTeacherMapList();

}
