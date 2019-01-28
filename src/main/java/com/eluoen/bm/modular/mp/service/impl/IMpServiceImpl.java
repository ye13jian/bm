package com.eluoen.bm.modular.mp.service.impl;

import com.eluoen.bm.modular.mp.service.IMpService;
import com.eluoen.bm.modular.system.dao.MpMapper;
import com.eluoen.bm.modular.system.model.BbsWx;
import com.eluoen.bm.modular.system.model.BbsWxReply;
import com.eluoen.bm.modular.system.model.Opinion;
import com.eluoen.bm.modular.system.model.Wxcollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IMpServiceImpl implements IMpService {

    @Resource
    private MpMapper mpMapper;

    @Override
    public Map<String, Object> searchQrcode(String qrcode) {
        return this.mpMapper.searchQrcode(qrcode);
    }

    @Override
    public Map<String, Object> searchQrcodeAuth(String qrcode) { return this.mpMapper.searchQrcodeAuth(qrcode); }

    @Override
    public Map<String, Object> searchBook(String bookid) {
        return this.mpMapper.searchBook(bookid);
    }

    @Override
    public List<Map<String, Object>> searchBookList(String openid) {
        return this.mpMapper.searchBookList(openid);
    }

    @Override
    public List<Map<String, Object>> searchBookCatalogList(String bookid) { return this.mpMapper.searchBookCatalogList(bookid); }

    @Override
    public List<Map<String, Object>> searchCollectionList(String openid) { return this.mpMapper.searchCollectionList(openid); }

    @Override
    public int addCollection(Wxcollection coll) { return this.mpMapper.addCollection(coll); }

    @Override
    public int subCollection(Wxcollection coll) { return this.mpMapper.subCollection(coll); }

    @Override
    public Map<String, Object> searchCollection(Wxcollection coll) { return this.mpMapper.searchCollection(coll); }

    @Override
    public Map<String, Object> searchBookCatalogDetail(Integer bookCatalogId) { return this.mpMapper.searchBookCatalogDetail(bookCatalogId); }

    @Override
    public Map<String, Object> searchMember(String openid) { return this.mpMapper.searchMember(openid); }

    @Override
    public int addMemberAuth(Map<String, Object> map) {
        this.mpMapper.addQrcodeAuth(map);
        this.mpMapper.addMember(map);
        return 0;
    }

    @Override
    public void addWxPlay(Wxcollection coll) {
        this.mpMapper.addWxPlay(coll);
    }

    @Override
    public int searchWxPlayCount(Integer bookcatalogid) {
        return this.mpMapper.searchWxPlayCount(bookcatalogid);
    }

    @Override
    public List<Map<String, Object>> searchBbsSysList() {
        return this.mpMapper.searchBbsSysList();
    }

    @Override
    public Map<String, Object> searchBbsSysDetail(Integer bbsSysId) {
        return this.mpMapper.searchBbsSysDetail(bbsSysId);
    }

    @Override
    public List<Map<String, Object>> searchBbsWxList() {
        return this.mpMapper.searchBbsWxList();
    }

    @Override
    public Map<String, Object> searchBbsWxDetail(Integer bbsWxId) {
        return this.mpMapper.searchBbsWxDetail(bbsWxId);
    }

    @Override
    public List<Map<String, Object>> searchBbsWxReplyList(Integer bbsWxId) {
        return this.mpMapper.searchBbsWxReplyList(bbsWxId);
    }

    @Override
    public void addBbsWx(BbsWx bbsWx) {
        this.mpMapper.addBbsWx(bbsWx);
    }

    @Override
    public void addBbsWxReply(BbsWxReply bbsWxReply) {
        this.mpMapper.addBbsWxReply(bbsWxReply);
    }

    @Override
    public void addOpinion(Opinion opinion) {
        this.mpMapper.addOpinion(opinion);
    }

    @Override
    public void addIntegral(Map<String, Object> map) {
        this.mpMapper.addIntegral(map);
        this.mpMapper.updateMemberScore(map);
    }


}
