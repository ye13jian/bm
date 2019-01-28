package com.eluoen.bm.modular.system.controller;

import cn.stylefeng.roses.core.util.FileUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.eluoen.bm.config.properties.GunsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码生成
 *
 * @author fengshuonan
 * @date 2017-05-05 23:10
 */
@Controller
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private Producer producer;

    /**
     * 生成验证码
     */
    @RequestMapping("")
    public void index(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = producer.createText();

        // store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write the data out
        try {
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回图片
     *
     * @author stylefeng
     * @Date 2017/5/24 23:00
     */
    //下面这种方式对带后缀的资源会造成后缀部分不显示
    //@RequestMapping("/{pictureId}")
    //public void renderPicture(@PathVariable("pictureId") String pictureId, HttpServletResponse response) {

    @RequestMapping("/kap")
    public void renderPicture(@RequestParam("name") String pictureId, HttpServletResponse response) {
        //String path = gunsProperties.getFileUploadPath() + pictureId + ".jpg";//传过来的是已经带后缀的文件名了
        String path = gunsProperties.getFileUploadPath() + pictureId;
        try {
            byte[] bytes = FileUtil.toByteArray(path);
            response.getOutputStream().write(bytes);
        }catch (Exception e){
            //如果找不到图片就返回一个默认图片
            try {
                if(pictureId.contains(".")){
                    String pix = pictureId.substring(pictureId.lastIndexOf(".")+1,pictureId.length());
                    //20181125如果是图片才返回一个默认的图片，其它资源如音视频不用返回
                    if("jpg".equals(pix)||"JPG".equals("pix")||"png".equals(pix)||"PNG".equals(pix)||"jpeg".equals(pix)||"JPEG".equals(pix)){
                        response.sendRedirect("/static/img/girl.gif");
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }





}
