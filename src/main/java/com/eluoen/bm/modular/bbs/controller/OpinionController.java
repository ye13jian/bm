package com.eluoen.bm.modular.bbs.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.bbs.service.IOpinionService;
import com.eluoen.bm.modular.system.model.Opinion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 意见建议控制器
 *
 * @author fengshuonan
 * @Date 2018-12-12 00:31:58
 */
@Controller
@RequestMapping("/opinion")
public class OpinionController extends BaseController {

    private String PREFIX = "/bbs/opinion/";

    @Autowired
    private IOpinionService opinionService;

    /**
     * 跳转到意见建议首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "opinion.html";
    }

    /**
     * 跳转到添加意见建议
     */
    @RequestMapping("/opinion_add")
    public String opinionAdd() {
        return PREFIX + "opinion_add.html";
    }

    /**
     * 跳转到修改意见建议
     */
    @RequestMapping("/opinion_update/{opinionId}")
    public String opinionUpdate(@PathVariable Integer opinionId, Model model) {
        Opinion opinion = opinionService.selectById(opinionId);
        model.addAttribute("item",opinion);
        LogObjectHolder.me().set(opinion);
        return PREFIX + "opinion_edit.html";
    }

    /**
     * 获取意见建议列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Integer userId = (Integer) super.getSession().getAttribute("userId");
        return opinionService.selectList_U(userId,condition);
    }

    /**
     * 新增意见建议
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Opinion opinion) {
        opinionService.insert(opinion);
        return SUCCESS_TIP;
    }

    /**
     * 删除意见建议
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer opinionId) {
        opinionService.deleteById(opinionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改意见建议
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Opinion opinion) {
        opinionService.updateById(opinion);
        return SUCCESS_TIP;
    }

    /**
     * 意见建议详情
     */
    @RequestMapping(value = "/detail/{opinionId}")
    @ResponseBody
    public Object detail(@PathVariable("opinionId") Integer opinionId) {
        return opinionService.selectById(opinionId);
    }
}
