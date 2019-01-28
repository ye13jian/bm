package com.eluoen.bm.modular.mp.controller;

import com.alibaba.fastjson.JSONObject;
import cn.stylefeng.roses.core.base.controller.BaseController;
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
@RequestMapping(value = "/mp/integralmall")
public class MpIntegralMallController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(MpIntegralMallController.class);

    private String PREFIX = "/mp/integralmall/";

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

    @RequestMapping(value = "/home")
    public String integralMallHome(Model model, HttpServletRequest request){
        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "../home_start.html";

        Integer userId = (Integer) request.getSession().getAttribute("userId");
        List<Map<String,Object>> gifts = mpIntegralMallService.searchGiftList(userId);
        model.addAttribute("gifts",gifts);

        return PREFIX + "home.html";
    }

    @RequestMapping(value = "/detail/{giftId}")
    public String giftDetail(@PathVariable(value = "giftId") String giftId, Model model, HttpServletRequest request){
        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "../home_start.html";
        Map<String,Object> gift = mpIntegralMallService.searchGift(giftId);
        Map<String,Object> member = mpService.searchMember(openid);
        model.addAttribute("gift",gift);
        model.addAttribute("member",member);
        return PREFIX + "detail.html";
    }

    @RequestMapping(value = "/confirm_add")
    public String confirmAdd(String giftId, Integer count, Model model, HttpServletRequest request){
        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "../home_start.html";

        Map<String,Object> gift = mpIntegralMallService.searchGift(giftId);
        model.addAttribute("gift",gift);
        model.addAttribute("count",count);

        Map<String,Object> info = new HashMap<String,Object>();
        model.addAttribute("info",info);

        String js_ticket = (String) request.getSession().getAttribute("js_ticket");
        String url = request.getRequestURL().toString() + "?giftId="+giftId+"&count="+count;
        Map<String,String> ret = StringUtil.sign(js_ticket,url);
        model.addAttribute("ret",ret);

        return PREFIX + "confirm.html";
    }

    @RequestMapping(value = "/confirm")
    @ResponseBody
    public Object confirm(@RequestParam Map<String,Object> map, Model model, HttpServletRequest request){
        log.info("map:"+JSONObject.toJSONString(map));
        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "../home_start.html";
        Map<String,Object> member = mpService.searchMember(openid);

        if(member!=null){
            Integer exchangeScore = Integer.parseInt(map.get("score").toString());
            Integer memberScore = (Integer) member.get("score");
            if(memberScore>=exchangeScore){
                map.put("openid",openid);

                mpIntegralMallService.insertExchange(map);

                return new SuccessTip();
            }else{
                return new ErrorTip(-1,"兑换失败：积分不足");
            }
        }else{
            return new ErrorTip(-1,"兑换失败：用户不存在");
        }

    }

    /**
     * 我的积分商城兑换订单
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderList")
    public String order(String orderType, Model model, HttpServletRequest request){
        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "../home_start.html";

        List<Map<String,Object>> orderList = mpIntegralMallService.searchOrderList(openid);
        model.addAttribute("orderList",orderList);
        model.addAttribute("orderType",orderType);
        return PREFIX + "order.html";
    }

}
