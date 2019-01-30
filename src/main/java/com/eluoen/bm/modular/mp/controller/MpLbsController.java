package com.eluoen.bm.modular.mp.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.alibaba.fastjson.JSONObject;
import com.eluoen.bm.core.tips.ErrorTip;
import com.eluoen.bm.core.tips.SuccessTip;
import com.eluoen.bm.modular.mp.service.IMpIntegralMallService;
import com.eluoen.bm.modular.mp.service.IMpService;
import com.eluoen.bm.modular.mp.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/mp/lbs")
public class MpLbsController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(MpLbsController.class);

    private String PREFIX = "/mp/lbs/";

    @Autowired
    private IMpIntegralMallService mpIntegralMallService;
    @Autowired
    private IMpService mpService;

    /**
     * 统一校验方法，检验session是否有openid
     * @param model
     * @param request
     * @return
     */
    public String getSessionOpenid(Model model, HttpServletRequest request){
        String openid = (String) request.getSession().getAttribute("openid");
        if(openid==null){
            model.addAttribute("IP",StringUtil.IP);
            model.addAttribute("appid",StringUtil.AppId);
        }
        return openid;
    }

    @RequestMapping(value = "/friendStart")
    public String friendStart(Model model, HttpServletRequest request){
        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "../home_start.html";

        String latLng = (String) request.getSession().getAttribute("latLng");
        if(latLng==null){
            String js_ticket = (String) request.getSession().getAttribute("js_ticket");
            String url = request.getRequestURL().toString();
            Map<String,String> ret = StringUtil.sign(js_ticket,url);
            model.addAttribute("ret",ret);
            return PREFIX + "friend_start.html";
        }else{
            //return PREFIX + "friend.html";
            return "redirect:/friend?latLng="+latLng;
        }



    }

    @RequestMapping(value = "/friend")
    public String friend(String latLng, Model model, HttpServletRequest request){

        log.info("latLng:"+latLng);



        return PREFIX + "friend.html";
    }

}
