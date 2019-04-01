package com.eluoen.bm.modular.mp.service.impl;

import com.eluoen.bm.modular.mp.service.IMpLbsService;
import com.eluoen.bm.modular.mp.service.IMpTeachersService;
import com.eluoen.bm.modular.system.dao.MpLbsMapper;
import com.eluoen.bm.modular.system.dao.MpTeachersMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IMpTeachersServiceImpl implements IMpTeachersService {

    @Resource
    private MpTeachersMapper mpTeachersMapper;


    @Override
    public Map<String, Object> selectTeacherByMobile(String mobile) {
        return mpTeachersMapper.selectTeacherByMobile(mobile);
    }

    @Override
    public List<Map<String, Object>> selectTeacherMapList() {
        return mpTeachersMapper.selectTeacherMapList();
    }
}
