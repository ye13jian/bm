package com.eluoen.bm.modular.integralMall.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.integralMall.service.IExpressService;
import com.eluoen.bm.modular.integralMall.service.IGiftExchangeService;
import com.eluoen.bm.modular.system.model.Express;
import com.eluoen.bm.modular.system.model.GiftExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 礼品兑换控制器
 *
 * @author fengshuonan
 * @Date 2019-01-03 16:29:20
 */
@Controller
@RequestMapping("/giftExchange")
public class GiftExchangeController extends BaseController {

    private String PREFIX = "/integralMall/giftExchange/";

    @Autowired
    private IGiftExchangeService giftExchangeService;
    @Autowired
    private IExpressService expressService;

    /**
     * 跳转到礼品兑换首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "giftExchange.html";
    }

    /**
     * 跳转到添加礼品兑换
     */
    @RequestMapping("/giftExchange_add")
    public String giftExchangeAdd() {
        return PREFIX + "giftExchange_add.html";
    }

    /**
     * 跳转到修改礼品兑换
     */
    @RequestMapping("/giftExchange_update/{giftExchangeId}")
    public String giftExchangeUpdate(@PathVariable Integer giftExchangeId, Model model) {
        Map<String,Object> giftExchange = giftExchangeService.selectById(giftExchangeId);
        model.addAttribute("item",giftExchange);
        LogObjectHolder.me().set(giftExchange);

        List<Express> expresses = expressService.selectList(null);
        model.addAttribute("expresses",expresses);

        return PREFIX + "giftExchange_edit.html";
    }

    /**
     * 获取礼品兑换列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Map<String,Object>> list(String startdate, String enddate, String condition) {
        List<Map<String,Object>> list =  giftExchangeService.selectList(startdate,enddate,condition);

        return list;
    }

    /**
     * 新增礼品兑换
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(GiftExchange giftExchange) {
        giftExchangeService.insert(giftExchange);
        return SUCCESS_TIP;
    }

    /**
     * 删除礼品兑换
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer giftExchangeId) {
        giftExchangeService.deleteById(giftExchangeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改礼品兑换
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(GiftExchange giftExchange) {
        giftExchangeService.updateById(giftExchange);
        return SUCCESS_TIP;
    }

    /**
     * 礼品兑换详情
     */
    @RequestMapping(value = "/detail/{giftExchangeId}")
    @ResponseBody
    public Object detail(@PathVariable("giftExchangeId") Integer giftExchangeId) {
        return giftExchangeService.selectById(giftExchangeId);
    }
}
