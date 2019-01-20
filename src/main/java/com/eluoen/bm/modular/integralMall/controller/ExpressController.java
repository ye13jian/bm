package com.eluoen.bm.modular.integralMall.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.integralMall.service.IExpressService;
import com.eluoen.bm.modular.system.model.Express;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 快递管理控制器
 *
 * @author fengshuonan
 * @Date 2019-01-14 14:04:18
 */
@Controller
@RequestMapping("/express")
public class ExpressController extends BaseController {

    private String PREFIX = "/integralMall/express/";

    @Autowired
    private IExpressService expressService;

    /**
     * 跳转到快递管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "express.html";
    }

    /**
     * 跳转到添加快递管理
     */
    @RequestMapping("/express_add")
    public String expressAdd() {
        return PREFIX + "express_add.html";
    }

    /**
     * 跳转到修改快递管理
     */
    @RequestMapping("/express_update/{expressId}")
    public String expressUpdate(@PathVariable Integer expressId, Model model) {
        Express express = expressService.selectById(expressId);
        model.addAttribute("item",express);
        LogObjectHolder.me().set(express);
        return PREFIX + "express_edit.html";
    }

    /**
     * 获取快递管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)){
            return expressService.selectList(null);
        }else{
            EntityWrapper<Express> entityWrapper = new EntityWrapper<Express>();
            Wrapper<Express> wrapper = entityWrapper.like("exp_name",condition);
            return expressService.selectList(wrapper);
        }
    }

    /**
     * 新增快递管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Express express) {
        expressService.insert(express);
        return SUCCESS_TIP;
    }

    /**
     * 删除快递管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer expressId) {
        expressService.deleteById(expressId);
        return SUCCESS_TIP;
    }

    /**
     * 修改快递管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Express express) {
        expressService.updateById(express);
        return SUCCESS_TIP;
    }

    /**
     * 快递管理详情
     */
    @RequestMapping(value = "/detail/{expressId}")
    @ResponseBody
    public Object detail(@PathVariable("expressId") Integer expressId) {
        return expressService.selectById(expressId);
    }

    /**
     * 启用或停止
     * @param expressId
     * @param status
     * @return
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public Object updateStatus(Integer expressId,Integer status){
        Express express = new Express();
        express.setId(expressId);
        express.setStatus(status);
        expressService.updateById(express);
        return SUCCESS_TIP;
    }

}
