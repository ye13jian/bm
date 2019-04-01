package com.eluoen.bm.modular.resource.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.eluoen.bm.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.eluoen.bm.modular.system.model.Teachers;
import com.eluoen.bm.modular.resource.service.ITeachersService;

/**
 * 师资管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-01 21:54:45
 */
@Controller
@RequestMapping("/teachers")
public class TeachersController extends BaseController {

    private String PREFIX = "/resource/teachers/";

    @Autowired
    private ITeachersService teachersService;

    /**
     * 跳转到师资管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "teachers.html";
    }

    /**
     * 跳转到添加师资管理
     */
    @RequestMapping("/teachers_add")
    public String teachersAdd() {
        return PREFIX + "teachers_add.html";
    }

    /**
     * 跳转到修改师资管理
     */
    @RequestMapping("/teachers_update/{teachersId}")
    public String teachersUpdate(@PathVariable Integer teachersId, Model model) {
        Teachers teachers = teachersService.selectById(teachersId);
        model.addAttribute("item",teachers);
        LogObjectHolder.me().set(teachers);
        return PREFIX + "teachers_edit.html";
    }

    /**
     * 获取师资管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return teachersService.selectList(null);
    }

    /**
     * 新增师资管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Teachers teachers) {
        teachersService.insert(teachers);
        return SUCCESS_TIP;
    }

    /**
     * 删除师资管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer teachersId) {
        teachersService.deleteById(teachersId);
        return SUCCESS_TIP;
    }

    /**
     * 修改师资管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Teachers teachers) {
        teachersService.updateById(teachers);
        return SUCCESS_TIP;
    }

    /**
     * 师资管理详情
     */
    @RequestMapping(value = "/detail/{teachersId}")
    @ResponseBody
    public Object detail(@PathVariable("teachersId") Integer teachersId) {
        return teachersService.selectById(teachersId);
    }
}
