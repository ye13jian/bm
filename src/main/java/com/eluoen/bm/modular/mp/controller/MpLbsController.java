package com.eluoen.bm.modular.mp.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eluoen.bm.core.tips.ErrorTip;
import com.eluoen.bm.core.tips.SuccessTip;
import com.eluoen.bm.modular.mp.service.IMpIntegralMallService;
import com.eluoen.bm.modular.mp.service.IMpLbsService;
import com.eluoen.bm.modular.mp.service.IMpService;
import com.eluoen.bm.modular.mp.util.MpUtil;
import com.eluoen.bm.modular.mp.util.StringUtil;
import com.eluoen.bm.modular.mp.util.WeiXinInterfacetUtil;
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
    private IMpLbsService mpLbsService;


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

    /**
     * 找朋友入口
     * @param model
     * @param request
     * @return
     */
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
            log.info("redirect:/mp/lbs/friend?latLng="+latLng);
            //return PREFIX + "friend.html";
            return "redirect:/mp/lbs/friend?latLng="+latLng;
        }



    }

    /**
     * 朋友列表界面
     * @param latLng
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/friend")
    public String friend(String latLng, Model model, HttpServletRequest request){

        String openid = getSessionOpenid(model,request);
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        String slatLng = (String) request.getSession().getAttribute("latLng");

        Map<String,Object> map = new HashMap<String,Object>();
        if(slatLng==null){

            map = MpUtil.getTencentGeocoder(latLng);
            if(map!=null){
                map.put("openid",openid);
                map.put("userid",userId);
                mpLbsService.replaceMemberLocation(map);
            }

        }else{
            String[] latLngs = slatLng.split(",");
            map.put("latitude",latLngs[0]);
            map.put("longitude",latLngs[1]);
            map.put("openid",openid);
            map.put("userid",userId);
        }

        List<Map<String,Object>> openidList = mpLbsService.selectMemberLocationByOpenid(map);
        List<Map<String,Object>> useridList = mpLbsService.selectMemberLocationByUserid(map);

        model.addAttribute("openidList",openidList);
        model.addAttribute("useridList",useridList);

        request.getSession().setAttribute("latLng",latLng);

        return PREFIX + "friend.html";
    }

    /**
     * 找到朋友，点击主动向朋友发送微信通知
     * @param touser
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendMessage")
    @ResponseBody
    public Object sendMessage(Integer templateId, String touser, Model model, HttpServletRequest request){
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "../home_start.html";

        Map<String,Object> template = this.mpLbsService.getTemplateInfo(templateId);
        Map<String,Object> memberLocation = this.mpLbsService.getMemberLocation(openid);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("touser",touser);
        map.put("template_id",template.get("templateid"));
        map.put("url",template.get("url")+"?touser="+openid);

        Map<String,Object> data = new HashMap<String,Object>();
        Map<String,Object> keyword1 = new HashMap<String,Object>();
        keyword1.put("value",memberLocation.get("nickname"));
        keyword1.put("color","#173177");
        data.put("keyword1",keyword1);
        Map<String,Object> keyword2 = new HashMap<String,Object>();
        keyword2.put("value",memberLocation.get("recommend"));
        keyword2.put("color","#173177");
        data.put("keyword2",keyword2);
        Map<String,Object> remark = new HashMap<String,Object>();
        remark.put("value",template.get("remark"));
        remark.put("color","#173177");
        data.put("remark",remark);

        map.put("data",data);

        String params = JSONObject.toJSONString(map);
        WeiXinInterfacetUtil.templateSend(params);

        //保存为首次对话信息
        Map<String,Object> dialogue = new HashMap<String,Object>();
        dialogue.put("openid",openid);
        dialogue.put("touser",touser);
        dialogue.put("message","Hi");
        mpLbsService.addDialogue(dialogue);

        return new SuccessTip();
    }

    /**
     * 收到通知后，点击跳转到聊天界面
     * @param touser
     * @return
     */
    @RequestMapping(value = "/dialogueTo")
    public String dialogueTo(String code, String touser, Model model, HttpServletRequest request){
        String openid = getSessionOpenid(model,request);

        if(openid==null){
            if(code!=null){

                // 获取微信网页授权
                JSONObject jsonObject = WeiXinInterfacetUtil.oauth2AccessTokenMap(code);
                if (jsonObject == null || jsonObject.get("access_token") == null|| jsonObject.get("openid") == null) {
                    model.addAttribute("message",jsonObject.toJSONString());
                    return PREFIX + "../home_error.html";
                }
                openid = jsonObject.get("openid").toString();
                request.getSession().setAttribute("openid",openid);

            }else{
                model.addAttribute("touser",touser);
                return PREFIX + "dialogue_start.html";
            }
        }

        Map<String,Object> to = mpLbsService.getMemberLocation(touser);
        Map<String,Object> me = mpLbsService.getMemberLocation(openid);
        model.addAttribute("to",to);
        model.addAttribute("me",me);
        model.addAttribute("resp","1");

        return PREFIX + "dialogue.html";
    }

    /**
     * 响应收到通知后，点击跳转到聊天界面
     * @param touser
     * @return
     */
    @RequestMapping(value = "/dialogueResp")
    public String dialogueResp(String code, String touser, Model model, HttpServletRequest request){
        String openid = getSessionOpenid(model,request);

        if(openid==null){
            if(code!=null){

                // 获取微信网页授权
                JSONObject jsonObject = WeiXinInterfacetUtil.oauth2AccessTokenMap(code);
                if (jsonObject == null || jsonObject.get("access_token") == null|| jsonObject.get("openid") == null) {
                    model.addAttribute("message",jsonObject.toJSONString());
                    return PREFIX + "../home_error.html";
                }
                openid = jsonObject.get("openid").toString();
                request.getSession().setAttribute("openid",openid);

            }else{
                model.addAttribute("touser",touser);
                return PREFIX + "dialogue_start.html";
            }
        }

        Map<String,Object> to = mpLbsService.getMemberLocation(touser);
        Map<String,Object> me = mpLbsService.getMemberLocation(openid);
        model.addAttribute("to",to);
        model.addAttribute("me",me);
        model.addAttribute("resp","0");

        return PREFIX + "dialogue.html";
    }

    /**
     * 保存对话信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/addDialogue")
    @ResponseBody
    public Object addDialogue(@RequestParam Map<String,Object> map){
        mpLbsService.addDialogue(map);
        return new SuccessTip();
    }

    /**
     * 获取未获取的对话信息
     * @param map
     * @return
     */
    @RequestMapping(value = "/getLastDialogue")
    @ResponseBody
    public Object getLastDialogue(@RequestParam Map<String,Object> map){
        List<Map<String,Object>> dialogues = mpLbsService.getDialogueList(map);
        log.info(JSON.toJSONString(dialogues));
        return dialogues;
    }

}
