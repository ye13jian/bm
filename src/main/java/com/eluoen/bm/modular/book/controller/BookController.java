package com.eluoen.bm.modular.book.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.eluoen.bm.modular.book.service.IBookService;
import com.eluoen.bm.modular.system.model.Book;
import com.eluoen.bm.core.common.constant.state.ManagerStatus;

import com.eluoen.bm.core.common.node.ZTreeNode;
import com.eluoen.bm.core.log.LogObjectHolder;
import com.eluoen.bm.modular.book.service.IBookService;
import com.eluoen.bm.modular.system.model.Book;
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
import java.util.List;

/**
 * 书籍管理控制器
 *
 * @author fengshuonan
 * @Date 2018-11-17 23:49:39
 */
@Controller
@RequestMapping("/book")
public class BookController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(BookController.class);

    private String PREFIX = "/book/book/";

    @Autowired
    private IBookService bookService;

    /**
     * 跳转到书籍管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "book.html";
    }

    /**
     * 跳转到添加书籍管理
     */
    @RequestMapping("/book_add")
    public String bookAdd() {
        return PREFIX + "book_add.html";
    }

    /**
     * 跳转到修改书籍管理
     */
    @RequestMapping("/book_update/{bookId}")
    public String bookUpdate(@PathVariable Integer bookId, Model model) {
        Book book = bookService.selectById(bookId);
        model.addAttribute("item",book);
        LogObjectHolder.me().set(book);
        return PREFIX + "book_edit.html";
    }

    /**
     * 获取书籍管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        log.info("book/list---------------------------------------------------");
        if(ToolUtil.isEmpty(condition)){
            return bookService.selectList(null);
        }else{
            EntityWrapper<Book> entityWrapper = new EntityWrapper<Book>();
            Wrapper<Book> wrapper = entityWrapper.like("name",condition);
            return bookService.selectList(wrapper);
        }
    }

    /**
     * 获取书籍的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.bookService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 新增书籍管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Book book) {

        // 完善书籍信息
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        book.setCreatedate(sdf1.format(new Date()));
        book.setCreatetime(sdf2.format(new Date()));
        bookService.insert(book);
        return SUCCESS_TIP;
    }

    /**
     * 删除书籍管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bookId) {
        bookService.deleteById(bookId);
        return SUCCESS_TIP;
    }

    /**
     * 修改书籍管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Book book) {
        bookService.updateById(book);
        return SUCCESS_TIP;
    }

    /**
     * 书籍管理详情
     */
    @RequestMapping(value = "/detail/{bookId}")
    //@ResponseBody
    public Object detail(@PathVariable("bookId") Integer bookId, Model model) {
        Book book = this.bookService.selectById(bookId);
        model.addAttribute("item",book);
        return PREFIX + "book_view.html";
    }
}
