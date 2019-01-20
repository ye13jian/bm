package com.eluoen.bm.modular.bbs.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.eluoen.bm.core.common.constant.state.ManagerStatus;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.bbs.service.IBbsSysService;
import com.eluoen.bm.modular.system.model.BbsSys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 置顶文章控制器
 *
 * @author fengshuonan
 * @Date 2018-12-10 10:47:17
 */
@Controller
@RequestMapping("/bbsSys")
public class BbsSysController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(BbsSysController.class);

    private String PREFIX = "/bbs/bbsSys/";

    @Autowired
    private IBbsSysService bbsSysService;

    /**
     * 跳转到置顶文章首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bbsSys.html";
    }

    /**
     * 跳转到添加置顶文章
     */
    @RequestMapping("/bbsSys_add")
    public String bbsSysAdd() {
        return PREFIX + "bbsSys_add.html";
    }

    /**
     * 跳转到修改置顶文章
     */
    @RequestMapping("/bbsSys_update/{bbsSysId}")
    public String bbsSysUpdate(@PathVariable Integer bbsSysId, Model model) {
        BbsSys bbsSys = bbsSysService.selectById(bbsSysId);
        model.addAttribute("item",bbsSys);
        LogObjectHolder.me().set(bbsSys);
        log.info("bbsSys:"+bbsSys.toString());
        return PREFIX + "bbsSys_edit.html";
    }

    /**
     * 获取置顶文章列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bbsSysService.selectList(null);
    }

    /**
     * 新增置顶文章
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BbsSys bbsSys) {

        //log.info("bbsSys:"+bbsSys.toString());
        String htmlstr = bbsSys.getContent();
        htmlstr = htmlstr.replaceAll("& ","&");
        bbsSys.setContent(htmlstr);


        // 完善书籍信息
        bbsSys.setStatus(ManagerStatus.OK.getCode());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        bbsSys.setCreatedate(sdf1.format(new Date()));
        bbsSys.setCreatetime(sdf2.format(new Date()));

        bbsSysService.insert(bbsSys);
        return SUCCESS_TIP;
    }

    /**
     * 删除置顶文章
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bbsSysId) {
        bbsSysService.deleteById(bbsSysId);
        return SUCCESS_TIP;
    }

    /**
     * 修改置顶文章
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BbsSys bbsSys) {

        String htmlstr = bbsSys.getContent();
        htmlstr = htmlstr.replaceAll("& ","&");
        bbsSys.setContent(htmlstr);

        bbsSysService.updateById(bbsSys);
        return SUCCESS_TIP;
    }

    /**
     * 置顶文章详情
     */
    @RequestMapping(value = "/detail/{bbsSysId}")
    @ResponseBody
    public Object detail(@PathVariable("bbsSysId") Integer bbsSysId) {
        return bbsSysService.selectById(bbsSysId);
    }
}
