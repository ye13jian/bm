package com.eluoen.bm.modular.mp.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.alibaba.fastjson.JSONObject;
import com.eluoen.bm.modular.mp.service.IMpWxService;
import com.eluoen.bm.modular.mp.util.MpWxUtil;
import com.eluoen.bm.modular.mp.util.ReceiptMsg;
import com.eluoen.bm.modular.mp.util.StringUtil;
import com.eluoen.bm.modular.mp.util.WeiXinInterfacetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 微信公众号开发
 */
@Controller
@RequestMapping(value = "/mp/wx")
public class MpWxControll extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(MpWxControll.class);

    @Autowired
    private IMpWxService mpWxService;

    /**
     * 默认微信开发入口
     * @return
     */
    @RequestMapping(value = "")
    public String weixin(HttpServletRequest req, HttpServletResponse resp){
        String reqURI = req.getRequestURI();
        String method = req.getMethod();
        log.info("mpwx:" + method + ":" + reqURI);
        if (method.equals("GET")) {
            get(req, resp);
        }else if (method.equals("POST")) {
            post(req, resp);
        }
        return null;
    }

    /**
     * 微信验证
     * @param req
     * @param resp
     */
    private void get(HttpServletRequest req, HttpServletResponse resp) {

        try {

            String signature = req.getParameter("signature");
            String timestamp = req.getParameter("timestamp");
            String nonce = req.getParameter("nonce");
            String echostr = req.getParameter("echostr");

            System.out.println("wylrb weixin[GET]:" + signature + "\t" + timestamp + "\t" + nonce + "\t" + echostr);

            if (signature != null && timestamp != null && nonce != null
                    && echostr != null) {
                if (StringUtil.checkSignature(signature, timestamp, nonce)) {
                    PrintWriter out = resp.getWriter();
                    out.print(echostr);
                    out.flush();
                    out.close();
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    /**
     * 业务
     * @param req
     * @param resp
     */
    private void post(HttpServletRequest req, HttpServletResponse resp) {

        try {

            ReceiptMsg msg = (ReceiptMsg) MpWxUtil.xmlToBean(req,ReceiptMsg.class);
            log.info("msg:"+msg.toString());

            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/xml;charset=UTF-8");
            //如果是关注公众号
            if("subscribe".equals(msg.getEvent())){
                subscribe(msg,req,resp);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 如果是关注公众号
     * @param msg
     * @return
     */
    public void subscribe(ReceiptMsg msg, HttpServletRequest req, HttpServletResponse resp) throws IOException {


        //返回数据
        String respXML = keywordReply("关注",msg);
        log.info("subscribe resp:"+respXML);
        PrintWriter out = resp.getWriter();
        out.write(respXML);
        out.close();


        String openid = msg.getFromUserName();
        String openids = JSONObject.toJSONString(new String[]{openid});

        //先查应该打哪些标签
        List<String> list = mpWxService.searchMemberTagName(openid);

        if(list!=null&&list.size()>0){

            for(String tagName:list){
                log.info("公众号tagName:"+tagName);
                log.info(Charset.defaultCharset()+"");
                log.info(System.getProperties()+"");
                JSONObject token = WeiXinInterfacetUtil.accessToken();
                String ACCESS_TOKEN = token.get("access_token").toString();

                //查看标签是否在数据库存在，如果有，表明公众号中已经创建过了
                Integer tagId = mpWxService.searchTag(tagName);
                log.info("tagId1:"+tagId);
                if(tagId==null){
                    //不存在则创建标签
                    log.info("公众号2tagName:"+tagName);
                    JSONObject tags = WeiXinInterfacetUtil.createTags(ACCESS_TOKEN,tagName);
                    JSONObject tag = (JSONObject) tags.get("tag");
                    if(tag!=null){
                        tagId = tag.getInteger("id");

                        //然后插入标签记录
                        mpWxService.addTag(tagName,tagId);
                    }

                }

                log.info("tagId2:"+tagId);

                //然后为用户打标签
                if(tagId!=null){
                    WeiXinInterfacetUtil.batchtaggingTags(ACCESS_TOKEN,openids,tagId);
                }

            }

        }

    }

    /**
     * 关键字回复
     * @param keyword
     * @return
     */
    public String keywordReply(String keyword,ReceiptMsg msg){
        //回复文本消息
        //<xml> <ToUserName>< ![CDATA[toUser] ]></ToUserName> <FromUserName>< ![CDATA[fromUser] ]></FromUserName> <CreateTime>12345678</CreateTime> <MsgType>< ![CDATA[text] ]></MsgType> <Content>< ![CDATA[你好] ]></Content> </xml>
        String reply = mpWxService.searchWxReply(keyword);
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA["+msg.getFromUserName()+"]]></ToUserName>");
        sb.append("<FromUserName><![CDATA["+msg.getToUserName()+"]]></FromUserName>");
        sb.append("<CreateTime>12345678</CreateTime>");
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        sb.append("<Content><![CDATA["+reply+"]]></Content>");
        sb.append("</xml>");
        return sb.toString();
    }


    public static void main(String[] args) {

        try {
            JSONObject token = WeiXinInterfacetUtil.accessToken();
            String access_token = token.get("access_token").toString();
            String tagName = "测试中文啊4";
            log.info(tagName);
            JSONObject tags = WeiXinInterfacetUtil.createTags(access_token,tagName);
            log.info("tags:"+tags);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
