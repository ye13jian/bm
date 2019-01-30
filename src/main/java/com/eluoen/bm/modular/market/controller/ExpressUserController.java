package com.eluoen.bm.modular.market.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.eluoen.bm.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.eluoen.bm.modular.system.model.ExpressUser;
import com.eluoen.bm.modular.market.service.IExpressUserService;

import java.util.List;
import java.util.Map;

/**
 * 我的快递控制器
 *
 * @author fengshuonan
 * @Date 2019-01-28 11:27:35
 */
@Controller
@RequestMapping("/expressUser")
public class ExpressUserController extends BaseController {

    private String PREFIX = "/market/expressUser/";

    @Autowired
    private IExpressUserService expressUserService;

    /**
     * 跳转到我的快递首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "expressUser.html";
    }

    /**
     * 跳转到添加我的快递
     */
    @RequestMapping("/expressUser_add")
    public String expressUserAdd(Model model) {
        List<Map<String,Object>> expresses = expressUserService.selectExpressList();
        model.addAttribute("expresses",expresses);
        return PREFIX + "expressUser_add.html";
    }

    /**
     * 跳转到修改我的快递
     */
    @RequestMapping("/expressUser_update/{expressUserId}")
    public String expressUserUpdate(@PathVariable Integer expressUserId, Model model) {
        ExpressUser expressUser = expressUserService.selectById(expressUserId);
        model.addAttribute("item",expressUser);
        LogObjectHolder.me().set(expressUser);
        return PREFIX + "expressUser_edit.html";
    }

    /**
     * 获取我的快递列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Integer userId = (Integer) super.getSession().getAttribute("userId");
        return expressUserService.selectList_U(userId, condition);
    }

    /**
     * 新增我的快递
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ExpressUser expressUser) {
        expressUserService.insert(expressUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除我的快递
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer expressUserId) {
        expressUserService.deleteById(expressUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的快递
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ExpressUser expressUser) {
        expressUserService.updateById(expressUser);
        return SUCCESS_TIP;
    }

    /**
     * 我的快递详情
     */
    @RequestMapping(value = "/detail/{expressUserId}")
    @ResponseBody
    public Object detail(@PathVariable("expressUserId") Integer expressUserId) {
        return expressUserService.selectById(expressUserId);
    }
}
