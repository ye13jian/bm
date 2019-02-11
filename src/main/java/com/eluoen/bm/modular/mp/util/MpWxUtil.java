package com.eluoen.bm.modular.mp.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class MpWxUtil {

    private final static Logger log = LoggerFactory.getLogger(MpWxUtil.class);


    public static Object toBean(Class<?> clazz, String xml) {
        Object xmlObject = null;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);

        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(new Class[]{clazz});

        //SpringBoot环境下，XML转对象时，同一个类无法进行转换，原因是因为SpringBoot重新加载了对象；若未指定classloader的时候，SpringBoot未正确使用classloader，需要指定classloader，需要在方法中指定加载的类，添加如下代码：
        //xstream.setClassLoader(clazz.getClassLoader());
        xstream.setClassLoader(clazz.getClassLoader());

        xstream.autodetectAnnotations(true);
        xmlObject= xstream.fromXML(xml);
        return xmlObject;
    }

    /**
     * 获取数据流，转换成String
     * @param req
     * @return
     * @throws IOException
     */
    private static String getReqText(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        StringBuffer sb = new StringBuffer();
        byte[] bs = new byte[4096];
        for (int n; (n = is.read(bs)) != -1; ) {
            sb.append(new String(bs, 0, n, Charset.forName("UTF-8")));
        }
        return sb.toString();
    }

    /**
     * xml转成bean泛型方法
     * @param req
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T xmlToBean(HttpServletRequest req, Class clazz) throws IOException {

        String resultXml = getReqText(req);

        // XStream对象设置默认安全防护，同时设置允许的类
        XStream stream = new XStream(new DomDriver("UTF-8"));
        XStream.setupDefaultSecurity(stream);
        stream.allowTypes(new Class[]{clazz});
        stream.processAnnotations(new Class[]{clazz});
//        stream.setMode(XStream.NO_REFERENCES);

        //SpringBoot环境下，XML转对象时，同一个类无法进行转换，原因是因为SpringBoot重新加载了对象；若未指定classloader的时候，SpringBoot未正确使用classloader，需要指定classloader，需要在方法中指定加载的类，添加如下代码：
        //xstream.setClassLoader(clazz.getClassLoader());
        //springboot下一定加这一句，不然会报 A cannot be cast to A 错误 (eluoen 20181213)
        stream.setClassLoader(clazz.getClassLoader());


        stream.alias("xml", clazz);
        return (T) stream.fromXML(resultXml);
    }

    public static void main(String[] args) {

        String xml = "<xml><ToUserName><![CDATA[gh_c43fa48fbad8]]></ToUserName>\n" +
                "<FromUserName><![CDATA[o5kXljm1CXR4fopA_mEA8z8lW5Rw]]></FromUserName>\n" +
                "<CreateTime>1544716396</CreateTime>\n" +
                "<MsgType><![CDATA[event]]></MsgType>\n" +
                "<Event><![CDATA[subscribe]]></Event>\n" +
                "<EventKey><![CDATA[]]></EventKey>\n" +
                "</xml>";

        ReceiptMsg msg = (ReceiptMsg) toBean(ReceiptMsg.class,xml);



        log.info(msg.toString());
    }

}
