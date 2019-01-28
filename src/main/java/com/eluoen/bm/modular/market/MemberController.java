package com.eluoen.bm.modular.market;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.market.service.IMemberService;
import com.eluoen.bm.modular.system.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 会员信息控制器
 *
 * @author fengshuonan
 * @Date 2018-11-17 23:50:17
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

    private String PREFIX = "/market/member/";

    @Autowired
    private IMemberService memberService;

    /**
     * 跳转到会员信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "member.html";
    }

    /**
     * 跳转到添加会员信息
     */
    @RequestMapping("/member_add")
    public String memberAdd() {
        return PREFIX + "member_add.html";
    }

    /**
     * 跳转到修改会员信息
     */
    @RequestMapping("/member_update/{memberId}")
    public String memberUpdate(@PathVariable Integer memberId, Model model) {
        Member member = memberService.selectById(memberId);
        model.addAttribute("item",member);
        LogObjectHolder.me().set(member);
        return PREFIX + "member_edit.html";
    }

    /**
     * 获取会员信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(ToolUtil.isEmpty(condition)){
            return memberService.selectList(null);
        }else{
            EntityWrapper<Member> entityWrapper = new EntityWrapper<>();
            //Wrapper<Member> wrapper = entityWrapper.like("openid",condition);
            //多字段匹配
            Wrapper<Member> wrapper = entityWrapper.like("CONCAT(openid, nickname, IFNULL(mobile,''), IFNULL(fullname,''))",condition);
            return memberService.selectList(wrapper);
        }
    }

    /**
     * 新增会员信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Member member) {
        memberService.insert(member);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberId) {
        memberService.deleteById(memberId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Member member) {
        memberService.updateById(member);
        return SUCCESS_TIP;
    }

    /**
     * 会员信息详情
     */
    @RequestMapping(value = "/detail/{memberId}")
    @ResponseBody
    public Object detail(@PathVariable("memberId") Integer memberId) {
        return memberService.selectById(memberId);
    }
}
