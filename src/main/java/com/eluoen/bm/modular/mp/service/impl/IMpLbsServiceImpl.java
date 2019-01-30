package com.eluoen.bm.modular.mp.service.impl;

import com.eluoen.bm.modular.mp.service.IMpLbsService;
import com.eluoen.bm.modular.system.dao.MpLbsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IMpLbsServiceImpl implements IMpLbsService {

    @Resource
    private MpLbsMapper mpLbsMapper;


    @Override
    public int replaceMemberLocation(Map<String, Object> map) {
        return this.mpLbsMapper.replaceMemberLocation(map);
    }

    @Override
    public List<Map<String, Object>> selectMemberLocationByOpenid(Map<String, Object> map) {
        return this.mpLbsMapper.selectMemberLocationByOpenid(map);
    }

    @Override
    public List<Map<String, Object>> selectMemberLocationByUserid(Map<String, Object> map) {
        return this.mpLbsMapper.selectMemberLocationByUserid(map);
    }
}
