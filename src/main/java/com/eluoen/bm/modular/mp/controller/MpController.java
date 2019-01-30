package com.eluoen.bm.modular.mp.controller;

import com.alibaba.fastjson.JSONObject;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.eluoen.bm.core.tips.ErrorTip;
import com.eluoen.bm.core.tips.SuccessTip;
import com.eluoen.bm.modular.mp.service.IMpService;
import com.eluoen.bm.modular.mp.util.MpUtil;
import com.eluoen.bm.modular.mp.util.StringUtil;
import com.eluoen.bm.modular.mp.util.WeiXinInterfacetUtil;
import com.eluoen.bm.modular.mpinfo.service.IMpInfoService;
import com.eluoen.bm.modular.system.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/mp")
public class MpController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(MpController.class);

    private String PREFIX = "/mp/";

    @Autowired
    private IMpService mpService;

    @Autowired
    private IMpInfoService mpInfoService;

    /**
     * 统一校验方法，检验session是否有openid
     * @param model
     * @param request
     * @return
     */
    public String getSessionOpenid(Model model, HttpServletRequest request){
        String openid = (String) request.getSession().getAttribute("openid");
        if(openid==null){
            model.addAttribute("IP",StringUtil.IP);
            model.addAttribute("appid",StringUtil.AppId);
        }
        return openid;
    }

    /**
     * 开始扫描，通过微信验证
     * @param qrcode
     * @param model
     * @return
     */
    @RequestMapping(value = "/s/{qrcode}")
    public String scan(@PathVariable(value = "qrcode") String qrcode, Model model){
        //log.info("s qrcode:"+qrcode);
        model.addAttribute("qrcode",qrcode);
        model.addAttribute("IP",StringUtil.IP);
        model.addAttribute("appid",StringUtil.AppId);
        return PREFIX + "scan_start.html";
    }

    /**
     * 扫描验证结果
     * @param qrcode
     * @param model
     * @return
     */
    @RequestMapping(value = "/sr")
    public String scanResult(String code, String qrcode, Model model, HttpServletRequest request){
        //log.info("sr"+code+" "+qrcode);

        try {

            model.addAttribute("qrcode",qrcode);
            int sign = 0;
            String openid = "";

            //验证二维码
            Map<String,Object> qrcodeInfo = mpService.searchQrcode(qrcode);
            if(qrcodeInfo==null){
                sign = 1;//防伪码错误
                model.addAttribute("sign",sign);
                return PREFIX + "scan_error.html";

            }else{

                //查询是否已经认证
                Map<String,Object> qrcodeAuthInfo = mpService.searchQrcodeAuth(qrcode);
                if(qrcodeAuthInfo!=null){
                    //表示已经认证过了
                    sign = 3;
                    //有可能扫的别人认证的码，所以这里不能session
                    //request.getSession().setAttribute("openid",qrcodeAuthInfo.get("openid").toString());
                }else{

                    sign = 4;

                    // 获取微信网页授权
                    JSONObject jsonObject = WeiXinInterfacetUtil.oauth2AccessTokenMap(code);
                    if (jsonObject == null || jsonObject.get("access_token") == null|| jsonObject.get("openid") == null) {
                        //System.out.println("ScanAuthAction重新定向...rerequest");
                        return null;
                    }
                    String accessToken = jsonObject.get("access_token").toString();
                    openid = jsonObject.get("openid").toString();
                    //System.out.println("reOpenid:" + reOpenid);

                    // 网页获取用户基本信息插入数据库
                    JSONObject wx_user = WeiXinInterfacetUtil.userinfoMap(accessToken, openid);

                    //System.out.println("nickname:" + wx_user.get("nickname")+ ",headimgurl:" + wx_user.get("headimgurl"));

                    //String nickname = wx_user.get("nickname").toString();
                    //try {
                        //nickname = URLEncoder.encode(nickname, "utf-8");
                    //} catch (UnsupportedEncodingException e) {
                        //e.printStackTrace();
                    //}

                    qrcodeInfo.putAll(wx_user);

                    //获取当前IP
                    String addr = MpUtil.getIpAddr(request);
                    Map<String,String> map = MpUtil.getBaiduLocation(addr);
                    qrcodeInfo.putAll(map);
                    //log.info("qrcodeInfo:"+qrcodeInfo);

                    int index = mpService.addMemberAuth(qrcodeInfo);

                    request.getSession().setAttribute("openid",openid);

                }

                //查询书籍
                Map<String,Object> bookInfo = mpService.searchBook(qrcodeInfo.get("bookid").toString());
                if(bookInfo==null){
                    sign = 2;//码未和书籍绑定
                    model.addAttribute("sign",sign);
                    return PREFIX + "scan_error.html";
                }

                //首次认证，新增积分
                if(sign==4){
                    Integer score = (Integer) bookInfo.get("score");
                    if(score>0){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("message",qrcode);
                        map.put("score",score);
                        map.put("openid",openid);
                        mpService.addIntegral(map);
                    }
                }

                Integer userId = (Integer) bookInfo.get("userid");
                Map<String,Object> mpInfo = mpInfoService.getMpInfo(userId);
                request.getSession().setAttribute("mpInfo",mpInfo);
                model.addAttribute("sign",sign);
                model.addAttribute("book",bookInfo);

                return PREFIX + "scan_result.html";

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 主页
     * @param code
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/home")
    public String home(Integer userId, String code, Model model, HttpServletRequest request){
        //检验是否有session
        String openid = (String) request.getSession().getAttribute("openid");
        //openid = "o5kXljm1CXR4fopA_mEA8z8lW5Rw";//测试用
        log.info("openid:"+openid);
        log.info("userId:"+userId);
        if(openid==null){
            if(code!=null){

                // 获取微信网页授权
                JSONObject jsonObject = WeiXinInterfacetUtil.oauth2AccessTokenMap(code);
                if (jsonObject == null || jsonObject.get("access_token") == null|| jsonObject.get("openid") == null) {
                    model.addAttribute("message",jsonObject.toJSONString());
                    return PREFIX + "home_error.html";
                }
                //网页授权accessToken
                //String accessToken = jsonObject.get("access_token").toString();
                openid = jsonObject.get("openid").toString();
                //System.out.println("reOpenid:" + openid);

                // 网页获取用户基本信息插入数据库
                //JSONObject wx_user = WeiXinInterfacetUtil.userinfoMap(accessToken, reOpenid);

            }else{
                model.addAttribute("userId",userId);
                model.addAttribute("IP",StringUtil.IP);
                model.addAttribute("appid",StringUtil.AppId);
                return PREFIX + "home_start.html";
            }
        }

        Map<String,Object> wxuser = mpService.searchMember(openid);
        if(wxuser==null){
            model.addAttribute("message","你还不是会员，请先认证！");
            return PREFIX + "home_error.html";
        }

        //MpInfo mpInfo = (MpInfo) request.getSession().getAttribute("mpInfo");
        //if(mpInfo==null){
            //mpInfo = mpInfoService.getMpInfo();
            //request.getSession().setAttribute("mpInfo",mpInfo);
        //}
        if(userId==null){
            userId = (Integer) request.getSession().getAttribute("userId");
        }
        Map<String,Object> mpInfo = mpInfoService.getMpInfo(userId);
        request.getSession().setAttribute("mpInfo",mpInfo);
        request.getSession().setAttribute("openid",openid);
        request.getSession().setAttribute("userId",Integer.parseInt(mpInfo.get("userid").toString()));

        return PREFIX + "home.html";
    }

    /**
     * 获取js_ticket
     * @param request
     * @return
     */
    @RequestMapping(value = "/js_ticket")
    @ResponseBody
    public Object getJweixin(HttpServletRequest request){
        JSONObject token = WeiXinInterfacetUtil.accessToken();
        String access_token = token.get("access_token").toString();
        JSONObject jsapi_ticket = WeiXinInterfacetUtil.jsapiTicket(access_token);
        String js_ticket = jsapi_ticket.get("ticket").toString();
        //Map<String,String> ret = StringUtil.sign(ticket,request.getRequestURL().toString());
        request.getSession().setAttribute("js_ticket",js_ticket);
        return js_ticket;
    }

    /**
     * 新增积分
     * @param openid
     * @param score
     * @param message
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/addIntegral", method=RequestMethod.POST)
    @ResponseBody
    public Object addIntegral(String openid, Integer score, String message, Model model, HttpServletRequest request){
        //首先校验session
        String sOpenid = getSessionOpenid(model,request);
        if(sOpenid!=null&&sOpenid.equals(openid)) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("message",message);
            map.put("score",score);
            map.put("openid",openid);
            mpService.addIntegral(map);
            return new SuccessTip();
        }else{
            return new ErrorTip(-1,"Invalid request");
        }

    }

    /**
     * 会员可看的书籍列表
     * @return
     */
    @RequestMapping(value = "/bookList")
    public String booklist(Model model, HttpServletRequest request){
        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "home_start.html";

        List<Map<String,Object>> bookList = mpService.searchBookList(openid);
        model.addAttribute("bookList",bookList);

        return PREFIX + "booklist.html";
    }

    /**
     * 获取书籍的目录列表
     * @param bookId
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/bookCatalogList/{bookId}")
    public String bookCatalogList(@PathVariable String bookId, Model model, HttpServletRequest request){
        //首先校验session
        if(getSessionOpenid(model,request)==null) return PREFIX + "home_start.html";

        List<Map<String,Object>> bookCatalogList = mpService.searchBookCatalogList(bookId);
        model.addAttribute("bookCatalogList",bookCatalogList);

        return PREFIX + "bookcataloglist.html";
    }

    /**
     * 书籍目录详细信息（直接扫描播放页面二维码）
     * @param bookCatalogId
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/bcd/{bookCatalogId}")
    public String bcd(@PathVariable Integer bookCatalogId, Model model, HttpServletRequest request) {
        //log.info("bookCatalogId:"+bookCatalogId);
        model.addAttribute("bookCatalogId",bookCatalogId);

        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid!=null) return PREFIX + "bcd_to.html";

        model.addAttribute("IP",StringUtil.IP);
        model.addAttribute("appid",StringUtil.AppId);
        return PREFIX + "bcd_start.html";
    }

    /**
     * 书籍目录详细信息（直接扫描播放页面二维码的校验）
     * @param bookCatalogId
     * @param model
     * @param request
     * @return
             */
    @RequestMapping(value = "/bcdValidate")
    public String bcdValidate(String code, Integer bookCatalogId, Model model, HttpServletRequest request) {
        //log.info("validate:"+bookCatalogId+" "+code);
        // 获取微信网页授权
        JSONObject jsonObject = WeiXinInterfacetUtil.oauth2AccessTokenMap(code);
        if (jsonObject == null || jsonObject.get("access_token") == null|| jsonObject.get("openid") == null) {
            model.addAttribute("message",jsonObject.toJSONString());
            return PREFIX + "home_error.html";
        }
        //String accessToken = jsonObject.get("access_token").toString();
        String openid = jsonObject.get("openid").toString();

        Map<String, Object> member = mpService.searchMember(openid);
        if(member!=null){
            //如果是会员，则跳转到详情页
            request.getSession().setAttribute("openid",openid);
            model.addAttribute("bookCatalogId",bookCatalogId);
            return PREFIX + "bcd_to.html";
        }else{
            //如果不是会员，则跳转到认证页
            model.addAttribute("message","非授权会员。");
            return PREFIX + "home_error.html";
        }
    }

    /**
     * 书籍目录详细信息（播放页面）
     * @param bookCatalogId
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/bookCatalogDetail/{bookCatalogId}")
    public String bookCatalogDetail(@PathVariable Integer bookCatalogId, Model model, HttpServletRequest request){

        //log.info("bookCatalogDetail:"+bookCatalogId);
        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "home_start.html";

        Map<String,Object> bookCatalog = mpService.searchBookCatalogDetail(bookCatalogId);


        //还要验证一下是不是这本书的会员
        List<Map<String,Object>> bookList = mpService.searchBookList(openid);
        for(Map<String,Object> map:bookList){

            //如果有会员书籍列表有和当前目录的书籍id匹配，则表示该openid可以查看资源
            if(map.get("id").equals(bookCatalog.get("bookid"))){

                Wxcollection coll = new Wxcollection();
                coll.setOpenid(openid);
                coll.setBookcatalogid(bookCatalogId);
                Map<String,Object> collection = mpService.searchCollection(coll);

                //播放次数统计
                //int wxPlayCount = mpService.searchWxPlayCount(bookCatalogId);
                //mpService.addWxPlay(coll);

                //log.info("collection:"+collection);
                //log.info("bookCatalog:"+bookCatalog.get("id")+" "+bookCatalog);

                model.addAttribute("bookCatalog",bookCatalog);
                model.addAttribute("collection",collection);
                //model.addAttribute("wxPlayCount",wxPlayCount);

                return PREFIX + "bookcatalogdetail_link.html";

            }
        }

        model.addAttribute("message","非该书籍授权会员。");
        return PREFIX + "home_error.html";

    }

    /**
     * 我的收藏
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/collection")
    public String collection(Model model, HttpServletRequest request){

        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "home_start.html";

        List<Map<String,Object>> bookCatalogList = mpService.searchCollectionList(openid);
        model.addAttribute("bookCatalogList",bookCatalogList);

        return PREFIX + "collection.html";
    }

    /**
     * 播放器版我的收藏
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/collectionAudio1")
    public String collectionAudio1(Model model, HttpServletRequest request){

        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "home_start.html";

        List<Map<String,Object>> bookCatalogList = mpService.searchCollectionList(openid);
        model.addAttribute("bookCatalogListJson", JSONObject.toJSONString(bookCatalogList));
        //model.addAttribute("ossIP", OssUtil.ossIP);
        //log.info(JSONObject.toJSONString(bookCatalogList));
        return PREFIX + "audio1.html";
    }



    /**
     * 添加收藏
     * @return
     */
    @RequestMapping(value = "/addCollection")
    @ResponseBody
    public String addCollection(Wxcollection coll){
        log.info("addConllection:"+coll.toString());
        int index = mpService.addCollection(coll);
        return index+"";
    }

    /**
     * 取消收藏
     * @return
     */
    @RequestMapping(value = "/subCollection")
    @ResponseBody
    public String subCollection(Wxcollection coll){
        log.info("subConllection:"+coll.toString());
        int index = mpService.subCollection(coll);
        return index+"";
    }

    /**
     * 会员中心
     * @return
     */
    @RequestMapping(value = "/center")
    public String center(Model model, HttpServletRequest request){
        //首先校验session
        String openid = getSessionOpenid(model,request);
        if(openid==null) return PREFIX + "home_start.html";

        Map<String,Object> member = mpService.searchMember(openid);
        model.addAttribute("member",member);

        //书籍数量
        List<Map<String,Object>> bookList = mpService.searchBookList(openid);
        model.addAttribute("bookCount",bookList.size());
        //收藏数量
        List<Map<String,Object>> bookCatalogList = mpService.searchCollectionList(openid);
        model.addAttribute("bookCatalogCount",bookCatalogList.size());

        return PREFIX + "center.html";
    }

    /**
     * 进入到意见页面
     * @return
     */
    @RequestMapping(value = "/opinion_create")
    public String opinion_create(){
        return PREFIX + "opinion.html";
    }

    /**
     * bbs首页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/bbsHome")
    public String bbsHome(Model model, HttpServletRequest request){
        //首先校验session
        if(getSessionOpenid(model,request)==null) return PREFIX + "home_start.html";

        List<Map<String,Object>> bbsSysList = mpService.searchBbsSysList();
        List<Map<String,Object>> bbsWxList = mpService.searchBbsWxList();
        model.addAttribute("bbsSysList",bbsSysList);
        model.addAttribute("bbsWxList",bbsWxList);

        return PREFIX + "bbs_home.html";
    }

    /**
     * bbs置顶详情
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/bbsSysDetail/{bbsSysId}")
    public String bbsSysDetail(@PathVariable(value = "bbsSysId") Integer bbsSysId, Model model, HttpServletRequest request){
        //首先校验session
        if(getSessionOpenid(model,request)==null) return PREFIX + "home_start.html";

        Map<String,Object> bbsSys = mpService.searchBbsSysDetail(bbsSysId);
        model.addAttribute("bbsSys",bbsSys);

        return PREFIX + "bbs_sys_detail.html";
    }

    /**
     * bbs 用户贴详情
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/bbsWxDetail/{bbsWxId}")
    public String bbsWxDetail(@PathVariable(value = "bbsWxId") Integer bbsWxId, Model model, HttpServletRequest request){
        //首先校验session
        if(getSessionOpenid(model,request)==null) return PREFIX + "home_start.html";

        Map<String,Object> bbsWx = mpService.searchBbsWxDetail(bbsWxId);
        List<Map<String,Object>> bbsWxReplyList = mpService.searchBbsWxReplyList(bbsWxId);
        model.addAttribute("bbsWx",bbsWx);
        model.addAttribute("bbsWxReplyList",bbsWxReplyList);

        return PREFIX + "bbs_wx_detail.html";
    }

    /**
     * bbs编辑
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/bbsEdit")
    public String bbsEdit(Model model, HttpServletRequest request){
        //首先校验session
        if(getSessionOpenid(model,request)==null) return PREFIX + "home_start.html";
        return PREFIX + "bbs_wx_edit.html";
    }

    /**
     * 添加用户贴
     * @param bbsWx
     * @return
     */
    @RequestMapping(value = "/addBbsWx")
    @ResponseBody
    public String addBbsWx(BbsWx bbsWx){
        mpService.addBbsWx(bbsWx);
        return 1+"";
    }

    /**
     * 添加用户贴回复
     * @param bbsWxReply
     * @return
     */
    @RequestMapping(value = "/addBbsWxReply")
    @ResponseBody
    public String addBbsWxReply(BbsWxReply bbsWxReply){
        mpService.addBbsWxReply(bbsWxReply);
        return 1+"";
    }

    /**
     * 添加用户意见或建议
     * @param opinion
     * @return
     */
    @RequestMapping(value = "/addOpinion")
    @ResponseBody
    public String addBbsWxReply(Opinion opinion){
        mpService.addOpinion(opinion);
        return "1";
    }

}
