package com.eluoen.bm.modular.market.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.market.service.IQrcodeAuthService;
import com.eluoen.bm.modular.system.model.QrcodeAuth;
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
 * 会员认证控制器
 *
 * @author fengshuonan
 * @Date 2018-12-08 22:21:11
 */
@Controller
@RequestMapping("/qrcodeAuth")
public class QrcodeAuthController extends BaseController {

    private Logger log = LoggerFactory.getLogger(QrcodeAuthController.class);

    private String PREFIX = "/market/qrcodeAuth/";

    @Autowired
    private IQrcodeAuthService qrcodeAuthService;

    /**
     * 跳转到会员认证首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrcodeAuth.html";
    }

    /**
     * 跳转到添加会员认证
     */
    @RequestMapping("/qrcodeAuth_add")
    public String qrcodeAuthAdd() {
        return PREFIX + "qrcodeAuth_add.html";
    }

    /**
     * 跳转到修改会员认证
     */
    @RequestMapping("/qrcodeAuth_update/{qrcodeAuthId}")
    public String qrcodeAuthUpdate(@PathVariable Integer qrcodeAuthId, Model model) {
        QrcodeAuth qrcodeAuth = qrcodeAuthService.selectById(qrcodeAuthId);
        model.addAttribute("item",qrcodeAuth);
        LogObjectHolder.me().set(qrcodeAuth);
        return PREFIX + "qrcodeAuth_edit.html";
    }

    /**
     * 获取会员认证列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //return qrcodeAuthService.selectList(null);

        Integer userId = (Integer) super.getSession().getAttribute("userId");
        log.info("qrcodeAuth list:"+userId);
        return qrcodeAuthService.selectList_U(userId,condition);
    }

    /**
     * 新增会员认证
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(QrcodeAuth qrcodeAuth) {
        qrcodeAuthService.insert(qrcodeAuth);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员认证
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer qrcodeAuthId) {
        qrcodeAuthService.deleteById(qrcodeAuthId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员认证
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(QrcodeAuth qrcodeAuth) {
        qrcodeAuthService.updateById(qrcodeAuth);
        return SUCCESS_TIP;
    }

    /**
     * 会员认证详情
     */
    @RequestMapping(value = "/detail/{qrcodeAuthId}")
    @ResponseBody
    public Object detail(@PathVariable("qrcodeAuthId") Integer qrcodeAuthId) {
        return qrcodeAuthService.selectById(qrcodeAuthId);
    }
}
