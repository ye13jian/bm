package com.eluoen.bm.modular.bbs.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.bbs.service.IBbsWxService;
import com.eluoen.bm.modular.system.model.BbsWx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户帖子控制器
 *
 * @author fengshuonan
 * @Date 2018-12-12 01:15:10
 */
@Controller
@RequestMapping("/bbsWx")
public class BbsWxController extends BaseController {

    private String PREFIX = "/bbs/bbsWx/";

    @Autowired
    private IBbsWxService bbsWxService;

    /**
     * 跳转到用户帖子首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bbsWx.html";
    }

    /**
     * 跳转到添加用户帖子
     */
    @RequestMapping("/bbsWx_add")
    public String bbsWxAdd() {
        return PREFIX + "bbsWx_add.html";
    }

    /**
     * 跳转到修改用户帖子
     */
    @RequestMapping("/bbsWx_update/{bbsWxId}")
    public String bbsWxUpdate(@PathVariable Integer bbsWxId, Model model) {
        BbsWx bbsWx = bbsWxService.selectById(bbsWxId);
        model.addAttribute("item",bbsWx);
        LogObjectHolder.me().set(bbsWx);
        return PREFIX + "bbsWx_edit.html";
    }

    /**
     * 获取用户帖子列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bbsWxService.selectList(null);
    }

    /**
     * 新增用户帖子
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BbsWx bbsWx) {
        bbsWxService.insert(bbsWx);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户帖子
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bbsWxId) {
        bbsWxService.deleteById(bbsWxId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户帖子
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BbsWx bbsWx) {
        bbsWxService.updateById(bbsWx);
        return SUCCESS_TIP;
    }

    /**
     * 用户帖子详情
     */
    @RequestMapping(value = "/detail/{bbsWxId}")
    @ResponseBody
    public Object detail(@PathVariable("bbsWxId") Integer bbsWxId) {
        return bbsWxService.selectById(bbsWxId);
    }
}
