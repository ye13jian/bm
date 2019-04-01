package com.eluoen.bm.modular.mp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eluoen.bm.modular.mp.service.IMpService;
import com.eluoen.bm.modular.mp.service.IMpTeachersService;
import com.eluoen.bm.modular.mp.util.WeiXinInterfacetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/mp/teachers")
public class MpTeachersController {

    private final static Logger log = LoggerFactory.getLogger(MpTeachersController.class);

    private String PREFIX = "/mp/teachers/";

    @Autowired
    private IMpTeachersService mpTeachersService;


    @RequestMapping(value = "/auth")
    public String auth(){

        return PREFIX + "auth.html";
    }

    @RequestMapping(value = "/check")
    @ResponseBody
    public Object checkTeacher(String mobile){
        Map<String,Object> teacher = mpTeachersService.selectTeacherByMobile(mobile);
        log.info(JSON.toJSONString(teacher));
        return teacher;
    }

    @RequestMapping(value = "/bookcatalogList")
    public String dialogueTo(Model model, HttpServletRequest request){


        List<Map<String,Object>> bcs = mpTeachersService.selectTeacherMapList();
        model.addAttribute("bcs",bcs);
        model.addAttribute("bcsjson",JSON.toJSONString(bcs));


        return PREFIX + "bookcatalogList.html";
    }
}
