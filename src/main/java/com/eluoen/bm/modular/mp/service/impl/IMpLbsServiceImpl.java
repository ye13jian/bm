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

    @Override
    public Map<String, Object> getTemplateInfo(Integer id) {
        return this.mpLbsMapper.getTemplateInfo(id);
    }

    @Override
    public Map<String, Object> getMemberLocation(String openid) {
        return this.mpLbsMapper.getMemberLocation(openid);
    }

    @Override
    public int addDialogue(Map<String, Object> map) {
        return this.mpLbsMapper.addDialogue(map);
    }

    @Override
    public List<Map<String,Object>> getDialogueList(Map<String, Object> map) {
        List<Map<String,Object>> dialogues = this.mpLbsMapper.getDialogueList(map);
        if(dialogues!=null&&dialogues.size()>0){
            String ids = "";
            for(Map<String,Object> dialogue:dialogues){
                ids = ids + "'"+dialogue.get("id")+"',";
            }
            ids = ids.substring(0,ids.length()-1);
            this.mpLbsMapper.updateDialogueStatus(ids);
        }
        return dialogues;
    }
}
