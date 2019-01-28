package com.eluoen.bm.modular.mpinfo.controller;

import com.alibaba.fastjson.JSONObject;
import com.eluoen.bm.core.shiro.ShiroUser;
import com.eluoen.bm.modular.mpinfo.service.IMpInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.eluoen.bm.modular.system.model.MpInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/mpInfo")
public class MpInfoController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(MpInfoController.class);

    private String PREFIX = "/mpinfo/";

    @Autowired
    private IMpInfoService mpInfoService;

    /**
     * 查询微信页面设置
     * @return
     */
    @RequestMapping(value = "/get/{toId}")
    public String getMpInfo(@PathVariable(value = "toId") String toId, Model model){
        Integer userId = (Integer) super.getSession().getAttribute("userId");
        Map<String,Object> mpInfo = mpInfoService.getMpInfo(userId);
        log.info("mpInfo:"+mpInfo.toString());
        model.addAttribute("mpInfo",mpInfo);
        return PREFIX + "mpinfo_"+toId+".html";
    }

    /**
     * 更新微信设置
     * @return
     */
    @RequestMapping(value = "/set")
    @ResponseBody
    public Object setMpInfo(MpInfo mpInfo){
        mpInfoService.setMpInfo(mpInfo);
        return SUCCESS_TIP;
    }

}
