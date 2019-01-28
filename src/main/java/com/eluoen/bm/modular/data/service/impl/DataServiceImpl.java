package com.eluoen.bm.modular.data.service.impl;

import com.eluoen.bm.modular.data.service.IDataService;
import com.eluoen.bm.modular.system.dao.DataMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements IDataService {

    @Resource
    private DataMapper dataMapper;

    @Override
    public List<Map<String, Object>> searchMemberDataByCity(Integer userId) {
        return this.dataMapper.searchMemberDataByCity(userId);
    }

    @Override
    public List<Map<String, Object>> searchMemberLast(Integer userId) {
        return this.dataMapper.searchMemberLast(userId);
    }

    @Override
    public List<Map<String, Object>> searchWxPlayDataByCity(Integer userId) {
        return this.dataMapper.searchWxPlayDataByCity(userId);
    }
}
