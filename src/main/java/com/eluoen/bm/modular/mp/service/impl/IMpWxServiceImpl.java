package com.eluoen.bm.modular.mp.service.impl;

import com.eluoen.bm.modular.mp.service.IMpWxService;
import com.eluoen.bm.modular.system.dao.MpWxMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class IMpWxServiceImpl implements IMpWxService {

    @Resource
    private MpWxMapper mpWxMapper;

    @Override
    public List<String> searchMemberTagName(String openid) {
        return this.mpWxMapper.searchMemberTagName(openid);
    }

    @Override
    public Integer searchTag(String tagName) {
        return this.mpWxMapper.searchTag(tagName);
    }

    @Override
    public void addTag(String tagName,Integer tagId) {
        this.mpWxMapper.addTag(tagName,tagId);
    }

    @Override
    public String searchWxReply(String keyword) {
        return this.mpWxMapper.searchWxReply(keyword);
    }

}
