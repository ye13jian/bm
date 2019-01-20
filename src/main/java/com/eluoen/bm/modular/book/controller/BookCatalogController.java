package com.eluoen.bm.modular.book.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.eluoen.bm.modular.mp.util.StringUtil;
import com.eluoen.bm.modular.system.model.BookCatalog;
import com.eluoen.bm.modular.util.ZipUtils;
import com.eluoen.bm.config.properties.GunsProperties;
import com.eluoen.bm.core.common.constant.state.ManagerStatus;
import com.eluoen.bm.core.common.exception.BizExceptionEnum;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.book.service.IBookCatalogService;
import com.eluoen.bm.modular.mp.util.StringUtil;
import com.eluoen.bm.modular.system.model.BookCatalog;
import com.eluoen.bm.modular.util.QRCodeUtil;
import com.eluoen.bm.modular.util.ZipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 书籍目录控制器
 *
 * @author fengshuonan
 * @Date 2018-11-24 22:43:19
 */
@Controller
@RequestMapping("/bookCatalog")
public class BookCatalogController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(BookCatalogController.class);

    private String PREFIX = "/book/bookCatalog/";

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private IBookCatalogService bookCatalogService;

    /**
     * 跳转到书籍目录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bookCatalog.html";
    }

    /**
     * 跳转到添加书籍目录
     */
    @RequestMapping("/bookCatalog_add/{bookId}")
    public String bookCatalogAdd(@PathVariable Integer bookId, Model model) {
        model.addAttribute("bookId",bookId);
        return PREFIX + "bookCatalog_add.html";
    }

    /**
     * 跳转到修改书籍目录
     */
    @RequestMapping("/bookCatalog_update/{bookCatalogId}")
    public String bookCatalogUpdate(@PathVariable Integer bookCatalogId, Model model) {
        BookCatalog bookCatalog = bookCatalogService.selectById(bookCatalogId);
        model.addAttribute("item",bookCatalog);
        //model.addAttribute("ossIP",OssUtil.ossIP);
        LogObjectHolder.me().set(bookCatalog);
        return PREFIX + "bookCatalog_edit.html";
    }

    /**
     * 获取书籍目录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition, @RequestParam(required = false) Integer bookid) {
        //return bookCatalogService.selectList(null);
        log.info("bookid:"+bookid);
        if(bookid!=null&&!ToolUtil.isEmpty(bookid)&&bookid!=0){
            return bookCatalogService.selectList(condition,bookid);
        }
        return null;
    }

    /**
     * 新增书籍目录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BookCatalog bookCatalog) {

        // 完善书籍目录信息
        bookCatalog.setStatus(ManagerStatus.OK.getCode());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        bookCatalog.setCreatedate(sdf1.format(new Date()));
        bookCatalog.setCreatetime(sdf2.format(new Date()));

        bookCatalogService.insert(bookCatalog);
        return SUCCESS_TIP;
    }

    /**
     * 删除书籍目录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bookCatalogId) {

        //删除对应的oss文件
        BookCatalog bookCatalog = bookCatalogService.selectById(bookCatalogId);
        if(bookCatalog!=null&&bookCatalog.getResurl()!=null&&!bookCatalog.getResurl().equals("")){
            //OssUtil.deleteOss(bookCatalog.getResurl());
        }

        bookCatalogService.deleteById(bookCatalogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改书籍目录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BookCatalog bookCatalog) {

        //如果重新上传的oss文件，删除对应的oss文件
        BookCatalog bookCatalog1 = bookCatalogService.selectById(bookCatalog.getId());
        log.info("ossflag:"+(!bookCatalog1.getResurl().equals(bookCatalog.getResurl()))+"");
        if(bookCatalog!=null&&bookCatalog1.getResurl()!=null&&!bookCatalog1.getResurl().equals("")&&!bookCatalog1.getResurl().equals(bookCatalog.getResurl())){
            //OssUtil.deleteOss(bookCatalog1.getResurl());
        }

        bookCatalogService.updateById(bookCatalog);
        return SUCCESS_TIP;
    }

    /**
     * 书籍目录详情
     */
    @RequestMapping(value = "/detail/{bookCatalogId}")
    @ResponseBody
    public Object detail(@PathVariable("bookCatalogId") Integer bookCatalogId) {
        return bookCatalogService.selectById(bookCatalogId);
    }

    /**
     * 生成整本书中所有目录的二维码
     * @param bookId
     */
    @RequestMapping(value = "/qrcode/{bookId}")
    //@ResponseBody
    public void exportQRImages(@PathVariable("bookId") Integer bookId, HttpServletResponse response){
        System.out.println("qrcode"+bookId);
        //获取文件存储路径
        String fileSavePath = gunsProperties.getFileUploadPath();
        System.out.println("fileSavePath:"+fileSavePath);


        List<Map<String, Object>> list = bookCatalogService.selectQrList(bookId);
        if(list==null||list.size()==0){
            throw new ServiceException(BizExceptionEnum.FILE_NOT_FOUND);
        }


        File[] files = new File[list.size()];
        int width = 300;//二维码宽度
        int height = 300;//二维码高度
        String format = "png";//二维码文件后缀

        for(int i=0;i<list.size();i++){
            String path = null;
            try {
                //获取每个二维码文件名路径
                String bcname = list.get(i).get("bcname").toString();
                String qrcode_txt = StringUtil.IP+"/mp/bcd/"+list.get(i).get("bcid");
                path = QRCodeUtil.generateQRCode(qrcode_txt,width,height,format,fileSavePath+bcname+"."+format);
            } catch (Exception e) {
                e.printStackTrace();
                //可以在这里throw自定义exception
            }
            files[i]=new File(path);
        }
        System.out.println("1"+bookId);


        String fileName = list.get(0).get("bname")+".zip";

        //将所有二维码文件全部压缩到zip
        ZipUtils.ZipMultiQrcodeFile(files, fileSavePath+fileName);//测试通过可用
        System.out.println("2"+bookId);

        //删除所有生成的二维码文件，因为已经有zip了
        for (File file:files){
            file.delete();
        }




        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;

        try {

            //设置
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;");
            //response.setHeader("Content-Disposition", "attachment;filename=" + fileName);中文文件名要转码
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

            //输入zip文件流
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(fileSavePath+ fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");



    }
}
