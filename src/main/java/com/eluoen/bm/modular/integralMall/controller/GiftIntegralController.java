package com.eluoen.bm.modular.integralMall.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.integralMall.service.IGiftIntegralService;
import com.eluoen.bm.modular.system.model.GiftIntegral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 积分管理控制器
 *
 * @author fengshuonan
 * @Date 2019-01-03 16:29:41
 */
@Controller
@RequestMapping("/giftIntegral")
public class GiftIntegralController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(GiftIntegralController.class);

    private String PREFIX = "/integralMall/giftIntegral/";

    @Autowired
    private IGiftIntegralService giftIntegralService;

    /**
     * 跳转到积分管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "giftIntegral.html";
    }

    /**
     * 跳转到添加积分管理
     */
    @RequestMapping("/giftIntegral_add")
    public String giftIntegralAdd() {
        return PREFIX + "giftIntegral_add.html";
    }

    /**
     * 跳转到修改积分管理
     */
    @RequestMapping("/giftIntegral_update/{giftIntegralId}")
    public String giftIntegralUpdate(@PathVariable Integer giftIntegralId, Model model) {
        GiftIntegral giftIntegral = giftIntegralService.selectById(giftIntegralId);
        model.addAttribute("item",giftIntegral);
        LogObjectHolder.me().set(giftIntegral);
        return PREFIX + "giftIntegral_edit.html";
    }

    /**
     * 获取积分管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String startdate, String enddate, String condition) {
        return giftIntegralService.selectList(startdate,enddate,condition);
    }

    /**
     * 新增积分管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(GiftIntegral giftIntegral) {
        giftIntegralService.insert(giftIntegral);
        return SUCCESS_TIP;
    }

    /**
     * 删除积分管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer giftIntegralId) {
        giftIntegralService.deleteById(giftIntegralId);
        return SUCCESS_TIP;
    }

    /**
     * 修改积分管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(GiftIntegral giftIntegral) {
        giftIntegralService.updateById(giftIntegral);
        return SUCCESS_TIP;
    }

    /**
     * 积分管理详情
     */
    @RequestMapping(value = "/detail/{giftIntegralId}")
    @ResponseBody
    public Object detail(@PathVariable("giftIntegralId") Integer giftIntegralId) {
        return giftIntegralService.selectById(giftIntegralId);
    }
}
