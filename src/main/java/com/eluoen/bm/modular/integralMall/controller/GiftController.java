package com.eluoen.bm.modular.integralMall.controller;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.eluoen.bm.core.common.constant.state.ManagerStatus;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.integralMall.service.IGiftService;
import com.eluoen.bm.modular.system.model.Gift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 礼品管理控制器
 *
 * @author fengshuonan
 * @Date 2019-01-03 16:28:52
 */
@Controller
@RequestMapping("/gift")
public class GiftController extends BaseController {

    private String PREFIX = "/integralMall/gift/";

    @Autowired
    private IGiftService giftService;

    /**
     * 跳转到礼品管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "gift.html";
    }

    /**
     * 跳转到添加礼品管理
     */
    @RequestMapping("/gift_add")
    public String giftAdd() {
        return PREFIX + "gift_add.html";
    }

    /**
     * 跳转到修改礼品管理
     */
    @RequestMapping("/gift_update/{giftId}")
    public String giftUpdate(@PathVariable Integer giftId, Model model) {
        Gift gift = giftService.selectById(giftId);
        model.addAttribute("item",gift);
        LogObjectHolder.me().set(gift);
        return PREFIX + "gift_edit.html";
    }

    /**
     * 获取礼品管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)){
            return giftService.selectList(null);
        }else{
            EntityWrapper<Gift> entityWrapper = new EntityWrapper<Gift>();
            Wrapper<Gift> wrapper = entityWrapper.like("name",condition);
            return giftService.selectList(wrapper);
        }
    }

    /**
     * 新增礼品管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Gift gift) {

        // 完善礼品信息
        gift.setStatus(ManagerStatus.OK.getCode());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        gift.setCreatedate(sdf1.format(new Date()));
        gift.setCreatetime(sdf2.format(new Date()));

        giftService.insert(gift);
        return SUCCESS_TIP;
    }

    /**
     * 删除礼品管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer giftId) {
        giftService.deleteById(giftId);
        return SUCCESS_TIP;
    }

    /**
     * 修改礼品管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Gift gift) {
        giftService.updateById(gift);
        return SUCCESS_TIP;
    }

    /**
     * 礼品管理详情
     */
    @RequestMapping(value = "/detail/{giftId}")
    @ResponseBody
    public Object detail(@PathVariable("giftId") Integer giftId) {
        return giftService.selectById(giftId);
    }
}
