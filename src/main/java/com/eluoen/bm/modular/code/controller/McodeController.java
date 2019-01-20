package com.eluoen.bm.modular.code.controller;

import cn.stylefeng.guns.generator.executor.config.WebGeneratorConfig;
import cn.stylefeng.guns.generator.executor.model.GenQo;
import cn.stylefeng.guns.generator.modular.factory.DefaultTemplateFactory;
import cn.stylefeng.guns.generator.modular.service.TableService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.config.properties.DruidProperties;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.SqlRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 代码生成控制器：重写的。因为调用的方法数据库名和相关信息写死了
 *
 * @author eluoen
 * @Date 2019年01月20日
 */
@Controller
@RequestMapping("/mcode")
public class McodeController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(McodeController.class);

    private static String PREFIX = "/code";

    @Autowired
    private TableService tableService;

    @Autowired
    private DruidProperties druidProperties;

    /**
     * 跳转到代码生成主页
     */
    @RequestMapping("")
    public String blackboard(Model model) {
        log.info("mcode 这里改数据库名称-------------------------------------------");

        List<Map<String,Object>> tables = getAllTables("bm");
        GenQo params = getDefaultParams();
        List<Map<String, Object>> templates = DefaultTemplateFactory.getDefaultTemplates();

        model.addAttribute("tables", tables);
        model.addAttribute("params", params);
        model.addAttribute("templates", templates);

        //log.info("tables:"+tables.toString());
        return PREFIX + "/code.html";
    }

    /**
     * 生成代码
     */
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    public Object generate(GenQo genQo) {

        //log.info("geoQo:"+JSONObject.toJSONString(genQo));

        genQo.setUrl(druidProperties.getUrl());
        genQo.setUserName(druidProperties.getUsername());
        genQo.setPassword(druidProperties.getPassword());
        WebGeneratorConfig webGeneratorConfig = new WebGeneratorConfig(genQo);
        webGeneratorConfig.doMpGeneration();
        webGeneratorConfig.doGunsGeneration();
        return SUCCESS_TIP;
    }

    /**
     * 数据库名
     * @param dbName
     * @return
     */
    private List<Map<String, Object>> getAllTables(String dbName) {
        String sql = "select TABLE_NAME as tableName,TABLE_COMMENT as tableComment from information_schema.`TABLES` where TABLE_SCHEMA = '" + dbName + "'";
        return SqlRunner.db().selectList(sql, new Object[0]);
    }

    /**
     * 自定义
     * @return
     */
    private static GenQo getDefaultParams() {
        GenQo genQo = new GenQo();
        genQo.setProjectPath(ToolUtil.getWebRootPath((String)null));
        genQo.setAuthor("eluoen");
        genQo.setProjectPackage("com.eluoen.book");
        genQo.setCorePackage("com.eluoen.book.core");
        genQo.setIgnoreTabelPrefix("tbl_");
        genQo.setModuleName("system");
        genQo.setParentMenuName("系统管理");
        return genQo;
    }
}
