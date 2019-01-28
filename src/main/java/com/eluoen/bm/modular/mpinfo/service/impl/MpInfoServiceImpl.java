package com.eluoen.bm.modular.mpinfo.service.impl;

import com.eluoen.bm.modular.mpinfo.service.IMpInfoService;
import com.eluoen.bm.modular.system.dao.MpInfoMapper;
import com.eluoen.bm.modular.system.model.MpInfo;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class MpInfoServiceImpl implements IMpInfoService {

    @Resource
    private MpInfoMapper mpInfoMapper;

    @Override
    public Map<String, Object> getMpInfo(Integer userId) {
        return mpInfoMapper.getMpInfo(userId);
    }

    @Override
    public int setMpInfo(MpInfo mpInfo) {
        return mpInfoMapper.setMpInfo(mpInfo);
    }
}
